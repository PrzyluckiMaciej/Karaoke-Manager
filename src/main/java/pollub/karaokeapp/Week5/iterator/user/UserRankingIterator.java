package pollub.karaokeapp.Week5.iterator.user;

import pollub.karaokeapp.Week5.iterator.AbstractKaraokeIterator;
import pollub.karaokeapp.Week5.iterator.KaraokeIterator;
import pollub.karaokeapp.Week5.iterator.KaraokeIteratorException;
import pollub.karaokeapp.model.user.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Tydzień 5, Wzorzec Iterator 4
 * Iterator użytkowników posortowanych malejąco po poziomie (rankingowy).
 */
public class UserRankingIterator extends AbstractKaraokeIterator<User> {
    private static final int FIRST_RANK = 1;

    public UserRankingIterator(List<User> users) {
        super(createRankedCopy(users));
    }

    private static List<User> createRankedCopy(List<User> users) {
        List<User> copy = new ArrayList<>(users);
        copy.sort(Comparator.comparingInt(User::getPoints).reversed());
        return copy;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < items.size();
    }

    @Override
    protected String getNoElementsMessage() {
        return "Brak kolejnych użytkowników w rankingu";
    }

    public int getLastRetrievedRank() {
        return currentIndex + FIRST_RANK;
    }

    public User getTopUser() {
        if (items.isEmpty()) {
            throw new KaraokeIteratorException("Ranking jest pusty");
        }
        return items.get(0);
    }

    public boolean hasMinimumPoints(int minPoints) {
        return items.stream().anyMatch(user -> user.getPoints() >= minPoints);
    }
}
// Koniec, Tydzień 5, Wzorzec Iterator 4