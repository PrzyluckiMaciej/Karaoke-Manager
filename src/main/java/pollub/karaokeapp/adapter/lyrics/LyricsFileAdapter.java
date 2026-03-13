// LyricsFileAdapter.java (ulepszona wersja)
package pollub.karaokeapp.adapter.lyrics;

import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.service.lyrics.LyricsProvider;
import pollub.karaokeapp.service.lyrics.RawLyricsFileReader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Tydzień 3, Wzorzec Adapter 3 (Adapter klasy)
 * Adapter do odczytu tekstów piosenek z pliku tekstowego
 */
public class LyricsFileAdapter extends RawLyricsFileReader implements LyricsProvider {

    public LyricsFileAdapter(String filePath) throws IOException {
        super(filePath);
    }

    @Override
    public String getLyrics(Song song) {
        String searchKey = song.getTitle() + " - " + song.getArtist();
        return extractLyricsFromLines(getLines(), searchKey, getFilePath());
    }

    private String extractLyricsFromLines(List<String> lines, String searchKey, Path filePath) {
        StringBuilder lyrics = new StringBuilder();
        boolean found = false;
        boolean collecting = false;

        for (String line : lines) {
            if (line.trim().equalsIgnoreCase(searchKey)) {
                found = true;
                collecting = true;
                continue;
            }
            if (collecting) {
                if (line.trim().isEmpty()) break;
                lyrics.append(line).append("\n");
            }
        }

        if (!found) {
            return "Brak tekstu dla: " + searchKey + " w pliku " + filePath.getFileName();
        }
        return lyrics.toString();
    }
}
// Koniec, Tydzień 3, Wzorzec Adapter 3