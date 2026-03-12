package pollub.karaokeapp.external;

/**
 * Stary system oceniania używający innego formatu
 */
public class LegacyScoringSystem {

    public double calculateLegacyScore(int pitchAccuracy, int rhythmAccuracy, int audienceReaction) {
        return (pitchAccuracy * 0.4 + rhythmAccuracy * 0.3 + audienceReaction * 0.3) * 10;
    }
}
