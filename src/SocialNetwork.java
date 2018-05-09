import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class SocialNetwork {

    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    public void createUser() throws InterruptedException, ParseException, SQLException{
    	Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o email: ");
		String email = scanner.next();
		System.out.println("Digite o nome: ");
		String name = scanner.next();
		System.out.println("Digite a Cidade de Nascimento: ");
		String birthTown = scanner.next();
		System.out.println("Digite a Cidade de Residencia: ");
		String livingTown = scanner.next();
		System.out.println("Digite a Data de Nascimento (dd/MM/yyyy): ");
		String birthDate = scanner.next();

        User user = new User(email);
        user.insert();
        user.update(name, email, birthTown, livingTown, birthDate);
		Interface.init();
	}
    
    public void updateUser() throws InterruptedException, ParseException, SQLException {
    	String email = getEmail();
    	User user = getUser(email);
    	int exit = 0;
    	Scanner scanner = new Scanner(System.in);

    	String newEmail = user.getEmail();
    	String newName = user.getName();
    	String newBirthTown = user.getBirthTown();
    	String newLivingTown = user.getLivingTown();
    	String newBirthDate = user.getBirthDate();
    	
    	while(exit!=1) {
	    	System.out.println("O que você deseja alterar? ");
			System.out.println("1-  Email");  
			System.out.println("2-  Nome");                              
			System.out.println("3 - Cidade de Nascimento"); 
			System.out.println("4-  Cidade de Residencia");	
			System.out.println("5-  Data de Nascimento");
			System.out.println("9 - Cancelar");
			System.out.println("0 - Finalizar");
			
			int option = scanner.nextInt();
	
			switch (option) {	
			case 1:
				System.out.println("Digite o novo Email: ");
				newEmail = scanner.next();
				break;
			case 2:
				System.out.println("Digite o novo Nome: ");
				newName = scanner.next();
				break;
			case 3:
				System.out.println("Digite a nova Cidade de Nascimento: ");
				newBirthTown = scanner.next();
				break;
			case 4:
				System.out.println("Digite a nova Cidade de Residencia: ");
				newLivingTown = scanner.next();
				break;
			case 5:
				System.out.println("Digite a nova Data de Nascimento: ");
				newBirthDate = scanner.next();
				break;		
			case 9:
				exit = 1;
				Interface.init();
				break;
			case 0:
				exit = 1;
				break;
			default:
				System.out.println("Por favor, digite uma opcao valida!");
				break;
			}
		}
    	
        user.update(newName, newEmail, newBirthTown, newLivingTown, newBirthDate);
	}
    
    public void deleteUser() throws InterruptedException, ParseException, SQLException {
    	String email = getEmail();
    	User user = getUser(email);
		
    	user.delete();
	}

    public void deleteFriendship() throws InterruptedException, ParseException, SQLException {
    	String email = getEmail();
    	User user = getUser(email);
		
    	user.deleteFriendship();
	}
    
    public void createFriendship() throws InterruptedException, ParseException, SQLException {
    	String email = getEmail();
    	User user = getUser(email);
		
    	user.createFriendship();
	}
    
    public void getFriends() throws InterruptedException, ParseException, SQLException {
    	String email = getEmail();
    	User user = getUser(email);
		
    	user.getFriends();
	}
    
    public String getEmail(){
    	System.out.println("Qual o e-mail do usuario que voce deseja selecionar?");
    	Scanner scanner = new Scanner(System.in);
    	
    	String email = scanner.next();
    	
    	if(email != null)
    		return email;
    	else 
			System.out.println("\n E-mail está nulo, por favor digite um E-mail valido.");
			return null;
    }
    
    public User getUser(String email) throws SQLException, InterruptedException, ParseException{
   		Connection c = BDConexao.conectar();
    	PreparedStatement st = c.prepareStatement("SELECT * FROM pessoas WHERE email = ?"); //todo MAX 1
    	st.setString(1, email);
    	
    	ResultSet rs = st.executeQuery();
    	while (rs.next())
    	{
    		String name = rs.getString(1);
    		String birthTown = rs.getString(3);
    		String livingTown = rs.getString(4);
    		String birthDate = rs.getString(2);
    		
    		User user = new User(email);
    		user.update(name, email, birthTown, livingTown, birthDate);
    		return user;
    	}
    	rs.close();
    	st.close();
		return null;
    }
    
    	
    
}
