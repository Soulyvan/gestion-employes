package com.entreprise.gestionemployes.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntrepriseDto {
    @NotEmpty(message = "Le nom de l'entreprise est obligatoire")
    private String username;

    @NotEmpty(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;

    @NotEmpty(message = "L'adresse est obligatoire")
    private String adresse;

    private String siteWeb; // Facultatif, pas obligé

    @NotEmpty(message = "Le secteur d'activité est obligatoire")
    private List<String> secteursActivite;

    @NotEmpty(message = "Le mot de passe est obligatoire")
    private String password;

    @NotEmpty(message = "La confirmation du mot de passe est obligatoire")
    private String passwordConfirm;

    public EntrepriseDto() {
    }

    public EntrepriseDto(String username, String email, String adresse, String siteWeb, List<String> secteursActivite, String password, String passwordConfirm) {
        this.username = username;
        this.email = email;
        this.adresse = adresse;
        this.siteWeb = siteWeb;
        this.secteursActivite = secteursActivite;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public @NotEmpty(message = "Le nom de l'entreprise est obligatoire") String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty(message = "Le nom de l'entreprise est obligatoire") String username) {
        this.username = username;
    }

    public @NotEmpty(message = "L'email est obligatoire") @Email(message = "Format d'email invalide") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "L'email est obligatoire") @Email(message = "Format d'email invalide") String email) {
        this.email = email;
    }

    public @NotEmpty(message = "L'adresse est obligatoire") String getAdresse() {
        return adresse;
    }

    public void setAdresse(@NotEmpty(message = "L'adresse est obligatoire") String adresse) {
        this.adresse = adresse;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public @NotEmpty(message = "Le secteur d'activité est obligatoire") List<String> getSecteursActivite() {
        return secteursActivite;
    }

    public void setSecteursActivite(@NotEmpty(message = "Le secteur d'activité est obligatoire") List<String> secteursActivite) {
        this.secteursActivite = secteursActivite;
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
