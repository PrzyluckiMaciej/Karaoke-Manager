package pollub.karaokeapp.Week5.memento.performance;

/**
 * Tydzień 5, Wzorzec Memento 2
 * Migawka stanu wykonania (wynik i liczba uczestników).
 */
public class PerformanceMemento {

    private final int score;
    private final int participantCount;
    private final String songTitle;
    private final long timestamp;

    public PerformanceMemento(int score, int participantCount, String songTitle) {
        this.score = score;
        this.participantCount = participantCount;
        this.songTitle = songTitle;
        this.timestamp = System.currentTimeMillis();
    }

    public int getScore()            { return score; }
    public int getParticipantCount() { return participantCount; }
    public String getSongTitle()     { return songTitle; }
    public long getTimestamp()       { return timestamp; }

    @Override
    public String toString() {
        return "PerformanceSnapshot{song='" + songTitle + "', score=" + score +
                ", participants=" + participantCount + "}";
    }
}
// Koniec, Tydzień 5, Wzorzec Memento 2