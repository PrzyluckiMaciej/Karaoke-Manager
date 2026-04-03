package pollub.karaokeapp.Week6.template;

import pollub.karaokeapp.model.performance.Performance;

/**
 * Tydzień 6, Wzorzec Template Method 4
 * Konkretna implementacja - ocenianie przez publiczność
 */
public class AudiencePerformanceEvaluator extends PerformanceTemplate {

    @Override
    protected int calculateBaseScore(Performance performance) {
        // Audience scoring: 70% bazowa punktacja
        return (int)(performance.getScore() * 0.7);
    }

    @Override
    protected int applyDifficultyMultiplier(int score, Performance performance) {
        // Audience: x1.2 za trudność (mniej karny niż Pro)
        int difficulty = performance.getSong().getDifficulty();
        return (int)(score * (1.0 + difficulty * 0.05));
    }

    @Override
    protected int applyBonus(int score, Performance performance) {
        // Audience: +30 za każdego uczestnika + bonus za duet
        int participantBonus = performance.getParticipants().size() * 30;
        int duetBonus = performance.getParticipants().size() > 1 ? 75 : 0;
        return score + participantBonus + duetBonus;
    }
}
// Koniec, Tydzień 6, Wzorzec Template Method 4
