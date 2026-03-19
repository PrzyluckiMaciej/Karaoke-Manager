package pollub.karaokeapp.Week4.proxy;

import pollub.karaokeapp.service.audio.AudioInput;
import pollub.karaokeapp.Week2.singleton.LoggerSingleton;

/**
 * Tydzień 4, Wzorzec Proxy 4
 * Proxy dla bezpiecznego dostępu do urządzeń audio
 * Kontroluje dostęp, zarządza zasobami i loguje operacje
 */
public class AudioDeviceProxy implements AudioInput {

    private final AudioInput realDevice;
    private final LoggerSingleton logger = LoggerSingleton.getInstance();
    private boolean isConnected = false;
    private int accessCount = 0;
    private long lastAccessTime;

    public AudioDeviceProxy(AudioInput realDevice) {
        this.realDevice = realDevice;
    }

    // Nawiązanie połączenia z urządzeniem
    public void connect() {
        logger.log("[DEVICE-PROXY] Nawiązywanie połączenia z urządzeniem...");
        this.isConnected = true;
        logger.log("[DEVICE-PROXY] ✓ Urządzenie połączone: " + realDevice.getDeviceInfo());
    }

    // Odłączenie urządzenia
    public void disconnect() {
        logger.log("[DEVICE-PROXY] Odłączanie urządzenia...");
        this.isConnected = false;
        logger.log("[DEVICE-PROXY] ✓ Urządzenie odłączone");
    }

    // Sprawdzenie, czy urządzenie jest połączone
    private void ensureConnected() {
        if (!isConnected) {
            throw new IllegalStateException("[DEVICE-PROXY] Urządzenie nie jest połączone!");
        }
    }

    @Override
    public byte[] record(int durationSeconds) {
        ensureConnected();
        accessCount++;
        lastAccessTime = System.currentTimeMillis();

        logger.log("[DEVICE-PROXY] Nagrywanie (" + accessCount + ". dostęp)...");
        byte[] recordedData = realDevice.record(durationSeconds);
        logger.log("[DEVICE-PROXY] ✓ Nagranie zakończone (" + recordedData.length + " bajtów)");

        return recordedData;
    }

    @Override
    public int getSampleRate() {
        ensureConnected();
        return realDevice.getSampleRate();
    }

    @Override
    public String getDeviceInfo() {
        return realDevice.getDeviceInfo() + " [PROXY, dostępy: " + accessCount + "]";
    }

    public boolean isConnected() {
        return isConnected;
    }

    public int getAccessCount() {
        return accessCount;
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }

    // Statystyki użycia
    public void printStatistics() {
        logger.log("[DEVICE-PROXY] === STATYSTYKI ===");
        logger.log("[DEVICE-PROXY] Urządzenie: " + realDevice.getDeviceInfo());
        logger.log("[DEVICE-PROXY] Liczba dostępów: " + accessCount);
        logger.log("[DEVICE-PROXY] Ostatni dostęp: " + lastAccessTime);
        logger.log("[DEVICE-PROXY] Status: " + (isConnected ? "POŁĄCZONE" : "ODŁĄCZONE"));
    }
}
// Koniec, Tydzień 4, Wzorzec Proxy 4
