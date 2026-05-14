package pollub.karaokeapp.Week3.adapter.lyrics;

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

    private static final String LINE_SEPARATOR = "\n";

    public LyricsFileAdapter(String filePath) throws IOException {
        super(filePath);
    }

    @Override
    public String getLyrics(Song song) {
        String searchKey = song.getTitle() + " - " + song.getArtist();
        List<String> lines = getLines();

        int startIndex = findSongEntryIndex(lines, searchKey);
        return collectLyricsFromLines(lines, startIndex);
    }

    private int findSongEntryIndex(List<String> lines, String searchKey) {
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).trim().equalsIgnoreCase(searchKey)) {
                return i;
            }
        }
        throw new LyricsNotFoundException(
                "Brak tekstu dla: " + searchKey + " w pliku " + getFilePath().getFileName()
        );
    }

    private String collectLyricsFromLines(List<String> lines, int startIndex) {
        StringBuilder lyrics = new StringBuilder();

        for (int i = startIndex + 1; i < lines.size(); i++) {
            String line = lines.get(i);

            if (isEmptyLine(line)) {
                break;
            }

            lyrics.append(line).append(LINE_SEPARATOR);
        }

        return lyrics.toString();
    }

    private boolean isEmptyLine(String line) {
        return line == null || line.trim().isEmpty();
    }
}

class LyricsNotFoundException extends RuntimeException {
    public LyricsNotFoundException(String message) {
        super(message);
    }
}
// Koniec, Tydzień 3, Wzorzec Adapter 3