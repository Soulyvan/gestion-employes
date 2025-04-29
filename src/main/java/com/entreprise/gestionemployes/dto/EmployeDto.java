package com.entreprise.gestionemployes.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeDto {
    @NotEmpty(message = "Le nom est obligatoire")
    private String nom;

    @NotEmpty(message = "Le prénom est obligatoire")
    private String prenom;

    @NotEmpty(message = "L'adresse est obligatoire")
    private String adresse;

    @NotEmpty(message = "Le téléphone est obligatoire")
    private String telephone;

    @NotEmpty(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;

    @NotEmpty(message = "La fonction est obligatoire")
    private String fonction;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEntreeService;

    private Double salaireAnnuel;

    private int directionId;

    private List<ExperienceProfessionnelleDto> experiences = new ArrayList<>();

    public EmployeDto() {
    }

    public EmployeDto(String nom, String prenom, String adresse, String telephone, String email, String fonction, LocalDate dateEntreeService, Double salaireAnnuel, int directionId, List<ExperienceProfessionnelleDto> experiences) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.fonction = fonction;
        this.dateEntreeService = dateEntreeService;
        this.salaireAnnuel = salaireAnnuel;
        this.directionId = directionId;
        this.experiences = experiences;
    }

    public @NotEmpty(message = "Le nom est obligatoire") String getNom() {
        return nom;
    }

    public void setNom(@NotEmpty(message = "Le nom est obligatoire") String nom) {
        this.nom = nom;
    }

    public @NotEmpty(message = "Le prénom est obligatoire") String getPrenom() {
        return prenom;
    }

    public void setPrenom(@NotEmpty(message = "Le prénom est obligatoire") String prenom) {
        this.prenom = prenom;
    }

    public @NotEmpty(message = "L'adresse est obligatoire") String getAdresse() {
        return adresse;
    }

    public void setAdresse(@NotEmpty(message = "L'adresse est obligatoire") String adresse) {
        this.adresse = adresse;
    }

    public @NotEmpty(message = "Le téléphone est obligatoire") String getTelephone() {
        return telephone;
    }

    public void setTelephone(@NotEmpty(message = "Le téléphone est obligatoire") String telephone) {
        this.telephone = telephone;
    }

    public @NotEmpty(message = "L'email est obligatoire") @Email(message = "Format d'email invalide") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "L'email est obligatoire") @Email(message = "Format d'email invalide") String email) {
        this.email = email;
    }

    public @NotEmpty(message = "La fonction est obligatoire") String getFonction() {
        return fonction;
    }

    public void setFonction(@NotEmpty(message = "La fonction est obligatoire") String fonction) {
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

    public int getDirectionId() {
        return directionId;
    }

    public void setDirectionId(int directionId) {
        this.directionId = directionId;
    }

    public List<ExperienceProfessionnelleDto> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<ExperienceProfessionnelleDto> experiences) {
        this.experiences = experiences;
    }
}
