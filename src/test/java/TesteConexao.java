import java.sql.Connection;
import java.sql.DriverManager;

public class TesteConexao {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sistema_clientes", "root", "");
            System.out.println("Conectado com sucesso!");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
