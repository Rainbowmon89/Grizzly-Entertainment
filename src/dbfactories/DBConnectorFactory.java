/*
 * This class will be used to create a databse connection for the server to connect to 
 */
package dbfactories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectorFactory {
	
	//create static variable of type connection and initialize it to null
	private static Connection dbconnection = null;
	
	//create public static constructor with return  type Connection
	public static Connection getDbConnection()
	{
		//first test if the connection is null,that is, if the connection is not yet established to the db
		if(dbconnection == null)
		{
			try
			{
				//create attributes for connection,url,username, password
				String url="jdbc:mysql://localhost:3307/grizzlyentertainment";
				String username="root";
				String password="usbw";
				
				//use driver manager to get connection
				dbconnection = DriverManager.getConnection(url,username,password);
			}catch(SQLException e)
			{
				System.out.println("Error while connecting to database.");
				e.printStackTrace();
			}	
		}
		return dbconnection;
	}
	
	//

}
