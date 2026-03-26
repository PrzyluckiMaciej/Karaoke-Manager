package pollub.karaokeapp.Week5.command;

import pollub.karaokeapp.Week2.singleton.LoggerSingleton;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Tydzień 5, Wzorzec Command 1 (Invoker)
 * Invoker przechowuje historię komend i umożliwia cofanie/ponowne wykonywanie.
 */
public class KaraokeCommandInvoker {

    private final Deque<KaraokeCommand> history = new ArrayDeque<>();
    private final Deque<KaraokeCommand> redoStack = new ArrayDeque<>();
    private final LoggerSingleton logger = LoggerSingleton.getInstance();

    public void execute(KaraokeCommand command) {
        command.execute();
        history.push(command);
        redoStack.clear();
        logger.log("[INVOKER] Wykonano: " + command.getDescription());
    }

    public void undo() {
        if (history.isEmpty()) {
            logger.log("[INVOKER] Brak komend do cofnięcia");
            return;
        }
        KaraokeCommand command = history.pop();
        command.undo();
        redoStack.push(command);
        logger.log("[INVOKER] Cofnięto: " + command.getDescription());
    }

    public void redo() {
        if (redoStack.isEmpty()) {
            logger.log("[INVOKER] Brak komend do ponowienia");
            return;
        }
        KaraokeCommand command = redoStack.pop();
        command.execute();
        history.push(command);
        logger.log("[INVOKER] Ponowiono: " + command.getDescription());
    }

    public void printHistory() {
        logger.log("[INVOKER] Historia komend:");
        history.forEach(c -> logger.log("  → " + c.getDescription()));
    }

    public int getHistorySize() {
        return history.size();
    }
}
// Koniec, Tydzień 5, Wzorzec Command 1 (Invoker)