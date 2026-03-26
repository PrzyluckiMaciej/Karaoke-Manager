package pollub.karaokeapp.Week5.mediator;

import pollub.karaokeapp.Week2.singleton.LoggerSingleton;

import java.util.HashMap;
import java.util.Map;

/**
 * Tydzień 5, Wzorzec Mediator 2
 * Konkretny mediator zarządzający sesją karaoke.
 * Koordynuje zdarzenia między: AudioComponent, ScoringComponent,
 * DisplayComponent i NotificationComponent.
 */
public class KaraokeSessionMediator implements KaraokeMediator {

    private final Map<String, KaraokeColleague> colleagues = new HashMap<>();
    private final LoggerSingleton logger = LoggerSingleton.getInstance();

    @Override
    public void registerColleague(String role, KaraokeColleague colleague) {
        colleagues.put(role, colleague);
        colleague.setMediator(this);
        logger.log("[MEDIATOR] Zarejestrowano: " + role + " (" + colleague.getName() + ")");
    }

    @Override
    public void notify(KaraokeColleague sender, String event, Object data) {
        logger.log("[MEDIATOR] Zdarzenie '" + event + "' od: " + sender.getName());

        switch (event) {
            case "SONG_STARTED":
                // Gdy piosenka startuje: wyświetl tekst i rozpocznij nagrywanie
                notifyColleague("display", "SHOW_LYRICS", data);
                notifyColleague("audio", "START_RECORDING", data);
                break;

            case "SONG_FINISHED":
                // Gdy piosenka kończy się: oblicz wynik i wyślij powiadomienie
                notifyColleague("audio", "STOP_RECORDING", data);
                notifyColleague("scoring", "CALCULATE_SCORE", data);
                break;

            case "SCORE_CALCULATED":
                // Gdy wynik jest gotowy: zaktualizuj wyświetlacz i powiadom użytkownika
                notifyColleague("display", "SHOW_SCORE", data);
                notifyColleague("notification", "SEND_SCORE_UPDATE", data);
                break;

            case "USER_JOINED":
                // Gdy użytkownik dołącza: powiadom wyświetlacz i system powiadomień
                notifyColleague("display", "SHOW_WELCOME", data);
                notifyColleague("notification", "SEND_WELCOME", data);
                break;

            default:
                logger.log("[MEDIATOR] Nieznane zdarzenie: " + event);
        }
    }

    private void notifyColleague(String role, String event, Object data) {
        KaraokeColleague colleague = colleagues.get(role);
        if (colleague != null) {
            colleague.receive(event, data);
        } else {
            logger.log("[MEDIATOR] ⚠ Brak zarejestrowanego komponentu: " + role);
        }
    }
}
// Koniec, Tydzień 5, Wzorzec Mediator 2