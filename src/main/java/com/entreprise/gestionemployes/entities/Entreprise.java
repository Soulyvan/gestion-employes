package com.entreprise.gestionemployes.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String username; // Nom de l'entreprise

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String adresse; // Nouvelle propriété

    private String siteWeb; // Site web de l'entreprise (facultatif)

    @ElementCollection
    private List<String> secteursActivite;

    @Column(nullable = false)
    private String password;

    private boolean active = true;

    public Entreprise() {
    }

    public Entreprise(int id, String username, String email, String adresse, String siteWeb, List<String> secteursActivite, String password, boolean active) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.adresse = adresse;
        this.siteWeb = siteWeb;
        this.secteursActivite = secteursActivite;
        this.password = password;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public List<String> getSecteursActivite() {
        return secteursActivite;
    }

    public void setSecteursActivite(List<String> secteursActivite) {
        this.secteursActivite = secteursActivite;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
