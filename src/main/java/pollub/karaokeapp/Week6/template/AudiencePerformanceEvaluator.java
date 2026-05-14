package pollub.karaokeapp.Week6.template;

import pollub.karaokeapp.model.performance.Performance;

/**
 * Tydzień 6, Wzorzec Template Method 4
 * Konkretna implementacja - ocenianie przez publiczność
 */
public class AudiencePerformanceEvaluator extends PerformanceTemplate {

    private static final double BASE_SCORE_PERCENTAGE = 0.7;
    private static final double DIFFICULTY_MULTIPLIER_PER_LEVEL = 0.05;
    private static final int PARTICIPANT_BONUS = 30;
    private static final int DUET_BONUS = 75;
    private static final int MIN_PARTICIPANTS_FOR_DUET = 1;

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
        int duetBonus = performance.getParticipants().size() > MIN_PARTICIPANTS_FOR_DUET ? DUET_BONUS : 0;
        return score + participantBonus + duetBonus;
    }
}