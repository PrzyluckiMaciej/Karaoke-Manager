package pollub.karaokeapp.Week3.composite.category;

import pollub.karaokeapp.model.song.Song;
import java.util.ArrayList;
import java.util.List;

/**
 * Tydzień 3, Wzorzec Composite 4
 * Kompozyt - kategoria zawierająca podkategorie
 */
public class CategoryComposite implements CategoryComponent {

    private String name;
    private List<CategoryComponent> subcategories = new ArrayList<>();
    private List<Song> songs = new ArrayList<>();

    public CategoryComposite(String name) {
        this.name = name;
    }

    public void addSubcategory(CategoryComponent subcategory) {
        subcategories.add(subcategory);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Song> getAllSongs() {
        List<Song> allSongs = new ArrayList<>(songs);
        for (CategoryComponent subcategory : subcategories) {
            allSongs.addAll(subcategory.getAllSongs());
        }
        return allSongs;
    }

    @Override
    public void addSong(Song song) {
        songs.add(song);
    }

    @Override
    public CategoryComponent findCategory(String path) {
        String[] parts = path.split("/");
        if (parts[0].equals(name)) {
            if (parts.length == 1) {
                return this;
            }
            String remainingPath = path.substring(name.length() + 1);
            for (CategoryComponent subcategory : subcategories) {
                CategoryComponent found = subcategory.findCategory(remainingPath);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    @Override
    public void printStructure(String indent) {
        System.out.println(indent + "📁 " + name + " (piosenek: " + songs.size() + ", podkategorii: " + subcategories.size() + ")");
        for (Song song : songs) {
            System.out.println(indent + "  🎵 " + song.getTitle());
        }
        for (CategoryComponent subcategory : subcategories) {
            subcategory.printStructure(indent + "  ");
        }
    }
}
// Koniec, Tydzień 3, Wzorzec Composite 4