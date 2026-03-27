package pollub.karaokeapp.Week5.command.playlist;

import pollub.karaokeapp.Week5.command.KaraokeCommand;
import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 5, Wzorzec Command 2 (cd.)
 * Komenda usunięcia piosenki z playlisty.
 * Deleguje faktyczną logikę do Receivera (PlaylistManager).
 */
public class RemoveSongFromPlaylistCommand implements KaraokeCommand {

    private final PlaylistManager receiver;
    private final Song song;
    private int removedIndex = -1;

    public RemoveSongFromPlaylistCommand(PlaylistManager receiver, Song song) {
        this.receiver = receiver;
        this.song = song;
    }

    @Override
    public void execute() {
        removedIndex = receiver.indexOf(song);
        if (removedIndex >= 0) {
            receiver.removeSong(song);
        }
    }

    @Override
    public void undo() {
        if (removedIndex >= 0) {
            System.out.println("[PLAYLIST-CMD] Cofanie usunięcia: '" + song.getTitle() + "'");
            receiver.insertSongAt(removedIndex, song);
        }
    }

    @Override
    public String getDescription() {
        return "Usuń '" + song.getTitle() + "' z playlisty '" + receiver.getPlaylist().getName() + "'";
    }
}
// Koniec, Tydzień 5, Wzorzec Command 2 (cd.)