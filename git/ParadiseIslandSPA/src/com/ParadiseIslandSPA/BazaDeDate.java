package com.ParadiseIslandSPA;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


class BazaDeDate {
	
	static private DataSource dbRes;

    private BazaDeDate()
    {
    }
    
    static
    {
        try
        {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        dbRes = (DataSource) envCtx.lookup("jdbc/paospa");
        }
        catch (NamingException ex)
        {
            ex.printStackTrace();
        }
    }
    

	public static DetaliiProdus getProdusById(String idProdus) {
		
		String query ="SELECT * FROM produse WHERE idProdus=?";
		
		DetaliiProdus produs = null;
		String nume,id;
		double pret; 
		
		try(Connection connection = dbRes.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)){
			 	
				ps.setString(1, idProdus);
			 	
				try(ResultSet rs = ps.executeQuery()){
			 		
			 		rs.next();
			 		id = rs.getString(1);
		 			nume = rs.getString(2);
		 			pret = rs.getDouble(3);
		 			//tip = rs.getString(4);
			 		produs = new DetaliiProdus(id,nume,pret,0);
			 		return produs;
			 		
			 	} catch (SQLException ex) {
			             Logger.getLogger(BazaDeDate.class.getName()).log(Level.SEVERE, null, ex);
			        }
		} catch (SQLException ex)  {
			Logger.getLogger(BazaDeDate.class.getName()).log(Level.SEVERE, null, ex);
		}finally {
			return produs;
		}
		
			
		
		
		
	}
	
//	public static Client getClientByEmail(String email) {
//		
//		String query = "SELECT * FROM clienti WHERE email=?";
//		
//		Client client = null;
//		String nume,prenume;
//		int idClient;
//		boolean admin;
//		
//		
//		try(Connection connection = dbRes.getConnection();
//				PreparedStatement ps = connection.prepareStatement(query)){
//			 	
//				ps.setString(1, email);
//			 	
//				try(ResultSet rs = ps.executeQuery()){
//			 		
//			 		if(!rs.next()) {
//			 			idClient = rs.getInt(1);
//			 			nume = rs.getString(2);
//			 			prenume = rs.getString(3);
//			 			admin = rs.getBoolean(5);
//			 			client = new Client(idClient,nume,prenume,admin);
//			 		}
//			 		
//			 	} catch (SQLException ex) {
//			             Logger.getLogger(BazaDeDate.class.getName()).log(Level.SEVERE, null, ex);
//			        }
//		} catch (SQLException ex)  {
//			Logger.getLogger(BazaDeDate.class.getName()).log(Level.SEVERE, null, ex);
//		}
//		finally {
//			return client;
//		}
//	}
	
	public static boolean addNewClient(Client client, String password) {
		
		
		String query = "INSERT INTO clienti ( Nume, Prenume, Email, Password, Bilant,Privilege)" + "values(?,?,?,?,?,?)";
		
		try(Connection connection = dbRes.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)){
			 	
				
				ps.setString(1, client.getNume());
				ps.setString(2, client.getPrenume());
				ps.setString(3, client.getEmail());
				ps.setString(4, password);
				ps.setString(5, "0");
				ps.setString(6, "0");
			 	
				ps.executeUpdate();
			 	return true;
			 		
			 	
		} catch (SQLException ex)  {
			Logger.getLogger(BazaDeDate.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}
	
	public static boolean loginValidation (String email, String password)  {
		
		String query = "Select count(*) from clienti where Email = ? and Password = ?";
		
		try(Connection connection = dbRes.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)){
			 	
				ps.setString(1, email); 
				ps.setString(2, password);
				
				try(ResultSet rs = ps.executeQuery()){
					rs.next();
					if(rs.getInt(1) == 1) {
						return true;
					}
					else {
						return false;
					}
			 		
			 	} catch (SQLException ex) {
			 			System.out.println("aici!!!!");
			             Logger.getLogger(BazaDeDate.class.getName()).log(Level.SEVERE, null, ex);
			             return false;
			        }
		} catch (SQLException ex)  {
			System.out.println("aici2!!!!");
			Logger.getLogger(BazaDeDate.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
		
	}
	
	public static List<DetaliiProdus> getListaProduse(String tipProdus){
		
		String query = "Select * from produse where Tip = ?"; 
		
		List<DetaliiProdus> listaProduse= new ArrayList<>();
		String nume,id;
		double pret;
		
		try(Connection connection = dbRes.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)){
			 	
				ps.setString(1, tipProdus); 
				
				try(ResultSet rs = ps.executeQuery()){
					while(rs.next()) {
						id = rs.getString(1);
						nume = rs.getString(2);
			 			pret = rs.getDouble(3);
			 		//	tip = rs.getString(4);
				 		DetaliiProdus produs = new DetaliiProdus(id,nume,pret,0);
				 		listaProduse.add(produs);
						
					}
			 		
			 	} catch (SQLException ex) {
			             Logger.getLogger(BazaDeDate.class.getName()).log(Level.SEVERE, null, ex);	             
			        }
		} catch (SQLException ex)  {
			Logger.getLogger(BazaDeDate.class.getName()).log(Level.SEVERE, null, ex);
		}
		finally {
			return listaProduse;
		}
		
	}
	

//	public static void main(String args[]) {
//		System.out.println("Da");
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//
//			Connection con = DriverManager.getConnection("jdbc:mysql://sql2.freemysqlhosting.net:3306/sql2240093",
//					"sql2240093", "cC6!xF7*");
//			// here sonoo is the database name, root is the username and root is the
//			// password
//			Statement stmt = con.createStatement();
//
//			ResultSet rs = stmt.executeQuery("select * from clienti");
//
//			while (rs.next())
//				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
//
//			con.close();
//
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//
//	}
	
}