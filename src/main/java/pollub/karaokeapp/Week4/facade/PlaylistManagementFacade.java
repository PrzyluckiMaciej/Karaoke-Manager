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

    // Tworzenie nowej playlisty
    public void createPlaylist(String playlistName) {
        Playlist newPlaylist = new Playlist(playlistName);
        playlists.add(newPlaylist);
        currentPlaylist = newPlaylist;
        logger.log("[PLAYLIST] Utworzona playlista: " + playlistName);
    }

    // Dodanie piosenki do aktualnej playlisty
    public void addSongToPlaylist(Song song) {
        if (currentPlaylist == null) {
            throw new IllegalStateException("Brak aktualnej playlisty!");
        }
        currentPlaylist.addSong(song);
        logger.log("[PLAYLIST] Dodana piosenka: " + song.getTitle());
    }

    // Usunięcie piosenki z aktualnej playlisty
    public void removeSongFromPlaylist(String songTitle) {
        if (currentPlaylist == null) {
            throw new IllegalStateException("Brak aktualnej playlisty!");
        }
        currentPlaylist.getSongs().removeIf(s -> s.getTitle().equals(songTitle));
        logger.log("[PLAYLIST] Usunięta piosenka: " + songTitle);
    }

    // Załadowanie istniejącej playlisty
    public void loadPlaylist(String playlistName) {
        for (Playlist playlist : playlists) {
            if (playlist.getName().equals(playlistName)) {
                currentPlaylist = playlist;
                logger.log("[PLAYLIST] Załadowana playlista: " + playlistName);
                return;
            }
        }
        throw new IllegalArgumentException("Playlista nie znaleziona: " + playlistName);
    }

    // Wyświetlenie zawartości aktualnej playlisty
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

    // Sortowanie aktualnej playlisty
    public void sortPlaylistByDifficulty() {
        if (currentPlaylist == null) {
            throw new IllegalStateException("Brak aktualnej playlisty!");
        }
        currentPlaylist.getSongs().sort((s1, s2) -> Integer.compare(s1.getDifficulty(), s2.getDifficulty()));
        logger.log("[PLAYLIST] ✓ Posortowana po trudności");
    }

    // Filtrowanie playlisty po gatunku
    public Playlist filterByGenre(String genre) {
        if (currentPlaylist == null) {
            throw new IllegalStateException("Brak aktualnej playlisty!");
        }
        Playlist filteredPlaylist = new Playlist(currentPlaylist.getName() + " (" + genre + ")");
        for (Song song : currentPlaylist.getSongs()) {
            if (song.getGenre().equalsIgnoreCase(genre)) {
                filteredPlaylist.addSong(song);
            }
        }
        logger.log("[PLAYLIST] ✓ Przefiltrowana: " + filteredPlaylist.getSongs().size() + " piosenek");
        return filteredPlaylist;
    }

    // Pobranie informacji o aktualnej playliście
    public String getPlaylistInfo() {
        if (currentPlaylist == null) {
            return "Brak aktualnej playlisty";
        }
        int totalDuration = currentPlaylist.getSongs().stream()
                .mapToInt(Song::getDuration)
                .sum();
        return "Playlista: " + currentPlaylist.getName() +
                " | Piosenek: " + currentPlaylist.getSongs().size() +
                " | Czas: " + totalDuration + "s";
    }

    // Usunięcie playlisty
    public void deletePlaylist(String playlistName) {
        playlists.removeIf(p -> p.getName().equals(playlistName));
        if (currentPlaylist != null && currentPlaylist.getName().equals(playlistName)) {
            currentPlaylist = null;
        }
        logger.log("[PLAYLIST] Usunięta playlista: " + playlistName);
    }

    public Playlist getCurrentPlaylist() {
        return currentPlaylist;
    }

    public List<Playlist> getAllPlaylists() {
        return new ArrayList<>(playlists);
    }
}
// Koniec, Tydzień 4, Wzorzec Facade 3
