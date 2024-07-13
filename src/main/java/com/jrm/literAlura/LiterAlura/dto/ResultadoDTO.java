package com.jrm.literAlura.LiterAlura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResultadoDTO(
            @JsonAlias("count") Long contador,
            @JsonAlias("results") List<DadosLivro> resultados
) {
}
