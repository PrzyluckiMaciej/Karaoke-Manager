package pollub.karaokeapp.Week3.composite.category;

import pollub.karaokeapp.model.song.Song;
import java.util.ArrayList;
import java.util.List;

/**
 * Tydzień 3, Wzorzec Composite 4
 * Liść - kategoria zawierająca piosenki (bez podkategorii)
 */
public class SongCategoryLeaf implements CategoryComponent {

    private static final int TARGET_NODE_DEPTH = 1;
    private static final String INDENT_SPACES = "  ";

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
        if (path == null) {
            return null;
        }
        return path.equals(name) ? this : null;
    }

    @Override
    public void printStructure(String indent) {
        printHeader(indent);
        printSongs(indent);
    }

    private void printHeader(String indent) {
        System.out.println(indent + "📂 " + name + " (" + songs.size() + " piosenek)");
    }

    private void printSongs(String indent) {
        String songIndent = indent + INDENT_SPACES;
        for (Song song : songs) {
            System.out.println(songIndent + "🎵 " + song.getTitle() + " - " + song.getArtist());
        }
    }
}