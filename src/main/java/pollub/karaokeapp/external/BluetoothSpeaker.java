package pollub.karaokeapp.external;

public class BluetoothSpeaker implements ExternalInputDevice {
    @Override
    public byte[] readAudioData() {
        return new byte[]{10, 11, 12, 13, 14, 15};
    }

    @Override
    public int getSampleRate() {
        return 48000;
    }

    @Override
    public String getDeviceType() {
        return "Bluetooth Speaker";
    }
}