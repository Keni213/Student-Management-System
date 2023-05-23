package courses;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import files.FileInfoReader;
import roles.Admin;
import roles.Professor;
import roles.Student;

class CourseTest {

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
	void testIsConflict() {
		ArrayList<Course> courseInfo = FileInfoReader.readCourseInfo("courseInfo.txt");	
		Course course1 = courseInfo.get(0);
		Course course2 = courseInfo.get(1);
		Course course3 = courseInfo.get(2);
		Course course4 = courseInfo.get(25);
		
		//check if the two course are not conflict
		assertFalse(course1.isConflict(course2));
		assertFalse(course2.isConflict(course3));
		
		//check if the two course are conflict
		assertTrue(course4.isConflict(course2));
		
		
		
		
	}

	@Test
	void testGetCourseName() {
		ArrayList<Course> courseInfo = FileInfoReader.readCourseInfo("courseInfo.txt");
		
		//test if the method return the correct name
		assertEquals("Game Design Practicum",Course.getCourseName("CIS568", courseInfo));
		assertEquals("Big Data Analytics",Course.getCourseName("CIS545", courseInfo));
		
		
	}
	
	@Test
	void testConverToCourse() {
	ArrayList<Course> courseInfo = FileInfoReader.readCourseInfo("courseInfo.txt");	
	
	//check if the method can concert a courseID to a Course class. 
	//and we can get the course name
	 assertEquals("Big Data Analytics",Course.convertToCourse("CIS545", courseInfo).getName());
	 assertEquals("Introduction to Software Development",Course.convertToCourse("CIT591", courseInfo).getName());
        }
	
	
	@Test
	void testGetLecturerName() {
	
    ArrayList<Course> courseInfo = FileInfoReader.readCourseInfo("courseInfo.txt");
    ArrayList<Professor> professorInfo = FileInfoReader.readProfessorInfo("profInfo.txt");
	//test one correct name
	assertEquals("Clayton Greenberg",Course.getLecturerName("001", professorInfo));
	//test one incorrect name
	assertNotEquals("Clayton Greenberg",Course.getLecturerName("008", professorInfo));
	
	  }
	
	@Test
	
	void testCheckExistCourse() {
	ArrayList<Course> courseInfo = FileInfoReader.readCourseInfo("courseInfo.txt");
		
	assertTrue(Course.checkExistCourse("CIS545",courseInfo));
	assertFalse(Course.checkExistCourse("CIS999",courseInfo));
	
	  }
	
	
	

}
