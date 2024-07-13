package com.jrm.literAlura.LiterAlura.principal;

import com.jrm.literAlura.LiterAlura.dto.ResultadoDTO;
import com.jrm.literAlura.LiterAlura.models.Autor;
import com.jrm.literAlura.LiterAlura.models.Livro;
import com.jrm.literAlura.LiterAlura.repository.AutorRepository;
import com.jrm.literAlura.LiterAlura.repository.LivroRepository;
import com.jrm.literAlura.LiterAlura.service.ConsumoApi;
import com.jrm.literAlura.LiterAlura.service.ConverteDados;

import java.util.*;

public class Main {
    private final String ENDERECO = "https://gutendex.com/";
    private Scanner in = new Scanner(System.in);
    private ConverteDados conversor = new ConverteDados();
    private ConsumoApi consumoApi = new ConsumoApi();

    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    public Main(LivroRepository r, AutorRepository a) {
        this.livroRepository = r;
        this.autorRepository = a;
    }

    public void mostreMenu() {
        var opcao = -1;
        var menu = """
                **********OPÇÕES**********
                                
                1 - Buscar o livro pelo seu título
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em um determinado ano
                5 - Listar livros em um determinado idioma
                                
                0 - sair
                """;
        while (opcao != 0) {
            System.out.println(menu);
            System.out.println("Escolha uma das opções acima:");

            opcao = in.nextInt();
            in.nextLine();

            switch (opcao) {
                case 1:
                    busqueLivroPeloTitulo();
                    break;
                case 2:
                    listeLivrosRegistrados();
                    break;
                case 3:
                    listeAutoresRegistrados();
                    break;
                case 4:
                    listeAutoresVivosEmDeterminadoAno();
                    break;
                case 5:
                    ListeLivrosPeloIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Digite uma opção válida!");
            }
        }
    }

    private void ListeLivrosPeloIdioma() {
        System.out.println("Escolha um dos idiomas a pesquisar:\n- pt\n- en");
        var idioma = in.nextLine();

        List<Livro> livros = livroRepository.findByIdioma(idioma);
        livros.forEach(System.out::println);
    }

    private void listeAutoresVivosEmDeterminadoAno() {
        System.out.println("Digite o ano que deseja: ");
        var ano = in.nextInt();

        List<Autor> autores = autorRepository.acharAutoresVivos(ano);
        autores.forEach(System.out::println);
    }

    private void listeAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();

        Set<Autor> autoresSet = new HashSet<>(autores);

        List<Autor> autoresSemRepeticao = new ArrayList<>(autoresSet);

        autoresSemRepeticao.forEach(System.out::println);
    }

    private void listeLivrosRegistrados() {
        List<Livro> livros = livroRepository.findAll();

        livros.forEach(System.out::println);
    }

    private void busqueLivroPeloTitulo() {
        ResultadoDTO dados = getDados();

        if (dados.contador() == 0) {
            System.out.println("Livro não encontrado...");
        } else {
            List<Livro> livrosAdicionados = dados.resultados().stream()
                    .map(Livro::new)
                    .toList();

            livroRepository.saveAll(livrosAdicionados);
        }
    }

    private ResultadoDTO getDados() {
        System.out.println("Digite o título do livro: ");
        var nomeLivro = in.nextLine();
        String enderecoBusca = ENDERECO + "books/?search=" + nomeLivro.toLowerCase().replaceAll(" ", "+");

        String json = consumoApi.obterDados(enderecoBusca);
        ResultadoDTO dados = conversor.obtemDados(json, ResultadoDTO.class);

        return dados;
    }

}
