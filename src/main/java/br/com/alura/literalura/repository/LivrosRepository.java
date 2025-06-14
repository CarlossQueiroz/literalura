package br.com.alura.literalura.repository;

import br.com.alura.literalura.modelo.Autor;
import br.com.alura.literalura.modelo.Livros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivrosRepository extends JpaRepository<Livros,Long> {
    boolean existsByTitulo(String titulo);

}
