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
    private static final String DEFAULT_AVATAR_ID = "default";

    static {
        initializeAvatars();
    }

    public static UserAvatarFlyweight getAvatar(String avatarId) {
        String key = avatarId.toLowerCase();

        if (avatarCache.containsKey(key)) {
            logger.log("[AVATAR-FLY] Pobranie z cache: " + avatarId);
            return avatarCache.get(key);
        }

        logger.log("[AVATAR-FLY] Awatar nie znaleziony: " + avatarId + ", zwracam domyślny");
        return getDefaultAvatar();
    }

    private static void initializeAvatars() {
        addAvatar("singer_gold", "Golden Singer", "avatar_singer_gold.png", "#FFD700", "MASTER_VOCALIST");
        addAvatar("singer_silver", "Silver Singer", "avatar_singer_silver.png", "#C0C0C0", "ADVANCED_VOCALIST");
        addAvatar("singer_bronze", "Bronze Singer", "avatar_singer_bronze.png", "#CD7F32", "INTERMEDIATE_VOCALIST");
        addAvatar("beginner_mic", "Beginner", "avatar_beginner.png", "#87CEEB", "NOVICE");
        addAvatar("legend_crown", "Karaoke Legend", "avatar_legend.png", "#FF1493", "KARAOKE_LEGEND");

        logger.log("[AVATAR-FLY] ✓ Zainicjalizowano " + avatarCache.size() + " awatarów");
    }

    private static void addAvatar(String id, String name, String image, String color, String badge) {
        avatarCache.put(id, new UserAvatarFlyweight(id, name, image, color, badge));
    }

    private static UserAvatarFlyweight getDefaultAvatar() {
        return new UserAvatarFlyweight(DEFAULT_AVATAR_ID, "Default User", "avatar_default.png", "#808080", "USER");
    }

    public static int getCacheSize() { return avatarCache.size(); }

    public static void printAvailableAvatars() {
        logger.log("[AVATAR-FLY] === Dostępne Awatary ===");
        for (String key : avatarCache.keySet()) {
            logger.log("[AVATAR-FLY] " + avatarCache.get(key));
        }
    }
}
// Koniec, Tydzień 4, Wzorzec Flyweight 4
