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

    private static final int LONG_SONG_THRESHOLD_SECONDS = 300;
    private static final int POPULAR_SONG_MAX_DIFFICULTY = 3;
    private static final int EXPERT_USER_MIN_LEVEL = 7;
    private static final int GROUP_PERFORMANCE_MIN_SIZE = 2;
    private static final int SHORT_SONG_MAX_DURATION_SECONDS = 180;
    private static final int LARGE_PLAYLIST_MIN_SIZE = 10;
    private static final int PREFETCH_PLAYLIST_MIN_SIZE = 5;

    private int optimizationsApplied = 0;

    @Override
    public void visitSong(Song song) {
        System.out.println("[OPTIMIZATION-VISITOR] Analiza piosenki: " + song.getTitle());

        if (song.getDuration() > LONG_SONG_THRESHOLD_SECONDS) {
            System.out.println("  ⚡ Zmniejszam bitrate dla długiej piosenki");
            optimizationsApplied++;
        }

        if (song.getDifficulty() <= POPULAR_SONG_MAX_DIFFICULTY) {
            System.out.println("  💾 Dodaję do cache (piosenka popularna)");
            optimizationsApplied++;
        }
    }

    @Override
    public void visitUser(User user) {
        System.out.println("[OPTIMIZATION-VISITOR] Analiza użytkownika: " + user.getNickname());

        if (user.getLevel() > EXPERT_USER_MIN_LEVEL) {
            System.out.println("  ⚡ Preload zasobów dla doświadczonego użytkownika");
            optimizationsApplied++;
        }
    }

    @Override
    public void visitPerformance(Performance performance) {
        System.out.println("[OPTIMIZATION-VISITOR] Analiza wydajności wykonania");

        int participants = performance.getParticipants().size();
        int songDuration = performance.getSong().getDuration();

        if (participants > GROUP_PERFORMANCE_MIN_SIZE) {
            System.out.println("  ⚡ Alokacja dodatkowych buforów dla " + participants + " uczestników");
            optimizationsApplied++;
        }

        if (songDuration < SHORT_SONG_MAX_DURATION_SECONDS) {
            System.out.println("  ⚡ Kompresja audio (krótka piosenka)");
            optimizationsApplied++;
        }
    }

    @Override
    public void visitPlaylist(Playlist playlist) {
        System.out.println("[OPTIMIZATION-VISITOR] Analiza playlisty: " + playlist.getName());

        int songCount = playlist.getSongs().size();

        if (songCount > LARGE_PLAYLIST_MIN_SIZE) {
            System.out.println("  ⚡ Utworzenie indeksu dla playlisty (" + songCount + " piosenek)");
            optimizationsApplied++;
        }

        if (songCount > PREFETCH_PLAYLIST_MIN_SIZE) {
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