package com.entreprise.gestionemployes.services;

import com.entreprise.gestionemployes.dto.DirectionDto;
import com.entreprise.gestionemployes.entities.Direction;
import com.entreprise.gestionemployes.entities.Entreprise;
import com.entreprise.gestionemployes.repositories.DirectionRepository;
import com.entreprise.gestionemployes.repositories.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectionService {
    private final DirectionRepository directionRepository;
    private final EntrepriseRepository entrepriseRepository;

    @Autowired
    public DirectionService(DirectionRepository directionRepository, EntrepriseRepository entrepriseRepository) {
        this.directionRepository = directionRepository;
        this.entrepriseRepository = entrepriseRepository;
    }

    // Ajouter une nouvelle direction
    public Direction createDirection(DirectionDto directionDto) throws Exception {
        // On récupère l'entreprise connectée et son nom
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Entreprise entreprise = entrepriseRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("Entreprise non trouvée"));

        Direction direction = new Direction();
        direction.setNom(directionDto.getNom());
        direction.setEntreprise(entreprise);

        return directionRepository.save(direction);
    }

    // Liste des directions d'une entreprise
    public List<Direction> getDirectionsForCurrentEntreprise() throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Entreprise entreprise = entrepriseRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("Entreprise non trouvée"));

        return directionRepository.findByEntreprise(entreprise);
    }

    // Trouver une direction par ID
    public Optional<Direction> findById(int id) {
        return directionRepository.findById(id);
    }
}
