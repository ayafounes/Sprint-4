package com.aya.evenements.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aya.evenements.entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
   }
