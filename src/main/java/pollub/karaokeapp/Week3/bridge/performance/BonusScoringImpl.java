package pollub.karaokeapp.Week3.bridge.performance;

/**
 * Tydzień 3, Wzorzec Bridge 1
 * Konkretna implementacja - bonusowe naliczanie
 */
public class BonusScoringImpl implements ScoringImplementation {

    @Override
    public int calculateScore(int baseScore, int performanceComplexity) {
        return baseScore + performanceComplexity * 5 + 50;
    }

    @Override
    public String getScoringName() {
        return "Bonus";
    }
}
// Koniec, Tydzień 3, Wzorzec Bridge 1