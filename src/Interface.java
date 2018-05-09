import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * @author Carol Heloisa
 * @author Matheus Patrick
 */
public class Interface {
	public static void init() throws InterruptedException, ParseException, SQLException {
		System.out.println("\n Rede Social ");
		System.out.println("1 - Criação de usuário");  
		System.out.println("2 - Cadastro de amizade");  
		System.out.println("3 - Alteracao de dados de usuário");                              
		System.out.println("4 - Remocao de amizades"); 
		System.out.println("5 - Remocao de pessoas");	
		System.out.println("6 - Buscar todos os amigos de primeiro grau de uma pessoa");
		System.out.println("0 - Sair do Programa");

		Scanner scanner = new Scanner(System.in);
		int opcao = scanner.nextInt();
		SocialNetwork SN = new SocialNetwork();
		//User U = new User();
		
		switch (opcao) {	
			case 1:
				SN.createUser();
				break;	
			case 2:
				SN.createFriendship();
				break;	
			case 3:
				SN.updateUser();
				break;	
			case 4:
				SN.deleteFriendship();
				break;
			case 5:
				SN.deleteUser();
				break;
			case 6:	
				SN.getFriends();
				break;			
			case 0:
				System.exit(0);
			default:
				System.out.println("Por favor, digite uma opcao valida!");
				init();
				break;
		}
	}

	public static void main(String[] args) throws InterruptedException, SQLException {
		try {
			init();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}