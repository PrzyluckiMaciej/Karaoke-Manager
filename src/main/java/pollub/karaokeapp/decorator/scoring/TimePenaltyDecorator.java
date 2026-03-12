package pollub.karaokeapp.decorator.scoring;

import pollub.karaokeapp.service.scoring.ScoringStrategy;

/**
 * Tydzień 3, Wzorzec Decorator 4
 * Dekorator nakładający karę czasową
 */
public class TimePenaltyDecorator extends ScoringDecorator {

    private int maxDurationSeconds;
    private int actualDurationSeconds;
    private int penaltyPerSecond;

    public TimePenaltyDecorator(ScoringStrategy decoratedStrategy,
                                int maxDurationSeconds,
                                int actualDurationSeconds,
                                int penaltyPerSecond) {
        super(decoratedStrategy);
        this.maxDurationSeconds = maxDurationSeconds;
        this.actualDurationSeconds = actualDurationSeconds;
        this.penaltyPerSecond = penaltyPerSecond;
    }

    @Override
    public int calculateScore(int baseScore) {
        int score = super.calculateScore(baseScore);

        if (actualDurationSeconds > maxDurationSeconds) {
            int overtime = actualDurationSeconds - maxDurationSeconds;
            int penalty = overtime * penaltyPerSecond;
            System.out.println("   - Kara czasowa: " + penalty + " pkt (przekroczono o " + overtime + "s)");
            return score - penalty;
        }

        return score;
    }
}