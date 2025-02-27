package com.project.elevage.intelligent.Smart_Farming.Controllers.TenantAdmin;

import com.project.elevage.intelligent.Smart_Farming.Entities.Widget.WidgetEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/widgets")
public class WidgetController {

    @Autowired
    private WidgetService widgetService;

    /**
     * Créer un nouveau widget pour un tableau de bord
     * @param widget Le widget à créer
     * @return Le widget créé
     */
    @PostMapping("/create")
    public ResponseEntity<WidgetEntity> createWidget(@RequestBody WidgetEntity widget) {
        WidgetEntity newWidget = widgetService.createWidget(widget);
        return ResponseEntity.ok(newWidget);
    }

    /**
     * Récupérer tous les widgets associés à un tableau de bord
     * @param dashboardId L'ID du tableau de bord
     * @return Liste des widgets
     */
    @GetMapping("/dashboard/{dashboardId}")
    public ResponseEntity<List<WidgetEntity>> getWidgetsByDashboard(@PathVariable Long dashboardId) {
        List<WidgetEntity> widgets = widgetService.getWidgetsByDashboard(dashboardId);
        return ResponseEntity.ok(widgets);
    }

    /**
     * Mettre à jour un widget
     * @param widgetId L'ID du widget
     * @param widget Les nouvelles données du widget
     * @return Le widget mis à jour
     */
    @PutMapping("/update/{widgetId}")
    public ResponseEntity<WidgetEntity> updateWidget(@PathVariable Long widgetId, @RequestBody WidgetEntity widget) {
        WidgetEntity updatedWidget = widgetService.updateWidget(widgetId, widget);
        return ResponseEntity.ok(updatedWidget);
    }

    /**
     * Supprimer un widget
     * @param widgetId L'ID du widget à supprimer
     * @return Message de confirmation
     */
    @DeleteMapping("/delete/{widgetId}")
    public ResponseEntity<String> deleteWidget(@PathVariable Long widgetId) {
        widgetService.deleteWidget(widgetId);
        return ResponseEntity.ok("Widget supprimé avec succès");
    }
}

