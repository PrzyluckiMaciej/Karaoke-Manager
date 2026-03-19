package pollub.karaokeapp.Week4.facade;

import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;
import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.Week2.builder.song.SongBuilder;
import pollub.karaokeapp.Week2.builder.user.UserBuilder;
import pollub.karaokeapp.Week2.builder.performance.PerformanceBuilder;
import pollub.karaokeapp.Week2.singleton.LoggerSingleton;
import pollub.karaokeapp.service.scoring.ScoringStrategy;
import pollub.karaokeapp.Week2.factory.scoring.ScoringStrategyFactory;

import java.util.List;

/**
 * Tydzień 4, Wzorzec Facade 1
 * Główna fasada dla aplikacji Karaoke
 * Upraszcza kompleksowe operacje do prostych metod
 */
public class KaraokeFacade {

    private final AudioSystemFacade audioSystem;
    private final PlaylistManagementFacade playlistManager;
    private final ScoringSystemFacade scoringSystem;
    private final LoggerSingleton logger = LoggerSingleton.getInstance();

    public KaraokeFacade() {
        this.audioSystem = new AudioSystemFacade();
        this.playlistManager = new PlaylistManagementFacade();
        this.scoringSystem = new ScoringSystemFacade();
    }

    // Uproszczona metoda do uruchomienia sesji karaoke
    public void startKaraokeSession(String sessionName) {
        logger.log("=== Uruchamianie sesji karaoke: " + sessionName + " ===");
        audioSystem.initializeAudio();
        audioSystem.calibrateDevices();
        logger.log("Sesja karaoke gotowa do użytku!");
    }

    // Uproszczona metoda do grania piosenki
    public void playSong(Song song, User singer) {
        logger.log("▶ Gra piosenka: " + song.getTitle() + " przez " + singer.getNickname());
        audioSystem.prepareAudioForPlayback(song);
        audioSystem.playAudio();
        logger.log("✓ Koniec piosenki");
    }

    // Uproszczona metoda do nagrania wykonania
    public Performance recordPerformance(Song song, List<User> participants) {
        logger.log("🎤 Nagrywanie wykonania: " + song.getTitle());

        byte[] recordedAudio = audioSystem.recordAudio(song.getDuration());
        Performance performance = new PerformanceBuilder(song)
                .addParticipant(participants.get(0))
                .setScore(0)
                .build();

        int score = scoringSystem.calculatePerformanceScore(performance, recordedAudio);
        performance.setScore(score);

        logger.log("✓ Wykonanie zarejestrowane. Wynik: " + score);
        return performance;
    }

    // Uproszczona metoda do zarządzania playlistą
    public void managePlaylist(String playlistName, List<Song> songs) {
        logger.log("📋 Zarządzanie playlistą: " + playlistName);
        playlistManager.createPlaylist(playlistName);
        for (Song song : songs) {
            playlistManager.addSongToPlaylist(song);
        }
        logger.log("✓ Playlista przygotowana. Piosenek: " + songs.size());
    }

    // Uproszczona metoda do pracy z efektami
    public void applyAudioEffects(String effectType) {
        logger.log("🎛️ Aplikowanie efektu: " + effectType);
        audioSystem.applyEffect(effectType);
        logger.log("✓ Efekt zastosowany");
    }

    // Uproszczona metoda do zakończenia sesji
    public void endKaraokeSession() {
        logger.log("⏹ Zamykanie sesji karaoke...");
        audioSystem.stopAudio();
        audioSystem.releaseResources();
        logger.log("✓ Sesja zakończona");
    }

    public AudioSystemFacade getAudioSystem() {
        return audioSystem;
    }

    public PlaylistManagementFacade getPlaylistManager() {
        return playlistManager;
    }

    public ScoringSystemFacade getScoringSystem() {
        return scoringSystem;
    }
}
// Koniec, Tydzień 4, Wzorzec Facade 1
