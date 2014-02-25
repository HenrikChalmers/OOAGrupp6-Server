package model;

import java.io.Serializable;

public class User implements Serializable{
	private String perNr,password,status,name;
	//private Schedule		//Todo fix so that the users have a scheme

	public User(String perNr, String password, String status){
		this.perNr = perNr;
		this.password = password;
		this.status = status;
		this.name = name;
		
		
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}
	
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPerNr(){
		return perNr;
	}
	
	/*public Schedule getSchedule(){
		return schedule;
	}
	public void setSchedule(Schedule schedule){
		this.schedule = schedule;
	}*/
}
