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
        System.out.println("[MACRO-CMD] Wykonywanie makra: '" + name + "' (" + commands.size() + " komend)");
        for (KaraokeCommand cmd : commands) {
            cmd.execute();
        }
    }

    @Override
    public void undo() {
        System.out.println("[MACRO-CMD] Cofanie makra: '" + name + "'");
        List<KaraokeCommand> reversed = new ArrayList<>(commands);
        Collections.reverse(reversed);
        for (KaraokeCommand cmd : reversed) {
            cmd.undo();
        }
    }

    @Override
    public String getDescription() {
        return "Makrokomenda '" + name + "' (" + commands.size() + " kroków)";
    }
}
// Koniec, Tydzień 5, Wzorzec Command