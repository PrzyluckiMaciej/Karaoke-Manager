package pollub.karaokeapp.Week6.template;

import pollub.karaokeapp.model.performance.Performance;

/**
 * Tydzień 6, Wzorzec Template Method 2
 * Konkretna implementacja - ocenianie pro
 */
public class ProPerformanceEvaluator extends PerformanceTemplate {

    private static final double BASE_SCORE_PERCENTAGE = 0.8;
    private static final double DIFFICULTY_MULTIPLIER_PER_LEVEL = 0.1;
    private static final int PARTICIPANT_BONUS = 50;

    @Override
    protected int calculateBaseScore(Performance performance) {
        return (int)(performance.getScore() * BASE_SCORE_PERCENTAGE);
    }

    @Override
    protected int applyDifficultyMultiplier(int score, Performance performance) {
        int difficulty = performance.getSong().getDifficulty();
        return (int)(score * (1.0 + difficulty * DIFFICULTY_MULTIPLIER_PER_LEVEL));
    }

    @Override
    protected int applyBonus(int score, Performance performance) {
        int participantBonus = performance.getParticipants().size() * PARTICIPANT_BONUS;
        return score + participantBonus;
    }
}