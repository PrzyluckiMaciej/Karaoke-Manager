package pollub.karaokeapp.Week5.interpreter.filter;

import pollub.karaokeapp.Week5.interpreter.SongFilterExpression;
import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 5, Wzorzec Interpreter 2
 * Terminal – filtruje piosenki po gatunku.
 */
public class GenreFilterExpression implements SongFilterExpression {

    private final String genre;

    public GenreFilterExpression(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean interpret(Song song) {
        return song.getGenre().equalsIgnoreCase(genre);
    }

    @Override
    public String getExpressionDescription() {
        return "gatunek='" + genre + "'";
    }
}
// Koniec, Tydzień 5, Wzorzec Interpreter 2