package pollub.karaokeapp.Week6.observer;

import pollub.karaokeapp.model.user.User;
import java.util.List;

/**
 * Tydzień 6, Wzorzec Observer 4
 * Konkretny obserwator - Wysyła powiadomienia użytkownikom o zmianach piosenki
 */
public class NotificationObserver implements KaraokeObserver {

    private final String name = "Notification Manager";
    private final List<User> subscribers;

    public NotificationObserver(List<User> subscribers) {
        this.subscribers = subscribers;
    }

    @Override
    public void update(String eventType, Object data) {
        System.out.println("[OBSERVER-NOTIFICATION] Wysyłanie powiadomień o zdarzeniu: " + eventType);

        for (User user : subscribers) {
            String message = buildNotificationMessage(eventType, data);
            System.out.println("  Powiadomienie dla " + user.getNickname() + ": " + message);
        }
    }

    private String buildNotificationMessage(String eventType, Object data) {
        if (SongEventType.TITLE_CHANGED.equals(eventType)) {
            return "Tytuł piosenki został zmieniony: " + data;
        } else if (SongEventType.DIFFICULTY_CHANGED.equals(eventType)) {
            return "Poziom trudności piosenki został zmieniony: " + data;
        } else if (SongEventType.PERFORMANCE_FINISHED.equals(eventType)) {
            return "Nowe wykonanie zostało zakończone! " + data;
        }
        return "Zmiana w systemie: " + data;
    }

    @Override
    public String getObserverName() {
        return name;
    }
}
// Koniec, Tydzień 6, Wzorzec Observer 4
