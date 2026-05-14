package pollub.karaokeapp.Week5.memento.playlist;

import pollub.karaokeapp.Week2.singleton.LoggerSingleton;
import pollub.karaokeapp.Week5.memento.EmptyHistoryException;
import pollub.karaokeapp.model.playlist.Playlist;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Tydzień 5, Wzorzec Memento 3 (Caretaker)
 * Opiekun historii stanów playlisty.
 * Umożliwia cofanie dodawania/usuwania piosenek i zmiany nazwy.
 */
public class PlaylistCaretaker {

    private final Playlist playlist;
    private final Deque<PlaylistMemento> history = new ArrayDeque<>();
    private final LoggerSingleton logger = LoggerSingleton.getInstance();

    public PlaylistCaretaker(Playlist playlist) {
        this.playlist = playlist;
    }

    public void save() {
        PlaylistMemento memento = createMemento();
        history.push(memento);
        logSave(memento);
    }

    private PlaylistMemento createMemento() {
        return new PlaylistMemento(playlist.getName(), playlist.getSongs());
    }

    private void logSave(PlaylistMemento memento) {
        logger.log("[PLAYLIST-MEMENTO] Zapisano stan: " + memento);
    }

    public void undo() throws EmptyHistoryException {
        if (history.isEmpty()) {
            throw new EmptyHistoryException("Brak historii do cofnięcia");
        }
        PlaylistMemento memento = history.pop();
        restoreFromMemento(memento);
        logRestore(memento);
    }

    private void restoreFromMemento(PlaylistMemento memento) {
        playlist.setName(memento.getName());
        playlist.setSongs(memento.getSongs());
    }

    private void logRestore(PlaylistMemento memento) {
        logger.log("[PLAYLIST-MEMENTO] Przywrócono stan: " + memento);
    }

    public int getHistorySize() {
        return history.size();
    }
}
// Koniec, Tydzień 5, Wzorzec Memento 3 (Caretaker)