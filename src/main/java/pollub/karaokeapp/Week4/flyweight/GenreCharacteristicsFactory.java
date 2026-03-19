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
        // Inicjalizacja ze wspólnymi gatunkami
        initializeGenres();
    }

    // Pobranie charakterystyk gatunku (z cache lub stworzenie)
    public static GenreCharacteristicsFlyweight getGenreCharacteristics(String genreName) {
        String key = genreName.toLowerCase();

        if (cache.containsKey(key)) {
            logger.log("[GENRE-FLY] Pobranie z cache: " + genreName);
            return cache.get(key);
        }

        // Jeśli nie istnieje, tworzymy nowy (na potrzeby przykładu)
        logger.log("[GENRE-FLY] Tworzenie nowych charakterystyk: " + genreName);
        GenreCharacteristicsFlyweight characteristics = new GenreCharacteristicsFlyweight(
                genreName,
                "Gatunek: " + genreName,
                5,
                "Standard",
                24
        );
        cache.put(key, characteristics);
        return characteristics;
    }

    //Inicjalizacja wspólnych gatunków
    private static void initializeGenres() {
        cache.put("rock", new GenreCharacteristicsFlyweight(
                "Rock",
                "Muzyka rockowa - energiczna i ekspresyjna",
                6,
                "Mikrofon dynamiczny, wzmacniacz",
                24
        ));

        cache.put("pop", new GenreCharacteristicsFlyweight(
                "Pop",
                "Muzyka popowa - łatwa i przyjazna",
                3,
                "Mikrofon pojemnościowy, system PA",
                18
        ));

        cache.put("jazz", new GenreCharacteristicsFlyweight(
                "Jazz",
                "Jazz - wymagająca i artystyczna",
                8,
                "Profesjonalny mikrofon, studyjny system",
                32
        ));

        cache.put("rap", new GenreCharacteristicsFlyweight(
                "Rap",
                "Rap - rytmiczny i agresywny",
                7,
                "Mikrofon USB, przenośny system",
                20
        ));

        cache.put("grunge", new GenreCharacteristicsFlyweight(
                "Grunge",
                "Grunge - surowy i emocjonalny",
                7,
                "Dynamiczny mikrofon, wzmacniacz",
                26
        ));

        logger.log("[GENRE-FLY] ✓ Zainicjalizowano 5 gatunków");
    }

    // Pobranie liczby instancji w cache
    public static int getCacheSize() {
        return cache.size();
    }

    // Wyświetlenie zawartości cache
    public static void printCacheContents() {
        logger.log("[GENRE-FLY] === Cache Gatunków ===");
        for (String key : cache.keySet()) {
            GenreCharacteristicsFlyweight genre = cache.get(key);
            logger.log("[GENRE-FLY] " + genre.getGenreName() + " - " + genre);
        }
    }
}
// Koniec, Tydzień 4, Wzorzec Flyweight 1
