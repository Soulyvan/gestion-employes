package com.entreprise.gestionemployes.controllers;

import com.entreprise.gestionemployes.dto.DirectionDto;
import com.entreprise.gestionemployes.entities.Direction;
import com.entreprise.gestionemployes.services.DirectionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/directions")
public class DirectionController {
    private final DirectionService directionService;

    @Autowired
    public DirectionController(DirectionService directionService) {
        this.directionService = directionService;
    }

    // Afficher toutes les directions
    @GetMapping("/")
    public String listDirections(Model model) throws Exception {
        model.addAttribute("directions", directionService.getDirectionsForCurrentEntreprise());
        return "liste-directions";
    }

    // Afficher formulaire d'ajout
    @GetMapping("/ajouter")
    public String showCreateForm(Model model) {
        model.addAttribute("direction", new DirectionDto());
        return "ajouter-direction";
    }

    // Traitement de l'ajout
    @PostMapping("/ajouter")
    public String createDirection(@Valid @ModelAttribute("direction") DirectionDto directionDto,
                                  BindingResult result, Model model) throws Exception {
        if (result.hasErrors()) {
            return "ajouter-direction";
        }

        directionService.createDirection(directionDto);
        return "redirect:/directions";
    }

    // Afficher détail d'une direction
    @GetMapping("/{id}")
    public String viewDirectionDetails(@PathVariable int id, Model model) {
        Optional<Direction> directionOpt = directionService.findById(id);
        if (directionOpt.isPresent()) {
            model.addAttribute("direction", directionOpt.get());
            return "details-direction";
        } else {
            return "redirect:/directions";
        }
    }

}
