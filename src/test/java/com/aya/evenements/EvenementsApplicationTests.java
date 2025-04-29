package com.aya.evenements;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.aya.evenements.entities.Evenement;
import com.aya.evenements.entities.Genre;
import com.aya.evenements.repos.EvenementRepository;
import com.aya.evenements.service.EvenementService;

@SpringBootTest
class EvenementsApplicationTests {

    @Autowired
    private EvenementRepository evenementRepository;

    @Autowired
    private EvenementService evenementService;  // Ajouter cette annotation ici

    @Test
    public void testCreateEvenement() {
        Evenement event = new Evenement("Coachella", 200.0, new Date());
        evenementRepository.save(event);
    }

    @Test
    public void testFindEvenement() {
        Evenement e = evenementRepository.findById(1L).get();
        System.out.println(e);
    }

    @Test
    public void testUpdateEvenement() {
        Evenement e = evenementRepository.findById(2L).get();
        e.setPrixEvenement(1000.0);
        evenementRepository.save(e);
    }

    @Test
    public void testDeleteEvenement() {
        evenementRepository.deleteById(3L);
    }

    @Test
    public void testListerTousEvenements() {
        List<Evenement> events = evenementRepository.findAll();
        for (Evenement e : events) {
            System.out.println(e);
        }
    }

    
    @Test
    public void testFindByNomEvenement() {
        List<Evenement> events = evenementRepository.findByNomEvenement("Festival Culturel");
        for (Evenement e : events) {
            System.out.println(e);
        }
    }
    @Test
    public void testFindByNomEvenementContains() {
        List<Evenement> events = evenementRepository.findByNomEvenementContains("Festival Culturel");
        for (Evenement e : events) {
            System.out.println(e);
        }
    }
    @Test
    public void testFindByNomPrix() {
        List<Evenement> events = evenementRepository.findByNomPrix("Festival Culturel", 120.0);
        for (Evenement e : events) {
            System.out.println(e);
        }
    }
    @Test
    public void testFindByGenre() {
        Genre genre = new Genre();
        genre.setIdGenre(1L);
        
        List<Evenement> events = evenementRepository.findByGenre(genre);
        
        for (Evenement e : events) {
            System.out.println(e);
        }
    }
    @Test
    public void testFindByGenreIdGenre() {
        List<Evenement> events = evenementRepository.findByGenreIdGenre(1L);
        
        for (Evenement event : events) {
            System.out.println(event);
        }
    }
    @Test
    public void testFindByOrderByNomEvenementAsc() {
        List<Evenement> events = evenementRepository.findByOrderByNomEvenementAsc();
        
        for (Evenement event : events) {
            System.out.println(event);
        }
    }
    @Test
    public void testTrierEvenementsNomsPrix() {
        List<Evenement> events = evenementRepository.trierEvenementsNomsPrix();
        
        for (Evenement event : events) {
            System.out.println(event);
        }
    }
    

    
    
    
}