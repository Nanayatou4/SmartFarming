package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Resources.TenantResource;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TenantResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantResourceService {

    @Autowired
    private TenantResourceRepository resourceRepository;


    public TenantResource allocateResources(String tenantId, Long storageQuota, Integer maxDevices) {
        TenantResource resource = new TenantResource();
        resource.setTenantId(tenantId);
        resource.setStorageQuota(storageQuota);
        resource.setUsedStorage(0L); // Par défaut, 0 utilisé
        resource.setMaxDevices(maxDevices);
        return resourceRepository.save(resource);
    }

    public TenantResource getResourceUsage(String tenantId) {
        return resourceRepository.findByTenantId(tenantId);
    }

    public void updateUsedStorage(String tenantId, Long usedStorage) {
        TenantResource resource = resourceRepository.findByTenantId(tenantId);
        if (resource != null) {
            resource.setUsedStorage(usedStorage);
            resourceRepository.save(resource);
        }
    }
}
