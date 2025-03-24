package com.project.elevage.intelligent.Smart_Farming.Controllers.TenantAdmin;

import com.project.elevage.intelligent.Smart_Farming.DTO.IntegrationTenantAdminDTO;
import com.project.elevage.intelligent.Smart_Farming.Entities.Eleveur.EleveurEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Integration.IntegrationEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.EleveurService;
import com.project.elevage.intelligent.Smart_Farming.Services.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenant-admins")
public class TenantAdminController {

    @Autowired
    private EleveurService eleveurService;

    @Autowired
    private IntegrationService integrationService;

    ///  ELEVEUR

    @PostMapping("/ajouter-eleveur/{tenantAdminId}")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<EleveurEntity> ajouterEleveur(
            @PathVariable Long tenantAdminId,
            @RequestBody EleveurEntity eleveur) {
        return ResponseEntity.ok(eleveurService.ajouterEleveur(tenantAdminId, eleveur));
    }

    @GetMapping("/eleveurs")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<List<EleveurEntity>> listerEleveurs() {
        return ResponseEntity.ok(eleveurService.listerEleveurs());
    }

    @PutMapping("/modifier-eleveur/{id}")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<EleveurEntity> modifierEleveur(
            @PathVariable Long id,
            @RequestBody EleveurEntity details) {
        return ResponseEntity.ok(eleveurService.modifierEleveur(id, details));
    }

    @DeleteMapping("/supprimer-eleveur/{id}")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<String> supprimerEleveur(@PathVariable Long id) {
        eleveurService.supprimerEleveur(id);
        return ResponseEntity.ok("Éleveur supprimé avec succès !");
    }

    ///  INTEGRATION
    @PostMapping("/saveIntegration")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<IntegrationEntity> enregistrerIntegrationByTenantAdmin(
            @RequestBody IntegrationTenantAdminDTO integrationTenantAdminDTO) {
        IntegrationEntity integration = integrationService.enregistrerIntegrationByTenantAdmin(
                integrationTenantAdminDTO.getEmail(), integrationTenantAdminDTO.getTenantId(),
                integrationTenantAdminDTO.getName(), integrationTenantAdminDTO.getType(),
                integrationTenantAdminDTO.getConfiguration(), integrationTenantAdminDTO.getEnabled());
        return ResponseEntity.status(HttpStatus.CREATED).body(integration);
    }

    @PatchMapping("/{integrationId}/toggle")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<IntegrationEntity> changerEtatIntegrationByTenantAdmin(
            @RequestBody IntegrationTenantAdminDTO integrationTenantAdminDTO) {
        IntegrationEntity integration = integrationService.changerEtatIntegrationByTenantAdmin(
                integrationTenantAdminDTO.getEmail(), integrationTenantAdminDTO.getTenantId(),
                integrationTenantAdminDTO.getIntegrationId(), integrationTenantAdminDTO.getEnabled());
        return ResponseEntity.ok(integration);
    }

    @DeleteMapping("/supprimer/{integrationId}")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<Void> supprimerIntegrationByTenantAdmin(
            @RequestBody IntegrationTenantAdminDTO integrationTenantAdminDTO) {
        integrationService.supprimerIntegrationByTenantAdmin(integrationTenantAdminDTO.getEmail(), integrationTenantAdminDTO.getTenantId(),
                integrationTenantAdminDTO.getIntegrationId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/allIntegrations")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<List<IntegrationEntity>> obtenirIntegrationsByTenantAdmin(
            @RequestBody IntegrationTenantAdminDTO integrationTenantAdminDTO) {
        List<IntegrationEntity> integrations = integrationService.obtenirIntegrationsByTenantAdmin(
                integrationTenantAdminDTO.getEmail(), integrationTenantAdminDTO.getTenantId());
        return ResponseEntity.ok(integrations);
    }
}





