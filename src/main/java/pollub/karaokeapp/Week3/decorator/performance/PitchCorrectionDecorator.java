package pollub.karaokeapp.Week3.decorator.performance;

import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.user.User;
import java.util.List;

/**
 * Tydzień 3, Wzorzec Decorator 1
 * Dekorator dodający korekcję tonu
 */
public class PitchCorrectionDecorator extends PerformanceDecorator {

    private static final int BEGINNER_THRESHOLD = 3;
    private static final int INTERMEDIATE_THRESHOLD = 7;
    private static final int BEGINNER_BONUS = 30;
    private static final int INTERMEDIATE_BONUS = 10;
    private static final int PROFESSIONAL_PENALTY = -5;

    private int correctionStrength;
    private String key;

    public PitchCorrectionDecorator(Performance decoratedPerformance, int correctionStrength, String key) {
        super(decoratedPerformance);
        validateCorrectionParameters(correctionStrength, key);
        this.correctionStrength = correctionStrength;
        this.key = key;
    }

    private void validateCorrectionParameters(int strength, String key) {
        if (strength < 1 || strength > 10) {
            throw new IllegalArgumentException("Correction strength must be between 1 and 10");
        }
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }
    }

    @Override
    public String toString() {
        return decoratedPerformance.toString() +
                " [Auto-tune: siła=" + correctionStrength + ", tonacja=" + key + "]";
    }

    @Override
    public int getScore() {
        int baseScore = decoratedPerformance.getScore();
        int avgLevel = calculateAverageParticipantLevel();
        int adjustment = calculateAdjustmentForLevel(avgLevel);
        return baseScore + adjustment;
    }

    private int calculateAverageParticipantLevel() {
        List<User> participants = decoratedPerformance.getParticipants();
        if (participants == null || participants.isEmpty()) {
            throw new IllegalStateException("Performance must have at least one participant");
        }
        return participants.stream()
                .mapToInt(User::getLevel)
                .sum() / participants.size();
    }

    private int calculateAdjustmentForLevel(int avgLevel) {
        if (avgLevel < BEGINNER_THRESHOLD) {
            return BEGINNER_BONUS;
        }
        if (avgLevel < INTERMEDIATE_THRESHOLD) {
            return INTERMEDIATE_BONUS;
        }
        return PROFESSIONAL_PENALTY;
    }
}
// Koniec, Tydzień 3, Wzorzec Decorator 1