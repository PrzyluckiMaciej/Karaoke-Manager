package pollub.karaokeapp.Week5.mediator;

/**
 * Tydzień 5, Wzorzec Mediator 4 (cd.)
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
                notificationsSent++;
                System.out.println("[NOTIFY] 📧 Wysłano powiadomienie o wyniku: " + data + " pkt (łącznie: " + notificationsSent + ")");
                break;
            case "SEND_WELCOME":
                notificationsSent++;
                System.out.println("[NOTIFY] 📱 Wysłano powitanie do: " + data + " (łącznie: " + notificationsSent + ")");
                break;
            default:
                System.out.println("[NOTIFY] Nieobsługiwane zdarzenie: " + event);
        }
    }

    public int getNotificationsSent() { return notificationsSent; }
}
// Koniec, Tydzień 5, Wzorzec Mediator 4 (cd.)