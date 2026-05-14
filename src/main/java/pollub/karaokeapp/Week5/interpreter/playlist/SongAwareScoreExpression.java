package pollub.karaokeapp.Week5.interpreter.playlist;

import pollub.karaokeapp.Week5.interpreter.scoring.ScoreExpression;
import pollub.karaokeapp.model.song.Song;

import java.util.Set;

/**
 * Tydzień 5, Wzorzec Interpreter 4 (cd.)
 * Rozszerzenie ScoreExpression o możliwość użycia piosenki w kontekście.
 * Dekorator – dodaje świadomość piosenki do wyrażenia punktowego.
 */
public class SongAwareScoreExpression implements ScoreExpression {

    public static final int HIGH_DIFFICULTY_THRESHOLD = 8;
    public static final int MEDIUM_DIFFICULTY_THRESHOLD = 5;
    public static final int HIGH_DIFFICULTY_BONUS = 50;
    public static final int MEDIUM_DIFFICULTY_BONUS = 25;
    public static final int LONG_SONG_DURATION = 300;
    public static final int LONG_SONG_BONUS = 20;
    public static final int SHORT_SONG_DURATION = 180;
    public static final int SHORT_SONG_BONUS = 10;
    public static final int POPULAR_GENRE_BONUS = 15;

    private static final Set<String> POPULAR_GENRES = Set.of("rock", "pop");

    private final ScoreExpression baseExpression;
    private final Song song;

    public SongAwareScoreExpression(ScoreExpression baseExpression, Song song) {
        if (baseExpression == null) {
            throw new IllegalArgumentException("Bazowe wyrażenie punktowe nie może być null");
        }
        if (song == null) {
            throw new IllegalArgumentException("Piosenka nie może być null");
        }
        this.baseExpression = baseExpression;
        this.song = song;
    }

    @Override
    public int interpret() {
        int baseScore = baseExpression.interpret();
        baseScore += calculateDifficultyBonus();
        baseScore += calculateDurationBonus();
        baseScore += calculateGenreBonus();
        return baseScore;
    }

    private int calculateDifficultyBonus() {
        int difficulty = song.getDifficulty();
        if (difficulty >= HIGH_DIFFICULTY_THRESHOLD) {
            return HIGH_DIFFICULTY_BONUS;
        } else if (difficulty >= MEDIUM_DIFFICULTY_THRESHOLD) {
            return MEDIUM_DIFFICULTY_BONUS;
        }
        return 0;
    }

    private int calculateDurationBonus() {
        int duration = song.getDuration();
        if (duration > LONG_SONG_DURATION) {
            return LONG_SONG_BONUS;
        } else if (duration < SHORT_SONG_DURATION) {
            return SHORT_SONG_BONUS;
        }
        return 0;
    }

    private int calculateGenreBonus() {
        String genre = song.getGenre().toLowerCase();
        if (POPULAR_GENRES.contains(genre)) {
            return POPULAR_GENRE_BONUS;
        }
        return 0;
    }

    @Override
    public String getExpressionDescription() {
        return "SongAware(" + baseExpression.getExpressionDescription() +
                ", song=" + song.getTitle() +
                ", diff=" + song.getDifficulty() +
                ", duration=" + song.getDuration() + "s)";
    }
}
// Koniec, Tydzień 5, Wzorzec Interpreter 4 (cd.)