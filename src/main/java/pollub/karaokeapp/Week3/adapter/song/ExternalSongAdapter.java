package pollub.karaokeapp.Week3.adapter.song;

import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.service.song.ExternalSongAPI;

/**
 * Tydzień 3, Wzorzec Adapter 1 (Adapter obiektu)
 * Adapter dostosowujący zewnętrzne API do naszego modelu Song
 */
public class ExternalSongAdapter {

    private final ExternalSongAPI externalAPI;
    private final Song adaptedSong;

    public ExternalSongAdapter(ExternalSongAPI externalAPI) {
        this.externalAPI = externalAPI;
        this.adaptedSong = createSongFromAPI(externalAPI);
    }

    private Song createSongFromAPI(ExternalSongAPI api) {
        String[] songInfo = api.parseSongInfo();

        String title = extractTitle(songInfo);
        String artist = extractArtist(songInfo);
        int duration = extractDuration(songInfo);
        String genre = extractGenre(songInfo);
        int difficulty = extractDifficulty(songInfo);

        return new Song(title, artist, duration, genre, difficulty);
    }

    private String extractTitle(String[] songInfo) {
        return songInfo[0];
    }

    private String extractArtist(String[] songInfo) {
        return songInfo[1];
    }

    private int extractDuration(String[] songInfo) {
        try {
            return Integer.parseInt(songInfo[2]);
        } catch (NumberFormatException e) {
            throw new InvalidSongDataException("Invalid duration format: " + songInfo[2], e);
        }
    }

    private String extractGenre(String[] songInfo) {
        return songInfo[3];
    }

    private int extractDifficulty(String[] songInfo) {
        try {
            return Integer.parseInt(songInfo[4]);
        } catch (NumberFormatException e) {
            throw new InvalidSongDataException("Invalid difficulty format: " + songInfo[4], e);
        }
    }

    public Song getSong() {
        return adaptedSong;
    }

    public String getRawData() {
        return externalAPI.getRawSongData();
    }
}

class InvalidSongDataException extends RuntimeException {
    public InvalidSongDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
// Koniec, Tydzień 3, Wzorzec Adapter 1