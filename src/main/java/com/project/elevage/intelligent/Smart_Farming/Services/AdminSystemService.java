package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.AdminSystem.AdminSystemEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Authority;
import com.project.elevage.intelligent.Smart_Farming.Entities.UserEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.UserEntityRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminSystemService {

    @Autowired
    private UserEntityRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder ;

    @PostConstruct
    @Transactional
    public void createDefaultAdmin() {
        if (usersRepository.findByAuthority(Authority.ADMIN_SYSTEM).isEmpty()) {
            AdminSystemEntity admin = new AdminSystemEntity();
            admin.setEmail("sysadmin@smartfarming.com");
            admin.setPassword(passwordEncoder.encode("sysadmin123"));  // Mot de passe haché
            admin.setNom("Admin");
            admin.setPrenom("Système");
            admin.setTelephone("123456789");
            admin.setTenant(null);
            usersRepository.save(admin);
        }
    }

}
