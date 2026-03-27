package pollub.karaokeapp.Week5.command.user;

import pollub.karaokeapp.Week5.command.KaraokeCommand;

/**
 * Tydzień 5, Wzorzec Command 4 (cd.)
 * Komenda zmiany poziomu użytkownika.
 * Deleguje faktyczną logikę do Receivera (UserProfileEditor).
 */
public class ChangeUserLevelCommand implements KaraokeCommand {

    private final UserProfileEditor receiver;
    private final int newLevel;
    private int previousLevel;

    public ChangeUserLevelCommand(UserProfileEditor receiver, int newLevel) {
        this.receiver = receiver;
        this.newLevel = newLevel;
    }

    @Override
    public void execute() {
        previousLevel = receiver.getCurrentLevel();
        receiver.changeLevel(newLevel);
    }

    @Override
    public void undo() {
        System.out.println("[USER-CMD] Cofanie zmiany poziomu: " + newLevel + " → " + previousLevel);
        receiver.changeLevel(previousLevel);
    }

    @Override
    public String getDescription() {
        return "Zmiana poziomu użytkownika '" + receiver.getUser().getNickname() + "' na " + newLevel;
    }
}
// Koniec, Tydzień 5, Wzorzec Command 4 (cd.)