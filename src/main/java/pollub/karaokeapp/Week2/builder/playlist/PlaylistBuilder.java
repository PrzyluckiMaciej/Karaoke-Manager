package pollub.karaokeapp.Week2.builder.playlist;

import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 2, Wzorzec Builder 4
 * Builder dla Playlisty z wymaganym polem name dla Playlist,
 * piosenki dodawane opcjonalnie metodą addSong.
 */
public class PlaylistBuilder {

    private final Playlist playlist;

    public PlaylistBuilder(String name) {
        this.playlist = new Playlist(name);
    }

    public PlaylistBuilder addSong(Song song) {
        this.playlist.addSong(song);
        return this;
    }

    public Playlist build() {
        return playlist;
    }
}
// Koniec, Tydzień 2, Wzorzec Builder 4