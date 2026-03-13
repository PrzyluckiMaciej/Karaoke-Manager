package pollub.karaokeapp.Week3.bridge.performance;

/**
 * Tydzień 3, Wzorzec Bridge 1
 * Konkretna implementacja - standardowe naliczanie
 */
public class StandardScoringImpl implements ScoringImplementation {

    @Override
    public int calculateScore(int baseScore, int performanceComplexity) {
        return baseScore + performanceComplexity * 2;
    }

    @Override
    public String getScoringName() {
        return "Standard";
    }
}
// Koniec, Tydzień 3, Wzorzec Bridge 1