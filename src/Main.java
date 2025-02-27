import model.Autor;
import model.Biblioteca;
import model.Livro;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        boolean isLoop = true;

        while (isLoop) {
            int escolha;
            System.out.println("\n========== MENU ==========");
            System.out.println("[ 1 ] - Cadastrar Autor;");
            System.out.println("[ 2 ] - Listar Autores;");
            System.out.println("[ 3 ] - Cadastrar Livro;");
            System.out.println("[ 4 ] - Listar Livros Disponíveis;");
            System.out.println("\n[ 0 ] - Sair;");
            System.out.print("\nEscolha uma das opções: ");
            escolha = scanner.nextInt();

            scanner.nextLine();

            switch (escolha){
                case 0:
                    isLoop = false;
                    break;
                case 1:
                    System.out.println("\n========== CADASTRAR AUTOR ==========");
                    System.out.print("\nQual o nome do autor: ");
                    String nome = scanner.nextLine().toUpperCase();
                    int quantidadeAutores = biblioteca.getAutores().size();
                    Autor novoAutor = new Autor(++quantidadeAutores, nome);
                    biblioteca.addAutor(novoAutor);
                    System.out.println(String.format("O(a) %s foi cadastrado(a) com sucesso!", novoAutor.getNome()));
                    break;
                case 2:
                    System.out.println("\n========== LISTA DE AUTORES ==========");
                    if (biblioteca.getAutores().isEmpty()) {
                        System.out.println("Nenhum autor cadastrado!");
                    } else {
                        for (Autor autor : biblioteca.getAutores()) {
                            System.out.println(String.format("%d - %s", autor.getId(), autor.getNome()));
                        }
                    }
                    break;
                case 3:
                    System.out.println("\n========== CADASTRAR LIVRO ==========");
                    System.out.print("\nQual o título do livro: ");
                    String titulo = scanner.nextLine().toUpperCase();
                    System.out.print("\nQual o código do autor: ");
                    int autorID = scanner.nextInt();
                    Autor autorSelecionado = biblioteca.getAutorById(autorID);
                    if(autorSelecionado != null){
                        int quantidadeLivros = biblioteca.getAutores().size();
                        Livro novoLivro = new Livro(++quantidadeLivros,titulo,autorSelecionado);
                        biblioteca.addLivro(novoLivro);
                        System.out.println(String.format("O livro %s foi cadastrado(a) com sucesso!", novoLivro.getTitulo()));
                    }else {
                        System.out.println("Autor não encontrado!");
                    }
                    break;
                case 4:
                    System.out.println("\n========== LISTA DE LIVROS DISPONIVEIS ==========");
                    List<Livro> livrosDisponiveis = biblioteca.getLivrosDisponiveis();
                    if (livrosDisponiveis.isEmpty()) {
                        System.out.println("Nenhum livro disponível!");
                    } else {
                        for (Livro livro : livrosDisponiveis) {
                            System.out.println(String.format("%d - %s", livro.getId(), livro.getTitulo()));
                        }
                    }
                    break;
                default:
                    System.out.println("[ ERROR ] - Opção inválida! Escolha uma das opções fornecidas!\n");
                    break;
            }
        }

        scanner.close();
        System.out.println("Finalizando a aplicação!");
    }
}
