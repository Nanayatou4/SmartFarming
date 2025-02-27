package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Widget.WidgetEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.WidgetEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WidgetService {

    @Autowired
    private WidgetEntityRepository widgetRepository;

    /**
     * Créer un widget pour un tableau de bord
     * @param widget Le widget à créer
     * @return Le widget créé
     */
    public WidgetEntity createWidget(WidgetEntity widget) {
        return widgetRepository.save(widget);
    }

    /**
     * Récupérer tous les widgets associés à un tableau de bord
     * @param dashboardId L'ID du tableau de bord
     * @return Liste des widgets du tableau de bord
     */
    public List<WidgetEntity> getWidgetsByDashboard(Long dashboardId) {
        // Logic to fetch widgets based on the dashboardId
        return widgetRepository.findAll(); // Implémenter cette logique selon les besoins
    }

    /**
     * Mettre à jour un widget
     * @param widgetId L'ID du widget à mettre à jour
     * @param widgetData Les nouvelles données pour le widget
     * @return Le widget mis à jour
     */
    public WidgetEntity updateWidget(Long widgetId, WidgetEntity widgetData) {
        Optional<WidgetEntity> existingWidget = widgetRepository.findById(widgetId);
        if (existingWidget.isPresent()) {
            WidgetEntity widget = existingWidget.get();
            widget.setTitle(widgetData.getTitle());
            widget.setType(widgetData.getType());
            widget.setConfig(widgetData.getConfig());
            return widgetRepository.save(widget);
        }
        throw new RuntimeException("Widget non trouvé");
    }

    /**
     * Supprimer un widget
     * @param widgetId L'ID du widget à supprimer
     */
    public void deleteWidget(Long widgetId) {
        widgetRepository.deleteById(widgetId);
    }
}
