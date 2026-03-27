package pollub.karaokeapp.Week5.interpreter.playlist;

import pollub.karaokeapp.Week5.interpreter.scoring.ScoreExpression;
import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 5, Wzorzec Interpreter 4 (cd.)
 * Rozszerzenie ScoreExpression o możliwość użycia piosenki w kontekście.
 * Dekorator – dodaje świadomość piosenki do wyrażenia punktowego.
 */
public class SongAwareScoreExpression implements ScoreExpression {

    private final ScoreExpression baseExpression;
    private final Song song;

    public SongAwareScoreExpression(ScoreExpression baseExpression, Song song) {
        this.baseExpression = baseExpression;
        this.song = song;
    }

    @Override
    public int interpret() {
        int baseScore = baseExpression.interpret();

        // Dodatkowe modyfikatory oparte na piosence
        if (song.getDifficulty() >= 8) {
            baseScore += 50; // bonus za bardzo trudne piosenki
        } else if (song.getDifficulty() >= 5) {
            baseScore += 25; // bonus za średnio trudne
        }

        if (song.getDuration() > 300) {
            baseScore += 20; // bonus za długie utwory (>5 min)
        } else if (song.getDuration() < 180) {
            baseScore += 10; // bonus za krótkie utwory (<3 min)
        }

        // Bonus za popularne gatunki
        String genre = song.getGenre().toLowerCase();
        if (genre.equals("rock") || genre.equals("pop")) {
            baseScore += 15;
        }

        return baseScore;
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