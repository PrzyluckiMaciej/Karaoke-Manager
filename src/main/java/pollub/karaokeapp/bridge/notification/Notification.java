package pollub.karaokeapp.bridge.notification;

import pollub.karaokeapp.model.user.User;

/**
 * Tydzień 3, Wzorzec Bridge 4
 * Abstrakcja - typ powiadomienia
 */
public abstract class Notification {

    protected NotificationChannel channel;

    public Notification(NotificationChannel channel) {
        this.channel = channel;
    }

    public abstract void send(String message, User recipient);
    public abstract String getNotificationType();
}
// Koniec, Tydzień 3, Wzorzec Bridge 4