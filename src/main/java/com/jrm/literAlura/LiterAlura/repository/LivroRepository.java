package com.jrm.literAlura.LiterAlura.repository;

import com.jrm.literAlura.LiterAlura.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    @Query("SELECT l FROM Livro l WHERE :idioma IN (l.linguagens)")
    List<Livro> findByIdioma(@Param("idioma") String idioma);
}
