/*
 *  Monique Bennett 2004188
 *  This is the server class that will hold the responsibility of accessing the database.
 *  Clients and administration will make request to server which will connect to the server.
 */
package server;

//imported packages
import java.io.IOException;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.net.ServerSocket;
import java.net.Socket;

import dbmodels.Equipment;

public class Server {
	
//attributes of the server
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	private ServerSocket serverSocket;
	private Socket connection;

//creating default class for the server
	public Server()
	{
		
		this.createConnection();
		this.closeConnection();
		//waitForRequest();
	}
//creating connection to the database
	public void createConnection()
	{
		try
		{
			if(serverSocket!= null)
			{
				if(serverSocket.getLocalPort() == 8888)
				{
					serverSocket.close();
				}
			}
			serverSocket = new ServerSocket(8888,1);//try changing back to 8888
			
		}catch(IOException e)
		{
			System.out.println("Error while creating connection.");
			e.printStackTrace(); //exception handling
		}
		System.out.println("Connection created successfully.");
	}
//creating input and output streams for the connection to the database	
	public void getStreams()
	{
		try
		{
			inputStream = new ObjectInputStream(connection.getInputStream());
			outputStream = new ObjectOutputStream(connection.getOutputStream());
		}
		catch(ObjectStreamException e)
		{
			System.out.println("There was an error retrieving streams.");  //exception specific to failure while retrieving the object streams
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Streams established successfully.");
		
	}
//closing the connection	
	public void closeConnection()
	{
		try
		{
			//to close connection, first close its streams, then the connection itself
			inputStream.close();
			outputStream.close();
			connection.close();
		}
		catch(IOException e)
		{
			System.out.println("Failed to close connection");
			e.printStackTrace();
		}
		System.out.println("Connection closed successfully.");
	}
//wait for connection request
	public void waitForRequest()
	{
		String action ="";
		try
		{
			while(true)										//while it is true that a request is made
			{
				connection = serverSocket.accept(); 		// connection is assigned value of accepted request/ connection is made to db
				this.getStreams();
				
				do
				{
					try
					{
						action = (String)inputStream.readObject();
						if(action.equals("Add Equipment"))
						{
							Equipment equipment = (Equipment)inputStream.readObject();
							outputStream.writeObject(true);			
						}
						else if (action.equals("Request Equipment"))
						{
							
						}
					}catch(ClassNotFoundException e)
					{
						outputStream.writeObject(false);
					}catch(ClassCastException e)
					{
						outputStream.writeObject(false);
					}
				} while(!action.equals("Exit"));
				this.closeConnection();
			}
				
		}catch(EOFException e)
		{
			System.out.println("Client has terminated connections with the server.");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
}
