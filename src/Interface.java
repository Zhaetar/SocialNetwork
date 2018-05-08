import java.text.ParseException;
import java.util.Scanner;

/**
 * @author Carol Heloisa
 * @author Matheus Patrick
 */
public class Interface {
	public static void init() throws InterruptedException, ParseException {
		System.out.println("\n Rede Social ");
		System.out.println("1-  Alteracao de Dados de Pessoa:");                              
		System.out.println("2 - Remocao de Amizades \n"); 
		System.out.println("3-  Remocao de Pessoas ");	
		System.out.println("4-  Buscar todos os amigos de primeiro grau uma pessoa");
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