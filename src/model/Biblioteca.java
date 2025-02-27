package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Biblioteca {

    private List<Autor> autores = new ArrayList<>();
    private List<Livro> livros = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    private int autorIdCounter = 0;
    private int livroIdCounter = 0;
    private int clienteIdCounter = 0;
    private int emprestimoIdCounter = 0;

    // Métodos de busca
    private Autor getAutorById(int id) {
        return autores.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    private Livro getLivroById(int id) {
        return livros.stream().filter(l -> l.getId() == id).findFirst().orElse(null);
    }

    private Cliente getClienteById(int id) {
        return clientes.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    private Emprestimo getEmprestimoById(int id) {
        return emprestimos.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    private List<Emprestimo> getEmprestimosAtivos() {
        return emprestimos.stream().filter(e -> e.getDataDevolucao() == null).toList();
    }

    // Métodos de adição
    private void addAutor(Autor autor) { autores.add(autor); }
    private void addLivro(Livro livro) { livros.add(livro); }
    private void addCliente(Cliente cliente) { clientes.add(cliente); }
    private void addEmprestimo(Emprestimo emprestimo) { emprestimos.add(emprestimo); }

    // Métodos principais
    public void cadastrarAutor(Scanner scanner) {
        System.out.println("\n========== CADASTRAR AUTOR ==========");
        System.out.print("\nQual o nome do autor: ");
        String nome = scanner.nextLine().toUpperCase();

        Autor novoAutor = new Autor(++autorIdCounter, nome);
        addAutor(novoAutor);

        System.out.println(String.format("O(a) %s foi cadastrado(a) com sucesso!", novoAutor.getNome()));
    }

    public void listarAutores() {
        System.out.println("\n========== LISTA DE AUTORES ==========");
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor cadastrado!");
        } else {
            autores.forEach(a -> System.out.println(a.getId() + " - " + a.getNome()));
        }
    }

    public void cadastrarLivro(Scanner scanner) {
        System.out.println("\n========== CADASTRAR LIVRO ==========");
        System.out.print("\nQual o título do livro: ");
        String titulo = scanner.nextLine().toUpperCase();

        System.out.print("\nQual o código do autor: ");
        int autorID = scanner.nextInt();
        scanner.nextLine();

        Autor autorSelecionado = getAutorById(autorID);

        if (autorSelecionado != null) {
            Livro novoLivro = new Livro(++livroIdCounter, titulo, autorSelecionado);
            addLivro(novoLivro);
            System.out.println("Livro cadastrado com sucesso!");
        } else {
            System.out.println("Autor não encontrado!");
        }
    }

    public void listarLivros() {
        System.out.println("\n========== LISTA DE LIVROS ==========");
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado!");
        } else {
            livros.forEach(l -> {
                String status = l.isDisponivel() ? "Disponível" : "Emprestado";
                System.out.println(l.getId() + " - " + l.getTitulo() + " (" + status + ")");
            });
        }
    }

    public void cadastrarCliente(Scanner scanner) {
        System.out.println("\n========== CADASTRAR CLIENTE ==========");
        System.out.print("\nQual o nome do cliente: ");
        String nomeCliente = scanner.nextLine().toUpperCase();

        Cliente novoCliente = new Cliente(++clienteIdCounter, nomeCliente);
        addCliente(novoCliente);

        System.out.println("Cliente cadastrado com sucesso!");
    }

    public void listarClientes() {
        System.out.println("\n========== LISTA DE CLIENTES ==========");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado!");
        } else {
            clientes.forEach(c -> System.out.println(c.getId() + " - " + c.getNome()));
        }
    }

    public void cadastrarEmprestimo(Scanner scanner) {
        System.out.println("\n========== CADASTRAR EMPRÉSTIMO ==========");
        System.out.print("\nQual o código do livro: ");
        int livroID = scanner.nextInt();
        System.out.print("\nQual o código do cliente: ");
        int clienteID = scanner.nextInt();
        scanner.nextLine();

        Livro livroSelecionado = getLivroById(livroID);
        Cliente clienteSelecionado = getClienteById(clienteID);

        if (livroSelecionado != null && clienteSelecionado != null) {
            if (livroSelecionado.isDisponivel()) {
                Emprestimo novoEmprestimo = new Emprestimo(++emprestimoIdCounter, livroSelecionado, clienteSelecionado);
                addEmprestimo(novoEmprestimo);
                livroSelecionado.setDisponivel(false);
                System.out.println("Empréstimo cadastrado com sucesso!");
            } else {
                System.out.println("Este livro já está emprestado!");
            }
        } else {
            System.out.println("Livro ou cliente não encontrado!");
        }
    }

    public void devolverEmprestimo(Scanner scanner) {
        System.out.println("\n========== DEVOLVER EMPRÉSTIMO ==========");
        System.out.print("\nQual o código do empréstimo: ");
        int codigoEmprestimo = scanner.nextInt();
        scanner.nextLine();

        Emprestimo emprestimoSelecionado = getEmprestimoById(codigoEmprestimo);

        if (emprestimoSelecionado != null && emprestimoSelecionado.getDataDevolucao() == null) {
            emprestimoSelecionado.getLivro().setDisponivel(true);
            emprestimoSelecionado.setDataDevolucao(LocalDateTime.now());
            System.out.println("Empréstimo devolvido com sucesso!");
        } else {
            System.out.println("Empréstimo não encontrado ou já devolvido!");
        }
    }

    public void listarEmprestimosAtivos() {
        System.out.println("\n========== LISTA DE EMPRESTIMOS ATIVOS ==========");
        List<Emprestimo> emprestimosAtivos = this.getEmprestimosAtivos();
        if (emprestimosAtivos.isEmpty()) {
            System.out.println("Nenhum emprestimo cadastrado!");
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");

            emprestimosAtivos.forEach(emprestimo -> {
                String dataFormatada = emprestimo.getDataCriacao().format(formatter);
                System.out.println(String.format("%d - %s - %s - %s",
                        emprestimo.getId(),
                        dataFormatada,
                        emprestimo.getLivro().getTitulo(),
                        emprestimo.getCliente().getNome()));
            });
        }
    }
}
