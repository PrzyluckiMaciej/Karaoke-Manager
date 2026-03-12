package pollub.karaokeapp.composite.playlist;

import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 3, Wzorzec Composite 1
 * Interfejs wspólny dla pojedynczych piosenek i grup playlist
 */
public interface PlaylistComponent {
    String getName();
    int getTotalDuration();
    int getSongCount();
    void display(String indent);
    Song getSong(int index);
}
// Koniec, Tydzień 3, Wzorzec Composite 1