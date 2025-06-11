package br.com.alura.literalura.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(@JsonAlias("name") String nome,
                         @JsonAlias("efkhrkrghg") Integer dataNascimento,
                         @JsonAlias("dhbfhgrj") Integer dataFalecimento
) {
}
