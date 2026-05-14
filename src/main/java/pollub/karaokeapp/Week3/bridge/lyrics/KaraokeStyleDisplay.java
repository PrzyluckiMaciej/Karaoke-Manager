package pollub.karaokeapp.Week3.bridge.lyrics;

import pollub.karaokeapp.Week3.bridge.constants.BridgeConstants;
import pollub.karaokeapp.model.song.Song;
import java.util.List;

/**
 * Tydzień 3, Wzorzec Bridge 3
 * Konkretna implementacja - styl karaoke z podświetlaniem sylab
 */
// Poprawiony KaraokeStyleDisplay
public class KaraokeStyleDisplay extends LyricsDisplay {

    private final boolean showTranslation;
    private final String backgroundColor;

    public KaraokeStyleDisplay(LyricsSource lyricsSource, boolean showTranslation, String backgroundColor) {
        super(lyricsSource);
        this.showTranslation = showTranslation && lyricsSource.hasTranslation();
        this.backgroundColor = backgroundColor;
    }

    @Override
    public void showLyrics(Song song) {
        printHeader("🎤", "Tryb KARAOKE (tło: " + backgroundColor + ")", lyricsSource.getSourceType());

        List<String> lines = getLyricsLines(song);
        for (int i = 0; i < lines.size(); i++) {
            printLine("⚫", lines.get(i), false);
            displayTranslationIfNeeded(i, lines.get(i));
        }
    }

    private void displayTranslationIfNeeded(int lineIndex, String originalLine) {
        if (showTranslation && shouldShowTranslation(lineIndex)) {
            printLine("🔵", "[Tłumaczenie: " + originalLine.toUpperCase() + "]", false);
        }
    }

    private boolean shouldShowTranslation(int lineIndex) {
        return lineIndex % BridgeConstants.TRANSLATION_LINE_MODULO == 0;
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