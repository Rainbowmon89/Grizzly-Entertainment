/*
 * Monique Bennett 2004188
 * This class will be used to craete sql statements for equipments table
 */
package dbmodels;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dbfactories.DBConnectorFactory;

public class Equipment {
	
	//create connection  and statement variable
	private Connection dbconn = null;
	private Statement statement = null;
	
	//in default constructor, assign value to connection from factory class
	public Equipment()
	{
		this.dbconn= DBConnectorFactory.getDbConnection();
	}
	
	//write CRUD functions with SQL 
	public void create(int EquipmentID, String EquipmentName,String Availability,double RentalCost)
	{
		//create sql statement
		String sql = "INSERT INTO grizzlyentertainment.equipment(EquipmentID, EquipmentName, Availability,RentalCost)"+
					 "VALUES ('"+EquipmentID+"','"+EquipmentName+"','"+Availability+"','"+RentalCost+"')";
		//initialize statement
		try
		{
			statement = dbconn.createStatement();
			//store result
			int inserted = statement.executeUpdate(sql);
			if(inserted == 1)
			{
				JOptionPane.showMessageDialog(null, "SUCCESS:Equipment added to database", "Insert Success Message", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "FAILURE:Equipment could not be added to database", "Error Message", JOptionPane.INFORMATION_MESSAGE);
			}
		}catch(SQLException e)
		{
			//could possibly reopen gui to allow re-attempt
			System.out.println("Error occured while writing to database.");
			e.printStackTrace();
		}
	}
	
	
	public void update(int EquipmentID,String newStatus)
	{//not working
		//create sql statement
		String sql = "UPDATE grizzlyentertainment.equipment "
					+"SET Availability = '"+newStatus+"'"
				    +"WHERE EquipmentID = '"+EquipmentID+"';";
		//initialize statement
		try
		{
			statement = dbconn.createStatement();
			int updated = statement.executeUpdate(sql);
			if(updated == 1)
			{
				JOptionPane.showMessageDialog(null, "SUCCESS: Record updated", "Successful Update Message", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "FAILURE: Record could not be updated", "Failure Update Message", JOptionPane.INFORMATION_MESSAGE);
			}
		}catch(SQLException e)
		 {
			 e.printStackTrace();
		 }
	}
	public void readAll()
	{
		String sql = "SELECT EquipmentID, EquipmentName, Availability, RentalCost FROM grizzlyentertainment.equipment";
		try{
			statement = dbconn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next())
			{
				int eID= result.getInt("EquipmentID");
				String eName = result.getString("EquipmentName");
				String eA = result.getString("Availability");
				double eRCost = result.getDouble("RentalCost");
				
				System.out.println("EID: "+eID+"\nName: "+eName+"\nAvailability: "+eA+"\nRental Cost: $"+eRCost+"\n");
				
			}
		}catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null, "ERROR while retrieving equipment info", "Read Error Message", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public void delete(int EquipmentID)
	{
		//create sql
		String sql = "DELETE FROM grizzlyentertainment.equipment WHERE EquipmentID= "+EquipmentID;
		try
		{
			statement = dbconn.createStatement();
			int deleted = statement.executeUpdate(sql);
			if(deleted == 1)
			 {
				 JOptionPane.showMessageDialog(null, "SUCCESS: Record deleted successfully ", "Delete success message", JOptionPane.INFORMATION_MESSAGE);
			 }
			 else
			 {
				 JOptionPane.showMessageDialog(null, "FAILURE: Record could not be deleted successfully ", "Delete failure message", JOptionPane.ERROR_MESSAGE);
			 }
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
}
