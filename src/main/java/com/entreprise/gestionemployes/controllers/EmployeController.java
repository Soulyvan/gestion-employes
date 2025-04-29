package com.entreprise.gestionemployes.controllers;

import com.entreprise.gestionemployes.dto.EmployeDto;
import com.entreprise.gestionemployes.dto.ExperienceProfessionnelleDto;
import com.entreprise.gestionemployes.entities.Employe;
import com.entreprise.gestionemployes.services.EmployeService;
import com.entreprise.gestionemployes.services.DirectionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employes")
public class EmployeController {
    private final EmployeService employeService;
    private final DirectionService directionService;

    @Autowired
    public EmployeController(EmployeService employeService, DirectionService directionService) {
        this.employeService = employeService;
        this.directionService = directionService;
    }

    // Liste de tous les employés
    @GetMapping("/")
    public String listEmployes(Model model) throws Exception {
        List<Employe> employes = employeService.getAllEmployesForCurrentEntreprise();
        model.addAttribute("employes", employes);
        return "liste-employes";
    }

    // Formulaire pour ajouter un employé
    @GetMapping("/ajouter")
    public String showCreateForm(Model model) throws Exception {
        model.addAttribute("employe", new EmployeDto());
        model.addAttribute("directions", directionService.getDirectionsForCurrentEntreprise());
        return "ajouter-employe";
    }

    // Traitement ajout employé
    @PostMapping("/ajouter")
    public String createEmploye(@Valid @ModelAttribute("employe") EmployeDto employeDto,
                                BindingResult result, Model model) throws Exception {
        if (result.hasErrors()) {
            model.addAttribute("directions", directionService.getDirectionsForCurrentEntreprise());
            return "ajouter-employe";
        }

        employeService.createEmploye(employeDto);
        return "redirect:/directions/"; // retour à liste directions
    }

    // Voir fiche employé
    @GetMapping("/{id}")
    public String viewEmployeDetails(@PathVariable int id, Model model) throws Exception {
        var employeOpt = employeService.findById(id);
        if (employeOpt.isPresent()) {
            model.addAttribute("employe", employeOpt.get());
            return "fiche-employe";
        } else {
            return "redirect:/directions";
        }
    }

    // Formulaire de modification
    @GetMapping("/modifier/{id}")
    public String showEditForm(@PathVariable int id, Model model) throws Exception {
        Employe employe = employeService.findById(id).orElseThrow(() -> new Exception("Employé non trouvé"));
        EmployeDto dto = new EmployeDto();

        dto.setNom(employe.getNom());
        dto.setPrenom(employe.getPrenom());
        dto.setAdresse(employe.getAdresse());
        dto.setTelephone(employe.getTelephone());
        dto.setEmail(employe.getEmail());
        dto.setFonction(employe.getFonction());
        dto.setDateEntreeService(employe.getDateEntreeService());
        dto.setSalaireAnnuel(employe.getSalaireAnnuel());
        dto.setDirectionId(employe.getDirection().getId());

        List<ExperienceProfessionnelleDto> exps = new ArrayList<>();
        if (employe.getExperiences() != null) {
            employe.getExperiences().forEach(exp -> {
                ExperienceProfessionnelleDto dtoExp = new ExperienceProfessionnelleDto();
                dtoExp.setDateDebut(exp.getDateDebut());
                dtoExp.setDateFin(exp.getDateFin());
                dtoExp.setLieu(exp.getLieu());
                dtoExp.setFonction(exp.getFonction());
                dtoExp.setDescription(exp.getDescription());
                exps.add(dtoExp);
            });
        }
        dto.setExperiences(exps);

        model.addAttribute("employe", dto);
        model.addAttribute("employeId", id);
        model.addAttribute("directions", directionService.getDirectionsForCurrentEntreprise());
        return "modifier-employe";
    }

    // Traitement modification
    @PostMapping("/modifier/{id}")
    public String updateEmploye(@PathVariable int id,
                                @Valid @ModelAttribute("employe") EmployeDto employeDto,
                                BindingResult result, Model model) throws Exception {
        if (result.hasErrors()) {
            model.addAttribute("directions", directionService.getDirectionsForCurrentEntreprise());
            model.addAttribute("employeId", id);
            return "modifier-employe";
        }

        /*employeService.updateEmploye(id, employeDto);
        return "redirect:/employes/" + id;*/

        try {
            employeService.updateEmploye(id, employeDto);
        } catch (Exception e) {
            e.printStackTrace(); // LOG CONSOLE
            model.addAttribute("employeId", id);
            model.addAttribute("directions", directionService.getDirectionsForCurrentEntreprise());
            model.addAttribute("erreur", e.getMessage()); // pour affichage dans le HTML
            return "modifier-employe";
        }

        return "redirect:/employes/" + id;
    }

}
