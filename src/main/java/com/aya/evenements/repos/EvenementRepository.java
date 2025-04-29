package com.aya.evenements.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.aya.evenements.entities.Evenement;
import com.aya.evenements.entities.Genre;

import java.util.List;
@RepositoryRestResource(path = "rest")
public interface EvenementRepository extends JpaRepository<Evenement, Long> {
	 	List<Evenement> findByNomEvenement(String nom);
	    List<Evenement> findByNomEvenementContains(String nom);
	    @Query("select e from Evenement e where e.nomEvenement like %?1% and e.prixEvenement > ?2")
	    List<Evenement> findByNomPrix(String nom, Double prix);
	    @Query("select e from Evenement e where e.genre = ?1")
	    List<Evenement> findByGenre(Genre genre);
	    List<Evenement> findByGenreIdGenre(Long id);
	    List<Evenement> findByOrderByNomEvenementAsc();
	    @Query("select e from Evenement e order by e.nomEvenement ASC, e.prixEvenement DESC")
	    List<Evenement> trierEvenementsNomsPrix();
}
