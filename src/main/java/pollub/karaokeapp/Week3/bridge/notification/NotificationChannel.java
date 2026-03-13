package pollub.karaokeapp.Week3.bridge.notification;

import pollub.karaokeapp.model.user.User;

/**
 * Tydzień 3, Wzorzec Bridge 4
 * Interfejs - kanał komunikacji
 */
public interface NotificationChannel {
    void deliver(String message, User user);
    boolean isAvailable();
    String getChannelName();
}
// Koniec, Tydzień 3, Wzorzec Bridge 4