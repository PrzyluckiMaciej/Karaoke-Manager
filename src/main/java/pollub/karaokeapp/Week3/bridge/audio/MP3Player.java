package pollub.karaokeapp.Week3.bridge.audio;

import pollub.karaokeapp.Week3.bridge.constants.BridgeConstants;

/**
 * Tydzień 3, Wzorzec Bridge 2
 * Konkretna implementacja - odtwarzacz MP3
 */
public class MP3Player extends AudioPlayer {

    private final VolumeControl volumeControl;
    private boolean isPlaying;

    public MP3Player(AudioSource audioSource) {
        super(audioSource);
        this.volumeControl = new VolumeControl(BridgeConstants.DEFAULT_MP3_VOLUME);
        this.isPlaying = false;
    }

    @Override
    public void play() {
        isPlaying = true;
        System.out.println("▶ Odtwarzacz MP3: " + audioSource.getSourceName() +
                " (czas: " + audioSource.getDuration() + "s, głośność: " + volumeControl.getVolume() + ")");
    }

    @Override
    public void stop() {
        isPlaying = false;
        System.out.println("⏹ Odtwarzacz MP3 zatrzymany");
    }

    @Override
    public void setVolume(int volume) {
        volumeControl.setVolume(volume);
        volumeControl.printVolumeChange("MP3");
    }

    @Override
    public String getPlayerInfo() {
        return "MP3 Player z źródłem: " + audioSource.getSourceName();
    }
}
// Koniec, Tydzień 3, Wzorzec Bridge 2