import model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            System.out.println("[ 5 ] - Cadastrar Cliente;");
            System.out.println("[ 6 ] - Listar Cliente;");
            System.out.println("[ 7 ] - Cadastrar Empréstimo;");
            System.out.println("[ 8 ] - Listar Empréstimos Ativos;");
            System.out.println("[ 9 ] - Devolver livro;");
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
                        int quantidadeLivros = biblioteca.getLivros().size();
                        Livro novoLivro = new Livro(++quantidadeLivros,titulo,autorSelecionado);
                        biblioteca.addLivro(novoLivro);
                        System.out.println(String.format("O livro %s foi cadastrado(a) com sucesso!", novoLivro.getTitulo()));
                    }else {
                        System.out.println("Autor não encontrado!");
                    }
                    break;
                case 4:
                    System.out.println("\n========== LISTA DE LIVROS DISPONÍVEIS ==========");
                    List<Livro> livrosDisponiveis = biblioteca.getLivrosDisponiveis();
                    if (livrosDisponiveis.isEmpty()) {
                        System.out.println("Nenhum livro disponível!");
                    } else {
                        for (Livro livro : livrosDisponiveis) {
                            System.out.println(String.format("%d - %s", livro.getId(), livro.getTitulo()));
                        }
                    }
                    break;
                case 5:
                    System.out.println("\n========== CADASTRAR CLIENTE ==========");
                    System.out.print("\nQual o nome do cliente: ");
                    String nomeCliente = scanner.nextLine().toUpperCase();
                    int quantidadeClientes = biblioteca.getClientes().size();
                    Cliente novoCliente = new Cliente(++quantidadeClientes, nomeCliente);
                    biblioteca.addCliente(novoCliente);
                    System.out.println(String.format("O(a) cliente %s foi cadastrado(a) com sucesso!", novoCliente.getNome()));
                    break;
                case 6:
                    System.out.println("\n========== LISTA DE CLIENTES ==========");
                    List<Cliente> clientes = biblioteca.getClientes();
                    if (clientes.isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado!");
                    } else {
                        for (Cliente cliente : clientes) {
                            System.out.println(String.format("%d - %s", cliente.getId(), cliente.getNome()));
                        }
                    }
                    break;
                case 7:
                    System.out.println("\n========== CADASTRAR EMPRÉSTIMO ==========");
                    System.out.print("\nQual o código do livro: ");
                    int livroID = scanner.nextInt();
                    System.out.print("\nQual o código do cliente: ");
                    int clienteID = scanner.nextInt();
                    scanner.nextLine();

                    Livro livroSelecionado = biblioteca.getLivroById(livroID);
                    Cliente clienteSelecionado = biblioteca.getClienteById(clienteID);

                    if (livroSelecionado != null && clienteSelecionado != null) {
                        if (livroSelecionado.isDisponivel()) {
                            int quantidadeEmprestimos = biblioteca.getEmprestimos().size();
                            Emprestimo novoEmprestimo = new Emprestimo(++quantidadeEmprestimos, livroSelecionado, clienteSelecionado);
                            biblioteca.addEmprestimo(novoEmprestimo);
                            livroSelecionado.setDisponivel(false);
                            System.out.println("Empréstimo cadastrado com sucesso!");
                        } else {
                            System.out.println("Este livro já está emprestado!");
                        }
                    } else {
                        System.out.println("Livro ou cliente não encontrado!");
                    }
                    break;

                case 8:
                    System.out.println("\n========== LISTA DE EMPRÉSTIMOS ATIVOS ==========");
                    List<Emprestimo> emprestimos = biblioteca.getEmprestimosAtivos();
                    if (emprestimos.isEmpty()) {
                        System.out.println("Nenhum empréstimo ativo cadastrado!");
                    } else {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");

                        for (Emprestimo emprestimo : emprestimos) {
                            String dataFormatada = emprestimo.getDataCriacao().format(formatter);
                            System.out.println(String.format("%d - %s - %s - %s",
                                    emprestimo.getId(),
                                    dataFormatada,
                                    emprestimo.getLivro().getTitulo(),
                                    emprestimo.getCliente().getNome()));
                        }
                    }
                    break;
                case 9:
                    System.out.println("\n========== DEVOLVER LIVRO ==========");
                    System.out.print("\nQual o código do empréstimo: ");
                    int codigoEmprestimo = scanner.nextInt();
                    scanner.nextLine();

                    Emprestimo emprestimoSelecionado = biblioteca.getEmprestimoById(codigoEmprestimo);
                    if(emprestimoSelecionado != null){
                        if(emprestimoSelecionado.getDataDevolucao() == null){
                            Livro livro = emprestimoSelecionado.getLivro();
                            livro.setDisponivel(true);
                            emprestimoSelecionado.setDataDevolucao(LocalDateTime.now());
                            System.out.println(String.format("O empréstimo %d foi devolvido com sucesso!", emprestimoSelecionado.getId()));
                        }else {
                            System.out.println("Esse empréstimo já foi devolvido!");
                        }
                    }else {
                        System.out.println("Empréstimo não encontrado!");
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
