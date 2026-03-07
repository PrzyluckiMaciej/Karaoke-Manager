package pollub.karaokeapp.factory.performance;

import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;

import java.util.List;

/**
 * Tydzień 2, Wzorzec Factory 3 (dalsza część)
 * Klasa potomna klasy abstrakcyjnej z Factory Method
 * dla wystąpienia typu duet (2 uczestników)
 */
public class DuetPerformanceFactory extends PerformanceFactory {

    @Override
    public Performance createPerformance(Song song, List<User> participants) {

        if (participants.size() != 2) {
            throw new IllegalArgumentException("DuetPerformance wymaga dokładnie 2 uczestników!");
        }

        return new Performance(song, participants, 0);
    }
}
// Koniec, Tydzień 2, Wzorzec Factory 3