import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
   protected String birthDate;
   protected String birthTown;
   protected String livingTown;
  
   public User(String email) {
	 this.setEmail(email);
    }
   
   public void update(String name, String email, String birthTown, String livingTown, String birthDate ) throws InterruptedException, ParseException, SQLException {
	   String oldEmail = this.email;
	   if(email!="" && email!=null)
		   this.setEmail(email);
	   if(name!="" && name!=null)
		   this.setName(name);
	   if(birthTown!="" && birthTown!=null)
		   this.setBirthTown(birthTown);
	   if(livingTown!="" && livingTown!=null)
		   this.setLivingTown(livingTown);
	   if(birthDate!=null)
		   this.setBirthDate(birthDate);

	   Statement statement = null;
	   Connection connection = BDConexao.conectar();
		
	   try {
		   statement = connection.createStatement();
		   String query;
		   query = "UPDATE pessoas SET email = '" + this.email + "'," + "cidadeNatal ='" + this.birthTown + "',"
					+ "dataNasc ='" + this.birthDate + "'," + "cidadeAtual='"
					+ this.livingTown + "'," + "nome='"+ this.name+"' WHERE email = '" + oldEmail +"'";
		
		   statement.execute(query);
		   statement.close();
		   connection.close();
		   System.out.println("Pessoa alterada com sucesso!");
		} catch (Exception e) {
				System.out.println(e);
				System.out.println("\nErro ao alterar dados do usuario! Voltando ao menu..");
				TimeUnit.SECONDS.sleep(1);
				Interface.init();
		}
    }
   
   public void insert() throws InterruptedException, ParseException, SQLException {
	   Statement statement = null;
		Connection connection = BDConexao.conectar();

		try {
			statement = connection.createStatement();
			String query;
			query = "INSERT INTO pessoas VALUES('"+this.name+"','"+this.email+"','"
					+ ""+this.birthDate+"','"+this.birthTown+"','"+this.livingTown+"');";

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

   public void delete() throws InterruptedException, SQLException {
		Statement statement = null;
		Connection connection = BDConexao.conectar();
		try {
			statement = connection.createStatement();
			String query = "DELETE FROM pessoas WHERE " + "email = '" + this.email + "'";
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
   
   public void createFriendship() throws InterruptedException, ParseException, SQLException {
	   Scanner scanner = new Scanner(System.in);
	   System.out.println("Digite o email do amigo: ");
	   String friendEmail = scanner.next();
	   Statement statement = null;
	   Connection connection = BDConexao.conectar();
	   if(friendEmail == "" || friendEmail == this.email) {
		  System.out.println("Erro ao adicionar amigo!");
		  Interface.init();
	   }
	try {
		statement = connection.createStatement();
		String query;
		//Cria data atual para colocar na amizade
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now(); 
		query = "INSERT INTO amigos VALUES('"+this.email+"', '"+friendEmail+"',"+dtf.format(localDate)+");"; //to-do consertar a data
		statement.execute(query);
		statement.close();
		connection.close();
		System.out.println("Amigo adicionado com sucesso!");
		Interface.init();
	} catch (Exception e) {
		System.out.println(e);
		System.out.println("\nErro ao adicionar amigo! Voltando ao menu..");
			TimeUnit.SECONDS.sleep(1);
			Interface.init();
		}
   }
   
   public void deleteFriendship() throws InterruptedException, ParseException, SQLException {
	   Scanner scanner = new Scanner(System.in);
	   System.out.println("Digite o email do amigo a ser deletado: ");
	   String friendEmail = scanner.next();
	   Statement statement = null;
	   Connection connection = BDConexao.conectar();
	   if(friendEmail == "") {
		System.out.println("\n E-mail está nulo, por favor digite um E-mail valido.");
	   }
	try {
		statement = connection.createStatement();
		String query;
		query = "DELETE FROM amigos WHERE amigo1 = '"+this.email+"' AND amigo2 = '"+friendEmail+"');"; 
		statement.execute(query);
		statement.close();
		connection.close();
		System.out.println("Amigo deletado com sucesso!");
	} catch (Exception e) {
		System.out.println(e);
		System.out.println("\nErro ao deletar o amigo! Voltando ao menu..");
			TimeUnit.SECONDS.sleep(1);
			Interface.init();
		}
   }
   
   public void getFriends() throws InterruptedException, SQLException {
   		Statement stmt = null;
   		Connection c = BDConexao.conectar();
   		try {
   			stmt = c.createStatement();		
   			String query = "SELECT * "
		   					+ "FROM amigos "
		   					+ "WHERE amigo2 IN "
		   					+ "(SELECT amigo2 from amizades where amigo1 = "+this.email+") ";
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
   
   public String getBirthDate() {
       return birthDate;
   }
   
   public void setBirthDate(String birthDate) {
       this.birthDate = birthDate;
   }
   
}

