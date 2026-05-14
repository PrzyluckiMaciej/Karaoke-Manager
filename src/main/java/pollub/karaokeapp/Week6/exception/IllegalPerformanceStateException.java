package pollub.karaokeapp.Week6.exception;

/**
 * Wyjątek dla nieprawidłowych stanów wykonania
 */
public class IllegalPerformanceStateException extends RuntimeException {

    public IllegalPerformanceStateException(String message) {
        super(message);
    }
}