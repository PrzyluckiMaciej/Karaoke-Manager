package pollub.karaokeapp.Week6.visitor;

import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;
import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.playlist.Playlist;

/**
 * Tydzień 6, Wzorzec Visitor 3
 * Konkretny visitor - waliduje dane
 */
public class ValidationVisitor implements KaraokeVisitor {

    private int errorsFound = 0;

    @Override
    public void visitSong(Song song) {
        System.out.println("[VALIDATION-VISITOR] Walidacja piosenki: " + song.getTitle());
        if (song.getTitle().isEmpty()) {
            System.out.println("  ❌ BŁĄD: Tytuł piosenki jest pusty");
            errorsFound++;
        }
        if (song.getDuration() <= 0) {
            System.out.println("  ❌ BŁĄD: Czas trwania musi być > 0");
            errorsFound++;
        }
        if (song.getDifficulty() < 1 || song.getDifficulty() > 10) {
            System.out.println("  ❌ BŁĄD: Trudność musi być 1-10");
            errorsFound++;
        }
        if (errorsFound == 0) System.out.println("  ✓ OK");
    }

    @Override
    public void visitUser(User user) {
        System.out.println("[VALIDATION-VISITOR] Walidacja użytkownika: " + user.getNickname());
        if (user.getNickname().isEmpty()) {
            System.out.println("  ❌ BŁĄD: Nickname jest pusty");
            errorsFound++;
        }
        if (user.getLevel() < 1 || user.getLevel() > 10) {
            System.out.println("  ❌ BŁĄD: Poziom musi być 1-10");
            errorsFound++;
        }
        if (errorsFound == 0) System.out.println("  ✓ OK");
    }

    @Override
    public void visitPerformance(Performance performance) {
        System.out.println("[VALIDATION-VISITOR] Walidacja wykonania");
        if (performance.getParticipants().isEmpty()) {
            System.out.println("  ❌ BŁĄD: Wykonanie musi mieć co najmniej jednego uczestnika");
            errorsFound++;
        }
        if (performance.getScore() < 0) {
            System.out.println("  ❌ BŁĄD: Wynik nie może być ujemny");
            errorsFound++;
        }
        if (errorsFound == 0) System.out.println("  ✓ OK");
    }

    @Override
    public void visitPlaylist(Playlist playlist) {
        System.out.println("[VALIDATION-VISITOR] Walidacja playlisty: " + playlist.getName());
        if (playlist.getName().isEmpty()) {
            System.out.println("  ❌ BŁĄD: Nazwa playlisty jest pusta");
            errorsFound++;
        }
        if (playlist.getSongs().isEmpty()) {
            System.out.println("  ❌ BŁĄD: Playlista jest pusta");
            errorsFound++;
        }
        if (errorsFound == 0) System.out.println("  ✓ OK");
    }

    public int getErrorCount() {
        return errorsFound;
    }

    @Override
    public String getVisitorName() {
        return "Validation Engine";
    }
}
// Koniec, Tydzień 6, Wzorzec Visitor 3
