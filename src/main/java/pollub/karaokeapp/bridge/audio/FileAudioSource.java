// FileAudioSource.java
package pollub.karaokeapp.bridge.audio;

/**
 * Tydzień 3, Wzorzec Bridge 2
 * Konkretna implementacja - źródło z pliku
 */
public class FileAudioSource implements AudioSource {

    private String filePath;
    private int duration;

    public FileAudioSource(String filePath, int duration) {
        this.filePath = filePath;
        this.duration = duration;
    }

    @Override
    public byte[] getAudioData() {
        // Symulacja odczytu z pliku
        return new byte[duration * 44100 * 2];
    }

    @Override
    public String getSourceName() {
        return "Plik: " + filePath;
    }

    @Override
    public int getDuration() {
        return duration;
    }
}
// Koniec, Tydzień 3, Wzorzec Bridge 2