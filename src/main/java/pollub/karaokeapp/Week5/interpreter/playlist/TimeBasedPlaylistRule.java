package pollub.karaokeapp.Week5.interpreter.playlist;

import pollub.karaokeapp.Week5.interpreter.filter.SongFilterExpression;
import pollub.karaokeapp.Week5.interpreter.scoring.ScoreExpression;
import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.playlist.Playlist;

import java.util.List;

/**
 * Tydzień 5, Wzorzec Interpreter 4 (cd.)
 * Reguła playlisty z ograniczeniem czasowym - generuje playlistę o określonej długości.
 * Rozszerzenie SmartPlaylistRule o kontrolę całkowitego czasu trwania.
 */
public class TimeBasedPlaylistRule implements PlaylistRuleExpression {

    private final SmartPlaylistRule baseRule;
    private final int maxDurationSeconds;
    private final String ruleName;

    public TimeBasedPlaylistRule(String ruleName, SongFilterExpression condition,
                                 ScoreExpression scoringRule, int maxDurationSeconds) {
        this.ruleName = ruleName;
        this.baseRule = new SmartPlaylistRule(ruleName, condition, scoringRule, Integer.MAX_VALUE);
        this.maxDurationSeconds = maxDurationSeconds;
    }

    @Override
    public Playlist generatePlaylist(List<Song> allSongs) {
        Playlist fullPlaylist = baseRule.generatePlaylist(allSongs);
        Playlist timeLimitedPlaylist = new Playlist(ruleName + " (czas: " + maxDurationSeconds + "s)");

        int currentDuration = 0;

        for (Song song : fullPlaylist.getSongs()) {
            if (currentDuration + song.getDuration() <= maxDurationSeconds) {
                timeLimitedPlaylist.addSong(song);
                currentDuration += song.getDuration();
            } else {
                break;
            }
        }

        return timeLimitedPlaylist;
    }

    @Override
    public String getRuleDescription() {
        return "Reguła z limitem czasowym: " + ruleName + "\n" +
                "  Maksymalny czas: " + (maxDurationSeconds / 60) + " min " +
                (maxDurationSeconds % 60) + " s\n" +
                "  Bazowa reguła:\n" +
                "    " + baseRule.getRuleDescription().replace("\n", "\n    ");
    }
}
// Koniec, Tydzień 5, Wzorzec Interpreter 4 (cd.)