package pollub.karaokeapp.Week3.decorator.scoring;

/**
 * Klasa konfiguracyjna dla kary czasowej
 * Rozwiązuje problem 4 argumentów w konstruktorze
 */
public class TimePenaltyConfig {

    private final int maxDurationSeconds;
    private final int actualDurationSeconds;
    private final int penaltyPerSecond;

    public TimePenaltyConfig(int maxDurationSeconds, int actualDurationSeconds, int penaltyPerSecond) {
        this.maxDurationSeconds = maxDurationSeconds;
        this.actualDurationSeconds = actualDurationSeconds;
        this.penaltyPerSecond = penaltyPerSecond;
    }

    public int getMaxDurationSeconds() {
        return maxDurationSeconds;
    }

    public int getActualDurationSeconds() {
        return actualDurationSeconds;
    }

    public int getPenaltyPerSecond() {
        return penaltyPerSecond;
    }

    public void validate() {
        if (maxDurationSeconds <= 0) {
            throw new IllegalArgumentException("Max duration must be positive");
        }
        if (actualDurationSeconds <= 0) {
            throw new IllegalArgumentException("Actual duration must be positive");
        }
        if (penaltyPerSecond < 0) {
            throw new IllegalArgumentException("Penalty per second cannot be negative");
        }
    }
}