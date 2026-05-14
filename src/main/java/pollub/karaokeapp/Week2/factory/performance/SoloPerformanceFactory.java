package pollub.karaokeapp.Week2.factory.performance;

import pollub.karaokeapp.Week2.KaraokeConstants;
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

        if (participants.size() != KaraokeConstants.SOLO_PERFORMANCE_PARTICIPANTS) {
            throw new IllegalArgumentException("SoloPerformance wymaga dokładnie 1 uczestnika!");
        }

        return new Performance(song, participants, KaraokeConstants.USER_DEFAULT_POINTS);
    }
}
// Koniec, Tydzień 2, Wzorzec Factory 3