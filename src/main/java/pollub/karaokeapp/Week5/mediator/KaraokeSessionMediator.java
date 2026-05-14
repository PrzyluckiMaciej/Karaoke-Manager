package pollub.karaokeapp.Week5.mediator;

import pollub.karaokeapp.Week2.singleton.LoggerSingleton;

import java.util.HashMap;
import java.util.Map;

/**
 * Tydzień 5, Wzorzec Mediator
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
        dispatchEvent(event, data);
    }

    private void dispatchEvent(String event, Object data) {
        switch (event) {
            case "SONG_STARTED":
                handleSongStarted(data);
                break;
            case "SONG_FINISHED":
                handleSongFinished(data);
                break;
            case "SCORE_CALCULATED":
                handleScoreCalculated(data);
                break;
            case "USER_JOINED":
                handleUserJoined(data);
                break;
            default:
                logger.log("[MEDIATOR] Nieznane zdarzenie: " + event);
        }
    }

    private void handleSongStarted(Object data) {
        notifyColleague("display", "SHOW_LYRICS", data);
        notifyColleague("audio", "START_RECORDING", data);
    }

    private void handleSongFinished(Object data) {
        notifyColleague("audio", "STOP_RECORDING", data);
        notifyColleague("scoring", "CALCULATE_SCORE", data);
    }

    private void handleScoreCalculated(Object data) {
        notifyColleague("display", "SHOW_SCORE", data);
        notifyColleague("notification", "SEND_SCORE_UPDATE", data);
    }

    private void handleUserJoined(Object data) {
        notifyColleague("display", "SHOW_WELCOME", data);
        notifyColleague("notification", "SEND_WELCOME", data);
    }

    private void notifyColleague(String role, String event, Object data) {
        KaraokeColleague colleague = colleagues.get(role);
        if (colleague == null) {
            throw new IllegalStateException(
                    "Brak zarejestrowanego komponentu: " + role + " dla zdarzenia: " + event
            );
        }
        colleague.receive(event, data);
    }
}
// Koniec, Tydzień 5, Wzorzec Mediator