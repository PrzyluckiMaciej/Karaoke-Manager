package pollub.karaokeapp.Week3.bridge.audio;

import pollub.karaokeapp.Week3.bridge.constants.BridgeConstants;

/**
 * Tydzień 3, Wzorzec Bridge 2
 * Konkretna implementacja - odtwarzacz streamingowy
 */
public class StreamingPlayer extends AudioPlayer {

    private final VolumeControl volumeControl;
    private boolean isPlaying;
    private final String streamUrl;

    public StreamingPlayer(AudioSource audioSource, String streamUrl) {
        super(audioSource);
        this.volumeControl = new VolumeControl(BridgeConstants.DEFAULT_STREAMING_VOLUME);
        this.isPlaying = false;
        this.streamUrl = streamUrl;
    }

    @Override
    public void play() {
        isPlaying = true;
        System.out.println("🌐 Streaming z: " + streamUrl);
        System.out.println("   Źródło: " + audioSource.getSourceName() +
                " (czas: " + audioSource.getDuration() + "s, głośność: " + volumeControl.getVolume() + ")");
    }

    @Override
    public void stop() {
        isPlaying = false;
        System.out.println("⏹ Streaming zatrzymany");
    }

    @Override
    public void setVolume(int volume) {
        volumeControl.setVolume(volume);
        volumeControl.printVolumeChange("streamingu");
    }

    @Override
    public String getPlayerInfo() {
        return "Streaming Player (" + streamUrl + ") z źródłem: " + audioSource.getSourceName();
    }
}
// Koniec, Tydzień 3, Wzorzec Bridge 2