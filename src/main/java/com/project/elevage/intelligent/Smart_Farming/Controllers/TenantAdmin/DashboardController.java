package com.project.elevage.intelligent.Smart_Farming.Controllers.TenantAdmin;

import com.project.elevage.intelligent.Smart_Farming.Entities.Dashboard.DashboardEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboards")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    /**
     * Créer un nouveau dashboard
     * @param dashboard Le dashboard à créer
     * @return Réponse avec le dashboard créé
     */
    @PostMapping("/create")
    public ResponseEntity<DashboardEntity> createDashboard(@RequestBody DashboardEntity dashboard) {
        DashboardEntity newDashboard = dashboardService.createDashboard(dashboard);
        return ResponseEntity.ok(newDashboard);
    }

    /**
     * Partager un dashboard avec un autre utilisateur
     * @param dashboardId L'ID du dashboard à partager
     * @param userId L'ID de l'utilisateur avec lequel partager
     * @return Réponse avec le message de confirmation
     */
    @PostMapping("/share/{dashboardId}/{userId}")
    public ResponseEntity<String> shareDashboard(@PathVariable Long dashboardId, @PathVariable Long userId) {
        dashboardService.shareDashboard(dashboardId, userId);
        return ResponseEntity.ok("Dashboard partagé avec succès");
    }

    /**
     * Personnaliser un dashboard (modifier les widgets ou l'agencement)
     * @param dashboardId L'ID du dashboard à personnaliser
     * @param dashboard L'objet dashboard mis à jour
     * @return Réponse avec le dashboard mis à jour
     */
    @PutMapping("/customize/{dashboardId}")
    public ResponseEntity<DashboardEntity> customizeDashboard(@PathVariable Long dashboardId, @RequestBody DashboardEntity dashboard) {
        DashboardEntity updatedDashboard = dashboardService.customizeDashboard(dashboardId, dashboard);
        return ResponseEntity.ok(updatedDashboard);
    }

    /**
     * Supprimer un dashboard
     * @param dashboardId L'ID du dashboard à supprimer
     * @return Réponse avec le message de confirmation
     */
    @DeleteMapping("/delete/{dashboardId}")
    public ResponseEntity<String> deleteDashboard(@PathVariable Long dashboardId) {
        dashboardService.deleteDashboard(dashboardId);
        return ResponseEntity.ok("Dashboard supprimé avec succès");
    }

    /**
     * Récupérer tous les dashboards pour un locataire
     * @return Liste des dashboards
     */
    @GetMapping("/list")
    public ResponseEntity<List<DashboardEntity>> getAllDashboards() {
        List<DashboardEntity> dashboards = dashboardService.getAllDashboards();
        return ResponseEntity.ok(dashboards);
    }
}