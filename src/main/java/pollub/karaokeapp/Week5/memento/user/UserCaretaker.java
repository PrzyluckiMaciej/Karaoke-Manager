package pollub.karaokeapp.Week5.memento.user;

import pollub.karaokeapp.Week2.singleton.LoggerSingleton;
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

    /** Zapis aktualnego stanu na stos historii */
    public void save() {
        UserMemento memento = new UserMemento(user.getNickname(), user.getLevel(), user.getPoints());
        history.push(memento);
        logger.log("[USER-MEMENTO] Zapisano stan: " + memento);
    }

    /** Zapis jako nazwany punkt kontrolny */
    public void saveCheckpoint(String checkpointName) {
        UserMemento memento = new UserMemento(user.getNickname(), user.getLevel(), user.getPoints());
        checkpoints.put(checkpointName, memento);
        logger.log("[USER-MEMENTO] Checkpoint '" + checkpointName + "': " + memento);
    }

    /** Cofnięcie do poprzedniego stanu ze stosu */
    public boolean undo() {
        if (history.isEmpty()) {
            logger.log("[USER-MEMENTO] Brak historii do cofnięcia");
            return false;
        }
        UserMemento memento = history.pop();
        restore(memento);
        logger.log("[USER-MEMENTO] Przywrócono stan: " + memento);
        return true;
    }

    /** Przywrócenie do nazwanego punktu kontrolnego */
    public boolean restoreCheckpoint(String checkpointName) {
        UserMemento memento = checkpoints.get(checkpointName);
        if (memento == null) {
            logger.log("[USER-MEMENTO] Nie znaleziono checkpointu: " + checkpointName);
            return false;
        }
        restore(memento);
        logger.log("[USER-MEMENTO] Przywrócono checkpoint '" + checkpointName + "': " + memento);
        return true;
    }

    /** Przywrócenie do konkretnej migawki */
    private void restore(UserMemento memento) {
        user.setNickname(memento.getNickname());
        user.setLevel(memento.getLevel());
        user.setPoints(memento.getPoints());
    }

    public int getHistorySize()           { return history.size(); }
    public int getCheckpointCount()       { return checkpoints.size(); }
}
// Koniec, Tydzień 5, Wzorzec Memento 4 (Caretaker)