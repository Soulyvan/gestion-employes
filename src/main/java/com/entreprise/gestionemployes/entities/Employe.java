package com.entreprise.gestionemployes.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String fonction;

    @Column(nullable = false)
    private LocalDate dateEntreeService;

    @Column(nullable = false)
    private Double salaireAnnuel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "direction_id", nullable = false)
    private Direction direction;

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExperienceProfessionnelle> experiences;

    public Employe() {
    }

    public Employe(int id, String nom, String prenom, String adresse, String telephone, String email, String fonction, LocalDate dateEntreeService, Double salaireAnnuel, Direction direction, List<ExperienceProfessionnelle> experiences) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.fonction = fonction;
        this.dateEntreeService = dateEntreeService;
        this.salaireAnnuel = salaireAnnuel;
        this.direction = direction;
        this.experiences = experiences;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public LocalDate getDateEntreeService() {
        return dateEntreeService;
    }

    public void setDateEntreeService(LocalDate dateEntreeService) {
        this.dateEntreeService = dateEntreeService;
    }

    public Double getSalaireAnnuel() {
        return salaireAnnuel;
    }

    public void setSalaireAnnuel(Double salaireAnnuel) {
        this.salaireAnnuel = salaireAnnuel;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public List<ExperienceProfessionnelle> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<ExperienceProfessionnelle> experiences) {
        this.experiences = experiences;
    }
}
