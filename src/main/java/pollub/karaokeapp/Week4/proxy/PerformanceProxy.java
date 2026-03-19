package pollub.karaokeapp.Week4.proxy;

import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;
import pollub.karaokeapp.Week2.singleton.LoggerSingleton;
import java.util.List;

/**
 * Tydzień 4, Wzorzec Proxy 2
 * Proxy dla wydajności - cache dla obliczeń wydajności
 * Przechowuje wyniki i unika powtarzających się obliczeń
 */
public class PerformanceProxy extends Performance {

    private final Performance realPerformance;
    private final LoggerSingleton logger = LoggerSingleton.getInstance();
    private Integer cachedScore;
    private long calculationTime;

    public PerformanceProxy(Performance realPerformance) {
        super(realPerformance.getSong(), realPerformance.getParticipants(), realPerformance.getScore());
        this.realPerformance = realPerformance;
        this.cachedScore = null;
    }

    @Override
    public int getScore() {
        if (cachedScore != null) {
            logger.log("[PERFORMANCE-PROXY] Pobieranie wyniku z cache");
            return cachedScore;
        }

        logger.log("[PERFORMANCE-PROXY] Obliczanie wyniku (brak w cache)...");
        long startTime = System.currentTimeMillis();

        int score = realPerformance.getScore();

        calculationTime = System.currentTimeMillis() - startTime;
        cachedScore = score;

        logger.log("[PERFORMANCE-PROXY] ✓ Wynik obliczony w " + calculationTime + "ms");
        return score;
    }

    @Override
    public void setScore(int score) {
        realPerformance.setScore(score);
        cachedScore = score;
        logger.log("[PERFORMANCE-PROXY] Wynik zaktualizowany w cache");
    }

    @Override
    public Song getSong() {
        return realPerformance.getSong();
    }

    @Override
    public List<User> getParticipants() {
        return realPerformance.getParticipants();
    }

    @Override
    public String toString() {
        return realPerformance.toString() + " [CACHED]";
    }

    @Override
    public Performance clone() {
        return realPerformance.clone();
    }

    public long getLastCalculationTime() {
        return calculationTime;
    }

    public void clearCache() {
        cachedScore = null;
        logger.log("[PERFORMANCE-PROXY] Cache wyczyszczony");
    }
}
// Koniec, Tydzień 4, Wzorzec Proxy 2
