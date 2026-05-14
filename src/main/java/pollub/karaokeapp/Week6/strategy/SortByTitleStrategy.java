package pollub.karaokeapp.Week6.strategy;

import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Tydzień 6, Wzorzec Strategy 4
 * Strategia sortowania piosenek alfabetycznie po tytule
 */
public class SortByTitleStrategy implements PlaylistStrategy {

    private final boolean ascending;

    public SortByTitleStrategy(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public List<?> execute(Playlist playlist) {
        List<Song> songs = SongSorter.sort(playlist, SongSorter.byTitle(ascending));

        System.out.println("[STRATEGY] Posortowano playlistę po tytule (" +
                (ascending ? "A-Z" : "Z-A") + ")");
        return songs;
    }

    @Override
    public String getStrategyName() {
        return "SortByTitle_" + (ascending ? "ASC" : "DESC");
    }
}
// Koniec, Tydzień 6, Wzorzec Strategy 4
