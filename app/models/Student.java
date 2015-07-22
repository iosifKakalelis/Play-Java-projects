package models;

import java.util.*;
import javax.persistence.*;
import java.lang.Object.*;
//import org.openqa.selenium.lift.find.Finder;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Student extends Model {
	
	@Id
	@GeneratedValue
	public Long id;
	
	@Constraints.Required
	private String name;
	
	@Constraints.Required
    protected String email;
    protected String password;
    public String field;
    protected String nationality;
    //protected String something;
    
	

    


	private String age;
	



	//public static Finder<Long,Student> find = new Finder(
	       // Long.class,Student.class);
	//@ManyToMany(mappedBy = "students")
	//public List<Course> courses = new ArrayList<Course>();
	@ManyToMany
	public List<Course> courses;

	public static Finder<Long,Student> find = new Finder<Long,Student>(
		        Long.class, Student.class
		    ); 
	public Student(String name, String age, String password) {
		this.name = name;
		this.age = age;
		this.password = password;
	}
	
	public Student(String name, String age) {
		this.name = name;
		this.age = age;
		
	}
	public Student() {
		
	}


	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public void addCourse(Course course) {
		
		StudentAndCourse aClass = new  StudentAndCourse();
		aClass.setStudent(this);
		aClass.setCourse(course);
		aClass.save();
		 
	
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}


	private List<Course> getCourses() {
		return courses;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	
	public String toString()
	{
		 return "Name:  " + this.getName()+ 
	                "\n Email: " + this.getEmail() +  "\n Age:  "
	                 + this.getAge()+ " Nationality: "+this.getNationality() ;
	}
	 
	    }


