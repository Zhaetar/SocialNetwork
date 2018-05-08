import java.beans.Statement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.sun.corba.se.pept.transport.Connection;

public class SocialNetwork {

    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    public void createUser() throws InterruptedException, ParseException{
    	Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o email: ");
		String email = scanner.next();
		System.out.println("Digite o nome: ");
		String name = scanner.next();
		System.out.println("Digite a Cidade de Nascimento: ");
		String birthTown = scanner.next();
		System.out.println("Digite a Cidade de Residencia: ");
		String livingTown = scanner.next();
		/*System.out.println("Digite a Data de Nascimento: ");
		String dataNasc = scanner.next();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        Date birthDate = dateFormat.parse(dataNasc);*/
        
        User user = new User(name, email, birthTown, livingTown/*, birthDate*/);
        user.insert();
		Interface.init();
		}
    
//    public void getUserByID(int userID) {
//		Statement statement = null;
//		Connection connection = (Connection) BDConexao.conectar();
// 	   String sql = "SELECT nome, CPF, email, "
// 	   		+ "residencia, nascimento"
// 	   		+ "FROM users " + "WHERE" + " email=?"; 
// 	    try { 
// 	    	statement.setInt(1, userID); 
// 	        rs = statement.executeQuery(); 
// 	        while (rs.next()){ 
// 	            User user = new User();
// 	            user.setEmail(rs.getString("email")); 
// 	            user.setName(rs.getString("nome")); 
// 	            //user.set(rs.getString("vachCustomerFirstName")); 
// 	            return user;
// 	        } 
// 	    } catch (SQLException e) {}
// 	        return null;
// 	    } finally {
//			if(rs != null) {
// 	            rs.close();
// 	        }
// 	    }
//    }
    
    public void deleteFriendship() {
//    	for (User user : userList) {
//    	    if ("peter".equals(user.getName()) {
//    	        return user;
//    	    }
//    	}
    }
    	
    
}
