package pollub.karaokeapp.Week3.bridge.audio;

/**
 * Tydzień 3, Wzorzec Bridge 2
 * Konkretna implementacja - odtwarzacz MP3
 */
public class MP3Player extends AudioPlayer {

    private int volume;
    private boolean isPlaying;

    public MP3Player(AudioSource audioSource) {
        super(audioSource);
        this.volume = 50;
        this.isPlaying = false;
    }

    @Override
    public void play() {
        isPlaying = true;
        System.out.println("▶ Odtwarzacz MP3: " + audioSource.getSourceName() +
                " (czas: " + audioSource.getDuration() + "s, głośność: " + volume + ")");
    }

    @Override
    public void stop() {
        isPlaying = false;
        System.out.println("⏹ Odtwarzacz MP3 zatrzymany");
    }

    @Override
    public void setVolume(int volume) {
        this.volume = Math.max(0, Math.min(100, volume));
        System.out.println("🔊 Głośność MP3: " + this.volume);
    }

    @Override
    public String getPlayerInfo() {
        return "MP3 Player z źródłem: " + audioSource.getSourceName();
    }
}
// Koniec, Tydzień 3, Wzorzec Bridge 2