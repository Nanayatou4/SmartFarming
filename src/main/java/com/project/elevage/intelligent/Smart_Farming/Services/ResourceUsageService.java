package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Resources.ResourceUsage;
import com.project.elevage.intelligent.Smart_Farming.Repositories.ResourceUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResourceUsageService {

    @Autowired
    private ResourceUsageRepository resourceUsageRepository;

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
