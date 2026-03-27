package pollub.karaokeapp.Week5.interpreter.playlist;

import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Tydzień 5, Wzorzec Interpreter 4 (cd.)
 * Kompozyt reguł playlist - pozwala na łączenie wielu reguł.
 * Nieterminalny – agreguje wiele reguł w jedną playlistę.
 */
public class CompositePlaylistRule implements PlaylistRuleExpression {

    private final List<PlaylistRuleExpression> rules = new ArrayList<>();
    private final String name;

    public CompositePlaylistRule(String name) {
        this.name = name;
    }

    public void addRule(PlaylistRuleExpression rule) {
        rules.add(rule);
    }

    public void removeRule(PlaylistRuleExpression rule) {
        rules.remove(rule);
    }

    public List<PlaylistRuleExpression> getRules() {
        return new ArrayList<>(rules);
    }

    @Override
    public Playlist generatePlaylist(List<Song> allSongs) {
        Playlist masterPlaylist = new Playlist(name + " - " + System.currentTimeMillis());

        for (PlaylistRuleExpression rule : rules) {
            Playlist generated = rule.generatePlaylist(allSongs);
            for (Song song : generated.getSongs()) {
                // Unikaj duplikatów
                if (!masterPlaylist.getSongs().contains(song)) {
                    masterPlaylist.addSong(song);
                }
            }
        }

        return masterPlaylist;
    }

    @Override
    public String getRuleDescription() {
        StringBuilder desc = new StringBuilder("Kompozyt Playlist: " + name + "\n");
        desc.append("Liczba reguł: ").append(rules.size()).append("\n");
        for (int i = 0; i < rules.size(); i++) {
            desc.append("\n  Reguła ").append(i + 1).append(":\n");
            String[] lines = rules.get(i).getRuleDescription().split("\n");
            for (String line : lines) {
                desc.append("    ").append(line).append("\n");
            }
        }
        return desc.toString();
    }
}
// Koniec, Tydzień 5, Wzorzec Interpreter 4 (cd.)