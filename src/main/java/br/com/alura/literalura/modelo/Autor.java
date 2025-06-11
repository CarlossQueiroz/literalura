package br.com.alura.literalura.modelo;

import java.util.List;

public class Autor {
    private long id;
    private String nome;
    private Integer dataNascimento;
    private Integer dataFalecimento;

    private List<Livros> livros;

    public Autor(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Integer dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getDataFalecimento() {
        return dataFalecimento;
    }

    public void setDataFalecimento(Integer dataFalecimento) {
        this.dataFalecimento = dataFalecimento;
    }

    public List<Livros> getLivros() {
        return livros;
    }

    public void setLivros(List<Livros> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        return "Autor:" + nome +
                ", dataNascimento:" + dataNascimento +
                ", dataFalecimento:" + dataFalecimento +
                ", livros:" + livros +
                '}';
    }
}
