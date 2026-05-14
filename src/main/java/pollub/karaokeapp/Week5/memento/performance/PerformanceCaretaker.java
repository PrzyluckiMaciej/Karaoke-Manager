package pollub.karaokeapp.Week5.memento.performance;

import pollub.karaokeapp.Week2.singleton.LoggerSingleton;
import pollub.karaokeapp.Week5.memento.EmptyHistoryException;
import pollub.karaokeapp.model.performance.Performance;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Tydzień 5, Wzorzec Memento 2 (Caretaker)
 * Opiekun historii stanów wykonania.
 */
public class PerformanceCaretaker {

    private final Performance performance;
    private final Deque<PerformanceMemento> history = new ArrayDeque<>();
    private final LoggerSingleton logger = LoggerSingleton.getInstance();

    public PerformanceCaretaker(Performance performance) {
        this.performance = performance;
    }

    public void save() {
        PerformanceMemento memento = createMemento();
        history.push(memento);
        logSave(memento);
    }

    private PerformanceMemento createMemento() {
        return new PerformanceMemento(
                performance.getScore(),
                performance.getParticipants().size(),
                performance.getSong().getTitle()
        );
    }

    private void logSave(PerformanceMemento memento) {
        logger.log("[PERF-MEMENTO] Zapisano stan: " + memento);
    }

    public void undo() throws EmptyHistoryException {
        if (history.isEmpty()) {
            throw new EmptyHistoryException("Brak historii do cofnięcia");
        }
        PerformanceMemento memento = history.pop();
        restoreFromMemento(memento);
        logRestore(memento);
    }

    private void restoreFromMemento(PerformanceMemento memento) {
        performance.setScore(memento.getScore());
    }

    private void logRestore(PerformanceMemento memento) {
        logger.log("[PERF-MEMENTO] Przywrócono stan: " + memento);
    }

    public int getHistorySize() {
        return history.size();
    }
}
// Koniec, Tydzień 5, Wzorzec Memento 2 (Caretaker)