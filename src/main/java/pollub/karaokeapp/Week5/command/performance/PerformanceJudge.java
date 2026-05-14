package pollub.karaokeapp.Week5.command.performance;

import pollub.karaokeapp.model.performance.Performance;

/**
 * Tydzień 5, Wzorzec Command 3 – Receiver
 * Odbiorca (Receiver) zawierający logikę biznesową oceniania wykonania.
 * Komendy delegują do tej klasy zamiast operować bezpośrednio na Performance.
 */
public class PerformanceJudge {

    public static final int MIN_SCORE = 0;
    public static final int MAX_SCORE = 100;

    private static final String LOG_PREFIX = "[PERF-RECEIVER]";

    private final Performance performance;

    public PerformanceJudge(Performance performance) {
        if (performance == null) {
            throw new IllegalArgumentException("Wykonanie nie może być null");
        }
        this.performance = performance;
    }

    public int getCurrentScore() {
        return performance.getScore();
    }

    public void applyScore(int score) {
        validateScore(score);
        logScoreChange(score);
        performance.setScore(score);
    }

    public Performance getPerformance() {
        return performance;
    }

    private void validateScore(int score) {
        if (score < MIN_SCORE || score > MAX_SCORE) {
            throw new IllegalArgumentException(
                    "Wynik musi być między " + MIN_SCORE + " a " + MAX_SCORE +
                            ". Podano: " + score
            );
        }
    }

    private void logScoreChange(int newScore) {
        System.out.println(LOG_PREFIX + " Ustawienie wyniku: " +
                performance.getScore() + " → " + newScore +
                " ('" + performance.getSong().getTitle() + "')");
    }
}
// Koniec, Tydzień 5, Wzorzec Command 3 – Receiver