package pollub.karaokeapp.Week3.bridge.audio;

/**
 * Tydzień 3, Wzorzec Bridge 2
 * Abstrakcja - odtwarzacz audio
 */
public abstract class AudioPlayer {

    protected AudioSource audioSource;

    public AudioPlayer(AudioSource audioSource) {
        this.audioSource = audioSource;
    }

    public abstract void play();
    public abstract void stop();
    public abstract void setVolume(int volume);
    public abstract String getPlayerInfo();
}
// Koniec, Tydzień 3, Wzorzec Bridge 2