package com.entreprise.gestionemployes.beans;

import com.entreprise.gestionemployes.entities.Utilisateur;
import com.entreprise.gestionemployes.services.UtilisateurService;
import jakarta.faces.application.FacesMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.io.IOException;

@Named("loginBean")
@SessionScoped
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginBean {
    @Inject
    private UtilisateurService utilisateurService;

    private String email;
    private String motDePasse;
    private Utilisateur utilisateurConnecte;

    public String connexion() {
        Utilisateur u = utilisateurService.trouverParEmailEtMotDePasse(email, motDePasse);
        if (u != null) {
            utilisateurConnecte = u;
            return "accueil.xhtml?faces-redirect=true";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Email ou mot de passe incorrect"));
        return null;
    }

    public String deconnexion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true";
    }

    // Getters / Setters
}
