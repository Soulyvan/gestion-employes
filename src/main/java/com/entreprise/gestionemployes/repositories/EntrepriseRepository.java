package com.entreprise.gestionemployes.repositories;

import com.entreprise.gestionemployes.entities.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {
    Optional<Entreprise> findByUsername(String username);
    Optional<Entreprise> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
