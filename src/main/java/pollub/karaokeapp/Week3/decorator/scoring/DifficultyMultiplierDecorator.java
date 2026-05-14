package pollub.karaokeapp.Week3.decorator.scoring;

import pollub.karaokeapp.service.scoring.ScoringStrategy;

/**
 * Tydzień 3, Wzorzec Decorator 4
 * Dekorator mnożący punkty w zależności od trudności
 */
public class DifficultyMultiplierDecorator extends ScoringDecorator {

    private static final double DIFFICULT_BONUS_MULTIPLIER = 1.5;
    private static final double EASY_PENALTY_MULTIPLIER = 0.8;
    private static final int DIFFICULTY_DIFF_THRESHOLD_HIGH = 2;
    private static final int DIFFICULTY_DIFF_THRESHOLD_LOW = 3;

    private int songDifficulty;
    private int userLevel;

    public DifficultyMultiplierDecorator(ScoringStrategy decoratedStrategy, int songDifficulty, int userLevel) {
        super(decoratedStrategy);
        validateDifficultyParameters(songDifficulty, userLevel);
        this.songDifficulty = songDifficulty;
        this.userLevel = userLevel;
    }

    private void validateDifficultyParameters(int songDiff, int userLvl) {
        if (songDiff < 1 || songDiff > 10) {
            throw new IllegalArgumentException("Song difficulty must be between 1 and 10");
        }
        if (userLvl < 1 || userLvl > 10) {
            throw new IllegalArgumentException("User level must be between 1 and 10");
        }
    }

    @Override
    public int calculateScore(int baseScore) {
        int score = super.calculateScore(baseScore);
        double multiplier = calculateMultiplier();
        logMultiplier(multiplier);
        return (int)(score * multiplier);
    }

    private double calculateMultiplier() {
        if (isSongMuchHarder()) {
            return DIFFICULT_BONUS_MULTIPLIER;
        }
        if (isSongMuchEasier()) {
            return EASY_PENALTY_MULTIPLIER;
        }
        return 1.0;
    }

    private boolean isSongMuchHarder() {
        return songDifficulty > userLevel + DIFFICULTY_DIFF_THRESHOLD_HIGH;
    }

    private boolean isSongMuchEasier() {
        return songDifficulty < userLevel - DIFFICULTY_DIFF_THRESHOLD_LOW;
    }

    private void logMultiplier(double multiplier) {
        if (multiplier == DIFFICULT_BONUS_MULTIPLIER) {
            System.out.println("   ✨ Mnożnik za trudność: " + multiplier + "x");
        } else if (multiplier == EASY_PENALTY_MULTIPLIER) {
            System.out.println("   ⚠ Mnożnik za łatwość: " + multiplier + "x");
        }
    }
}
// Koniec, Tydzień 3, Wzorzec Decorator 4