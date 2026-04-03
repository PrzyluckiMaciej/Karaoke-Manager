package pollub.karaokeapp.Week6.observer;

/**
 * Tydzień 6, Wzorzec Observer 1
 * Interfejs obserwatora - każdy obserwator reaguje na zmiany
 * w systemie karaoke
 */
public interface KaraokeObserver {
    void update(String event, Object data);
    String getObserverName();
}
// Koniec, Tydzień 6, Wzorzec Observer 1
