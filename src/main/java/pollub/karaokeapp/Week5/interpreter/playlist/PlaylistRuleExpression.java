package pollub.karaokeapp.Week5.interpreter.playlist;

import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;

import java.util.List;

/**
 * Tydzień 5, Wzorzec Interpreter 4
 * Interfejs dla wyrażeń generujących playlisty.
 * Gramatyka: PLAYLIST_RULE ::= "IF" condition "THEN" scoring "LIMIT" number
 */
public interface PlaylistRuleExpression {
    Playlist generatePlaylist(List<Song> allSongs);
    String getRuleDescription();
}
// Koniec, Tydzień 5, Wzorzec Interpreter 4