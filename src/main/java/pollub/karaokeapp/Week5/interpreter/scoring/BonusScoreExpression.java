package pollub.karaokeapp.Week5.interpreter.scoring;

/**
 * Tydzień 5, Wzorzec Interpreter 4 (cd.)
 * Nieterminalny – dodaje stały bonus do wyrażenia punktowego.
 */
public class BonusScoreExpression implements ScoreExpression {

    private final ScoreExpression expression;
    private final int bonusValue;
    private final String bonusReason;

    public BonusScoreExpression(ScoreExpression expression, int bonusValue, String bonusReason) {
        this.expression = expression;
        this.bonusValue = bonusValue;
        this.bonusReason = bonusReason;
    }

    @Override
    public int interpret() {
        return expression.interpret() + bonusValue;
    }

    @Override
    public String getExpressionDescription() {
        return "BONUS[" + bonusReason + "+" + bonusValue + "](" + expression.getExpressionDescription() + ")";
    }
}
// Koniec, Tydzień 5, Wzorzec Interpreter 4 (cd.)