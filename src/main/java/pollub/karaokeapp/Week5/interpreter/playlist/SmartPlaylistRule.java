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

    private final SongFilterExpression condition;
    private final ScoreExpression scoringRule;
    private final int limit;
    private final String ruleName;

    public SmartPlaylistRule(String ruleName, SongFilterExpression condition,
                             ScoreExpression scoringRule, int limit) {
        this.ruleName = ruleName;
        this.condition = condition;
        this.scoringRule = scoringRule;
        this.limit = limit;
    }

    @Override
    public Playlist generatePlaylist(List<Song> allSongs) {
        // Filtruj piosenki spełniające warunek
        List<Song> eligibleSongs = new ArrayList<>();
        for (Song song : allSongs) {
            if (condition.interpret(song)) {
                eligibleSongs.add(song);
            }
        }

        // Oceń każdą piosenkę według reguły punktowej
        Map<Song, Integer> scoredSongs = new HashMap<>();
        for (Song song : eligibleSongs) {
            int score = evaluateScoreForSong(song);
            scoredSongs.put(song, score);
        }

        // Sortuj według wyniku (malejąco) i ogranicz
        List<Song> sortedSongs = new ArrayList<>(scoredSongs.keySet());
        sortedSongs.sort((s1, s2) -> Integer.compare(
                scoredSongs.get(s2),
                scoredSongs.get(s1)
        ));

        if (sortedSongs.size() > limit) {
            sortedSongs = sortedSongs.subList(0, limit);
        }

        Playlist playlist = new Playlist(ruleName + " - " + System.currentTimeMillis());
        for (Song song : sortedSongs) {
            playlist.addSong(song);
        }

        return playlist;
    }

    private int evaluateScoreForSong(Song song) {
        // W rzeczywistej implementacji ScoreExpression powinien mieć dostęp do Song
        // Dla uproszczenia, tworzymy kontekst punktowy na podstawie piosenki
        int baseScore = scoringRule.interpret();

        // Dodatkowe modyfikatory oparte na właściwościach piosenki
        int songModifier = (int) (song.getDifficulty() * 10);

        // Bonus za długie piosenki (> 5 minut)
        if (song.getDuration() > 300) {
            songModifier += 20;
        }

        return baseScore + songModifier;
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