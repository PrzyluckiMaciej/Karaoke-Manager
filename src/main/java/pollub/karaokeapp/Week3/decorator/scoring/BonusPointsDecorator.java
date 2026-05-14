package pollub.karaokeapp.Week3.decorator.scoring;

import pollub.karaokeapp.service.scoring.ScoringStrategy;

/**
 * Tydzień 3, Wzorzec Decorator 4
 * Dekorator dodający stały bonus do punktów
 */
public class BonusPointsDecorator extends ScoringDecorator {

    private int bonusPoints;
    private String bonusReason;

    public BonusPointsDecorator(ScoringStrategy decoratedStrategy, int bonusPoints, String bonusReason) {
        super(decoratedStrategy);
        validateBonusParameters(bonusPoints, bonusReason);
        this.bonusPoints = bonusPoints;
        this.bonusReason = bonusReason;
    }

    private void validateBonusParameters(int points, String reason) {
        if (reason == null || reason.trim().isEmpty()) {
            throw new IllegalArgumentException("Bonus reason cannot be null or empty");
        }
    }

    @Override
    public int calculateScore(int baseScore) {
        int score = super.calculateScore(baseScore);
        logBonus();
        return score + bonusPoints;
    }

    private void logBonus() {
        System.out.println("   + Bonus " + bonusPoints + " pkt (" + bonusReason + ")");
    }
}
// Koniec, Tydzień 3, Wzorzec Decorator 4