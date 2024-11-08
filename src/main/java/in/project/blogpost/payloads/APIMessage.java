package in.project.blogpost.payloads;

public class APIMessage {
	
	private String message;
	private boolean success;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public APIMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public APIMessage(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}
	
	
	
	
}
