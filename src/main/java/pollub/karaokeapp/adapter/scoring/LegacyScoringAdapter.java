package pollub.karaokeapp.adapter.scoring;

import pollub.karaokeapp.external.LegacyScoringSystem;
import pollub.karaokeapp.service.scoring.ScoringStrategy;

/**
 * Tydzień 3, Wzorzec Adapter 2
 * Adapter dostosowujący stary system oceniania do interfejsu ScoringStrategy
 */
public class LegacyScoringAdapter implements ScoringStrategy {

    private LegacyScoringSystem legacySystem;
    private int pitchAccuracy;
    private int rhythmAccuracy;
    private int audienceReaction;

    public LegacyScoringAdapter(LegacyScoringSystem legacySystem,
                                int pitchAccuracy,
                                int rhythmAccuracy,
                                int audienceReaction) {
        this.legacySystem = legacySystem;
        this.pitchAccuracy = pitchAccuracy;
        this.rhythmAccuracy = rhythmAccuracy;
        this.audienceReaction = audienceReaction;
    }

    @Override
    public int calculateScore(int baseScore) {
        double legacyScore = legacySystem.calculateLegacyScore(pitchAccuracy, rhythmAccuracy, audienceReaction);
        return (int) (baseScore + legacyScore);
    }
}
// Koniec, Tydzień 3, Wzorzec Adapter 2