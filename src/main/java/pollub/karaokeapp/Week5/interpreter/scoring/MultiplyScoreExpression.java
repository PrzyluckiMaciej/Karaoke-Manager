package pollub.karaokeapp.Week5.interpreter.scoring;

/**
 * Tydzień 5, Wzorzec Interpreter 3
 * Nieterminalny – mnożenie wyrażenia punktowego przez współczynnik trudności.
 */
public class MultiplyScoreExpression implements ScoreExpression {

    public static final double MIN_MULTIPLIER = 0.1;
    public static final double MAX_MULTIPLIER = 10.0;

    private final ScoreExpression expression;
    private final double multiplier;
    private final String multiplierLabel;

    public MultiplyScoreExpression(ScoreExpression expression, double multiplier, String multiplierLabel) {
        if (expression == null) {
            throw new IllegalArgumentException("Wyrażenie punktowe nie może być null");
        }
        if (multiplier < MIN_MULTIPLIER || multiplier > MAX_MULTIPLIER) {
            throw new IllegalArgumentException(
                    "Mnożnik musi być między " + MIN_MULTIPLIER + " a " + MAX_MULTIPLIER +
                            ". Podano: " + multiplier
            );
        }
        if (multiplierLabel == null || multiplierLabel.trim().isEmpty()) {
            throw new IllegalArgumentException("Etykieta mnożnika nie może być pusta");
        }
        this.expression = expression;
        this.multiplier = multiplier;
        this.multiplierLabel = multiplierLabel;
    }

    @Override
    public int interpret() {
        double result = expression.interpret() * multiplier;

        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            throw new ArithmeticException(
                    "Wynik mnożenia poza zakresem int: " + result
            );
        }
        return (int) result;
    }

    @Override
    public String getExpressionDescription() {
        return "(" + expression.getExpressionDescription() + " * " + multiplierLabel + "[" + multiplier + "])";
    }
}