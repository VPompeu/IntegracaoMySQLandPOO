import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ideia {
    DTO database = new DTO();
    private int id;
    private String titulo;
    private String descricao;
    private Urgencia urgencia;

    public Ideia() {
    }

    public Ideia(int id, String titulo, String descricao, Urgencia urgencia) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.urgencia = urgencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Urgencia getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(Urgencia urgencia) {
        this.urgencia = urgencia;
    }


    public void inserir() {
        try {
            Connection connection = database.getConnection();
            Scanner scanner = new Scanner(System.in);

            System.out.println("ID: ");
            //id = scanner.nextInt();
            id = Integer.parseInt(scanner.nextLine());
            
            System.out.println("Titulo: ");
            //scanner.nextLine();
            titulo = scanner.nextLine();

            System.out.println("Descrição: ");
            descricao = scanner.nextLine();

            int valorUrgencia;
            do {
                System.out.println("Urgência (1 - 5): ");
                valorUrgencia = Integer.parseInt(scanner.nextLine());
                switch (valorUrgencia) {
                    case 1:
                        urgencia = Urgencia.UM;
                        break;

                    case 2:
                        urgencia = Urgencia.DOIS;
                        break;

                    case 3:
                        urgencia = Urgencia.TRES;
                        break;

                    case 4:
                        urgencia = Urgencia.QUATRO;
                        break;

                    case 5:
                        urgencia = Urgencia.CINCO;
                        break;

                    default:
                        System.out.println("Urgência inválida!");
                }
            } while (urgencia == null);

            String query = "INSERT INTO Ideia (id, titulo, descricao, urgencia) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, titulo);
            preparedStatement.setString(3, descricao);
            preparedStatement.setInt(4, urgencia.getValor());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            //scanner.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar() {
        try {

            Connection connection = database.getConnection();
            Scanner scanner = new Scanner(System.in);

            System.out.println("Informe o ID da ideia: ");
            int deleteId = scanner.nextInt();

            if (checaId(deleteId)) {
                String query = "DELETE FROM Ideia WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.setInt(1, deleteId);
                preparedStatement.executeUpdate();

                System.out.println("Ideia excluida!");
            } else {
                System.out.println("Falha ao excluir a ideia!");
            }

            //scanner.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para checar se o ID está cadastrado!
    public boolean checaId(int deleteId) {
        try {
            Connection connection = database.getConnection();
            String query = "SELECT COUNT(*) FROM Ideia WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, deleteId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Ideia> listar() {
        List<Ideia> ideias = new ArrayList<>();
        DTO database = new DTO();

        try {
            Connection connection = database.getConnection();
            String query = "SELECT * FROM Ideia";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String titulo = resultSet.getString("titulo");
                String descricao = resultSet.getString("descricao");
                int valorUrgencia = resultSet.getInt("urgencia");

                Urgencia urgencia = Urgencia.fromValor(valorUrgencia);

                Ideia ideia = new Ideia(id, titulo, descricao, urgencia);
                ideias.add(ideia);
            }
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ideias;
    }
}