package testing;


import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import controllers.*;
import models.Student;


public class InspectDB implements Populator
{

	private static Connection connection;
	
	public InspectDB()
	{
		
	}

	public static void action() throws Exception 
	{
		
		/* Establish connection to the database */

	    String driverName = "com.mysql.jdbc.Driver";
	    Class.forName(driverName);

	    String serverName = "127.0.0.1";
	    String portNumber = "3306";
	    String mydatabase = serverName + ":" + portNumber + "/mydb";
	    String url = "jdbc:mysql://" + mydatabase;
	    String username = "root";
	    String password = "dyzz462xx";
		connection = DriverManager.getConnection(url, username, password);
		
		/* get my DB metadata */
	    DatabaseMetaData dbmd = connection.getMetaData();
	    ResultSet columns = dbmd.getColumns(null, null, "student", null);
	    
	    /* get my Models fields */
	    Field[] fields = null;
	    try {
    		Class<?> c = Class.forName("models.Student");
    		/* the following command accesses private members too */
    		fields= c.getDeclaredFields();
    	}
    	catch (ClassNotFoundException x) {
    	    x.printStackTrace();
    	}
	    
	    /* check that my Model matches the Schema */
	    /*
	    boolean b = true; 
	    b = CheckMapping.mappingIsCorrect(fields, columns);
	    System.out.println("Mapping is OK: "+ b);

	    String aString = "whatever";
	    Class<?> c = aString.getClass();
	    UpdateSchema.addColumn(connection,"student",c,"new_field");
	    */
	  
	    Report report = CheckDB.check(fields, columns);
	    /* This is where i should take action to fix the issues */
	    if(report.getCode() == 0) {
	    	System.out.println("Everything is ok :)");
	    }
	    else if(report.getCode() == 1) {
	    	System.out.println("Adding the new field to the database");
	    	PopulatorA a = new PopulatorA();
	    	// Updating schema and populating concurently
	    	UpdateSchema.addColumn(connection, "student", report.getColumnType(),report.columnName, a);
        	
	    	/*Populator populator = new Populator() {
			
			@Override
			public void populate() {
				List<Student>  students = Application.findAllStudents();
				
				 for(Student stud:students)
				 {
					stud.setTeam2("Aris"); 
					stud.update();
				 } 	 
				
			}
		};
		/*
		 
		 */
	   
	    }
	    else if(report.getCode() == 2) {
	    	System.out.println("Dropping the extra column from the database");
	    	UpdateSchema.dropColumn(connection, "student",report.columnName);
	    }
	  
	    /* More else ifs should be added here for other schema updates. */
	  
	}
	
	  public static void menu(Report report) throws SQLException 
	  {
	    	int choiceentry;
	    	Scanner in = new Scanner (System.in);
	    	
	    	do {
	    	    System.out.println("Enter \"1\", \"2\" or \"3\"");
	    	    System.out.println("1 - Create a new field");
	    	    System.out.println("2 - Create a new field and populate all students");
	    	    System.out.println("3 - Exit");
	    	    choiceentry = in.nextInt();
	    	
			

	    	    switch (choiceentry)
	    	    {
	    	        case 1:
	    	        	
	    	            break;
	    	        case 2: 
	    	        	String choice;
	    	        	System.out.println("Insert Value: ");
	    	        	choice = in.next();
	    	        	//UpdateSchema.addColumn(connection, "student", report.getColumnType(),report.columnName);
	    	        	controllers.Application.populateStudents(report.columnName, choice);
	    	        case 3: 
	    	            // .. exit program
	    	            break;
	    	        default:
	    	            System.out.println("Choice must be a value between 1 and 3.");
	    	    }   
	    	} while (choiceentry != 3);
	    }
	  
	  
	  

	@Override
	public void populate() {
		
		List<Student>  students = Application.findAllStudents();
		
		 for(Student stud:students)
		 {
			stud.setAge("30"); 
			stud.update();
		 } 	 
		
		
		
	}
	  
	  
	
}


