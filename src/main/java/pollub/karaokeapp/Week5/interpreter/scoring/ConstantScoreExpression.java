package pollub.karaokeapp.Week5.interpreter.scoring;

/**
 * Tydzień 5, Wzorzec Interpreter 3
 * Terminal – stała wartość punktowa.
 */
public class ConstantScoreExpression implements ScoreExpression {

    private final int value;
    private final String label;

    public ConstantScoreExpression(int value, String label) {
        if (label == null || label.trim().isEmpty()) {
            throw new IllegalArgumentException("Etykieta nie może być pusta");
        }
        this.value = value;
        this.label = label;
    }

    @Override
    public int interpret() {
        return value;
    }

    @Override
    public String getExpressionDescription() {
        return label + "(" + value + ")";
    }
}