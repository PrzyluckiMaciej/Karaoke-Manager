package pollub.karaokeapp.Week3.bridge.lyrics;

import pollub.karaokeapp.Week3.bridge.constants.BridgeConstants;
import pollub.karaokeapp.model.song.Song;
import java.util.List;

/**
 * Tydzień 3, Wzorzec Bridge 3
 * Konkretna implementacja - przewijane wyświetlanie tekstów
 */
public class ScrollingLyricsDisplay extends LyricsDisplay {

    private final int scrollSpeed;
    private int currentLine;

    public ScrollingLyricsDisplay(LyricsSource lyricsSource, int scrollSpeed) {
        super(lyricsSource);
        this.scrollSpeed = scrollSpeed;
        this.currentLine = 0;
    }

    @Override
    public void showLyrics(Song song) {
        List<String> lines = getLyricsLines(song);

        printHeader("📜", "Przewijane teksty (szybkość: " + scrollSpeed + ")", lyricsSource.getSourceType());
        System.out.println("   Linii: " + lines.size());

        int linesToShow = Math.min(BridgeConstants.PREVIEW_MAX_LINES, lines.size());
        for (int i = 0; i < linesToShow; i++) {
            printLine("", lines.get(i), i == currentLine);
        }
    }

    @Override
    public void highlightLine(int lineNumber) {
        this.currentLine = lineNumber;
        System.out.println("🎯 Podświetlono linię " + lineNumber);
    }

    @Override
    public String getDisplayInfo() {
        return "Przewijane teksty (szybkość: " + scrollSpeed +
                ", źródło: " + lyricsSource.getSourceType() +
                ", tłumaczenie: " + (lyricsSource.hasTranslation() ? "tak" : "nie") + ")";
    }
}
// Koniec, Tydzień 3, Wzorzec Bridge 3