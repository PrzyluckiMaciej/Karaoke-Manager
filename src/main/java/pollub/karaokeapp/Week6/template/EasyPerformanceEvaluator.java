package pollub.karaokeapp.Week6.template;

import pollub.karaokeapp.model.performance.Performance;

/**
 * Tydzień 6, Wzorzec Template Method 3
 * Konkretna implementacja - ocenianie easy
 */
public class EasyPerformanceEvaluator extends PerformanceTemplate {

    private static final int PARTICIPANT_BONUS = 20;

    @Override
    protected int calculateBaseScore(Performance performance) {
        return performance.getScore();
    }

    @Override
    protected int applyDifficultyMultiplier(int score, Performance performance) {
        return score;
    }

    @Override
    protected int applyBonus(int score, Performance performance) {
        int participantBonus = performance.getParticipants().size() * PARTICIPANT_BONUS;
        return score + participantBonus;
    }
}