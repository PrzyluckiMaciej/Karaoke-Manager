package pollub.karaokeapp.external;

/**
 * Symulacja zewnętrznego API dostarczającego piosenki w innym formacie
 */
public class ExternalSongAPI {
    private String songData;

    public ExternalSongAPI(String songData) {
        this.songData = songData;
    }

    public String getRawSongData() {
        return songData;
    }

    public String[] parseSongInfo() {
        // Format: "Tytuł;Artysta;Czas;Gatunek;Trudność"
        return songData.split(";");
    }
}
