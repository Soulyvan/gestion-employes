package com.entreprise.gestionemployes.services;

import com.entreprise.gestionemployes.dto.EntrepriseDto;
import com.entreprise.gestionemployes.entities.Entreprise;
import com.entreprise.gestionemployes.repositories.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EntrepriseService {
    private final EntrepriseRepository entrepriseRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EntrepriseService(EntrepriseRepository entrepriseRepository, PasswordEncoder passwordEncoder) {
        this.entrepriseRepository = entrepriseRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Entreprise registerEntreprise(EntrepriseDto entrepriseDto) throws Exception {
        // Vérifier si l'username existe déjà
        if (entrepriseRepository.existsByUsername(entrepriseDto.getUsername())) {
            throw new Exception("Ce nom d'utilisateur est déjà utilisé");
        }

        // Vérifier si l'email existe déjà
        if (entrepriseRepository.existsByEmail(entrepriseDto.getEmail())) {
            throw new Exception("Cet email est déjà utilisé");
        }

        // Vérifier que les mots de passe correspondent
        if (!entrepriseDto.getPassword().equals(entrepriseDto.getPasswordConfirm())) {
            throw new Exception("Les mots de passe ne correspondent pas");
        }

        // Créer et sauvegarder la nouvelle entreprise
        Entreprise entreprise = new Entreprise();
        entreprise.setUsername(entrepriseDto.getUsername());
        entreprise.setEmail(entrepriseDto.getEmail());
        entreprise.setPassword(passwordEncoder.encode(entrepriseDto.getPassword()));

        return entrepriseRepository.save(entreprise);
    }

    public Optional<Entreprise> findByUsername(String username) {
        return entrepriseRepository.findByUsername(username);
    }

}
