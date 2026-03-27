package pollub.karaokeapp.Week5.command.song;

import pollub.karaokeapp.Week5.command.KaraokeCommand;

/**
 * Tydzień 5, Wzorzec Command 1 (cd.)
 * Komenda zmiany trudności piosenki.
 * Deleguje faktyczną logikę do Receivera (SongEditor).
 */
public class ChangeSongDifficultyCommand implements KaraokeCommand {

    private final SongEditor receiver;
    private final int newDifficulty;
    private int previousDifficulty;

    public ChangeSongDifficultyCommand(SongEditor receiver, int newDifficulty) {
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
        System.out.println("[SONG-CMD] Cofanie zmiany trudności: " + newDifficulty + " → " + previousDifficulty);
        receiver.changeDifficulty(previousDifficulty);
    }

    @Override
    public String getDescription() {
        return "Zmiana trudności piosenki '" + receiver.getSong().getTitle() + "' na " + newDifficulty;
    }
}
// Koniec, Tydzień 5, Wzorzec Command 1 (cd.)