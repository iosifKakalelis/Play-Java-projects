package testing;

public class Report {
	/* code = 0 means everything is ok, code = 1 means a column must be added 
	 * code =2 means a column must be dropped etc. Later in time more codes 
	 * and fixes can be implemented.
	 */
	int code;
	/* This column will be either dropped or created depending on code */
	String columnName;
	/* This will only take value in case a column must be added */
	Class<?> columnType;
	
	public Report() {
		super();
	}
	public Report(int code) {
		super();
		this.code = code;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public Class<?> getColumnType() {
		return columnType;
	}
	public void setColumnType(Class<?> columnType) {
		this.columnType = columnType;
	}
	
}

	
	

