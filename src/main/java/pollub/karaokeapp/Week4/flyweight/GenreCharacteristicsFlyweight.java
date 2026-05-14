package pollub.karaokeapp.Week4.flyweight;

import pollub.karaokeapp.Week4.common.UserSkillConstants;

/**
 * Tydzień 4, Wzorzec Flyweight 1
 * Flyweight dla współdzielonych charakterystyk gatunków muzycznych
 * Zmniejsza zużycie pamięci poprzez dzielenie danych między instancje
 */
public class GenreCharacteristicsFlyweight {
    private final String genreName;
    private final String description;
    private final int baseDifficultyLevel;
    private final String recommendedEquipment;
    private final int averageVocalRange;

    public GenreCharacteristicsFlyweight(String genreName, String description,
                                         int baseDifficultyLevel, String recommendedEquipment,
                                         int averageVocalRange) {
        this.genreName = genreName;
        this.description = description;
        this.baseDifficultyLevel = baseDifficultyLevel;
        this.recommendedEquipment = recommendedEquipment;
        this.averageVocalRange = averageVocalRange;
    }

    public int calculateAdjustedDifficulty(int userSkill) {
        if (isLowSkillUser(userSkill)) {
            return adjustDifficultyDown();
        } else if (isHighSkillUser(userSkill)) {
            return adjustDifficultyUp();
        } else {
            return baseDifficultyLevel;
        }
    }

    private boolean isLowSkillUser(int userSkill) {
        return userSkill < UserSkillConstants.LOW_SKILL_THRESHOLD;
    }

    private boolean isHighSkillUser(int userSkill) {
        return userSkill > UserSkillConstants.HIGH_SKILL_THRESHOLD;
    }

    private int adjustDifficultyDown() {
        return Math.max(UserSkillConstants.DIFFICULTY_MIN,
                baseDifficultyLevel + UserSkillConstants.DIFFICULTY_ADJUST_LOW);
    }

    private int adjustDifficultyUp() {
        return Math.min(UserSkillConstants.DIFFICULTY_MAX,
                baseDifficultyLevel + UserSkillConstants.DIFFICULTY_ADJUST_HIGH);
    }

    // gettery
    public String getGenreName() { return genreName; }
    public String getDescription() { return description; }
    public int getBaseDifficultyLevel() { return baseDifficultyLevel; }
    public String getRecommendedEquipment() { return recommendedEquipment; }
    public int getAverageVocalRange() { return averageVocalRange; }

    @Override
    public String toString() {
        return "GenreCharacteristicsFlyweight{" +
                "genreName='" + genreName + '\'' +
                ", description='" + description + '\'' +
                ", baseDifficultyLevel=" + baseDifficultyLevel +
                ", recommendedEquipment='" + recommendedEquipment + '\'' +
                ", averageVocalRange=" + averageVocalRange +
                '}';
    }
}
// Koniec, Tydzień 4, Wzorzec Flyweight 1
