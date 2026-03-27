package pollub.karaokeapp.Week5.command.user;

import pollub.karaokeapp.model.user.User;

/**
 * Tydzień 5, Wzorzec Command 4 – Receiver
 * Odbiorca (Receiver) zawierający logikę biznesową edycji profilu użytkownika.
 * Komendy delegują do tej klasy zamiast operować bezpośrednio na User.
 */
public class UserProfileEditor {

    private final User user;

    public UserProfileEditor(User user) {
        this.user = user;
    }

    public String getCurrentNickname() {
        return user.getNickname();
    }

    public void changeNickname(String newNickname) {
        System.out.println("[USER-RECEIVER] Zmiana nicku: '" + user.getNickname() + "' → '" + newNickname + "'");
        user.setNickname(newNickname);
    }

    public int getCurrentLevel() {
        return user.getLevel();
    }

    public void changeLevel(int newLevel) {
        System.out.println("[USER-RECEIVER] Zmiana poziomu: " + user.getLevel() + " → " + newLevel);
        user.setLevel(newLevel);
    }

    public int getCurrentPoints() {
        return user.getPoints();
    }

    public void addPoints(int points) {
        int updated = user.getPoints() + points;
        System.out.println("[USER-RECEIVER] Dodanie punktów: " + user.getPoints() + " + " + points + " = " + updated);
        user.setPoints(updated);
    }

    public void subtractPoints(int points) {
        int updated = user.getPoints() - points;
        System.out.println("[USER-RECEIVER] Odjęcie punktów: " + user.getPoints() + " - " + points + " = " + updated);
        user.setPoints(updated);
    }

    public User getUser() {
        return user;
    }
}
// Koniec, Tydzień 5, Wzorzec Command 4 – Receiver