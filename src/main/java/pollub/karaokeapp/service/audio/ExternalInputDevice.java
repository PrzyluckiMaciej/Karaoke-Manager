package pollub.karaokeapp.service.audio;

/**
 * Różne zewnętrzne urządzenia wejściowe
 */
public interface ExternalInputDevice {
    byte[] readAudioData();
    int getSampleRate();
    String getDeviceType();
}