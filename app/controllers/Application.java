package controllers;

import models.*;
import play.*;
import play.api.data.Form;
import play.mvc.*;
import java.util.*;
import views.html.*;
import javax.validation.*;
import static play.data.validation.Constraints.*;
import com.avaje.ebean.*;
import play.data.*;
//import play.data.Form;
//import views.html.helper.form;

public class Application extends Controller {
	private String stud_n;
	private String class_n;
	private play.data.Form<Student> studentForm = play.data.Form.form(Student.class);
	
	public Result init()
	{
		 return ok(menu.render());

	}
   
	public Result index() {
    	//Form<Student> studentForm = Form.form(Student.class);
   /* Course c1 = new Course("Maths");	
    Course c2 = new Course("Physics");	
    c1.save();
    c2.save();
    Student s1=	new Student("Iosif Kakalelis","23");
    Student s2=	new Student("Teo Kakalelis","15");
    Student s3=	new Student("Manolis Stergiadis","23");
    Student s4=	new Student("John Snow","23");
    Student s5=	new Student("John Snowfgd gdgdgdag","23");
    s1.save();
    s2.save();
    s3.save();
    s4.save();
    //s5.save();
    s1.addCourse(c1);
    s2.addCourse(c1);
    s3.addCourse(c1);
    s4.addCourse(c1);
    s1.addCourse(c2);
    s3.addCourse(c2);
   // List<Student> students =  new ArrayList <Student>() ; 
   //List<StudentAndCourse>  students = StudentAndCourse.find.all();
    	// Student s6=	new Student("dadgdsgsgsgdghddhdhdh",23);	
    	// s6.save();
 //  List<Student> students = Student.find.where()
*/	      //  .eq("name", "Iosif Kakalelis").findList();
	//	Course c3 = new Course("Chemistry");
		//c3.save();
		//Student s1 =  findStudent("Iosif Kakalelis");	
		//s1.addCourse(c3);
		//s1.update();
		//Student s1 =  findStudent("Iosif Kakalelis");	
		//s1.setAge("21");
		//s1.update();
       stud_n="Iosif Kakalelis";
    	 class_n="Maths";
    	
    	List<StudentAndCourse> courses = findClass(class_n);
    	List<StudentAndCourse> students= findModules(stud_n);
    	
        return ok(main.render("It works",stud_n,class_n,students,courses));

    }
	public Result results()
	{
		List<List<StudentAndCourse>> listOfLists = new ArrayList<List<StudentAndCourse>>();
		List<Student> students= findAllStudents();
		for(Student stud:students) {
		//	List<StudentAndCourse> courses =findModules(stud);
			//listOfLists.add(courses);
			
		}
		return ok(search.render(students));
	}
    public Result show() {
    	return ok(home.render(studentForm));
     }
    public Result post() {

	    	Student student = studentForm.bindFromRequest().get();
	    	student.save();
	    return ok(info.render("Student added.."));
	}
    
   
    public static List<StudentAndCourse> findClass(String name)
    {
    	List<StudentAndCourse> courses =   
    		    Ebean.find(StudentAndCourse.class)  
    		    .fetch("course", new FetchConfig().query())  
    		        .where().ilike("course.name", name)  
    		        .findList();  
    	return courses;
    }
    
    public static  List<StudentAndCourse> findModules(String name)
    {
    	List<StudentAndCourse> students =   
    		    Ebean.find(StudentAndCourse.class)  
    		    .fetch("student", new FetchConfig().query())  
    		        .where().ilike("student.name", name)  
    		        .findList();  
    	return students;
    }
    
    public static  List<Course> findAllCourses()
    {
    	List<Course> courses =  Course.find.all();
    		    
    	return courses;
    }
    
    public static  List<Student> findAllStudents()
    {
    	List<Student> students =   Student.find.all();
    		    
    	return students;
    }
    
    public static  Student findStudent(String name)
    {
    	List<Student> students =   
    		    Ebean.find(Student.class)  
    		        .where().ilike("name", name)  
    		        .findList();  
    	
    	return students.get(0);
    }
}
