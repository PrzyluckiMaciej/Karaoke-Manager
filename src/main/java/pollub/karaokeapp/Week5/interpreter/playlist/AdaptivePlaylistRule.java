package pollub.karaokeapp.Week5.interpreter.playlist;

import pollub.karaokeapp.Week5.interpreter.filter.DifficultyRangeExpression;
import pollub.karaokeapp.Week5.interpreter.filter.SongFilterExpression;
import pollub.karaokeapp.Week5.interpreter.scoring.ScoreExpression;
import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;
import pollub.karaokeapp.model.playlist.Playlist;

import java.util.List;

/**
 * Tydzień 5, Wzorzec Interpreter 4 (cd.)
 * Adaptacyjna reguła playlisty - dostosowuje się do poziomu użytkownika.
 * Rozszerzenie SmartPlaylistRule o świadomość użytkownika.
 */
public class AdaptivePlaylistRule implements PlaylistRuleExpression {

    private final User user;
    private final ScoreExpression scoringRule;
    private final int limit;
    private final String ruleName;

    public AdaptivePlaylistRule(String ruleName, User user,
                                ScoreExpression scoringRule, int limit) {
        this.ruleName = ruleName;
        this.user = user;
        this.scoringRule = scoringRule;
        this.limit = limit;
    }

    private SongFilterExpression getAdaptiveCondition() {
        int userLevel = user.getLevel();

        if (userLevel < 3) {
            return new DifficultyRangeExpression(1, 4);
        } else if (userLevel < 7) {
            return new DifficultyRangeExpression(4, 7);
        } else {
            return new DifficultyRangeExpression(7, 10);
        }
    }

    @Override
    public Playlist generatePlaylist(List<Song> allSongs) {
        SmartPlaylistRule adaptiveRule = new SmartPlaylistRule(
                ruleName + "_" + user.getNickname(),
                getAdaptiveCondition(),
                scoringRule,
                limit
        );

        return adaptiveRule.generatePlaylist(allSongs);
    }

    @Override
    public String getRuleDescription() {
        SongFilterExpression condition = getAdaptiveCondition();
        int userLevel = user.getLevel();
        String levelText;

        if (userLevel < 3) {
            levelText = "początkujący (1-4)";
        } else if (userLevel < 7) {
            levelText = "średniozaawansowany (4-7)";
        } else {
            levelText = "zaawansowany (7-10)";
        }

        return "Adaptacyjna Reguła: " + ruleName + "\n" +
                "  Użytkownik: " + user.getNickname() + " (poziom " + userLevel + " - " + levelText + ")\n" +
                "  Warunek: " + condition.getExpressionDescription() + "\n" +
                "  Punktacja: " + scoringRule.getExpressionDescription() + "\n" +
                "  Limit: " + limit + " piosenek";
    }
}
// Koniec, Tydzień 5, Wzorzec Interpreter 4 (cd.)