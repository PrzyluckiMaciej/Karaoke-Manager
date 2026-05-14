package pollub.karaokeapp.Week3.adapter.audio;

import pollub.karaokeapp.service.audio.AudioInput;
import pollub.karaokeapp.service.audio.ExternalInputDevice;

/**
 * Tydzień 3, Wzorzec Adapter 4 (Adapter obiektu)
 * Adapter dostosowujący różne zewnętrzne urządzenia do jednolitego interfejsu AudioInput
 */
public class AudioInputAdapter implements AudioInput {

    private static final int BYTES_PER_SAMPLE = 2;
    private static final int BYTES_PER_CHUNK = 6;  // Zależne od urządzenia, można wyciągnąć z device

    private final ExternalInputDevice device;
    private byte[] lastBuffer;

    public AudioInputAdapter(ExternalInputDevice device) {
        this.device = device;
    }

    @Override
    public byte[] record(int durationSeconds) {
        byte[] buffer = allocateBuffer(durationSeconds);
        fillBufferWithAudioData(buffer);
        return storeAndReturn(buffer);
    }

    private byte[] allocateBuffer(int durationSeconds) {
        int totalBytes = durationSeconds * device.getSampleRate() * BYTES_PER_SAMPLE;
        return new byte[totalBytes];
    }

    private void fillBufferWithAudioData(byte[] buffer) {
        for (int offset = 0; offset < buffer.length; offset += BYTES_PER_CHUNK) {
            byte[] chunk = device.readAudioData();
            copyChunkToBuffer(chunk, buffer, offset);
        }
    }

    private void copyChunkToBuffer(byte[] chunk, byte[] buffer, int offset) {
        int bytesToCopy = Math.min(chunk.length, buffer.length - offset);
        System.arraycopy(chunk, 0, buffer, offset, bytesToCopy);
    }

    private byte[] storeAndReturn(byte[] buffer) {
        this.lastBuffer = buffer;
        return buffer;
    }

    @Override
    public int getSampleRate() {
        return device.getSampleRate();
    }

    @Override
    public String getDeviceInfo() {
        return "Adapter dla: " + device.getDeviceType();
    }
}
// Koniec, Tydzień 3, Wzorzec Adapter 4