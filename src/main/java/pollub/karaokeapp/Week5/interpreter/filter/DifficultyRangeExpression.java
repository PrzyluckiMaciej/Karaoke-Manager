package pollub.karaokeapp.Week5.interpreter.filter;

import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 5, Wzorzec Interpreter 1
 * Terminal – filtruje piosenki po zakresie trudności.
 */
public class DifficultyRangeExpression implements SongFilterExpression {

    private final int minDifficulty;
    private final int maxDifficulty;

    public DifficultyRangeExpression(int minDifficulty, int maxDifficulty) {
        this.minDifficulty = minDifficulty;
        this.maxDifficulty = maxDifficulty;
    }

    @Override
    public boolean interpret(Song song) {
        return song.getDifficulty() >= minDifficulty && song.getDifficulty() <= maxDifficulty;
    }

    @Override
    public String getExpressionDescription() {
        return "trudność∈[" + minDifficulty + "," + maxDifficulty + "]";
    }
}
// Koniec, Tydzień 5, Wzorzec Interpreter 1