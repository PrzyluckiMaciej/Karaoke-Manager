package pollub.karaokeapp.Week5.iterator.user;

import pollub.karaokeapp.Week5.iterator.KaraokeIterator;
import pollub.karaokeapp.model.user.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Tydzień 5, Wzorzec Iterator 4
 * Iterator użytkowników posortowanych malejąco po poziomie (rankingowy).
 */
public class UserRankingIterator implements KaraokeIterator<User> {

    private final List<User> sortedUsers;
    private int currentIndex = 0;

    public UserRankingIterator(List<User> users) {
        this.sortedUsers = new ArrayList<>(users);
        this.sortedUsers.sort(Comparator.comparingInt(User::getPoints).reversed());
    }

    @Override
    public boolean hasNext() {
        return currentIndex < sortedUsers.size();
    }

    @Override
    public User next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException("Brak kolejnych użytkowników w rankingu");
        }
        return sortedUsers.get(currentIndex++);
    }

    @Override
    public void reset() {
        currentIndex = 0;
    }

    public int getCurrentRank() {
        return currentIndex;
    }
}
// Koniec, Tydzień 5, Wzorzec Iterator 4