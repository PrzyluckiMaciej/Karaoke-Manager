package pollub.karaokeapp.Week6.observer;

/**
 * Tydzień 6, Wzorzec Observer 4
 * Konkretny obserwator - system analityki śledzi zmiany w piosenkach
 */
public class AnalyticsObserver implements KaraokeObserver {

    private final String name = "Analytics Engine";
    private int eventCount = 0;

    @Override
    public void update(String event, Object data) {
        eventCount++;

        if (SongEventType.DIFFICULTY_CHANGED.equals(event)) {
            System.out.println("[OBSERVER-ANALYTICS] Zmiana trudności zarejestrowana. Statystyka: " + data);
            trackModification("difficulty", data);
        } else if (SongEventType.TITLE_CHANGED.equals(event)) {
            System.out.println("[OBSERVER-ANALYTICS] Zmiana tytułu zarejestrowana. Zmian łącznie: " + eventCount);
            trackModification("title", data);
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
