package pollub.karaokeapp.Week3.decorator.performance;

import pollub.karaokeapp.model.performance.Performance;

/**
 * Tydzień 3, Wzorzec Decorator 1
 * Dekorator dodający korekcję tonu
 */
public class PitchCorrectionDecorator extends PerformanceDecorator {

    private int correctionStrength;
    private String key;

    public PitchCorrectionDecorator(Performance decoratedPerformance, int correctionStrength, String key) {
        super(decoratedPerformance);
        this.correctionStrength = correctionStrength;
        this.key = key;
    }

    @Override
    public String toString() {
        return decoratedPerformance.toString() +
                " [Auto-tune: siła=" + correctionStrength + ", tonacja=" + key + "]";
    }

    @Override
    public int getScore() {
        // Auto-tune pomaga niedoświadczonym wokalistom
        int baseScore = decoratedPerformance.getScore();
        int avgLevel = decoratedPerformance.getParticipants().stream()
                .mapToInt(u -> u.getLevel())
                .sum() / decoratedPerformance.getParticipants().size();

        if (avgLevel < 3) {
            return baseScore + 30; // duża pomoc dla początkujących
        } else if (avgLevel < 7) {
            return baseScore + 10; // umiarkowana pomoc
        } else {
            return baseScore - 5; // profesjonaliści nie potrzebują auto-tune
        }
    }
}
// Koniec, Tydzień 3, Wzorzec Decorator 1