package pollub.karaokeapp.Week4.flyweight;

import pollub.karaokeapp.Week2.singleton.LoggerSingleton;
import java.util.HashMap;
import java.util.Map;

/**
 * Tydzień 4, Wzorzec Flyweight 1 (Factory)
 * Factory dla GenreCharacteristicsFlyweight
 * Zarządza pulą i ponownym użyciem obiektów Flyweight
 */
public class GenreCharacteristicsFactory {
    private static final Map<String, GenreCharacteristicsFlyweight> cache = new HashMap<>();
    private static final LoggerSingleton logger = LoggerSingleton.getInstance();

    static {
        initializeGenres();
    }

    public static GenreCharacteristicsFlyweight getGenreCharacteristics(String genreName) {
        String key = genreName.toLowerCase();

        if (cache.containsKey(key)) {
            logger.log("[GENRE-FLY] Pobranie z cache: " + genreName);
            return cache.get(key);
        }

        throw new IllegalArgumentException("Nieznany gatunek: " + genreName + ". Dostępne: " + cache.keySet());
    }

    private static void initializeGenres() {
        addGenre("rock", "Muzyka rockowa - energiczna i ekspresyjna", 6, "Mikrofon dynamiczny, wzmacniacz", 24);
        addGenre("pop", "Muzyka popowa - łatwa i przyjazna", 3, "Mikrofon pojemnościowy, system PA", 18);
        addGenre("jazz", "Jazz - wymagająca i artystyczna", 8, "Profesjonalny mikrofon, studyjny system", 32);
        addGenre("rap", "Rap - rytmiczny i agresywny", 7, "Mikrofon USB, przenośny system", 20);
        addGenre("grunge", "Grunge - surowy i emocjonalny", 7, "Dynamiczny mikrofon, wzmacniacz", 26);

        logger.log("[GENRE-FLY] ✓ Zainicjalizowano " + cache.size() + " gatunków");
    }

    private static void addGenre(String name, String desc, int difficulty, String equipment, int range) {
        cache.put(name, new GenreCharacteristicsFlyweight(name, desc, difficulty, equipment, range));
    }

    public static int getCacheSize() { return cache.size(); }

    public static void printCacheContents() {
        logger.log("[GENRE-FLY] === Cache Gatunków ===");
        for (String key : cache.keySet()) {
            logger.log("[GENRE-FLY] " + cache.get(key));
        }
    }
}
// Koniec, Tydzień 4, Wzorzec Flyweight 1
