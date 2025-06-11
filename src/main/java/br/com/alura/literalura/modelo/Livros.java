package br.com.alura.literalura.modelo;

public class Livros {
    private Long id;
    private String titulo;
    private Autor autor;
    private Idioma idioma;
    private Integer numeroDeDownloads;

    public Livros(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDeDownloads() {
        return numeroDeDownloads;
    }

    public void setNumeroDeDownloads(Integer numeroDeDownloads) {
        this.numeroDeDownloads = numeroDeDownloads;
    }

    @Override
    public String toString() {
        return "Livro: " + titulo + '\'' +
                ", autor=" + autor +
                ", idioma=" + idioma +
                ", numeroDeDownloads=" + numeroDeDownloads +
                '}';
    }
}
