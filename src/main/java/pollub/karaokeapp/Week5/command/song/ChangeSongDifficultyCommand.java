package pollub.karaokeapp.Week5.command.song;

import pollub.karaokeapp.Week5.command.KaraokeCommand;
import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 5, Wzorzec Command 2
 * Komenda zmiany trudności piosenki (z obsługą undo).
 */
public class ChangeSongDifficultyCommand implements KaraokeCommand {

    private final Song song;
    private final int newDifficulty;
    private int previousDifficulty;

    public ChangeSongDifficultyCommand(Song song, int newDifficulty) {
        this.song = song;
        this.newDifficulty = newDifficulty;
    }

    @Override
    public void execute() {
        previousDifficulty = song.getDifficulty();
        song.setDifficulty(newDifficulty);
        System.out.println("[SONG-CMD] Trudność zmieniona: " + previousDifficulty + " → " + newDifficulty);
    }

    @Override
    public void undo() {
        song.setDifficulty(previousDifficulty);
        System.out.println("[SONG-CMD] Cofnięto zmianę trudności: " + newDifficulty + " → " + previousDifficulty);
    }

    @Override
    public String getDescription() {
        return "Zmiana trudności piosenki '" + song.getTitle() + "' na " + newDifficulty;
    }
}
// Koniec, Tydzień 5, Wzorzec Command 2