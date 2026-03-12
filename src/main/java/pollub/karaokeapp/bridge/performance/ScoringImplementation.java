package pollub.karaokeapp.bridge.performance;

/**
 * Tydzień 3, Wzorzec Bridge 1
 * Interfejs - sposób naliczania punktów
 */
public interface ScoringImplementation {
    int calculateScore(int baseScore, int performanceComplexity);
    String getScoringName();
}
// Koniec, Tydzień 3, Wzorzec Bridge 1