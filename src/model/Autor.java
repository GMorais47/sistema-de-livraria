package model;

public class Autor {
    private final int id;
    private String nome;

    public Autor(int id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
