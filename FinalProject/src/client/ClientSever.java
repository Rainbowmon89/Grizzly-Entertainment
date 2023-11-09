package client;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import project.APConnectorproject;

public class ClientSever {
	private Connection dbConn = null;
	private Statement stmt;
	private ResultSet result;
	
	
	public ClientSever() {
		dbConn = APConnectorproject.getDatabaseConnection();
	}
	
	public void create(String id,String firstname,String lastname,
		 String phone, String email, String address) {
		 String insertSQL = "INSERT INTO final_project.customer (id, firstName, lastname, phone, email, address)"
				 + "VALUES('" + id+"','"+ firstname+"','"+ lastname+"','"+ phone+"',"
				 + "'"+email +"','"+ address +");";
		 try {
			stmt = dbConn.createStatement();
			int inserted = stmt.executeUpdate(insertSQL);
			if(inserted == 1) {
				JOptionPane.showMessageDialog(null, " Customer record inserted successfully",
						"Insertion status", JOptionPane.INFORMATION_MESSAGE, null);
				return;
			}else {
				JOptionPane.showMessageDialog(null, "record Insertion Failed",
						"Insertion Status", JOptionPane.ERROR_MESSAGE, null);
			}
			
			
		} catch (SQLException e) {
			System.err.println("SQL Exception:" + e.getMessage());
		}catch (Exception e) {
			System.err.println("unexpected error:" +e.getMessage());
		}
		
	}
	
	public void readAll() {
		String readAllSQL = "SELECT id, firstname, lastname,"
				+"FROM final_project.customer ";
		
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(readAllSQL);
			while (result.next()) {
				String id = result.getString("id");
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				String phone = result.getString("phone");
				String email = result.getString("email");
				String address = result.getString("address");
				System.out.println("ID: "+id +
						"\nName: "+ firstname + " "+lastname+ 
						"\nPhone: "+ phone+"\nEmail: " + email+ "\nAddress: "+address+"");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void update(String id, String lastname) {
		
	}
	public void delete(String id) {
		
	}
	

}





	
	

