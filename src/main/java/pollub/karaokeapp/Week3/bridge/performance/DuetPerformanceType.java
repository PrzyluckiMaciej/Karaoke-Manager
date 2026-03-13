package pollub.karaokeapp.Week3.bridge.performance;

import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;
import java.util.List;

/**
 * Tydzień 3, Wzorzec Bridge 1
 * Konkretna implementacja - występ w duecie
 */
public class DuetPerformanceType extends PerformanceType {

    public DuetPerformanceType(ScoringImplementation scoringImplementation) {
        super(scoringImplementation);
    }

    @Override
    public Performance createPerformance(Song song, List<User> participants) {
        if (participants.size() != 2) {
            throw new IllegalArgumentException("Duet wymaga 2 uczestników!");
        }

        Performance performance = new Performance(song, participants, 0);
        int complexity = song.getDifficulty() *
                (participants.get(0).getLevel() + participants.get(1).getLevel()) / 2;
        int score = scoringImplementation.calculateScore(120, complexity); // duet ma wyższy base
        performance.setScore(score);

        return performance;
    }

    @Override
    public String getTypeDescription() {
        return "Występ w duecie z systemem oceny: " + scoringImplementation.getScoringName();
    }
}
// Koniec, Tydzień 3, Wzorzec Bridge 1