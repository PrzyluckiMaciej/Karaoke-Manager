package pollub.karaokeapp.Week4.flyweight;

import pollub.karaokeapp.Week2.singleton.LoggerSingleton;
import java.util.HashMap;
import java.util.Map;

/**
 * Tydzień 4, Wzorzec Flyweight 4 (Factory)
 * Factory dla UserAvatarFlyweight
 * Zarządza poolem awatarów
 */
public class UserAvatarFactory {

    private static final Map<String, UserAvatarFlyweight> avatarCache = new HashMap<>();
    private static final LoggerSingleton logger = LoggerSingleton.getInstance();

    static {
        initializeAvatars();
    }

    // Pobranie awatara z cache
    public static UserAvatarFlyweight getAvatar(String avatarId) {
        String key = avatarId.toLowerCase();

        if (avatarCache.containsKey(key)) {
            logger.log("[AVATAR-FLY] Pobranie z cache: " + avatarId);
            return avatarCache.get(key);
        }

        logger.log("[AVATAR-FLY] Awatar nie znaleziony: " + avatarId);
        return getDefaultAvatar();
    }

    // Inicjalizacja wspólnych awatarów
    private static void initializeAvatars() {
        avatarCache.put("singer_gold", new UserAvatarFlyweight(
                "SINGER_GOLD",
                "Golden Singer",
                "avatar_singer_gold.png",
                "#FFD700",
                "MASTER_VOCALIST"
        ));

        avatarCache.put("singer_silver", new UserAvatarFlyweight(
                "SINGER_SILVER",
                "Silver Singer",
                "avatar_singer_silver.png",
                "#C0C0C0",
                "ADVANCED_VOCALIST"
        ));

        avatarCache.put("singer_bronze", new UserAvatarFlyweight(
                "SINGER_BRONZE",
                "Bronze Singer",
                "avatar_singer_bronze.png",
                "#CD7F32",
                "INTERMEDIATE_VOCALIST"
        ));

        avatarCache.put("beginner_mic", new UserAvatarFlyweight(
                "BEGINNER_MIC",
                "Beginner",
                "avatar_beginner.png",
                "#87CEEB",
                "NOVICE"
        ));

        avatarCache.put("legend_crown", new UserAvatarFlyweight(
                "LEGEND_CROWN",
                "Karaoke Legend",
                "avatar_legend.png",
                "#FF1493",
                "KARAOKE_LEGEND"
        ));

        logger.log("[AVATAR-FLY] ✓ Zainicjalizowano 5 awatarów");
    }

    // Domyślny awatar
    private static UserAvatarFlyweight getDefaultAvatar() {
        return new UserAvatarFlyweight(
                "DEFAULT",
                "Default User",
                "avatar_default.png",
                "#808080",
                "USER"
        );
    }

    // Pobranie liczby awatarów w cache
    public static int getCacheSize() {
        return avatarCache.size();
    }

    // Wyświetlenie dostępnych awatarów
    public static void printAvailableAvatars() {
        logger.log("[AVATAR-FLY] === Dostępne Awatary ===");
        for (String key : avatarCache.keySet()) {
            UserAvatarFlyweight avatar = avatarCache.get(key);
            logger.log("[AVATAR-FLY] " + avatar.getAvatarName() + " - " + avatar);
        }
    }
}
// Koniec, Tydzień 4, Wzorzec Flyweight 4
