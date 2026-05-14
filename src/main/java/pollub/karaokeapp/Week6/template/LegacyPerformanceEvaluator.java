package pollub.karaokeapp.Week6.template;

import pollub.karaokeapp.model.performance.Performance;

/**
 * Tydzień 6, Wzorzec Template Method 4
 * Konkretna implementacja - ocenianie legacy
 */
public class LegacyPerformanceEvaluator extends PerformanceTemplate {

    private static final int FIXED_BASE_SCORE = 50;
    private static final int DIFFICULTY_BONUS_PER_LEVEL = 10;
    private static final int DUET_BONUS = 100;
    private static final int MIN_PARTICIPANTS_FOR_DUET = 1;

    @Override
    protected int calculateBaseScore(Performance performance) {
        return FIXED_BASE_SCORE;
    }

    @Override
    protected int applyDifficultyMultiplier(int score, Performance performance) {
        int difficulty = performance.getSong().getDifficulty();
        return score + (difficulty * DIFFICULTY_BONUS_PER_LEVEL);
    }

    @Override
    protected int applyBonus(int score, Performance performance) {
        int participants = performance.getParticipants().size();
        if (participants > MIN_PARTICIPANTS_FOR_DUET) {
            return score + DUET_BONUS;
        }
        return score;
    }
}