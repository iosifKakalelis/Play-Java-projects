package veltio.testing;



public class Column {
	private Class<?> javaType;
	private int sqlType;
	private String[] constraints;
	private String name;
	private boolean isPk = false;
	
	
	public Column(Class<?> javaType, String name, boolean isPk) {
		super();
		this.javaType = javaType;
		this.name = name;
		this.isPk = isPk;
	}
	
	public Class<?> getJavaType() {
		return javaType;
	}
	public void setJavaType(Class<?> javaType) {
		this.javaType = javaType;
	}
	public int getSqlType() {
		return sqlType;
	}
	public void setSqlType(int sqlType) {
		this.sqlType = sqlType;
	}
	public String[] getConstraints() {
		return constraints;
	}
	public void setConstraints(String[] constraints) {
		this.constraints = constraints;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isPk() {
		return isPk;
	}
	public void setPk(boolean isPk) {
		this.isPk = isPk;
	}
	
	
	
	


	
	
	
	
	

}
