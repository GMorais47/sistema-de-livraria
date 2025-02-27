import model.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        boolean isLoop = true;

        while (isLoop) {
            int escolha = -1;

            printMenu();

            if (scanner.hasNextInt()) {
                escolha = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("[ ERROR ] - Entrada inválida! Insira um número.");
                scanner.nextLine();
                continue;
            }

            switch (escolha) {
                case 0:
                    isLoop = false;
                    break;
                case 1:
                    biblioteca.cadastrarAutor(scanner);
                    break;
                case 2:
                    biblioteca.listarAutores();
                    break;
                case 3:
                    biblioteca.cadastrarLivro(scanner);
                    break;
                case 4:
                    biblioteca.listarLivros();
                    break;
                case 5:
                    biblioteca.cadastrarCliente(scanner);
                    break;
                case 6:
                    biblioteca.listarClientes();
                    break;
                case 7:
                    biblioteca.cadastrarEmprestimo(scanner);
                    break;
                case 8:
                    biblioteca.listarEmprestimosAtivos();
                    break;
                case 9:
                    biblioteca.devolverEmprestimo(scanner);
                    break;
                default:
                    System.out.println("[ ERROR ] - Opção inválida! Escolha uma das opções fornecidas!\n");
            }
        }

        scanner.close();
        System.out.println("Finalizando a aplicação!");
    }

    private static void printMenu(){
        System.out.println("\n========== MENU ==========");
        System.out.println("[ 1 ] - Cadastrar Autor;");
        System.out.println("[ 2 ] - Listar Autores;");
        System.out.println("[ 3 ] - Cadastrar Livro;");
        System.out.println("[ 4 ] - Listar Livros Disponíveis;");
        System.out.println("[ 5 ] - Cadastrar Cliente;");
        System.out.println("[ 6 ] - Listar Cliente;");
        System.out.println("[ 7 ] - Cadastrar Empréstimo;");
        System.out.println("[ 8 ] - Listar Empréstimos Ativos;");
        System.out.println("[ 9 ] - Devolver livro;");
        System.out.println("\n[ 0 ] - Sair;");
        System.out.print("\nEscolha uma das opções: ");
    }
}
