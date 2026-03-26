package pollub.karaokeapp.Week5.iterator.playlist;

import pollub.karaokeapp.Week5.iterator.KaraokeIterator;
import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Tydzień 5, Wzorzec Iterator 1 (cd.)
 * Iterator filtrujący – przechodzi tylko po piosenkach pasującego gatunku.
 */
public class GenreFilteredPlaylistIterator implements KaraokeIterator<Song> {

    private final List<Song> filteredSongs;
    private int currentIndex = 0;

    public GenreFilteredPlaylistIterator(Playlist playlist, String genre) {
        this.filteredSongs = playlist.getSongs().stream()
                .filter(s -> s.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasNext() {
        return currentIndex < filteredSongs.size();
    }

    @Override
    public Song next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException("Brak kolejnych piosenek w gatunku");
        }
        return filteredSongs.get(currentIndex++);
    }

    @Override
    public void reset() {
        currentIndex = 0;
    }

    public int getFilteredCount() {
        return filteredSongs.size();
    }
}
// Koniec, Tydzień 5, Wzorzec Iterator 1 (cd.)