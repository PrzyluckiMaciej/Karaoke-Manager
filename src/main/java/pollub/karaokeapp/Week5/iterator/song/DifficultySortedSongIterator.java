package pollub.karaokeapp.Week5.iterator.song;

import pollub.karaokeapp.Week5.iterator.AbstractKaraokeIterator;
import pollub.karaokeapp.Week5.iterator.KaraokeIterator;
import pollub.karaokeapp.model.song.Song;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Tydzień 5, Wzorzec Iterator 3
 * Iterator piosenek posortowanych rosnąco wg trudności – tryb nauki.
 */
public class DifficultySortedSongIterator extends AbstractKaraokeIterator<Song> {

    public DifficultySortedSongIterator(List<Song> songs) {
        super(createDifficultySortedCopy(songs));
    }

    private static List<Song> createDifficultySortedCopy(List<Song> songs) {
        List<Song> copy = new ArrayList<>(songs);
        copy.sort(Comparator.comparingInt(Song::getDifficulty));
        return copy;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < items.size();
    }

    @Override
    protected String getNoElementsMessage() {
        return "Nie znaleziono więcej piosenek w trybie nauki";
    }

    public int getRemainingCount() {
        return items.size() - currentIndex;
    }

    public int getCurrentDifficultyLevel() {
        if (currentIndex == 0 || currentIndex > items.size()) {
            return 0;
        }
        return items.get(currentIndex - 1).getDifficulty();
    }

    public boolean hasEasierSongs() {
        return currentIndex > 0;
    }

    public boolean hasHarderSongs() {
        return currentIndex < items.size();
    }
}
// Koniec, Tydzień 5, Wzorzec Iterator 3