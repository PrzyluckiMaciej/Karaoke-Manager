package pollub.karaokeapp.Week4.facade;

import pollub.karaokeapp.Week4.common.ScoringConstants;
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

    public int calculatePerformanceScore(Performance performance, byte[] recordedAudio) {
        logScoreCalculationStart();
        int[] metrics = analyzeAudioMetrics(recordedAudio);
        int baseScore = calculateWeightedScore(metrics);
        int finalScore = applyDifficultyMultiplier(baseScore, performance.getSong().getDifficulty());
        logScoreDetails(metrics, finalScore);
        return finalScore;
    }

    private void logScoreCalculationStart() {
        logger.log("[SCORING] Ocenianie wykonania...");
    }

    private int[] analyzeAudioMetrics(byte[] audioData) {
        return new int[] {
                analyzePitchMetric(audioData),
                analyzeRhythmMetric(audioData),
                analyzeToneMetric(audioData)
        };
    }

    private int analyzePitchMetric(byte[] audioData) {
        return analyzeMetricWithBounds(audioData, "wysokość tonu",
                ScoringConstants.MIN_ACCURACY, ScoringConstants.MAX_VARIATION);
    }

    private int analyzeRhythmMetric(byte[] audioData) {
        return analyzeMetricWithBounds(audioData, "rytm",
                ScoringConstants.MIN_ACCURACY - ScoringConstants.OFFSET_RHYTHM,
                ScoringConstants.MAX_VARIATION + ScoringConstants.OFFSET_RHYTHM);
    }

    private int analyzeToneMetric(byte[] audioData) {
        return analyzeMetricWithBounds(audioData, "jakość tonu",
                ScoringConstants.MIN_ACCURACY - ScoringConstants.OFFSET_TONE,
                ScoringConstants.MAX_VARIATION + ScoringConstants.OFFSET_TONE);
    }

    private int analyzeMetricWithBounds(byte[] audioData, String metricName, int minValue, int variation) {
        int value = minValue + (int)(Math.random() * variation);
        logger.log("  → Analiza " + metricName + ": " + value + "%");
        return value;
    }

    private int calculateWeightedScore(int[] metrics) {
        return (metrics[0] * ScoringConstants.PITCH_WEIGHT +
                metrics[1] * ScoringConstants.RHYTHM_WEIGHT +
                metrics[2] * ScoringConstants.TONE_WEIGHT) / ScoringConstants.TOTAL_WEIGHT;
    }

    private int applyDifficultyMultiplier(int baseScore, int difficulty) {
        int multiplier = ScoringConstants.DIFFICULTY_BASE_MULTIPLIER +
                (difficulty * ScoringConstants.DIFFICULTY_INCREMENT);
        return (baseScore * multiplier) / 100;
    }

    private void logScoreDetails(int[] metrics, int finalScore) {
        logger.log("[SCORING] Pitch: " + metrics[0] + "%, Rhythm: " + metrics[1] +
                "%, Tone: " + metrics[2] + "% → Wynik: " + finalScore);
    }

    public void recordScore(int score) {
        scoreManager.addScore(score);
        logger.log("[SCORING] ✓ Wynik zarejestrowany: " + score);
    }

    public int getTotalScore() { return scoreManager.getTotalScore(); }
    public String getScoreSummary() { return "Łączny wynik: " + getTotalScore() + " pkt"; }
    public void resetScores() {
        scoreManager.resetScores();
        logger.log("[SCORING] ✓ Wyniki zresetowane");
    }
}
// Koniec, Tydzień 4, Wzorzec Facade 4
