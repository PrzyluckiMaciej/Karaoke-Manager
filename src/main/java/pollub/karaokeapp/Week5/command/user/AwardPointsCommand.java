package pollub.karaokeapp.Week5.command.user;

import pollub.karaokeapp.Week5.command.KaraokeCommand;

/**
 * Tydzień 5, Wzorzec Command 4 (cd.)
 * Komenda przyznania punktów użytkownikowi.
 * Deleguje faktyczną logikę do Receivera (UserProfileEditor).
 */
public class AwardPointsCommand implements KaraokeCommand {

    private final UserProfileEditor receiver;
    private final int points;
    private final String reason;

    public AwardPointsCommand(UserProfileEditor receiver, int points, String reason) {
        this.receiver = receiver;
        this.points = points;
        this.reason = reason;
    }

    @Override
    public void execute() {
        System.out.println("[USER-CMD] Przyznawanie punktów za: " + reason);
        receiver.addPoints(points);
    }

    @Override
    public void undo() {
        System.out.println("[USER-CMD] Cofanie przyznania punktów za: " + reason);
        receiver.subtractPoints(points);
    }

    @Override
    public String getDescription() {
        return "Przyznaj " + points + " pkt użytkownikowi '" + receiver.getUser().getNickname() + "' za: " + reason;
    }
}
// Koniec, Tydzień 5, Wzorzec Command 4 (cd.)