package pollub.karaokeapp.Week5.iterator.song;

import pollub.karaokeapp.Week5.iterator.KaraokeIterator;
import pollub.karaokeapp.model.song.Song;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Tydzień 5, Wzorzec Iterator 3
 * Iterator piosenek posortowanych rosnąco wg trudności – tryb nauki.
 */
public class DifficultySortedSongIterator implements KaraokeIterator<Song> {

    private final List<Song> sortedSongs;
    private int currentIndex = 0;

    public DifficultySortedSongIterator(List<Song> songs) {
        this.sortedSongs = new ArrayList<>(songs);
        this.sortedSongs.sort(Comparator.comparingInt(Song::getDifficulty));
    }

    @Override
    public boolean hasNext() {
        return currentIndex < sortedSongs.size();
    }

    @Override
    public Song next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException("Brak kolejnych piosenek");
        }
        return sortedSongs.get(currentIndex++);
    }

    @Override
    public void reset() {
        currentIndex = 0;
    }

    public int getRemaining() {
        return sortedSongs.size() - currentIndex;
    }
}
// Koniec, Tydzień 5, Wzorzec Iterator 3