package com.entreprise.gestionemployes.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EntrepriseDto {
    @NotEmpty(message = "Le nom d'utilisateur est obligatoire")
    private String username;

    @NotEmpty(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;

    @NotEmpty(message = "Le mot de passe est obligatoire")
    private String password;

    @NotEmpty(message = "La confirmation du mot de passe est obligatoire")
    private String passwordConfirm;

    public EntrepriseDto() {
    }

    public EntrepriseDto(String username, String email, String password, String passwordConfirm) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public @NotEmpty(message = "Le nom d'utilisateur est obligatoire") String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty(message = "Le nom d'utilisateur est obligatoire") String username) {
        this.username = username;
    }

    public @NotEmpty(message = "L'email est obligatoire") @Email(message = "Format d'email invalide") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "L'email est obligatoire") @Email(message = "Format d'email invalide") String email) {
        this.email = email;
    }

    public @NotEmpty(message = "Le mot de passe est obligatoire") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "Le mot de passe est obligatoire") String password) {
        this.password = password;
    }

    public @NotEmpty(message = "La confirmation du mot de passe est obligatoire") String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(@NotEmpty(message = "La confirmation du mot de passe est obligatoire") String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
