package pollub.karaokeapp.Week10.functional;

import pollub.karaokeapp.model.user.User;

/**
 * Tydzień 10, Interfejs funkcyjny 3
 * UserPointsCalculator oblicza liczbę punktów przyznawanych użytkownikowi
 * na podstawie bazowej wartości. Umożliwia definiowanie różnych reguł
 * naliczania punktów za pomocą wyrażeń lambda.
 */
@FunctionalInterface
public interface UserPointsCalculator {
    int calculate(User user, int basePoints);
}
//Koniec, Tydzień 10, Interfejs funkcyjny 3
