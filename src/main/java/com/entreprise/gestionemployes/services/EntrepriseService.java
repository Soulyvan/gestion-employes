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

    @Transactional
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
        entreprise.setAdresse(entrepriseDto.getAdresse());
        entreprise.setSiteWeb(entrepriseDto.getSiteWeb());
        entreprise.setSecteursActivite(entrepriseDto.getSecteursActivite());

        return entrepriseRepository.save(entreprise);
    }

    @Transactional
    public Entreprise updateEntreprise(EntrepriseDto entrepriseDto, int id) throws Exception {
        Entreprise entreprise = entrepriseRepository.findById(id)
                .orElseThrow(() -> new Exception("Entreprise non trouvée"));

        // Vérification pour l'username
        if (!entreprise.getUsername().equals(entrepriseDto.getUsername()) &&
                entrepriseRepository.existsByUsername(entrepriseDto.getUsername())) {
            throw new Exception("Ce nom d'utilisateur est déjà utilisé");
        }

        // Vérification pour l'email
        if (!entreprise.getEmail().equals(entrepriseDto.getEmail()) &&
                entrepriseRepository.existsByEmail(entrepriseDto.getEmail())) {
            throw new Exception("Cet email est déjà utilisé");
        }

        entreprise.setUsername(entrepriseDto.getUsername());
        entreprise.setEmail(entrepriseDto.getEmail());
        entreprise.setAdresse(entrepriseDto.getAdresse());
        entreprise.setSiteWeb(entrepriseDto.getSiteWeb());
        entreprise.setSecteursActivite(entrepriseDto.getSecteursActivite());

        // Mise à jour du mot de passe si un nouveau est fourni
        if (entrepriseDto.getPassword() != null && !entrepriseDto.getPassword().isEmpty()) {
            if (!entrepriseDto.getPassword().equals(entrepriseDto.getPasswordConfirm())) {
                throw new Exception("Les mots de passe ne correspondent pas");
            }
            entreprise.setPassword(passwordEncoder.encode(entrepriseDto.getPassword()));
        }

        return entrepriseRepository.save(entreprise);
    }

    public Optional<Entreprise> findByUsername(String username) {
        return entrepriseRepository.findByUsername(username);
    }

    public Optional<Entreprise> findById(int id) {
        return entrepriseRepository.findById(id);
    }

    public Entreprise getCurrentEntreprise() {
        // À implémenter avec SecurityContextHolder pour récupérer l'entreprise connectée
        return null;
    }
}
