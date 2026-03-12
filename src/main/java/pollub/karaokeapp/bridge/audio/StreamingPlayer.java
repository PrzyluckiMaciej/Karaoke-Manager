package pollub.karaokeapp.bridge.audio;

/**
 * Tydzień 3, Wzorzec Bridge 2
 * Konkretna implementacja - odtwarzacz streamingowy
 */
public class StreamingPlayer extends AudioPlayer {

    private int volume;
    private boolean isPlaying;
    private String streamUrl;

    public StreamingPlayer(AudioSource audioSource, String streamUrl) {
        super(audioSource);
        this.volume = 70;
        this.isPlaying = false;
        this.streamUrl = streamUrl;
    }

    @Override
    public void play() {
        isPlaying = true;
        System.out.println("🌐 Streaming z: " + streamUrl);
        System.out.println("   Źródło: " + audioSource.getSourceName() +
                " (czas: " + audioSource.getDuration() + "s, głośność: " + volume + ")");
    }

    @Override
    public void stop() {
        isPlaying = false;
        System.out.println("⏹ Streaming zatrzymany");
    }

    @Override
    public void setVolume(int volume) {
        this.volume = Math.max(0, Math.min(100, volume));
        System.out.println("🔊 Głośność streamingu: " + this.volume);
    }

    @Override
    public String getPlayerInfo() {
        return "Streaming Player (" + streamUrl + ") z źródłem: " + audioSource.getSourceName();
    }
}
// Koniec, Tydzień 3, Wzorzec Bridge 2