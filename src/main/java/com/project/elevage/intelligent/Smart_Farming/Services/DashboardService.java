package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Dashboard.DashboardEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.UserEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.DashboardEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TenantEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DashboardService {

    @Autowired
    private DashboardEntityRepository dashboardRepository;

    @Autowired
    private TenantEntityRepository tenantRepository;

    @Autowired
    private UserEntityRepository userRepository;


    /**
     * Créer un dashboard pour un Tenant spécifique
     */
    public DashboardEntity createDashboard(Long tenantId, DashboardEntity dashboard) {
        TenantEntity tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant non trouvé"));

        dashboard.setTenant(tenant);
        return dashboardRepository.save(dashboard);
    }

    /**
     * Récupérer tous les dashboards d'un Tenant
     */
    public List<DashboardEntity> getDashboardsByTenant(Long tenantId) {
        TenantEntity tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant non trouvé"));

        return dashboardRepository.findByTenant(tenant);
    }

    /**
     * Modifier un dashboard
     */
    public DashboardEntity updateDashboard(Long dashboardId, DashboardEntity dashboardDetails) {
        DashboardEntity dashboard = dashboardRepository.findById(dashboardId)
                .orElseThrow(() -> new RuntimeException("Dashboard non trouvé"));

        dashboard.setName(dashboardDetails.getName());
        dashboard.setDescription(dashboardDetails.getDescription());

        return dashboardRepository.save(dashboard);
    }



    /**
     * Personnaliser un dashboard (modifier l'agencement ou les widgets)
     * @param dashboardId L'ID du dashboard à personnaliser
     * @param dashboard L'objet dashboard mis à jour
     * @return Le dashboard mis à jour
     */
    public DashboardEntity customizeDashboard(Long dashboardId, DashboardEntity dashboard) {
        DashboardEntity existingDashboard = dashboardRepository.findById(dashboardId)
                .orElseThrow(() -> new RuntimeException("Dashboard non trouvé"));

        existingDashboard.setName(dashboard.getName());
        existingDashboard.setWidgets(dashboard.getWidgets());

        return dashboardRepository.save(existingDashboard);
    }

    /**
     * Supprimer un dashboard
     * @param dashboardId L'ID du dashboard à supprimer
     */
    public void deleteDashboard(Long dashboardId) {
        if (!dashboardRepository.existsById(dashboardId)) {
            throw new RuntimeException("Dashboard non trouvé");
        }
        dashboardRepository.deleteById(dashboardId);
    }


    /**
     * Récupérer tous les dashboards pour un locataire
     * @return Liste des dashboards
     */
    public List<DashboardEntity> getAllDashboardsForAdmin() {
        return dashboardRepository.findAll();
    }

    /**
     * Récupérer les dashboards accessibles par un utilisateur
     */
    public List<DashboardEntity> getUserDashboards(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        return dashboardRepository.findByTenant(user.getTenant());
    }

}
