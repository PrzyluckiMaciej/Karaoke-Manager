package pollub.karaokeapp.Week3.bridge.lyrics;

import pollub.karaokeapp.model.song.Song;

import java.util.List;

/**
 * Tydzień 3, Wzorzec Bridge 3
 * Abstrakcja - sposób wyświetlania tekstów
 */
public abstract class LyricsDisplay {

    protected LyricsSource lyricsSource;

    public LyricsDisplay(LyricsSource lyricsSource) {
        this.lyricsSource = lyricsSource;
    }

    public abstract void showLyrics(Song song);
    public abstract void highlightLine(int lineNumber);
    public abstract String getDisplayInfo();

    protected void printHeader(String emoji, String title, String sourceInfo) {
        System.out.println(emoji + " " + title);
        System.out.println("   Źródło: " + sourceInfo);
    }

    protected void printLine(String prefix, String line, boolean isHighlighted) {
        String marker = isHighlighted ? "→" : " ";
        System.out.println("   " + marker + " " + prefix + " " + line);
    }

    protected List<String> getLyricsLines(Song song) {
        return lyricsSource.getLyricsLines(song);
    }
}
// Koniec, Tydzień 3, Wzorzec Bridge 3