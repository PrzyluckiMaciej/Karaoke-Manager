package pollub.karaokeapp.Week6.visitor;

import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;
import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.playlist.Playlist;

/**
 * Tydzień 6, Wzorzec Visitor 4
 * Konkretny visitor - optymalizuje wydajność i zasoby
 */
public class PerformanceOptimizationVisitor implements KaraokeVisitor {

    private int optimizationsApplied = 0;

    @Override
    public void visitSong(Song song) {
        System.out.println("[OPTIMIZATION-VISITOR] Analiza piosenki: " + song.getTitle());

        // Optymalizacja 1: Zmniejsz bitrate dla piosenek > 5 minut
        if (song.getDuration() > 300) {
            System.out.println("  ⚡ Zmniejszam bitrate dla długiej piosenki");
            optimizationsApplied++;
        }

        // Optymalizacja 2: Cache dla popularnych piosenek
        if (song.getDifficulty() <= 3) {
            System.out.println("  💾 Dodaję do cache (piosenka popularna)");
            optimizationsApplied++;
        }
    }

    @Override
    public void visitUser(User user) {
        System.out.println("[OPTIMIZATION-VISITOR] Analiza użytkownika: " + user.getNickname());

        // Optymalizacja: Preload danych dla high-level użytkowników
        if (user.getLevel() > 7) {
            System.out.println("  ⚡ Preload zasobów dla doświadczonego użytkownika");
            optimizationsApplied++;
        }
    }

    @Override
    public void visitPerformance(Performance performance) {
        System.out.println("[OPTIMIZATION-VISITOR] Analiza wydajności wykonania");

        int participants = performance.getParticipants().size();
        int songDuration = performance.getSong().getDuration();

        // Optymalizacja: Alokuj więcej buforów dla grupowych wykonań
        if (participants > 2) {
            System.out.println("  ⚡ Alokacja dodatkowych buforów dla " + participants + " uczestników");
            optimizationsApplied++;
        }

        // Optymalizacja: Kompresja audio dla krótkich piosenek
        if (songDuration < 180) {
            System.out.println("  ⚡ Kompresja audio (krótka piosenka)");
            optimizationsApplied++;
        }
    }

    @Override
    public void visitPlaylist(Playlist playlist) {
        System.out.println("[OPTIMIZATION-VISITOR] Analiza playlisty: " + playlist.getName());

        int songCount = playlist.getSongs().size();

        // Optymalizacja: Index dla dużych playlist
        if (songCount > 10) {
            System.out.println("  ⚡ Utworzenie indeksu dla playlisty (" + songCount + " piosenek)");
            optimizationsApplied++;
        }

        // Optymalizacja: Prefetch następnych piosenek
        if (songCount > 5) {
            System.out.println("  ⚡ Prefetch następnych piosenek");
            optimizationsApplied++;
        }
    }

    public int getOptimizationsApplied() {
        return optimizationsApplied;
    }

    @Override
    public String getVisitorName() {
        return "Performance Optimization Engine";
    }
}
// Koniec, Tydzień 6, Wzorzec Visitor 4
