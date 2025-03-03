package com.project.elevage.intelligent.Smart_Farming.Controllers;

import com.project.elevage.intelligent.Smart_Farming.Entities.Eleveur.EleveurEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.TenantAdmin.TenantAdminEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.EleveurService;
import com.project.elevage.intelligent.Smart_Farming.Services.TenantAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenant-admins")
public class TenantAdminController {

    @Autowired
    private EleveurService eleveurService;

    @PostMapping("/ajouter-eleveur/{tenantAdminId}")
    public ResponseEntity<EleveurEntity> ajouterEleveur(
            @PathVariable Long tenantAdminId,
            @RequestBody EleveurEntity eleveur) {
        return ResponseEntity.ok(eleveurService.ajouterEleveur(tenantAdminId, eleveur));
    }

    @GetMapping("/eleveurs")
    public ResponseEntity<List<EleveurEntity>> listerEleveurs() {
        return ResponseEntity.ok(eleveurService.listerEleveurs());
    }

    @PutMapping("/modifier-eleveur/{id}")
    public ResponseEntity<EleveurEntity> modifierEleveur(
            @PathVariable Long id,
            @RequestBody EleveurEntity details) {
        return ResponseEntity.ok(eleveurService.modifierEleveur(id, details));
    }

    @DeleteMapping("/supprimer-eleveur/{id}")
    public ResponseEntity<String> supprimerEleveur(@PathVariable Long id) {
        eleveurService.supprimerEleveur(id);
        return ResponseEntity.ok("Éleveur supprimé avec succès !");
    }



}
