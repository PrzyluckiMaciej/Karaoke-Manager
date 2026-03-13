package pollub.karaokeapp.Week3.decorator.scoring;

import pollub.karaokeapp.service.scoring.ScoringStrategy;

/**
 * Tydzień 3, Wzorzec Decorator 4
 * Dekorator mnożący punkty w zależności od trudności
 */
public class DifficultyMultiplierDecorator extends ScoringDecorator {

    private int songDifficulty;
    private int userLevel;

    public DifficultyMultiplierDecorator(ScoringStrategy decoratedStrategy, int songDifficulty, int userLevel) {
        super(decoratedStrategy);
        this.songDifficulty = songDifficulty;
        this.userLevel = userLevel;
    }

    @Override
    public int calculateScore(int baseScore) {
        int score = super.calculateScore(baseScore);

        double multiplier = 1.0;
        if (songDifficulty > userLevel + 2) {
            multiplier = 1.5; // piosenka trudniejsza niż umiejętności - bonus za odwagę
            System.out.println("   ✨ Mnożnik za trudność: 1.5x");
        } else if (songDifficulty < userLevel - 3) {
            multiplier = 0.8; // piosenka zbyt łatwa - mniej punktów
            System.out.println("   ⚠ Mnożnik za łatwość: 0.8x");
        }

        return (int)(score * multiplier);
    }
}