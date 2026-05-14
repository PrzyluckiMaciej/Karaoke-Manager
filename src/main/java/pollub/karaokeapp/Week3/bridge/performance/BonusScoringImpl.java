package pollub.karaokeapp.Week3.bridge.performance;

import pollub.karaokeapp.Week3.bridge.constants.BridgeConstants;

/**
 * Tydzień 3, Wzorzec Bridge 1
 * Konkretna implementacja - bonusowe naliczanie
 */
public class BonusScoringImpl implements ScoringImplementation {

    @Override
    public int calculateScore(int baseScore, int performanceComplexity) {
        return baseScore + performanceComplexity * BridgeConstants.BONUS_SCORE_MULTIPLIER
                + BridgeConstants.BONUS_FLAT_ADDITION;
    }

    @Override
    public String getScoringName() {
        return "Bonus";
    }
}
// Koniec, Tydzień 3, Wzorzec Bridge 1