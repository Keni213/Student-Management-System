package roles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class User {
	
	
	HashMap info;
	
	
	//constructor
	
		/**
		 * create an user without any parameter
		 * @param length of the ship
		 */
	
		public User(HashMap info) {
			this.info = info;			
		}; 
		
		public User() {
					
		}; 
		
		
		
	
	//Getter and Setter
	
	/**
	 * get the info
	 * @return
	 */
	
	public HashMap getInfo() {
			return info;
		}
   
	/**
	 * set the info
	 * @param info
	 */

	public void setInfo(HashMap info) {
			this.info = info;
		}
	
	
	

}
