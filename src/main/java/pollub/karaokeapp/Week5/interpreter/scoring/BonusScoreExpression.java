package pollub.karaokeapp.Week5.interpreter.scoring;

/**
 * Tydzień 5, Wzorzec Interpreter 3
 * Nieterminalny – dodaje stały bonus do wyrażenia punktowego.
 */
public class BonusScoreExpression implements ScoreExpression {

    public static final int MIN_BONUS_VALUE = 1;

    private final ScoreExpression expression;
    private final int bonusValue;
    private final String bonusReason;

    public BonusScoreExpression(ScoreExpression expression, int bonusValue, String bonusReason) {
        if (expression == null) {
            throw new IllegalArgumentException("Wyrażenie punktowe nie może być null");
        }
        if (bonusValue < MIN_BONUS_VALUE) {
            throw new IllegalArgumentException(
                    "Bonus musi być dodatni (min. " + MIN_BONUS_VALUE + "). Podano: " + bonusValue
            );
        }
        if (bonusReason == null || bonusReason.trim().isEmpty()) {
            throw new IllegalArgumentException("Powód bonusa nie może być pusty");
        }
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