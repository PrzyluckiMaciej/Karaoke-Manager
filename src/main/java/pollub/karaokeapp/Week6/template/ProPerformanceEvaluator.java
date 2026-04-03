package pollub.karaokeapp.Week6.template;

import pollub.karaokeapp.model.performance.Performance;

/**
 * Tydzień 6, Wzorzec Template Method 2
 * Konkretna implementacja - ocenianie pro
 */
public class ProPerformanceEvaluator extends PerformanceTemplate {

    @Override
    protected int calculateBaseScore(Performance performance) {
        // Pro ocenianie: 80% bazowa punktacja
        return (int)(performance.getScore() * 0.8);
    }

    @Override
    protected int applyDifficultyMultiplier(int score, Performance performance) {
        // Pro: x1.5 za trudność
        int difficulty = performance.getSong().getDifficulty();
        return (int)(score * (1.0 + difficulty * 0.1));
    }

    @Override
    protected int applyBonus(int score, Performance performance) {
        // Pro: +50 za każdego uczestnika
        int participantBonus = performance.getParticipants().size() * 50;
        return score + participantBonus;
    }
}
// Koniec, Tydzień 6, Wzorzec Template Method 2
