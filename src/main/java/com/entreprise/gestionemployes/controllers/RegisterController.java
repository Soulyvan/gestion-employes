package com.entreprise.gestionemployes.controllers;

import com.entreprise.gestionemployes.services.UtilisateurService;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import lombok.Data;

@ManagedBean
@RequestScoped
@Data
public class RegisterController {
    private String username;
    private String email;
    private String password;
    private String entrepriseNom;

    @Inject
    private UtilisateurService userService;

    public String register() {
        userService.registerUser(username, email, password, entrepriseNom);
        return "login.xhtml?faces-redirect=true";
    }
}
