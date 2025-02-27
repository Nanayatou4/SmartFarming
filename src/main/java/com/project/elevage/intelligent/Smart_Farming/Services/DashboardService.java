package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Dashboard.DashboardEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.DashboardEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DashboardService {

    @Autowired
    private DashboardEntityRepository dashboardRepository;

    /**
     * Créer un nouveau dashboard
     * @param dashboard Le dashboard à créer
     * @return Le dashboard créé
     */
    public DashboardEntity createDashboard(DashboardEntity dashboard) {
        return dashboardRepository.save(dashboard);
    }

    /**
     * Partager un dashboard avec un autre utilisateur
     * @param dashboardId L'ID du dashboard
     * @param userId L'ID de l'utilisateur
     */
    public void shareDashboard(Long dashboardId, Long userId) {
        Optional<DashboardEntity> dashboard = dashboardRepository.findById(dashboardId);
        if (dashboard.isPresent()) {
            DashboardEntity sharedDashboard = dashboard.get();
            // Ajouter la logique pour associer l'utilisateur au dashboard (par exemple, un partage)
            // sharedDashboard.addUser(userId);
            dashboardRepository.save(sharedDashboard);
        } else {
            throw new RuntimeException("Dashboard non trouvé");
        }
    }

    /**
     * Personnaliser un dashboard (modifier l'agencement ou les widgets)
     * @param dashboardId L'ID du dashboard à personnaliser
     * @param dashboard L'objet dashboard mis à jour
     * @return Le dashboard mis à jour
     */
    public DashboardEntity customizeDashboard(Long dashboardId, DashboardEntity dashboard) {
        Optional<DashboardEntity> existingDashboard = dashboardRepository.findById(dashboardId);
        if (existingDashboard.isPresent()) {
            DashboardEntity updatedDashboard = existingDashboard.get();
            updatedDashboard.setName(dashboard.getName());
            updatedDashboard.setWidgets(dashboard.getWidgets());  // Mise à jour des widgets
            return dashboardRepository.save(updatedDashboard);
        }
        throw new RuntimeException("Dashboard non trouvé");
    }

    /**
     * Supprimer un dashboard
     * @param dashboardId L'ID du dashboard à supprimer
     */
    public void deleteDashboard(Long dashboardId) {
        Optional<DashboardEntity> dashboard = dashboardRepository.findById(dashboardId);
        if (dashboard.isPresent()) {
            dashboardRepository.delete(dashboard.get());
        } else {
            throw new RuntimeException("Dashboard non trouvé");
        }
    }

    /**
     * Récupérer tous les dashboards pour un locataire
     * @return Liste des dashboards
     */
    public List<DashboardEntity> getAllDashboards() {
        return dashboardRepository.findAll();
    }
}
