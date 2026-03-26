package pollub.karaokeapp.Week5.mediator;

/**
 * Tydzień 5, Wzorzec Mediator 1 (cd.)
 * Klasa bazowa uczestnika (Colleague) systemu mediator.
 * Każdy komponent zna tylko mediatora, nie innych uczestników.
 */
public abstract class KaraokeColleague {

    protected KaraokeMediator mediator;
    protected final String name;

    public KaraokeColleague(String name) {
        this.name = name;
    }

    public void setMediator(KaraokeMediator mediator) {
        this.mediator = mediator;
    }

    public String getName() {
        return name;
    }

    public abstract void receive(String event, Object data);
}
// Koniec, Tydzień 5, Wzorzec Mediator 1 (cd.)