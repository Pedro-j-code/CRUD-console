package main.service;

import main.model.Tarefa;
import main.repository.TarefaRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class TarefaService {
    private TarefaRepository repo;
    private Scanner sc;
    private DateTimeFormatter fmt;

    public TarefaService() {
        this.repo = new TarefaRepository();
        this.sc = new Scanner(System.in);
        this.fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    public void criar() {
        System.out.println("\n--- Nova Tarefa ---");

        System.out.print("Título: ");
        String titulo = sc.nextLine();

        System.out.print("Descrição: ");
        String desc = sc.nextLine();

        LocalDate data = pedirData();

        if (data != null) {
            Tarefa nova = new Tarefa(titulo, desc, data);
            repo.add(nova);
            System.out.println("Tarefa criada! ID: " + nova.getId());
        }
    }

    public void listar() {
        System.out.println("\n--- Todas as Tarefas ---");
        List<Tarefa> todas = repo.getAll();

        if (todas.isEmpty()) {
            System.out.println("Nenhuma tarefa.");
        } else {
            for (Tarefa t : todas) {
                System.out.println(t);
            }
        }
    }

    public void editar() {
        System.out.println("\n--- Editar Tarefa ---");

        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Tarefa t = repo.findById(id);

        if (t == null) {
            System.out.println("Não achei essa tarefa.");
            return;
        }

        System.out.println("Tarefa: " + t);

        System.out.print("Novo título (enter para não mudar): ");
        String novoTitulo = sc.nextLine();
        if (novoTitulo.isEmpty()) {
            novoTitulo = t.getTitulo();
        }

        System.out.print("Nova descrição (enter para não mudar): ");
        String novaDesc = sc.nextLine();
        if (novaDesc.isEmpty()) {
            novaDesc = t.getDescricao();
        }

        System.out.print("Nova data (dd/MM/aaaa ou enter para não mudar): ");
        String dataStr = sc.nextLine();
        LocalDate novaData;

        if (dataStr.isEmpty()) {
            novaData = t.getData();
        } else {
            try {
                novaData = LocalDate.parse(dataStr, fmt);
            } catch (Exception e) {
                System.out.println("Data errada! Use dd/MM/aaaa");
                return;
            }
        }

        boolean ok = repo.update(id, novoTitulo, novaDesc, novaData);
        if (ok) {
            System.out.println("Tarefa atualizada!");
        } else {
            System.out.println("Erro ao atualizar.");
        }
    }

    public void apagar() {
        System.out.println("\n--- Apagar Tarefa ---");

        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        boolean ok = repo.delete(id);
        if (ok) {
            System.out.println("Tarefa apagada!");
        } else {
            System.out.println("Não achei essa tarefa.");
        }
    }

    private LocalDate pedirData() {
        while (true) {
            System.out.print("Data (dd/MM/aaaa): ");
            String txt = sc.nextLine();

            try {
                return LocalDate.parse(txt, fmt);
            } catch (Exception e) {
                System.out.println("Data errada! Tente de novo.");
            }
        }
    }

    public void fechar() {
        sc.close();
    }
}