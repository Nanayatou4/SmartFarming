package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Resources.ResourceEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Resources.ResourceUsage;
import com.project.elevage.intelligent.Smart_Farming.Entities.Resources.TenantResource;
import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.ResourceEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.ResourceUsageRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TenantEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TenantResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResourceManagementService {

    @Autowired
    private ResourceEntityRepository resourceRepository;

    @Autowired
    private TenantResourceRepository tenantResourceRepository;

    @Autowired
    private ResourceUsageRepository resourceUsageRepository;

    @Autowired
    private TenantEntityRepository tenantRepository;


    // Attribuer une ressource à un Tenant
    public ResourceEntity assignResourceToTenant(Long tenantId, ResourceEntity resource) {
        TenantEntity tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant non trouvé"));
        resource.setTenant(tenant);
        return resourceRepository.save(resource);
    }


    // Modifier une ressource
    public ResourceEntity updateResource(Long resourceId, ResourceEntity updatedResource) {
        return resourceRepository.findById(resourceId).map(resource -> {
            if (updatedResource.getType() != null) {
                resource.setType(updatedResource.getType());
            }
            if (updatedResource.getDescription() != null) {
                resource.setDescription(updatedResource.getDescription());
            }
            if (updatedResource.getLimitValue() != null) {
                resource.setLimitValue(updatedResource.getLimitValue());
            }
            if (updatedResource.getIsActive() != null) {
                resource.setIsActive(updatedResource.getIsActive());
            }
            return resourceRepository.save(resource);
        }).orElseThrow(() -> new RuntimeException("Ressource non trouvée"));
    }


    // Supprimer une ressource
    public void deleteResource(Long resourceId) {
        if (!resourceRepository.existsById(resourceId)) {
            throw new RuntimeException("Ressource non trouvée");
        }
        resourceRepository.deleteById(resourceId);
    }

    // Récupérer les ressources d’un Tenant
    public List<ResourceEntity> getResourcesByTenant(Long tenantId) {
        return resourceRepository.findByTenantId(tenantId);
    }

    // Allouer un quota de ressources (Stockage, Appareils) à un Tenant
    public TenantResource allocateResources(Long tenantId, Long storageQuota, Integer maxDevices) {
        TenantEntity tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant non trouvé"));

        TenantResource resource = new TenantResource();
        resource.setTenant(tenant);
        resource.setStorageQuota(storageQuota);
        resource.setUsedStorage(0L);
        resource.setMaxDevices(maxDevices);

        return tenantResourceRepository.save(resource);
    }

    // Suivre l’allocation des ressources
    public TenantResource getResourceAllocation(Long tenantId) {
        return tenantResourceRepository.findByTenantId(tenantId);  // Utiliser tenantId
    }

    public TenantResource getResourceUsage(Long tenantId) {
        return tenantResourceRepository.findByTenantId(tenantId);
    }

    // Mettre à jour le stockage utilisé
    public void updateUsedStorage(Long tenantId, Long usedStorage) {
        TenantResource resource = tenantResourceRepository.findByTenantId(tenantId);
        if (resource != null) {
            resource.setUsedStorage(usedStorage);
            tenantResourceRepository.save(resource);
        } else {
            throw new RuntimeException("Resource not found for tenant ID: " + tenantId);
        }
    }

    // Suivre l'utilisation des ressources
    public List<ResourceUsage> getAllUsages() {
        return resourceUsageRepository.findAll();
    }

    public List<ResourceUsage> getUsageByTenant(Long tenantId) {
        return resourceUsageRepository.findByTenantId(tenantId);
    }

    public ResourceUsage saveUsage(ResourceUsage usage) {
        usage.setLastUpdated(LocalDateTime.now());
        return resourceUsageRepository.save(usage);
    }
}
