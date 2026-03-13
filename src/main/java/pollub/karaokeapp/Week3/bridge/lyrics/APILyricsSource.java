package pollub.karaokeapp.Week3.bridge.lyrics;

import pollub.karaokeapp.model.song.Song;
import java.util.ArrayList;
import java.util.List;

public class APILyricsSource implements LyricsSource {

    private String apiEndpoint;

    public APILyricsSource(String apiEndpoint) {
        this.apiEndpoint = apiEndpoint;
    }

    @Override
    public List<String> getLyricsLines(Song song) {
        // Symulacja zapytania do API
        List<String> lines = new ArrayList<>();
        lines.add("[API] " + song.getTitle() + " - wers 1");
        lines.add("[API] Refren z API");
        lines.add("[API] Kolejna zwrotka");
        return lines;
    }

    @Override
    public String getSourceType() {
        return "API: " + apiEndpoint;
    }

    @Override
    public boolean hasTranslation() {
        return true; // API może dostarczać tłumaczenia
    }
}
// Koniec, Tydzień 3, Wzorzec Bridge 3