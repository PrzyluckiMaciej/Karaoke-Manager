package pollub.karaokeapp.decorator.performance;

import pollub.karaokeapp.model.performance.Performance;

/**
 * Tydzień 3, Wzorzec Decorator 1
 * Dekorator dodający pogłos
 */
public class ReverbEffectDecorator extends PerformanceDecorator {

    private String roomType; // small, medium, large, hall
    private int reverbTime;

    public ReverbEffectDecorator(Performance decoratedPerformance, String roomType, int reverbTime) {
        super(decoratedPerformance);
        this.roomType = roomType;
        this.reverbTime = reverbTime;
    }

    @Override
    public String toString() {
        return decoratedPerformance.toString() +
                " [Pogłos: pomieszczenie=" + roomType + ", czas=" + reverbTime + "ms]";
    }

    @Override
    public int getScore() {
        int baseScore = decoratedPerformance.getScore();
        if (decoratedPerformance.getSong().getGenre().equalsIgnoreCase("Rock") ||
                decoratedPerformance.getSong().getGenre().equalsIgnoreCase("Metal")) {
            return baseScore - 10;
        }
        return baseScore + 15;
    }
}
// Koniec, Tydzień 3, Wzorzec Decorator 1