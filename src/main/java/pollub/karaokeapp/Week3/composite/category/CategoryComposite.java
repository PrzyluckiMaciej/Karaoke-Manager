package pollub.karaokeapp.Week3.composite.category;

import pollub.karaokeapp.Week3.composite.utils.IndexUtils;
import pollub.karaokeapp.model.song.Song;
import java.util.ArrayList;
import java.util.List;

/**
 * Tydzień 3, Wzorzec Composite 4
 * Kompozyt - kategoria zawierająca podkategorie
 */
public class CategoryComposite implements CategoryComponent {

    private static final int PATH_SEPARATOR_INDEX = 1;
    private static final String PATH_SEPARATOR = "/";

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
        if (path == null || path.isEmpty()) {
            return null;
        }

        String[] pathParts = splitPath(path);
        if (!matchesCurrentNode(pathParts)) {
            return null;
        }

        return isTargetNode(pathParts) ? this : findInSubcategories(extractRemainingPath(pathParts));
    }

    private String[] splitPath(String path) {
        return path.split(PATH_SEPARATOR);
    }

    private boolean matchesCurrentNode(String[] pathParts) {
        return pathParts.length > 0 && pathParts[0].equals(name);
    }

    private boolean isTargetNode(String[] pathParts) {
        return pathParts.length == PATH_SEPARATOR_INDEX;
    }

    private String extractRemainingPath(String[] pathParts) {
        return String.join(PATH_SEPARATOR,
                java.util.Arrays.copyOfRange(pathParts, PATH_SEPARATOR_INDEX, pathParts.length));
    }

    private CategoryComponent findInSubcategories(String remainingPath) {
        for (CategoryComponent subcategory : subcategories) {
            CategoryComponent found = subcategory.findCategory(remainingPath);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    @Override
    public void printStructure(String indent) {
        printHeader(indent);
        printSongs(indent);
        printSubcategories(indent);
    }

    private void printHeader(String indent) {
        System.out.println(indent + "📁 " + name +
                " (piosenek: " + songs.size() +
                ", podkategorii: " + subcategories.size() + ")");
    }

    private void printSongs(String indent) {
        String songIndent = indent + "  ";
        for (Song song : songs) {
            System.out.println(songIndent + "🎵 " + song.getTitle());
        }
    }

    private void printSubcategories(String indent) {
        String childIndent = indent + "  ";
        for (CategoryComponent subcategory : subcategories) {
            subcategory.printStructure(childIndent);
        }
    }
}