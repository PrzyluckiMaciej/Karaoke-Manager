package pollub.karaokeapp.Week6.template;

import pollub.karaokeapp.model.performance.Performance;

/**
 * Tydzień 6, Wzorzec Template Method 1
 * Abstrakcyjny szablon dla procesu oceniania wykonania
 */
public abstract class PerformanceTemplate {

    public final int evaluatePerformance(Performance performance) {
        System.out.println("\n[TEMPLATE] Rozpoczęcie oceny: " + performance.getSong().getTitle());

        int baseScore = calculateBaseScore(performance);
        System.out.println("  1. Wynik bazowy: " + baseScore);

        int adjustedScore = applyDifficultyMultiplier(baseScore, performance);
        System.out.println("  2. Po mnożniku trudności: " + adjustedScore);

        int bonusScore = applyBonus(adjustedScore, performance);
        System.out.println("  3. Po bonusach: " + bonusScore);

        recordResult(performance, bonusScore);
        System.out.println("  ✓ Ocena zakończona\n");

        return bonusScore;
    }

    protected abstract int calculateBaseScore(Performance performance);
    protected abstract int applyDifficultyMultiplier(int score, Performance performance);
    protected abstract int applyBonus(int score, Performance performance);

    protected void recordResult(Performance performance, int finalScore) {
        System.out.println("  [RECORD] Zapisano wynik: " + finalScore);
    }
}
// Koniec, Tydzień 6, Wzorzec Template Method 1
