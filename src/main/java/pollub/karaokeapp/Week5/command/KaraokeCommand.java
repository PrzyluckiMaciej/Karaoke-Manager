package pollub.karaokeapp.Week5.command;

/**
 * Tydzień 5, Wzorzec Command
 * Interfejs bazowy dla wszystkich komend w systemie karaoke.
 * Każda komenda może zostać wykonana i cofnięta (undo).
 */
public interface KaraokeCommand {
    void execute();
    void undo();
    String getDescription();
}
// Koniec, Tydzień 5, Wzorzec Command