package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import courses.Course;
import files.FileInfoReader;
import roles.Admin;
import roles.Professor;
import roles.Student;

public class Controller {
	
	

public static void main(String[] args) {
		
		
   //import all the student information 
				
   ArrayList<Student>  studentInfo = FileInfoReader.readStudentInfo("studentInfo.txt");
   

	//import all the professor information
		
   ArrayList<Professor> professorInfo = FileInfoReader.readProfessorInfo("profInfo.txt");	
		
		
   //import all the admin information 
   
	
   ArrayList<Admin> adminInfo = FileInfoReader.readAdminInfo("adminInfo.txt");		
		
		
		
   //import all the course information 	
   
   ArrayList<Course> courseInfo = FileInfoReader.readCourseInfo("courseInfo.txt");		
	
		
    //Run the system
 while(true) {//start the system loop)
    	
    	System.out.println("--------------------------");
		System.out.println("Student Management System");
		System.out.println("--------------------------");
		System.out.println("1 --Login as a student");
		System.out.println("2 --Login as a professor");
		System.out.println("3 --Login as an admin");
		System.out.println("4 --Quit the system");
		
		
		//log in as a student
		System.out.println("Please enter your option, eg.'1");
		
		Scanner scanner = new Scanner(System.in);
			int role = scanner.nextInt();
		
//Whole system end 
			
		if(role==4) { //end the whole system loop if input 4
		  System.out.println("System closed");
			break;
		}
			
			
		
//if the user is an student, student loop
	     while(role== 1) {
		
		boolean stopRole1 =false;	    
			    
		System.out.println("Please enter your username,or type 'q' to quit");
		String usernameInput = scanner.next();
			  
		  //if input q, break and back to the last menu
		if(usernameInput.equals("q")) {//break the loop and back to last menu		    	 
						
			    	break;//break the loop
			    } 
			    
		//get the passoword	
		System.out.println("Please enter your password,or type 'q' to quit");
		String passwordInput = scanner.next();
			
			//if input q, break and back to the last menu
		  	    if(passwordInput.equals("q")) {//if q,quit the role 1 loop
		  			   break;
		  		   }
	    //
		  	    
		if(Student.checkLogIn(studentInfo,usernameInput,passwordInput)==false){
			System.out.println("Invalid username/password,please input again");
			continue;
			    	 
			     }  	    
		  	    
		  	    
		  	    
	//loop the student in the student information
	  for(int i =0;i<studentInfo.size();i++) {			
		if(studentInfo.get(i).logIn(usernameInput, passwordInput)){
				
				   //get student Name
		String studentName = (String) studentInfo.get(i).getName();
		
	       // confirm the user  and start the student system.		
		   while(true) {
			    System.out.println(" ");
			    System.out.println("-----------------------");
				System.out.println("Welcome, "+ studentName);
				System.out.println("-----------------------");
				System.out.println("1 -- View all courses ");
				System.out.println("2 -- Add courses to your list ");
				System.out.println("3 -- View enrolled course");
				System.out.println("4 -- Drop course");
				System.out.println("5 -- View Grades");
				System.out.println("6 -- Return to previous menu");
				System.out.println(" ");
				
				int option = scanner.nextInt();
				
			//if input 6, back to the previous menu
		         if(option ==6) {
		        	 stopRole1=true;
					break;
				     }
				

				// option 1, view all courses
		        if(option ==1) {//start the view course
					//create an iterator and desplay all the value for each course
					Iterator<Course> iterator = courseInfo.iterator();
					while(iterator.hasNext()) {
						System.out.println(iterator.next().getStringCourseInfo());
					}	
				}//end view course
				
				
				// option 2, Add courses to your list
	            if(option ==2) {//start the option 2
				
				while(true) {//start the add course loop
					
					System.out.println("Please select the course ID you want to add to your list, eg. 'CIT590'. ");
					System.out.println("Or enter 'q' to reuturn the previous menu. ");
				
					//input a course ID
				     String selectedCourseID = scanner.next();
				 
			    if (selectedCourseID.equals("q")){//if input is q,break the loop and back to the menu   
					 break;
				     }
				
			   
			    if ((studentInfo.get(i).ifAddCourse(selectedCourseID,courseInfo))==true) {
					//if there is no conflict with selected course
					//convert the enrolled Courses (As Course Class) and add to enrolledList ArrayList
				    
			    	studentInfo.get(i).getEnrolledList().add(Course.convertToCourse(selectedCourseID, courseInfo));
					
					
					System.out.println("Course Added successfully");
					System.out.println(" ");
					
					

				}else {//cannot add a course which has a time conflict with another added course.
					
					// get the conflict course ID
					String conflictID = studentInfo.get(i).getConflictID(selectedCourseID,courseInfo);
					
					//get the conflict course name
					String conflictName = studentInfo.get(i).getConflictName(selectedCourseID,courseInfo);
					
					System.out.println("the course you selected has time conflict with " + conflictID + " " + conflictName );
					
				      }
				  }//end the loop
	             }//end the option2
			
				// option 3, View Enrolled courses	
		      if(option == 3) {//star the option 3
					 System.out.println("the course in your list: ");
					 
					 Iterator<Course> iterator = studentInfo.get(i).getEnrolledList().iterator();
					 while(iterator.hasNext()) {
						//print the information for each course in the list
					    System.out.println(iterator.next().getStringCourseInfo());
					 }		 
				}//end the option 3
				 

				//option 4, drop courses from list4
		      
                 if(option == 4) {
                	 //print the course enrolled
                     System.out.println("the course in your list: ");
					 
					 Iterator<Course> iterator = studentInfo.get(i).getEnrolledList().iterator();
					 while(iterator.hasNext()) {
						//print the information for each course in the list
					    System.out.println(iterator.next().getStringCourseInfo());
					 }
					 
                	 System.out.print("Please enter the ID of the course you want to drop, eg. 'CIT591' ");
                	 
                	 //get the ID, use Scanner
 	 
                	 String dropID = scanner.next();
                	 
                	 //drop the class and update the enrolledlist;
                	 
                	 studentInfo.get(i).getEnrolledList().remove(Course.convertToCourse(dropID, courseInfo));
                	
                	 System.out.print("you have already drop this course. ");
	 
				}//end the option 3
               
                 
			//option 5, view the course grade
				 if(option == 5) { 
					 //get courseList
					 ArrayList<String> courseIDList = studentInfo.get(i).getCouseID();
					
					 
					 //get GradeList
					 ArrayList<String> courseGradeList = studentInfo.get(i).getCouseGrade();
					
					 //iterator each one 
					 for(int m=0;m<courseIDList.size();m++) {
						 String couresID = courseIDList.get(m);
						 String courseGrade = courseGradeList.get(m);
						 String courseName =  Course.convertToCourse(courseIDList.get(m), courseInfo).getName();
						 System.out.println(couresID + " " + courseName + " "+courseGrade);					 
					 }
					 
				  }//end the option 5 

			     }//end the student system loop
				
			  }
			}
	      if(stopRole1=true) {
		  break;
	  }
	    
	       }
	     //end the role 1 loop
		 
		 
	
	     
	     
	     
	     
				
		while(role==2){
			
			boolean stopRole2=false;
			
			System.out.println("Please enter your username,or type 'q' to quit");
			
			String usernameInput = scanner.next();
		//if input q, quit the loop
		if(usernameInput.equals("q")) {
				break;
			}
			
			
			System.out.println("Please enter your password,or type 'q' to quit");
			
			String passwordInput = scanner.next();
		//if input q, quit the loop
		if(passwordInput.equals("q")) {
				break;
			
			}
			
		//check if any professor log in 
			
			if(Professor.checkLogIn(professorInfo, usernameInput, passwordInput)==false) {
				System.out.print("Invalid username/password, please input again.");
				continue;
			}
			
			
			
		//loop the professor in the professorInfo and get the logIn professor. 				
			for (int i =0;i<professorInfo.size();i++) {
				 
				//if input the correct username and password
				if(professorInfo.get(i).logIn(usernameInput, passwordInput)){
					                    
					//get the professor Name
					String professorName = (String) professorInfo.get(i).getName();
	
		            // set professor givenEnrolled Lsit
					professorInfo.get(i).setGivenCoursesList(courseInfo);
					
			while(true) {
				    System.out.println(" ");
					System.out.println("Welcome, "+ professorName);
					System.out.println("1 -- View given courses ");
					System.out.println("2 -- View student list of the given course ");
					System.out.println("3 -- Return to the previous menu");
				
					
					int option = scanner.nextInt();
					
		    //if input 3, back to the previous menu
				if(option ==3) {
					   stopRole2=true;
						break;
					}
				
			   if(option ==1) {// option 1, view all courses
					    System.out.println("------------The Course List--------------");
						//iterator the Course in the given coursesList
						Iterator<Course> itcourses=professorInfo.get(i).getGivenCoursesList().iterator();
						
						while(itcourses.hasNext()) {
							//print the course
							System.out.println(itcourses.next().getStringCourseInfo());
							}
						}
					
					
					if(option==2) {//view student list of the given course
						
						//print all courses in the given list
						//iterator the Course in the given coursesLis
						System.out.println("-----------------------The Course List----------------------");
						Iterator<Course> itcourses=professorInfo.get(i).getGivenCoursesList().iterator();
						
						while(itcourses.hasNext()) {
							//print the course
							System.out.println(itcourses.next().getStringCourseInfo());
							System.out.println(" ");
							 }
						 
					
						 
						while(true) {
							
						
						System.out.println("Pease enter the course ID, eg 'CIT519' ");
						System.out.println("or type 'q' to quit. ");
						
						String givenCourse = scanner.next();
						
						
						if(givenCourse.equals("q")) {
							break;
						}
						//Pride the student information in this courses
						
						System.out.println("The student in in your course "+Course.convertToCourse(givenCourse, courseInfo).getName()+":");
						System.out.println(" ");
						
						Iterator itStudent = Course.convertToCourse(givenCourse, courseInfo).getEnrolledList().iterator();
						 
						while(itStudent.hasNext()) {
							
						System.out.println(((Student) itStudent).getStringStudentInfo());
					      } //end print loop
						}//end the course input loop 
				      }//end the option 2 loop
					}//end true loop
				  }//end the professor log in loop
			}//loop the professorInfo end
						
		  if(stopRole2=true) {
			  break;
		  }
		}//end the role 2 loop
		
		
		
		
		
		
		//role 3, log in as admin
		
		while(role== 3) {
			
			boolean stopRole3=false;
			
			//get username
            System.out.println("Please enter your username,or type 'q' to quit");
			
			String usernameInput = scanner.next();
			
			if(usernameInput.equals("q")){
				break;
			}
					
			//get password
			System.out.println("Please enter your password,or type 'q' to quit");
			
			String passwordInput = scanner.next();
			
			if(passwordInput.equals("q")){
				break;
			}
			
			//check if any admin login, if not, input again
			

			
			if(Admin.checkLogIn(adminInfo, usernameInput, passwordInput)==false) {
				System.out.print("Invalid username/password, please input again.");
				continue;
			}
			
			
			
			
		//loop all admin information	
	   for (int i =0;i<adminInfo.size();i++) {
				 
				//if input the correct username and password
				if(adminInfo.get(i).logIn(usernameInput, passwordInput)){
					                    
					//get the Admin Name
					String adminName = (String) adminInfo.get(i).getName();
	
		          
					
			   while(true) {
				    System.out.println(" ");
					System.out.println("Welcome, "+ adminName);
					System.out.println("1 -- View given courses ");
					System.out.println("2 -- Add new courses");
					System.out.println("3 -- Delete courses");
					System.out.println("4 -- Add new professer ");
					System.out.println("5 -- Delete professor ");
					System.out.println("6 -- Add new student");
					System.out.println("7 -- Delete student  ");
					System.out.println("8 -- Return to previous menue ");
					System.out.println(" ");
				
					
					int option = scanner.nextInt();
					
					//if input 6, back to the previous menu
				  if(option ==8) {
					  stopRole3=true;
						break;
				        }
						
				  if(option ==1) {//view given courses 
					    //create an iterator and desplay all the value for each course
						Iterator<Course> iterator = courseInfo.iterator();
						while(iterator.hasNext()) {
							System.out.println(iterator.next().getStringCourseInfo());
						  }
						continue;
				        }
							
			      if(option==2) {//add new Courses
			    	  //
			    	  while(true) {
			    		  
			    	  //input course ID
			    	  System.out.println("Please enter the course ID, or type'q' to end.");
			    	  String newCourseID = scanner.next();
			    	  
			    	  if(newCourseID.equals("q")){
			    		  break;
			    	  }
			    	  
			    	  if(Course.checkExistCourse(newCourseID,courseInfo)) {//this course has already exist
			    		  System.out.println(" the course already exist. ");
			    		  break;//break the loop and start input the course ID
			    	  }
			    	  
			    	  if(Course.checkExistCourse(newCourseID,courseInfo)==false) {//if the new course is not exixted in the course info. add it
			    		  
			    		  //input course name
			    		  System.out.println(" Please enter the course name, or type'q' to end.");
			    		  String newCourseName=scanner.next();
			    		  if(newCourseName.equals("q")) {
			    			  break;
			    		  }
			 
			    		  
			    		  //input course start time
			    		  System.out.println(" Please enter the course start time, or type'q' to end.  ");
			    		  String newStartTime = scanner.next();
			    		  if(newCourseName.equals("q")) {
			    			  break;
			    		  }
			 
			    		  
			    		  //input course end time
			    		  System.out.println(" Please enter the course end time, or type'q' to end.  ");
			    		  String newEndTime=scanner.next();
			    		  if(newEndTime.equals("q")) {
			    			  break;
			    		  }
			    		  //input course date
			    		  System.out.println(" Please enter the course date, or type'q' to end.  ");
			    		  String newDate = scanner.next();
			    		  if(newDate.equals("q")) {
			    			  break;
			    		  }
			    		  
			    		  //input course capacity
			    		  System.out.println(" Please enter the course capacity, or type'q' to end. ");
			    		  String newCapacity  = scanner.next();
			    		  if(newCapacity.equals("q")) {
			    			  break;
			    		  }
			    		  
			    		  
			    		  System.out.println(" Please enter the course lecturer's id, or type'q' to end. ");
			    		  String newLecturerID = scanner.next();
			    		  if(newLecturerID.equals("q")) {
			    			  break;
			    		  }
			    		  
			    		  
			    		  //transfer lecturer ID to lecturer name 
			    		  String newLecturerName = Course.getLecturerName(newLecturerID,professorInfo);
			    		  
			    		 
			    		  
			    		  //get the course HashMap
			    		  HashMap<String,String>  newCourseMap= Course.getNewCourseMap(newCourseID, newCourseName, newStartTime , newEndTime, newDate, newCapacity, newLecturerName);
			    		  //create a new Course instance
			    		  Course newCourse = new Course(newCourseMap);
			    		  //add this student to studentInfo
			    		  
			    		  //check it is the new professor, can't add the course
			    		  if(Professor.checkExistProfessor(newLecturerName, professorInfo)==false) {
			    			  System.out.println("non-exist professor, please add the professor first.");
			    			  
			    		  }else 
			    		   {

		    				  if (Professor.getConflictCourseTime(newCourse, newLecturerName, courseInfo,  professorInfo)!=null){
		    			             
		    					  System.out.println("The new added course has time conflict with course:");
		    			           
		    					  //get conflict courses
		    					  System.out.println(Professor.getConflictCourseTime(newCourse, newLecturerName, courseInfo,  professorInfo).getStringCourseInfo()); 
		        
		    			            }
		    				  else 
		    			            {
		    				    	//add this student to studentInfo
		    				    		  courseInfo.add(newCourse);
		    				    		  System.out.println("The new course added successfully.");
		    				    		  System.out.println(newCourse.getStringCourseInfo());
		    				    		  break;
		    			          	
		    			            }
			    		  }
			    			  
			    			  
			    		  }
			    	  if(true) {
		    	    	  continue;
		    	      }
			    	        		  
			    	  }
			    	  continue;
			    		  
			    	  }
			    	  
			    	  
			    	  
			      if(option==3) {
			    	  //delete course
			    	  while(true) {
			    		  
			    		  System.out.println("Please enter the course ID, or type'q' to end.");
				    	    String deleteCourseID = scanner.next();
			    		  //if enter q, break the loop,back to the last menu
				    	   if(deleteCourseID =="q") {
				    	    	break;
				    	    }
			    		   //get the Course based on the courseID
				    	   //remove it from courseInfo
				    	   courseInfo.remove(Course.convertToCourse(deleteCourseID, courseInfo));
				    	   break;
			    	  }
			    	  continue;
			      }
			      
			      
			      
			      if(option ==4) {//add new professor
			    	  
			    while(true)	{
			    	
			    	
			    			    	  
		    	    	//input the ID
			    	 System.out.println(" Please enter the professor's ID or type 'q' to quit.");
		    		 String professorID = scanner.next();
		    		 if(professorID.equals("q") ){
		    			  break;
		    		  }
		    		 
		    		 //if the ID already exist, continue the next loop
		    		 if(Professor.checkIDExist(professorID,professorInfo)) {
		    			  System.out.println("The ID already exist.");
		    			  continue;
		    		     }
		    		 	    		 
			         //input professor name
		    	      System.out.println(" Please enter the professor's name");
		    	      String professorName = scanner.next();
		    	      if(professorName.equals("q")) {//if input q, end the loop for add new professor 
		    	    	  break;     
		    	         }
		    	      	    	      
			    	  //input professor username
		    	      
				    	System.out.println(" Please enter the professor's username or type 'q' to quit.");
			    		String professorUsername = scanner.next();
			    		if(professorUsername.equals("q")){
			    			 break;
			    		  }
			    		  
			    		 if(Professor.checkUsernameExist(professorUsername,professorInfo)) {
			    			  
			    			  System.out.println("The username already is not available");
			    			  continue;//loop again to input the new one
			    		     }
			    		  		    	
			    		 //input password
			    		
			    	      System.out.println(" Please enter the professor's password");
			    	      String professorPassword = scanner.next();
			    	      if(professorPassword.equals("q")) {//if input q, end the loop for adding new professor 
			    	    	  break;     
			    	         }
		    	        
			    	      //store the information in the Map collection
			    	      HashMap<String,String> newProfessorMap = new HashMap<String,String>();
			    	      newProfessorMap.put("ID",professorID);
			    	      newProfessorMap.put("name", professorName);
			    	      newProfessorMap.put("username", professorUsername);
			    	      newProfessorMap.put("password", professorPassword);
			    	     
			    	      
			    	      //create a new professor
			    	      Professor newProfessor = new Professor(newProfessorMap);
			    	      newProfessor.setInfo(newProfessorMap);
			    	      newProfessor.setId(professorID);
			    	      newProfessor.setName(professorUsername);
			    	      newProfessor.setUsername(professorUsername);
			    	      newProfessor.setPassword(professorPassword);
			    	      //add to professorInfo
			    	      
			    	      professorInfo.add(newProfessor);
			    	      
			    	      System.out.println("Successfully add a new professor.");
			    	      
			    	      System.out.println(professorInfo.get(32).getInfo());
			    	      
			    	      break;
			    	     
			   	  
			            }//loop again
			           continue;
			      
			    	  }//end the option 4
			      
			      
			      if(option==5) {//Delete professor
			    	 while(true) { 
			    	  System.out.println(" Detele a professor from system, please input the the professor'id', or type 'q' to quict.");
			    	  String deleteProfessor = scanner.next();
			    	  if(deleteProfessor.equals("q")) {
			    		  break;
			    	  }
			    	  
			    	  if(Professor.checkIDExist(deleteProfessor,professorInfo)){
			    	     //DELETE professor based on the provided ID
			    		  professorInfo.remove( Professor.convertIDToProfessor(deleteProfessor, professorInfo));
			    		  System.out.println(" Successfully remove professor ");
			    		  break;
			    	  }else {
			    		  System.out.println("Invalid ID, no professor removed");
			    		  break;
			    	  }
			        }
			    	 //end the loop
			    	 continue;
			      }//end the option4
	
			    	
			      
			      
			      
			      if(option==6) {//add new student
			    	  
			    	  while(true) {//start the loop of adding new student
			    		
			    
			             System.out.println("please enter the student ID, or type'q' to quit ");
			    	     String newStudentID = scanner.next();
			    			  
			    	     if(newStudentID.equals("q")){
			    	       break;
			    	     }
			    			 //if the student ID exist, stop this loop
			    		 if(Student.checkIDExist(newStudentID, studentInfo)) {
			    				  continue;
			    			  }
			    		  
			    		  //student name
			    		  System.out.println("please enter the student name, or type'q' to quit ");
			    		  String newStudentName = scanner.next();
			    		  
			    		  if(newStudentName.equals("q")){// if q, quite. end the adding student loop
		    				  break;
		    			  }
			    		  
			    		  
			    		  //username
			    		  
			    		
			    		  System.out.println("please enter username, or type'q' to quit ");
			    		  String newStudentUsername = scanner.next();
			    			  
			    			  if(newStudentUsername.equals("q")) {
			    				  
			    				  break;
			    			  }
			    			  
			    			  if(Student.checkUsernameExist(newStudentUsername, studentInfo)) {
			    				  continue;
			    			  }
			    			  
			    	
			    		  //student password
			    		  System.out.println("please enter the student password, or type'q' to quit ");
			    		  String newStudentPassword= scanner.next();
			    		  
			    		  if(newStudentPassword.equals("q")) {// if q, quite. end the adding student loop
		    				  break;
		    			  }
			    		  
			    		  //Please enter ID of a course which this student already took, one in a time, type 'q' if you want to quit, type'n' to stop adding.
			    		  
			    		 
			    		 System.out.println("Please enter ID of a course which this student already took, one in a time, type 'q' if you want to quit, type'n' to stop adding.");
			    		 String inputCourseID = scanner.next();
			    			  
			    	          if(inputCourseID.equals("q")) {
			    				  break;
			    			  }
			    			  
			    			  if(inputCourseID.equals("n")) {
			    				  break;
			    		      }
			    		
			    		  //please enter the grade, eg.'A')
			    		  System.out.println("please enter the grade, eg.'A'");  
			    		  String newGrade=scanner.next();
			    		     
			    		  //create the grade string
			    		  String grade= inputCourseID + ":" + newGrade;
			    		  
			    		  
			    		  //successfully added the new student : 007 bob 
			    		  
			    		  HashMap<String,String> newStudentMap = new HashMap<>();
			    		  newStudentMap.put("name",newStudentName);
			    		  newStudentMap.put("id",newStudentID);
			    		  newStudentMap.put("username",newStudentUsername);
			    		  newStudentMap.put("password",newStudentPassword);
			    		  newStudentMap.put("grade",grade);
			    		  
			    		  //add student
			    		  Student newStudent = new Student(newStudentMap);
			    		  newStudent.setId(newStudentName);
			    		  newStudent.setName(newStudentUsername);
			    		  newStudent.setUsername(newStudentUsername);
			    		  newStudent.setPassword(newStudentPassword);
			    		  newStudent.setGrade(grade);
			    		  
			    		  
			    		  
			    		  
			    		  studentInfo.add(newStudent);
			    		 System.out.println("Add student successfully:" + newStudentID+ " " + newStudentName); 
			    		
			    		break;	
			    		 
			    		 
			    		  
			    	  }//while end
			    	  
			    	  
			    	  continue;
			    	  
			      }//end of add new student.option6
			      
			      
			 if(option==7) {//delete student
				 
				 while(true) { 
			    	  System.out.println(" Detele a student from system, please input the the student'id', or type 'q' to quict.");
			    	  String deleteStudentID = scanner.next();
			    	  if(deleteStudentID.equals("q")) {
			    		  break;
			    	  }
			    	  
			    	  if(Student.checkIDExist(deleteStudentID,studentInfo)){
			    	     //DELETE professor based on the provided ID
			    		  professorInfo.remove( Professor.convertIDToProfessor(deleteStudentID, professorInfo));
			    		  System.out.println(" Successfully remove Student ");
			    		  break;
			    	  }else {
			    		  
			    		  System.out.println(" Invalid ID, no student removed. ");
			    		  break;
			    	     }
			           }//end the loop	  
	 
				  continue;
			        }
			  
			      }//admin system end
		   }
				
	     }
		if(stopRole3=true) {
			  break;

      }
					
	}//the end of system loop

    }
			
	   		
	   }
	   }	

		
