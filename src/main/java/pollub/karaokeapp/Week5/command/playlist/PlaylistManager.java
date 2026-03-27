package pollub.karaokeapp.Week5.command.playlist;

import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 5, Wzorzec Command 2 – Receiver
 * Odbiorca (Receiver) zawierający logikę biznesową zarządzania playlistą.
 * Komendy delegują do tej klasy zamiast operować bezpośrednio na Playlist.
 */
public class PlaylistManager {

    private final Playlist playlist;

    public PlaylistManager(Playlist playlist) {
        this.playlist = playlist;
    }

    public void addSong(Song song) {
        playlist.addSong(song);
        System.out.println("[PLAYLIST-RECEIVER] Dodano '" + song.getTitle()
                + "' do playlisty '" + playlist.getName() + "'");
    }

    public void removeSong(Song song) {
        playlist.getSongs().remove(song);
        System.out.println("[PLAYLIST-RECEIVER] Usunięto '" + song.getTitle()
                + "' z playlisty '" + playlist.getName() + "'");
    }

    public int indexOf(Song song) {
        return playlist.getSongs().indexOf(song);
    }

    public void insertSongAt(int index, Song song) {
        playlist.getSongs().add(index, song);
        System.out.println("[PLAYLIST-RECEIVER] Przywrócono '" + song.getTitle()
                + "' na pozycję " + index + " w playliście '" + playlist.getName() + "'");
    }

    public Playlist getPlaylist() {
        return playlist;
    }
}
// Koniec, Tydzień 5, Wzorzec Command 2 – Receiver