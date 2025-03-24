package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Integration.IntegrationEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.TenantAdmin.TenantAdminEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.IntegrationEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TenantAdminEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TenantEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IntegrationService {

    @Autowired
    private IntegrationEntityRepository integrationRepository;

    @Autowired
    private TenantAdminEntityRepository  tenantAdminRepository;

    @Autowired
    private TenantEntityRepository tenantRepository;


    ///  **********ADMIN SYSTEM***********

    // Ajouter ou mettre à jour une intégration
    public IntegrationEntity enregistrerIntegration(String name, Long tenantId, String type, String configuration,  Boolean enabled) {
        TenantEntity tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant non trouvé"));
        Optional<IntegrationEntity> integrationOpt = integrationRepository.findByName(name);
        IntegrationEntity integration = integrationOpt.orElse(new IntegrationEntity());
        integration.setName(name);
        integration.setType(type);
        integration.setTenant(tenant);
        integration.setConfiguration(configuration);
        integration.setEnabled(enabled);
        return integrationRepository.save(integration);
    }

    // Activer/Désactiver une intégration
    public IntegrationEntity changerEtatIntegration(String name, Boolean enabled) {
        IntegrationEntity integration = integrationRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Intégration non trouvée"));
        integration.setEnabled(enabled);
        return integrationRepository.save(integration);
    }

    // Obtenir la liste des intégrations
    public List<IntegrationEntity> obtenirToutesIntegrations() {
        return integrationRepository.findAll();
    }

    // Supprimer une intégration
    public void supprimerIntegration(String name) {
        IntegrationEntity integration = integrationRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Intégration non trouvée"));
        integrationRepository.delete(integration);
    }

    ///  **********TENANT ADMIN***********

    // Vérifier si le TenantAdmin appartient bien au tenant
    private void verifierTenantAdmin(String email, Long tenantId) {
        TenantAdminEntity tenantAdmin = tenantAdminRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("TenantAdmin non trouvé"));

        if (!tenantAdmin.getTenant().getId().equals(tenantId)) {
            throw new RuntimeException("Accès refusé : ce TenantAdmin n'appartient pas à ce tenant !");
        }
    }


    // Ajouter une intégration pour son propre tenant
    public IntegrationEntity enregistrerIntegrationByTenantAdmin(String email, Long tenantId, String name, String type, String configuration, Boolean enabled) {
        verifierTenantAdmin(email, tenantId);  // Vérifier l'appartenance au tenant

        TenantEntity tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant non trouvé"));

        IntegrationEntity integration = new IntegrationEntity();
        integration.setName(name);
        integration.setType(type);
        integration.setConfiguration(configuration);
        integration.setEnabled(enabled);
        integration.setTenant(tenant);  // Lier l'intégration au tenant

        return integrationRepository.save(integration);
    }


    // Activer ou désactiver une intégration (pour son tenant)
    public IntegrationEntity changerEtatIntegrationByTenantAdmin(String tenantAdminUsername, Long tenantId, Long integrationId, Boolean enabled) {
        verifierTenantAdmin(tenantAdminUsername, tenantId);  // Vérifier l'appartenance

        IntegrationEntity integration = integrationRepository.findByIdAndTenantId(integrationId, tenantId)
                .orElseThrow(() -> new RuntimeException("Intégration non trouvée ou non associée à ce tenant"));

        integration.setEnabled(enabled);
        return integrationRepository.save(integration);
    }


    // Supprimer une intégration
    public void supprimerIntegrationByTenantAdmin(String email, Long tenantId, Long integrationId) {
        verifierTenantAdmin(email, tenantId);  // Vérifier l'appartenance

        IntegrationEntity integration = integrationRepository.findByIdAndTenantId(integrationId, tenantId)
                .orElseThrow(() -> new RuntimeException("Intégration non trouvée ou non associée à ce tenant"));

        integrationRepository.delete(integration);
    }


    // Récupérer toutes les intégrations d'un tenant
    public List<IntegrationEntity> obtenirIntegrationsByTenantAdmin(String email, Long tenantId) {
        verifierTenantAdmin(email, tenantId);  // Vérifier l'appartenance
        return integrationRepository.findByTenantId(tenantId);
    }


}
