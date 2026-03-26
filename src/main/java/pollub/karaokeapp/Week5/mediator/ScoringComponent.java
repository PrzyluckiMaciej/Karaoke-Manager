package pollub.karaokeapp.Week5.mediator;

/**
 * Tydzień 5, Wzorzec Mediator 3 (cd.)
 * Komponent punktacji – uczestnik mediatora.
 * Oblicza wynik i zgłasza go przez mediatora.
 */
public class ScoringComponent extends KaraokeColleague {

    private int lastScore = 0;

    public ScoringComponent() {
        super("ScoringComponent");
    }

    @Override
    public void receive(String event, Object data) {
        switch (event) {
            case "CALCULATE_SCORE":
                lastScore = 70 + (int) (Math.random() * 30); // symulacja oceny
                System.out.println("[SCORING] 🏆 Obliczono wynik dla '" + data + "': " + lastScore + " pkt");
                mediator.notify(this, "SCORE_CALCULATED", lastScore);
                break;
            default:
                System.out.println("[SCORING] Nieobsługiwane zdarzenie: " + event);
        }
    }

    public int getLastScore() { return lastScore; }
}
// Koniec, Tydzień 5, Wzorzec Mediator 3 (cd.)