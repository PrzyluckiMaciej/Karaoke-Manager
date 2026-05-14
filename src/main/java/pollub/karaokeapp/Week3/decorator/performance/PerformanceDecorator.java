package pollub.karaokeapp.Week3.decorator.performance;

import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;
import java.util.List;

/**
 * Tydzień 3, Wzorzec Decorator 1
 * Bazowa klasa dekoratora dla występów
 */
public abstract class PerformanceDecorator extends Performance {

    protected Performance decoratedPerformance;

    public PerformanceDecorator(Performance decoratedPerformance) {
        super(decoratedPerformance.getSong(),
                decoratedPerformance.getParticipants(),
                decoratedPerformance.getScore());
        validateDecoratedPerformance(decoratedPerformance);
        this.decoratedPerformance = decoratedPerformance;
    }

    private void validateDecoratedPerformance(Performance performance) {
        if (performance == null) {
            throw new IllegalArgumentException("Decorated performance cannot be null");
        }
    }

    @Override
    public String toString() {
        return decoratedPerformance.toString();
    }

    @Override
    public Song getSong() {
        return decoratedPerformance.getSong();
    }

    @Override
    public List<User> getParticipants() {
        return decoratedPerformance.getParticipants();
    }

    @Override
    public int getScore() {
        return decoratedPerformance.getScore();
    }

    @Override
    public void setScore(int score) {
        decoratedPerformance.setScore(score);
    }
}
// Koniec, Tydzień 3, Wzorzec Decorator 1