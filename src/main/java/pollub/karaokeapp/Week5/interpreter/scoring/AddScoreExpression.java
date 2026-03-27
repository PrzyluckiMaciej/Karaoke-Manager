package pollub.karaokeapp.Week5.interpreter.scoring;

/**
 * Tydzień 5, Wzorzec Interpreter 3
 * Nieterminalny – suma dwóch wyrażeń punktowych.
 */
public class AddScoreExpression implements ScoreExpression {

    private final ScoreExpression left;
    private final ScoreExpression right;

    public AddScoreExpression(ScoreExpression left, ScoreExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret() + right.interpret();
    }

    @Override
    public String getExpressionDescription() {
        return "(" + left.getExpressionDescription() + " + " + right.getExpressionDescription() + ")";
    }
}
// Koniec, Tydzień 5, Wzorzec Interpreter 3