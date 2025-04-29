package com.aya.evenements.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.aya.evenements.dto.EvenementDTO;
import com.aya.evenements.entities.Evenement;
import com.aya.evenements.entities.Genre;

public interface EvenementService {
   
    EvenementDTO getEvenement(Long id);
    List<EvenementDTO> getAllEvenements();
    
    EvenementDTO saveEvenement(EvenementDTO e);
    EvenementDTO updateEvenement(EvenementDTO e);
    
    void deleteEvenement(Evenement e);
    void deleteEvenementById(Long id);
    
    
    List<Evenement> findByNomEvenement(String nom);
    List<Evenement> findByNomEvenementContains(String nom);
    List<Evenement> findByNomPrix(String nom, Double prix);
    List<Evenement> findByGenre(Genre genre);
    List<Evenement> findByGenreIdGenre(Long id);
    List<Evenement> findByOrderByNomEvenementAsc();
    List<Evenement> trierEvenementsNomsPrix();
    Page<Evenement> getAllEvenementsParPage(int page, int size);
    List<Genre> getAllGenres();
    EvenementDTO convertEntityToDto (Evenement evenement);
    Evenement convertDtoToEntity(EvenementDTO evenementDto);

}