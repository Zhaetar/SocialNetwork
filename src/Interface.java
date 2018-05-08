import java.text.ParseException;
import java.util.Scanner;

/**
 * @author Carol Heloisa
 * @author Matheus Patrick
 */
public class Interface {
	public static void init() throws InterruptedException, ParseException {
		System.out.println("\n Rede Social ");
		System.out.println("1-  Criação de usuário");  
		System.out.println("2-  Alteracao de dados de usuário");                              
		System.out.println("3 - Remocao de amizades"); 
		System.out.println("4-  Remocao de pessoas");	
		System.out.println("5-  Buscar todos os amigos de primeiro grau de uma pessoa");
		System.out.println("0 - Sair do Programa");

		Scanner scanner = new Scanner(System.in);
		int opcao = scanner.nextInt();
		SocialNetwork SN = new SocialNetwork();
		
		switch (opcao) {	
			case 1:
				SN.createUser();
				break;	
			case 2:
//	            Amizade a = new Amizade();
//				a.removerAmizade();			
//				break;	
			case 3:
//				Pessoa p = new Pessoa();                
//				p.removerPessoa();
//				break;
			case 4:	
//	           Pessoa p = new Pessoa();                
//				p.alterarPessoa();
//				break;		
			case 0:
				System.exit(0);
			default:
				System.out.println("Por favor, digite uma opcao valida!");
				init();
				break;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		try {
			init();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}