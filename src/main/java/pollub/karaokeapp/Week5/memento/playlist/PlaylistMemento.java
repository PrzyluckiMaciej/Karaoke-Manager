package pollub.karaokeapp.Week5.memento.playlist;

import pollub.karaokeapp.model.song.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Tydzień 5, Wzorzec Memento 3
 * Migawka stanu playlisty (nazwa i lista piosenek).
 */
public class PlaylistMemento {

    private final String name;
    private final List<Song> songs;
    private final long timestamp;

    public PlaylistMemento(String name, List<Song> songs) {
        this.name = name;
        this.songs = new ArrayList<>(songs); // głęboka kopia listy
        this.timestamp = System.currentTimeMillis();
    }

    public String getName()        { return name; }
    public List<Song> getSongs()   { return new ArrayList<>(songs); }
    public long getTimestamp()     { return timestamp; }

    @Override
    public String toString() {
        return "PlaylistSnapshot{name='" + name + "', songs=" + songs.size() + "}";
    }
}
// Koniec, Tydzień 5, Wzorzec Memento 3