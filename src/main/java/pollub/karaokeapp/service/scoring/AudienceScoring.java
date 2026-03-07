package pollub.karaokeapp.service.scoring;

public class AudienceScoring implements ScoringStrategy {
    @Override
    public int calculateScore(int baseScore) {
        return baseScore + 50;
    }
}