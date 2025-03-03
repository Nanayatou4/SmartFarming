package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Asset.AssetEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.AssetEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    @Autowired
    private AssetEntityRepository assetRepository;

    /**
     * Ajouter un nouvel objet (asset)
     * @param asset L'objet à ajouter
     * @return L'objet ajouté
     */
    public AssetEntity addAsset(AssetEntity asset) {
        return assetRepository.save(asset);
    }

    /**
     * Modifier un objet (asset) existant
     * @param assetId L'ID de l'objet à modifier
     * @param asset L'objet mis à jour
     * @return L'objet mis à jour
     */
    public AssetEntity updateAsset(Long assetId, AssetEntity asset) {
        return assetRepository.findById(assetId)
                .map(existingAsset -> {
                    existingAsset.setName(asset.getName());
                    existingAsset.setDescription(asset.getDescription());
                    existingAsset.setDevice(asset.getDevice());
                    return assetRepository.save(existingAsset);
                })
                .orElseThrow(() -> new EntityNotFoundException("Objet non trouvé avec ID: " + assetId));
    }


    /**
     * Supprimer un objet (asset)
     * @param assetId L'ID de l'objet à supprimer
     */
    public void deleteAsset(Long assetId) {
        AssetEntity asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new EntityNotFoundException("Objet non trouvé avec ID: " + assetId));
        assetRepository.delete(asset);
    }


    /**
     * Récupérer la liste des objets pour un locataire
     * @return Liste des objets
     */
    public List<AssetEntity> getAllAssets() {
        return assetRepository.findAll();
    }
}
