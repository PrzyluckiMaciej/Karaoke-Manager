package pollub.karaokeapp.Week3.decorator.scoring;

import pollub.karaokeapp.service.scoring.ScoringStrategy;

/**
 * Tydzień 3, Wzorzec Decorator 4
 * Bazowa klasa dekoratora dla strategii punktacji
 */
public abstract class ScoringDecorator implements ScoringStrategy {

    protected ScoringStrategy decoratedStrategy;

    public ScoringDecorator(ScoringStrategy decoratedStrategy) {
        validateDecoratedStrategy(decoratedStrategy);
        this.decoratedStrategy = decoratedStrategy;
    }

    private void validateDecoratedStrategy(ScoringStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Decorated scoring strategy cannot be null");
        }
    }

    @Override
    public int calculateScore(int baseScore) {
        return decoratedStrategy.calculateScore(baseScore);
    }
}
// Koniec, Tydzień 3, Wzorzec Decorator 4