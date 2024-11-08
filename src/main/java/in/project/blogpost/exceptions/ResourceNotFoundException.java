package in.project.blogpost.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	
	private String resourceName;
	private String fieldName;
	private long fieldvalue;
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public long getFieldvalue() {
		return fieldvalue;
	}
	public void setFieldvalue(long fieldvalue) {
		this.fieldvalue = fieldvalue;
	}
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldvalue) {
		super(String.format("%s not found with %s: %d", resourceName, fieldName, fieldvalue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldvalue = fieldvalue;
	}
	
	

}
