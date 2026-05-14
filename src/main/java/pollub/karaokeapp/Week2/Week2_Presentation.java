package pollub.karaokeapp.Week2;

import pollub.karaokeapp.Week2.builder.performance.PerformanceBuilder;
import pollub.karaokeapp.Week2.builder.playlist.PlaylistBuilder;
import pollub.karaokeapp.Week2.builder.song.SongBuilder;
import pollub.karaokeapp.Week2.builder.user.UserBuilder;
import pollub.karaokeapp.Week2.factory.performance.DuetPerformanceFactory;
import pollub.karaokeapp.Week2.factory.performance.PerformanceFactory;
import pollub.karaokeapp.Week2.factory.performance.SoloPerformanceFactory;
import pollub.karaokeapp.Week2.factory.scoring.ScoringStrategyFactory;
import pollub.karaokeapp.Week2.factory.song.SongFactory;
import pollub.karaokeapp.Week2.factory.user.UserFactory;
import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;
import pollub.karaokeapp.Week2.prototype.Prototype;
import pollub.karaokeapp.service.scoring.ScoringStrategy;
import pollub.karaokeapp.Week2.singleton.ConfigSingleton;
import pollub.karaokeapp.Week2.singleton.KaraokeManagerSingleton;
import pollub.karaokeapp.Week2.singleton.LoggerSingleton;
import pollub.karaokeapp.Week2.singleton.ScoreManagerSingleton;

import java.util.List;

public class Week2_Presentation {

    public static void week2_presentation() {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("Demonstracja wszystkich wzorców projektowych użytych w projekcie KaraokeApp");
        System.out.println("--------------------------------------------------------------------------------\n");

        System.out.println("----------------------------------- Factory -----------------------------------");

        try {
            Song song1 = SongFactory.createSong("rock", "Numb", "Linkin Park", 187, 6);
            Song song2 = SongFactory.createSong("grunge", "Lithium", "Nirvana", 256, 4);
            System.out.println("✓ Utworzono piosenki: " + song1.getTitle() + ", " + song2.getTitle());

            User user1 = UserFactory.createUser("admin", "Marek");
            User user2 = UserFactory.createUser("premium", "Paweł");
            System.out.println("✓ Utworzono użytkowników: " + user1.getNickname() + ", " + user2.getNickname());

            PerformanceFactory soloFactory = new SoloPerformanceFactory();
            Performance soloPerformance = soloFactory.createPerformance(song1, List.of(user1));
            System.out.println("✓ Utworzono występ SOLO");

            PerformanceFactory duetFactory = new DuetPerformanceFactory();
            Performance duetPerformance = duetFactory.createPerformance(song2, List.of(user1, user2));
            System.out.println("✓ Utworzono występ DUET");

            // Test nieprawidłowej liczby uczestników dla SOLO
            try {
                Performance invalidSolo = soloFactory.createPerformance(song1, List.of(user1, user2));
                System.out.println("❌ BŁĄD: Nie powinno udać się utworzyć SOLO z 2 uczestnikami");
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Poprawnie obsłużono błąd SOLO: " + e.getMessage());
            }

            // Test nieprawidłowej liczby uczestników dla DUET
            try {
                Performance invalidDuet = duetFactory.createPerformance(song2, List.of(user1));
                System.out.println("❌ BŁĄD: Nie powinno udać się utworzyć DUET z 1 uczestnikiem");
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Poprawnie obsłużono błąd DUET: " + e.getMessage());
            }

            ScoringStrategy easyStrategy = ScoringStrategyFactory.createStrategy("easy");
            ScoringStrategy proStrategy = ScoringStrategyFactory.createStrategy("pro");
            System.out.println("✓ Utworzono strategie punktowania");

            // Test nieznanej strategii
            try {
                ScoringStrategy unknownStrategy = ScoringStrategyFactory.createStrategy("impossible");
                System.out.println("❌ BŁĄD: Nie powinno udać się utworzyć nieznanej strategii");
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Poprawnie obsłużono nieznaną strategię: " + e.getMessage());
            }

            System.out.println("Wykonanie solo: " + soloPerformance);
            System.out.println("Wykonanie w duecie: " + duetPerformance);
            System.out.println("Easy Strategy score(100): " + easyStrategy.calculateScore(100));
            System.out.println("Pro Strategy score(100): " + proStrategy.calculateScore(100));

        } catch (IllegalArgumentException e) {
            System.err.println("❌ BŁĄD w sekcji Factory: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ NIEOCZEKIWANY BŁĄD w sekcji Factory: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("--------------------------------------------------------------------------------\n");

        System.out.println("----------------------------------- Builder -----------------------------------");

        try {
            Song song3 = new SongBuilder("Chory na wszystko", "Strachy na lachy")
                    .setGenre("Rock")
                    .setDuration(222)
                    .setDifficulty(2)
                    .build();
            System.out.println("✓ Utworzono piosenkę z Buildera");

            User user3 = new UserBuilder("Maciej")
                    .setLevel(3)
                    .setPoints(50)
                    .build();
            System.out.println("✓ Utworzono użytkownika z Buildera");

            Performance performanceBuilt = new PerformanceBuilder(song3)
                    .addParticipant(user3)
                    .addParticipant(new UserBuilder("Jan").build())
                    .setScore(250)
                    .build();
            System.out.println("✓ Utworzono występ z Buildera");

            Playlist playlist = new PlaylistBuilder("Składaneczka")
                    .addSong(new SongBuilder("Test", "Testowy").build())
                    .addSong(new SongBuilder("Test2", "Testowy2").build())
                    .addSong(song3)
                    .build();
            System.out.println("✓ Utworzono playlistę z Buildera");

            // Test pustej nazwy playlisty
            try {
                Playlist invalidPlaylist = new PlaylistBuilder("").build();
                System.out.println("❌ BŁĄD: Playlista z pustą nazwą powinna być nieprawidłowa");
            } catch (Exception e) {
                System.out.println("✓ Poprawnie obsłużono pustą nazwę playlisty: " + e.getMessage());
            }

            System.out.println("Wykonanie (performance) z Buildera: " + performanceBuilt);
            System.out.println("Playlista z Buildera: " + playlist);

        } catch (IllegalArgumentException e) {
            System.err.println("❌ BŁĄD w sekcji Builder: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ NIEOCZEKIWANY BŁĄD w sekcji Builder: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("--------------------------------------------------------------------------------\n");

        System.out.println("----------------------------------- Prototype – klonowanie obiektów -----------------------------------");

        try {
            // Przygotowanie obiektów do klonowania
            Song originalSong = new SongBuilder("Original", "Artist")
                    .setGenre("Pop")
                    .setDuration(180)
                    .setDifficulty(3)
                    .build();

            User originalUser = new UserBuilder("OriginalUser")
                    .setLevel(5)
                    .setPoints(100)
                    .build();

            Performance originalPerformance = new PerformanceBuilder(originalSong)
                    .addParticipant(originalUser)
                    .setScore(100)
                    .build();

            Playlist originalPlaylist = new PlaylistBuilder("OriginalPlaylist")
                    .addSong(originalSong)
                    .build();

            // Klonowanie
            Performance clonedPerformance = ((Prototype<Performance>) originalPerformance).clone();
            clonedPerformance.setScore(300);
            System.out.println("✓ Sklonowano Performance");

            Song clonedSong = ((Prototype<Song>) originalSong).clone();
            clonedSong.setDifficulty(5);
            System.out.println("✓ Sklonowano Song");

            User clonedUser = ((Prototype<User>) originalUser).clone();
            clonedUser.setNickname("Marko");
            System.out.println("✓ Sklonowano User");

            Playlist clonedPlaylist = ((Prototype<Playlist>) originalPlaylist).clone();
            clonedPlaylist.setName("Edit Składaneczka");
            System.out.println("✓ Sklonowano Playlist");

            // Test klonowania null
            try {
                Performance nullClone = ((Prototype<Performance>) null).clone();
                System.out.println("❌ BŁĄD: Klonowanie null powinno rzucić wyjątkiem");
            } catch (NullPointerException e) {
                System.out.println("✓ Poprawnie obsłużono klonowanie null: " + e.getMessage());
            }

            System.out.println("Prototype – przykłady:");
            System.out.println("Oryginalny Performance: " + originalPerformance);
            System.out.println("Sklonowany Performance: " + clonedPerformance);
            System.out.println("Oryginalny Song: " + originalSong);
            System.out.println("Sklonowany Song: " + clonedSong);
            System.out.println("Oryginalny User: " + originalUser);
            System.out.println("Sklonowany User: " + clonedUser);
            System.out.println("Oryginalna Playlista: " + originalPlaylist);
            System.out.println("Sklonowana Playlista: " + clonedPlaylist);

        } catch (NullPointerException e) {
            System.err.println("❌ BŁĄD: Próba klonowania null - " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ NIEOCZEKIWANY BŁĄD w sekcji Prototype: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("--------------------------------------------------------------------------------\n");

        System.out.println("----------------------------------- Singletony -----------------------------------");

        try {
            ConfigSingleton config = ConfigSingleton.getInstance();
            config.setConfigName("KaraokeNightConfig");
            System.out.println("✓ Pobrano ConfigSingleton");

            LoggerSingleton logger = LoggerSingleton.getInstance();
            logger.log("Rozpoczęcie sesji karaoke");
            System.out.println("✓ Pobrano LoggerSingleton");

            ScoreManagerSingleton scoreManager = ScoreManagerSingleton.getInstance();
            scoreManager.addScore(500);
            System.out.println("✓ Pobrano ScoreManagerSingleton");

            KaraokeManagerSingleton stageManager = KaraokeManagerSingleton.INSTANCE;
            stageManager.nextStage();
            System.out.println("✓ Pobrano KaraokeManagerSingleton");

            // Test wielowątkowego dostępu do ScoreManagerSingleton
            Runnable scoreAdder = () -> {
                for (int i = 0; i < 10; i++) {
                    ScoreManagerSingleton.getInstance().addScore(10);
                }
            };

            Thread t1 = new Thread(scoreAdder);
            Thread t2 = new Thread(scoreAdder);
            t1.start();
            t2.start();
            t1.join();
            t2.join();

            System.out.println("✓ Test wielowątkowy zakończony pomyślnie (wynik: " + scoreManager.getTotalScore() + ")");

            System.out.println("Config name: " + config.getConfigName());
            logger.log("Aktualny Stage: " + stageManager.getCurrentStage());
            System.out.println("Total score: " + scoreManager.getTotalScore());

        } catch (InterruptedException e) {
            System.err.println("❌ BŁĄD w testach wielowątkowych: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.err.println("❌ NIEOCZEKIWANY BŁĄD w sekcji Singleton: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------------------------------------------------\n");

        try {
            karaokeSimulation();
        } catch (Exception e) {
            System.err.println("❌ BŁĄD podczas symulacji karaoke: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void karaokeSimulation() {
        System.out.println("------ Dodatkowo: symulacja łączonego działania wzorców w karaoke ------");

        // Singletony
        LoggerSingleton logger = LoggerSingleton.getInstance();
        ScoreManagerSingleton scoreManager = ScoreManagerSingleton.getInstance();
        ConfigSingleton config = ConfigSingleton.getInstance();

        try {
            config.setConfigName("Crazy Night Karaoke");
            logger.log("Start wydarzenia: " + config.getConfigName());

            // Builder – Tworzenie playlisty
            Song song1 = new SongBuilder("Espresso", "Sabrina Carpenter")
                    .setGenre("Pop")
                    .setDuration(175)
                    .setDifficulty(2)
                    .build();

            Song song2 = new SongBuilder("Tamagotchi", "Taconafide")
                    .setGenre("Rap")
                    .setDuration(202)
                    .setDifficulty(5)
                    .build();

            Playlist playlist = new PlaylistBuilder("CrazyNightPlaylist")
                    .addSong(song1)
                    .addSong(song2)
                    .build();

            logger.log("Utworzono playlistę: " + playlist);

            // Factory – Tworzenie użytkowników
            User user1 = UserFactory.createUser("standard", "Marko");
            User user2 = UserFactory.createUser("premium", "Pablo");

            // Test nieprawidłowego typu użytkownika
            try {
                User invalidUser = UserFactory.createUser("superuser", "Test");
                System.out.println("❌ BŁĄD: Nie powinno udać się utworzyć nieznanego typu użytkownika");
            } catch (IllegalArgumentException e) {
                logger.log("✓ Poprawnie obsłużono nieznany typ użytkownika: " + e.getMessage());
            }

            logger.log("Zarejestrowano użytkowników: " + user1 + ", " + user2);

            // Factory – Tworzenie wystąpień
            PerformanceFactory soloFactory = new SoloPerformanceFactory();
            Performance soloPerformance = soloFactory.createPerformance(song1, List.of(user1));

            PerformanceFactory duetFactory = new DuetPerformanceFactory();
            Performance duetPerformance = duetFactory.createPerformance(song2, List.of(user1, user2));

            logger.log("Utworzono występy SOLO i DUET");

            // Strategy – Liczenie punktów
            ScoringStrategy strategy = ScoringStrategyFactory.createStrategy("pro");

            int soloScore = strategy.calculateScore(85);
            int duetScore = strategy.calculateScore(92);

            // Test strategii z ujemną wartością
            try {
                int negativeScore = strategy.calculateScore(-50);
                System.out.println("❌ BŁĄD: Strategia nie powinna akceptować ujemnych wartości");
            } catch (IllegalArgumentException e) {
                logger.log("✓ Poprawnie obsłużono ujemną wartość punktów: " + e.getMessage());
            }

            soloPerformance.setScore(soloScore);
            duetPerformance.setScore(duetScore);

            logger.log("Obliczono punkty SOLO: " + soloScore);
            logger.log("Obliczono punkty DUET: " + duetScore);

            scoreManager.addScore(soloScore);
            scoreManager.addScore(duetScore);

            // Prototype – Kopia zapasowa występów
            Performance soloBackup = soloPerformance.clone();
            Performance duetBackup = duetPerformance.clone();

            logger.log("Wykonano backup występów (Prototype)");

            // Test modyfikacji backupu
            soloBackup.setScore(999);
            logger.log("Zmodyfikowano backup - oryginał pozostał niezmieniony: " +
                    "Oryginał=" + soloPerformance.getScore() + ", Backup=" + soloBackup.getScore());

            // Podsumowanie
            System.out.println("\n--------------------------------------------------------------------------------");
            System.out.println("--- PODSUMOWANIE WIECZORU ---");
            System.out.println("Playlist: " + playlist);
            System.out.println("Solo Performance: " + soloPerformance);
            System.out.println("Duet Performance: " + duetPerformance);
            System.out.println("Backup SOLO: " + soloBackup);
            System.out.println("Backup DUET: " + duetBackup);
            System.out.println("Łączna suma punktów: " + scoreManager.getTotalScore());
            System.out.println("--------------------------------------------------------------------------------\n");

            logger.log("Zakończenie wydarzenia.");

        } catch (IllegalArgumentException e) {
            System.err.println("❌ BŁĄD walidacji w symulacji: " + e.getMessage());
            logger.log("BŁĄD: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ NIEOCZEKIWANY BŁĄD w symulacji: " + e.getMessage());
            logger.log("NIEOCZEKIWANY BŁĄD: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("---------------------------------------------------------------------------------------------------------------");
            System.out.println("---------------------------------------------------------------------------------------------------------------\n");
        }
    }
}