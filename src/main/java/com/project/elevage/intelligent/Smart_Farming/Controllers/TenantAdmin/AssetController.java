package com.project.elevage.intelligent.Smart_Farming.Controllers.TenantAdmin;

import com.project.elevage.intelligent.Smart_Farming.DTO.AssetRequest;
import com.project.elevage.intelligent.Smart_Farming.Entities.Asset.AssetEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/tenant-admins/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    /**
     * Ajouter un asset pour un Tenant Admin (utilisation de @RequestBody pour tout)
     */
    @PostMapping("/tenant/add")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<AssetEntity> addAssetAsTenantAdmin(@RequestBody AssetRequest request) {
        try {
            AssetEntity createdAsset = assetService.addAssetAsTenantAdmin(request.getTenantAdminEmail(),
                    request.getTenantId(),
                    request.getAsset());
            return new ResponseEntity<>(createdAsset, HttpStatus.CREATED);
        } catch (AccessDeniedException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Modifier un asset pour un Tenant Admin (utilisation de @RequestBody pour tout)
     */
    @PutMapping("/tenant/update/{assetId}")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<AssetEntity> updateAssetAsTenantAdmin(@PathVariable Long assetId,
                                                                @RequestBody AssetRequest request) {
        try {
            AssetEntity updatedAsset = assetService.updateAssetAsTenantAdmin(request.getTenantAdminEmail(),
                    request.getTenantId(),
                    assetId,
                    request.getAsset());
            return new ResponseEntity<>(updatedAsset, HttpStatus.OK);
        } catch (AccessDeniedException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Supprimer un asset pour un Tenant Admin (utilisation de @RequestBody pour tout)
     */
    @DeleteMapping("/tenant/delete/{assetId}")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<Void> deleteAssetAsTenantAdmin(@PathVariable Long assetId,
                                                         @RequestBody AssetRequest request) {
        try {
            assetService.deleteAssetAsTenantAdmin(request.getTenantAdminEmail(),
                    request.getTenantId(),
                    assetId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (AccessDeniedException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Autres endpoints
    @GetMapping("/all")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<List<AssetEntity>> getAllAssets() {
        List<AssetEntity> assets = assetService.getAllAssets();
        return new ResponseEntity<>(assets, HttpStatus.OK);
    }

}
