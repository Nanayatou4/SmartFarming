package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Asset.AssetEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.TenantAdmin.TenantAdminEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.AssetEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TenantAdminEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TenantEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    @Autowired
    private AssetEntityRepository assetRepository;

    @Autowired
    private TenantAdminEntityRepository tenantAdminRepository;

    @Autowired
    private TenantEntityRepository tenantRepository;

                               ///  ************ADMIN SYSTEM************


    /**
     * Ajouter un asset dans un tenant (Admin System)
     */
    public AssetEntity addAssetToTenant(Long tenantId, AssetEntity asset) {
        TenantEntity tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant introuvable"));

        asset.setTenant(tenant); // Associer l'asset au tenant
        return assetRepository.save(asset);
    }

    /**
     * Modifier un asset existant (Admin System)
     */
    public AssetEntity updateAsset(Long assetId, AssetEntity asset) {
        return assetRepository.findById(assetId)
                .map(existingAsset -> {
                    // Mettre à jour les champs uniquement si la valeur n'est pas nulle
                    if (asset.getName() != null) {
                        existingAsset.setName(asset.getName());
                    }
                    if (asset.getDescription() != null) {
                        existingAsset.setDescription(asset.getDescription());
                    }
                    if (asset.getDevice() != null) {
                        existingAsset.setDevice(asset.getDevice());
                    }
                    return assetRepository.save(existingAsset);
                })
                .orElseThrow(() -> new EntityNotFoundException("Objet non trouvé avec ID: " + assetId));
    }


    /**
     * Supprimer un asset (Admin System)
     */
    public void deleteAsset(Long assetId) {
        AssetEntity asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new EntityNotFoundException("Objet non trouvé avec ID: " + assetId));
        assetRepository.delete(asset);
    }


    /**
     * Récupérer tous les assets (Admin System)
     */
    public List<AssetEntity> getAllAssets() {
        return assetRepository.findAll();
    }


             /// **************TENANT ADMIN***************
    /**
     * Vérifier si le Tenant Admin appartient bien au Tenant
     */
    private void verifierTenantAdmin(String email, Long tenantId) throws AccessDeniedException {
        TenantAdminEntity tenantAdmin = tenantAdminRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("TenantAdmin non trouvé"));

        if (!tenantAdmin.getTenant().getId().equals(tenantId)) {
            throw new AccessDeniedException("Accès refusé : ce TenantAdmin n'appartient pas à ce tenant !");
        }
    }

    /**
     * Ajouter un asset (Tenant Admin uniquement pour son tenant)
     */
    public AssetEntity addAssetAsTenantAdmin(String email, Long tenantId, AssetEntity asset) throws AccessDeniedException {
        verifierTenantAdmin(email, tenantId);  // Vérifier l'appartenance au tenant

        TenantEntity tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant non trouvé"));

        asset.setTenant(tenant);
        return assetRepository.save(asset);
    }

    /**
     * Modifier un asset (Tenant Admin uniquement pour son tenant)
     */
    public AssetEntity updateAssetAsTenantAdmin(String email, Long tenantId, Long assetId, AssetEntity asset) throws AccessDeniedException {
        verifierTenantAdmin(email, tenantId);  // Vérifier l'appartenance au tenant

        AssetEntity existingAsset = assetRepository.findById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset non trouvé"));

        // Vérifier si l'asset appartient bien au tenant
        if (!existingAsset.getTenant().getId().equals(tenantId)) {
            throw new AccessDeniedException("Accès refusé : cet asset n'appartient pas à ce tenant !");
        }

        existingAsset.setName(asset.getName());
        existingAsset.setDescription(asset.getDescription());
        existingAsset.setDevice(asset.getDevice());
        return assetRepository.save(existingAsset);
    }

    /**
     * Supprimer un asset (Tenant Admin uniquement pour son tenant)
     */
    public void deleteAssetAsTenantAdmin(String email, Long tenantId, Long assetId) throws AccessDeniedException {
        verifierTenantAdmin(email, tenantId);  // Vérifier l'appartenance au tenant

        AssetEntity asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset non trouvé"));

        // Vérifier si l'asset appartient bien au tenant
        if (!asset.getTenant().getId().equals(tenantId)) {
            throw new AccessDeniedException("Accès refusé : cet asset n'appartient pas à ce tenant !");
        }

        assetRepository.delete(asset);
    }

//    //  Méthodes pour les Éleveurs et les Vétérinaires
//
//    /**
//     * Récupérer les assets spécifiques à un éleveur
//     */
//    public List<AssetEntity> getAssetsForFarmer(Long farmerId) {
//        User farmer = userRepository.findById(farmerId)
//                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé"));
//
//        if (!farmer.getRole().equals("ELEVEUR")) {
//            throw new SecurityException("Seul un éleveur peut voir ses assets.");
//        }
//
//        return assetRepository.findByUserId(farmerId);
//    }
//
//    /**
//     * Récupérer les assets spécifiques à un vétérinaire
//     */
//    public List<AssetEntity> getAssetsForVet(Long vetId) {
//        User vet = userRepository.findById(vetId)
//                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé"));
//
//        if (!vet.getRole().equals("VETERINAIRE")) {
//            throw new SecurityException("Seul un vétérinaire peut voir les assets liés aux animaux.");
//        }
//
//        return assetRepository.findAssetsForVeterinaire(vetId);
//    }
}
