package pollub.karaokeapp.external;

/**
 * Różne zewnętrzne urządzenia wejściowe
 */
public interface ExternalInputDevice {
    byte[] readAudioData();
    int getSampleRate();
    String getDeviceType();
}