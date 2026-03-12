package pollub.karaokeapp.decorator.scoring;

import pollub.karaokeapp.service.scoring.ScoringStrategy;

/**
 * Tydzień 3, Wzorzec Decorator 4
 * Bazowa klasa dekoratora dla strategii punktacji
 */
public abstract class ScoringDecorator implements ScoringStrategy {

    protected ScoringStrategy decoratedStrategy;

    public ScoringDecorator(ScoringStrategy decoratedStrategy) {
        this.decoratedStrategy = decoratedStrategy;
    }

    @Override
    public int calculateScore(int baseScore) {
        return decoratedStrategy.calculateScore(baseScore);
    }
}
// Koniec, Tydzień 3, Wzorzec Decorator 4