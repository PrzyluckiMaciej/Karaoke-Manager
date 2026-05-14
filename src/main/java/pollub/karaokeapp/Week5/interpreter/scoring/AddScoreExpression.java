package pollub.karaokeapp.Week5.interpreter.scoring;

/**
 * Tydzień 5, Wzorzec Interpreter 3
 * Nieterminalny – suma dwóch wyrażeń punktowych.
 */
public class AddScoreExpression implements ScoreExpression {

    private final ScoreExpression left;
    private final ScoreExpression right;

    public AddScoreExpression(ScoreExpression left, ScoreExpression right) {
        if (left == null) {
            throw new IllegalArgumentException("Lewe wyrażenie punktowe nie może być null");
        }
        if (right == null) {
            throw new IllegalArgumentException("Prawe wyrażenie punktowe nie może być null");
        }
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        int leftValue = left.interpret();
        int rightValue = right.interpret();

        // Zabezpieczenie przed overflow
        if (leftValue > Integer.MAX_VALUE - rightValue) {
            throw new ArithmeticException(
                    "Przekroczono maksymalną wartość całkowitą: " + leftValue + " + " + rightValue
            );
        }
        return leftValue + rightValue;
    }

    @Override
    public String getExpressionDescription() {
        return "(" + left.getExpressionDescription() + " + " + right.getExpressionDescription() + ")";
    }
}