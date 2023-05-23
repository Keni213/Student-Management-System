package roles;

import java.util.ArrayList;
import java.util.HashMap;

public class Admin extends User{

	//instance variable
    private String id;
    private String name;
    private String password;
    private String username;
	
	
	
	
	//Constructor
	public Admin(HashMap<String,String> info) {
		super(info);
		// TODO Auto-generated constructor stub
		this.id = (String) info.get("id");
		this.name = (String) info.get("name");
		this.username=(String) info.get("username");
		this.password = (String) info.get("password");
		
	}

//getter and setter


	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	// chech if any one checked in 
	
	 public static boolean checkLogIn(ArrayList<Admin> adminInfo,String usernameInput, String passwordInput) {
			
		   for(int i=0;i<adminInfo.size();i++) {
			  if(adminInfo.get(i).logIn(usernameInput, passwordInput)){
				return true;	
				
			  }
		    }
		   return false;
	    }
	 
	 //admin Login
	 

	  public boolean logIn(String usernameInput, String passwordInput) {
	        if((this.username.equals(usernameInput)) && this.password.equals(passwordInput)) {
		  return true;
			}else {
				return false;
			}
		  }

	
	
	
	
	

}
