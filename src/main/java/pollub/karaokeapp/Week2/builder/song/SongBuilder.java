package pollub.karaokeapp.Week2.builder.song;

import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 2, Wzorzec Builder 1
 * Builder dla piosenki z wymaganymi polami title i artist,
 * pozostałe parametry opcjonalne.
 */
public class SongBuilder {

    private final String title;
    private final String artist;

    private int duration;
    private String genre;
    private int difficulty;

    public SongBuilder(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public SongBuilder setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public SongBuilder setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public SongBuilder setDifficulty(int difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public Song build() {
        return new Song(title, artist, duration, genre, difficulty);
    }
}
// Koniec, Tydzień 2, Wzorzec Builder 1