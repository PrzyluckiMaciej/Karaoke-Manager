package pollub.karaokeapp.Week5.command.song;

import pollub.karaokeapp.Week5.command.KaraokeCommand;
import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 5, Wzorzec Command 1
 * Komenda zmiany tytułu piosenki (z obsługą undo).
 */
public class ChangeSongTitleCommand implements KaraokeCommand {

    private final Song song;
    private final String newTitle;
    private String previousTitle;

    public ChangeSongTitleCommand(Song song, String newTitle) {
        this.song = song;
        this.newTitle = newTitle;
    }

    @Override
    public void execute() {
        previousTitle = song.getTitle();
        song.setTitle(newTitle);
        System.out.println("[SONG-CMD] Tytuł zmieniony: '" + previousTitle + "' → '" + newTitle + "'");
    }

    @Override
    public void undo() {
        song.setTitle(previousTitle);
        System.out.println("[SONG-CMD] Cofnięto zmianę tytułu: '" + newTitle + "' → '" + previousTitle + "'");
    }

    @Override
    public String getDescription() {
        return "Zmiana tytułu piosenki na '" + newTitle + "'";
    }
}
// Koniec, Tydzień 5, Wzorzec Command 1