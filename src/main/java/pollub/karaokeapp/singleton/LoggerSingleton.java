package pollub.karaokeapp.singleton;

/**
 * Tydzień 2, Wzorzec Singleton 2
 * Lazy Initialization, czyli instancja singletona tworzona jest dopiero przy pierwszym wywołaniu.
 * Logger do zapisywania logów z różnych wydarzeń karaoke. Nie jest to podejście bezpieczne wątkowo,
 * Dwa wątki mogą jednocześnie utworzyć dwie instancje.
 */
public class LoggerSingleton {

    private static LoggerSingleton instance;

    private LoggerSingleton() {}

    public static LoggerSingleton getInstance() {
        if (instance == null) {
            instance = new LoggerSingleton();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}
// Koniec, Tydzień 2, Wzorzec Singleton 2