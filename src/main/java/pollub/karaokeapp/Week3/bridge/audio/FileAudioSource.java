// FileAudioSource.java
package pollub.karaokeapp.Week3.bridge.audio;

import pollub.karaokeapp.Week3.bridge.constants.BridgeConstants;

/**
 * Tydzień 3, Wzorzec Bridge 2
 * Konkretna implementacja - źródło z pliku
 */
public class FileAudioSource implements AudioSource {

    private final String filePath;
    private final int duration;

    public FileAudioSource(String filePath, int duration) {
        this.filePath = filePath;
        this.duration = duration;
    }

    @Override
    public byte[] getAudioData() {
        int bufferSize = duration * BridgeConstants.DEFAULT_SAMPLE_RATE * BridgeConstants.BYTES_PER_SAMPLE;
        return new byte[bufferSize];
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