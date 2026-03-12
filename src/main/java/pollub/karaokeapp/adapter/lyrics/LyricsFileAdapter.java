// LyricsFileAdapter.java (ulepszona wersja)
package pollub.karaokeapp.adapter.lyrics;

import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.service.lyrics.LyricsProvider;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Tydzień 3, Wzorzec Adapter 3
 * Adapter do odczytu tekstów piosenek z pliku tekstowego
 * Format pliku:
 * Tytuł - Artysta
 * linijka 1 tekstu
 * linijka 2 tekstu
 * ...
 * (pusta linia)
 * kolejna piosenka...
 */
public class LyricsFileAdapter implements LyricsProvider {

    private Path filePath;
    private String fileContent;
    private List<String> lines;

    public LyricsFileAdapter(String filePath) throws IOException {
        this.filePath = Paths.get(filePath);
        this.fileContent = Files.readString(this.filePath);
        this.lines = Files.readAllLines(this.filePath);
    }

    @Override
    public String getLyrics(Song song) {
        String searchKey = song.getTitle() + " - " + song.getArtist();
        return extractLyricsForSong(searchKey);
    }

    private String extractLyricsForSong(String searchKey) {
        StringBuilder lyrics = new StringBuilder();
        boolean found = false;
        boolean collecting = false;

        for (String line : lines) {
            // Szukamy nagłówka piosenki
            if (line.trim().equalsIgnoreCase(searchKey)) {
                found = true;
                collecting = true;
                continue;
            }

            // Jeśli znaleźliśmy piosenkę i zaczęliśmy kolekcjonować
            if (collecting) {
                // Pusta linia oznacza koniec tekstu bieżącej piosenki
                if (line.trim().isEmpty()) {
                    break;
                }
                lyrics.append(line).append("\n");
            }
        }

        if (!found) {
            return "Brak tekstu dla: " + searchKey + " w pliku " + filePath.getFileName();
        }

        return lyrics.toString();
    }
}