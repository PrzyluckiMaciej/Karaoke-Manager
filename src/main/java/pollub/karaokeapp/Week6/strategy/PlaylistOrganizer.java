package pollub.karaokeapp.Week6.strategy;

import pollub.karaokeapp.model.playlist.Playlist;
import java.util.List;

/**
 * Tydzień 6, Wzorzec Strategy 4
 * Kontekst - organizator playlisty z wymianą strategii
 */
public class PlaylistOrganizer {

    private PlaylistStrategy strategy;

    public PlaylistOrganizer(PlaylistStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PlaylistStrategy strategy) {
        this.strategy = strategy;
        System.out.println("[ORGANIZER] Zmiana strategii na: " + strategy.getStrategyName());
    }

    public List<?> organize(Playlist playlist) {
        return strategy.execute(playlist);
    }

    public String getCurrentStrategy() {
        return strategy.getStrategyName();
    }
}
// Koniec, Tydzień 6, Wzorzec Strategy 4
