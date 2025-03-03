package com.project.elevage.intelligent.Smart_Farming.Controllers.TenantAdmin;

import com.project.elevage.intelligent.Smart_Farming.Entities.Asset.AssetEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    /**
     * Ajouter un nouvel objet (asset)
     * @param asset L'objet à ajouter
     * @return Réponse avec l'objet ajouté
     */
    @PostMapping("/add")
    public ResponseEntity<AssetEntity> addAsset(@RequestBody AssetEntity asset) {
        AssetEntity newAsset = assetService.addAsset(asset);
        return ResponseEntity.ok(newAsset);
    }

    /**
     * Modifier un objet (asset) existant
     * @param assetId L'ID de l'objet à modifier
     * @param asset L'objet mis à jour
     * @return Réponse avec l'objet mis à jour
     */
    @PutMapping("/update/{assetId}")
    public ResponseEntity<AssetEntity> updateAsset(@PathVariable Long assetId, @RequestBody AssetEntity asset) {
        AssetEntity updatedAsset = assetService.updateAsset(assetId, asset);
        return ResponseEntity.ok(updatedAsset);
    }

    /**
     * Supprimer un objet (asset)
     * @param assetId L'ID de l'objet à supprimer
     * @return Réponse avec un message de confirmation
     */
    @DeleteMapping("/delete/{assetId}")
    public ResponseEntity<String> deleteAsset(@PathVariable Long assetId) {
        assetService.deleteAsset(assetId);
        return ResponseEntity.ok("Objet supprimé avec succès");
    }

    /**
     * Récupérer la liste des objets pour un locataire
     * @return Liste des objets
     */
    @GetMapping("/list")
    public ResponseEntity<List<AssetEntity>> getAllAssets() {
        List<AssetEntity> assets = assetService.getAllAssets();
        return ResponseEntity.ok(assets);
    }
}
