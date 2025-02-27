package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.SSL.SSLCertificateEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.SSLCertificateEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SSLCertificateService {
    @Autowired
    private SSLCertificateEntityRepository sslCertificateRepository;

    // Ajouter ou mettre à jour un certificat SSL
    public SSLCertificateEntity enregistrerCertificat(String domain, String certificate, String privateKey) {
        Optional<SSLCertificateEntity> certOpt = sslCertificateRepository.findByDomain(domain);
        SSLCertificateEntity sslCertificate = certOpt.orElse(new SSLCertificateEntity());
        sslCertificate.setDomain(domain);
        sslCertificate.setCertificate(certificate);
        sslCertificate.setPrivateKey(privateKey);
        return sslCertificateRepository.save(sslCertificate);
    }

    // Obtenir un certificat SSL
    public SSLCertificateEntity obtenirCertificat(String domain) {
        return sslCertificateRepository.findByDomain(domain)
                .orElseThrow(() -> new RuntimeException("Certificat non trouvé"));
    }
}
