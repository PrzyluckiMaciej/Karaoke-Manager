package pollub.karaokeapp.Week5.iterator.performance;

import pollub.karaokeapp.Week5.iterator.AbstractKaraokeIterator;
import pollub.karaokeapp.Week5.iterator.KaraokeIterator;
import pollub.karaokeapp.model.performance.Performance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Tydzień 5, Wzorzec Iterator 2 (cd.)
 * Iterator rankingowy – iteruje wykonania posortowane malejąco wg wyniku.
 */
public class TopScorePerformanceIterator extends AbstractKaraokeIterator<Performance> {
    private static final int FIRST_RANK = 1;

    public TopScorePerformanceIterator(List<Performance> performances) {
        super(createSortedCopy(performances));
    }

    private static List<Performance> createSortedCopy(List<Performance> performances) {
        List<Performance> copy = new ArrayList<>(performances);
        copy.sort(Comparator.comparingInt(Performance::getScore).reversed());
        return copy;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < items.size();
    }

    @Override
    protected String getNoElementsMessage() {
        return "Brak kolejnych wykonań w rankingu";
    }

    public int getLastRetrievedRank() {
        return currentIndex + FIRST_RANK;
    }

    public int getTotalCount() {
        return items.size();
    }
}
// Koniec, Tydzień 5, Wzorzec Iterator 2 (cd.)