package models;
import java.util.*;
import javax.persistence.*;
import java.lang.Object.*;
//import org.openqa.selenium.lift.find.Finder;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;
@Entity
public class StudentAndCourse extends Model {

  public  StudentAndCourse() {
        bridgeId = new BridgeId();      
    }

    @EmbeddedId
    protected BridgeId bridgeId;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne 
    @JoinColumn(name="course_id", insertable = false, updatable = false)
    private Course course;
    @Formats.DateTime(pattern="dd/MM/yyyy")
    private Date date;
    private int version=1;
    
    
   

    public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Student getStudent() {
        return student;
    }

    public void setStudent(Student aStudent) {
        student=aStudent;
        bridgeId.student_id = aStudent.getId();
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course aCourse){
        course=aCourse;
        bridgeId.course_id = aCourse.getId();
    }
    
    
    public static Finder<BridgeId ,StudentAndCourse> find = new Finder<BridgeId ,StudentAndCourse>(
	        BridgeId.class,StudentAndCourse.class
	    ); 
}