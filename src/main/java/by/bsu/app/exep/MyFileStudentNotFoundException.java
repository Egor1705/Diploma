package by.bsu.app.exep;

public class MyFileStudentNotFoundException extends RuntimeException {

	  public MyFileStudentNotFoundException(String message) {
	        super(message);
	    }

	    public MyFileStudentNotFoundException(String message, Throwable cause) {
	        super(message, cause);
	    }
	
}
