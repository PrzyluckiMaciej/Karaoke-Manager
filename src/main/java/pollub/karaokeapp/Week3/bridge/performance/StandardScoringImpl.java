package pollub.karaokeapp.Week3.bridge.performance;

import pollub.karaokeapp.Week3.bridge.constants.BridgeConstants;

/**
 * Tydzień 3, Wzorzec Bridge 1
 * Konkretna implementacja - standardowe naliczanie
 */
public class StandardScoringImpl implements ScoringImplementation {

    @Override
    public int calculateScore(int baseScore, int performanceComplexity) {
        return baseScore + performanceComplexity * BridgeConstants.STANDARD_SCORE_MULTIPLIER;
    }

    @Override
    public String getScoringName() {
        return "Standard";
    }
}
// Koniec, Tydzień 3, Wzorzec Bridge 1