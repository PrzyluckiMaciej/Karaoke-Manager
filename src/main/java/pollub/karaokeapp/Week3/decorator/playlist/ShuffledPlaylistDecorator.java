package pollub.karaokeapp.Week3.decorator.playlist;

import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Tydzień 3, Wzorzec Decorator 2
 * Dekorator tasujący playlistę
 */
public class ShuffledPlaylistDecorator extends PlaylistDecorator {

    private long seed;

    public ShuffledPlaylistDecorator(Playlist decoratedPlaylist, long seed) {
        super(decoratedPlaylist);
        this.seed = seed;
    }

    @Override
    public List<Song> getSongs() {
        List<Song> shuffled = new ArrayList<>(decoratedPlaylist.getSongs());
        Collections.shuffle(shuffled, new java.util.Random(seed));
        return shuffled;
    }

    @Override
    public String toString() {
        return decoratedPlaylist.toString() + " [WYMIESZANE]";
    }
}
// Koniec, Tydzień 3, Wzorzec Decorator 2