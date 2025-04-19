package com.entreprise.gestionemployes.services;

import com.entreprise.gestionemployes.entities.Entreprise;
import com.entreprise.gestionemployes.entities.Utilisateur;
import com.entreprise.gestionemployes.repositories.EntrepriseRepository;
import com.entreprise.gestionemployes.repositories.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UtilisateurService {
    private final UtilisateurRepository userRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void registerUser(String username, String email, String password, String entrepriseNom) {
        Entreprise entreprise = entrepriseRepository.findByNom(entrepriseNom);
        if (entreprise == null) {
            entreprise = entrepriseRepository.save(
                    Entreprise.builder().nom(entrepriseNom).build()
            );
        }

        Utilisateur user = Utilisateur.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .entreprise(entreprise)
                .role("USER")
                .build();

        userRepository.save(user);
    }
}
