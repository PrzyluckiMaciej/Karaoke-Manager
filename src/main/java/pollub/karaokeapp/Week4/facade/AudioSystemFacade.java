package pollub.karaokeapp.Week4.facade;

import pollub.karaokeapp.Week3.adapter.audio.AudioInputAdapter;
import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.Week2.singleton.LoggerSingleton;
import pollub.karaokeapp.service.audio.AudioInput;
import pollub.karaokeapp.service.audio.USBMicrophone;

/**
 * Tydzień 4, Wzorzec Facade 2
 * Fasada dla systemu audio
 * Ukrywa złożoność inicjalizacji i zarządzania urządzeniami audio
 */
public class AudioSystemFacade {
    private static final int MIN_VOLUME = 0;
    private static final int MAX_VOLUME = 100;
    private static final int DEFAULT_VOLUME = 50;
    private static final int SAMPLE_RATE = 44100;
    private static final int BYTES_PER_SAMPLE = 2;
    private static final String DEFAULT_EFFECT = "none";

    private final LoggerSingleton logger = LoggerSingleton.getInstance();
    private boolean audioInitialized = false;
    private boolean isPlaying = false;
    private int volume = DEFAULT_VOLUME;
    private String currentEffect = DEFAULT_EFFECT;

    public void initializeAudio() {
        logInitializationStart();
        performHardwareInitialization();
        markInitializationComplete();
    }

    private void logInitializationStart() {
        logger.log("[AUDIO] Inicjalizacja systemu audio...");
    }

    private void performHardwareInitialization() {
        loadAudioDrivers();
        detectAudioDevices();
        calibrateAudioInput();
        setupMicrophone();
    }

    private void markInitializationComplete() {
        audioInitialized = true;
        logger.log("[AUDIO] ✓ System audio gotowy");
    }

    public void prepareAudioForPlayback(Song song) {
        ensureAudioInitialized();
        logSongPreparation(song);
        loadAudioFile(song.getTitle());
        normalizeAudioLevel(song);
        logPreparationComplete();
    }

    private void ensureAudioInitialized() {
        if (!audioInitialized) {
            initializeAudio();
        }
    }

    private void logSongPreparation(Song song) {
        logger.log("[AUDIO] Przygotowywanie: " + song.getTitle());
    }

    private void logPreparationComplete() {
        logger.log("[AUDIO] ✓ Audio przygotowane");
    }

    public void playAudio() {
        verifyAudioInitialized();
        startPlayback();
    }

    private void verifyAudioInitialized() {
        if (!audioInitialized) {
            throw new IllegalStateException("Audio nie zostało zainicjalizowane!");
        }
    }

    private void startPlayback() {
        isPlaying = true;
        logger.log("[AUDIO] ▶ Odtwarzanie (głośność: " + volume + "%, efekt: " + currentEffect + ")");
    }

    public byte[] recordAudio(int durationSeconds) {
        logRecordingStart(durationSeconds);
        byte[] recordedData = captureFromDevice(durationSeconds);
        logRecordingComplete();
        return recordedData;
    }

    private void logRecordingStart(int durationSeconds) {
        logger.log("[AUDIO] 🎤 Nagrywanie przez " + durationSeconds + " sekund...");
    }

    private byte[] captureFromDevice(int durationSeconds) {
        AudioInput device = createAudioDevice();
        return device.record(durationSeconds);
    }

    private void logRecordingComplete() {
        logger.log("[AUDIO] ✓ Nagranie zakończone");
    }

    private AudioInput createAudioDevice() {
        return new AudioInputAdapter(new USBMicrophone());
    }

    public void stopAudio() {
        isPlaying = false;
        logger.log("[AUDIO] ⏹ Zatrzymanie odtwarzania");
    }

    public void applyEffect(String effectType) {
        this.currentEffect = effectType;
        logger.log("[AUDIO] Efekt zmieniony na: " + effectType);
        configureEffectParameters(effectType);
    }

    public void setVolume(int volumeLevel) {
        this.volume = validateVolume(volumeLevel);
        logger.log("[AUDIO] Głośność: " + this.volume + "%");
    }

    private int validateVolume(int volumeLevel) {
        return Math.max(MIN_VOLUME, Math.min(MAX_VOLUME, volumeLevel));
    }

    public void calibrateDevices() {
        logger.log("[AUDIO] Kalibracja urządzeń...");
        testMicrophone();
        testSpeakers();
        logger.log("[AUDIO] ✓ Kalibracja zakończona");
    }

    public void releaseResources() {
        logger.log("[AUDIO] Zwalnianie zasobów audio...");
        closeAudioDevices();
        unloadAudioDrivers();
        audioInitialized = false;
        logger.log("[AUDIO] ✓ Zasoby zwolnione");
    }

    // Metody pomocnicze
    private void loadAudioDrivers() { logger.log("  → Ładowanie sterowników audio"); }
    private void detectAudioDevices() { logger.log("  → Wykrywanie urządzeń audio"); }
    private void calibrateAudioInput() { logger.log("  → Kalibracja wejścia audio"); }
    private void setupMicrophone() { logger.log("  → Konfiguracja mikrofonu"); }
    private void loadAudioFile(String filename) { logger.log("  → Ładowanie pliku: " + filename); }
    private void normalizeAudioLevel(Song song) { logger.log("  → Normalizacja poziomu dla: " + song.getTitle()); }
    private void configureEffectParameters(String effect) { logger.log("  → Konfigurowanie parametrów efektu: " + effect); }
    private void testMicrophone() { logger.log("  → Test mikrofonu"); }
    private void testSpeakers() { logger.log("  → Test głośników"); }
    private void closeAudioDevices() { logger.log("  → Zamykanie urządzeń audio"); }
    private void unloadAudioDrivers() { logger.log("  → Wyładowywanie sterowników"); }

    // Gettery
    public boolean isAudioInitialized() { return audioInitialized; }
    public boolean isPlaying() { return isPlaying; }
    public int getVolume() { return volume; }
    public String getCurrentEffect() { return currentEffect; }
}
// Koniec, Tydzień 4, Wzorzec Facade 2
