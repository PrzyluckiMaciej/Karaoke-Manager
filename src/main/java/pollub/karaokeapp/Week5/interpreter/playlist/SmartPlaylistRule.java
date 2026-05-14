package pollub.karaokeapp.Week5.interpreter.playlist;

import pollub.karaokeapp.Week5.interpreter.filter.SongFilterExpression;
import pollub.karaokeapp.Week5.interpreter.scoring.ScoreExpression;
import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.playlist.Playlist;

import java.util.*;

/**
 * Tydzień 5, Wzorzec Interpreter 4 (cd.)
 * Interpretuje reguły playlisty - łączy filtry i scoring do generowania inteligentnych playlist.
 * Nieterminalny – wyrażenie generujące playlistę na podstawie warunku i reguły punktowej.
 */
public class SmartPlaylistRule implements PlaylistRuleExpression {

    public static final int LONG_SONG_DURATION_SECONDS = 300;
    public static final int LONG_SONG_BONUS = 20;
    public static final int DIFFICULTY_MULTIPLIER = 10;

    private final SongFilterExpression condition;
    private final ScoreExpression scoringRule;
    private final int limit;
    private final String ruleName;

    public SmartPlaylistRule(String ruleName, SongFilterExpression condition,
                             ScoreExpression scoringRule, int limit) {
        validateConstructorParams(ruleName, condition, scoringRule, limit);
        this.ruleName = ruleName;
        this.condition = condition;
        this.scoringRule = scoringRule;
        this.limit = limit;
    }

    private void validateConstructorParams(String ruleName, SongFilterExpression condition,
                                           ScoreExpression scoringRule, int limit) {
        if (ruleName == null || ruleName.trim().isEmpty()) {
            throw new IllegalArgumentException("Nazwa reguły nie może być pusta");
        }
        if (condition == null) {
            throw new IllegalArgumentException("Warunek filtrowania nie może być null");
        }
        if (scoringRule == null) {
            throw new IllegalArgumentException("Reguła punktacji nie może być null");
        }
        if (limit <= 0) {
            throw new IllegalArgumentException("Limit musi być dodatni, podano: " + limit);
        }
    }

    @Override
    public Playlist generatePlaylist(List<Song> allSongs) {
        validateSongList(allSongs);

        List<Song> eligibleSongs = filterSongsByCondition(allSongs);
        Map<Song, Integer> scoredSongs = scoreEligibleSongs(eligibleSongs);
        List<Song> topSongs = selectTopSongsByScore(scoredSongs);

        return createPlaylistFromSongs(topSongs);
    }

    private void validateSongList(List<Song> allSongs) {
        if (allSongs == null) {
            throw new IllegalArgumentException("Lista piosenek nie może być null");
        }
    }

    private List<Song> filterSongsByCondition(List<Song> allSongs) {
        List<Song> eligibleSongs = new ArrayList<>();
        for (Song song : allSongs) {
            if (condition.interpret(song)) {
                eligibleSongs.add(song);
            }
        }
        return eligibleSongs;
    }

    private Map<Song, Integer> scoreEligibleSongs(List<Song> eligibleSongs) {
        Map<Song, Integer> scoredSongs = new HashMap<>();
        for (Song song : eligibleSongs) {
            int score = evaluateScoreForSong(song);
            scoredSongs.put(song, score);
        }
        return scoredSongs;
    }

    private List<Song> selectTopSongsByScore(Map<Song, Integer> scoredSongs) {
        List<Song> sortedSongs = new ArrayList<>(scoredSongs.keySet());
        sortedSongs.sort((s1, s2) -> Integer.compare(
                scoredSongs.get(s2),
                scoredSongs.get(s1)
        ));

        if (sortedSongs.size() > limit) {
            return sortedSongs.subList(0, limit);
        }
        return sortedSongs;
    }

    private Playlist createPlaylistFromSongs(List<Song> songs) {
        Playlist playlist = new Playlist(ruleName + " - " + System.currentTimeMillis());
        for (Song song : songs) {
            playlist.addSong(song);
        }
        return playlist;
    }

    private int evaluateScoreForSong(Song song) {
        int baseScore = scoringRule.interpret();
        int songModifier = calculateSongModifier(song);
        return baseScore + songModifier;
    }

    private int calculateSongModifier(Song song) {
        int modifier = song.getDifficulty() * DIFFICULTY_MULTIPLIER;
        if (isLongSong(song)) {
            modifier += LONG_SONG_BONUS;
        }
        return modifier;
    }

    private boolean isLongSong(Song song) {
        return song.getDuration() > LONG_SONG_DURATION_SECONDS;
    }

    @Override
    public String getRuleDescription() {
        return "Reguła: " + ruleName + "\n" +
                "  Warunek: " + condition.getExpressionDescription() + "\n" +
                "  Punktacja: " + scoringRule.getExpressionDescription() + "\n" +
                "  Limit: " + limit + " piosenek";
    }
}
// Koniec, Tydzień 5, Wzorzec Interpreter 4 (cd.)