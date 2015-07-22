package models;
import java.util.*;
import javax.persistence.*;
import java.lang.Object.*;
//import org.openqa.selenium.lift.find.Finder;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;
@Entity
public class Course extends Model{
	@Constraints.Required	
	@Id
	@GeneratedValue	
	public Long id;
	@Constraints.Required	
	private String name;

	@ManyToMany(mappedBy = "courses")
	private List<Student> students;
	//public List<Student> students = new ArrayList<Student>();
	public static Finder<Long,Course> find = new Finder(
			Long.class,Course.class);



	public Course() {


	}


	public Course(String name) {


		this.name = name;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public List<Student> getStudents() {
		return students;
	}


	public void addStudent(Student aStudent) {
		students.add(aStudent);
	}

	public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Course c: Course.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }

}
