package pollub.karaokeapp.Week5.interpreter.filter.logic;

import pollub.karaokeapp.Week5.interpreter.filter.SongFilterExpression;
import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 5, Wzorzec Interpreter 2
 * Nieterminalny – wyrażenie OR łączące dwa filtry.
 */
public class OrFilterExpression implements SongFilterExpression {

    private final SongFilterExpression left;
    private final SongFilterExpression right;

    public OrFilterExpression(SongFilterExpression left, SongFilterExpression right) {
        if (left == null) {
            throw new IllegalArgumentException("Lewe wyrażenie filtrujące nie może być null");
        }
        if (right == null) {
            throw new IllegalArgumentException("Prawe wyrażenie filtrujące nie może być null");
        }
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean interpret(Song song) {
        if (song == null) {
            throw new IllegalArgumentException("Piosenka nie może być null");
        }
        return left.interpret(song) || right.interpret(song);
    }

    @Override
    public String getExpressionDescription() {
        return "(" + left.getExpressionDescription() + " OR " + right.getExpressionDescription() + ")";
    }
}