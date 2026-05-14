package pollub.karaokeapp.Week3.composite.playlist;

import pollub.karaokeapp.Week3.composite.utils.IndexUtils;
import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 3, Wzorzec Composite 1
 * Liść - pojedyncza piosenka
 */
public class SongLeaf implements PlaylistComponent {

    private static final int SINGLE_SONG_COUNT = 1;
    private static final int FIRST_ELEMENT_INDEX = 0;

    private Song song;

    public SongLeaf(Song song) {
        this.song = song;
    }

    @Override
    public String getName() {
        return song.getTitle() + " - " + song.getArtist();
    }

    @Override
    public int getTotalDuration() {
        return song.getDuration();
    }

    @Override
    public int getSongCount() {
        return SINGLE_SONG_COUNT;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "🎵 " + getName() + " (" + song.getDuration() + "s)");
    }

    @Override
    public Song getSong(int index) {
        if (IndexUtils.isFirstElement(index)) {
            return song;
        }
        throw new IndexOutOfBoundsException("Index: " + index + ", dozwolony tylko 0");
    }
}