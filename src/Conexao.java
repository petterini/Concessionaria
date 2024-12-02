import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public static Connection getConexao(){
		try {
			return (Connection) DriverManager.getConnection("jdbc:postgresql://localhost/concessionaria", "postgres", "pedro");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
