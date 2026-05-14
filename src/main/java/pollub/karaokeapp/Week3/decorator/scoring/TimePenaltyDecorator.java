package pollub.karaokeapp.Week3.decorator.scoring;

import pollub.karaokeapp.service.scoring.ScoringStrategy;

/**
 * Tydzień 3, Wzorzec Decorator 4
 * Dekorator nakładający karę czasową
 */
public class TimePenaltyDecorator extends ScoringDecorator {

    private TimePenaltyConfig config;

    public TimePenaltyDecorator(ScoringStrategy decoratedStrategy, TimePenaltyConfig config) {
        super(decoratedStrategy);
        validateTimePenaltyConfig(config);
        this.config = config;
    }

    private void validateTimePenaltyConfig(TimePenaltyConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("Time penalty config cannot be null");
        }
        config.validate();
    }

    @Override
    public int calculateScore(int baseScore) {
        int score = super.calculateScore(baseScore);
        return applyTimePenalty(score);
    }

    private int applyTimePenalty(int score) {
        if (!isTimeExceeded()) {
            return score;
        }
        return score - calculatePenalty();
    }

    private boolean isTimeExceeded() {
        return config.getActualDurationSeconds() > config.getMaxDurationSeconds();
    }

    private int calculatePenalty() {
        int overtime = config.getActualDurationSeconds() - config.getMaxDurationSeconds();
        int penalty = overtime * config.getPenaltyPerSecond();
        logPenalty(overtime, penalty);
        return penalty;
    }

    private void logPenalty(int overtime, int penalty) {
        System.out.println("   - Kara czasowa: " + penalty + " pkt (przekroczono o " + overtime + "s)");
    }
}
// Koniec, Tydzień 3, Wzorzec Decorator 4