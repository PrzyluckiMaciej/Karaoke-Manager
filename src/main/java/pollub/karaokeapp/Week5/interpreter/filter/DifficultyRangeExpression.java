package pollub.karaokeapp.Week5.interpreter.filter;

import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.Week5.command.song.SongEditor;

/**
 * Tydzień 5, Wzorzec Interpreter 1
 * Terminal – filtruje piosenki po zakresie trudności.
 */
public class DifficultyRangeExpression implements SongFilterExpression {

    private final int minDifficulty;
    private final int maxDifficulty;

    public DifficultyRangeExpression(int minDifficulty, int maxDifficulty) {
        validateDifficultyRange(minDifficulty, maxDifficulty);
        this.minDifficulty = minDifficulty;
        this.maxDifficulty = maxDifficulty;
    }

    private void validateDifficultyRange(int min, int max) {
        if (min < SongEditor.MIN_DIFFICULTY) {
            throw new IllegalArgumentException(
                    "Minimalna trudność nie może być mniejsza niż " + SongEditor.MIN_DIFFICULTY +
                            ". Podano: " + min
            );
        }
        if (max > SongEditor.MAX_DIFFICULTY) {
            throw new IllegalArgumentException(
                    "Maksymalna trudność nie może być większa niż " + SongEditor.MAX_DIFFICULTY +
                            ". Podano: " + max
            );
        }
        if (min > max) {
            throw new IllegalArgumentException(
                    "Minimalna trudność (" + min + ") nie może być większa od maksymalnej (" + max + ")"
            );
        }
    }

    @Override
    public boolean interpret(Song song) {
        if (song == null) {
            throw new IllegalArgumentException("Piosenka nie może być null");
        }
        int difficulty = song.getDifficulty();
        return difficulty >= minDifficulty && difficulty <= maxDifficulty;
    }

    @Override
    public String getExpressionDescription() {
        return "trudność∈[" + minDifficulty + "," + maxDifficulty + "]";
    }
}
// Koniec, Tydzień 5, Wzorzec Interpreter 1