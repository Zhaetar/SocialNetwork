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
		System.out.println("Digite a Data de Nascimento (dd/MM/yyyy): ");
		String dataNasc = scanner.next();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        Date birthDate = dateFormat.parse(dataNasc);

        User user = new User(email);
        user.update(name, email, birthDate, birthTown, livingTown);
        user.insert();
		Interface.init();
	}
    
    public void updateUser() throws InterruptedException, ParseException {
    	String email = getEmail();
    	user = getUser(email);
		
		System.out.println("Digite o novo email (deixe em branco caso não queira alterar): ");
		String newEmail = scanner.next();

		System.out.println("Digite o novo nome (deixe em branco caso não queira alterar): ");
		String newName = scanner.next();
		
		System.out.println("Digite a nova Cidade de Nascimento (deixe em branco caso não queira alterar): ");
		String newBirthTown = scanner.next();
		
		System.out.println("Digite a nova Cidade de Residencia (deixe em branco caso não queira alterar): ");
		String newLivingTown = scanner.next();
		
		System.out.println("Digite a nova Data de Nascimento (deixe em branco caso não queira alterar): ");
		String dataNasc = scanner.next();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        Date newBirthDate = dateFormat.parse(dataNasc);
		
        user.update(newName, newEmail, newBirthTown, newLivingTown, newBirthDate);
	}
    
    public void deleteUser() throws InterruptedException, ParseException {
    	String email = getEmail();
    	user = getUser(email);
		
    	user.delete();
	}
    
    public String getEmail(){
    	System.out.println("Qual o e-mail do usuario que voce deseja selecionar?");
    	Scanner scanner = new Scanner(System.in);
    	int email = scanner.next();
    	
    	if(email != null)
    		return email;
    	else 
    		//error
    }
    
    public User getUser(String email){
    	PreparedStatement st = conn.prepareStatement("SELECT * FROM pessoas WHERE email = '?'"); //todo MAX 1
    	st.setInt(1, email);
    	
    	ResultSet rs = st.executeQuery();
    	while (rs.next())
    	{
    		String name = rs.getString(1);
    		String email = rs.getString(2);
    		String birthTown = rs.getString(3);
    		String livingTown = rs.getString(4);
    		String birthDate = rs.getDate(1);
    		
    		User user = new User(email);
    		user.update(name, email, birthTown, livingTown, birthDate);
    		return User;
    	}
    	rs.close();
    	st.close();
    }
    
    	
    
}
