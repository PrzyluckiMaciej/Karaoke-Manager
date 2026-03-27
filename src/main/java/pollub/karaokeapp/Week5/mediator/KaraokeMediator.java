package pollub.karaokeapp.Week5.mediator;

/**
 * Tydzień 5, Wzorzec Mediator
 * Interfejs mediatora systemu karaoke.
 * Komponenty komunikują się wyłącznie przez mediatora, nie bezpośrednio ze sobą.
 */
public interface KaraokeMediator {
    void notify(KaraokeColleague sender, String event, Object data);
    void registerColleague(String role, KaraokeColleague colleague);
}
// Koniec, Tydzień 5, Wzorzec Mediator