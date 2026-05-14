package pollub.karaokeapp.Week5.interpreter.filter;

import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 5, Wzorzec Interpreter 1
 * Terminal – filtruje piosenki po gatunku.
 */
public class GenreFilterExpression implements SongFilterExpression {

    private final String genre;

    public GenreFilterExpression(String genre) {
        if (genre == null || genre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nazwa gatunku nie może być pusta");
        }
        this.genre = genre;
    }

    @Override
    public boolean interpret(Song song) {
        if (song == null) {
            throw new IllegalArgumentException("Piosenka nie może być null");
        }
        String songGenre = song.getGenre();
        if (songGenre == null) {
            return false;
        }
        return songGenre.equalsIgnoreCase(genre);
    }

    @Override
    public String getExpressionDescription() {
        return "gatunek='" + genre + "'";
    }
}