package pollub.karaokeapp.Week5.command.song;

import pollub.karaokeapp.Week5.command.KaraokeCommand;

/**
 * Tydzień 5, Wzorzec Command 1 (cd.)
 * Komenda zmiany trudności piosenki.
 * Deleguje faktyczną logikę do Receivera (SongEditor).
 */
public class ChangeSongDifficultyCommand implements KaraokeCommand {

    private static final String LOG_PREFIX = "[SONG-CMD]";

    private final SongEditor receiver;
    private final int newDifficulty;
    private int previousDifficulty;

    public ChangeSongDifficultyCommand(SongEditor receiver, int newDifficulty) {
        if (receiver == null) {
            throw new IllegalArgumentException("Receiver nie może być null");
        }
        validateDifficultyRange(newDifficulty);
        this.receiver = receiver;
        this.newDifficulty = newDifficulty;
    }

    @Override
    public void execute() {
        previousDifficulty = receiver.getCurrentDifficulty();
        receiver.changeDifficulty(newDifficulty);
    }

    @Override
    public void undo() {
        logUndo();
        receiver.changeDifficulty(previousDifficulty);
    }

    @Override
    public String getDescription() {
        return "Zmiana trudności piosenki '" + receiver.getSong().getTitle() + "' na " + newDifficulty;
    }

    private void validateDifficultyRange(int difficulty) {
        if (difficulty < SongEditor.MIN_DIFFICULTY || difficulty > SongEditor.MAX_DIFFICULTY) {
            throw new IllegalArgumentException(
                    "Trudność musi być między " + SongEditor.MIN_DIFFICULTY +
                            " a " + SongEditor.MAX_DIFFICULTY + ". Podano: " + difficulty
            );
        }
    }

    private void logUndo() {
        System.out.println(LOG_PREFIX + " Cofanie zmiany trudności: " +
                newDifficulty + " → " + previousDifficulty);
    }
}
// Koniec, Tydzień 5, Wzorzec Command 1 (cd.)