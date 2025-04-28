package com.entreprise.gestionemployes.dto;

import jakarta.validation.constraints.NotEmpty;

public class DirectionDto {
    @NotEmpty(message = "Le nom de la direction est obligatoire")
    private String nom;

    public DirectionDto() {
    }

    public DirectionDto(String nom) {
        this.nom = nom;
    }

    public @NotEmpty(message = "Le nom de la direction est obligatoire") String getNom() {
        return nom;
    }

    public void setNom(@NotEmpty(message = "Le nom de la direction est obligatoire") String nom) {
        this.nom = nom;
    }
}
