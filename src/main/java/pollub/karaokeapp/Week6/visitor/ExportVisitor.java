package pollub.karaokeapp.Week6.visitor;

import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;
import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.playlist.Playlist;

/**
 * Tydzień 6, Wzorzec Visitor 4
 * Konkretny visitor - eksportuje dane do formatu tekstowego
 */
public class ExportVisitor implements KaraokeVisitor {

    private StringBuilder exportData = new StringBuilder();

    @Override
    public void visitSong(Song song) {
        String songData = String.format("SONG|%s|%s|%d|%s|%d\n",
                song.getTitle(), song.getArtist(), song.getDuration(),
                song.getGenre(), song.getDifficulty());
        exportData.append(songData);
        System.out.println("[EXPORT-VISITOR] Eksportowana piosenka: " + song.getTitle());
    }

    @Override
    public void visitUser(User user) {
        String userData = String.format("USER|%s|%d|%d\n",
                user.getNickname(), user.getLevel(), user.getPoints());
        exportData.append(userData);
        System.out.println("[EXPORT-VISITOR] Eksportowany użytkownik: " + user.getNickname());
    }

    @Override
    public void visitPerformance(Performance performance) {
        String perfData = String.format("PERFORMANCE|%s|%d|%d\n",
                performance.getSong().getTitle(),
                performance.getParticipants().size(),
                performance.getScore());
        exportData.append(perfData);
        System.out.println("[EXPORT-VISITOR] Eksportowane wykonanie");
    }

    @Override
    public void visitPlaylist(Playlist playlist) {
        String playlistData = String.format("PLAYLIST|%s|%d\n",
                playlist.getName(), playlist.getSongs().size());
        exportData.append(playlistData);
        System.out.println("[EXPORT-VISITOR] Eksportowana playlista: " + playlist.getName());
    }

    public String getExportedData() {
        return exportData.toString();
    }

    public void saveToFile(String filename) {
        System.out.println("[EXPORT] Zapisywanie do pliku: " + filename);
        System.out.println("--- ZAWARTOŚĆ PLIKU ---");
        System.out.println(exportData.toString());
        System.out.println("--- KONIEC ---");
    }

    @Override
    public String getVisitorName() {
        return "Export Engine";
    }
}
// Koniec, Tydzień 6, Wzorzec Visitor 4
