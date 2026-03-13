package pollub.karaokeapp.Week3.composite.category;

import pollub.karaokeapp.model.song.Song;
import java.util.ArrayList;
import java.util.List;

/**
 * Tydzień 3, Wzorzec Composite 4
 * Liść - kategoria zawierająca piosenki (bez podkategorii)
 */
public class SongCategoryLeaf implements CategoryComponent {

    private String name;
    private List<Song> songs = new ArrayList<>();

    public SongCategoryLeaf(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Song> getAllSongs() {
        return new ArrayList<>(songs);
    }

    @Override
    public void addSong(Song song) {
        songs.add(song);
    }

    @Override
    public CategoryComponent findCategory(String path) {
        if (path.equals(name)) {
            return this;
        }
        return null;
    }

    @Override
    public void printStructure(String indent) {
        System.out.println(indent + "📂 " + name + " (" + songs.size() + " piosenek)");
        for (Song song : songs) {
            System.out.println(indent + "  🎵 " + song.getTitle() + " - " + song.getArtist());
        }
    }
}
// Koniec, Tydzień 3, Wzorzec Composite 4