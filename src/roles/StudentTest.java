package roles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import courses.Course;
import files.FileInfoReader;

class StudentTest {

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
		ArrayList<Student>  studentInfo = FileInfoReader.readStudentInfo("studentInfo.txt");
		Student student1=studentInfo.get(0);
		
		//test if input correct password and username
		assertTrue(student1.logIn("testStudent01", "password590"));
		//test if input wrong password
		assertFalse(student1.logIn("testStudent01", "password5911"));
	
	}

	@Test
	void testCheckLogIn() {
		ArrayList<Student>  studentInfo = FileInfoReader.readStudentInfo("studentInfo.txt");
		
		Student stu1 = studentInfo.get(0);
		//admin01 get login
		assertTrue(stu1.checkLogIn( studentInfo,"testStudent01", "password590"));
		
		Student stu2=studentInfo.get(1);
		//no one get log in
		assertFalse(stu2.checkLogIn(studentInfo,"testStudent01", "password5911"));
		
	}

	@Test
	void testIfAddCourse() {
		
		ArrayList<Course> courseInfo = FileInfoReader.readCourseInfo("courseInfo.txt");	
		ArrayList<Student>  studentInfo = FileInfoReader.readStudentInfo("studentInfo.txt");
		
		Student stu1 = studentInfo.get(0);
		
		//we can add a course when the enrolled list is empty
		assertTrue(stu1.ifAddCourse("CIT591",courseInfo));
		
		//we can add a course when the two course are not conflict
		assertTrue(stu1.ifAddCourse("CIT592",courseInfo));
		
		
		
	}

	@Test
	void testGetConflictName() {
		ArrayList<Course> courseInfo = FileInfoReader.readCourseInfo("courseInfo.txt");	
		ArrayList<Student>  studentInfo = FileInfoReader.readStudentInfo("studentInfo.txt");
		
		
		Course course1=courseInfo.get(0);
		Course course2=courseInfo.get(1);
		Course course42=courseInfo.get(42);
		
		Student student01 = studentInfo.get(0);
		student01.getEnrolledList().add(course1);
		student01.getEnrolledList().add(course2);
		
		
	    //get the conflict course 
		assertEquals("Introduction to Software Development",student01.getConflictName("CIS560",courseInfo));
	
	}

	@Test
	void testGetConflictID() {
		ArrayList<Course> courseInfo = FileInfoReader.readCourseInfo("courseInfo.txt");	
		ArrayList<Student>  studentInfo = FileInfoReader.readStudentInfo("studentInfo.txt");
		
		
		Course course1=courseInfo.get(0);
		Course course2=courseInfo.get(1);
		Course course42=courseInfo.get(42);
		
		Student student01 = studentInfo.get(0);
		student01.getEnrolledList().add(course1);
		student01.getEnrolledList().add(course2);
	
	    //get the conflict ID 
		assertEquals("CIT591",student01.getConflictID("CIS560",courseInfo));
	}

	@Test
	void testGetCouseID() {
		
		//get student grade- course ID
		ArrayList<Course> courseInfo = FileInfoReader.readCourseInfo("courseInfo.txt");	
		ArrayList<Student>  studentInfo = FileInfoReader.readStudentInfo("studentInfo.txt");
		
		Student stu1 = studentInfo.get(0);
		
		//test if we can get the first course id from grade list
		assertEquals("CIS191" , stu1.getCouseID().get(0));
		//test if we can get the second course id from grade list
		assertEquals("CIS320" , stu1.getCouseID().get(1));
		
		
	}

	@Test
	void testGetCouseGrade() {
		//get student grade- course ID
		ArrayList<Course> courseInfo = FileInfoReader.readCourseInfo("courseInfo.txt");	
		ArrayList<Student>  studentInfo = FileInfoReader.readStudentInfo("studentInfo.txt");
				
		Student stu1 = studentInfo.get(0);
		Student stu2 = studentInfo.get(0);
				
		//test if we can get the first grade from grade list
		assertEquals("A" , stu1.getCouseGrade().get(0));
		//test if we can get the second grade from grade list
		assertEquals("A" , stu1.getCouseGrade().get(1));
	}

	

	@Test
	void testCheckIDExist() {
		ArrayList<Student>  studentInfo = FileInfoReader.readStudentInfo("studentInfo.txt");
		//check exist
		assertTrue(Student.checkIDExist("001",studentInfo));
		//check not exist
		assertFalse(Student.checkIDExist("008",studentInfo));
	}

	@Test
	void testCheckUsernameExist() {
		ArrayList<Student>  studentInfo = FileInfoReader.readStudentInfo("studentInfo.txt");
		
		//check exist
		assertTrue(Student.checkUsernameExist("testStudent01",studentInfo));
		//check not exist
		assertFalse(Student.checkUsernameExist("jijig",studentInfo));
	}

}
