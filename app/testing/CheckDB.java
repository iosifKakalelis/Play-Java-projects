package testing;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public final class CheckDB {
	private CheckDB() {
		throw new AssertionError();
	}
	public static Report check(Field[] f,ResultSet c) throws SQLException {
		int fieldCount = f.length;
		int i = 0;
		List<String> columns = Collections.synchronizedList(new ArrayList<String>());
		List<String> fields = Collections.synchronizedList(new ArrayList<String>());
		while (c.next()) {
	    	columns.add(c.getString("COLUMN_NAME"));
	    	i++;
	    }
		int columnCount = i;
		c.beforeFirst();
		i = 0;
		while(i<fieldCount){
			String temp = f[i].toGenericString();
			String[] parts = temp.split("\\.");
			fields.add(parts[parts.length-1]);
			i++;
		}
		for(i=0;i<fields.size();i++){
			if(!columns.contains(fields.get(i))){
				System.out.println("field: "+fields.get(i)+" doesnt exist in the DB");
				Report report = new Report(1);
				report.setColumnName(fields.get(i));
				report.setColumnType(f[i].getType());
				return report;
			}
		}
		for(i=0;i<columnCount;i++) {
			if(!fields.contains(columns.get(i))){
				System.out.println("column: "+columns.get(i)+" doesnt exist in the Model!");
				Report report = new Report(2);
				report.setColumnName(columns.get(i));
				return report;
			}
		}
		/* If i manage to get here im sure everything is fine */
		Report report = new Report(0);
		return report;
	}
}
	