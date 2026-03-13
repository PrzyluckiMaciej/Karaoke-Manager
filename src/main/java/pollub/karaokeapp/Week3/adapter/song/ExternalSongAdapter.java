package pollub.karaokeapp.Week3.adapter.song;

import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.service.song.ExternalSongAPI;

/**
 * Tydzień 3, Wzorzec Adapter 1 (Adapter obiektu)
 * Adapter dostosowujący zewnętrzne API do naszego modelu Song
 */
public class ExternalSongAdapter extends Song {

    private final ExternalSongAPI externalAPI;

    public ExternalSongAdapter(ExternalSongAPI externalAPI) {
        super(
                extractTitle(externalAPI),
                extractArtist(externalAPI),
                extractDuration(externalAPI),
                extractGenre(externalAPI),
                extractDifficulty(externalAPI)
        );
        this.externalAPI = externalAPI;
    }

    private static String extractTitle(ExternalSongAPI api) {
        return api.parseSongInfo()[0];
    }

    private static String extractArtist(ExternalSongAPI api) {
        return api.parseSongInfo()[1];
    }

    private static int extractDuration(ExternalSongAPI api) {
        return Integer.parseInt(api.parseSongInfo()[2]);
    }

    private static String extractGenre(ExternalSongAPI api) {
        return api.parseSongInfo()[3];
    }

    private static int extractDifficulty(ExternalSongAPI api) {
        return Integer.parseInt(api.parseSongInfo()[4]);
    }

    public String getRawData() {
        return externalAPI.getRawSongData();
    }
}
// Koniec, Tydzień 3, Wzorzec Adapter 1