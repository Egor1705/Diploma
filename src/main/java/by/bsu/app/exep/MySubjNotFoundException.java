package by.bsu.app.exep;

public class MySubjNotFoundException extends RuntimeException {
    public MySubjNotFoundException(String message) {
        super(message);
    }

    public MySubjNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
