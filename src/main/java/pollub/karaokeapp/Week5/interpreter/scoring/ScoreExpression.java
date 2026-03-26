package pollub.karaokeapp.Week5.interpreter.scoring;

/**
 * Tydzień 5, Wzorzec Interpreter 4
 * Interfejs wyrażeń dla prostego języka reguł punktacyjnych.
 * Gramatyka: SCORE ::= NUMBER | SCORE '+' SCORE | SCORE '*' NUMBER | 'BONUS' '(' SCORE ')'
 */
public interface ScoreExpression {
    int interpret();
    String getExpressionDescription();
}
// Koniec, Tydzień 5, Wzorzec Interpreter 4