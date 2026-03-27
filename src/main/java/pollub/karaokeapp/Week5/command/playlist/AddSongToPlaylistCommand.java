package pollub.karaokeapp.Week5.command.playlist;

import pollub.karaokeapp.Week5.command.KaraokeCommand;
import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 5, Wzorzec Command 2
 * Komenda dodania piosenki do playlisty.
 * Deleguje faktyczną logikę do Receivera (PlaylistManager).
 */
public class AddSongToPlaylistCommand implements KaraokeCommand {

    private final PlaylistManager receiver;
    private final Song song;

    public AddSongToPlaylistCommand(PlaylistManager receiver, Song song) {
        this.receiver = receiver;
        this.song = song;
    }

    @Override
    public void execute() {
        receiver.addSong(song);
    }

    @Override
    public void undo() {
        System.out.println("[PLAYLIST-CMD] Cofanie dodania: '" + song.getTitle() + "'");
        receiver.removeSong(song);
    }

    @Override
    public String getDescription() {
        return "Dodaj '" + song.getTitle() + "' do playlisty '" + receiver.getPlaylist().getName() + "'";
    }
}
// Koniec, Tydzień 5, Wzorzec Command 2