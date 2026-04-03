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

    private boolean ascending;

    public SortByDifficultyStrategy(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public List<?> execute(Playlist playlist) {
        List<Song> songs = playlist.getSongs().stream()
                .sorted((s1, s2) -> ascending ?
                        Integer.compare(s1.getDifficulty(), s2.getDifficulty()) :
                        Integer.compare(s2.getDifficulty(), s1.getDifficulty()))
                .collect(Collectors.toList());

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
