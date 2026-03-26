package pollub.karaokeapp.Week5.iterator.performance;

import pollub.karaokeapp.Week5.iterator.KaraokeIterator;
import pollub.karaokeapp.model.performance.Performance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Tydzień 5, Wzorzec Iterator 2 (cd.)
 * Iterator rankingowy – iteruje wykonania posortowane malejąco wg wyniku.
 */
public class TopScorePerformanceIterator implements KaraokeIterator<Performance> {

    private final List<Performance> sortedPerformances;
    private int currentIndex = 0;

    public TopScorePerformanceIterator(List<Performance> performances) {
        this.sortedPerformances = new ArrayList<>(performances);
        this.sortedPerformances.sort(Comparator.comparingInt(Performance::getScore).reversed());
    }

    @Override
    public boolean hasNext() {
        return currentIndex < sortedPerformances.size();
    }

    @Override
    public Performance next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException("Brak kolejnych wykonań w rankingu");
        }
        return sortedPerformances.get(currentIndex++);
    }

    @Override
    public void reset() {
        currentIndex = 0;
    }

    public int getRank() {
        return currentIndex; // pozycja ostatnio pobranego elementu
    }
}
// Koniec, Tydzień 5, Wzorzec Iterator 2 (cd.)