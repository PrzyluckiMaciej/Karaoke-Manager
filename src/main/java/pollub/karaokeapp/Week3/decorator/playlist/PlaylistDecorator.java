package pollub.karaokeapp.Week3.decorator.playlist;

import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;
import java.util.List;

/**
 * Tydzień 3, Wzorzec Decorator 2
 * Bazowa klasa dekoratora dla playlist
 */
public abstract class PlaylistDecorator extends Playlist {

    protected Playlist decoratedPlaylist;

    public PlaylistDecorator(Playlist decoratedPlaylist) {
        super(decoratedPlaylist.getName());
        validateDecoratedPlaylist(decoratedPlaylist);
        this.decoratedPlaylist = decoratedPlaylist;
    }

    private void validateDecoratedPlaylist(Playlist playlist) {
        if (playlist == null) {
            throw new IllegalArgumentException("Decorated playlist cannot be null");
        }
    }

    @Override
    public List<Song> getSongs() {
        return decoratedPlaylist.getSongs();
    }

    @Override
    public String toString() {
        return decoratedPlaylist.toString();
    }
}
// Koniec, Tydzień 3, Wzorzec Decorator 2