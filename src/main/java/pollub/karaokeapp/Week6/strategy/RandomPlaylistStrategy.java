package pollub.karaokeapp.Week6.strategy;

import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Tydzień 6, Wzorzec Strategy 3
 * Strategia losowego odtwarzania piosenek
 */
public class RandomPlaylistStrategy implements PlaylistStrategy {

    @Override
    public List<?> execute(Playlist playlist) {
        List<Song> shuffled = new ArrayList<>(playlist.getSongs());
        Collections.shuffle(shuffled);
        System.out.println("[STRATEGY] Playlistę potasowano losowo (" + shuffled.size() + " piosenek)");
        return shuffled;
    }

    @Override
    public String getStrategyName() {
        return "RandomPlaylist";
    }
}
// Koniec, Tydzień 6, Wzorzec Strategy 3
