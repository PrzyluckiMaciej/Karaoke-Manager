package pollub.karaokeapp.model.song;

import pollub.karaokeapp.Week2.prototype.Prototype;
import pollub.karaokeapp.Week6.visitor.KaraokeVisitor;

/**
 * Tydzień 2, Wzorzec Prototype 2
 * Klasa Song implementuje interfejs Prototype,
 * umożliwiając klonowanie obiektów piosenek (np. remix).
 */
public class Song implements Prototype<Song> {

    private String title;
    private String artist;
    private int duration;
    private String genre;
    private int difficulty;

    public Song(String title, String artist, int duration, String genre, int difficulty) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.genre = genre;
        this.difficulty = difficulty;
    }

    @Override
    public Song clone() {
        return new Song(title, artist, duration, genre, difficulty);
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", duration=" + duration +
                ", genre='" + genre + '\'' +
                ", difficulty=" + difficulty +
                '}';
    }

    public void accept(KaraokeVisitor visitor) {
        visitor.visitSong(this);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
//Koniec, Tydzień 2, Wzorzec Prototype 2