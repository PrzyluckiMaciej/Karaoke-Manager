package pollub.karaokeapp.Week5.command.performance;

import pollub.karaokeapp.Week5.command.KaraokeCommand;

/**
 * Tydzień 5, Wzorzec Command 3
 * Komenda ustawienia wyniku wykonania.
 * Deleguje faktyczną logikę do Receivera (PerformanceJudge).
 */
public class SetPerformanceScoreCommand implements KaraokeCommand {

    private final PerformanceJudge receiver;
    private final int newScore;
    private int previousScore;

    public SetPerformanceScoreCommand(PerformanceJudge receiver, int newScore) {
        this.receiver = receiver;
        this.newScore = newScore;
    }

    @Override
    public void execute() {
        previousScore = receiver.getCurrentScore();
        receiver.applyScore(newScore);
    }

    @Override
    public void undo() {
        System.out.println("[PERF-CMD] Cofanie zmiany wyniku: " + newScore + " → " + previousScore);
        receiver.applyScore(previousScore);
    }

    @Override
    public String getDescription() {
        return "Ustaw wynik " + newScore + " dla '" + receiver.getPerformance().getSong().getTitle() + "'";
    }
}
// Koniec, Tydzień 5, Wzorzec Command 3