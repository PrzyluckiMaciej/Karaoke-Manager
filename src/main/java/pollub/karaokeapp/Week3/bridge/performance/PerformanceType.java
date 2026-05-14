package pollub.karaokeapp.Week3.bridge.performance;

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

    protected void validateParticipantCount(List<User> participants, int expected, String typeName) {
        if (participants.size() != expected) {
            throw new IllegalArgumentException(
                    typeName + " wymaga " + expected + " uczestnika/uczestników!"
            );
        }
    }

    protected int calculateAverageLevel(List<User> participants) {
        return participants.stream()
                .mapToInt(User::getLevel)
                .sum() / participants.size();
    }

    protected abstract int getBaseScore();
    protected abstract int getExpectedParticipants();
}
// Koniec, Tydzień 3, Wzorzec Bridge 1