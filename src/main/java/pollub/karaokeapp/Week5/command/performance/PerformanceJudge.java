package pollub.karaokeapp.Week5.command.performance;

import pollub.karaokeapp.model.performance.Performance;

/**
 * Tydzień 5, Wzorzec Command 3 – Receiver
 * Odbiorca (Receiver) zawierający logikę biznesową oceniania wykonania.
 * Komendy delegują do tej klasy zamiast operować bezpośrednio na Performance.
 */
public class PerformanceJudge {

    private final Performance performance;

    public PerformanceJudge(Performance performance) {
        this.performance = performance;
    }

    public int getCurrentScore() {
        return performance.getScore();
    }

    public void applyScore(int score) {
        System.out.println("[PERF-RECEIVER] Ustawienie wyniku: "
                + performance.getScore() + " → " + score
                + " ('" + performance.getSong().getTitle() + "')");
        performance.setScore(score);
    }

    public Performance getPerformance() {
        return performance;
    }
}
// Koniec, Tydzień 5, Wzorzec Command 3 – Receiver