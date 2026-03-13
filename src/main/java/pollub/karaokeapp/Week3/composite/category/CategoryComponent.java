package pollub.karaokeapp.Week3.composite.category;

import pollub.karaokeapp.model.song.Song;
import java.util.List;

/**
 * Tydzień 3, Wzorzec Composite 4
 * Interfejs dla kategorii piosenek
 */
public interface CategoryComponent {
    String getName();
    List<Song> getAllSongs();
    void addSong(Song song);
    CategoryComponent findCategory(String path);
    void printStructure(String indent);
}
// Koniec, Tydzień 3, Wzorzec Composite 4