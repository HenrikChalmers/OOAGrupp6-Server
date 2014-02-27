package model;


import java.io.Serializable;

/**
 * Has one specific user"s" all combined information
 * 
 * @author Henrik Johansson
 * @version 2014-02-26
 */
public class User implements Serializable{
	
	private String perNr,password,status,name;
	//private TEMPSchedule fullSchedule = null;				//TODO Simon 

	/**
	 * @param perNr
	 * @param password
	 * @param status
	 */
	public User(String perNr, String password, String status){
		this.perNr = perNr;
		this.password = password;
		this.status = status;
		
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
	
	
	/*								//TODO Simon L�gg till s� att man kan anv�nda schemat h�r i User. 
	 * 								//Hela User kommer att skickas s� du beh�ver endast f� det att fungera fr�n User klassen.
	 * 
	public TEMPSchedule getWeek(){		
		return fullSchedule;
	}
	
	public void setSchedule(TEMPSchedule fullSchedule){
		this.fullSchedule = fullSchedule;
	}
	
	public void setWeekSchedule(){
		
	}*/
}
