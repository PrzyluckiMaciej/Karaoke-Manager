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

    public static final int SECONDS_PER_MINUTE = 60;

    private final SmartPlaylistRule baseRule;
    private final int maxDurationSeconds;
    private final String ruleName;

    public TimeBasedPlaylistRule(String ruleName, SongFilterExpression condition,
                                 ScoreExpression scoringRule, int maxDurationSeconds) {
        validateParams(ruleName, condition, scoringRule, maxDurationSeconds);
        this.ruleName = ruleName;
        this.baseRule = new SmartPlaylistRule(ruleName, condition, scoringRule, Integer.MAX_VALUE);
        this.maxDurationSeconds = maxDurationSeconds;
    }

    private void validateParams(String ruleName, SongFilterExpression condition,
                                ScoreExpression scoringRule, int maxDurationSeconds) {
        if (ruleName == null || ruleName.trim().isEmpty()) {
            throw new IllegalArgumentException("Nazwa reguły nie może być pusta");
        }
        if (condition == null) {
            throw new IllegalArgumentException("Warunek filtrowania nie może być null");
        }
        if (scoringRule == null) {
            throw new IllegalArgumentException("Reguła punktacji nie może być null");
        }
        if (maxDurationSeconds <= 0) {
            throw new IllegalArgumentException(
                    "Maksymalny czas musi być dodatni, podano: " + maxDurationSeconds + " sekund"
            );
        }
    }

    @Override
    public Playlist generatePlaylist(List<Song> allSongs) {
        Playlist fullPlaylist = baseRule.generatePlaylist(allSongs);
        return limitPlaylistByDuration(fullPlaylist);
    }

    private Playlist limitPlaylistByDuration(Playlist fullPlaylist) {
        Playlist timeLimitedPlaylist = createTimeLimitedPlaylist();
        int currentDuration = 0;

        for (Song song : fullPlaylist.getSongs()) {
            if (canAddSongWithinTimeLimit(currentDuration, song)) {
                timeLimitedPlaylist.addSong(song);
                currentDuration += song.getDuration();
            } else {
                break;
            }
        }
        return timeLimitedPlaylist;
    }

    private Playlist createTimeLimitedPlaylist() {
        return new Playlist(ruleName + " (czas: " + maxDurationSeconds + "s)");
    }

    private boolean canAddSongWithinTimeLimit(int currentDuration, Song song) {
        return currentDuration + song.getDuration() <= maxDurationSeconds;
    }

    @Override
    public String getRuleDescription() {
        int minutes = maxDurationSeconds / SECONDS_PER_MINUTE;
        int seconds = maxDurationSeconds % SECONDS_PER_MINUTE;

        return "Reguła z limitem czasowym: " + ruleName + "\n" +
                "  Maksymalny czas: " + minutes + " min " + seconds + " s\n" +
                "  Bazowa reguła:\n" +
                "    " + baseRule.getRuleDescription().replace("\n", "\n    ");
    }
}
// Koniec, Tydzień 5, Wzorzec Interpreter 4 (cd.)