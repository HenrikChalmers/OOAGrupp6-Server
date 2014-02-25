package model;

import java.io.Serializable;
import java.util.HashMap;

public class Users implements Serializable{
	private HashMap<String, User> usersList;
	
	public Users(){
		usersList = new HashMap<String, User>();
	}
	
	public void remove(String perNrIn){
		if( usersList.containsKey(perNrIn) ){
			usersList.remove(perNrIn);
		}
	}
	
	public void changePass(String perNrIn, String passwordIn){
		usersList.get(perNrIn).setPassword(passwordIn);
	}
	
	public void changeStatus(String perNrIn, String statusIn){
		usersList.get(perNrIn).setStatus(statusIn);
	}
	
	public void add(String perNrIn, String passwordIn, String statusIn){
		if(usersList.containsKey(perNrIn))
		usersList.put(perNrIn, new User(perNrIn,passwordIn,statusIn) );
	}
	

}
