package com.entreprise.gestionemployes.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ExperienceProfessionnelleDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDebut;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFin;

    private String lieu;
    private String fonction;
    private String description;

    public ExperienceProfessionnelleDto() {
    }

    public ExperienceProfessionnelleDto(LocalDate dateDebut, LocalDate dateFin, String lieu, String fonction, String description) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lieu = lieu;
        this.fonction = fonction;
        this.description = description;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
