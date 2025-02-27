package model;

import java.time.LocalDateTime;

public class Emprestimo {
    private final int id;
    private final Livro livro;
    private final Cliente cliente;
    private final LocalDateTime dataCriacao;
    private LocalDateTime dataDevolucao;

    public Emprestimo(int id, Livro livro, Cliente cliente){
        this.id = id;
        this.livro = livro;
        this.cliente = cliente;
        this.dataCriacao = LocalDateTime.now();
    }

    public Livro getLivro() {
        return livro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDateTime dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
