/**
 * ClientNode keeps track of all current connections.
 * 
 * @author Henrik Johansson
 * @version 2013-02-07
 */

package model;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class ClientNode implements Observer {
	private static ClientNode nod = null;
	
	private HashMap<InetAddress, Communication> clientConnected;
	
	private Boolean recieveInited = false;
	private ServerSocket server;
	private final int SERVER_SOCKET = 4444;

	/**
	 * @param port
	 *            Porten
	 * 
	 */
	private ClientNode(int port) {

		try {
			server = new ServerSocket(SERVER_SOCKET);
			recieveInit();								//TODO Singleton alreadey need not recieveinit Booolean
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public static synchronized ClientNode getInstance(int port) {
		if (nod == null) {
			nod = new ClientNode(port);
		}
		return nod;
	}
	
	public void update(Observable o, Object arg){
		if(o instanceof CommRecieve && arg instanceof InetAddress){
			//Check if inetaddress is already in list
			//if not add new, otherwise do nothing
			
			if( !clientConnected.containsKey(arg) ){
				addConnection((InetAddress) arg);
			}
		}
		else if(o instanceof CommRecieve && arg instanceof String){
			//Recieved message from Inetaddress send to Communication part
			
			forwardMessage( ((CommRecieve) o).getInetAddress()  , (String)arg );
		}
	}
	
	private void recieveInit() {
		if(!recieveInited){
			recieveInited = true;
			
			 clientConnected = new HashMap<InetAddress, Communication>() ;
			
			CommRecieve recComm = new CommRecieve(server);
			recComm.addObserver(this);
			Thread recieve = new Thread( recComm );
			recieve.start();
		}
	}


	private void removeConnect() {

	}

	private void addConnection(InetAddress iaddr) {
		System.out.println("Add Connection");
		clientConnected.put(iaddr, new Communication(server) );
		new Thread( clientConnected.get(iaddr) );
		
		
	}
	
	private void forwardMessage(InetAddress iaddr, Object message){
		if(clientConnected.containsKey(iaddr) ){
			clientConnected.get(iaddr).messageRecieved(iaddr, (String) message);
			
		}
		else{
			System.out.println("HashMap did not contain InetAddress: " + iaddr);
		}
	}
	
	

}
