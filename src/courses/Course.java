package courses;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import roles.Professor;
import roles.Student;

public class Course {
	
	
	
	//instance variable
	    HashMap courseInfo;
		private String id;
		private String name;
		private String lecturer;
		private String days;
		private LocalTime startTime;
		private LocalTime endTime;
		private String capacity;
	       //also create a arrayList to store the enrolledList
		private ArrayList<Student> enrolledList;

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
	
	
	  //constructor
		public Course(HashMap courseMap) {		
		    this.courseInfo = courseMap;
			this.id=(String) courseMap.get("id");
			this.name=(String) courseMap.get("name");
			this.lecturer = (String) courseMap.get("lecturer");
			this.days = (String) courseMap.get("days");
			this.startTime =LocalTime.parse((CharSequence) courseMap.get("startTime"),formatter);
			this.endTime=LocalTime.parse( (CharSequence) courseMap.get("endTime"), formatter); 
			this.capacity = (String) courseMap.get("capacity");
			this.enrolledList= new ArrayList<Student>();
	
		}
		
	
	     public Course() {
	
	     }
	

	
	//setter/ getter
   /**
    * 
    * @return the course information
    */
	public HashMap<String, String> getCourseInfo() {
		return this.courseInfo;
	}

   /**
    * set the course information
    * @param info
    */
	public void setCourseInfo(HashMap info) {
		this.courseInfo = info;
	}
	
	/**
	 * get Course ID
	 * @return
	 */

	public String getId() {
	    return id;
       }
    
	/**
	 * set couse ID
	 * @param id
	 */

    public void setId(String id) {
	  this.id = id;  
      }
    /**
     * get course name
     * @return
     */
    public String getName() {
	     return name;
      }
 
    /**
     * set Course Name
     * @param name
     */
    public void setName(String name) {
	     this.name = name;
      }

/** 
 * get course lecturer
 * @return
 */
    public String getLecturer() {
	     return lecturer;
      }

    /**
     * set course Lecturer
     * @param lecturer
     */

    public void setLecturer(String lecturer) {
	     this.lecturer = lecturer;
      }


    /**
     * get course days
     * @return
     */
    public String getDays() {
	      return days;
      }
    /**
     * set course day
     * @param days
     */

    public void setDays(String days) {
	     this.days = days;
      }

/**
 * get course LocalTime
 * @return
 */
    public LocalTime getStartTime() {
	     return this.startTime;
      }

    /**
     * set course Start time
     * @param startTime
     */

    public void setStartTime(LocalTime startTime) {
	     this.startTime = startTime;
     }

    /**
     * set course end time
     * @return
     */

     public LocalTime getEndTime() {
	     return this.endTime;
      }

     /**
      * set endTime
      * @param endTime
      */

     public void setEndTime(LocalTime endTime) {
	      this.endTime = endTime;
      }

/**
 * get capacity
 * @return
 */
     public String getCapacity() {
	      return capacity;
        }
     
     /**
      * set Capacity
      * @param capacity
      */
  
       public void setCapacity(String capacity) {
	       this.capacity = capacity;
        }

/**
 * get enrolledList
 * @return
 */
        public ArrayList<Student> getEnrolledList() {
	        return enrolledList;
         }
   
        /**
         * set enrolled List
         * @param enrolledList
         */

        public void setEnrolledList(ArrayList<Student> enrolledList) {
	       this.enrolledList = enrolledList;
          }
      
	   
	   
	   
	   //add student name to Course enrolledList
	   
        /**
         * add student to the enrolledList for each course
         * @param student
         */
	   public void addNameToEnrollendList(Student student) {
		   this.enrolledList.add(student);
	   }
	   
	   //drop student name from courseEnrolledList
	   /**
	    * remove the student from enrolledList
	    * @param student
	    */
	   public void removeNameFromEnrollendList(Student student) {
		   this.enrolledList.remove(student);
	   }
	   
	   
	   
	   
	 //Methods
	   
	   /**
	    * print the course information. 
	    */
	   public String getStringCourseInfo(){
		   String info =null;
	       info = this.getId()+ "|"+
		          this.getName()+ ", " + 
	    		  this.startTime+"-"+ this.endTime + 
	    		  ", on " + this.days+
	    		  ", with course capacity:"+ this.capacity+
	    		  ", students:"+this.enrolledList.size()+
	    		  ", lecturer: "+ this.lecturer;
	    	return info;	         
	     }

	

	
	
	/**
	 * check if one course is conflict with other course
	 * @param otherCourse, checking the days, startTime, endTime
	 * @return true if the two course is conflict, false if the two course 
	 */
	
	public boolean isConflict(Course otherCourse) {
		//check from days if the days contain the same days, check the starTime and endTime
		for(int i = 0;i< ((String) this.getCourseInfo().get("days")).length();i++) {
			//if they have the same days
			if (((String) otherCourse.getCourseInfo().get("days")).contains(String.valueOf(((String) this.getCourseInfo().get("days")).charAt(i)))){
				
				//this selected course
				LocalTime thisStart =  this.getStartTime();
				LocalTime thisEnd = this.getEndTime();
				
				// the other course
				LocalTime otherStart = otherCourse.getStartTime();
				LocalTime otherEnd = otherCourse.endTime;
				
				//if the time overlap with each other
				//if this start >other end or this end<other start, return false,means no conflict	
				//if we can't meet any of these situation, the two courses has time conflict
				if((thisStart.isAfter(otherEnd)|| thisEnd.isBefore(otherStart))==false) {
					
					return true;
				}		
			}	
		}
		return false;	
     }
	
	/**
	 * get Course Name based on courseID
	 * @param courseID
	 * @param courseInfo
	 * @return
	 */
	
	 public static String getCourseName(String courseID, ArrayList<Course>courseInfo) {
	 	   
	 	   //initialize courseName
	 	   String courseName = null;
	 	   
	 	   //find the name from courseInfo arrayList
	 	   for(int i =0;i< courseInfo.size();i++) {
	 		 
	 		   if( courseInfo.get(i).getCourseInfo().get("id").equals(courseID)) {
	 			  courseName = (String) courseInfo.get(i).getCourseInfo().get("name");
	 			  break;
	 		   }    		   
	 	   }
	 	   return courseName;
		      }
	 
	 
	 
	 /**
	  * convert to the Course based on the id
	  * @param 
	  * @param courseInfo
	  * @return
	  */
	 public static Course convertToCourse(String id, ArrayList<Course>courseInfo) {	
	
		 
		 //check the information from courseInfo
		 for(int i=0;i< courseInfo.size();i++) {
			 //if the 
			 if(courseInfo.get(i).getId().equals(id)) {
				return courseInfo.get(i);
			 }
		 }	 
		 return null; 
	 }
	 
	 
	//add courses  
	 //get lecturer name
	 
	 
	 /**
	  * based on the professor ID, provide the professer name
	  * @param id
	  * @param professorInfo
	  * @return
	  */
	      public static String getLecturerName(String id,ArrayList<Professor> professorInfo) {
		         
	    	  String name = null;
	    	  
	    	  for(int i=0;i<professorInfo.size();i++) {
		        	if(professorInfo.get(i).getId().equals(id)) {
		        		name = professorInfo.get(i).getName();
		        	}
		          }
		          return name;
	         }

	      
	      
	
	      /**
	       * get a course Map base on provided information
	       * @param id
	       * @param name
	       * @param startTime
	       * @param endTime
	       * @param date
	       * @param capacity
	       * @param lecturer
	       * @return
	       */
	    public static HashMap<String, String> getNewCourseMap(String id, String name, String startTime, String endTime, String date, String capacity, String lecturer) { 
	        HashMap<String, String> newCourseMap = new HashMap<>();
	        newCourseMap.put("id",id);
	        newCourseMap.put("name",name);
	        newCourseMap.put("startTime",startTime);
	        newCourseMap.put("endTime",endTime);
	        newCourseMap.put("date",date);
	        newCourseMap.put("capacity",capacity);
	        newCourseMap.put("lecturer",lecturer);
	        newCourseMap.put("date",date);
  	 
	    	return newCourseMap;
	    	
	    }
	    
	  
	 
	  /**
	   * check if the course exist in the course info
	   * @param id provided the courseID
	   * @param info
	   * @return
	   */
	    
	  public static boolean checkExistCourse(String id, ArrayList<Course> info) {
		  for(int i=0;i<info.size();i++) {
			  if(info.get(i).getId().equals(id) ){
				  return true;
			  }
		  }
		  return false;
	  }
	 
	 
	    	
	
	
	
}
	
	
	
