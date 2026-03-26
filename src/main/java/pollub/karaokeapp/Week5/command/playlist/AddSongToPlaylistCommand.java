package pollub.karaokeapp.Week5.command.playlist;

import pollub.karaokeapp.Week5.command.KaraokeCommand;
import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 5, Wzorzec Command 3
 * Komenda dodania piosenki do playlisty (z obsługą undo).
 */
public class AddSongToPlaylistCommand implements KaraokeCommand {

    private final Playlist playlist;
    private final Song song;

    public AddSongToPlaylistCommand(Playlist playlist, Song song) {
        this.playlist = playlist;
        this.song = song;
    }

    @Override
    public void execute() {
        playlist.addSong(song);
        System.out.println("[PLAYLIST-CMD] Dodano '" + song.getTitle() + "' do playlisty '" + playlist.getName() + "'");
    }

    @Override
    public void undo() {
        playlist.getSongs().remove(song);
        System.out.println("[PLAYLIST-CMD] Cofnięto dodanie '" + song.getTitle() + "' z playlisty '" + playlist.getName() + "'");
    }

    @Override
    public String getDescription() {
        return "Dodaj '" + song.getTitle() + "' do playlisty '" + playlist.getName() + "'";
    }
}
// Koniec, Tydzień 5, Wzorzec Command 3