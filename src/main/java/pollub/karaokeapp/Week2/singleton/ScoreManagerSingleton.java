package pollub.karaokeapp.Week2.singleton;

/**
 * Tydzień 2, Wzorzec Singleton 3
 * Podwójne blokowanie, czyli bezpieczna implementacja wielowątkowa.
 * W tym przykładzie mamy globalny manager wyników karaoke.
 * Tylko pierwsze wątki mają szansę utworzyć Singleton. Stosowanie sekcji krytycznej - synchronized
 */
public class ScoreManagerSingleton {

    private static volatile ScoreManagerSingleton instance;
    private int totalScore = 0;

    private ScoreManagerSingleton() {}

    public static ScoreManagerSingleton getInstance() {
        if (instance == null) {
            synchronized (ScoreManagerSingleton.class) {
                if (instance == null) {
                    instance = new ScoreManagerSingleton();
                }
            }
        }
        return instance;
    }

    public void addScore(int score) {
        totalScore += score;
    }

    public void resetScores() {
        totalScore = 0;
    }

    public int getTotalScore() {
        return totalScore;
    }
}
// Koniec, Tydzień 2, Wzorzec Singleton 3