package pollub.karaokeapp.service.lyrics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RawLyricsFileReader {

    private final Path filePath;
    private final List<String> lines;

    public RawLyricsFileReader(String filePath) throws IOException {
        this.filePath = Paths.get(filePath);
        this.lines = Files.readAllLines(this.filePath);
    }

    public List<String> getLines() {
        return lines;
    }

    public Path getFilePath() {
        return filePath;
    }
}