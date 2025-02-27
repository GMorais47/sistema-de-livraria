package model;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    List<Autor> autores = new ArrayList<>();
    List<Livro> livros = new ArrayList<>();
    List<Cliente> clientes = new ArrayList<>();
    List<Emprestimo> emprestimos = new ArrayList<>();

    public void addAutor(Autor autor){
        autores.add(autor);
    }

    public List<Autor>getAutores(){
        return autores;
    }

    public Autor getAutorById(int id){
        for (Autor autor : autores){
            if(autor.getId() == id)
             return autor;
        }
        return null;
    }

    public List<Livro> getLivros(){
        return livros;
    }

    public Livro getLivroById(int id){
        for (Livro livro : livros){
            if(livro.getId() == id)
                return livro;
        }
        return null;
    }

    public List<Livro> getLivrosDisponiveis(){
        List<Livro> disponiveis = new ArrayList<>();

        for (Livro livro : livros){
            if(livro.isDisponivel())
                disponiveis.add(livro);
        }

        return disponiveis;
    }

    public void addLivro(Livro livro){
        livros.add(livro);
    }

    public void addCliente(Cliente cliente){
        clientes.add(cliente);
    }

    public List<Cliente> getClientes(){
        return clientes;
    }

    public Cliente getClienteById(int id){
        for (Cliente cliente : clientes){
            if(cliente.getId() == id)
                return cliente;
        }
        return null;
    }

    public List<Emprestimo> getEmprestimos(){
        return emprestimos;
    }

    public List<Emprestimo> getEmprestimosAtivos(){
        List<Emprestimo> emprestimosAtivos = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos){
            if( emprestimo.getDataDevolucao() == null)
                emprestimosAtivos.add(emprestimo);
        }
        return emprestimosAtivos;
    }

    public Emprestimo getEmprestimoById(int id){
        for (Emprestimo emprestimo : emprestimos){
            if(emprestimo.getId() == id)
                return emprestimo;
        }
        return null;
    }

    public void addEmprestimo(Emprestimo emprestimo){
        emprestimos.add(emprestimo);
    }

}
