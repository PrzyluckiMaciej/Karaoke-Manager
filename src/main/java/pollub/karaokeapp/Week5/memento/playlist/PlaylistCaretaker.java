package pollub.karaokeapp.Week5.memento.playlist;

import pollub.karaokeapp.Week2.singleton.LoggerSingleton;
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
        PlaylistMemento memento = new PlaylistMemento(
                playlist.getName(),
                playlist.getSongs()
        );
        history.push(memento);
        logger.log("[PLAYLIST-MEMENTO] Zapisano stan: " + memento);
    }

    public boolean undo() {
        if (history.isEmpty()) {
            logger.log("[PLAYLIST-MEMENTO] Brak historii do cofnięcia");
            return false;
        }
        PlaylistMemento memento = history.pop();
        playlist.setName(memento.getName());
        playlist.setSongs(memento.getSongs());
        logger.log("[PLAYLIST-MEMENTO] Przywrócono stan: " + memento);
        return true;
    }

    public int getHistorySize() { return history.size(); }
}
// Koniec, Tydzień 5, Wzorzec Memento 3 (Caretaker)