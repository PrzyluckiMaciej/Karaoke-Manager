package pollub.karaokeapp.service.audio;

public interface AudioInput {
    byte[] record(int durationSeconds);
    int getSampleRate();
    String getDeviceInfo();
}