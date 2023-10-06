import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Ideia ideia = new Ideia();
        Scanner scanner = new Scanner(System.in);
        boolean fecharMenu = false;

        do {
            int opcao;
            System.out.println("-- Lista de opcoes --");
            System.out.println("1: INSERIR");
            System.out.println("2: DELETAR");
            System.out.println("3: LISTAR");
            System.out.println("Outra opçao: FECHAR MENU");

            System.out.println("Digite sua opçao: ");
            //opcao = Integer.parseInt(scanner.nextLine());
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> {
                    System.out.println("INSERIR");
                    ideia.inserir();
                    break;
                }
                case 2 -> {
                    System.out.println("DELETAR");
                    List<Ideia> ideias = Ideia.listar();
                    for (Ideia i : ideias) {
                        System.out.println("ID: " + i.getId());
                        System.out.println("Titulo: " + i.getTitulo());
                        System.out.println("Descricao: " + i.getDescricao());
                        System.out.println("Urgência: " + i.getUrgencia());
                        System.out.println("---");
                    }
                    ideia.deletar();
                    break;
                }
                case 3 -> {
                    System.out.println("LISTAR");
                    List<Ideia> ideias = Ideia.listar();
                    for (Ideia i : ideias) {
                        System.out.println("ID: " + i.getId());
                        System.out.println("Titulo: " + i.getTitulo());
                        System.out.println("Descricao: " + i.getDescricao());
                        System.out.println("Urgência: " + i.getUrgencia());
                        System.out.println("---");
                    }
                    break;
                }
                default -> fecharMenu = true;
            }
        } while (!fecharMenu);
        scanner.close();
    }
}