package model;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    List<Autor> autores = new ArrayList<>();

    public void addAutor(Autor autor){
        autores.add(autor);
    }

    public List<Autor>getAutores(){
        return autores;
    }

}
