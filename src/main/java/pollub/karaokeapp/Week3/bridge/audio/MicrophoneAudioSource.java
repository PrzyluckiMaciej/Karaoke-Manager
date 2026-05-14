package pollub.karaokeapp.Week3.bridge.audio;

import pollub.karaokeapp.Week3.bridge.constants.BridgeConstants;

/**
 * Tydzień 3, Wzorzec Bridge 2
 * Konkretna implementacja - źródło z mikrofonu
 */
public class MicrophoneAudioSource implements AudioSource {

    private final String micName;

    public MicrophoneAudioSource(String micName) {
        this.micName = micName;
    }

    @Override
    public byte[] getAudioData() {
        return new byte[BridgeConstants.MIC_BUFFER_SIZE];
    }

    @Override
    public String getSourceName() {
        return "Mikrofon: " + micName;
    }

    @Override
    public int getDuration() {
        return -1; // live streaming - nieznany czas
    }
}
// Koniec, Tydzień 3, Wzorzec Bridge 2