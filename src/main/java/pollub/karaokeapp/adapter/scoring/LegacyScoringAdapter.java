package pollub.karaokeapp.adapter.scoring;

import pollub.karaokeapp.service.scoring.LegacyScoringSystem;
import pollub.karaokeapp.service.scoring.ScoringStrategy;

/**
 * Tydzień 3, Wzorzec Adapter 2 (Adapter klasy)
 * Adapter dostosowujący stary system oceniania do interfejsu ScoringStrategy
 */
public class LegacyScoringAdapter extends LegacyScoringSystem implements ScoringStrategy {

    private final int pitchAccuracy;
    private final int rhythmAccuracy;
    private final int audienceReaction;

    public LegacyScoringAdapter(int pitchAccuracy, int rhythmAccuracy, int audienceReaction) {
        this.pitchAccuracy = pitchAccuracy;
        this.rhythmAccuracy = rhythmAccuracy;
        this.audienceReaction = audienceReaction;
    }

    @Override
    public int calculateScore(int baseScore) {
        double legacyScore = calculateLegacyScore(pitchAccuracy, rhythmAccuracy, audienceReaction);
        return (int) (baseScore + legacyScore);
    }
}
// Koniec, Tydzień 3, Wzorzec Adapter 2