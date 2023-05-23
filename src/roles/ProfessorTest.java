package roles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import courses.Course;
import files.FileInfoReader;

class ProfessorTest {

	@BeforeEach
	void setUp() throws Exception {
		
		  //import all the student information 
		
		   ArrayList<Student>  studentInfo = FileInfoReader.readStudentInfo("studentInfo.txt");
		   

			//import all the professor information
				
		   ArrayList<Professor> professorInfo = FileInfoReader.readProfessorInfo("profInfo.txt");	
				
				
		   //import all the admin information 
		   
			
		   ArrayList<Admin> adminInfo = FileInfoReader.readAdminInfo("adminInfo.txt");		
				
				
				
		   //import all the course information 	
		   
		   ArrayList<Course> courseInfo = FileInfoReader.readCourseInfo("courseInfo.txt");	
	}

	@Test
	void testLogIn() {
		ArrayList<Professor> professorInfo = FileInfoReader.readProfessorInfo("profInfo.txt");
		Professor professor1=professorInfo.get(0);
		
		//test if input correct password and username
		assertTrue(professor1.logIn("Greenberg", "password590"));
		//test if input wrong password
		assertFalse(professor1.logIn("Greenberg", "password5911"));
		//test if input wrong username
		assertFalse(professor1.logIn("adminggg", "password590"));

		
		
        Professor professor2=professorInfo.get(1);
		
		//test if input correct password and username
		assertTrue(professor2.logIn("Smith", "password590"));
		//test if input wrong password
		assertFalse(professor2.logIn("Smith", "password5911"));
		//test if input wrong username
		assertFalse(professor2.logIn("adminggg", "password590"));
	}

	
	
	
	@Test
	void testCheckLogIn() {
		ArrayList<Professor> professorInfo = FileInfoReader.readProfessorInfo("profInfo.txt");
		
		Professor pro1 = professorInfo.get(0);
		//admin01 get login
		assertTrue(pro1.checkLogIn( professorInfo,"Greenberg", "password590"));
		
		Professor pro2=professorInfo.get(0);
		//no one get log in
		assertFalse(pro2.checkLogIn(professorInfo,"Green", "password5911"));
	}

	@Test
	void testCheckExistProfessor() {
		ArrayList<Professor> professorInfo = FileInfoReader.readProfessorInfo("profInfo.txt");
		
        //check no exist professor name
		assertFalse(Professor.checkExistProfessor("abc def", professorInfo));
		
		//check two exist professor
		assertTrue(Professor.checkExistProfessor("Clayton Greenberg", professorInfo));		
		assertTrue(Professor.checkExistProfessor("Stephen Lane", professorInfo));
		
	}

	@Test
	void testGetConflictCourseTime() {
		ArrayList<Course> courseInfo = FileInfoReader.readCourseInfo("courseInfo.txt");
		ArrayList<Professor> professorInfo = FileInfoReader.readProfessorInfo("profInfo.txt");
		
		Course newCourse=courseInfo.get(0);
		//no course conflict
		assertEquals(null,Professor.getConflictCourseTime(newCourse, "Stephen Lane", courseInfo, professorInfo));
	
	}

	@Test
	void testConvertToProfessor() {
		ArrayList<Professor> professorInfo = FileInfoReader.readProfessorInfo("profInfo.txt");
		
		//test, given a professor name ,we can get a professorClass with all his information contained
		assertEquals("Clayton Greenberg",Professor.convertToProfessor("Clayton Greenberg",professorInfo).getName());
		
		//given professor name, we can get the information after converting to professorClass
		assertEquals("025",Professor.convertToProfessor("Stephen Lane",professorInfo).getId());
	}

	@Test
	void testConvertIDToProfessor() {
		ArrayList<Professor> professorInfo = FileInfoReader.readProfessorInfo("profInfo.txt");
		
		//test, given a professor ID ,we can get a professorClass with all his information contained
		assertEquals("Clayton Greenberg",Professor.convertIDToProfessor("001" ,professorInfo).getName());
		
		//test, given a professor ID ,we can get a professorClass with all his information contained
		assertEquals("Swapneel Sheth",Professor.convertIDToProfessor("006" ,professorInfo).getName());
	}

	@Test
	void testCheckIDExist() {
		ArrayList<Professor> professorInfo = FileInfoReader.readProfessorInfo("profInfo.txt");
		
		//check if the professor contained
		assertTrue(Professor.checkIDExist("022", professorInfo));
		
		//this id is not exist
		assertFalse(Professor.checkIDExist("082", professorInfo));
		
	}

	@Test
	void testCheckUsernameExist() {
		ArrayList<Professor> professorInfo = FileInfoReader.readProfessorInfo("profInfo.txt");
		
		//check the username exist
		assertTrue(Professor.checkUsernameExist("Lee",professorInfo));
		
		//check the username not exist
		assertFalse(Professor.checkUsernameExist("Guo",professorInfo));
		
	}

}
