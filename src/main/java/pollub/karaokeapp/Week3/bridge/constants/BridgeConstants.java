package pollub.karaokeapp.Week3.bridge.constants;

public final class BridgeConstants {

    private BridgeConstants() {}

    // Audio constants
    public static final int DEFAULT_SAMPLE_RATE = 44100;
    public static final int BYTES_PER_SAMPLE = 2;
    public static final int MIC_BUFFER_SIZE = 1024;

    // Volume constants
    public static final int MIN_VOLUME = 0;
    public static final int MAX_VOLUME = 100;
    public static final int DEFAULT_MP3_VOLUME = 50;
    public static final int DEFAULT_STREAMING_VOLUME = 70;

    // Scoring constants
    public static final int STANDARD_SCORE_MULTIPLIER = 2;
    public static final int BONUS_SCORE_MULTIPLIER = 5;
    public static final int BONUS_FLAT_ADDITION = 50;
    public static final int SOLO_BASE_SCORE = 100;
    public static final int DUET_BASE_SCORE = 120;

    // Participant counts
    public static final int SOLO_PARTICIPANTS = 1;
    public static final int DUET_PARTICIPANTS = 2;

    // SMS constants
    public static final int SMS_MAX_LENGTH = 160;

    // Lyrics constants
    public static final int PREVIEW_MAX_LINES = 5;
    public static final int TRANSLATION_LINE_MODULO = 2;
}