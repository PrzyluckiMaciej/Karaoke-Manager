package pollub.karaokeapp.Week6.template;

import pollub.karaokeapp.model.performance.Performance;

/**
 * Tydzień 6, Wzorzec Template Method 4
 * Konkretna implementacja - ocenianie legacy
 */
public class LegacyPerformanceEvaluator extends PerformanceTemplate {

    @Override
    protected int calculateBaseScore(Performance performance) {
        // Legacy: fixed 50 points
        return 50;
    }

    @Override
    protected int applyDifficultyMultiplier(int score, Performance performance) {
        // Legacy: +10 za każdy level trudności
        int difficulty = performance.getSong().getDifficulty();
        return score + (difficulty * 10);
    }

    @Override
    protected int applyBonus(int score, Performance performance) {
        // Legacy: +100 za duet, +0 za solo
        int participants = performance.getParticipants().size();
        if (participants > 1) {
            return score + 100;
        }
        return score;
    }
}
// Koniec, Tydzień 6, Wzorzec Template Method 4
