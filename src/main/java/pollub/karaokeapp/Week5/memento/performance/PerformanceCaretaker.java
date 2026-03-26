package pollub.karaokeapp.Week5.memento.performance;

import pollub.karaokeapp.Week2.singleton.LoggerSingleton;
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
        PerformanceMemento memento = new PerformanceMemento(
                performance.getScore(),
                performance.getParticipants().size(),
                performance.getSong().getTitle()
        );
        history.push(memento);
        logger.log("[PERF-MEMENTO] Zapisano stan: " + memento);
    }

    public boolean undo() {
        if (history.isEmpty()) {
            logger.log("[PERF-MEMENTO] Brak historii do cofnięcia");
            return false;
        }
        PerformanceMemento memento = history.pop();
        performance.setScore(memento.getScore());
        logger.log("[PERF-MEMENTO] Przywrócono stan: " + memento);
        return true;
    }

    public int getHistorySize() { return history.size(); }
}
// Koniec, Tydzień 5, Wzorzec Memento 2 (Caretaker)