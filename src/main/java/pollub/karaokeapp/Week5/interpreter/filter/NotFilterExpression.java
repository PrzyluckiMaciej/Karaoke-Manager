package pollub.karaokeapp.Week5.interpreter.filter;

import pollub.karaokeapp.Week5.interpreter.SongFilterExpression;
import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 5, Wzorzec Interpreter 3 (cd.)
 * Nieterminalny – wyrażenie NOT negujące filtr.
 */
public class NotFilterExpression implements SongFilterExpression {

    private final SongFilterExpression expression;

    public NotFilterExpression(SongFilterExpression expression) {
        this.expression = expression;
    }

    @Override
    public boolean interpret(Song song) {
        return !expression.interpret(song);
    }

    @Override
    public String getExpressionDescription() {
        return "NOT(" + expression.getExpressionDescription() + ")";
    }
}
// Koniec, Tydzień 5, Wzorzec Interpreter 3 (cd.)