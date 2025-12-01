package main.repository;

import main.model.Tarefa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TarefaRepository {
    private List<Tarefa> lista;

    public TarefaRepository() {
        this.lista = new ArrayList<>();
    }

    public void add(Tarefa tarefa) {
        lista.add(tarefa);
    }

    public List<Tarefa> getAll() {
        return new ArrayList<>(lista);
    }

    public Tarefa findById(int id) {
        for (Tarefa t : lista) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    public boolean update(int id, String novoTitulo, String novaDesc, LocalDate novaData) {
        Tarefa t = findById(id);
        if (t != null) {
            t.setTitulo(novoTitulo);
            t.setDescricao(novaDesc);
            t.setData(novaData);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        Tarefa t = findById(id);
        if (t != null) {
            lista.remove(t);
            return true;
        }
        return false;
    }
}