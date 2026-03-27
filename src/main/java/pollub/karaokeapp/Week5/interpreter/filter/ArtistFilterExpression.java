package pollub.karaokeapp.Week5.interpreter.filter;

import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 5, Wzorzec Interpreter 1
 * Terminal – filtruje piosenki po artyście.
 */
public class ArtistFilterExpression implements SongFilterExpression {

    private final String artist;

    public ArtistFilterExpression(String artist) {
        this.artist = artist;
    }

    @Override
    public boolean interpret(Song song) {
        return song.getArtist().equalsIgnoreCase(artist);
    }

    @Override
    public String getExpressionDescription() {
        return "artysta='" + artist + "'";
    }
}
// Koniec, Tydzień 5, Wzorzec Interpreter 1