package pollub.karaokeapp.Week6.exception;

/**
 * Wyjątek dla walidacji danych w systemie karaoke
 */
public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}