package pollub.karaokeapp.Week6.observer;

/**
 * Stałe reprezentujące typy zdarzeń w systemie karaoke
 * Centralne miejsce dla wszystkich typów zdarzeń - eliminuje duplikację stringów
 */
public final class SongEventType {

    private SongEventType() {
        // Prywatny konstruktor - klasa wyłącznie ze stałymi
        throw new UnsupportedOperationException("Klasa stałych nie może być instancjonowana");
    }

    /** Zdarzenie: zmiana tytułu piosenki */
    public static final String TITLE_CHANGED = "SONG_TITLE_CHANGED";

    /** Zdarzenie: zmiana poziomu trudności piosenki */
    public static final String DIFFICULTY_CHANGED = "SONG_DIFFICULTY_CHANGED";

    /** Zdarzenie: zmiana gatunku piosenki */
    public static final String GENRE_CHANGED = "SONG_GENRE_CHANGED";

    /** Zdarzenie: zmiana artysty piosenki */
    public static final String ARTIST_CHANGED = "SONG_ARTIST_CHANGED";

    /** Zdarzenie: nowe wykonanie zostało rozpoczęte */
    public static final String PERFORMANCE_STARTED = "PERFORMANCE_STARTED";

    /** Zdarzenie: wykonanie zostało zakończone */
    public static final String PERFORMANCE_FINISHED = "PERFORMANCE_FINISHED";

    /** Zdarzenie: playlista została zmodyfikowana */
    public static final String PLAYLIST_MODIFIED = "PLAYLIST_MODIFIED";
}