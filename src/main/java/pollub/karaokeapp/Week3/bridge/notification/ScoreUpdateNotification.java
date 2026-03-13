package pollub.karaokeapp.Week3.bridge.notification;

import pollub.karaokeapp.model.user.User;

/**
 * Tydzień 3, Wzorzec Bridge 4
 * Konkretna implementacja - aktualizacja wyniku
 */
public class ScoreUpdateNotification extends Notification {

    private int newScore;
    private int rankingPosition;

    public ScoreUpdateNotification(NotificationChannel channel, int newScore, int rankingPosition) {
        super(channel);
        this.newScore = newScore;
        this.rankingPosition = rankingPosition;
    }

    @Override
    public void send(String message, User recipient) {
        String fullMessage = "🏆 Aktualizacja wyniku!\n" +
                "Nowy wynik: " + newScore + " punktów\n" +
                "Pozycja w rankingu: " + rankingPosition + "\n" +
                "Komentarz: " + message;

        channel.deliver(fullMessage, recipient);
    }

    @Override
    public String getNotificationType() {
        return "Aktualizacja wyniku (kanał: " + channel.getChannelName() + ")";
    }
}
// Koniec, Tydzień 3, Wzorzec Bridge 4