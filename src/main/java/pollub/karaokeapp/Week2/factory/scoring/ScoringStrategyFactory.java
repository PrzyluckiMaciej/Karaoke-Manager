package pollub.karaokeapp.Week2.factory.scoring;

import pollub.karaokeapp.service.scoring.*;

/**
 * Tydzień 2, Wzorzec Factory 4
 * Zastosowanie Simple Factory do tworzenia różnych strategii naliczania punktów.
 * Różne naliczania punktów zaimplementowano w pakiecie service.
 */
public class ScoringStrategyFactory {

    public static ScoringStrategy createStrategy(String type) {

        switch (type.toLowerCase()) {
            case "easy":
                return new EasyScoring();
            case "pro":
                return new ProScoring();
            case "audience":
                return new AudienceScoring();
            default:
                throw new IllegalArgumentException("Nieznany typ strategii!");
        }
    }
}
//Koniec, Tydzień 2, Wzorzec Factory 4