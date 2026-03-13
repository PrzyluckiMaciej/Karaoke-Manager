package pollub.karaokeapp.Week3.bridge.audio;

/**
 * Tydzień 3, Wzorzec Bridge 2
 * Konkretna implementacja - źródło z mikrofonu
 */
public class MicrophoneAudioSource implements AudioSource {

    private String micName;

    public MicrophoneAudioSource(String micName) {
        this.micName = micName;
    }

    @Override
    public byte[] getAudioData() {
        // Symulacja odczytu z mikrofonu
        return new byte[1024];
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