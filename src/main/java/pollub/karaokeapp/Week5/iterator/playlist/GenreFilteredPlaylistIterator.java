package pollub.karaokeapp.Week5.iterator.playlist;

import pollub.karaokeapp.Week5.iterator.AbstractKaraokeIterator;
import pollub.karaokeapp.Week5.iterator.KaraokeIterator;
import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Tydzień 5, Wzorzec Iterator 1 (cd.)
 * Iterator filtrujący – przechodzi tylko po piosenkach pasującego gatunku.
 */
public class GenreFilteredPlaylistIterator extends AbstractKaraokeIterator<Song> {

    public GenreFilteredPlaylistIterator(Playlist playlist, String genre) {
        super(filterByGenre(playlist, genre));
    }

    private static List<Song> filterByGenre(Playlist playlist, String genre) {
        return playlist.getSongs().stream()
                .filter(song -> song.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasNext() {
        return currentIndex < items.size();
    }

    @Override
    protected String getNoElementsMessage() {
        return "Brak kolejnych piosenek w wybranym gatunku";
    }

    public int getFilteredCount() {
        return items.size();
    }

    public boolean hasGenre(String genre) {
        return items.stream().anyMatch(song -> song.getGenre().equalsIgnoreCase(genre));
    }
}
// Koniec, Tydzień 5, Wzorzec Iterator 1 (cd.)