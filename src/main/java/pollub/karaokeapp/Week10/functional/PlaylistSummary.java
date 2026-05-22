package pollub.karaokeapp.Week10.functional;

import pollub.karaokeapp.model.playlist.Playlist;

/**
 * Tydzień 10, Interfejs funkcyjny 4
 * PlaylistSummary generuje tekstowe podsumowanie playlisty.
 * Umożliwia definiowanie różnych formatów podsumowania
 * za pomocą wyrażeń lambda.
 */
@FunctionalInterface
public interface PlaylistSummary {
    String summarize(Playlist playlist);
}
//Koniec, Tydzień 10, Interfejs funkcyjny 4
