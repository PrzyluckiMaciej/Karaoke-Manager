package pollub.karaokeapp.Week4.proxy;

import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.service.song.ExternalSongAPI;
import pollub.karaokeapp.Week2.singleton.LoggerSingleton;

/**
 * Tydzień 4, Wzorzec Proxy 1
 * Proxy do leniwego ładowania piosenek z zewnętrznego API
 * Piosenka jest ładowana dopiero przy pierwszym dostępie
 */
public class LazyLoadingSongProxy extends Song {
    private final ExternalSongAPI externalAPI;
    private Song realSong;
    private boolean loaded = false;
    private final LoggerSingleton logger = LoggerSingleton.getInstance();

    public LazyLoadingSongProxy(String externalSongData) {
        super("", "", 0, "", 0);
        this.externalAPI = new ExternalSongAPI(externalSongData);
    }

    private void loadSongIfNeeded() {
        if (!loaded) {
            performLazyLoad();
        }
    }

    private void performLazyLoad() {
        logLazyLoadStart();
        realSong = createSongFromApiData();
        markAsLoaded();
        logLazyLoadComplete();
    }

    private void logLazyLoadStart() {
        logger.log("[LAZY-PROXY] Ładowanie piosenki z API...");
    }

    private Song createSongFromApiData() {
        String[] data = externalAPI.parseSongInfo();
        return new Song(data[0], data[1], Integer.parseInt(data[2]), data[3], Integer.parseInt(data[4]));
    }

    private void markAsLoaded() {
        loaded = true;
    }

    private void logLazyLoadComplete() {
        logger.log("[LAZY-PROXY] ✓ Piosenka załadowana: " + realSong.getTitle());
    }

    @Override
    public String getTitle() {
        loadSongIfNeeded();
        return realSong.getTitle();
    }

    @Override
    public String getArtist() {
        loadSongIfNeeded();
        return realSong.getArtist();
    }

    @Override
    public int getDuration() {
        loadSongIfNeeded();
        return realSong.getDuration();
    }

    @Override
    public String getGenre() {
        loadSongIfNeeded();
        return realSong.getGenre();
    }

    @Override
    public int getDifficulty() {
        loadSongIfNeeded();
        return realSong.getDifficulty();
    }

    @Override
    public String toString() {
        loadSongIfNeeded();
        return realSong.toString();
    }

    @Override
    public Song clone() {
        loadSongIfNeeded();
        return realSong.clone();
    }

    public boolean isLoaded() { return loaded; }
}
// Koniec, Tydzień 4, Wzorzec Proxy 1
