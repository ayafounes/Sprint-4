package com.aya.evenements.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.aya.evenements.dto.EvenementDTO;
import com.aya.evenements.entities.Evenement;
import com.aya.evenements.entities.Genre;
import com.aya.evenements.repos.EvenementRepository;
import com.aya.evenements.repos.GenreRepository;

@Service
public class EvenementServiceImpl implements EvenementService {

    @Autowired
    EvenementRepository evenementRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public EvenementDTO saveEvenement(EvenementDTO e) {
        return convertEntityToDto( evenementRepository.save(convertDtoToEntity(e)));
    }

    @Override
    public EvenementDTO updateEvenement(EvenementDTO e) {
    	return convertEntityToDto(evenementRepository.save(convertDtoToEntity(e)));

    }

    @Override
    public void deleteEvenement(Evenement e) {
        evenementRepository.delete(e);
    }

    @Override
    public void deleteEvenementById(Long id) {
        evenementRepository.deleteById(id);
    }

    @Override
    public EvenementDTO getEvenement(Long id) {
        return convertEntityToDto(evenementRepository.findById(id).get());
    }

    @Override
    public List<EvenementDTO> getAllEvenements() {
        return evenementRepository.findAll().stream()
        		.map(this::convertEntityToDto)
        		.collect(Collectors.toList());
        
      //OU BIEN
        /*List<Evenement> events = evenementRepository.findAll();
        List<EvenementDTO> listeventDto = new ArrayList<>(events.size());
        for (Evenement e : events)
        listeventDto.add(convertEntityToDto(e));
        return listeventDto;*/

    }
    
    @Override
    public List<Evenement> findByNomEvenement(String nom) {
        return evenementRepository.findByNomEvenement(nom);
    }

    @Override
    public List<Evenement> findByNomEvenementContains(String nom) {
        return evenementRepository.findByNomEvenementContains(nom);
    }

    @Override
    public List<Evenement> findByNomPrix(String nom, Double prix) {
        return evenementRepository.findByNomPrix(nom, prix);
    }

    @Override
    public List<Evenement> findByGenre(Genre genre) {
        return evenementRepository.findByGenre(genre);
    }

    @Override
    public List<Evenement> findByGenreIdGenre(Long id) {
        return evenementRepository.findByGenreIdGenre(id);
    }

    @Override
    public List<Evenement> findByOrderByNomEvenementAsc() {
        return evenementRepository.findByOrderByNomEvenementAsc();
    }

    @Override
    public List<Evenement> trierEvenementsNomsPrix() {
        return evenementRepository.trierEvenementsNomsPrix();
    }
    @Override
    public Page<Evenement> getAllEvenementsParPage(int page, int size) {
        return evenementRepository.findAll(PageRequest.of(page, size));
    }
    @Autowired
    GenreRepository genreRepository;

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    

    
	@Override
	public EvenementDTO convertEntityToDto(Evenement evenement) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

		
		EvenementDTO evenementDTO = modelMapper.map(evenement, EvenementDTO.class);
		 return evenementDTO;
		 

		
		 /*EvenementDTO evenementDTO = new EvenementDTO();
		 evenementDTO.setIdEvenement(evenement.getIdEvenement());
		 evenementDTO.setNomEvenement(evenement.getNomEvenement());
		 evenementDTO.setPrixEvenement(evenement.getPrixEvenement());
		 evenementDTO.setDateEvenement(evenement.getDateEvenement());
		 evenementDTO.setGenre(evenement.getGenre());
		 return evenementDTO;*/

		 /*return  EvenementDTO.builder()
		.idEvenement(evenement.getIdEvenement())
		.nomEvenement(evenement.getNomEvenement())
		.prixEvenement(evenement.getPrixEvenement())
		.dateEvenement(evenement.getDateEvenement())
		.genre(evenement.getGenre())
		
		.build();*/

		
	}

	@Override
	public Evenement convertDtoToEntity(EvenementDTO evenementDto) {
		
		Evenement evenement = new Evenement();
		evenement = modelMapper.map(evenementDto, Evenement.class);
			return evenement;
		
		/*Evenement evenement = new Evenement();
		evenement.setIdEvenement(evenementDto.getIdEvenement());
		evenement.setNomEvenement(evenementDto.getNomEvenement());
		evenement.setPrixEvenement(evenementDto.getPrixEvenement());
		evenement.setDateEvenement(evenementDto.getDateEvenement());
		evenement.setGenre(evenementDto.getGenre());
		 return evenement;*/
	}


}