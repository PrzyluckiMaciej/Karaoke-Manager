package pollub.karaokeapp.Week3.bridge.lyrics;

import pollub.karaokeapp.model.song.Song;

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
}
// Koniec, Tydzień 3, Wzorzec Bridge 3