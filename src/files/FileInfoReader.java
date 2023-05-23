package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import courses.Course;
import roles.Admin;
import roles.Professor;
import roles.Student;

public class FileInfoReader {
	
	/**
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	
	public static void readFileGetAllLines(String fileName) throws IOException{
		
	
		//create file object
		File file = new File(fileName);
		
		//create file reader
		FileReader fileReader = new FileReader(file);
		
		
		//create buffered reader to read all lines

		BufferedReader bufferedReader = new BufferedReader(fileReader); 
			
		//create String line to store each course.
		String line;
			
			
			//to read whole lines with BufferedReader, use readLine method
			while((line = bufferedReader.readLine()) != null) {
				
				System.out.print(line);
				System.out.print("\n");		
			}
	
	}
	
	
	
	/**
	 * get studentList
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException 
	 */
		
	public static ArrayList<Student> readStudentInfo(String filename) {
		
		//create list to store the student list
		ArrayList<Student> studentList = new ArrayList<Student>();
		
		//read information from file
		File file = new File(filename);
		
		//FileReader
		FileReader fileReader;
		
	try {	
		
	   fileReader = new FileReader(file);
		//BufferedRead
	    BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		//read each line from student file
		String line;
		
		//
		try {
			
			while((line = bufferedReader.readLine())!=null){
				
				String[] studentInfo = line.trim().split(";");
				HashMap<String, String> studentMap = new HashMap<String, String>();
				
					
				studentMap.put("id",studentInfo[0].trim());
				studentMap.put("name",studentInfo[1].trim());
				studentMap.put("username",studentInfo[2].trim());
				studentMap.put("password",studentInfo[3].trim());
				studentMap.put("courseGrade",studentInfo[4].trim());
				studentMap.put("enrolledCourses",null);
				
				//instantiate the student				
				Student student = new Student(studentMap);
				
				studentList.add(student);
		
				
			}
		} catch (IOException e) {
			
			System.out.println("Sorry, file not found for "+ file.getName());
		}
		
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	return studentList;
		
		
	}
	
	
	/**
	 * get adminList
	 * @param fileName
	 * @return admin List contain student and all the information
	 * 
	 * @throws FileNotFoundException 
	 */
		
	public static ArrayList<Admin> readAdminInfo(String filename) {
		
		//create list to store the student list
		ArrayList<Admin> adminList = new ArrayList<Admin>();
		
		//read information from file
		File file = new File(filename);
		
		
	try {	
		
		//FileReader
	     FileReader fileReader = new FileReader(file);
	     
		//BufferedRead
	    BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		//read each line from student file
		String line;
		
		//
		try {
			
			while((line = bufferedReader.readLine())!=null){
				
				String[] adminInfo = line.trim().split(";");
				HashMap<String, String> adminMap = new HashMap<String, String>();
					
				adminMap.put("id",adminInfo[0].trim());
				adminMap.put("name",adminInfo[1].trim());
				adminMap.put("username",adminInfo[2].trim());
				adminMap.put("password",adminInfo[3].trim());
				
				//instantiate the student				
				Admin admin = new Admin(adminMap);
				
				//add this admin to ArrayList admin
				adminList.add(admin);
		
				
			}
		} catch (IOException e) {
			
			System.out.println("Sorry, file not found for "+ file.getName());
		}
		
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	return adminList;
		
		
	}
	
	
	
	
	
	

	/**
	 * get studentList
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException 
	 */
		
	public static ArrayList<Professor> readProfessorInfo(String filename) {
		
		//create list to store the student list
		ArrayList<Professor> professorList = new ArrayList<Professor>();
		
		//read information from file
		File file = new File(filename);
		
		
		
	try {	
		
	    FileReader fileReader = new FileReader(file);
	   
		//BufferedRead
	    BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		//read each line from student file
		String line;
		
		//
		try {
			
			while((line = bufferedReader.readLine())!=null){
				
				String[] professorInfo = line.trim().split(";");
				HashMap<String, String> professorMap = new HashMap<String, String>();
					
				professorMap.put("id",professorInfo[1].trim());
				professorMap.put("name",professorInfo[0].trim());
				professorMap.put("username",professorInfo[2].trim());
				professorMap.put("password",professorInfo[3].trim());

				
				//instantiate the student				
				Professor professor = new Professor(professorMap);
				
				professorList.add(professor);
		
				
			}
		} catch (IOException e) {
			
			System.out.println("Sorry, file not found for "+ file.getName());
		}
		
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	return professorList;
		
		
	}
	
	
	
	
	

	/**
	 * get courseList
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException 
	 */
		
	public static ArrayList<Course> readCourseInfo(String filename) {
		
		//create list to store the student list
		ArrayList<Course> courseList = new ArrayList<Course>();
		
		//read information from file
		File file = new File(filename);
		
		
		
	try {	
		
	    FileReader fileReader = new FileReader(file);
	   
		//BufferedRead
	    BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		//read each line from student file
		String line;
		
		//
		try {
			
			while((line = bufferedReader.readLine())!=null){
				
				String[] courseInfo = line.trim().split(";");
				HashMap<String, String> courseMap  = new HashMap<String, String>();
					
				courseMap.put("id",courseInfo[0].trim());
				courseMap.put("name",courseInfo[1].trim());
				courseMap.put("lecturer", courseInfo[2].trim());
				courseMap.put("days",courseInfo[3].trim());
				courseMap.put("startTime",courseInfo[4].trim());
				courseMap.put("endTime",courseInfo[5].trim());
				courseMap.put("capacity",courseInfo[6].trim());
				
				//instantiate the student				
				Course course = new Course(courseMap);
				
				courseList.add(course);
		
				
			}
		} catch (IOException e) {
			
			System.out.println("Sorry, file not found for "+ file.getName());
		}
		
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	return courseList;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
