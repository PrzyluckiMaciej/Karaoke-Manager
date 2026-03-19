package pollub.karaokeapp.Week4.flyweight;

/**
 * Tydzień 4, Wzorzec Flyweight 2
 * Flyweight dla współdzielonych efektów audio
 * Zmniejsza pamięć przez współdzielanie wspólnych konfiguracji efektów
 */
public class AudioEffectFlyweight {

    private final String effectName;
    private final String effectType; // echo, reverb, pitch, etc.
    private final float defaultIntensity;
    private final int defaultDelay;
    private final String presetConfiguration;

    public AudioEffectFlyweight(
            String effectName,
            String effectType,
            float defaultIntensity,
            int defaultDelay,
            String presetConfiguration) {
        this.effectName = effectName;
        this.effectType = effectType;
        this.defaultIntensity = defaultIntensity;
        this.defaultDelay = defaultDelay;
        this.presetConfiguration = presetConfiguration;
    }

    public String getEffectName() {
        return effectName;
    }

    public String getEffectType() {
        return effectType;
    }

    public float getDefaultIntensity() {
        return defaultIntensity;
    }

    public int getDefaultDelay() {
        return defaultDelay;
    }

    public String getPresetConfiguration() {
        return presetConfiguration;
    }

    @Override
    public String toString() {
        return "Effect{" +
                "name='" + effectName + '\'' +
                ", type='" + effectType + '\'' +
                ", intensity=" + defaultIntensity +
                ", delay=" + defaultDelay +
                '}';
    }
}
// Koniec, Tydzień 4, Wzorzec Flyweight 2
