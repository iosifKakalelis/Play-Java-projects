package testing;



import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public final class UpdateSchema {
	private UpdateSchema() {
		throw new AssertionError();
	}
	public static void addColumn(Connection con,String table,Class<?> cls,String colName, Populator populator) throws SQLException {
		 if(!colName.contains("find"))
		 {
			  	Statement st = con.createStatement();
				String sqlType = SQLTypeMap.toSql(cls);
				String sql = "alter table "+table+" add "+colName+" "+sqlType;
				System.out.println(sql);
			    int n = st.executeUpdate(sql);
			    System.out.println("Query OK, " + n + " rows affected");
			    populator.populate();
		 }
		 
		 else{
			  System.out.println("Find is an Ebean field");
		 }
		

	}
	public static void dropColumn(Connection con,String table,String colName) throws SQLException {
		Statement st = con.createStatement();
		String sql = "alter table "+table+" drop "+colName;
		System.out.println(sql);
	    int n = st.executeUpdate(sql);
	    System.out.println("Query OK, " + n + " rows affected");
	}
}
