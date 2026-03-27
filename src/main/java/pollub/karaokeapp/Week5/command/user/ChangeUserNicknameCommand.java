package pollub.karaokeapp.Week5.command.user;

import pollub.karaokeapp.Week5.command.KaraokeCommand;

/**
 * Tydzień 5, Wzorzec Command 4
 * Komenda zmiany nicku użytkownika.
 * Deleguje faktyczną logikę do Receivera (UserProfileEditor).
 */
public class ChangeUserNicknameCommand implements KaraokeCommand {

    private final UserProfileEditor receiver;
    private final String newNickname;
    private String previousNickname;

    public ChangeUserNicknameCommand(UserProfileEditor receiver, String newNickname) {
        this.receiver = receiver;
        this.newNickname = newNickname;
    }

    @Override
    public void execute() {
        previousNickname = receiver.getCurrentNickname();
        receiver.changeNickname(newNickname);
    }

    @Override
    public void undo() {
        System.out.println("[USER-CMD] Cofanie zmiany nicku: '" + newNickname + "' → '" + previousNickname + "'");
        receiver.changeNickname(previousNickname);
    }

    @Override
    public String getDescription() {
        return "Zmiana nicku użytkownika na '" + newNickname + "'";
    }
}
// Koniec, Tydzień 5, Wzorzec Command 4