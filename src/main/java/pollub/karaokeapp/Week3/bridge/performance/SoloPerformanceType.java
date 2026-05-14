package pollub.karaokeapp.Week3.bridge.performance;

import pollub.karaokeapp.Week3.bridge.constants.BridgeConstants;
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
        validateParticipantCount(participants, getExpectedParticipants(), "Solo");

        Performance performance = new Performance(song, participants, 0);
        int complexity = song.getDifficulty() * participants.get(0).getLevel();
        int score = scoringImplementation.calculateScore(getBaseScore(), complexity);
        performance.setScore(score);

        return performance;
    }

    @Override
    public String getTypeDescription() {
        return "Występ solowy z systemem oceny: " + scoringImplementation.getScoringName();
    }

    @Override
    protected int getBaseScore() {
        return BridgeConstants.SOLO_BASE_SCORE;
    }

    @Override
    protected int getExpectedParticipants() {
        return BridgeConstants.SOLO_PARTICIPANTS;
    }
}
// Koniec, Tydzień 3, Wzorzec Bridge 1