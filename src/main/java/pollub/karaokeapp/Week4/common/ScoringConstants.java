// Week4/common/ScoringConstants.java
package pollub.karaokeapp.Week4.common;

public final class ScoringConstants {
    private ScoringConstants() {}

    public static final int PITCH_WEIGHT = 1;
    public static final int RHYTHM_WEIGHT = 1;
    public static final int TONE_WEIGHT = 1;
    public static final int TOTAL_WEIGHT = 3;
    public static final int DIFFICULTY_BASE_MULTIPLIER = 80;
    public static final int DIFFICULTY_INCREMENT = 5;
    public static final int MIN_ACCURACY = 60;
    public static final int MAX_VARIATION = 40;
    public static final int OFFSET_RHYTHM = 5;
    public static final int OFFSET_TONE = 10;
}