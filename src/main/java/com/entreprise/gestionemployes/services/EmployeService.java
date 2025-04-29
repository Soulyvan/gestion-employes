package com.entreprise.gestionemployes.services;

import com.entreprise.gestionemployes.dto.EmployeDto;
import com.entreprise.gestionemployes.dto.ExperienceProfessionnelleDto;
import com.entreprise.gestionemployes.entities.Direction;
import com.entreprise.gestionemployes.entities.Employe;
import com.entreprise.gestionemployes.entities.Entreprise;
import com.entreprise.gestionemployes.entities.ExperienceProfessionnelle;
import com.entreprise.gestionemployes.repositories.DirectionRepository;
import com.entreprise.gestionemployes.repositories.EmployeRepository;
import com.entreprise.gestionemployes.repositories.EntrepriseRepository;
import com.entreprise.gestionemployes.repositories.ExperienceProfessionnelleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeService {
    private final EmployeRepository employeRepository;
    private final DirectionRepository directionRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final ExperienceProfessionnelleRepository experienceProfessionnelleRepository;

    @Autowired
    public EmployeService(EmployeRepository employeRepository, DirectionRepository directionRepository,
                          EntrepriseRepository entrepriseRepository, ExperienceProfessionnelleRepository experienceProfessionnelleRepository) {
        this.employeRepository = employeRepository;
        this.directionRepository = directionRepository;
        this.entrepriseRepository = entrepriseRepository;
        this.experienceProfessionnelleRepository = experienceProfessionnelleRepository;
    }

    // Créer un employé
    public Employe createEmploye(EmployeDto employeDto) throws Exception {
        Direction direction = directionRepository.findById(employeDto.getDirectionId())
                .orElseThrow(() -> new Exception("Direction non trouvée"));

        Employe employe = new Employe();
        employe.setNom(employeDto.getNom());
        employe.setPrenom(employeDto.getPrenom());
        employe.setAdresse(employeDto.getAdresse());
        employe.setTelephone(employeDto.getTelephone());
        employe.setEmail(employeDto.getEmail());
        employe.setFonction(employeDto.getFonction());
        employe.setDateEntreeService(employeDto.getDateEntreeService());
        employe.setSalaireAnnuel(employeDto.getSalaireAnnuel());
        employe.setDirection(direction);

        List<ExperienceProfessionnelle> experiences = new ArrayList<>();
        for (ExperienceProfessionnelleDto expDto : employeDto.getExperiences()) {
            ExperienceProfessionnelle exp = new ExperienceProfessionnelle();
            exp.setDateDebut(expDto.getDateDebut());
            exp.setDateFin(expDto.getDateFin());
            exp.setLieu(expDto.getLieu());
            exp.setFonction(expDto.getFonction());
            exp.setDescription(expDto.getDescription());
            exp.setEmploye(employe);
            experiences.add(exp);
        }
        employe.setExperiences(experiences);

        return employeRepository.save(employe);
    }

    // Récupérer les employés d'une direction
    public List<Employe> getEmployesByDirection(int directionId) throws Exception {
        Direction direction = directionRepository.findById(directionId)
                .orElseThrow(() -> new Exception("Direction non trouvée"));

        return employeRepository.findByDirection(direction);
    }

    // Récupérer tous les employés
    public List<Employe> getAllEmployesForCurrentEntreprise() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Entreprise entreprise = entrepriseRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("Entreprise non trouvée"));

        List<Direction> directions = directionRepository.findByEntreprise(entreprise);
        List<Employe> all = new ArrayList<>();

        for (Direction d : directions) {
            all.addAll(employeRepository.findByDirection(d));
        }

        return all;
    }

    // Trouver un employé par ID
    public Optional<Employe> findById(int id) {
        return employeRepository.findById(id);
    }

    // Modifier employé
    public Employe updateEmploye(int id, EmployeDto employeDto) throws Exception {
        Employe employe = employeRepository.findById(id)
                .orElseThrow(() -> new Exception("Employé non trouvé"));

        Direction direction = directionRepository.findById(employeDto.getDirectionId())
                .orElseThrow(() -> new Exception("Direction non trouvée"));

        employe.setNom(employeDto.getNom());
        employe.setPrenom(employeDto.getPrenom());
        employe.setAdresse(employeDto.getAdresse());
        employe.setTelephone(employeDto.getTelephone());
        employe.setEmail(employeDto.getEmail());
        employe.setFonction(employeDto.getFonction());
        employe.setDateEntreeService(employeDto.getDateEntreeService());
        employe.setSalaireAnnuel(employeDto.getSalaireAnnuel());
        employe.setDirection(direction);

        // Supprimer les anciennes expériences de la base
        if (employe.getExperiences() != null && !employe.getExperiences().isEmpty()) {
            experienceProfessionnelleRepository.deleteAll(employe.getExperiences());
            employe.getExperiences().clear(); // important aussi côté Java
        }

        List<ExperienceProfessionnelle> nouvellesExp = new ArrayList<>();
        for (ExperienceProfessionnelleDto expDto : employeDto.getExperiences()) {
            ExperienceProfessionnelle exp = new ExperienceProfessionnelle();
            exp.setDateDebut(expDto.getDateDebut());
            exp.setDateFin(expDto.getDateFin());
            exp.setLieu(expDto.getLieu());
            exp.setFonction(expDto.getFonction());
            exp.setDescription(expDto.getDescription());
            exp.setEmploye(employe);
            nouvellesExp.add(exp);
        }

        // employe.setExperiences(nouvellesExp); ça cause une erreur
        employe.getExperiences().clear(); // vide la liste existante proprement
        employe.getExperiences().addAll(nouvellesExp); // ajoute les nouvelles

        return employeRepository.save(employe);
    }

}
