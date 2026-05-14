package pollub.karaokeapp.Week4.flyweight;

import pollub.karaokeapp.Week2.singleton.LoggerSingleton;
import java.util.HashMap;
import java.util.Map;

/**
 * Tydzień 4, Wzorzec Flyweight 3 (Factory)
 * Factory dla SongMetadataFlyweight
 * Zarządza poolem metadanych
 */
public class SongMetadataFactory {
    private static final Map<String, SongMetadataFlyweight> metadataCache = new HashMap<>();
    private static final LoggerSingleton logger = LoggerSingleton.getInstance();
    private static final String DEFAULT_COPYRIGHT = "© 2024 Karaoke Manager";
    private static final String STANDARD_LICENSE = "STANDARD_LICENSE";
    private static final String EXPLICIT_LICENSE = "EXPLICIT_LICENSE";

    static {
        initializeMetadata();
    }

    public static SongMetadataFlyweight getMetadata(String language, String contentRating) {
        String key = (language + "_" + contentRating).toLowerCase();

        if (metadataCache.containsKey(key)) {
            logger.log("[METADATA-FLY] Pobranie z cache: " + key);
            return metadataCache.get(key);
        }

        logger.log("[METADATA-FLY] Tworzenie nowych metadanych: " + key);
        SongMetadataFlyweight metadata = createMetadata(language, contentRating, false);
        metadataCache.put(key, metadata);
        return metadata;
    }

    private static SongMetadataFlyweight createMetadata(String language, String rating, boolean explicit) {
        String license = explicit ? EXPLICIT_LICENSE : STANDARD_LICENSE;
        return new SongMetadataFlyweight(language, rating, DEFAULT_COPYRIGHT, license, explicit);
    }

    private static void initializeMetadata() {
        addMetadata("english", "u", false);
        addMetadata("english", "pg", false);
        addMetadata("polish", "12", false);
        addMetadata("english", "15", true);
        addMetadata("polish", "18", true);

        logger.log("[METADATA-FLY] ✓ Zainicjalizowano " + metadataCache.size() + " kombinacji metadanych");
    }

    private static void addMetadata(String language, String rating, boolean explicit) {
        String key = language + "_" + rating;
        metadataCache.put(key, createMetadata(language, rating, explicit));
    }

    public static int getCacheSize() { return metadataCache.size(); }

    public static void printCacheContents() {
        logger.log("[METADATA-FLY] === Cache Metadanych ===");
        for (String key : metadataCache.keySet()) {
            logger.log("[METADATA-FLY] " + key + " -> " + metadataCache.get(key));
        }
    }
}
// Koniec, Tydzień 4, Wzorzec Flyweight 3
