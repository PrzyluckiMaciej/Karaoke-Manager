package pollub.karaokeapp.Week4.flyweight;

import pollub.karaokeapp.Week2.singleton.LoggerSingleton;
import pollub.karaokeapp.Week4.common.AudioEffectSpec;

import java.util.HashMap;
import java.util.Map;

/**
 * Tydzień 4, Wzorzec Flyweight 2 (Factory)
 * Factory dla AudioEffectFlyweight
 * Zarządza pulą efektów audio
 */
public class AudioEffectFactory {
    private static final Map<String, AudioEffectFlyweight> effectCache = new HashMap<>();
    private static final LoggerSingleton logger = LoggerSingleton.getInstance();

    static {
        initializeEffects();
    }

    public static AudioEffectFlyweight getEffect(String effectName) {
        String key = effectName.toLowerCase();

        if (effectCache.containsKey(key)) {
            logCacheHit(effectName);
            return effectCache.get(key);
        }

        throw new IllegalArgumentException("Nieznany efekt: " + effectName + ". Dostępne: " + effectCache.keySet());
    }

    private static void logCacheHit(String effectName) {
        logger.log("[EFFECT-FLY] Pobranie z cache: " + effectName);
    }

    private static void initializeEffects() {
        addEffect(new AudioEffectSpec("echo", "Echo", "echo", 0.8f, 50, "PRESET_ECHO_STANDARD"));
        addEffect(new AudioEffectSpec("reverb", "Reverb", "reverb", 0.6f, 200, "PRESET_REVERB_HALL"));
        addEffect(new AudioEffectSpec("pitch_correction", "Pitch Correction", "pitch", 1.0f, 0, "PRESET_AUTO_TUNE"));
        addEffect(new AudioEffectSpec("distortion", "Distortion", "distortion", 0.5f, 0, "PRESET_MILD_DISTORTION"));
        addEffect(new AudioEffectSpec("chorus", "Chorus", "chorus", 0.7f, 30, "PRESET_CHORUS_LIGHT"));

        logger.log("[EFFECT-FLY] ✓ Zainicjalizowano " + effectCache.size() + " efektów");
    }

    private static void addEffect(AudioEffectSpec spec) {
        effectCache.put(spec.getKey(), new AudioEffectFlyweight(
                spec.getName(), spec.getType(), spec.getIntensity(),
                spec.getDelay(), spec.getPreset()
        ));
    }

    public static int getCacheSize() { return effectCache.size(); }

    public static void printAvailableEffects() {
        logger.log("[EFFECT-FLY] === Dostępne Efekty ===");
        for (String key : effectCache.keySet()) {
            logger.log("[EFFECT-FLY] " + effectCache.get(key));
        }
    }
}
// Koniec, Tydzień 4, Wzorzec Flyweight 2
