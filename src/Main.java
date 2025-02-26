import model.Autor;
import model.Biblioteca;

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
            System.out.println("\n[ 0 ] - Sair;");
            System.out.print("\nEscolha uma das opções: ");
            escolha = scanner.nextInt();

            scanner.nextLine(); // Consumir o ENTER pendente

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
                default:
                    System.out.println("[ ERROR ] - Opção inválida! Escolha uma das opções fornecidas!\n");
                    break;
            }
        }

        scanner.close();
        System.out.println("Finalizando a aplicação!");
    }
}
