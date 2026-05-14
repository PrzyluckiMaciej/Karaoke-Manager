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

    public static final int BEGINNER_LEVEL_THRESHOLD = 3;
    public static final int INTERMEDIATE_LEVEL_THRESHOLD = 7;
    public static final int BEGINNER_MAX_DIFFICULTY = 4;
    public static final int INTERMEDIATE_MAX_DIFFICULTY = 7;
    public static final int ADVANCED_MAX_DIFFICULTY = 10;
    public static final int MIN_DIFFICULTY = 1;

    private static final String BEGINNER_LABEL = "początkujący (1-4)";
    private static final String INTERMEDIATE_LABEL = "średniozaawansowany (4-7)";
    private static final String ADVANCED_LABEL = "zaawansowany (7-10)";

    private final User user;
    private final ScoreExpression scoringRule;
    private final int limit;
    private final String ruleName;

    public AdaptivePlaylistRule(String ruleName, User user,
                                ScoreExpression scoringRule, int limit) {
        validateConstructorParams(ruleName, user, scoringRule, limit);
        this.ruleName = ruleName;
        this.user = user;
        this.scoringRule = scoringRule;
        this.limit = limit;
    }

    private void validateConstructorParams(String ruleName, User user,
                                           ScoreExpression scoringRule, int limit) {
        if (ruleName == null || ruleName.trim().isEmpty()) {
            throw new IllegalArgumentException("Nazwa reguły nie może być pusta");
        }
        if (user == null) {
            throw new IllegalArgumentException("Użytkownik nie może być null");
        }
        if (scoringRule == null) {
            throw new IllegalArgumentException("Reguła punktacji nie może być null");
        }
        if (limit <= 0) {
            throw new IllegalArgumentException("Limit musi być dodatni, podano: " + limit);
        }
    }

    private SongFilterExpression getAdaptiveCondition() {
        UserLevelCategory levelCategory = getUserLevelCategory();
        DifficultyRange range = levelCategory.getDifficultyRange();
        return new DifficultyRangeExpression(range.getMin(), range.getMax());
    }

    private UserLevelCategory getUserLevelCategory() {
        int userLevel = user.getLevel();
        if (userLevel < BEGINNER_LEVEL_THRESHOLD) {
            return UserLevelCategory.BEGINNER;
        } else if (userLevel < INTERMEDIATE_LEVEL_THRESHOLD) {
            return UserLevelCategory.INTERMEDIATE;
        } else {
            return UserLevelCategory.ADVANCED;
        }
    }

    private String getLevelDescription() {
        return getUserLevelCategory().getDescription();
    }

    private String getLevelRangeDescription() {
        DifficultyRange range = getUserLevelCategory().getDifficultyRange();
        return range.getMin() + "-" + range.getMax();
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
        StringBuilder sb = new StringBuilder();
        sb.append("Adaptacyjna Reguła: ").append(ruleName).append("\n");
        sb.append("  Użytkownik: ").append(user.getNickname())
                .append(" (poziom ").append(user.getLevel())
                .append(" - ").append(getLevelDescription()).append(")\n");
        sb.append("  Warunek: trudność∈[").append(getLevelRangeDescription()).append("]\n");
        sb.append("  Punktacja: ").append(scoringRule.getExpressionDescription()).append("\n");
        sb.append("  Limit: ").append(limit).append(" piosenek");
        return sb.toString();
    }

    private enum UserLevelCategory {
        BEGINNER(BEGINNER_LABEL, MIN_DIFFICULTY, BEGINNER_MAX_DIFFICULTY),
        INTERMEDIATE(INTERMEDIATE_LABEL, BEGINNER_MAX_DIFFICULTY, INTERMEDIATE_MAX_DIFFICULTY),
        ADVANCED(ADVANCED_LABEL, INTERMEDIATE_MAX_DIFFICULTY, ADVANCED_MAX_DIFFICULTY);

        private final String description;
        private final DifficultyRange range;

        UserLevelCategory(String description, int minDiff, int maxDiff) {
            this.description = description;
            this.range = new DifficultyRange(minDiff, maxDiff);
        }

        public String getDescription() {
            return description;
        }

        public DifficultyRange getDifficultyRange() {
            return range;
        }
    }

    private static class DifficultyRange {
        private final int min;
        private final int max;

        DifficultyRange(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public int getMin() { return min; }
        public int getMax() { return max; }
    }
}
// Koniec, Tydzień 5, Wzorzec Interpreter 4 (cd.)