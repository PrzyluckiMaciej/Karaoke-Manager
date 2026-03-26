package pollub.karaokeapp.Week5.iterator.performance;

import pollub.karaokeapp.Week5.iterator.KaraokeIterator;
import pollub.karaokeapp.model.performance.Performance;

import java.util.List;

/**
 * Tydzień 5, Wzorzec Iterator 2
 * Iterator historii wykonań – przechodzi po liście Performance od najnowszego.
 */
public class PerformanceHistoryIterator implements KaraokeIterator<Performance> {

    private final List<Performance> performances;
    private int currentIndex;

    public PerformanceHistoryIterator(List<Performance> performances) {
        this.performances = performances;
        this.currentIndex = performances.size() - 1; // od końca (najnowsze)
    }

    @Override
    public boolean hasNext() {
        return currentIndex >= 0;
    }

    @Override
    public Performance next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException("Brak kolejnych wykonań");
        }
        return performances.get(currentIndex--);
    }

    @Override
    public void reset() {
        currentIndex = performances.size() - 1;
    }

    public int getRemainingCount() {
        return currentIndex + 1;
    }
}
// Koniec, Tydzień 5, Wzorzec Iterator 2