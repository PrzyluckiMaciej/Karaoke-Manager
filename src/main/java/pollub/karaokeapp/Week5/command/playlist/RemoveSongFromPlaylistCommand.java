package pollub.karaokeapp.Week5.command.playlist;

import pollub.karaokeapp.Week5.command.KaraokeCommand;
import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 5, Wzorzec Command 3 (cd.)
 * Komenda usunięcia piosenki z playlisty (z obsługą undo).
 */
public class RemoveSongFromPlaylistCommand implements KaraokeCommand {

    private final Playlist playlist;
    private final Song song;
    private int removedIndex = -1;

    public RemoveSongFromPlaylistCommand(Playlist playlist, Song song) {
        this.playlist = playlist;
        this.song = song;
    }

    @Override
    public void execute() {
        removedIndex = playlist.getSongs().indexOf(song);
        if (removedIndex >= 0) {
            playlist.getSongs().remove(removedIndex);
            System.out.println("[PLAYLIST-CMD] Usunięto '" + song.getTitle() + "' z playlisty '" + playlist.getName() + "'");
        }
    }

    @Override
    public void undo() {
        if (removedIndex >= 0) {
            playlist.getSongs().add(removedIndex, song);
            System.out.println("[PLAYLIST-CMD] Przywrócono '" + song.getTitle() + "' do playlisty '" + playlist.getName() + "'");
        }
    }

    @Override
    public String getDescription() {
        return "Usuń '" + song.getTitle() + "' z playlisty '" + playlist.getName() + "'";
    }
}
// Koniec, Tydzień 5, Wzorzec Command 3 (cd.)