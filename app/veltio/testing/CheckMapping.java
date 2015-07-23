package veltio.testing;



import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** This is my old version, maintained only for copying snippets.
 *  Use CheckDB for testing.
 */
public final class CheckMapping {
	private CheckMapping() {
		throw new AssertionError();
	}
	public static boolean mappingIsCorrect(Field[] f,ResultSet c) throws SQLException, ClassNotFoundException {
		/* i need to upgrade this to return a Report object instead of bool */
		boolean b;
		b = countIsCorrect(f,c);
		if(!b){
			return b;
		}
		else {
			b = namesAreCorrect(f,c);
			if(!b) return b;
			else {
				b = typesAreCorrect(f,c);
				return b;
			}
		}
	}
	/* I may have to make these methods public during testing
	 * in the case i need to play with  them separately.
	 */
	private static boolean countIsCorrect(Field[] f,ResultSet c) throws SQLException, ClassNotFoundException {
		Report report;
		boolean b = true;
		int i = 0;
		while (c.next()) {
	    	i++;
	    }
		/* Always send the cursor  back to the start or bad things happen */
		c.beforeFirst();
		
		int columnCount = i;
		int fieldCount = f.length;
		if(fieldCount > columnCount) {
			System.out.println("There are more Model fields than DB columns");
			b = false; // this clause ends here for the simplest case
			/** Implement the following!! **/
			// Column col = findWhichFieldIsMissing();
			List<String> fields = Collections.synchronizedList(new ArrayList<String>());
			List<String> columns = Collections.synchronizedList(new ArrayList<String>());
			while (c.next()) {
		    	columns.add(c.getString("COLUMN_NAME"));
		    }
			c.beforeFirst();
			i = 0 ;
			while(i<f.length){
				String temp = f[i].toGenericString();
				String[] parts = temp.split("\\.");
				fields.add(parts[parts.length-1]);
				i++;
				b = true;
			}
			/* I am sure that 1 iteration will hit */
			for(i=0;i<fields.size();i++){
				if(!columns.contains(fields.get(i))){
					System.out.println("field: "+fields.get(i)+" doesnt exist in the DB");
					
					b = false;
					break;	
				}
			}
			// Report rep = new Report(false,true,false,col.getName(),col.getType());
			// return rep;
		}
		else if(fieldCount < columnCount) {
			System.out.println("There are less Model fields than DB columns");
			b = false;
		}
		// if(!b) { doSomethingAboutIt(); }
		return b;
	}
	
	private static boolean namesAreCorrect(Field[] f,ResultSet c) throws SQLException, ClassNotFoundException {
		boolean b = true;
		List<String> columns = Collections.synchronizedList(new ArrayList<String>());
		List<String> fields = Collections.synchronizedList(new ArrayList<String>());
		int i = 0;
		while (c.next()) {
	    	columns.add(c.getString("COLUMN_NAME"));
	    }
		c.beforeFirst();
	
		while(c.next()){
			String temp = f[i].toGenericString();
			String[] parts = temp.split("\\.");
			fields.add(parts[parts.length-1]);
			i++;
			b = true;
		}
		c.beforeFirst();
		
		for(i=0;i<fields.size();i++){
			if(!columns.contains(fields.get(i))){
				System.out.println("field: "+fields.get(i)+" doesnt exist in the DB");
				// doSomethingToFixIt();
				b = false;
				break;	
			}
		}
		// if(!b) { doSomethingAboutIt(); }
		return b;
	}
	
	private static boolean typesAreCorrect(Field[] f,ResultSet c) throws SQLException {
		boolean b = true;
		String modelType = null;
		String databaseType = null;

		int i = 0;
		while (c.next()) {
	    	int dataType = c.getInt("DATA_TYPE");
	        databaseType = SQLTypeMap.toClass( dataType ).toString();
	        modelType = f[i].getType().toString();
	        if(!modelType.equals(databaseType)) {
	        	System.out.println("database type is " +databaseType
	        			+ " but model type is "+modelType );	        
	        	b = false;
	        	break;
	        }
	    	i++;
		}
		c.beforeFirst();
		// if(!b) { doSomethingAboutIt(); }
		return b;
	}

	

}
