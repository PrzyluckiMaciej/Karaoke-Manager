package pollub.karaokeapp.Week3.bridge.notification;

import pollub.karaokeapp.model.user.User;

/**
 * Tydzień 3, Wzorzec Bridge 4
 * Konkretna implementacja - zaproszenie do występu
 */
public class PerformanceInviteNotification extends Notification {

    private String songTitle;
    private String time;

    public PerformanceInviteNotification(NotificationChannel channel, String songTitle, String time) {
        super(channel);
        this.songTitle = songTitle;
        this.time = time;
    }

    @Override
    public void send(String message, User recipient) {
        String fullMessage = buildInviteMessage(message);

        if (!channel.isAvailable()) {
            throw new ChannelUnavailableException(
                    "Kanał " + channel.getChannelName() + " niedostępny dla " + recipient.getNickname()
            );
        }

        channel.deliver(fullMessage, recipient);
    }

    private String buildInviteMessage(String message) {
        return "🎤 Zaproszenie do występu!\n" +
                "Piosenka: " + songTitle + "\n" +
                "Czas: " + time + "\n" +
                "Wiadomość: " + message;
    }

    @Override
    public String getNotificationType() {
        return "Zaproszenie do występu (kanał: " + channel.getChannelName() + ")";
    }
}
// Koniec, Tydzień 3, Wzorzec Bridge 4