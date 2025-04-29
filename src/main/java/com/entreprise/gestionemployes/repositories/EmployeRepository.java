package com.entreprise.gestionemployes.repositories;

import com.entreprise.gestionemployes.entities.Direction;
import com.entreprise.gestionemployes.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeRepository extends JpaRepository<Employe, Integer> {
    List<Employe> findByDirection(Direction direction);
}
