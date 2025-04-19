package com.entreprise.gestionemployes.controllers;

import com.entreprise.gestionemployes.entities.Entreprise;
import com.entreprise.gestionemployes.services.UtilisateurService;
import lombok.Getter;
import lombok.Setter;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class EntrepriseController implements Serializable {
    @Inject
    private UtilisateurService entrepriseService;

    @Getter
    @Setter
    private Entreprise entreprise = new Entreprise();

    public String enregistrer() {
        entrepriseService.enregistrer(entreprise);
        return "login.xhtml?faces-redirect=true";
    }
}
