package pollub.karaokeapp.Week3.bridge.audio;

/**
 * Tydzień 3, Wzorzec Bridge 2
 * Interfejs - źródło dźwięku
 */
public interface AudioSource {
    byte[] getAudioData();
    String getSourceName();
    int getDuration();
}
// Koniec, Tydzień 3, Wzorzec Bridge 2