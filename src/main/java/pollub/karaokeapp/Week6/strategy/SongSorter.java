package pollub.karaokeapp.Week6.strategy;

import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Klasa pomocnicza eliminująca powtarzanie kodu sortowania
 */
public class SongSorter {

    public static List<Song> sort(Playlist playlist, Comparator<Song> comparator) {
        if (playlist == null || playlist.getSongs() == null) {
            throw new IllegalArgumentException("Playlista nie może być null");
        }
        return playlist.getSongs().stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public static Comparator<Song> byDifficulty(boolean ascending) {
        return ascending ?
                Comparator.comparingInt(Song::getDifficulty) :
                Comparator.comparingInt(Song::getDifficulty).reversed();
    }

    public static Comparator<Song> byDuration(boolean ascending) {
        return ascending ?
                Comparator.comparingInt(Song::getDuration) :
                Comparator.comparingInt(Song::getDuration).reversed();
    }

    public static Comparator<Song> byTitle(boolean ascending) {
        return ascending ?
                Comparator.comparing(Song::getTitle, String.CASE_INSENSITIVE_ORDER) :
                Comparator.comparing(Song::getTitle, String.CASE_INSENSITIVE_ORDER).reversed();
    }
}