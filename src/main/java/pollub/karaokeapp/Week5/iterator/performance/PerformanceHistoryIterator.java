package pollub.karaokeapp.Week5.iterator.performance;

import pollub.karaokeapp.Week5.iterator.AbstractKaraokeIterator;
import pollub.karaokeapp.Week5.iterator.KaraokeIterator;
import pollub.karaokeapp.model.performance.Performance;

import java.util.List;

/**
 * Tydzień 5, Wzorzec Iterator 2
 * Iterator historii wykonań – przechodzi po liście Performance od najnowszego.
 */
public class PerformanceHistoryIterator extends AbstractKaraokeIterator<Performance> {
    private static final int START_FROM_END_OFFSET = 1;
    private static final int EMPTY_LIST_INDEX = -1;

    public PerformanceHistoryIterator(List<Performance> performances) {
        super(performances);
        this.currentIndex = performances.size() - START_FROM_END_OFFSET;
    }

    @Override
    public boolean hasNext() {
        return currentIndex >= 0;
    }

    @Override
    protected String getNoElementsMessage() {
        return "Brak kolejnych wykonań w historii";
    }

    @Override
    public void reset() {
        currentIndex = items.size() - START_FROM_END_OFFSET;
    }

    public int getRemainingCount() {
        return currentIndex + START_FROM_END_OFFSET;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
// Koniec, Tydzień 5, Wzorzec Iterator 2