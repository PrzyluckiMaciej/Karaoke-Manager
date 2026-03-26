package pollub.karaokeapp.Week5.command.performance;

import pollub.karaokeapp.Week5.command.KaraokeCommand;
import pollub.karaokeapp.model.performance.Performance;

/**
 * Tydzień 5, Wzorzec Command 4
 * Komenda ustawienia wyniku dla wykonania (z obsługą undo).
 */
public class SetPerformanceScoreCommand implements KaraokeCommand {

    private final Performance performance;
    private final int newScore;
    private int previousScore;

    public SetPerformanceScoreCommand(Performance performance, int newScore) {
        this.performance = performance;
        this.newScore = newScore;
    }

    @Override
    public void execute() {
        previousScore = performance.getScore();
        performance.setScore(newScore);
        System.out.println("[PERF-CMD] Wynik ustawiony: " + previousScore + " → " + newScore
                + " ('" + performance.getSong().getTitle() + "')");
    }

    @Override
    public void undo() {
        performance.setScore(previousScore);
        System.out.println("[PERF-CMD] Cofnięto zmianę wyniku: " + newScore + " → " + previousScore);
    }

    @Override
    public String getDescription() {
        return "Ustaw wynik " + newScore + " dla '" + performance.getSong().getTitle() + "'";
    }
}
// Koniec, Tydzień 5, Wzorzec Command 4