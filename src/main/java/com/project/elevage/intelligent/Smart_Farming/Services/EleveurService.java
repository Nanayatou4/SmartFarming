package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Eleveur.EleveurEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.TenantAdmin.TenantAdminEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.EleveurEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TenantAdminEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EleveurService {

    @Autowired
    private EleveurEntityRepository eleveurRepository;

    @Autowired
    private PasswordEncoder  passwordEncoder;

    @Autowired
    private TenantAdminEntityRepository tenantAdminRepository;

    @Transactional
    public EleveurEntity ajouterEleveur(Long tenantAdminId, EleveurEntity eleveur) {
        TenantAdminEntity admin = tenantAdminRepository.findById(tenantAdminId)
                .orElseThrow(() -> new RuntimeException("Tenant Admin introuvable"));

        TenantEntity tenant = admin.getTenant();

        eleveur.setTenant(tenant);

        // Vérifier si le mot de passe est fourni et l'encoder
        if (eleveur.getPassword() == null || eleveur.getPassword().isEmpty()) {
            throw new RuntimeException("Le mot de passe est obligatoire !");
        }
        eleveur.setPassword(passwordEncoder.encode(eleveur.getPassword()));

        return eleveurRepository.save(eleveur);
    }

    @Transactional
    public List<EleveurEntity> listerEleveurs() {
        return eleveurRepository.findAll();
    }

    @Transactional
    public EleveurEntity modifierEleveur(Long id, EleveurEntity details) {
        EleveurEntity eleveur = eleveurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Éleveur introuvable"));

        eleveur.setNom(details.getNom());
        eleveur.setEmail(details.getEmail());
        eleveur.setPassword(passwordEncoder.encode(details.getPassword()));
        eleveur.setSpecialite(details.getSpecialite());
        eleveur.setExperience(details.getExperience());
        eleveur.setNombre_animaux(details.getNombre_animaux());
        eleveur.setRegion(details.getRegion());

        return eleveurRepository.save(eleveur);
    }

    @Transactional
    public void supprimerEleveur(Long id) {
        EleveurEntity eleveur = eleveurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Éleveur introuvable"));
        eleveurRepository.delete(eleveur);
    }



}
