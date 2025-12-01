import main.service.TarefaService;
import java.util.Scanner;

public class appMain {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        TarefaService servico = new TarefaService();

        boolean rodando = true;

        while (rodando) {
            mostrarMenu();
            System.out.print("\nOpção: ");
            int op = entrada.nextInt();
            entrada.nextLine();

            switch (op) {
                case 1:
                    servico.criar();
                    break;
                case 2:
                    servico.listar();
                    break;
                case 3:
                    servico.editar();
                    break;
                case 4:
                    servico.apagar();
                    break;
                case 5:
                    rodando = false;
                    System.out.println("\nFim!");
                    break;
                default:
                    System.out.println("Opção errada!");
            }

            if (rodando) {
                System.out.println("\nEnter para continuar...");
                entrada.nextLine();
            }
        }

        servico.fechar();
        entrada.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Criar tarefa");
        System.out.println("2. Ver tarefas");
        System.out.println("3. Editar tarefa");
        System.out.println("4. Apagar tarefa");
        System.out.println("5. Sair");
    }
}