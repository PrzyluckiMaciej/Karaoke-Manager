package pollub.karaokeapp.Week6.observer;

/**
 * Tydzień 6, Wzorzec Observer 3
 * Konkretny obserwator - system oceniania reaguje na zmiany w piosence
 * Przykład: zmiana trudności wpływa na sposób obliczania punktów
 */
public class ScoringObserver implements KaraokeObserver {

    private final String name = "Scoring System";

    @Override
    public void update(String event, Object data) {
        if (SongEventType.DIFFICULTY_CHANGED.equals(event)) {
            System.out.println("[OBSERVER-SCORING] Trudność zmieniona! Aktualizuję algorytm oceniania: " + data);
            recalibrateScoringAlgorithm((String) data);
        } else if (SongEventType.TITLE_CHANGED.equals(event)) {
            System.out.println("[OBSERVER-SCORING] Tytuł zmieniony: " + data);
        }
    }

    private void recalibrateScoringAlgorithm(String difficultyInfo) {
        System.out.println("  ⚙️ Rekalibracja algorytmu oceniania...");
        System.out.println("  ✓ Nowa waga punktów zastosowana: " + difficultyInfo);
    }

    @Override
    public String getObserverName() {
        return name;
    }
}
// Koniec, Tydzień 6, Wzorzec Observer 3
