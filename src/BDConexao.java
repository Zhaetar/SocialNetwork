

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Carol Heloisa
 * @author Matheus Patrick
 */
public class BDConexao {
	static Connection conexao = null;

	public static Connection conectar() {
		try {
			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/redesocial", "postgres","111111");
			return conexao;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return null;

	}
}