package pollub.karaokeapp.Week4;

import pollub.karaokeapp.Week4.facade.*;
import pollub.karaokeapp.Week4.proxy.*;
import pollub.karaokeapp.Week4.flyweight.*;
import pollub.karaokeapp.Week2.builder.song.SongBuilder;
import pollub.karaokeapp.Week2.builder.user.UserBuilder;
import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;
import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.Week2.singleton.LoggerSingleton;
import pollub.karaokeapp.service.audio.AudioInput;

import java.util.List;

public class Week4_Presentation {

    public static void week4_presentation() {
        LoggerSingleton logger = LoggerSingleton.getInstance();
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("PREZENTACJA NOWYCH WZORCÓW PROJEKTOWYCH - TYDZIEŃ 4");
        System.out.println("Facade, Proxy, Flyweight");
        System.out.println("--------------------------------------------------------------------------------\n");

        demonstrateFacade();
        demonstrateProxy();
        demonstrateFlyweight();

        System.out.println("--------------------------------------------------------------------------------------------\n");
    }

    private static void demonstrateFacade() {
        System.out.println("\n----------------------------------- FACADE -----------------------------------");

        LoggerSingleton logger = LoggerSingleton.getInstance();

        // Facade 1: Główna fasada aplikacji
        System.out.println("\n--- Facade 1: Główna fasada aplikacji ---");
        KaraokeFacade karaokeFacade = new KaraokeFacade();
        karaokeFacade.startKaraokeSession("Piątkowa Noc Karaoke");

        // Facade 2: System audio
        System.out.println("\n--- Facade 2: System audio ---");
        AudioSystemFacade audioSystem = karaokeFacade.getAudioSystem();
        audioSystem.setVolume(75);
        audioSystem.applyEffect("reverb");

        // Facade 3: Zarządzanie playlistami
        System.out.println("\n--- Facade 3: Zarządzanie playlistami ---");
        Song song1 = new SongBuilder("Bohemian Rhapsody", "Queen")
                .setGenre("Rock")
                .setDuration(354)
                .setDifficulty(8)
                .build();

        Song song2 = new SongBuilder("Imagine", "John Lennon")
                .setGenre("Pop")
                .setDuration(183)
                .setDifficulty(5)
                .build();

        PlaylistManagementFacade playlistManager = karaokeFacade.getPlaylistManager();
        playlistManager.createPlaylist("Rock Classics");
        playlistManager.addSongToPlaylist(song1);
        playlistManager.addSongToPlaylist(song2);
        System.out.println(playlistManager.getPlaylistInfo());

        // Facade 4: System oceniania
        System.out.println("\n--- Facade 4: System oceniania ---");
        ScoringSystemFacade scoringSystem = karaokeFacade.getScoringSystem();
        User user = new UserBuilder("Rockowy Jan").setLevel(7).build();
        Performance performance = new Performance(song1, List.of(user), 0);
        byte[] recordedAudio = new byte[1000];
        int score = scoringSystem.calculatePerformanceScore(performance, recordedAudio);
        scoringSystem.recordScore(score);
        System.out.println("Wynik: " + score);

        karaokeFacade.endKaraokeSession();
        System.out.println("--------------------------------------------------------------------------------\n");
    }

    private static void demonstrateProxy() {
        System.out.println("\n----------------------------------- PROXY -----------------------------------");

        LoggerSingleton logger = LoggerSingleton.getInstance();

        // Proxy 1: Leniwi ładowaniem piosenek
        System.out.println("\n--- Proxy 1: Lazy Loading Songs ---");
        LazyLoadingSongProxy lazySong = new LazyLoadingSongProxy("Numb;Linkin Park;187;Rock;6");
        System.out.println("Piosenka utworzona (nie załadowana jeszcze)");
        System.out.println("Tytuł: " + lazySong.getTitle() + " (teraz załadowana)");

        // Proxy 2: Wydajność i cache
        System.out.println("\n--- Proxy 2: Performance Caching ---");
        Performance perf = new Performance(lazySong, List.of(new UserBuilder("Test User").build()), 150);
        PerformanceProxy cachedPerf = new PerformanceProxy(perf);

        long start = System.currentTimeMillis();
        int score1 = cachedPerf.getScore();
        long time1 = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        int score2 = cachedPerf.getScore();
        long time2 = System.currentTimeMillis() - start;

        System.out.println("Pierwszy dostęp: " + time1 + "ms, Wynik: " + score1);
        System.out.println("Drugi dostęp (cache): " + time2 + "ms, Wynik: " + score2);

        // Proxy 3: Autentykacja i kontrola dostępu
        System.out.println("\n--- Proxy 3: User Authentication ---");
        User realUser = new UserBuilder("John Doe").setLevel(5).setPoints(1000).build();
        UserAuthenticationProxy secureUser = new UserAuthenticationProxy(realUser, "premium");

        System.out.println("Próba dostępu bez autentykacji...");
        try {
            secureUser.getPoints();
        } catch (SecurityException e) {
            System.out.println("  Błąd: " + e.getMessage());
        }

        System.out.println("Autentykacja użytkownika...");
        if (secureUser.authenticate("password123")) {
            System.out.println("  Punkty: " + secureUser.getPoints());
            System.out.println("  Poziom: " + secureUser.getLevel());
        }
        secureUser.logout();

        // Proxy 4: Audio device proxy
        System.out.println("\n--- Proxy 4: Audio Device Proxy ---");
        AudioDeviceProxy deviceProxy = new AudioDeviceProxy(new MockAudioDevice());

        System.out.println("Próba nagrywania bez połączenia...");
        try {
            deviceProxy.record(5);
        } catch (IllegalStateException e) {
            System.out.println("  Błąd: " + e.getMessage());
        }

        System.out.println("Nawiązywanie połączenia...");
        deviceProxy.connect();
        byte[] audio = deviceProxy.record(10);
        System.out.println("Nagranie: " + audio.length + " bajtów");
        deviceProxy.printStatistics();
        deviceProxy.disconnect();

        System.out.println("--------------------------------------------------------------------------------\n");
    }

    private static void demonstrateFlyweight() {
        System.out.println("\n----------------------------------- FLYWEIGHT -----------------------------------");

        LoggerSingleton logger = LoggerSingleton.getInstance();

        // Flyweight 1: Genre characteristics
        System.out.println("\n--- Flyweight 1: Genre Characteristics ---");
        GenreCharacteristicsFactory.printCacheContents();

        GenreCharacteristicsFlyweight rock1 = GenreCharacteristicsFactory.getGenreCharacteristics("rock");
        GenreCharacteristicsFlyweight rock2 = GenreCharacteristicsFactory.getGenreCharacteristics("rock");
        System.out.println("Rock1 i Rock2 to ta sama instancja: " + (rock1 == rock2));
        System.out.println("Cache size: " + GenreCharacteristicsFactory.getCacheSize());

        System.out.println("\n--- Flyweight 1: Genre Characteristics (z zewnętrznym stanem) ---");
        GenreCharacteristicsFlyweight rock = GenreCharacteristicsFactory.getGenreCharacteristics("rock");
        System.out.println("Podstawowa trudność dla Rock: " + rock.getBaseDifficultyLevel());

        // Użycie userSkill przekazywanego przez klienta
        int userSkill = 5; // Przykładowy zewnętrzny parametr (umiejętności użytkownika)
        int adjustedDifficulty = rock.calculateAdjustedDifficulty(userSkill);
        System.out.println("Dostosowana trudność dla użytkownika z umiejętnościami " + userSkill + ": " + adjustedDifficulty);

        // Porównanie z innym userSkill
        int beginnerSkill = 2;
        int adjustedForBeginner = rock.calculateAdjustedDifficulty(beginnerSkill);
        System.out.println("Dostosowana trudność dla początkującego (umiejętności " + beginnerSkill + "): " + adjustedForBeginner);

        // Flyweight 2: Audio effects
        System.out.println("\n--- Flyweight 2: Audio Effects ---");
        AudioEffectFactory.printAvailableEffects();

        AudioEffectFlyweight echo1 = AudioEffectFactory.getEffect("echo");
        AudioEffectFlyweight echo2 = AudioEffectFactory.getEffect("echo");
        System.out.println("Echo1 i Echo2 to ta sama instancja: " + (echo1 == echo2));
        System.out.println("Cache size: " + AudioEffectFactory.getCacheSize());

        // Flyweight 3: Song metadata
        System.out.println("\n--- Flyweight 3: Song Metadata ---");
        SongMetadataFlyweight meta1 = SongMetadataFactory.getMetadata("English", "PG");
        SongMetadataFlyweight meta2 = SongMetadataFactory.getMetadata("English", "PG");
        System.out.println("Meta1 i Meta2 to ta sama instancja: " + (meta1 == meta2));
        System.out.println("Cache size: " + SongMetadataFactory.getCacheSize());
        SongMetadataFactory.printCacheContents();

        // Flyweight 4: User avatars
        System.out.println("\n--- Flyweight 4: User Avatars ---");
        UserAvatarFactory.printAvailableAvatars();

        UserAvatarFlyweight avatar1 = UserAvatarFactory.getAvatar("singer_gold");
        UserAvatarFlyweight avatar2 = UserAvatarFactory.getAvatar("singer_gold");
        System.out.println("Avatar1 i Avatar2 to ta sama instancja: " + (avatar1 == avatar2));
        System.out.println("Cache size: " + UserAvatarFactory.getCacheSize());

        System.out.println("--------------------------------------------------------------------------------\n");
    }

    private static class MockAudioDevice implements AudioInput {
        @Override
        public byte[] record(int durationSeconds) {
            return new byte[durationSeconds * 44100 * 2];
        }

        @Override
        public int getSampleRate() {
            return 44100;
        }

        @Override
        public String getDeviceInfo() {
            return "Mock Audio Device";
        }
    }
}
// Koniec, Tydzień 4, Wzorce Facade, Proxy, Flyweight
