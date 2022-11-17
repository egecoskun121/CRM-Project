package egecoskun121.com.crm.exception;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException(String details) {
        super(details);
    }
}
