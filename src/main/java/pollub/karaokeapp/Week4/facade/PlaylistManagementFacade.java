package pollub.karaokeapp.Week4.facade;

import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.Week2.singleton.LoggerSingleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Tydzień 4, Wzorzec Facade 3
 * Fasada dla zarządzania playlistami
 * Upraszcza operacje na playlistach i ich komponentach
 */
public class PlaylistManagementFacade {
    private final LoggerSingleton logger = LoggerSingleton.getInstance();
    private final List<Playlist> playlists = new ArrayList<>();
    private Playlist currentPlaylist;

    private void validateCurrentPlaylist() {
        if (currentPlaylist == null) {
            throw new IllegalStateException("Brak aktualnej playlisty!");
        }
    }

    private void logPlaylistAction(String action, String detail) {
        logger.log("[PLAYLIST] " + action + ": " + detail);
    }

    public void createPlaylist(String playlistName) {
        Playlist newPlaylist = new Playlist(playlistName);
        playlists.add(newPlaylist);
        currentPlaylist = newPlaylist;
        logPlaylistAction("Utworzona playlista", playlistName);
    }

    public void addSongToPlaylist(Song song) {
        validateCurrentPlaylist();
        currentPlaylist.addSong(song);
        logPlaylistAction("Dodana piosenka", song.getTitle());
    }

    public void removeSongFromPlaylist(String songTitle) {
        validateCurrentPlaylist();
        currentPlaylist.getSongs().removeIf(s -> s.getTitle().equals(songTitle));
        logPlaylistAction("Usunięta piosenka", songTitle);
    }

    public void loadPlaylist(String playlistName) {
        Playlist found = findPlaylistByName(playlistName);
        currentPlaylist = found;
        logPlaylistAction("Załadowana playlista", playlistName);
    }

    private Playlist findPlaylistByName(String playlistName) {
        for (Playlist playlist : playlists) {
            if (playlist.getName().equals(playlistName)) {
                return playlist;
            }
        }
        throw new IllegalArgumentException("Playlista nie znaleziona: " + playlistName);
    }

    public void displayCurrentPlaylist() {
        if (currentPlaylist == null) {
            logger.log("[PLAYLIST] Brak aktualnej playlisty!");
            return;
        }
        logger.log("[PLAYLIST] 📋 Zawartość: " + currentPlaylist.getName());
        for (Song song : currentPlaylist.getSongs()) {
            logger.log("  → " + song.getTitle() + " (" + song.getDuration() + "s)");
        }
    }

    public void sortPlaylistByDifficulty() {
        validateCurrentPlaylist();
        currentPlaylist.getSongs().sort((s1, s2) -> Integer.compare(s1.getDifficulty(), s2.getDifficulty()));
        logPlaylistAction("✓ Posortowana po trudności", "");
    }

    public Playlist filterByGenre(String genre) {
        validateCurrentPlaylist();
        Playlist filteredPlaylist = createEmptyFilteredPlaylist(genre);
        addSongsMatchingGenre(filteredPlaylist, genre);
        logFilterResult(filteredPlaylist);
        return filteredPlaylist;
    }

    private Playlist createEmptyFilteredPlaylist(String genre) {
        return new Playlist(currentPlaylist.getName() + " (" + genre + ")");
    }

    private void addSongsMatchingGenre(Playlist target, String genre) {
        for (Song song : currentPlaylist.getSongs()) {
            if (song.getGenre().equalsIgnoreCase(genre)) {
                target.addSong(song);
            }
        }
    }

    private void logFilterResult(Playlist playlist) {
        logger.log("[PLAYLIST] ✓ Przefiltrowana: " + playlist.getSongs().size() + " piosenek");
    }

    public String getPlaylistInfo() {
        if (currentPlaylist == null) {
            return "Brak aktualnej playlisty";
        }
        int totalDuration = calculateTotalDuration();
        return buildPlaylistInfoString(totalDuration);
    }

    private int calculateTotalDuration() {
        return currentPlaylist.getSongs().stream()
                .mapToInt(Song::getDuration)
                .sum();
    }

    private String buildPlaylistInfoString(int totalDuration) {
        return "Playlista: " + currentPlaylist.getName() +
                " | Piosenek: " + currentPlaylist.getSongs().size() +
                " | Czas: " + totalDuration + "s";
    }

    public void deletePlaylist(String playlistName) {
        playlists.removeIf(p -> p.getName().equals(playlistName));
        if (currentPlaylist != null && currentPlaylist.getName().equals(playlistName)) {
            currentPlaylist = null;
        }
        logPlaylistAction("Usunięta playlista", playlistName);
    }

    public Playlist getCurrentPlaylist() { return currentPlaylist; }
    public List<Playlist> getAllPlaylists() { return new ArrayList<>(playlists); }
}
// Koniec, Tydzień 4, Wzorzec Facade 3
