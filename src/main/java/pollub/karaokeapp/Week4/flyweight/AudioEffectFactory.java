package pollub.karaokeapp.Week4.flyweight;

import pollub.karaokeapp.Week2.singleton.LoggerSingleton;
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

    // Pobranie efektu z cache
    public static AudioEffectFlyweight getEffect(String effectName) {
        String key = effectName.toLowerCase();

        if (effectCache.containsKey(key)) {
            logger.log("[EFFECT-FLY] Pobranie z cache: " + effectName);
            return effectCache.get(key);
        }

        logger.log("[EFFECT-FLY] Efekt nie znaleziony: " + effectName);
        return null;
    }

    // Inicjalizacja wspólnych efektów
    private static void initializeEffects() {
        effectCache.put("echo", new AudioEffectFlyweight(
                "Echo",
                "echo",
                0.8f,
                50,
                "PRESET_ECHO_STANDARD"
        ));

        effectCache.put("reverb", new AudioEffectFlyweight(
                "Reverb",
                "reverb",
                0.6f,
                200,
                "PRESET_REVERB_HALL"
        ));

        effectCache.put("pitch_correction", new AudioEffectFlyweight(
                "Pitch Correction",
                "pitch",
                1.0f,
                0,
                "PRESET_AUTO_TUNE"
        ));

        effectCache.put("distortion", new AudioEffectFlyweight(
                "Distortion",
                "distortion",
                0.5f,
                0,
                "PRESET_MILD_DISTORTION"
        ));

        effectCache.put("chorus", new AudioEffectFlyweight(
                "Chorus",
                "chorus",
                0.7f,
                30,
                "PRESET_CHORUS_LIGHT"
        ));

        logger.log("[EFFECT-FLY] ✓ Zainicjalizowano 5 efektów");
    }

    // Pobranie liczby efektów w cache
    public static int getCacheSize() {
        return effectCache.size();
    }

    // Wyświetlenie dostępnych efektów
    public static void printAvailableEffects() {
        logger.log("[EFFECT-FLY] === Dostępne Efekty ===");
        for (String key : effectCache.keySet()) {
            AudioEffectFlyweight effect = effectCache.get(key);
            logger.log("[EFFECT-FLY] " + effect.getEffectName() + " - " + effect);
        }
    }
}
// Koniec, Tydzień 4, Wzorzec Flyweight 2
