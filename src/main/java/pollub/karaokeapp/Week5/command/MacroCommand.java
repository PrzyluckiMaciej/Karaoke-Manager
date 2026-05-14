package pollub.karaokeapp.Week5.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Tydzień 5, Wzorzec Command
 * Makrokomenda – wykonuje sekwencję komend jako jedną operację.
 * Undo cofa komendy w odwrotnej kolejności.
 */
public class MacroCommand implements KaraokeCommand {

    private static final String LOG_PREFIX = "[MACRO-CMD]";

    private final String name;
    private final List<KaraokeCommand> commands = new ArrayList<>();

    public MacroCommand(String name) {
        this.name = name;
    }

    public void addCommand(KaraokeCommand command) {
        commands.add(command);
    }

    @Override
    public void execute() {
        logExecutionStart();
        executeAllCommands();
    }

    @Override
    public void undo() {
        logUndoStart();
        undoAllCommandsInReverseOrder();
    }

    @Override
    public String getDescription() {
        return "Makrokomenda '" + name + "' (" + commands.size() + " kroków)";
    }

    private void logExecutionStart() {
        System.out.println(LOG_PREFIX + " Wykonywanie makra: '" + name + "' (" + commands.size() + " komend)");
    }

    private void logUndoStart() {
        System.out.println(LOG_PREFIX + " Cofanie makra: '" + name + "'");
    }

    private void executeAllCommands() {
        for (KaraokeCommand cmd : commands) {
            cmd.execute();
        }
    }

    private void undoAllCommandsInReverseOrder() {
        List<KaraokeCommand> reversed = new ArrayList<>(commands);
        Collections.reverse(reversed);
        for (KaraokeCommand cmd : reversed) {
            cmd.undo();
        }
    }
}
// Koniec, Tydzień 5, Wzorzec Command