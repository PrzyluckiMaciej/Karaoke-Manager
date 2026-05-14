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
        KaraokeFacade karaokeFacade = null;

        try {
            // Facade 1: Główna fasada aplikacji
            System.out.println("\n--- Facade 1: Główna fasada aplikacji ---");
            karaokeFacade = new KaraokeFacade();
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

        } catch (IllegalStateException e) {
            System.err.println("[FACADE-BŁĄD] Stan aplikacji: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("[FACADE-BŁĄD] Nieprawidłowy argument: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("[FACADE-BŁĄD] Nieoczekiwany błąd: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (karaokeFacade != null) {
                try {
                    karaokeFacade.endKaraokeSession();
                } catch (Exception e) {
                    System.err.println("[FACADE-BŁĄD] Problem podczas zamykania sesji: " + e.getMessage());
                }
            }
        }

        System.out.println("--------------------------------------------------------------------------------\n");
    }

    private static void demonstrateProxy() {
        System.out.println("\n----------------------------------- PROXY -----------------------------------");

        LoggerSingleton logger = LoggerSingleton.getInstance();

        // Proxy 1: Lazy loading songs
        System.out.println("\n--- Proxy 1: Lazy Loading Songs ---");
        try {
            LazyLoadingSongProxy lazySong = new LazyLoadingSongProxy("Numb;Linkin Park;187;Rock;6");
            System.out.println("Piosenka utworzona (nie załadowana jeszcze)");
            System.out.println("Tytuł: " + lazySong.getTitle() + " (teraz załadowana)");
        } catch (Exception e) {
            System.err.println("[PROXY-BŁĄD] Błąd przy lazy loading: " + e.getMessage());
            e.printStackTrace();
        }

        // Proxy 2: Performance Caching
        System.out.println("\n--- Proxy 2: Performance Caching ---");
        try {
            Song testSong = new SongBuilder("Test Song", "Test Artist")
                    .setGenre("Pop")
                    .setDuration(180)
                    .setDifficulty(5)
                    .build();

            Performance perf = new Performance(testSong, List.of(new UserBuilder("Test User").build()), 150);
            PerformanceProxy cachedPerf = new PerformanceProxy(perf);

            long start = System.currentTimeMillis();
            int score1 = cachedPerf.getScore();
            long time1 = System.currentTimeMillis() - start;

            start = System.currentTimeMillis();
            int score2 = cachedPerf.getScore();
            long time2 = System.currentTimeMillis() - start;

            System.out.println("Pierwszy dostęp: " + time1 + "ms, Wynik: " + score1);
            System.out.println("Drugi dostęp (cache): " + time2 + "ms, Wynik: " + score2);
        } catch (Exception e) {
            System.err.println("[PROXY-BŁĄD] Błąd przy cache'owaniu wydajności: " + e.getMessage());
            e.printStackTrace();
        }

        // Proxy 3: Authentication and access control
        System.out.println("\n--- Proxy 3: User Authentication ---");
        try {
            User realUser = new UserBuilder("John Doe").setLevel(5).setPoints(1000).build();
            UserAuthenticationProxy secureUser = new UserAuthenticationProxy(realUser, "premium");

            System.out.println("Próba dostępu bez autentykacji...");
            try {
                secureUser.getPoints();
            } catch (SecurityException e) {
                System.out.println("  Oczekiwany błąd: " + e.getMessage());
            }

            System.out.println("Autentykacja użytkownika...");
            if (secureUser.authenticate("password123")) {
                System.out.println("  Punkty: " + secureUser.getPoints());
                System.out.println("  Poziom: " + secureUser.getLevel());
            } else {
                System.err.println("  Autentykacja nieudana!");
            }
            secureUser.logout();

            // Test nieprawidłowego hasła
            System.out.println("\nTest nieprawidłowego hasła:");
            UserAuthenticationProxy anotherUser = new UserAuthenticationProxy(realUser, "user");
            boolean authResult = anotherUser.authenticate("wrong_password");
            System.out.println("  Autentykacja z nieprawidłowym hasłem: " + (authResult ? "sukces" : "porażka"));

        } catch (SecurityException e) {
            System.err.println("[PROXY-BŁĄD] Błąd bezpieczeństwa: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("[PROXY-BŁĄD] Nieoczekiwany błąd autentykacji: " + e.getMessage());
            e.printStackTrace();
        }

        // Proxy 4: Audio device proxy
        System.out.println("\n--- Proxy 4: Audio Device Proxy ---");
        AudioDeviceProxy deviceProxy = null;
        try {
            deviceProxy = new AudioDeviceProxy(new MockAudioDevice());

            System.out.println("Próba nagrywania bez połączenia...");
            try {
                deviceProxy.record(5);
            } catch (IllegalStateException e) {
                System.out.println("  Oczekiwany błąd: " + e.getMessage());
            }

            System.out.println("Nawiązywanie połączenia...");
            deviceProxy.connect();

            // Test nagrywania z różnymi parametrami
            System.out.println("Nagrywanie 5 sekund...");
            byte[] audio5 = deviceProxy.record(5);
            System.out.println("  Nagranie 5s: " + audio5.length + " bajtów");

            System.out.println("Nagrywanie 10 sekund...");
            byte[] audio10 = deviceProxy.record(10);
            System.out.println("  Nagranie 10s: " + audio10.length + " bajtów");

            deviceProxy.printStatistics();

            // Test podwójnego odłączenia
            deviceProxy.disconnect();
            try {
                deviceProxy.disconnect();
                System.out.println("  Podwójne odłączenie - brak błędu");
            } catch (Exception e) {
                System.out.println("  Błąd przy podwójnym odłączeniu: " + e.getMessage());
            }

        } catch (IllegalStateException e) {
            System.err.println("[PROXY-BŁĄD] Błąd stanu urządzenia: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("[PROXY-BŁĄD] Błąd urządzenia audio: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (deviceProxy != null && deviceProxy.isConnected()) {
                try {
                    deviceProxy.disconnect();
                    System.out.println("  Urządzenie zostało rozłączone w bloku finally");
                } catch (Exception e) {
                    System.err.println("  Błąd przy rozłączaniu: " + e.getMessage());
                }
            }
        }

        System.out.println("--------------------------------------------------------------------------------\n");
    }

    private static void demonstrateFlyweight() {
        System.out.println("\n----------------------------------- FLYWEIGHT -----------------------------------");

        LoggerSingleton logger = LoggerSingleton.getInstance();

        // Flyweight 1: Genre characteristics
        System.out.println("\n--- Flyweight 1: Genre Characteristics ---");
        try {
            GenreCharacteristicsFactory.printCacheContents();

            GenreCharacteristicsFlyweight rock1 = GenreCharacteristicsFactory.getGenreCharacteristics("rock");
            GenreCharacteristicsFlyweight rock2 = GenreCharacteristicsFactory.getGenreCharacteristics("rock");
            System.out.println("Rock1 i Rock2 to ta sama instancja: " + (rock1 == rock2));
            System.out.println("Cache size: " + GenreCharacteristicsFactory.getCacheSize());

            System.out.println("\n--- Flyweight 1: Genre Characteristics (z zewnętrznym stanem) ---");
            GenreCharacteristicsFlyweight rock = GenreCharacteristicsFactory.getGenreCharacteristics("rock");
            System.out.println("Podstawowa trudność dla Rock: " + rock.getBaseDifficultyLevel());

            // Test różnych poziomów umiejętności
            int[] skillLevels = {1, 3, 5, 7, 9, 10};
            for (int userSkill : skillLevels) {
                int adjustedDifficulty = rock.calculateAdjustedDifficulty(userSkill);
                System.out.println("  Umiejętność " + userSkill + " → trudność: " + adjustedDifficulty);
            }

            // Test nieprawidłowego gatunku
            try {
                GenreCharacteristicsFactory.getGenreCharacteristics("metal");
            } catch (IllegalArgumentException e) {
                System.out.println("  Oczekiwany błąd: " + e.getMessage());
            }

        } catch (IllegalArgumentException e) {
            System.err.println("[FLYWEIGHT-BŁĄD] Nieprawidłowy gatunek: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("[FLYWEIGHT-BŁĄD] Błąd w GenreCharacteristics: " + e.getMessage());
            e.printStackTrace();
        }

        // Flyweight 2: Audio effects
        System.out.println("\n--- Flyweight 2: Audio Effects ---");
        try {
            AudioEffectFactory.printAvailableEffects();

            AudioEffectFlyweight echo1 = AudioEffectFactory.getEffect("echo");
            AudioEffectFlyweight echo2 = AudioEffectFactory.getEffect("echo");
            System.out.println("Echo1 i Echo2 to ta sama instancja: " + (echo1 == echo2));
            System.out.println("Cache size: " + AudioEffectFactory.getCacheSize());

            // Test dostępu do wszystkich efektów
            String[] effects = {"echo", "reverb", "pitch_correction", "distortion", "chorus"};
            for (String effect : effects) {
                AudioEffectFlyweight effectObj = AudioEffectFactory.getEffect(effect);
                System.out.println("  " + effect + ": " + effectObj);
            }

            // Test nieprawidłowego efektu
            try {
                AudioEffectFactory.getEffect("nonexistent");
            } catch (IllegalArgumentException e) {
                System.out.println("  Oczekiwany błąd: " + e.getMessage());
            }

        } catch (IllegalArgumentException e) {
            System.err.println("[FLYWEIGHT-BŁĄD] Nieprawidłowy efekt: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("[FLYWEIGHT-BŁĄD] Błąd w AudioEffectFactory: " + e.getMessage());
            e.printStackTrace();
        }

        // Flyweight 3: Song metadata
        System.out.println("\n--- Flyweight 3: Song Metadata ---");
        try {
            SongMetadataFlyweight meta1 = SongMetadataFactory.getMetadata("English", "PG");
            SongMetadataFlyweight meta2 = SongMetadataFactory.getMetadata("English", "PG");
            System.out.println("Meta1 i Meta2 to ta sama instancja: " + (meta1 == meta2));
            System.out.println("Cache size: " + SongMetadataFactory.getCacheSize());

            // Test różnych kombinacji
            String[][] testCases = {
                    {"English", "U"}, {"English", "PG"}, {"English", "15"},
                    {"Polish", "12"}, {"Polish", "18"}, {"French", "PG"}
            };

            for (String[] testCase : testCases) {
                try {
                    SongMetadataFlyweight meta = SongMetadataFactory.getMetadata(testCase[0], testCase[1]);
                    System.out.println("  " + testCase[0] + "/" + testCase[1] + ": " + meta);
                } catch (IllegalArgumentException e) {
                    System.out.println("  " + testCase[0] + "/" + testCase[1] + ": " + e.getMessage());
                }
            }

            SongMetadataFactory.printCacheContents();

        } catch (IllegalArgumentException e) {
            System.err.println("[FLYWEIGHT-BŁĄD] Nieprawidłowe metadane: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("[FLYWEIGHT-BŁĄD] Błąd w SongMetadataFactory: " + e.getMessage());
            e.printStackTrace();
        }

        // Flyweight 4: User avatars
        System.out.println("\n--- Flyweight 4: User Avatars ---");
        try {
            UserAvatarFactory.printAvailableAvatars();

            UserAvatarFlyweight avatar1 = UserAvatarFactory.getAvatar("singer_gold");
            UserAvatarFlyweight avatar2 = UserAvatarFactory.getAvatar("singer_gold");
            System.out.println("Avatar1 i Avatar2 to ta sama instancja: " + (avatar1 == avatar2));
            System.out.println("Cache size: " + UserAvatarFactory.getCacheSize());

            // Test różnych awatarów (w tym nieistniejących - powinien zwrócić domyślny)
            String[] avatarIds = {"singer_gold", "singer_silver", "singer_bronze", "beginner_mic", "legend_crown", "nonexistent"};
            for (String avatarId : avatarIds) {
                UserAvatarFlyweight avatar = UserAvatarFactory.getAvatar(avatarId);
                System.out.println("  " + avatarId + " → " + avatar);
            }

        } catch (Exception e) {
            System.err.println("[FLYWEIGHT-BŁĄD] Błąd w UserAvatarFactory: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("--------------------------------------------------------------------------------\n");
    }

    private static class MockAudioDevice implements AudioInput {
        @Override
        public byte[] record(int durationSeconds) {
            if (durationSeconds <= 0) {
                throw new IllegalArgumentException("Czas nagrywania musi być dodatni, otrzymano: " + durationSeconds);
            }
            if (durationSeconds > 60) {
                System.err.println("[MOCK] Ostrzeżenie: długi czas nagrywania (" + durationSeconds + "s) może wpłynąć na wydajność");
            }
            return new byte[durationSeconds * 44100 * 2];
        }

        @Override
        public int getSampleRate() {
            return 44100;
        }

        @Override
        public String getDeviceInfo() {
            return "Mock Audio Device (symulowane urządzenie)";
        }
    }
}
// Koniec, Tydzień 4, Wzorce Facade, Proxy, Flyweight - z obsługą wyjątków