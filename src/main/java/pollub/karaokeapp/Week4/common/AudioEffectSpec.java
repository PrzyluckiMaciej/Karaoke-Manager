// Week4/common/AudioEffectSpec.java
package pollub.karaokeapp.Week4.common;

public class AudioEffectSpec {
    private final String key;
    private final String name;
    private final String type;
    private final float intensity;
    private final int delay;
    private final String preset;

    public AudioEffectSpec(String key, String name, String type, float intensity, int delay, String preset) {
        this.key = key;
        this.name = name;
        this.type = type;
        this.intensity = intensity;
        this.delay = delay;
        this.preset = preset;
    }

    // gettery
    public String getKey() { return key; }
    public String getName() { return name; }
    public String getType() { return type; }
    public float getIntensity() { return intensity; }
    public int getDelay() { return delay; }
    public String getPreset() { return preset; }
}