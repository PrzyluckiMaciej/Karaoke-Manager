package pollub.karaokeapp.factory.performance;

import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;

import java.util.List;

/**
 * Tydzień 2, Wzorzec Factory 3 (dalsza część)
 * Klasa potomna klasy abstrakcyjnej z Factory Method
 * dla wystąpienia solo (1 uczestnik)
 */
public class SoloPerformanceFactory extends PerformanceFactory {

    @Override
    public Performance createPerformance(Song song, List<User> participants) {

        if (participants.size() != 1) {
            throw new IllegalArgumentException("SoloPerformance wymaga dokładnie 1 uczestnika!");
        }

        return new Performance(song, participants, 0);
    }
}
// Koniec, Tydzień 2, Wzorzec Factory 3