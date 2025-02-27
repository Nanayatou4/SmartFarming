package com.project.elevage.intelligent.Smart_Farming.Controllers;

import com.project.elevage.intelligent.Smart_Farming.Entities.SSL.SSLCertificateEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.SSLCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/ssl")
public class SSLCertificateController {

    @Autowired
    private SSLCertificateService sslCertificateService;

    // Ajouter ou mettre à jour un certificat
    @PostMapping("/ajouter")
    public SSLCertificateEntity ajouterCertificat(@RequestParam String domain,
                                                  @RequestParam String certificate,
                                                  @RequestParam String privateKey) {
        return sslCertificateService.enregistrerCertificat(domain, certificate, privateKey);
    }

    // Obtenir un certificat SSL
    @GetMapping("/{domain}")
    public SSLCertificateEntity obtenirCertificat(@PathVariable String domain) {
        return sslCertificateService.obtenirCertificat(domain);
    }
}