package pollub.karaokeapp.Week5.iterator.playlist;

import pollub.karaokeapp.Week5.iterator.KaraokeIterator;
import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 5, Wzorzec Iterator 1
 * Iterator sekwencyjny dla playlisty – przechodzi po piosenkach po kolei.
 */
public class PlaylistIterator implements KaraokeIterator<Song> {

    private final Playlist playlist;
    private int currentIndex = 0;

    public PlaylistIterator(Playlist playlist) {
        this.playlist = playlist;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < playlist.getSongs().size();
    }

    @Override
    public Song next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException("Koniec playlisty");
        }
        return playlist.getSongs().get(currentIndex++);
    }

    @Override
    public void reset() {
        currentIndex = 0;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }
}
// Koniec, Tydzień 5, Wzorzec Iterator 1