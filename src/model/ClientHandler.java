package model;



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.LinkedList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * @author Erik
 * @version 2014-02-27
 */
public class ClientHandler extends Thread {
	
	private Socket			clientSocket;
	private Communication	cmnds;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	
	
	public ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
		cmnds = new Communication(this);
		start();
	}
	
	public void run()
	{
		System.out.println("Connection recieved");
		try
		{
			in = new ObjectInputStream(clientSocket.getInputStream());
			out = new ObjectOutputStream(clientSocket.getOutputStream());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
			handleRequests();
	}

	private void handleRequests() {
		LinkedList<Object> request = null;
		while(true)
		{
			try
			{
				request = (LinkedList<Object>) in.readObject();
			}
			catch(Exception e)
			{
				try
				{
					in.close();
					clientSocket.close();
				}
				catch(IOException e1)
				{
					e1.printStackTrace();
				}
				return;
			}
			cmnds.messageRecieved(request);
		}
	}

	public void send(Object listToSend) {
		try{
			out.writeObject(listToSend);
		} catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public void closeSocket() {
		try {
			clientSocket.close();
		} catch (IOException e1) {
			System.out.println("Could not close socket");
			e1.printStackTrace();
		}
	}
	/*
	 * 	@author Olof Spetz
	 * 	returns the IP:Port address from client
	 * 
	 */
	public String getAddress()
	{
		String ip =  clientSocket.getRemoteSocketAddress().toString();
		return ip;
	}
}

