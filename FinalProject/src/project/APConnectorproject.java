package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;





public class APConnectorproject {
	
	private static  Connection dbConn = null;
	
	public static Connection getDatabaseConnection() {
		if(dbConn == null) {
			String url = "jdbc:mysql://localhost:3306/final_project";
			try {
				dbConn = DriverManager.getConnection(url, "root", "password");
				JOptionPane.showMessageDialog(null, "Connection established",
						"JDBC Connection Status", JOptionPane.INFORMATION_MESSAGE);
				
				//JOptionPane.showMessageDialog(null,"Connection Established","JDBC Connection status", JOptionPane.INFORMATION_MESSAGE)
			} catch (SQLException e) {
	 			System.err.println("SQL EXCEPION:" + e.getMessage());
			} catch (Exception e) {
				System.err.println("Unexpected Error:" + e.getMessage());
				
			}
		}
		
		
		return dbConn;

	}

}
