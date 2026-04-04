package pollub.karaokeapp.Week6;

import pollub.karaokeapp.Week2.builder.song.SongBuilder;
import pollub.karaokeapp.Week2.builder.user.UserBuilder;
import pollub.karaokeapp.Week2.singleton.LoggerSingleton;
import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;

import pollub.karaokeapp.Week6.observer.*;
import pollub.karaokeapp.Week6.state.*;
import pollub.karaokeapp.Week6.strategy.*;
import pollub.karaokeapp.Week6.template.*;
import pollub.karaokeapp.Week6.visitor.*;

import java.util.ArrayList;
import java.util.List;

public class Week6_Presentation {

    public static void week6_presentation() {
        LoggerSingleton logger = LoggerSingleton.getInstance();
        logger.log("Rozpoczęcie prezentacji wzorców z Tygodnia 6");

        System.out.println("\n=================================================================================");
        System.out.println("PREZENTACJA NOWYCH WZORCÓW PROJEKTOWYCH - TYDZIEŃ 6");
        System.out.println("Observer, State, Strategy, Template Method, Visitor");
        System.out.println("=================================================================================\n");

        demonstrateObserver();
        demonstrateState();
        demonstrateStrategy();
        demonstrateTemplate();
        demonstrateVisitor();

        logger.log("Zakończenie prezentacji Tygodnia 6");
    }

    // =========================================================================
    // OBSERVER
    // =========================================================================
    private static void demonstrateObserver() {
        System.out.println("\n----------------------------------- OBSERVER -----------------------------------");

        // Observer 1: SongEventPublisher z ScoringObserver
        System.out.println("\n--- Observer 1: System oceniania reaguje na zmiany piosenki ---");
        Song song1 = new SongBuilder("Numb", "Linkin Park")
                .setGenre("Rock").setDuration(187).setDifficulty(6).build();

        SongEventPublisher publisher1 = new SongEventPublisher(song1);
        ScoringObserver scoringObserver = new ScoringObserver();
        publisher1.subscribe(scoringObserver);

        publisher1.changeSongDifficulty(8);
        publisher1.changeSongTitle("Numb (Remix)");

        // Observer 2: Wielokrotni obserwatorzy
        System.out.println("\n--- Observer 2: Wielokrotni obserwatorzy ---");
        Song song2 = new SongBuilder("Bohemian Rhapsody", "Queen")
                .setGenre("Rock").setDuration(354).setDifficulty(9).build();

        SongEventPublisher publisher2 = new SongEventPublisher(song2);
        ScoringObserver scoringObs2 = new ScoringObserver();
        AnalyticsObserver analyticsObs = new AnalyticsObserver();

        publisher2.subscribe(scoringObs2);
        publisher2.subscribe(analyticsObs);

        publisher2.changeSongDifficulty(10);
        publisher2.changeSongTitle("Bohemian Rhapsody (Extended)");

        System.out.println("\nAktualne zdarzenia w systemie analityki: " + analyticsObs.getEventCount());

        // Observer 3: PlaylistObserver
        System.out.println("\n--- Observer 3: Manager playlisty reaguje na zmiany ---");
        Song song3 = new SongBuilder("Stairway to Heaven", "Led Zeppelin")
                .setGenre("Rock").setDuration(482).setDifficulty(8).build();

        SongEventPublisher publisher3 = new SongEventPublisher(song3);
        PlaylistObserver playlistObs = new PlaylistObserver();
        publisher3.subscribe(playlistObs);

        publisher3.changeSongDifficulty(7);

        // Wszyscy obserwatorzy razem
        System.out.println("\n--- Observer 1-3: Wszyscy obserwatorzy monitorują zmiany ---");
        Song song4 = new SongBuilder("Hotel California", "Eagles")
                .setGenre("Rock").setDuration(391).setDifficulty(7).build();

        SongEventPublisher publisher4 = new SongEventPublisher(song4);
        publisher4.subscribe(new ScoringObserver());
        publisher4.subscribe(new AnalyticsObserver());
        publisher4.subscribe(new PlaylistObserver());

        publisher4.changeSongTitle("Hotel California (Live)");
        publisher4.changeSongDifficulty(6);

        // Observer 4: NotificationObserver
        System.out.println("\n--- Observer 4: System powiadomień reaguje na zmiany ---");
        Song song5 = new SongBuilder("Shape of You", "Ed Sheeran")
                .setGenre("Pop").setDuration(233).setDifficulty(4).build();
        User user1 = new UserBuilder("Anna").setLevel(6).build();
        User user2 = new UserBuilder("Tomek").setLevel(5).build();
        List<User> subscribers = List.of(user1, user2);

        SongEventPublisher publisher5 = new SongEventPublisher(song5);
        NotificationObserver notificationObs = new NotificationObserver(subscribers);
        publisher5.subscribe(notificationObs);

        publisher5.changeSongTitle("Shape of You (Acoustic)");
        publisher5.changeSongDifficulty(5);

        System.out.println("--------------------------------------------------------------------------------\n");
    }

    // =========================================================================
    // STATE
    // =========================================================================
    private static void demonstrateState() {
        System.out.println("\n----------------------------------- STATE -----------------------------------");

        Song song = new SongBuilder("Imagine", "John Lennon")
                .setGenre("Pop").setDuration(183).setDifficulty(5).build();
        User user = new UserBuilder("Józek").setLevel(7).build();

        // State 1: Przejścia stanów
        System.out.println("\n--- State 1: Przejścia stanów Playing -> Paused -> Stopped ---");
        Performance performance1 = new Performance(song, List.of(user), 250);
        performance1.setState(new PlayingPerformanceState());

        System.out.println("Stan: " + performance1.getState().getStateName());
        performance1.playPerformance();
        performance1.pausePerformance();
        System.out.println("Stan: " + performance1.getState().getStateName());
        performance1.playPerformance();
        performance1.stopPerformance();

        // State 2: Buffering state
        System.out.println("\n--- State 2: Stan Buffering (ładowanie piosenki) ---");
        Performance performance2 = new Performance(song, List.of(user), 200);
        performance2.setState(new BufferingPerformanceState());

        System.out.println("Stan: " + performance2.getState().getStateName());
        performance2.playPerformance();
        performance2.pausePerformance();
        performance2.stopPerformance();

        System.out.println("\n--- State 3: Pełny cykl wykonania ---");
        Performance performance3 = new Performance(song, List.of(user), 300);
        performance3.setState(new PlayingPerformanceState());

        System.out.println("Stan: " + performance3.getState().getStateName());
        performance3.pausePerformance();
        System.out.println("Stan: " + performance3.getState().getStateName());
        performance3.playPerformance();
        System.out.println("Stan: " + performance3.getState().getStateName());

        // State 4: Próba wykonania niepoprawnych operacji
        System.out.println("\n--- State 4: Obsługa błędnych przejść ---");
        Performance performance4 = new Performance(song, List.of(user), 150);
        performance4.setState(new StoppedPerformanceState());

        System.out.println("Stan: " + performance4.getState().getStateName());
        performance4.pausePerformance();
        performance4.stopPerformance();
        performance4.playPerformance();

        System.out.println("--------------------------------------------------------------------------------\n");
    }

    // =========================================================================
    // STRATEGY
    // =========================================================================
    private static void demonstrateStrategy() {
        System.out.println("\n----------------------------------- STRATEGY -----------------------------------");

        Playlist playlist = new Playlist("Rock Party");
        Song s1 = new SongBuilder("The Lazy Song", "Bruno Mars").setGenre("Pop").setDuration(180).setDifficulty(2).build();
        Song s2 = new SongBuilder("American Idiot", "Green Day").setGenre("Rock").setDuration(200).setDifficulty(5).build();
        Song s3 = new SongBuilder("Fear of the Dark", "Iron Maiden").setGenre("Hard Rock").setDuration(220).setDifficulty(9).build();
        playlist.addSong(s1);
        playlist.addSong(s2);
        playlist.addSong(s3);

        // Strategy 1: Sortowanie po trudności
        System.out.println("\n--- Strategy 1: Sortowanie po trudności (rosnąco) ---");
        PlaylistOrganizer organizer = new PlaylistOrganizer(new SortByDifficultyStrategy(true));
        List<?> sorted = organizer.organize(playlist);
        sorted.forEach(s -> System.out.println("  " + ((Song)s).getTitle() + " - Trudność: " + ((Song)s).getDifficulty()));

        // Strategy 2: Losowe odtwarzanie
        System.out.println("\n--- Strategy 2: Losowe odtwarzanie ---");
        organizer.setStrategy(new RandomPlaylistStrategy());
        List<?> random = organizer.organize(playlist);
        random.forEach(s -> System.out.println("  " + ((Song)s).getTitle()));

        // Strategy 3: Sortowanie malejące po trudności
        System.out.println("\n--- Strategy 3: Sortowanie po trudności (malejąco) ---");
        organizer.setStrategy(new SortByDifficultyStrategy(false));
        List<?> sortedDesc = organizer.organize(playlist);
        sortedDesc.forEach(s -> System.out.println("  " + ((Song)s).getTitle() + " - Trudność: " + ((Song)s).getDifficulty()));

        // Strategy 4: Sortowanie po czasie trwania
        System.out.println("\n--- Strategy 4: Sortowanie po czasie trwania (od najkrótszych) ---");
        organizer.setStrategy(new SortByDurationStrategy(true));
        List<?> byDuration = organizer.organize(playlist);
        byDuration.forEach(s -> System.out.println("  " + ((Song)s).getTitle() + " - Czas: " + ((Song)s).getDuration() + "s"));

        // Strategy 5: Sortowanie po tytule
        System.out.println("\n--- Strategy 5: Sortowanie po tytule (alfabetycznie A-Z) ---");
        organizer.setStrategy(new SortByTitleStrategy(true));
        List<?> byTitle = organizer.organize(playlist);
        byTitle.forEach(s -> System.out.println("  " + ((Song)s).getTitle() + " - Artysta: " + ((Song)s).getArtist()));

        System.out.println("--------------------------------------------------------------------------------\n");
    }

    // =========================================================================
    // TEMPLATE METHOD
    // =========================================================================
    private static void demonstrateTemplate() {
        System.out.println("\n----------------------------------- TEMPLATE METHOD -----------------------------------");

        Song song = new SongBuilder("Anthem", "Blink-182")
                .setGenre("Rock").setDuration(240).setDifficulty(7).build();
        User user1 = new UserBuilder("Marko").setLevel(8).build();
        User user2 = new UserBuilder("Pablo").setLevel(7).build();

        Performance performance = new Performance(song, List.of(user1, user2), 320);

        // Template 1: Pro evaluator
        System.out.println("\n--- Template 1: Pro Performance Evaluator ---");
        PerformanceTemplate proEval = new ProPerformanceEvaluator();
        int proScore = proEval.evaluatePerformance(performance);
        System.out.println("Wynik Pro: " + proScore);

        // Template 2: Easy evaluator
        System.out.println("\n--- Template 2: Easy Performance Evaluator ---");
        PerformanceTemplate easyEval = new EasyPerformanceEvaluator();
        int easyScore = easyEval.evaluatePerformance(performance);
        System.out.println("Wynik Easy: " + easyScore);

        // Template 3: Legacy evaluator
        System.out.println("\n--- Template 3: Legacy Performance Evaluator ---");
        PerformanceTemplate legacyEval = new LegacyPerformanceEvaluator();
        int legacyScore = legacyEval.evaluatePerformance(performance);
        System.out.println("Wynik Legacy: " + legacyScore);

        // Template 4: Audience evaluator
        System.out.println("\n--- Template 4: Audience Performance Evaluator ---");
        PerformanceTemplate audienceEval = new AudiencePerformanceEvaluator();
        int audienceScore = audienceEval.evaluatePerformance(performance);
        System.out.println("Wynik Audience: " + audienceScore);

        System.out.println("--------------------------------------------------------------------------------\n");
    }

    // =========================================================================
    // VISITOR
    // =========================================================================
    private static void demonstrateVisitor() {
        System.out.println("\n----------------------------------- VISITOR -----------------------------------");

        // Przygotowanie danych
        Song song1 = new SongBuilder("Wires", "The Neighbourhood").setGenre("Rock").setDuration(180).setDifficulty(3).build();
        Song song2 = new SongBuilder("Casablanca", "Sentino").setGenre("Rap").setDuration(200).setDifficulty(7).build();
        User user1 = new UserBuilder("Maćko").setLevel(5).build();
        User user2 = new UserBuilder("Grześko").setLevel(8).build();
        Performance perf = new Performance(song1, List.of(user1), 250);
        Playlist playlist = new Playlist("Niiice Playlist");
        playlist.addSong(song1);
        playlist.addSong(song2);

        // Visitor 1: Statistics
        System.out.println("\n--- Visitor 1: Zbieranie statystyk ---");
        StatisticsVisitor statsVisitor = new StatisticsVisitor();
        song1.accept(statsVisitor);
        song2.accept(statsVisitor);
        user1.accept(statsVisitor);
        user2.accept(statsVisitor);
        perf.accept(statsVisitor);
        playlist.accept(statsVisitor);
        statsVisitor.printStatistics();

        // Visitor 2: Validation
        System.out.println("\n--- Visitor 2: Walidacja danych ---");
        ValidationVisitor validationVisitor = new ValidationVisitor();
        song1.accept(validationVisitor);
        user1.accept(validationVisitor);
        perf.accept(validationVisitor);
        playlist.accept(validationVisitor);
        System.out.println("Znalezione błędy: " + validationVisitor.getErrorCount());

        // Visitor 3: Export
        System.out.println("\n--- Visitor 3: Eksport danych ---");
        ExportVisitor exportVisitor = new ExportVisitor();
        song1.accept(exportVisitor);
        user1.accept(exportVisitor);
        perf.accept(exportVisitor);
        playlist.accept(exportVisitor);
        exportVisitor.saveToFile("karaoke_export.csv");

        // Visitor 4: Performance Optimization
        System.out.println("\n--- Visitor 4: Optymalizacja wydajności ---");
        PerformanceOptimizationVisitor optimizationVisitor = new PerformanceOptimizationVisitor();
        song1.accept(optimizationVisitor);
        song2.accept(optimizationVisitor);
        user1.accept(optimizationVisitor);
        user2.accept(optimizationVisitor);
        perf.accept(optimizationVisitor);
        playlist.accept(optimizationVisitor);
        System.out.println("Zastosowanych optymalizacji: " + optimizationVisitor.getOptimizationsApplied());

        System.out.println("--------------------------------------------------------------------------------\n");
    }
}
