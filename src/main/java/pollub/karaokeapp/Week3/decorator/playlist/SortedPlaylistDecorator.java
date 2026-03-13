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

    private String sortBy; // "title", "artist", "duration", "difficulty"

    public SortedPlaylistDecorator(Playlist decoratedPlaylist, String sortBy) {
        super(decoratedPlaylist);
        this.sortBy = sortBy;
    }

    @Override
    public List<Song> getSongs() {
        Comparator<Song> comparator;

        switch (sortBy.toLowerCase()) {
            case "title":
                comparator = Comparator.comparing(Song::getTitle);
                break;
            case "artist":
                comparator = Comparator.comparing(Song::getArtist);
                break;
            case "duration":
                comparator = Comparator.comparingInt(Song::getDuration);
                break;
            case "difficulty":
                comparator = Comparator.comparingInt(Song::getDifficulty);
                break;
            default:
                comparator = Comparator.comparing(Song::getTitle);
        }

        return decoratedPlaylist.getSongs().stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return decoratedPlaylist.toString() + " [SORTOWANIE: " + sortBy + "]";
    }
}
// Koniec, Tydzień 3, Wzorzec Decorator 2