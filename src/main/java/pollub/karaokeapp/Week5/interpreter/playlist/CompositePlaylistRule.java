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
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nazwa kompozytu nie może być pusta");
        }
        this.name = name;
    }

    public void addRule(PlaylistRuleExpression rule) {
        if (rule == null) {
            throw new IllegalArgumentException("Reguła nie może być null");
        }
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
        validateSongList(allSongs);

        Playlist masterPlaylist = createEmptyPlaylist();
        mergeAllRulePlaylists(allSongs, masterPlaylist);
        return masterPlaylist;
    }

    private void validateSongList(List<Song> allSongs) {
        if (allSongs == null) {
            throw new IllegalArgumentException("Lista piosenek nie może być null");
        }
    }

    private Playlist createEmptyPlaylist() {
        return new Playlist(name + " - " + System.currentTimeMillis());
    }

    private void mergeAllRulePlaylists(List<Song> allSongs, Playlist masterPlaylist) {
        for (PlaylistRuleExpression rule : rules) {
            Playlist generated = rule.generatePlaylist(allSongs);
            mergePlaylistWithoutDuplicates(generated, masterPlaylist);
        }
    }

    private void mergePlaylistWithoutDuplicates(Playlist source, Playlist target) {
        for (Song song : source.getSongs()) {
            if (!target.getSongs().contains(song)) {
                target.addSong(song);
            }
        }
    }

    @Override
    public String getRuleDescription() {
        StringBuilder desc = new StringBuilder();
        desc.append("Kompozyt Playlist: ").append(name).append("\n");
        desc.append("Liczba reguł: ").append(rules.size()).append("\n");

        for (int i = 0; i < rules.size(); i++) {
            appendRuleDescription(desc, i, rules.get(i));
        }
        return desc.toString();
    }

    private void appendRuleDescription(StringBuilder desc, int index, PlaylistRuleExpression rule) {
        desc.append("\n  Reguła ").append(index + 1).append(":\n");
        String[] lines = rule.getRuleDescription().split("\n");
        for (String line : lines) {
            desc.append("    ").append(line).append("\n");
        }
    }
}
// Koniec, Tydzień 5, Wzorzec Interpreter 4 (cd.)