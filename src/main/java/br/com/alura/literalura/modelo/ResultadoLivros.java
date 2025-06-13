package br.com.alura.literalura.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResultadoLivros(List<DadosLivros> results) {
}
