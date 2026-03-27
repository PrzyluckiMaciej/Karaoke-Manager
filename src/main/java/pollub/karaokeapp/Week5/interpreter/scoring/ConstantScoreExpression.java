package pollub.karaokeapp.Week5.interpreter.scoring;

/**
 * Tydzień 5, Wzorzec Interpreter 3
 * Terminal – stała wartość punktowa.
 */
public class ConstantScoreExpression implements ScoreExpression {

    private final int value;
    private final String label;

    public ConstantScoreExpression(int value, String label) {
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
// Koniec, Tydzień 5, Wzorzec Interpreter 3