package br.com.alura.literalura.principal;

import br.com.alura.literalura.modelo.*;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivrosRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com";

    private List<Livros> listaLivros = new ArrayList<>();
    private LivrosRepository livrosRepositorio;
    private AutorRepository autorRepositorio;

    public Principal(LivrosRepository livrosRepositorio, AutorRepository autorRepositorio) {
        this.livrosRepositorio = livrosRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Buscar livro por título
                    2 - Buscar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determiando idioma\s
                    
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    BuscarLivroPorTítulo();
                    break;
                case 2:
                    buscarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    //buscarSeriePorTitulo();
                    break;
                case 5:
                    //buscarSeriesPorAtor();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    public void listarAutoresRegistrados() {
        List<Autor> autores = autorRepositorio.findAll();

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor registrado.");
            return;
        }

        for (Autor autor : autores) {
            System.out.println("Nome: " + autor.getNome());
            System.out.println("Nascimento: " + autor.getDataNascimento());
            System.out.println("Falecimento: " + autor.getDataFalecimento());
            System.out.println("-----------------------------------");
        }
    }

    private void buscarLivrosRegistrados() {
            List<Livros> livros = livrosRepositorio.findAll();

            if (livros.isEmpty()) {
                System.out.println("Nenhum livro encontrado no banco de dados.");
                return;
            }

            for (Livros livro : livros) {
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Idioma: " + livro.getIdioma());
                System.out.println("Downloads: " + livro.getNumeroDeDownloads());
                livro.getAutor().forEach(autor -> {
                    System.out.println("Autor: " + autor.getNome());
                    System.out.println("Nascimento: " + autor.getDataNascimento());
                    System.out.println("Falecimento: " + autor.getDataFalecimento());
                });
                System.out.println("--------------------------------------");
            }
    }

    private void BuscarLivroPorTítulo() {
        System.out.println("Digite o título do livro que deseja buscar:");
        var livrobusca = scanner.nextLine();

        var json = consumo.obterDados(ENDERECO + "/books?search=" + livrobusca.replace(" ", "+"));

        if (json == null || json.isBlank()) {
            System.out.println("Resposta vazia da API.");
            return;
        }

        ResultadoLivros dados = conversor.obterDados(json, ResultadoLivros.class);
        if (dados.results().isEmpty()) {
            System.out.println("Nenhum livro encontrado com esse título.");
        } else {

            listaLivros = dados.results().stream()
                    .map(this::converterParaLivro)
                    .filter(livro -> !livrosRepositorio.existsByTitulo(livro.getTitulo()))
                    .collect(Collectors.toList());

            livrosRepositorio.saveAll(listaLivros);

            getDadosLivro();
        }
    }

    private Livros converterParaLivro(DadosLivros dl) {
        var livro = new Livros();
        livro.setTitulo(dl.titulo());
        livro.setIdioma(dl.idioma());
        livro.setNumeroDeDownloads(dl.numeroDeDownloads());
        livro.setAutor(dl.autor().stream()
                .map(this::converterParaAutor)
                .collect(Collectors.toList()));

        //repositorio.save(livro);

        return livro;
    }


    private Autor converterParaAutor(DadosAutor da) {
        return autorRepositorio.findByNome(da.nome())
                .orElseGet(() -> {
                    Autor novoAutor = new Autor();
                    novoAutor.setNome(da.nome());
                    novoAutor.setDataNascimento(da.anoNascimento());
                    novoAutor.setDataFalecimento(da.anoFalecimento());
                    return novoAutor;
                });
    }

    private void getDadosLivro() {
        for (Livros livros : listaLivros) {
            System.out.println("Título: " + livros.getTitulo());
            System.out.println("Idioma: " + livros.getIdioma());
            System.out.println("Downloads: " + livros.getNumeroDeDownloads());
            livros.getAutor().stream().map(Autor::getNome).forEach(n -> System.out.println("Autor(es): " + n));
            System.out.println("--------------------------------------");
        }
    }

}
