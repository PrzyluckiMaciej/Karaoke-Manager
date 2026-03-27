package pollub.karaokeapp.Week5.command.song;

import pollub.karaokeapp.Week5.command.KaraokeCommand;

/**
 * Tydzień 5, Wzorzec Command 1
 * Komenda zmiany tytułu piosenki.
 * Deleguje faktyczną logikę do Receivera (SongEditor).
 */
public class ChangeSongTitleCommand implements KaraokeCommand {

    private final SongEditor receiver;
    private final String newTitle;
    private String previousTitle;

    public ChangeSongTitleCommand(SongEditor receiver, String newTitle) {
        this.receiver = receiver;
        this.newTitle = newTitle;
    }

    @Override
    public void execute() {
        previousTitle = receiver.getCurrentTitle();
        receiver.changeTitle(newTitle);
    }

    @Override
    public void undo() {
        System.out.println("[SONG-CMD] Cofanie zmiany tytułu: '" + newTitle + "' → '" + previousTitle + "'");
        receiver.changeTitle(previousTitle);
    }

    @Override
    public String getDescription() {
        return "Zmiana tytułu piosenki na '" + newTitle + "'";
    }
}
// Koniec, Tydzień 5, Wzorzec Command 1