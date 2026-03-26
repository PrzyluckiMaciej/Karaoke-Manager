package pollub.karaokeapp.Week5.memento.song;

/**
 * Tydzień 5, Wzorzec Memento 1
 * Memento przechowujące migawkę stanu piosenki.
 * Klasa wewnętrzna – dostęp kontrolowany przez Originator (SongCaretaker).
 */
public class SongMemento {

    private final String title;
    private final String artist;
    private final int duration;
    private final String genre;
    private final int difficulty;
    private final long timestamp;

    public SongMemento(String title, String artist, int duration, String genre, int difficulty) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.genre = genre;
        this.difficulty = difficulty;
        this.timestamp = System.currentTimeMillis();
    }

    public String getTitle()      { return title; }
    public String getArtist()     { return artist; }
    public int getDuration()      { return duration; }
    public String getGenre()      { return genre; }
    public int getDifficulty()    { return difficulty; }
    public long getTimestamp()    { return timestamp; }

    @Override
    public String toString() {
        return "SongSnapshot{title='" + title + "', artist='" + artist +
                "', genre='" + genre + "', difficulty=" + difficulty + "}";
    }
}
// Koniec, Tydzień 5, Wzorzec Memento 1