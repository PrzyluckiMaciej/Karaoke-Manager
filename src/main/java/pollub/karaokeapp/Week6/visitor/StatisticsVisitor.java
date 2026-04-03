package pollub.karaokeapp.Week6.visitor;

import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;
import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.playlist.Playlist;

/**
 * Tydzień 6, Wzorzec Visitor 2
 * Konkretny visitor - zbiera statystyki
 */
public class StatisticsVisitor implements KaraokeVisitor {

    private int songCount = 0;
    private int userCount = 0;
    private int performanceCount = 0;
    private int totalDuration = 0;

    @Override
    public void visitSong(Song song) {
        songCount++;
        totalDuration += song.getDuration();
        System.out.println("[STATS-VISITOR] Piosenka: " + song.getTitle() + " (" + song.getDuration() + "s)");
    }

    @Override
    public void visitUser(User user) {
        userCount++;
        System.out.println("[STATS-VISITOR] Użytkownik: " + user.getNickname() + ", Poziom: " + user.getLevel());
    }

    @Override
    public void visitPerformance(Performance performance) {
        performanceCount++;
        System.out.println("[STATS-VISITOR] Wykonanie: " + performance.getSong().getTitle() +
                ", Wynik: " + performance.getScore());
    }

    @Override
    public void visitPlaylist(Playlist playlist) {
        System.out.println("[STATS-VISITOR] Playlista: " + playlist.getName() +
                ", Piosenek: " + playlist.getSongs().size());
    }

    public void printStatistics() {
        System.out.println("\n=== STATYSTYKI ===");
        System.out.println("Liczba piosenek: " + songCount);
        System.out.println("Liczba użytkowników: " + userCount);
        System.out.println("Liczba wykonań: " + performanceCount);
        System.out.println("Łączny czas piosenek: " + totalDuration + "s");
    }

    @Override
    public String getVisitorName() {
        return "Statistics Collector";
    }
}
// Koniec, Tydzień 6, Wzorzec Visitor 2
