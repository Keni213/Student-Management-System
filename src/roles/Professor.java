package roles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import courses.Course;

public class Professor extends User {
    
	//instance variable
	
	private String id;
	private String name;
	private String username;
	private String password;

	private ArrayList<Course> givenCoursesList;
	
	
	
	//constructor
	public Professor(HashMap info) {
		super(info);
		this.id = (String) info.get("id"); 
		this.name = (String)info.get("name");
		this.username =(String)info.get("username");
		this.password = (String)info.get("password");
		this.givenCoursesList=new ArrayList<Course>();

	
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	public ArrayList<Course> getGivenCoursesList() {
		
		
		return givenCoursesList;
	}
	
	
	
	
	
	
	
	/**
     * add the courses which taught by this professor to the givenCousesList
     * @param courseInfo based on all the information
     */
	public void setGivenCoursesList(ArrayList<Course>courseInfo) {
		for(int i=0;i<courseInfo.size();i++) {
			
	    //check all the courses from courseInfo, if the professor's name is same with the course's lecturer
		if(this.getName().equals(courseInfo.get(i).getCourseInfo().get("lecturer"))) {
	
			//add this course to this givenCoursesLis 
         	this.givenCoursesList.add(courseInfo.get(i));
		}	
       }
	}
                              
       
	
	//logIn
	/**
	 * log in based on input
	 * @param usernameInput input
	 * @param passwordInput input
	 * @return true if correct, return false if not matched
	 */
	
	  public boolean logIn(String usernameInput, String passwordInput) {
	        if((this.username.equals(usernameInput)) && this.password.equals(passwordInput)) {
		  return true;
			}else {
				return false;
			}
		  }
	  /**
	   * check if anyone checkin
	   * @param professorInfo based on the information provided
	   * @param usernameInput 
	   * @param passwordInput
	   * @return true if someone get login, return false if no one get login
	   */
	  
	   public static boolean checkLogIn(ArrayList<Professor> professorInfo,String usernameInput, String passwordInput) {
			
		   for(int i=0;i<professorInfo.size();i++) {
			  if(professorInfo.get(i).logIn(usernameInput, passwordInput)){
				return true;	
				
			  }
		    }
		   return false;
	    }
		
	
	  
	  
	  //contain professor name
	   
	   /**
	    * check if this name is contain in the information
	    * @param professorName
	    * @param info
	    * @return true if we have this professor, 
	    * return false if we don't have this professor
	    */
	  
	  public static boolean checkExistProfessor(String professorName, ArrayList<Professor>info) {
		  for(int i=0;i<info.size();i++){
			  if(info.get(i).getName().equals(professorName)) {
				  return true;
			  }
		  }  
		  return false;
	  }
	  
	  
	  //Conflict Time
	  
	  public static Course getConflictCourseTime(Course newCourse, String lecturerName, ArrayList<Course>courseInfo, ArrayList<Professor> professorInfo) {
		
		  for(int i=0;i< Professor.convertToProfessor(lecturerName, professorInfo).getGivenCoursesList().size();i++) {
			 if( Professor.convertToProfessor(lecturerName, professorInfo).getGivenCoursesList().get(i).isConflict(newCourse)) {
				 return Professor.convertToProfessor(lecturerName, professorInfo).getGivenCoursesList().get(i);
		   }
		 
	      }
		  return null;
	  }
	  
	  
	  //professor name convert to Professor object
	  public static Professor convertToProfessor(String name,ArrayList<Professor> professorInfo) {
		  
		  for (int i =0; i<professorInfo.size();i++) {
			  if( professorInfo.get(i).getName().equals(name)){
				  return professorInfo.get(i);
			  }
		  }
		  return null;
	  }
	  
	  
	  //professor ID convert to Professor object
	  public static Professor convertIDToProfessor(String id ,ArrayList<Professor> professorInfo) {
		  
		  for (int i =0; i<professorInfo.size();i++) {
			  if( professorInfo.get(i).getId().equals(id)){
				  return professorInfo.get(i);
			  }
		  }
		  return null;
	  }
	  
	  
	  
	  
	 
	  
 //check ID exist
	  
	  public static boolean checkIDExist(String id,ArrayList<Professor> Info) {
		  for(int i=0;i<Info.size();i++) {
			  if(Info.get(i).getId().equals(id)) {
				  return true;
			  } 
		  }
		  return false;
	  }
	  
	  
	  
 //check Username exist
	  
	  public static boolean checkUsernameExist(String username,ArrayList<Professor> Info) {
		  for(int i=0;i<Info.size();i++) {
			  if(Info.get(i).getUsername().equals(username)) {
				  return true;
			  } 
		  }
		  return false;
	  }
	
	

}
