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
        if (expression == null) {
            throw new IllegalArgumentException("Wyrażenie filtrujące nie może być null");
        }
        this.expression = expression;
    }

    @Override
    public boolean interpret(Song song) {
        if (song == null) {
            throw new IllegalArgumentException("Piosenka nie może być null");
        }
        return !expression.interpret(song);
    }

    @Override
    public String getExpressionDescription() {
        return "NOT(" + expression.getExpressionDescription() + ")";
    }
}