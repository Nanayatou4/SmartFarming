package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TenantEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TenantService {

    @Autowired
    private TenantEntityRepository tenantRepository;

    @Autowired
    private EmailService emailService;

    private static final Logger logger = LoggerFactory.getLogger(TenantService.class);

    public TenantEntity createTenant(TenantEntity tenant) {
        logger.info("Création du tenant: {}", tenant.getTitle());
        return tenantRepository.save(tenant);
    }

    public TenantEntity updateTenant(Long id, TenantEntity updatedTenant) {
        return tenantRepository.findById(id).map(tenant -> {
            tenant.setTitle(updatedTenant.getTitle());
            tenant.setEmail(updatedTenant.getEmail());
            tenant.setPhone(updatedTenant.getPhone());
            tenant.setAddress(updatedTenant.getAddress());
            tenant.setRegion(updatedTenant.getRegion());
            tenant.setSearchText(updatedTenant.getSearchText());
            tenant.setDescription(updatedTenant.getDescription());
            return tenantRepository.save(tenant);
        }).orElseThrow(() -> new RuntimeException("Tenant non trouvé"));
    }

    public List<TenantEntity> searchTenantsByTitle(String title) {
        return tenantRepository.findByTitleContainingIgnoreCase(title);
    }

    public Page<TenantEntity> getAllTenantsWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return tenantRepository.findAllWithPagination(pageable);
    }

    public TenantEntity toggleTenantStatus(Long id, boolean isActive) {
        return tenantRepository.findById(id).map(tenant -> {
            tenant.setActive(isActive);
            return tenantRepository.save(tenant);
        }).orElseThrow(() -> new RuntimeException("Tenant non trouvé"));
    }

    public void deleteTenant(Long id) {
        logger.warn("Suppression du tenant ID: {}", id);
        tenantRepository.deleteById(id);
        emailService.sendAlert("sysadmin@smartfarming.com", "Alerte - Suppression d'un locataire",
                "Le locataire ID: " + id + " a été supprimé.");
    }

    public List<TenantEntity> getAllTenants() {
        return tenantRepository.findAll();
    }

}
