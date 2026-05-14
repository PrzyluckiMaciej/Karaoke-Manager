package pollub.karaokeapp.Week5.memento.song;

import pollub.karaokeapp.Week2.singleton.LoggerSingleton;
import pollub.karaokeapp.Week5.memento.EmptyHistoryException;
import pollub.karaokeapp.model.song.Song;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Tydzień 5, Wzorzec Memento 1 (Caretaker)
 * Opiekun historii stanów piosenki.
 * Przechowuje stos migawek i umożliwia cofanie zmian.
 */
public class SongCaretaker {

    private final Song song;
    private final Deque<SongMemento> history = new ArrayDeque<>();
    private final LoggerSingleton logger = LoggerSingleton.getInstance();

    public SongCaretaker(Song song) {
        this.song = song;
    }

    public void save() {
        SongMemento memento = createMemento();
        history.push(memento);
        logSave(memento);
    }

    private SongMemento createMemento() {
        return new SongMemento(
                song.getTitle(), song.getArtist(),
                song.getDuration(), song.getGenre(), song.getDifficulty()
        );
    }

    private void logSave(SongMemento memento) {
        logger.log("[SONG-MEMENTO] Zapisano stan: " + memento);
    }

    public void undo() throws EmptyHistoryException {
        if (history.isEmpty()) {
            throw new EmptyHistoryException("Brak historii do cofnięcia");
        }
        SongMemento memento = history.pop();
        restoreFromMemento(memento);
        logRestore(memento);
    }

    private void restoreFromMemento(SongMemento memento) {
        song.setTitle(memento.getTitle());
        song.setArtist(memento.getArtist());
        song.setDuration(memento.getDuration());
        song.setGenre(memento.getGenre());
        song.setDifficulty(memento.getDifficulty());
    }

    private void logRestore(SongMemento memento) {
        logger.log("[SONG-MEMENTO] Przywrócono stan: " + memento);
    }

    public int getHistorySize() {
        return history.size();
    }
}
// Koniec, Tydzień 5, Wzorzec Memento 1 (Caretaker)