package pollub.karaokeapp.adapter.audio;

import pollub.karaokeapp.service.audio.AudioInput;
import pollub.karaokeapp.service.audio.ExternalInputDevice;

/**
 * Tydzień 3, Wzorzec Adapter 4 (Adapter obiektu)
 * Adapter dostosowujący różne zewnętrzne urządzenia do jednolitego interfejsu AudioInput
 */
public class AudioInputAdapter implements AudioInput {

    private final ExternalInputDevice device;
    private byte[] lastBuffer;

    public AudioInputAdapter(ExternalInputDevice device) {
        this.device = device;
    }

    @Override
    public byte[] record(int durationSeconds) {
        byte[] result = new byte[durationSeconds * device.getSampleRate() * 2];

        for (int i = 0; i < result.length; i += 6) {
            byte[] chunk = device.readAudioData();
            System.arraycopy(chunk, 0, result, i, Math.min(chunk.length, result.length - i));
        }

        lastBuffer = result;
        return result;
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