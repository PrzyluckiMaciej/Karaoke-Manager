package pollub.karaokeapp.Week3.bridge.performance;

import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;
import java.util.List;

/**
 * Tydzień 3, Wzorzec Bridge 1
 * Konkretna implementacja - występ solowy
 */
public class SoloPerformanceType extends PerformanceType {

    public SoloPerformanceType(ScoringImplementation scoringImplementation) {
        super(scoringImplementation);
    }

    @Override
    public Performance createPerformance(Song song, List<User> participants) {
        if (participants.size() != 1) {
            throw new IllegalArgumentException("Solo wymaga 1 uczestnika!");
        }

        Performance performance = new Performance(song, participants, 0);
        int complexity = song.getDifficulty() * participants.get(0).getLevel();
        int score = scoringImplementation.calculateScore(100, complexity);
        performance.setScore(score);

        return performance;
    }

    @Override
    public String getTypeDescription() {
        return "Występ solowy z systemem oceny: " + scoringImplementation.getScoringName();
    }
}
// Koniec, Tydzień 3, Wzorzec Bridge 1