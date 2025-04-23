package com.entreprise.gestionemployes.controllers;

import com.entreprise.gestionemployes.dto.EntrepriseDto;
import com.entreprise.gestionemployes.services.EntrepriseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.Serializable;

@Controller
public class AuthController implements Serializable {
    private final EntrepriseService entrepriseService;

    @Autowired
    public AuthController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    // Page d'accueil qui redirige vers la connexion
    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    // Page de connexion
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // Page d'inscription
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("entreprise", new EntrepriseDto());
        return "register";
    }

    // Traitement de l'inscription
    @PostMapping("/register")
    public String registerEntreprise(@Valid @ModelAttribute("entreprise") EntrepriseDto entrepriseDto,
                                     BindingResult result,
                                     Model model) {

        if (result.hasErrors()) {
            return "register";
        }

        try {
            entrepriseService.registerEntreprise(entrepriseDto);
            model.addAttribute("success", "Inscription réussie! Veuillez vous connecter.");
            return "redirect:/login?success";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    // Dashboard accessible après connexion
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
