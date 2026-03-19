package pollub.karaokeapp.Week4.facade;

import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.Week2.singleton.LoggerSingleton;
import pollub.karaokeapp.Week2.singleton.ScoreManagerSingleton;

/**
 * Tydzień 4, Wzorzec Facade 4
 * Fasada dla systemu zdobywania punktów
 * Upraszcza obliczanie punktów i zarządzanie wynikami
 */
public class ScoringSystemFacade {

    private final LoggerSingleton logger = LoggerSingleton.getInstance();
    private final ScoreManagerSingleton scoreManager = ScoreManagerSingleton.getInstance();

    // Obliczenie wyniku dla wykonania
    public int calculatePerformanceScore(Performance performance, byte[] recordedAudio) {
        logger.log("[SCORING] Ocenianie wykonania...");

        int pitchAccuracy = analyzePitchAccuracy(recordedAudio);
        int rhythmAccuracy = analyzeRhythmAccuracy(recordedAudio);
        int toneQuality = analyzeToneQuality(recordedAudio);

        int baseScore = (pitchAccuracy + rhythmAccuracy + toneQuality) / 3;
        int difficultyMultiplier = calculateDifficultyMultiplier(performance.getSong().getDifficulty());
        int finalScore = (baseScore * difficultyMultiplier) / 100;

        logger.log("[SCORING] Pitch: " + pitchAccuracy + "%, Rhythm: " + rhythmAccuracy +
                "%, Tone: " + toneQuality + "% → Wynik: " + finalScore);

        return finalScore;
    }

    // Zarejestrowanie wyniku
    public void recordScore(int score) {
        scoreManager.addScore(score);
        logger.log("[SCORING] ✓ Wynik zarejestrowany: " + score);
    }

    // Pobranie łącznego wyniku
    public int getTotalScore() {
        return scoreManager.getTotalScore();
    }

    // Analiza dokładności wysokości tonu
    private int analyzePitchAccuracy(byte[] audioData) {
        // Symulacja analizy
        int accuracy = 70 + (int)(Math.random() * 30);
        logger.log("  → Analiza wysokości tonu: " + accuracy + "%");
        return accuracy;
    }

    // Analiza dokładności rytmu
    private int analyzeRhythmAccuracy(byte[] audioData) {
        // Symulacja analizy
        int accuracy = 65 + (int)(Math.random() * 35);
        logger.log("  → Analiza rytmu: " + accuracy + "%");
        return accuracy;
    }

    // Analiza jakości tonu
    private int analyzeToneQuality(byte[] audioData) {
        // Symulacja analizy
        int quality = 60 + (int)(Math.random() * 40);
        logger.log("  → Jakość tonu: " + quality + "%");
        return quality;
    }

    // Obliczenie mnożnika za trudność
    private int calculateDifficultyMultiplier(int difficulty) {
        return 80 + (difficulty * 5);
    }

    // Pobranie ranking
    public String getScoreSummary() {
        return "Łączny wynik: " + getTotalScore() + " pkt";
    }

    // Reset wyników
    public void resetScores() {
        scoreManager.resetScores();
        logger.log("[SCORING] ✓ Wyniki zresetowane");
    }
}
// Koniec, Tydzień 4, Wzorzec Facade 4
