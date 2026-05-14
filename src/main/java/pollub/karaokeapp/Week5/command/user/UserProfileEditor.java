package pollub.karaokeapp.Week5.command.user;

import pollub.karaokeapp.model.user.User;

/**
 * Tydzień 5, Wzorzec Command 4 – Receiver
 * Odbiorca (Receiver) zawierający logikę biznesową edycji profilu użytkownika.
 * Komendy delegują do tej klasy zamiast operować bezpośrednio na User.
 */
public class UserProfileEditor {

    public static final int MIN_LEVEL = 1;
    public static final int MAX_LEVEL = 10;
    public static final int MIN_POINTS_AWARD = 1;

    private static final String LOG_PREFIX = "[USER-RECEIVER]";

    private final User user;

    public UserProfileEditor(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Użytkownik nie może być null");
        }
        this.user = user;
    }

    public String getCurrentNickname() {
        return user.getNickname();
    }

    public void changeNickname(String newNickname) {
        validateNickname(newNickname);
        logNicknameChange(newNickname);
        user.setNickname(newNickname);
    }

    public int getCurrentLevel() {
        return user.getLevel();
    }

    public void changeLevel(int newLevel) {
        validateLevel(newLevel);
        logLevelChange(newLevel);
        user.setLevel(newLevel);
    }

    public int getCurrentPoints() {
        return user.getPoints();
    }

    public void addPoints(int points) {
        validatePoints(points);
        int updated = user.getPoints() + points;
        logPointsAddition(points, updated);
        user.setPoints(updated);
    }

    public void subtractPoints(int points) {
        validatePoints(points);
        validateSufficientPoints(points);
        int updated = user.getPoints() - points;
        logPointsSubtraction(points, updated);
        user.setPoints(updated);
    }

    public User getUser() {
        return user;
    }

    private void validateNickname(String nickname) {
        if (nickname == null || nickname.trim().isEmpty()) {
            throw new IllegalArgumentException("Nickname nie może być pusty");
        }
        if (nickname.length() > 20) {
            throw new IllegalArgumentException("Nickname nie może przekraczać 20 znaków");
        }
    }

    private void validateLevel(int level) {
        if (level < MIN_LEVEL || level > MAX_LEVEL) {
            throw new IllegalArgumentException(
                    "Poziom musi być między " + MIN_LEVEL + " a " + MAX_LEVEL +
                            ". Podano: " + level
            );
        }
    }

    private void validatePoints(int points) {
        if (points < MIN_POINTS_AWARD) {
            throw new IllegalArgumentException(
                    "Liczba punktów musi być dodatnia (min. " + MIN_POINTS_AWARD + "). Podano: " + points
            );
        }
    }

    private void validateSufficientPoints(int pointsToSubtract) {
        if (user.getPoints() - pointsToSubtract < 0) {
            throw new IllegalStateException(
                    "Użytkownik '" + user.getNickname() + "' nie ma wystarczającej liczby punktów. " +
                            "Posiada: " + user.getPoints() + ", próba odjęcia: " + pointsToSubtract
            );
        }
    }

    private void logNicknameChange(String newNickname) {
        System.out.println(LOG_PREFIX + " Zmiana nicku: '" + user.getNickname() + "' → '" + newNickname + "'");
    }

    private void logLevelChange(int newLevel) {
        System.out.println(LOG_PREFIX + " Zmiana poziomu: " + user.getLevel() + " → " + newLevel);
    }

    private void logPointsAddition(int points, int updated) {
        System.out.println(LOG_PREFIX + " Dodanie punktów: " + user.getPoints() + " + " + points + " = " + updated);
    }

    private void logPointsSubtraction(int points, int updated) {
        System.out.println(LOG_PREFIX + " Odjęcie punktów: " + user.getPoints() + " - " + points + " = " + updated);
    }
}
// Koniec, Tydzień 5, Wzorzec Command 4 – Receiver