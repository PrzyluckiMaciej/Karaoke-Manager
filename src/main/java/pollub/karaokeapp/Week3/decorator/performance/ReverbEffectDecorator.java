package pollub.karaokeapp.Week3.decorator.performance;

import pollub.karaokeapp.model.performance.Performance;
import java.util.Set;

/**
 * Tydzień 3, Wzorzec Decorator 1
 * Dekorator dodający pogłos
 */
public class ReverbEffectDecorator extends PerformanceDecorator {

    private static final int ROCK_METAL_PENALTY = -10;
    private static final int REVERB_BONUS = 15;
    private static final Set<String> ROCK_GENRES = Set.of("Rock", "Metal");

    private String roomType;
    private int reverbTime;

    public ReverbEffectDecorator(Performance decoratedPerformance, String roomType, int reverbTime) {
        super(decoratedPerformance);
        validateReverbParameters(roomType, reverbTime);
        this.roomType = roomType;
        this.reverbTime = reverbTime;
    }

    private void validateReverbParameters(String roomType, int reverbTime) {
        if (roomType == null || roomType.trim().isEmpty()) {
            throw new IllegalArgumentException("Room type cannot be null or empty");
        }
        if (reverbTime < 0) {
            throw new IllegalArgumentException("Reverb time cannot be negative");
        }
    }

    @Override
    public String toString() {
        return decoratedPerformance.toString() +
                " [Pogłos: pomieszczenie=" + roomType + ", czas=" + reverbTime + "ms]";
    }

    @Override
    public int getScore() {
        int baseScore = decoratedPerformance.getScore();
        boolean isRockOrMetal = isRockOrMetalGenre();
        return isRockOrMetal ? baseScore + ROCK_METAL_PENALTY : baseScore + REVERB_BONUS;
    }

    private boolean isRockOrMetalGenre() {
        String genre = decoratedPerformance.getSong().getGenre();
        return genre != null && ROCK_GENRES.contains(genre);
    }
}
// Koniec, Tydzień 3, Wzorzec Decorator 1