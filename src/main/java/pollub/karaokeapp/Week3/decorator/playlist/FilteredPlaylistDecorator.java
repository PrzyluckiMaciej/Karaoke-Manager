package pollub.karaokeapp.Week3.decorator.playlist;

import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Tydzień 3, Wzorzec Decorator 2
 * Dekorator filtrujący playlistę według gatunku
 */
public class FilteredPlaylistDecorator extends PlaylistDecorator {

    private String allowedGenre;

    public FilteredPlaylistDecorator(Playlist decoratedPlaylist, String allowedGenre) {
        super(decoratedPlaylist);
        validateAllowedGenre(allowedGenre);
        this.allowedGenre = allowedGenre;
    }

    private void validateAllowedGenre(String genre) {
        if (genre == null || genre.trim().isEmpty()) {
            throw new IllegalArgumentException("Allowed genre cannot be null or empty");
        }
    }

    @Override
    public List<Song> getSongs() {
        return decoratedPlaylist.getSongs().stream()
                .filter(this::hasAllowedGenre)
                .collect(Collectors.toList());
    }

    private boolean hasAllowedGenre(Song song) {
        return song.getGenre().equalsIgnoreCase(allowedGenre);
    }

    @Override
    public String toString() {
        return decoratedPlaylist.toString() + " [FILTR: tylko " + allowedGenre + "]";
    }
}
// Koniec, Tydzień 3, Wzorzec Decorator 2