package roles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import courses.Course;

import files.FileInfoReader;

public class Student extends User {
	

	//instance variable
	private String id;
	private String name;
	private String username;
	private String password;
	private String grade;

    //also create a arrayList to store the enrolledList
	private ArrayList<Course> enrolledList;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
	


	
	
	//constructor
    /**
     * create new student based on student information and set all the information based on the hashmap  
     * @param studentMap
     */
	public Student(HashMap<String, String> studentMap) {
		super(studentMap);
		this.id=(String) studentMap.get("id");
		this.name=(String) studentMap.get("name");
		this.username= (String) studentMap.get("username");
		this.password=(String) studentMap.get("password");
		this.enrolledList=new ArrayList<Course>();
		this.grade = (String) studentMap.get("courseGrade");

	}

	public Student() {
	
	}

	//getter/setter

   /**
    * get student name
    * @return
    */
   public String getName() {
	return this.name;
   }

   /**
    * set name
    * @param name
    */
   public void setName(String name) {
	this.name = name;
   }

   /**
    * get id
    * @return
    */
   public String getId() {
	return id;
   }

   /**
    * set id
    * @param id
    */
   public void setId(String id) {
	 this.id = id;
   }
/**
 * get username
 * @return
 */
    public String getUsername() {
	  return username;
    }

    /**
     * set username
     * @param username
     */
    public void setUsername(String username) {
	  this.username = username;
    }
/**
 * get Password
 * @return
 */
    public String getPassword() {
	   return password;
     }
    
    /**
     * set Password
     * @param password
     */

    public void setPassword(String password) {
	  this.password=password;
    }
    /**
     * set EnrolledList
     * @param enrolledList
     */

    public void setEnrolledList(ArrayList<Course> enrolledList) {
	     this.enrolledList = enrolledList;
     }
	
   /**
    * get enrolled List
    * @return
    */

    public ArrayList<Course> getEnrolledList() {
	    return enrolledList;
     }
    
    public String getGrade() {
		return grade;
	}
    
    public void setGrade(String grade) {
  	  this.grade = grade;
      }
     
   
   //Method
   
   //print student information
   

	//get each student information, for printing information
    /**
     * can be used to print the student information in a student list
     * @return the id and student name
     */
     public String getEachStudentInfo() {   
	   String info = this.id+" "+ this.name;
	  return info;
   }
   
    
	
	
	//Student management system
	
	//  login student
	
	/**
	 * if the input password and and usrmane are same with this student password and username. the logIn is true
	 * @param usernameInput
	 * @param passwordInput
	 * @return
	 */
    public boolean logIn(String usernameInput, String passwordInput) {
        if((this.username.equals(usernameInput)) && this.password.equals(passwordInput)) {
	  return true;
		}else {
			return false;
		}
	  }

   
    /**
     * check if any student log in in the professorInfo
     * @param studentInfo
     * @param usernameInput
     * @param passwordInput
     * @return
     */
    public static boolean checkLogIn(ArrayList<Student> studentInfo,String usernameInput, String passwordInput) {
	
	   for(int i=0;i<studentInfo.size();i++) {
		  if(studentInfo.get(i).logIn(usernameInput, passwordInput)){
			return true;	
			
		  }
	    }
	   return false;
    }
	
     //check conflict  CourseFunction for each course  (isConflict(Course otherCourse))
       //add courses
    
    /**
     * check if we can add this selected course to the enrolled list
     * if these is no course in the list, add it
     * if there is other course in the list, check each one conflict
     * @param selectedCourse  which is input id
     * @param courseInfo check the information and convert it to a course in the courseInfo
     * @return true if it is ok to add
     */
    
    public boolean ifAddCourse(String selectedCourse,ArrayList<Course> courseInfo) {
    	//first, concert the given string to the course 
    	Course selecteCourse =  Course.convertToCourse(selectedCourse,courseInfo);
    	
    	//check conflict with other enrolled course
          	
        if(this.getEnrolledList().size()==0) {//if the getEnrolledList is empty, we can add this courses
    	       return true;
           }else {//if there are already some course here, we can check the time
    	 for(int i=0;i<this.getEnrolledList().size();i++) {
    		 //check each course in the enrolledList
    		 Course otherCourse =this.getEnrolledList().get(i);
    		//if two courses are not conflict, we can add this course 	 
    		 if(selecteCourse.isConflict(otherCourse)) {
    			return false;
    		 }
    	
          }
        }
        return true;
     }

     
     
     
     //get conflictName
    
    /**
     * get the conflict course name
     * @param selectedCourse
     * @param courseInfo
     * @return
     */
     
     public String getConflictName(String selectedCourse,ArrayList<Course> courseInfo) {
         
    	 String conflictName=null;
    	 
    	 //get the selectedCourse Information 
    	 Course selecteCourse =  Course.convertToCourse(selectedCourse,courseInfo);
    	 
    	 for(int i=0;i<this.getEnrolledList().size();i++) {
             
    		 //check the other course in the list
    		 Course otherCourse = this.getEnrolledList().get(i);
    		
    		//if the two course is conflict		 
    		 if(selecteCourse.isConflict(otherCourse)==true) {
    			 		 
    			 conflictName = (String) otherCourse.getCourseInfo().get("name");
    		     break;	
    		 }
    	 }
    	 return conflictName;
    	 
     }
     
     /**
      * get the conflict course id
      * @param selectedCourse
      * @param courseInfo
      * @return
      */  
      public String getConflictID(String selectedCourse,ArrayList<Course> courseInfo) {
             
        	 String conflictID = null;
        	 //get the selectedCourse Information
        	 Course selecteCourse =  Course.convertToCourse(selectedCourse,courseInfo);
        	 
        	 for(int i=0;i<this.getEnrolledList().size();i++) {
                 
        		 //check the other course in the list
        		 Course otherCourse =this.getEnrolledList().get(i);
        		 
        		//if the two course is conflict		 
        		 if(selecteCourse.isConflict(otherCourse)==true) {
        			 		 
        			 conflictID = (String) otherCourse.getCourseInfo().get("id");
        		 }
        	 }
        	 return conflictID;
        	 
         }
	
	
	
	    /**
	     * view enrolled List
	     */

         public void viewEnrolledList ( ){
 	        for (int i=0;i<this.enrolledList.size();i++) {
 	        System.out.println(this.enrolledList.get(i).getStringCourseInfo());
    	}
    }

	
	

	//method 5: view grade
	
    /**
     * based on the course grade,get the  ID list
     * @return
     */
    public ArrayList<String> getCouseID( ){

        // create a list to stroe the course name
        ArrayList<String> courseIDList = new ArrayList<String>();

         //"CIS191: A, CIS320: A"
        String courseGrade = (String) this.getInfo().get("courseGrade");

         //create Array for course grade [xxx:a , xxx:b    ]
        String[] courseArray = courseGrade.split(","); 		
         
        //for each course in the courseArray xxx:a
         for(int c=0;c< courseArray.length;c++) {
 	    //select name and put it in the nameList
         String[] courseSplit= courseArray[c].split(":");
         String id = courseSplit[0].trim();
         courseIDList .add(id);		
         }
         return courseIDList ;
         }
    
    /**
     * based on the course grade, get grade list
     * @return
     */
    
    public ArrayList<String> getCouseGrade( ){

        // create a list to stroe the course name
        ArrayList<String> courseGradeList = new ArrayList<String>();

         //"CIS191: A, CIS320: A"
        String courseGrade = (String) this.getInfo().get("courseGrade");

         //create Array for course grade [xxx:a , xxx:b    ]
        String[] courseArray = courseGrade.split(","); 		
         
        //for each course in the courseArray xxx:a
         for(int c=0;c< courseArray.length;c++) {
 	    //select name and put it in the nameList
         String[] courseSplit= courseArray[c].split(":");
         String id = courseSplit[1].trim();
         courseGradeList .add(id);		
         }
         return courseGradeList ;
         }
    

	
	
	/**
	 * get student Info
	 * @return
	 */
    
    public String getStringStudentInfo(){
		   String info =null;
	       info = this.getId()+ " "+
		          this.getName();
	    		 
	    	return info;	         
	     }
	
    
    
    
    /**
     * check if the student id exist
     * @param id
     * @param studentInfo
     * @return
     */
    public static boolean checkIDExist(String id,ArrayList<Student>studentInfo) {
    	for(int i=0;i<studentInfo.size();i++) {
    		if(studentInfo.get(i).getId().equals(id)){
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * check if the id exist 
     * @param id
     * @param studentInfo
     * @return
     */
    
    public static boolean checkUsernameExist(String username,ArrayList<Student>studentInfo) {
    	for(int i=0;i<studentInfo.size();i++) {
    		if(studentInfo.get(i).getUsername().equals(username)){
    			return true;
    		}
    	}
    	return false;
    }
	

}
