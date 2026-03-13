package pollub.karaokeapp.Week3.composite.playlist;

import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 3, Wzorzec Composite 1
 * Liść - pojedyncza piosenka
 */
public class SongLeaf implements PlaylistComponent {

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
        return 1;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "🎵 " + getName() + " (" + song.getDuration() + "s)");
    }

    @Override
    public Song getSong(int index) {
        if (index == 0) {
            return song;
        }
        throw new IndexOutOfBoundsException("Index: " + index);
    }
}
// Koniec, Tydzień 3, Wzorzec Composite 1