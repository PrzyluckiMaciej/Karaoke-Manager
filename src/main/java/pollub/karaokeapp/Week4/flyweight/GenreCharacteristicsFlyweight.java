package pollub.karaokeapp.Week4.flyweight;

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
    private final int averageVocalRange; // w półtonach

    public GenreCharacteristicsFlyweight(
            String genreName,
            String description,
            int baseDifficultyLevel,
            String recommendedEquipment,
            int averageVocalRange) {
        this.genreName = genreName;
        this.description = description;
        this.baseDifficultyLevel = baseDifficultyLevel;
        this.recommendedEquipment = recommendedEquipment;
        this.averageVocalRange = averageVocalRange;
    }

    public String getGenreName() {
        return genreName;
    }

    public String getDescription() {
        return description;
    }

    public int getBaseDifficultyLevel() {
        return baseDifficultyLevel;
    }

    public String getRecommendedEquipment() {
        return recommendedEquipment;
    }

    public int getAverageVocalRange() {
        return averageVocalRange;
    }

    public int calculateAdjustedDifficulty(int userSkill) {
        // baseDifficultyLevel to współdzielona cecha
        // userSkill to cecha niezależna
        if (userSkill < 3) {
            return Math.max(1, baseDifficultyLevel - 2);
        } else if (userSkill > 7) {
            return Math.min(10, baseDifficultyLevel + 1);
        } else {
            return baseDifficultyLevel;
        }
    }

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
