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

    static {
        initializeMetadata();
    }

    // Pobranie metadanych (z cache lub stworzenie)
    public static SongMetadataFlyweight getMetadata(String language, String contentRating) {
        String key = (language + "_" + contentRating).toLowerCase();

        if (metadataCache.containsKey(key)) {
            logger.log("[METADATA-FLY] Pobranie z cache: " + key);
            return metadataCache.get(key);
        }

        logger.log("[METADATA-FLY] Tworzenie nowych metadanych: " + key);
        SongMetadataFlyweight metadata = new SongMetadataFlyweight(
                language,
                contentRating,
                "© 2024 Karaoke Manager",
                "STANDARD_LICENSE",
                false
        );
        metadataCache.put(key, metadata);
        return metadata;
    }

    // Inicjalizacja wspólnych kombinacji metadanych
    private static void initializeMetadata() {
        metadataCache.put("english_u", new SongMetadataFlyweight(
                "English",
                "U",
                "© 2024 Karaoke Manager",
                "STANDARD_LICENSE",
                false
        ));

        metadataCache.put("english_pg", new SongMetadataFlyweight(
                "English",
                "PG",
                "© 2024 Karaoke Manager",
                "STANDARD_LICENSE",
                false
        ));

        metadataCache.put("polish_12", new SongMetadataFlyweight(
                "Polish",
                "12",
                "© 2024 Karaoke Manager",
                "STANDARD_LICENSE",
                false
        ));

        metadataCache.put("english_15_explicit", new SongMetadataFlyweight(
                "English",
                "15",
                "© 2024 Karaoke Manager",
                "EXPLICIT_LICENSE",
                true
        ));

        metadataCache.put("polish_18_explicit", new SongMetadataFlyweight(
                "Polish",
                "18",
                "© 2024 Karaoke Manager",
                "EXPLICIT_LICENSE",
                true
        ));

        logger.log("[METADATA-FLY] ✓ Zainicjalizowano 5 kombinacji metadanych");
    }

    // Pobranie liczby instancji w cache
    public static int getCacheSize() {
        return metadataCache.size();
    }

    // Wyświetlenie zawartości cache
    public static void printCacheContents() {
        logger.log("[METADATA-FLY] === Cache Metadanych ===");
        for (String key : metadataCache.keySet()) {
            SongMetadataFlyweight metadata = metadataCache.get(key);
            logger.log("[METADATA-FLY] " + key + " -> " + metadata);
        }
    }
}
// Koniec, Tydzień 4, Wzorzec Flyweight 3
