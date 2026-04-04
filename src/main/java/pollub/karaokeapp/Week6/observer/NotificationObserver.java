package pollub.karaokeapp.Week6.observer;

import pollub.karaokeapp.model.user.User;
import java.util.List;

/**
 * Tydzień 6, Wzorzec Observer 4
 * Konkretny obserwator - Wysyła powiadomienia użytkownikom o zmianach piosenki
 */
public class NotificationObserver implements KaraokeObserver {

    private String name = "Notification Manager";
    private List<User> subscribers;

    public NotificationObserver(List<User> subscribers) {
        this.subscribers = subscribers;
    }

    @Override
    public void update(String eventType, Object data) {
        System.out.println("[OBSERVER-NOTIFICATION] Wysyłanie powiadomień o zdarzeniu: " + eventType);
        for (User user : subscribers) {
            System.out.println("  Powiadomienie dla " + user.getNickname() + ": " + data);
        }
    }

    @Override
    public String getObserverName() {
        return name;
    }
}
// Koniec, Tydzień 6, Wzorzec Observer 4
