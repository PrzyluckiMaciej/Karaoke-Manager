package pollub.karaokeapp.Week5.interpreter.filter.logic;

import pollub.karaokeapp.Week5.interpreter.filter.SongFilterExpression;
import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 5, Wzorzec Interpreter 2
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
// Koniec, Tydzień 5, Wzorzec Interpreter 2