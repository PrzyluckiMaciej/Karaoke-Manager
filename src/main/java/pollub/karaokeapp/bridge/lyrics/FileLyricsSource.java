package pollub.karaokeapp.bridge.lyrics;

import pollub.karaokeapp.model.song.Song;
import java.util.ArrayList;
import java.util.List;

/**
 * Tydzień 3, Wzorzec Bridge 3
 * Konkretna implementacja - teksty z pliku
 */
public class FileLyricsSource implements LyricsSource {

    private String filePath;

    public FileLyricsSource(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<String> getLyricsLines(Song song) {
        // Symulacja odczytu z pliku
        List<String> lines = new ArrayList<>();
        lines.add("Pierwsza linia tekstu piosenki " + song.getTitle());
        lines.add("Druga linia - refren");
        lines.add("Trzecia linia - zwrotka 2");
        lines.add("Czwarta linia - refren powtórka");
        lines.add("Piąta linia - zakończenie");
        return lines;
    }

    @Override
    public String getSourceType() {
        return "Plik: " + filePath;
    }

    @Override
    public boolean hasTranslation() {
        return filePath.contains("translated");
    }
}
// Koniec, Tydzień 3, Wzorzec Bridge 3