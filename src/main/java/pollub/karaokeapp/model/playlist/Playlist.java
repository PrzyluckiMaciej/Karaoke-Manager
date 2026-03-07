package pollub.karaokeapp.model.playlist;

import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.prototype.Prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * Tydzień 2, Wzorzec Prototype 5
 * Playlist pozwala na głębokie kopiowanie listy piosenek gdyż
 * Każda piosenka w playliście jest klonowana osobno.
 */
public class Playlist implements Prototype<Playlist> {

    private String name;
    private List<Song> songs = new ArrayList<>();

    public Playlist(String name) {
        this.name = name;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    @Override
    public Playlist clone() {
        Playlist cloned = new Playlist(name);
        for (Song song : songs) {
            cloned.addSong(song.clone());
        }
        return cloned;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "name='" + name + '\'' +
                ", songs=" + songs +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
//Koniec, Tydzień 2, Wzorzec Prototype 5