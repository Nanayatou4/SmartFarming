package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Eleveur.EleveurEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.TenantAdmin.TenantAdminEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Veterinaire.VeterinaireEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.EleveurEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TenantAdminEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TenantEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.VeterinaireEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TenantAdminService {
    @Autowired
    private TenantAdminEntityRepository tenantAdminRepository;

    @Autowired
    private TenantEntityRepository tenantRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EleveurEntityRepository eleveurRepository;

    @Autowired
    private VeterinaireEntityRepository veterinaireRepository;


    public TenantAdminEntity createTenantAdmin(Long tenantId, TenantAdminEntity admin) {
        TenantEntity tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant non trouvé"));

        admin.setTenant(tenant);
        admin.setPassword(passwordEncoder.encode(admin.getPassword())); // Crypter le mot de passe
        return tenantAdminRepository.save(admin);
    }

    public TenantAdminEntity updateTenantAdmin(Long id, TenantAdminEntity updatedAdmin) {
        return tenantAdminRepository.findById(id).map(admin -> {
            admin.setEmail(updatedAdmin.getEmail());
            admin.setPassword(updatedAdmin.getPassword());
            return tenantAdminRepository.save(admin);
        }).orElseThrow(() -> new RuntimeException("Admin non trouvé"));
    }

    public void deleteTenantAdmin(Long id) {
        tenantAdminRepository.deleteById(id);
    }

    public List<TenantAdminEntity> getAllTenantAdmins() {
        return tenantAdminRepository.findAll();
    }

    public List<TenantAdminEntity> getTenantAdminsByTenant(Long tenantId) {
        return tenantAdminRepository.findByTenant_Id(tenantId);
    }


}
