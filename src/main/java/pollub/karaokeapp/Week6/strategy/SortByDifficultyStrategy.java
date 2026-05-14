package pollub.karaokeapp.Week6.strategy;

import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Tydzień 6, Wzorzec Strategy 2
 * Strategia sortowania piosenek po trudności
 */
public class SortByDifficultyStrategy implements PlaylistStrategy {

    private final boolean ascending;

    public SortByDifficultyStrategy(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public List<?> execute(Playlist playlist) {
        List<Song> songs = SongSorter.sort(playlist, SongSorter.byDifficulty(ascending));

        System.out.println("[STRATEGY] Posortowano playlistę po trudności (" +
                (ascending ? "rosnąco" : "malejąco") + ")");
        return songs;
    }

    @Override
    public String getStrategyName() {
        return "SortByDifficulty_" + (ascending ? "ASC" : "DESC");
    }
}
// Koniec, Tydzień 6, Wzorzec Strategy 2
