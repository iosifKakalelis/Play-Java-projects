package controllers;

import models.*;
import play.*;
import play.api.data.Form;
import play.mvc.*;
import java.util.*;
import java.util.concurrent.Callable;

import views.html.*;
import javax.validation.*;
import java.sql.CallableStatement;
import static play.data.Form.form;
import static play.data.validation.Constraints.*;
import com.avaje.ebean.*;
import java.lang.reflect.Method;
import play.data.*;
import veltio.testing.*;
//import play.data.Form;
//import views.html.helper.form;

public class Application extends Controller implements Methods {
	private String stud_n;
	private String class_n;
	private play.data.Form<Student> studentForm = play.data.Form.form(Student.class);
	public static List<Student>  students;
	public Result init() throws Exception
	{
		/*try{
			inspectDB.action();	
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		*/
		// Method method1 = Application.class.getMethod("addAge", null);   
		//this.populate("student", "age", this,method1);
		//Student s1 =  findStudent("Iosif Kakalelis");
		// this.createAndPopulateStudents("junk", "VARCHAR(255)", "trash");
		// this.createAndPopulateStudent(s1,"grade", "VARCHAR(255)", "100");
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
   */
   // List<Student> students =  new ArrayList <Student>() ; 
   //List<StudentAndCourse>  students = StudentAndCourse.find.all();
    	// Student s6=	new Student("dadgdsgsgsgdghddhdhdh",23);	
    	// s6.save();
 //  List<Student> students = Student.find.where()
	      //  .eq("name", "Iosif Kakalelis").findList();
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
    	 if(studentForm.hasErrors()) {
             return badRequest(info.render("Error"));
         }
	    	
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
    
    public static Result delete(String name) {
   
        Student temp = findStudent(name);
    	temp.delete();
       return ok(info.render("Student deleted.."));
    }
    
    
    public static  Student findStudent(String name)
    {
    	List<Student> students =   
    		    Ebean.find(Student.class)  
    		        .where().ilike("name", name)  
    		        .findList();  
    	
    	return students.get(0);
    }
    
    public static  Course findCourse(String name)
    {
    	List<Course> courses =   
    		    Ebean.find(Course.class)  
    		        .where().ilike("name", name)  
    		        .findList();  
    	
    	return courses.get(0);
    }
    public  Result edit(String id) {
    	Student s = findStudent(id);
      //  play.data.Form<Student> 
    		studentForm = form(Student.class).fill(s);
        
        return ok(
            editForm.render(id, studentForm)
        );
    }
    
    public void assign(String id, String name) {
    	 
    	 Student s = this.findStudent(name);
    	 Course c =findCourse(id);
    	 s.addCourse(c);
    	 
    }
   
    public Result assignCourse()
    {
    	play.data.Form<Foo> classForm = play.data.Form.form(Foo.class);
    	//Map<String, String> toBind = new HashMap<String, String>();
    	//toBind.put("course", "student");
    	Foo aClass = classForm.bindFromRequest().get();
    	String course = aClass.getCourse();
    	System.out.println(course);
    	String student= aClass.getStudent();
    	System.out.println(student);
    	assign(course,student);
    	return ok(info.render("Course assigned"));
    	
    }
    public  Result update(String id) {
        studentForm = form(Student.class).bindFromRequest();
        if(studentForm.hasErrors()) {
            return badRequest(info.render("error"));
        }
        else
        {
        	  Student s1 =studentForm.bindFromRequest().get();
        
        	  Student s2 =  findStudent(s1.getName());
        	  s2.setName(s1.getName());
        	  s2.setEmail(s1.getEmail());
        	  s2.setAge(s1.getAge());
        	  s2.setPassword(s1.getPassword());
              s2.update();
              
              return ok(info.render("success"));
        	
        }
      
    }
    
    public void Test() {
    	 List<Student> students= this.findAllStudents();
		 for(Student stud:students)
		 {
			String sql = "UPDATE student SET field2='"+stud.getName()+","+stud.getAge()+"' WHERE name='"+stud.getName()+"'";
			 CallableSql cs = Ebean.createCallableSql(sql);
			 Ebean.execute(cs);
		 } 
		 

			this.addColumn("student","Nationality" , "VARCHAR(255)");
			Student s1 =  findStudent("Iosif Kakalelis");	
			this.populateStudent(s1, "Nationality", "Greek");
    }
    
    public static void addColumn(String table ,String name, String type) {
    	String sql = "ALTER TABLE "+table+" ADD COLUMN "+name+ " "+type;
    	CallableSql cs = Ebean.createCallableSql(sql);
		Ebean.execute(cs);
    }
    
    public static void populateStudents(String field, String something) {
    	  students= findAllStudents();
		 for(Student stud:students)
		 {
			String sql = "UPDATE student SET "+field+"='"+something+"' WHERE name='"+stud.getName()+"'";
			 CallableSql cs = Ebean.createCallableSql(sql);
			 Ebean.execute(cs);
		 } 
    }
    
    public static void populateStudent(Student stud, String field, String something) {
   
			String sql = "UPDATE student SET "+field+"='"+something+"' WHERE name='"+stud.getName()+"'";
			 CallableSql cs = Ebean.createCallableSql(sql);
			 Ebean.execute(cs);
		  
   }
    
    public  void populateCourses(String field, String something) {
   	 List<Course> courses= this.findAllCourses();
		 for(Course course:courses)
		 {
			String sql = "UPDATE course SET "+field+"='"+something+"' WHERE name='"+course.getName()+"'";
			 CallableSql cs = Ebean.createCallableSql(sql);
			 Ebean.execute(cs);
		 } 
   }
    
    public static void createAndPopulateStudents( String name, String type, String something)
    
    {
    	addColumn("student",name, type);
    	populateStudents(name, something);
    	
    }
 public static void createAndPopulateStudent(Student stud, String name, String type, String something)
    
    {
    	addColumn("student",name, type);
    	populateStudent(stud,name, something);
    	
    }
 public void  populate(String table , String field, Methods x, Method y) throws Exception{
	 
	Object var = y.invoke(x);
	 
	 if(table=="student"){
		 students= findAllStudents();
		 for(Student stud:students)
		 {
			String sql = "UPDATE student SET "+field+"='"+var+"' WHERE name='"+stud.getName()+"'";
			 CallableSql cs = Ebean.createCallableSql(sql);
			 Ebean.execute(cs);
		 } 	 
	 }
	 else if(table=="course") {
		List<Course> courses =  Course.find.all();
		 for(Course course:courses)
		 {
			 String sql = "UPDATE course SET "+field+"='"+var+"' WHERE name='"+course.getName()+"'";
			 CallableSql cs = Ebean.createCallableSql(sql);
			 Ebean.execute(cs);
		 } 	 
	 }
	 else if(table=="student_and_course")
	 {
		 List<StudentAndCourse> alist = StudentAndCourse.find.all();
		 for(StudentAndCourse list:alist){
			 String sql = "UPDATE student_and_course SET "+field+"='"+var+"' WHERE student_id='"+list.getStudent().getId()+"' AND course_id = '"+list.getCourse().getId()+"'";
			 CallableSql cs = Ebean.createCallableSql(sql);
			 Ebean.execute(cs);
		 }
		 
	 }
	 
	 else
	 {
		 System.out.println("Table doesn't exist");
	 }
	 
	 }

	@Override
	public int addAge() {
		 students= findAllStudents();
		 int sum=0;
		 for(Student stud:students)
		 {
			 sum+=Integer.parseInt(stud.getAge());
		 } 
		 return sum;
	}
	 
	 
	 
 
 
}
 

