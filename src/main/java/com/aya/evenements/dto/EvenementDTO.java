package com.aya.evenements.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.aya.evenements.entities.Genre;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvenementDTO {
	private Long idEvenement;
	private String nomEvenement;
	private Double prixEvenement;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateEvenement;
	private Genre genre;
	private String nomGen;
	
}
