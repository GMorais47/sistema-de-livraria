package model;

import java.time.LocalDateTime;

public class Cliente {
    private final int id;
    private String nome;
    private final LocalDateTime dataCriacao;

    public Cliente(int id, String nome){
        this.id = id;
        this.nome = nome;
        this.dataCriacao = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
