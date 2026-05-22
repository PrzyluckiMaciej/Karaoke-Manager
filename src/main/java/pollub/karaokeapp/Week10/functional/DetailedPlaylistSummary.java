package pollub.karaokeapp.Week10.functional;

import pollub.karaokeapp.model.playlist.Playlist;

/**
 * Tydzień 10, Implementacja klasowa interfejsu funkcyjnego 4
 * DetailedPlaylistSummary generuje szczegółowe podsumowanie playlisty,
 * zawierające nazwę, liczbę piosenek i łączny czas trwania w minutach.
 */
public class DetailedPlaylistSummary implements PlaylistSummary {

    @Override
    public String summarize(Playlist playlist) {
        int totalDuration = playlist.getSongs().stream()
                .mapToInt(song -> song.getDuration())
                .sum();
        int minutes = totalDuration / 60;
        int seconds = totalDuration % 60;
        return String.format("[Playlista] \"%s\" | Piosenek: %d | Łączny czas: %d min %d sek",
                playlist.getName(),
                playlist.getSongs().size(),
                minutes,
                seconds);
    }
}
//Koniec, Tydzień 10, Implementacja klasowa interfejsu funkcyjnego 4
