package pollub.karaokeapp.Week5.memento.song;

import pollub.karaokeapp.Week2.singleton.LoggerSingleton;
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
        SongMemento memento = new SongMemento(
                song.getTitle(), song.getArtist(),
                song.getDuration(), song.getGenre(), song.getDifficulty()
        );
        history.push(memento);
        logger.log("[SONG-MEMENTO] Zapisano stan: " + memento);
    }

    public boolean undo() {
        if (history.isEmpty()) {
            logger.log("[SONG-MEMENTO] Brak historii do cofnięcia");
            return false;
        }
        SongMemento memento = history.pop();
        restore(memento);
        logger.log("[SONG-MEMENTO] Przywrócono stan: " + memento);
        return true;
    }

    private void restore(SongMemento memento) {
        song.setTitle(memento.getTitle());
        song.setArtist(memento.getArtist());
        song.setDuration(memento.getDuration());
        song.setGenre(memento.getGenre());
        song.setDifficulty(memento.getDifficulty());
    }

    public int getHistorySize() { return history.size(); }
}
// Koniec, Tydzień 5, Wzorzec Memento 1 (Caretaker)