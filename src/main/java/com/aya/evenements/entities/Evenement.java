package com.aya.evenements.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity

public class Evenement {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvenement;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateEvenement;

    @NotNull
    @Size(min = 4, max = 50)
    private String nomEvenement;

    @Min(value = 0)
    @Max(value = 10000)
    private Double prixEvenement;
    
    @ManyToOne
	private Genre genre;
    public Evenement() {
        super();
    }

    public Evenement(String nomEvenement, Double prixEvenement, Date dateEvenement) {
        super();
        this.nomEvenement = nomEvenement;
        this.prixEvenement = prixEvenement;
        this.dateEvenement = dateEvenement;
    }

    public Long getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(Long idEvenement) {
        this.idEvenement = idEvenement;
    }

    public String getNomEvenement() {
        return nomEvenement;
    }

    public void setNomEvenement(String nomEvenement) {
        this.nomEvenement = nomEvenement;
    }

    public Double getPrixEvenement() {
        return prixEvenement;
    }

    public void setPrixEvenement(Double prixEvenement) {
        this.prixEvenement = prixEvenement;
    }

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    @Override
    public String toString() {
        return "Evenement [idEvenement=" + idEvenement + ", nomEvenement=" + nomEvenement + ", prixEvenement=" + prixEvenement
                + ", dateEvenement=" + dateEvenement + "]";
    }
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}