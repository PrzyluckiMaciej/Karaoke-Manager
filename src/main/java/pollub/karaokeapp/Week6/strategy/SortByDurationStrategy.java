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

    private boolean ascending;

    public SortByDurationStrategy(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public List<?> execute(Playlist playlist) {
        List<Song> songs = playlist.getSongs().stream()
                .sorted((s1, s2) -> ascending ?
                        Integer.compare(s1.getDuration(), s2.getDuration()) :
                        Integer.compare(s2.getDuration(), s1.getDuration()))
                .collect(Collectors.toList());

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
