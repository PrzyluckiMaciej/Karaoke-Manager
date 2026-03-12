package pollub.karaokeapp.decorator.scoring;

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
        this.bonusPoints = bonusPoints;
        this.bonusReason = bonusReason;
    }

    @Override
    public int calculateScore(int baseScore) {
        int score = super.calculateScore(baseScore);
        System.out.println("   + Bonus " + bonusPoints + " pkt (" + bonusReason + ")");
        return score + bonusPoints;
    }
}