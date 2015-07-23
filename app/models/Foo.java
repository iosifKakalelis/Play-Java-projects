package models;

public class Foo{
	private String course;
	private String student;
	
	public Foo() {
	}
	
	public Foo(String student, String course) {
		this.student = student;
		this.course = course;
	}
	
	public String getCourse() {
		return course;
	}
	
	public void setCourse(String course) {
		this.course = course;
	}
	
	public String getStudent() {
		return student;
	}
	
	public void setStudent(String student) {
		this.student = student;
	}	
}
