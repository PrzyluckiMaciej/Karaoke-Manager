package pollub.karaokeapp.Week6.strategy;

import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Tydzień 6, Wzorzec Strategy 4
 * Strategia sortowania piosenek po czasie trwania
 */
public class SortByDurationStrategy implements PlaylistStrategy {

    private final boolean ascending;

    public SortByDurationStrategy(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public List<?> execute(Playlist playlist) {
        List<Song> songs = SongSorter.sort(playlist, SongSorter.byDuration(ascending));

        System.out.println("[STRATEGY] Posortowano playlistę po czasie trwania (" +
                (ascending ? "od najkrótszych" : "od najdłuższych") + ")");
        return songs;
    }

    @Override
    public String getStrategyName() {
        return "SortByDuration_" + (ascending ? "ASC" : "DESC");
    }
}
// Koniec, Tydzień 6, Wzorzec Strategy 4
