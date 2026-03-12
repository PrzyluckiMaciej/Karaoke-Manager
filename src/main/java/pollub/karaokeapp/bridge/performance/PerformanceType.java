package pollub.karaokeapp.bridge.performance;

import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;
import java.util.List;

/**
 * Tydzień 3, Wzorzec Bridge 1
 * Abstrakcja - typ występu
 */
public abstract class PerformanceType {

    protected ScoringImplementation scoringImplementation;

    public PerformanceType(ScoringImplementation scoringImplementation) {
        this.scoringImplementation = scoringImplementation;
    }

    public abstract Performance createPerformance(Song song, List<User> participants);
    public abstract String getTypeDescription();
}
// Koniec, Tydzień 3, Wzorzec Bridge 1