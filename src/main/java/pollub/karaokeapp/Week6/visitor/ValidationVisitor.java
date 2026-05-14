package pollub.karaokeapp.Week6.visitor;

import pollub.karaokeapp.Week6.exception.ValidationException;
import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;
import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.playlist.Playlist;

/**
 * Tydzień 6, Wzorzec Visitor 3
 * Konkretny visitor - waliduje dane
 */
public class ValidationVisitor implements KaraokeVisitor {

    private static final int MIN_DIFFICULTY = 1;
    private static final int MAX_DIFFICULTY = 10;
    private static final int MIN_USER_LEVEL = 1;
    private static final int MAX_USER_LEVEL = 10;

    private int errorsFound = 0;

    @Override
    public void visitSong(Song song) {
        System.out.println("[VALIDATION-VISITOR] Walidacja piosenki: " + song.getTitle());

        if (song.getTitle() == null || song.getTitle().trim().isEmpty()) {
            throw new ValidationException("Tytuł piosenki nie może być pusty");
        }
        if (song.getDuration() <= 0) {
            throw new ValidationException("Czas trwania musi być > 0, obecnie: " + song.getDuration());
        }
        if (song.getDifficulty() < MIN_DIFFICULTY || song.getDifficulty() > MAX_DIFFICULTY) {
            throw new ValidationException(
                    "Trudność musi być pomiędzy " + MIN_DIFFICULTY + " a " + MAX_DIFFICULTY +
                            ", obecnie: " + song.getDifficulty()
            );
        }

        System.out.println("  ✓ OK");
    }

    @Override
    public void visitUser(User user) {
        System.out.println("[VALIDATION-VISITOR] Walidacja użytkownika: " + user.getNickname());

        if (user.getNickname() == null || user.getNickname().trim().isEmpty()) {
            throw new ValidationException("Nickname użytkownika nie może być pusty");
        }
        if (user.getLevel() < MIN_USER_LEVEL || user.getLevel() > MAX_USER_LEVEL) {
            throw new ValidationException(
                    "Poziom musi być pomiędzy " + MIN_USER_LEVEL + " a " + MAX_USER_LEVEL +
                            ", obecnie: " + user.getLevel()
            );
        }

        System.out.println("  ✓ OK");
    }

    @Override
    public void visitPerformance(Performance performance) {
        System.out.println("[VALIDATION-VISITOR] Walidacja wykonania");

        if (performance.getParticipants() == null || performance.getParticipants().isEmpty()) {
            throw new ValidationException("Wykonanie musi mieć co najmniej jednego uczestnika");
        }
        if (performance.getScore() < 0) {
            throw new ValidationException("Wynik nie może być ujemny, obecnie: " + performance.getScore());
        }

        System.out.println("  ✓ OK");
    }

    @Override
    public void visitPlaylist(Playlist playlist) {
        System.out.println("[VALIDATION-VISITOR] Walidacja playlisty: " + playlist.getName());

        if (playlist.getName() == null || playlist.getName().trim().isEmpty()) {
            throw new ValidationException("Nazwa playlisty nie może być pusta");
        }
        if (playlist.getSongs() == null || playlist.getSongs().isEmpty()) {
            throw new ValidationException("Playlista nie może być pusta");
        }

        System.out.println("  ✓ OK");
    }

    public int getErrorCount() {
        return errorsFound;
    }

    @Override
    public String getVisitorName() {
        return "Validation Engine";
    }
}