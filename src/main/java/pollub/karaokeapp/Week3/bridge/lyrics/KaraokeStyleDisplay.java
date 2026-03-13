package pollub.karaokeapp.Week3.bridge.lyrics;

import pollub.karaokeapp.model.song.Song;
import java.util.List;

/**
 * Tydzień 3, Wzorzec Bridge 3
 * Konkretna implementacja - styl karaoke z podświetlaniem sylab
 */
public class KaraokeStyleDisplay extends LyricsDisplay {

    private boolean showTranslation;
    private String backgroundColor;

    public KaraokeStyleDisplay(LyricsSource lyricsSource, boolean showTranslation, String backgroundColor) {
        super(lyricsSource);
        this.showTranslation = showTranslation && lyricsSource.hasTranslation();
        this.backgroundColor = backgroundColor;
    }

    @Override
    public void showLyrics(Song song) {
        List<String> lines = lyricsSource.getLyricsLines(song);
        System.out.println("🎤 Tryb KARAOKE (tło: " + backgroundColor + ")");
        System.out.println("   Źródło: " + lyricsSource.getSourceType());

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            System.out.println("   ⚫ " + line);

            if (showTranslation && i % 2 == 0) {
                System.out.println("   🔵 [Tłumaczenie: " + line.toUpperCase() + "]");
            }
        }
    }

    @Override
    public void highlightLine(int lineNumber) {
        System.out.println("✨ Podświetlanie linii " + lineNumber + " w trybie karaoke");
    }

    @Override
    public String getDisplayInfo() {
        return "Karaoke Style (tło: " + backgroundColor +
                ", tłumaczenie: " + (showTranslation ? "tak" : "nie") + ")";
    }
}
// Koniec, Tydzień 3, Wzorzec Bridge 3