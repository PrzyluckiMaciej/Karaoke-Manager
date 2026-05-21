package pollub.karaokeapp.Week10.functional;

import pollub.karaokeapp.model.user.User;

/**
 * Tydzień 10, Implementacja klasowa interfejsu funkcyjnego 3
 * LevelBasedPointsCalculator mnoży bazową liczbę punktów przez poziom użytkownika.
 * Wyższy poziom gracza przekłada się na większą nagrodę za każde wykonanie.
 */
public class LevelBasedPointsCalculator implements UserPointsCalculator {

    @Override
    public int calculate(User user, int basePoints) {
        return basePoints * user.getLevel();
    }
}
//Koniec, Tydzień 10, Implementacja klasowa interfejsu funkcyjnego 3
