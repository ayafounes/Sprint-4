package com.aya.evenements.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aya.evenements.dto.EvenementDTO;
import com.aya.evenements.entities.Evenement;
import com.aya.evenements.entities.Genre;
import com.aya.evenements.service.EvenementService;

import jakarta.validation.Valid;

@Controller
public class EvenementController {

    @Autowired
    EvenementService evenementService;
    @GetMapping("/accessDenied")
    public String error()
    {
    return "accessDenied";
    }
    @GetMapping(value = "/")
    public String welcome() {
     return "index";
    }

    // Afficher la liste des événements
    @RequestMapping("/ListeEvenements")
    public String listeEvenements(ModelMap modelMap,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "size", defaultValue = "2") int size) {
        
        Page<Evenement> events = evenementService.getAllEvenementsParPage(page, size);
        
        modelMap.addAttribute("evenements", events);
        modelMap.addAttribute("pages", new int[events.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("size", size);
        
        return "listeEvenements";
    }

    // Afficher le formulaire pour créer un événement
    @RequestMapping("/showCreate")
    public String showCreate(ModelMap modelMap) {
        modelMap.addAttribute("evenement", new Evenement());
        modelMap.addAttribute("mode", "new");

        // Ajouter les genres disponibles
        List<Genre> genres = evenementService.getAllGenres();
        modelMap.addAttribute("genres", genres);

        return "formEvenement";
    }

    // Sauvegarder l'événement (ajout ou modification)
    @RequestMapping("/saveEvenement")
    public String saveEvenement(@Valid @ModelAttribute("evenement") EvenementDTO evenement,
                              BindingResult bindingResult,
                              @RequestParam("mode") String mode,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "2") int size,
                              ModelMap modelMap) {
        
        if (bindingResult.hasErrors()) {
            List<Genre> genres = evenementService.getAllGenres();
            modelMap.addAttribute("genres", genres);
            modelMap.addAttribute("mode", mode); // Important pour conserver le mode
            return "formEvenement";
        }
       

        if ("new".equals(mode)) {
            evenementService.saveEvenement(evenement);
        } else {
            evenementService.updateEvenement(evenement);
        }

        return "redirect:/ListeEvenements?page=" + page + "&size=" + size;
    }

    // Modifier un événement
    @RequestMapping("/modifierEvenement")
    public String editerEvenement(@RequestParam("id") Long id, ModelMap modelMap) {
        EvenementDTO e = evenementService.getEvenement(id);
        modelMap.addAttribute("evenement", e);
        modelMap.addAttribute("mode", "edit");

        // Ajouter les genres disponibles
        List<Genre> genres = evenementService.getAllGenres();
        modelMap.addAttribute("genres", genres);

        return "formEvenement";
    }

    // Supprimer un événement
    @RequestMapping("/supprimerEvenement")
    public String supprimerEvenement(@RequestParam("id") Long id,
                                    ModelMap modelMap,
                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "2") int size) {
        
        evenementService.deleteEvenementById(id);
        Page<Evenement> events = evenementService.getAllEvenementsParPage(page, size);
        
        modelMap.addAttribute("evenements", events);
        modelMap.addAttribute("pages", new int[events.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        
        return "listeEvenements";
    }

    // Mise à jour avec la date
    @RequestMapping("/updateEvenement")
    public String updateEvenement(@ModelAttribute("evenement") EvenementDTO evenement,
                                @RequestParam("dateEvenement") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                ModelMap modelMap) {
        
        evenement.setDateEvenement(date);
        evenementService.updateEvenement(evenement);
        return "redirect:/ListeEvenements";
    }
}