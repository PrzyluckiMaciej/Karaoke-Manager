package pollub.karaokeapp.Week5.command.playlist;

import pollub.karaokeapp.Week5.command.KaraokeCommand;
import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 5, Wzorzec Command 2 (cd.)
 * Komenda usunięcia piosenki z playlisty.
 * Deleguje faktyczną logikę do Receivera (PlaylistManager).
 */
public class RemoveSongFromPlaylistCommand implements KaraokeCommand {

    private static final String LOG_PREFIX = "[PLAYLIST-CMD]";

    private final PlaylistManager receiver;
    private final Song song;
    private int removedIndex = -1;

    public RemoveSongFromPlaylistCommand(PlaylistManager receiver, Song song) {
        if (receiver == null || song == null) {
            throw new IllegalArgumentException("Receiver i piosenka nie mogą być null");
        }
        this.receiver = receiver;
        this.song = song;
    }

    @Override
    public void execute() {
        validateSongExistsInPlaylist();
        removedIndex = receiver.indexOf(song);
        receiver.removeSong(song);
    }

    @Override
    public void undo() {
        if (removedIndex >= 0) {
            logUndo();
            receiver.insertSongAt(removedIndex, song);
        }
    }

    @Override
    public String getDescription() {
        return "Usuń '" + song.getTitle() + "' z playlisty '" + receiver.getPlaylist().getName() + "'";
    }

    private void validateSongExistsInPlaylist() {
        int index = receiver.indexOf(song);
        if (index < 0) {
            throw new IllegalArgumentException(
                    "Piosenka '" + song.getTitle() + "' nie istnieje w playliście '" +
                            receiver.getPlaylist().getName() + "'"
            );
        }
    }

    private void logUndo() {
        System.out.println(LOG_PREFIX + " Cofanie usunięcia: '" + song.getTitle() + "'");
    }
}
// Koniec, Tydzień 5, Wzorzec Command 2 (cd.)