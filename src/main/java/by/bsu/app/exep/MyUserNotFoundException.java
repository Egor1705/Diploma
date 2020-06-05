package by.bsu.app.exep;

public class MyUserNotFoundException extends RuntimeException {

	 public MyUserNotFoundException(String message) {
	        super(message);
	    }

	    public MyUserNotFoundException(String message, Throwable cause) {
	        super(message, cause);
	    }
	
}
