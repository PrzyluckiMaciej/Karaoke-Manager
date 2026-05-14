package pollub.karaokeapp.Week5.command.playlist;

import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 5, Wzorzec Command 2 – Receiver
 * Odbiorca (Receiver) zawierający logikę biznesową zarządzania playlistą.
 * Komendy delegują do tej klasy zamiast operować bezpośrednio na Playlist.
 */
public class PlaylistManager {

    private static final String LOG_PREFIX = "[PLAYLIST-RECEIVER]";

    private final Playlist playlist;

    public PlaylistManager(Playlist playlist) {
        if (playlist == null) {
            throw new IllegalArgumentException("Playlista nie może być null");
        }
        this.playlist = playlist;
    }

    public void addSong(Song song) {
        validateSong(song);
        validateSongNotAlreadyInPlaylist(song);
        playlist.addSong(song);
        logSongAdded(song);
    }

    public void removeSong(Song song) {
        validateSong(song);
        playlist.getSongs().remove(song);
        logSongRemoved(song);
    }

    public int indexOf(Song song) {
        validateSong(song);
        return playlist.getSongs().indexOf(song);
    }

    public void insertSongAt(int index, Song song) {
        validateSong(song);
        validateIndex(index);
        playlist.getSongs().add(index, song);
        logSongRestored(index, song);
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    private void validateSong(Song song) {
        if (song == null) {
            throw new IllegalArgumentException("Piosenka nie może być null");
        }
    }

    private void validateSongNotAlreadyInPlaylist(Song song) {
        if (playlist.getSongs().contains(song)) {
            throw new IllegalStateException(
                    "Piosenka '" + song.getTitle() + "' już istnieje w playliście '" +
                            playlist.getName() + "'"
            );
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > playlist.getSongs().size()) {
            throw new IllegalArgumentException(
                    "Indeks " + index + " jest poza zakresem. Dozwolone: 0-" + playlist.getSongs().size()
            );
        }
    }

    private void logSongAdded(Song song) {
        System.out.println(LOG_PREFIX + " Dodano '" + song.getTitle() +
                "' do playlisty '" + playlist.getName() + "'");
    }

    private void logSongRemoved(Song song) {
        System.out.println(LOG_PREFIX + " Usunięto '" + song.getTitle() +
                "' z playlisty '" + playlist.getName() + "'");
    }

    private void logSongRestored(int index, Song song) {
        System.out.println(LOG_PREFIX + " Przywrócono '" + song.getTitle() +
                "' na pozycję " + index + " w playliście '" + playlist.getName() + "'");
    }
}
// Koniec, Tydzień 5, Wzorzec Command 2 – Receiver