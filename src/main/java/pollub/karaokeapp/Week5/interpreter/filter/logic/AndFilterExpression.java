package pollub.karaokeapp.Week5.interpreter.filter.logic;

import pollub.karaokeapp.Week5.interpreter.filter.SongFilterExpression;
import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 5, Wzorzec Interpreter 2
 * Nieterminalny – wyrażenie AND łączące dwa filtry.
 */
public class AndFilterExpression implements SongFilterExpression {

    private final SongFilterExpression left;
    private final SongFilterExpression right;

    public AndFilterExpression(SongFilterExpression left, SongFilterExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean interpret(Song song) {
        return left.interpret(song) && right.interpret(song);
    }

    @Override
    public String getExpressionDescription() {
        return "(" + left.getExpressionDescription() + " AND " + right.getExpressionDescription() + ")";
    }
}
// Koniec, Tydzień 5, Wzorzec Interpreter 2