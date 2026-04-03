package pollub.karaokeapp.Week6.strategy;

import pollub.karaokeapp.model.playlist.Playlist;
import java.util.List;

/**
 * Tydzień 6, Wzorzec Strategy 1
 * Strategia dla organizacji/wyszukiwania piosenek w playliście
 */
public interface PlaylistStrategy {
    List<?> execute(Playlist playlist);
    String getStrategyName();
}
// Koniec, Tydzień 6, Wzorzec Strategy 1
