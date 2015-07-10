package db.jdbc;

import play.db.*;

import java.sql.*;
import java.util.*;

public class SampleDB {
	
	public static Connection connect()
	{
		return DB.getConnection();
	}
	
	public static void disconnect (Connection connection) throws  Exception {
		connection.close();
	}

	public static void  createTestTable() throws Exception {
		Connection c = connect();
		try{
			c.createStatement().executeUpdate("create tabletest (id varchar(6), name varchar(10),version varchar(3)");
		}
		
			finally {
				disconnect(c);
			}
	}
	
	public static void insertData(String id , String name , String version) throws Exception {
		Connection c= connect();
		try{
			c.createStatement().executeUpdate("insert into test values('"+id+"','"+name+"','"+version+"')");
		}
		finally {
			disconnect(c);
		}
	}

	public static List<String> getData() throws Exception {
		Connection c = connect();
		try{
			ResultSet resultSet = c.createStatement().executeQuery("select * from test");
			List<String> values = new ArrayList<String>();
			while(resultSet.next())
			{
				values.add(resultSet.getString(1));
			}
			return values;
		}
		finally {
			disconnect(c);
		}
	}
}
