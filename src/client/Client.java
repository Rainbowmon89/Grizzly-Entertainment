package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

	private int client_id;
	private int request_id;
	private String name;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	private Socket connection;
	
	
	//default constructor
	public Client()
	{
		this.createConnection();
		this.getStreams();
		this.closeConnection();
		
	}
	
	//creating connection to already created server
	public void createConnection()
	{
		try
		{
			connection = new Socket(InetAddress.getLocalHost(),8888);
		}catch(IOException e)
		{
			System.out.println("Failed to establish connection to server.");
			e.printStackTrace();
		}
		System.out.println("Connection made successfully.");
	}
	
	//getting streams for connection
	public void getStreams()
	{
		try
		{
			inputStream = new ObjectInputStream(connection.getInputStream());
			outputStream = new ObjectOutputStream(connection.getOutputStream());
		}
		catch(IOException e)
		{
			System.out.println("Falied to establish connection streams.");
			e.printStackTrace();
		}
		System.out.println("Streams were successfully added.");
	}
	//close connection
	public void closeConnection()
	{
		try
		{
			inputStream.close();
			outputStream.close();
			connection.close();
		}
		catch(IOException e)
		{
			System.out.println("Error occured while closing connection");
			e.printStackTrace();
		}
		System.out.println("Connection closed successfully.");
	}
	
	//receive response from server
	public void receiveResponse()
	{
		Boolean flag = false;
		try
		{
			 flag = (Boolean)inputStream.readObject();
		}
		catch(ClassCastException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		System.out.println("Flag is: "+flag);
	}
}
