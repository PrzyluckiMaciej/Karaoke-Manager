package pollub.karaokeapp.Week5.mediator;

/**
 * Tydzień 5, Wzorzec Mediator 4
 * Komponent punktacji – uczestnik mediatora.
 * Oblicza wynik i zgłasza go przez mediatora.
 */
public class ScoringComponent extends KaraokeColleague {

    private static final int SCORE_MIN = 70;
    private static final int SCORE_RANDOM_RANGE = 30;
    private int lastScore = 0;

    public ScoringComponent() {
        super("ScoringComponent");
    }

    @Override
    public void receive(String event, Object data) {
        if ("CALCULATE_SCORE".equals(event)) {
            calculateAndNotifyScore(data);
        } else {
            System.out.println("[SCORING] Nieobsługiwane zdarzenie: " + event);
        }
    }

    private void calculateAndNotifyScore(Object songTitle) {
        lastScore = SCORE_MIN + (int) (Math.random() * SCORE_RANDOM_RANGE);
        System.out.println("[SCORING] 🏆 Obliczono wynik dla '" + songTitle + "': " + lastScore + " pkt");
        mediator.notify(this, "SCORE_CALCULATED", lastScore);
    }

    public int getLastScore() { return lastScore; }
}
// Koniec, Tydzień 5, Wzorzec Mediator 4