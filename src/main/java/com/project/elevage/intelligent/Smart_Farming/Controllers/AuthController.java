package com.project.elevage.intelligent.Smart_Farming.Controllers;

import com.project.elevage.intelligent.Smart_Farming.DTO.AuthenticationRequest;
import com.project.elevage.intelligent.Smart_Farming.DTO.AuthenticationResponse;
import com.project.elevage.intelligent.Smart_Farming.Entities.UserEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TenantAdminEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.UserEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private TenantAdminEntityRepository tenantAdminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserEntityRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        try {
            // Vérifiez d'abord si l'utilisateur existe
            Optional<UserEntity> userOpt = userRepository.findByEmail(request.getEmail());
            if (userOpt.isEmpty()) {
                System.err.println("Utilisateur non trouvé: " + request.getEmail());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utilisateur non trouvé");
            }

            UserEntity user = userOpt.get();
            System.out.println("Utilisateur trouvé: " + user.getEmail() + ", Rôle: " + user.getAuthority());

            // Vérifiez si le mot de passe correspond
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                System.err.println("Mot de passe incorrect pour: " + request.getEmail());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mot de passe incorrect");
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtUtils.generateToken(request.getEmail());
            System.out.println("Token généré avec succès pour: " + request.getEmail());

            return ResponseEntity.ok(new AuthenticationResponse(token));
        } catch (BadCredentialsException e) {
            System.err.println("Mauvaises informations d'identification: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Informations d'identification incorrectes");
        } catch (Exception e) {
            System.err.println("Erreur d'authentification: " + e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne: " + e.getMessage());
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Endpoint auth accessible");
    }
}
