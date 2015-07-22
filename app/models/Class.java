package models;

import controllers.*;
import java.util.*;
import javax.persistence.*;
import java.lang.Object.*;
//import org.openqa.selenium.lift.find.Finder;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Class extends Model{
	private String name;
	private static int version;
	private static List<StudentAndCourse> pointer;
	private static List<Class> archive;

	
	public Class(String name) {
		this.name = name;
		pointer= controllers.Application.findClass(name);
	    version=0;
	    archive = new ArrayList<Class>();
	}
	
	public Class(Class aClass)
	{
		
	}
	 
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static int getVersion() {
		return version;
	}

	public static void setVersion(int version) {
		Class.version = version;
	}

	public static List<StudentAndCourse> getPointer() {
		return pointer;
	}

	public static void setPointer(List<StudentAndCourse> pointer) {
		Class.pointer = pointer;
	}

	public void update() {
		List<StudentAndCourse> source= controllers.Application.findClass(name);
		if(source.equals(pointer))
		{
			System.out.println("Nothing to update");
		}
		else
		{
			//Class oldClass = (Class)this.clone();
			//archive.add(oldClass);
			//pointer=source;
			//version++;
			
		}
	}
	 	//private Object clone() throws CloneNotSupportedException {
	       // return super.clone();
	  //  }
	
}
