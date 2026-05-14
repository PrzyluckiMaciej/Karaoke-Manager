package pollub.karaokeapp.Week5.iterator.playlist;

import pollub.karaokeapp.Week5.iterator.AbstractKaraokeIterator;
import pollub.karaokeapp.Week5.iterator.KaraokeIterator;
import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 5, Wzorzec Iterator 1
 * Iterator sekwencyjny dla playlisty – przechodzi po piosenkach po kolei.
 */
public class PlaylistIterator extends AbstractKaraokeIterator<Song> {

    public PlaylistIterator(Playlist playlist) {
        super(playlist.getSongs());
    }

    @Override
    public boolean hasNext() {
        return currentIndex < items.size();
    }

    @Override
    protected String getNoElementsMessage() {
        return "Osiągnięto koniec playlisty";
    }

    public int getNextIndex() {
        return currentIndex;
    }

    public boolean isAtStart() {
        return currentIndex == 0;
    }

    public boolean isAtEnd() {
        return currentIndex >= items.size();
    }
}
// Koniec, Tydzień 5, Wzorzec Iterator 1