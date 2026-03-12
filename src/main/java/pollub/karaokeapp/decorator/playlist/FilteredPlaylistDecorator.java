package pollub.karaokeapp.decorator.playlist;

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
        this.allowedGenre = allowedGenre;
    }

    @Override
    public List<Song> getSongs() {
        return decoratedPlaylist.getSongs().stream()
                .filter(song -> song.getGenre().equalsIgnoreCase(allowedGenre))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return decoratedPlaylist.toString() + " [FILTR: tylko " + allowedGenre + "]";
    }
}
// Koniec, Tydzień 3, Wzorzec Decorator 2