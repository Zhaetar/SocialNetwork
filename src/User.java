import java.sql.Connection;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class User { 
   protected String name;
   protected String email;
   protected Date birthDate;
   protected String birthTown;
   protected String livingTown;
  
   public User(String name, String email, String birthTown, String livingTown/*, Date birthDate*/) {
     this.name= name;
     this.email = email;
     this.birthTown = birthTown;
     this.livingTown = livingTown;
     //this.birthDate = birthDate;
    }

   public void insert() throws InterruptedException, ParseException {
	   Statement statement = null;
		Connection connection = BDConexao.conectar();

		try {
			statement = connection.createStatement();
			String query;
			query = "INSERT INTO pessoas VALUES("+this.name+","+this.email+","
					+ ""+this.birthDate+","+this.birthTown+");";

			statement.execute(query);
			statement.close();
			connection.close();
			System.out.println("Pessoa criada com sucesso!");
		} catch (Exception e) {
			System.out.println(e);
			System.out
					.println("\nErro ao inserir usuario! Voltando ao menu..");
			Interface.init();
		}
   }
   
   public void update() throws InterruptedException, ParseException {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite o novo email (deixe em branco caso não queira alterar): ");
		String newEmail = scanner.next();
		if(newEmail!="" && newEmail!=null)
			setEmail(newEmail);

		System.out.println("Digite o novo nome (deixe em branco caso não queira alterar): ");
		String newName = scanner.next();
		if(newName!="" && newName!=null)
			setName(newName);

		System.out.println("Digite a nova Cidade de Nascimento (deixe em branco caso não queira alterar): ");
		String newBirthTown = scanner.next();
		if(newBirthTown!="" && newBirthTown!=null)
			setBirthTown(newBirthTown);

		System.out.println("Digite a nova Cidade de Residencia (deixe em branco caso não queira alterar): ");
		String newLivingTown = scanner.next();
		if(newLivingTown!="" && newLivingTown!=null)
			setLivingTown(newLivingTown);

		System.out.println("Digite a nova Data de Nascimento (deixe em branco caso não queira alterar): ");
		String dataNasc = scanner.next();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        Date newBirthDate = dateFormat.parse(dataNasc);
		if(newBirthDate!=null)
			setBirthDate(newBirthDate);

        Statement statement = null;
		Connection connection = BDConexao.conectar();

		try {
			statement = connection.createStatement();
			String query;
			query = "UPDATE pessoa SET email = " + this.email + "," + "cidadeNasc ='" + this.birthTown + "',"
					+ "dataNasc ='" + this.birthDate + "'," + "cidadeResidencia="
					+ this.livingTown + "'," + "nome="+ this.name;

			statement.execute(query);
			statement.close();
			connection.close();
			System.out.println("Pessoa alterada com sucesso!");
		} catch (Exception e) {
			System.out.println(e);
			System.out
					.println("\nErro ao alterar dados do usuario! Voltando ao menu..");
			TimeUnit.SECONDS.sleep(1);
			Interface.init();
		}

	}

   public void delete() throws InterruptedException {
		Statement statement = null;
		Connection connection = BDConexao.conectar();
		try {
			statement = connection.createStatement();
			String query = "DELETE FROM pessoa WHERE " + "email = " + this.email + "";
			statement.execute(query);
			statement.close();
			connection.close();
			System.out.println("Pessoa removida com sucesso!");
		} catch (Exception e) {
			System.out.println(e);
			System.out
					.println("\nErro ao remover dados da Pessoa! Voltando ao menu..");
			TimeUnit.SECONDS.sleep(1);
			try {
				Interface.init();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
   	}
   
   public void buscarAmigosPrimeiroGrau(String email) throws InterruptedException {
   	Scanner scanner = new Scanner(System.in);
   		Statement stmt = null;
   		Connection c = BDConexao.conectar();
   		try {
   			stmt = c.createStatement();		
   			String query = "SELECT * "
   					+ "FROM amigos "
   					+ "WHERE idAmigo IN "
   					+ "(SELECT  idAmigo from pessoas where email +  = " + this.email + " ) "
   							+ "AND grauamizade = '1'";
   			stmt.execute(query);
   			stmt.close();
   			c.close();			
   		} catch (Exception e) {
   			System.out.println(e);
   			System.out.println("\n Erro ao listar os Amigos! Voltando ao menu..");
   			TimeUnit.SECONDS.sleep(1);
   			try {
   				Interface.init();
   			} catch (ParseException e1) {
   				e1.printStackTrace();
   			}
   		}
      }

   public String getName() {
       return name;
   }
   
   public void setName(String name) {
       this.name = name;
   }
   
   public String getEmail() {
       return email;
   }   
   
   public void setEmail(String email) {
       this.email = email;
   }
   
   public String getBirthTown() {
       return birthTown;
   }
   
   public void setBirthTown(String birthTown) {
       this.birthTown = birthTown;
   }
   
   public String getLivingTown() {
       return livingTown;
   }
   
   public void setLivingTown(String livingTown) {
       this.livingTown = livingTown;
   }
   
   public Date getBirthDate() {
       return birthDate;
   }
   
   public void setBirthDate(Date birthDate) {
       this.birthDate = birthDate;
   }
   
}

