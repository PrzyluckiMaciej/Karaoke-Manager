package pollub.karaokeapp.Week5.interpreter.scoring;

/**
 * Tydzień 5, Wzorzec Interpreter 4 (cd.)
 * Nieterminalny – mnożenie wyrażenia punktowego przez współczynnik trudności.
 */
public class MultiplyScoreExpression implements ScoreExpression {

    private final ScoreExpression expression;
    private final double multiplier;
    private final String multiplierLabel;

    public MultiplyScoreExpression(ScoreExpression expression, double multiplier, String multiplierLabel) {
        this.expression = expression;
        this.multiplier = multiplier;
        this.multiplierLabel = multiplierLabel;
    }

    @Override
    public int interpret() {
        return (int) (expression.interpret() * multiplier);
    }

    @Override
    public String getExpressionDescription() {
        return "(" + expression.getExpressionDescription() + " * " + multiplierLabel + "[" + multiplier + "])";
    }
}
// Koniec, Tydzień 5, Wzorzec Interpreter 4 (cd.)