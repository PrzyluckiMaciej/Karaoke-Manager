package pollub.karaokeapp.service.scoring;

public class EasyScoring implements ScoringStrategy {
    @Override
    public int calculateScore(int baseScore) {
        return baseScore + 20;
    }
}