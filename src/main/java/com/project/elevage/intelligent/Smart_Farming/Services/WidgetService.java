package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Dashboard.DashboardEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.TenantAdmin.TenantAdminEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Widget.WidgetEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Widget.WidgetStatus;
import com.project.elevage.intelligent.Smart_Farming.Repositories.DashboardEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TenantAdminEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TenantEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.WidgetEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class WidgetService {

    @Autowired
    private WidgetEntityRepository widgetRepository;

    @Autowired
    private DashboardEntityRepository dashboardRepository;

    @Autowired
    private TenantAdminEntityRepository tenantAdminRepository;

    @Autowired
    private TenantEntityRepository tenantRepository;

    @Autowired
    private AnalyticsService analyticsService;

    ///  *************************ADMIN SYSTEM**************************

    public List<WidgetEntity> getAllWidgets() {
        return widgetRepository.findAll();
    }

    public void disableWidget(Long widgetId) {
        WidgetEntity widget = widgetRepository.findById(widgetId)
                .orElseThrow(() -> new RuntimeException("Widget non trouvé"));

        // Vérifier si le widget est déjà desactive
        if (widget.getStatus() == WidgetStatus.DISABLED) {
            throw new RuntimeException("Le widget est déjà desactivé.");
        }

        widget.setStatus(WidgetStatus.DISABLED);
        widgetRepository.save(widget);
    }

    public void enableWidget(Long widgetId) {
        WidgetEntity widget = widgetRepository.findById(widgetId)
                .orElseThrow(() -> new RuntimeException("Widget non trouvé"));

        // Vérifier si le widget est déjà actif
        if (widget.getStatus() == WidgetStatus.ACTIVE) {
            throw new RuntimeException("Le widget est déjà activé.");
        }

        // Activer le widget en changeant son statut à 'ACTIVE'
        widget.setStatus(WidgetStatus.ACTIVE);
        widgetRepository.save(widget);
    }

    public WidgetEntity createAnalyticsWidget(Long dashboardId, String metricType) {
        DashboardEntity dashboard = dashboardRepository.findById(dashboardId)
                .orElseThrow(() -> new RuntimeException("Dashboard non trouvé"));

        WidgetEntity widget = new WidgetEntity();
        widget.setDashboard(dashboard);
        widget.setType("analytics");
        widget.setTitle("Analytics: " + metricType);
        widget.setConfig(metricType); // Stocke "temperature" ou "humidity"

        return widgetRepository.save(widget);
    }

    public Map<String, Double> getAnalyticsData(Long widgetId) {
        WidgetEntity widget = widgetRepository.findById(widgetId)
                .orElseThrow(() -> new RuntimeException("Widget non trouvé"));

        if (!"analytics".equals(widget.getType())) {
            throw new RuntimeException("Ce widget n'est pas un widget analytique.");
        }

        String metricType = widget.getConfig();

        if ("temperature".equals(metricType)) {
            return analyticsService.getAverageTemperature();
        } else if ("humidity".equals(metricType)) {
            return analyticsService.getAverageHumidity();
        } else {
            throw new RuntimeException("Type de métrique inconnu.");
        }
    }




    ///  ***********************TENANT ADMIN*****************************


    /**
     * Vérifier que le Tenant Admin appartient bien à ce Tenant
     */
    private void verifyTenantAdmin(Long tenantAdminId, Long tenantId) {
        TenantAdminEntity tenantAdmin = tenantAdminRepository.findById(tenantAdminId)
                .orElseThrow(() -> new RuntimeException("Tenant Admin non trouvé"));

        if (!tenantAdmin.getTenant().getId().equals(tenantId)) {
            throw new RuntimeException("Accès interdit : vous n'appartenez pas à ce Tenant.");
        }
    }

    /**
     * Créer un widget pour un tableau de bord d'un Tenant spécifique
     */
    public WidgetEntity createWidget(Long tenantAdminId, Long tenantId, Long dashboardId, WidgetEntity widget) {
        verifyTenantAdmin(tenantAdminId, tenantId); // Vérification du Tenant Admin

        DashboardEntity dashboard = dashboardRepository.findById(dashboardId)
                .orElseThrow(() -> new RuntimeException("Dashboard non trouvé"));

        if (!dashboard.getTenant().getId().equals(tenantId)) {
            throw new RuntimeException("Accès interdit : ce tableau de bord n'appartient pas à votre Tenant.");
        }

        widget.setDashboard(dashboard);
        return widgetRepository.save(widget);
    }

    /**
     * Récupérer tous les widgets d'un tableau de bord
     */
    public List<WidgetEntity> getWidgetsByDashboard(Long tenantAdminId, Long tenantId, Long dashboardId) {
        verifyTenantAdmin(tenantAdminId, tenantId); // Vérification du Tenant Admin

        DashboardEntity dashboard = dashboardRepository.findById(dashboardId)
                .orElseThrow(() -> new RuntimeException("Dashboard non trouvé"));

        if (!dashboard.getTenant().getId().equals(tenantId)) {
            throw new RuntimeException("Accès interdit : ce tableau de bord n'appartient pas à votre Tenant.");
        }

        return widgetRepository.findByDashboardIdAndStatus(dashboardId, WidgetStatus.ACTIVE);
    }


    /**
     * Mettre à jour un widget
     */
    public WidgetEntity updateWidget(Long tenantAdminId, Long tenantId, Long widgetId, WidgetEntity widgetData) {
        verifyTenantAdmin(tenantAdminId, tenantId); // Vérification du Tenant Admin

        WidgetEntity widget = widgetRepository.findById(widgetId)
                .orElseThrow(() -> new RuntimeException("Widget non trouvé"));

        if (!widget.getDashboard().getTenant().getId().equals(tenantId)) {
            throw new RuntimeException("Accès interdit : ce widget n'appartient pas à votre Tenant.");
        }

        if (widget.getStatus() == WidgetStatus.DISABLED) {
            throw new RuntimeException("Ce widget est désactivé et ne peut pas être modifié.");
        }


        widget.setTitle(widgetData.getTitle());
        widget.setType(widgetData.getType());
        widget.setConfig(widgetData.getConfig());

        return widgetRepository.save(widget);
    }

    /**
     * Supprimer un widget
     */
    public void deleteWidget(Long tenantAdminId, Long tenantId, Long widgetId) {
        verifyTenantAdmin(tenantAdminId, tenantId); // Vérification du Tenant Admin

        WidgetEntity widget = widgetRepository.findById(widgetId)
                .orElseThrow(() -> new RuntimeException("Widget non trouvé"));

        if (!widget.getDashboard().getTenant().getId().equals(tenantId)) {
            throw new RuntimeException("Accès interdit : ce widget n'appartient pas à votre Tenant.");
        }

        if (widget.getStatus() == WidgetStatus.DISABLED) {
            throw new RuntimeException("Ce widget est désactivé et ne peut pas être supprimé.");
        }

        widgetRepository.deleteById(widgetId);
    }

    public void enableWidget(Long tenantAdminId, Long tenantId, Long widgetId) {
        // Vérification que le Tenant Admin appartient bien au Tenant
        verifyTenantAdmin(tenantAdminId, tenantId);

        // Récupérer le widget par son ID
        WidgetEntity widget = widgetRepository.findById(widgetId)
                .orElseThrow(() -> new RuntimeException("Widget non trouvé"));

        // Vérification que le widget appartient au Tenant
        if (!widget.getDashboard().getTenant().getId().equals(tenantId)) {
            throw new RuntimeException("Accès interdit : ce widget n'appartient pas à votre Tenant.");
        }

        // Vérifier si le widget est déjà activé
        if (widget.getStatus() == WidgetStatus.ACTIVE) {
            throw new RuntimeException("Le widget est déjà activé.");
        }

        // Activer le widget en changeant son statut à 'ACTIVE'
        widget.setStatus(WidgetStatus.ACTIVE);
        widgetRepository.save(widget);
    }

}
