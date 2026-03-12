package pollub.karaokeapp.external;

public class USBMicrophone implements ExternalInputDevice {
    @Override
    public byte[] readAudioData() {
        return new byte[]{0, 1, 2, 3, 4, 5};
    }

    @Override
    public int getSampleRate() {
        return 44100;
    }

    @Override
    public String getDeviceType() {
        return "USB Microphone";
    }
}