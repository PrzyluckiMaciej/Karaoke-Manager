package pollub.karaokeapp.Week3.decorator.playlist;

import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Tydzień 3, Wzorzec Decorator 2
 * Dekorator sortujący playlistę
 */
public class SortedPlaylistDecorator extends PlaylistDecorator {

    public static final String SORT_BY_TITLE = "title";
    public static final String SORT_BY_ARTIST = "artist";
    public static final String SORT_BY_DURATION = "duration";
    public static final String SORT_BY_DIFFICULTY = "difficulty";

    private String sortBy;

    public SortedPlaylistDecorator(Playlist decoratedPlaylist, String sortBy) {
        super(decoratedPlaylist);
        validateSortKey(sortBy);
        this.sortBy = sortBy;
    }

    private void validateSortKey(String sortKey) {
        if (sortKey == null || sortKey.trim().isEmpty()) {
            throw new IllegalArgumentException("Sort key cannot be null or empty");
        }
        getComparatorForSortKey(sortKey);
    }

    @Override
    public List<Song> getSongs() {
        Comparator<Song> comparator = getComparatorForSortKey(sortBy);
        return decoratedPlaylist.getSongs().stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    private Comparator<Song> getComparatorForSortKey(String sortKey) {
        switch (sortKey.toLowerCase()) {
            case SORT_BY_TITLE:
                return Comparator.comparing(Song::getTitle);
            case SORT_BY_ARTIST:
                return Comparator.comparing(Song::getArtist);
            case SORT_BY_DURATION:
                return Comparator.comparingInt(Song::getDuration);
            case SORT_BY_DIFFICULTY:
                return Comparator.comparingInt(Song::getDifficulty);
            default:
                throw new IllegalArgumentException(
                        "Unsupported sort key: " + sortKey +
                                ". Supported: title, artist, duration, difficulty"
                );
        }
    }

    @Override
    public String toString() {
        return decoratedPlaylist.toString() + " [SORTOWANIE: " + sortBy + "]";
    }
}
// Koniec, Tydzień 3, Wzorzec Decorator 2