package pollub.karaokeapp.Week3.adapter.scoring;

import pollub.karaokeapp.service.scoring.LegacyScoringSystem;
import pollub.karaokeapp.service.scoring.ScoringStrategy;

/**
 * Tydzień 3, Wzorzec Adapter 2 (Adapter klasy)
 * Adapter dostosowujący stary system oceniania do interfejsu ScoringStrategy
 */
public class LegacyScoringAdapter extends LegacyScoringSystem implements ScoringStrategy {

    private static final int MIN_PITCH_ACCURACY = 0;
    private static final int MAX_PITCH_ACCURACY = 100;
    private static final int MIN_RHYTHM_ACCURACY = 0;
    private static final int MAX_RHYTHM_ACCURACY = 100;
    private static final int MIN_AUDIENCE_REACTION = 0;
    private static final int MAX_AUDIENCE_REACTION = 100;

    private final int pitchAccuracy;
    private final int rhythmAccuracy;
    private final int audienceReaction;

    public LegacyScoringAdapter(int pitchAccuracy, int rhythmAccuracy, int audienceReaction) {
        this.pitchAccuracy = validatePitchAccuracy(pitchAccuracy);
        this.rhythmAccuracy = validateRhythmAccuracy(rhythmAccuracy);
        this.audienceReaction = validateAudienceReaction(audienceReaction);
    }

    private int validatePitchAccuracy(int value) {
        if (value < MIN_PITCH_ACCURACY || value > MAX_PITCH_ACCURACY) {
            throw new IllegalArgumentException(
                    "Pitch accuracy must be between " + MIN_PITCH_ACCURACY + " and " + MAX_PITCH_ACCURACY
            );
        }
        return value;
    }

    private int validateRhythmAccuracy(int value) {
        if (value < MIN_RHYTHM_ACCURACY || value > MAX_RHYTHM_ACCURACY) {
            throw new IllegalArgumentException(
                    "Rhythm accuracy must be between " + MIN_RHYTHM_ACCURACY + " and " + MAX_RHYTHM_ACCURACY
            );
        }
        return value;
    }

    private int validateAudienceReaction(int value) {
        if (value < MIN_AUDIENCE_REACTION || value > MAX_AUDIENCE_REACTION) {
            throw new IllegalArgumentException(
                    "Audience reaction must be between " + MIN_AUDIENCE_REACTION + " and " + MAX_AUDIENCE_REACTION
            );
        }
        return value;
    }

    @Override
    public int calculateScore(int baseScore) {
        double legacyScore = calculateLegacyScore(pitchAccuracy, rhythmAccuracy, audienceReaction);
        return baseScore + (int) legacyScore;
    }
}
// Koniec, Tydzień 3, Wzorzec Adapter 2