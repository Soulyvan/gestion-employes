package com.entreprise.gestionemployes.repositories;

import com.entreprise.gestionemployes.entities.Direction;
import com.entreprise.gestionemployes.entities.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DirectionRepository extends JpaRepository<Direction, Integer> {
    List<Direction> findByEntreprise(Entreprise entreprise);
}
