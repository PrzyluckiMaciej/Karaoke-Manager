package pollub.karaokeapp.Week3.bridge.audio;

import pollub.karaokeapp.Week3.bridge.constants.BridgeConstants;

public class VolumeControl {

    private int volume;

    public VolumeControl(int defaultVolume) {
        this.volume = clamp(defaultVolume);
    }

    public void setVolume(int volume) {
        this.volume = clamp(volume);
    }

    public int getVolume() {
        return volume;
    }

    private int clamp(int value) {
        return Math.max(BridgeConstants.MIN_VOLUME, Math.min(BridgeConstants.MAX_VOLUME, value));
    }

    public void printVolumeChange(String playerType) {
        System.out.println("🔊 Głośność " + playerType + ": " + volume);
    }
}