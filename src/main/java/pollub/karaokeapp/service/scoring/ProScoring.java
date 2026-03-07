package pollub.karaokeapp.service.scoring;

public class ProScoring implements ScoringStrategy {
    @Override
    public int calculateScore(int baseScore) {
        return baseScore;
    }
}