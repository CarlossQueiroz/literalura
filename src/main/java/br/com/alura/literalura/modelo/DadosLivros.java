package br.com.alura.literalura.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivros(
        @JsonAlias("title") String titulo,
        @JsonAlias("autor") Autor autor,
        @JsonAlias("idiome") Idioma idiom,
        @JsonAlias("number downsloads") Integer numeroDeDownloads) {
}
