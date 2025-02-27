package model;

import java.time.LocalDateTime;

public class Livro {
    private final int id;
    private String titulo;
    private Autor autor;
    private boolean disponivel;
    private final LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;

    public Livro(int id, String titulo, Autor autor){
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true;
        this.dataCadastro = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }
}
