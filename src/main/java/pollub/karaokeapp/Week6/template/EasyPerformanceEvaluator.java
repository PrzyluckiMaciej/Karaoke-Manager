package pollub.karaokeapp.Week6.template;

import pollub.karaokeapp.model.performance.Performance;

/**
 * Tydzień 6, Wzorzec Template Method 3
 * Konkretna implementacja - ocenianie easy
 */
public class EasyPerformanceEvaluator extends PerformanceTemplate {

    @Override
    protected int calculateBaseScore(Performance performance) {
        // Easy ocenianie: 100% bazowa punktacja
        return performance.getScore();
    }

    @Override
    protected int applyDifficultyMultiplier(int score, Performance performance) {
        // Easy: x1.0 (bez mnożnika)
        return score;
    }

    @Override
    protected int applyBonus(int score, Performance performance) {
        // Easy: +20 za każdego uczestnika
        int participantBonus = performance.getParticipants().size() * 20;
        return score + participantBonus;
    }
}
// Koniec, Tydzień 6, Wzorzec Template Method 3
