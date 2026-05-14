package pollub.karaokeapp.Week5.mediator;

/**
 * Tydzień 5, Wzorzec Mediator 3
 * Komponent powiadomień – uczestnik mediatora.
 * Wysyła powiadomienia e-mail/SMS po zdarzeniach sesji.
 */
public class NotificationComponent extends KaraokeColleague {

    private int notificationsSent = 0;

    public NotificationComponent() {
        super("NotificationComponent");
    }

    public void userJoined(String userName) {
        mediator.notify(this, "USER_JOINED", userName);
    }

    @Override
    public void receive(String event, Object data) {
        switch (event) {
            case "SEND_SCORE_UPDATE":
                sendScoreNotification(data);
                break;
            case "SEND_WELCOME":
                sendWelcomeNotification(data);
                break;
            default:
                System.out.println("[NOTIFY] Nieobsługiwane zdarzenie: " + event);
        }
    }

    private void sendScoreNotification(Object score) {
        notificationsSent++;
        System.out.println("[NOTIFY] 📧 Wysłano powiadomienie o wyniku: " + score + " pkt (łącznie: " + notificationsSent + ")");
    }

    private void sendWelcomeNotification(Object userName) {
        notificationsSent++;
        System.out.println("[NOTIFY] 📱 Wysłano powitanie do: " + userName + " (łącznie: " + notificationsSent + ")");
    }

    public int getNotificationsSent() { return notificationsSent; }
}
// Koniec, Tydzień 5, Wzorzec Mediator 3