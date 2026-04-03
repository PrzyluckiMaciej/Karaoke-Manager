package pollub.karaokeapp.Week6.observer;

/**
 * Tydzień 6, Wzorzec Observer 4
 * Konkretny obserwator - system analityki śledzi zmiany w piosenkach
 */
public class AnalyticsObserver implements KaraokeObserver {

    private String name = "Analytics Engine";
    private int eventCount = 0;

    @Override
    public void update(String event, Object data) {
        eventCount++;
        switch (event) {
            case "SONG_DIFFICULTY_CHANGED":
                System.out.println("[OBSERVER-ANALYTICS] Zmiana trudności zarejestrowana. Statystyka: " + data);
                trackModification("difficulty", data);
                break;
            case "SONG_TITLE_CHANGED":
                System.out.println("[OBSERVER-ANALYTICS] Zmiana tytułu zarejestrowana. Zmian łącznie: " + eventCount);
                trackModification("title", data);
                break;
        }
    }

    private void trackModification(String type, Object data) {
        System.out.println("  📊 Zapisano do bazy danych: typ=" + type + ", zmiana=" + data);
    }

    public int getEventCount() {
        return eventCount;
    }

    @Override
    public String getObserverName() {
        return name;
    }
}
// Koniec, Tydzień 6, Wzorzec Observer 4
