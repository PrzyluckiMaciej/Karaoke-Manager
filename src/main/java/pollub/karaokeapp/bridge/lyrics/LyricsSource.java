package pollub.karaokeapp.bridge.lyrics;

import pollub.karaokeapp.model.song.Song;
import java.util.List;

/**
 * Tydzień 3, Wzorzec Bridge 3
 * Interfejs - źródło tekstów
 */
public interface LyricsSource {
    List<String> getLyricsLines(Song song);
    String getSourceType();
    boolean hasTranslation();
}
// Koniec, Tydzień 3, Wzorzec Bridge 3