package main.model;

import java.time.LocalDate;

public class Tarefa {
    private static int proximoId = 1;

    private int id;
    private String titulo;
    private String descricao;
    private LocalDate data;

    public Tarefa(String titulo, String descricao, LocalDate data) {
        this.id = proximoId++;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | " + titulo + " | " + descricao + " | Data: " + data;
    }
}