package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Resources.ResourceEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.ResourceEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TenantEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    @Autowired
    private ResourceEntityRepository resourceRepository;

    @Autowired
    private TenantEntityRepository tenantRepository;

    // ✅ Attribuer une ressource à un Tenant
    public ResourceEntity assignResourceToTenant(Long tenantId, ResourceEntity resource) {
        TenantEntity tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant non trouvé"));

        resource.setTenant(tenant);
        return resourceRepository.save(resource);
    }

    // ✅ Modifier une ressource
    public ResourceEntity updateResource(Long resourceId, ResourceEntity updatedResource) {
        return resourceRepository.findById(resourceId).map(resource -> {
            resource.setType(updatedResource.getType());
            resource.setDescription(updatedResource.getDescription());
            resource.setLimitValue(updatedResource.getLimitValue());
            resource.setIsActive(updatedResource.getIsActive());
            return resourceRepository.save(resource);
        }).orElseThrow(() -> new RuntimeException("Ressource non trouvée"));
    }

    // ✅ Supprimer une ressource
    public void deleteResource(Long resourceId) {
        resourceRepository.deleteById(resourceId);
    }

    // ✅ Récupérer les ressources d’un Tenant
    public List<ResourceEntity> getResourcesByTenant(Long tenantId) {
        return resourceRepository.findByTenantId(tenantId);
    }
}
