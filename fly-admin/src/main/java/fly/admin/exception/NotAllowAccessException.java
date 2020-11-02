package fly.admin.exception;

public class NotAllowAccessException extends RuntimeException {
    public NotAllowAccessException(String message) {
        super(message);
    }
}
