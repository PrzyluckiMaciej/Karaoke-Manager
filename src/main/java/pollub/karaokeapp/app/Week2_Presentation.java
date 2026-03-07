package pollub.karaokeapp.app;

import pollub.karaokeapp.builder.performance.PerformanceBuilder;
import pollub.karaokeapp.builder.playlist.PlaylistBuilder;
import pollub.karaokeapp.builder.song.SongBuilder;
import pollub.karaokeapp.builder.user.UserBuilder;
import pollub.karaokeapp.factory.performance.DuetPerformanceFactory;
import pollub.karaokeapp.factory.performance.PerformanceFactory;
import pollub.karaokeapp.factory.performance.SoloPerformanceFactory;
import pollub.karaokeapp.factory.scoring.ScoringStrategyFactory;
import pollub.karaokeapp.factory.song.SongFactory;
import pollub.karaokeapp.factory.user.UserFactory;
import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;
import pollub.karaokeapp.prototype.Prototype;
import pollub.karaokeapp.service.scoring.ScoringStrategy;
import pollub.karaokeapp.singleton.ConfigSingleton;
import pollub.karaokeapp.singleton.KaraokeManagerSingleton;
import pollub.karaokeapp.singleton.LoggerSingleton;
import pollub.karaokeapp.singleton.ScoreManagerSingleton;

import java.util.List;

public class Week2_Presentation {

    public static void week2_presentation() {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("Demonstracja wszystkich wzorców projektowych użytych w projekcie KaraokeApp");
        System.out.println("--------------------------------------------------------------------------------\n");

        System.out.println("----------------------------------- Factory -----------------------------------");

        Song song1 = SongFactory.createSong("rock", "Numb", "Linkin Park", 187, 6);
        Song song2 = SongFactory.createSong("grunge", "Lithium", "Nirvana", 256, 4);

        User user1 = UserFactory.createUser("admin", "Marek");
        User user2 = UserFactory.createUser("premium", "Paweł");

        PerformanceFactory soloFactory = new SoloPerformanceFactory();
        Performance soloPerformance = soloFactory.createPerformance(song1, List.of(user1));

        PerformanceFactory duetFactory = new DuetPerformanceFactory();
        Performance duetPerformance = duetFactory.createPerformance(song2, List.of(user1, user2));

        ScoringStrategy easyStrategy = ScoringStrategyFactory.createStrategy("easy");
        ScoringStrategy proStrategy = ScoringStrategyFactory.createStrategy("pro");

        System.out.println("Wykonanie solo: " + soloPerformance);
        System.out.println("Wykonanie w duecie: " + duetPerformance);
        System.out.println("Easy Strategy score(100): " + easyStrategy.calculateScore(100));
        System.out.println("Pro Strategy score(100): " + proStrategy.calculateScore(100));
        System.out.println("--------------------------------------------------------------------------------\n");

        System.out.println("----------------------------------- Builder -----------------------------------");

        Song song3 = new SongBuilder("Chory na wszystko", "Strachy na lachy")
                .setGenre("Rock")
                .setDuration(222)
                .setDifficulty(2)
                .build();

        User user3 = new UserBuilder("Maciej")
                .setLevel(3)
                .setPoints(50)
                .build();

        Performance performanceBuilt = new PerformanceBuilder(song3)
                .addParticipant(user2)
                .addParticipant(user3)
                .setScore(250)
                .build();

        Playlist playlist = new PlaylistBuilder("Składaneczka")
                .addSong(song1)
                .addSong(song2)
                .addSong(song3)
                .build();

        System.out.println("Wykonanie (performance) z Buildera: " + performanceBuilt);
        System.out.println("Playlista z Buildera: " + playlist);
        System.out.println("--------------------------------------------------------------------------------\n");

        System.out.println("----------------------------------- Prototype – klonowanie obiektów -----------------------------------");
        try {
            Performance clonedPerformance = ((Prototype<Performance>) performanceBuilt).clone();
            clonedPerformance.setScore(300);

            Song clonedSong = ((Prototype<Song>) song3).clone();
            clonedSong.setDifficulty(5);

            User clonedUser = ((Prototype<User>) user1).clone();
            clonedUser.setNickname("Marko");

            Playlist clonedPlaylist = ((Prototype<Playlist>) playlist).clone();
            clonedPlaylist.setName("Edit Składaneczka");

            System.out.println("Prototype – przykłady:");
            System.out.println("Oryginalny Performance: " + performanceBuilt);
            System.out.println("Sklonowany Performance: " + clonedPerformance);
            System.out.println("Oryginalny Song: " + song3);
            System.out.println("Sklonowany Song: " + clonedSong);
            System.out.println("Oryginalny User: " + user1);
            System.out.println("Sklonowany User: " + clonedUser);
            System.out.println("Oryginalna Playlista: " + playlist);
            System.out.println("Sklonowana Playlista: " + clonedPlaylist);
            System.out.println("--------------------------------------------------------------------------------\n");
        } catch (Exception e) {
            e.getMessage();
            System.out.println("--------------------------------------------------------------------------------\n");
        }

        System.out.println("----------------------------------- Singletony -----------------------------------");

        ConfigSingleton config = ConfigSingleton.getInstance();
        config.setConfigName("KaraokeNightConfig");

        LoggerSingleton logger = LoggerSingleton.getInstance();
        logger.log("Rozpoczęcie sesji karaoke");

        ScoreManagerSingleton scoreManager = ScoreManagerSingleton.getInstance();
        scoreManager.addScore(500);

        KaraokeManagerSingleton stageManager = KaraokeManagerSingleton.INSTANCE;
        stageManager.nextStage();

        System.out.println("Config name: " + config.getConfigName());
        logger.log("Aktualny Stage: " + stageManager.getCurrentStage());
        System.out.println("Total score: " + scoreManager.getTotalScore());
        System.out.println();

        System.out.println("---------------------------------------------------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------------------------------------------------\n");

        karaokeSimulation();
    }

    private static void karaokeSimulation() {

        System.out.println("------ Dodatkowo: symulacja łączonego działania wzorców w karaoke ------");

        // Singletony
        LoggerSingleton logger = LoggerSingleton.getInstance();
        ScoreManagerSingleton scoreManager = ScoreManagerSingleton.getInstance();
        ConfigSingleton config = ConfigSingleton.getInstance();

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

        System.out.println("---------------------------------------------------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------------------------------------------------\n");
    }
}
