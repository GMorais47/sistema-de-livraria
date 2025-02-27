package model;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    List<Autor> autores = new ArrayList<>();
    List<Livro> livros = new ArrayList<>();

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

    public List<Livro>getLivros(){
        return livros;
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

}
