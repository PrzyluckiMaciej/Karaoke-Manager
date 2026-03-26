package pollub.karaokeapp.Week5.mediator;

/**
 * Tydzień 5, Wzorzec Mediator 4
 * Komponent wyświetlania – uczestnik mediatora.
 * Obsługuje wyświetlanie tekstu, wyniku i powiadomień na ekranie.
 */
public class DisplayComponent extends KaraokeColleague {

    public DisplayComponent() {
        super("DisplayComponent");
    }

    @Override
    public void receive(String event, Object data) {
        switch (event) {
            case "SHOW_LYRICS":
                System.out.println("[DISPLAY] 📜 Wyświetlanie tekstu do: " + data);
                break;
            case "SHOW_SCORE":
                System.out.println("[DISPLAY] 🏆 Wynik na ekranie: " + data + " pkt");
                break;
            case "SHOW_WELCOME":
                System.out.println("[DISPLAY] 👋 Witaj, " + data + "!");
                break;
            default:
                System.out.println("[DISPLAY] Nieobsługiwane zdarzenie: " + event);
        }
    }
}
// Koniec, Tydzień 5, Wzorzec Mediator 4