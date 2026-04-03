package pollub.karaokeapp.Week6.visitor;

import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;
import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.playlist.Playlist;

/**
 * Tydzień 6, Wzorzec Visitor 1
 * Interfejs visitora - definiuje operacje na różnych
 * elementach karaoke
 */
public interface KaraokeVisitor {
    void visitSong(Song song);
    void visitUser(User user);
    void visitPerformance(Performance performance);
    void visitPlaylist(Playlist playlist);
    String getVisitorName();
}
// Koniec, Tydzień 6, Wzorzec Visitor 1
