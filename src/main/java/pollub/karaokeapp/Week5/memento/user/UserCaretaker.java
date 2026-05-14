package pollub.karaokeapp.Week5.memento.user;

import pollub.karaokeapp.Week2.singleton.LoggerSingleton;
import pollub.karaokeapp.Week5.memento.EmptyHistoryException;
import pollub.karaokeapp.model.user.User;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Tydzień 5, Wzorzec Memento 4 (Caretaker)
 * Opiekun historii stanów użytkownika.
 * Obsługuje stos undo oraz nazwane punkty kontrolne (checkpoints).
 */
public class UserCaretaker {

    private final User user;
    private final Deque<UserMemento> history = new ArrayDeque<>();
    private final Map<String, UserMemento> checkpoints = new HashMap<>();
    private final LoggerSingleton logger = LoggerSingleton.getInstance();

    public UserCaretaker(User user) {
        this.user = user;
    }

    public void save() {
        UserMemento memento = createMemento();
        history.push(memento);
        logSave(memento);
    }

    public void saveCheckpoint(String checkpointName) {
        UserMemento memento = createMemento();
        checkpoints.put(checkpointName, memento);
        logCheckpoint(checkpointName, memento);
    }

    private UserMemento createMemento() {
        return new UserMemento(user.getNickname(), user.getLevel(), user.getPoints());
    }

    private void logSave(UserMemento memento) {
        logger.log("[USER-MEMENTO] Zapisano stan: " + memento);
    }

    private void logCheckpoint(String checkpointName, UserMemento memento) {
        logger.log("[USER-MEMENTO] Checkpoint '" + checkpointName + "': " + memento);
    }

    public void undo() throws EmptyHistoryException {
        if (history.isEmpty()) {
            throw new EmptyHistoryException("Brak historii do cofnięcia");
        }
        UserMemento memento = history.pop();
        restoreFromMemento(memento);
        logRestore(memento);
    }

    public void restoreCheckpoint(String checkpointName) throws IllegalArgumentException {
        UserMemento memento = checkpoints.get(checkpointName);
        if (memento == null) {
            throw new IllegalArgumentException("Nie znaleziono checkpointu: " + checkpointName);
        }
        restoreFromMemento(memento);
        logCheckpointRestore(checkpointName, memento);
    }

    private void restoreFromMemento(UserMemento memento) {
        user.setNickname(memento.getNickname());
        user.setLevel(memento.getLevel());
        user.setPoints(memento.getPoints());
    }

    private void logRestore(UserMemento memento) {
        logger.log("[USER-MEMENTO] Przywrócono stan: " + memento);
    }

    private void logCheckpointRestore(String checkpointName, UserMemento memento) {
        logger.log("[USER-MEMENTO] Przywrócono checkpoint '" + checkpointName + "': " + memento);
    }

    public int getHistorySize() {
        return history.size();
    }

    public int getCheckpointCount() {
        return checkpoints.size();
    }
}
// Koniec, Tydzień 5, Wzorzec Memento 4 (Caretaker)