package pollub.karaokeapp.Week5;

import pollub.karaokeapp.Week2.builder.song.SongBuilder;
import pollub.karaokeapp.Week2.builder.user.UserBuilder;
import pollub.karaokeapp.Week2.factory.song.SongFactory;
import pollub.karaokeapp.Week2.factory.user.UserFactory;
import pollub.karaokeapp.Week2.singleton.LoggerSingleton;
import pollub.karaokeapp.Week5.interpreter.filter.logic.AndFilterExpression;
import pollub.karaokeapp.Week5.interpreter.filter.logic.NotFilterExpression;
import pollub.karaokeapp.Week5.interpreter.filter.logic.OrFilterExpression;
import pollub.karaokeapp.Week5.interpreter.playlist.*;
import pollub.karaokeapp.Week5.iterator.KaraokeIteratorException;
import pollub.karaokeapp.Week5.memento.EmptyHistoryException;
import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;

import pollub.karaokeapp.Week5.command.*;
import pollub.karaokeapp.Week5.command.song.*;
import pollub.karaokeapp.Week5.command.playlist.*;
import pollub.karaokeapp.Week5.command.performance.*;
import pollub.karaokeapp.Week5.command.user.*;

import pollub.karaokeapp.Week5.interpreter.filter.*;
import pollub.karaokeapp.Week5.interpreter.scoring.*;

import pollub.karaokeapp.Week5.iterator.playlist.*;
import pollub.karaokeapp.Week5.iterator.performance.*;
import pollub.karaokeapp.Week5.iterator.user.*;
import pollub.karaokeapp.Week5.iterator.song.*;

import pollub.karaokeapp.Week5.mediator.*;

import pollub.karaokeapp.Week5.memento.song.*;
import pollub.karaokeapp.Week5.memento.performance.*;
import pollub.karaokeapp.Week5.memento.playlist.*;
import pollub.karaokeapp.Week5.memento.user.*;

import java.util.ArrayList;
import java.util.List;

public class Week5_Presentation {

    public static void week5_presentation() {
        LoggerSingleton logger = LoggerSingleton.getInstance();
        logger.log("Rozpoczęcie prezentacji wzorców z Tygodnia 5");

        System.out.println("\n================================================================================");
        System.out.println("PREZENTACJA NOWYCH WZORCÓW PROJEKTOWYCH - TYDZIEŃ 5");
        System.out.println("Command, Interpreter, Iterator, Mediator, Memento");
        System.out.println("================================================================================\n");

        demonstrateCommand();
        demonstrateInterpreter();
        demonstrateSmartPlaylistInterpreter();
        demonstrateIterator();
        demonstrateMediator();
        demonstrateMemento();

        logger.log("Zakończenie prezentacji Tygodnia 5");
    }

    // =========================================================================
    // COMMAND
    // =========================================================================
    private static void demonstrateCommand() {
        System.out.println("\n----------------------------------- COMMAND -----------------------------------");

        KaraokeCommandInvoker invoker = new KaraokeCommandInvoker();

        // Command 1: Komendy piosenki przez Receiver SongEditor
        System.out.println("\n--- Command 1: Komendy edycji piosenki (Receiver: SongEditor) ---");
        Song song = new SongBuilder("Numb", "Linkin Park")
                .setGenre("Rock").setDuration(187).setDifficulty(6).build();

        SongEditor songEditor = new SongEditor(song);
        System.out.println("Stan początkowy: " + song);

        try {
            invoker.execute(new ChangeSongTitleCommand(songEditor, "Numb (Encore)"));
            System.out.println("Po zmianie tytułu:    " + song);

            invoker.execute(new ChangeSongDifficultyCommand(songEditor, 8));
            System.out.println("Po zmianie trudności: " + song);

            invoker.undo();
            System.out.println("Po cofnięciu (undo):  " + song);

            invoker.redo();
            System.out.println("Po ponowieniu (redo): " + song);
        } catch (IllegalArgumentException e) {
            System.err.println("Błąd walidacji: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.err.println("Błąd stanu: " + e.getMessage());
        }

        // Command 2: Komendy playlisty przez Receiver PlaylistManager
        System.out.println("\n--- Command 2: Komendy zarządzania playlistą (Receiver: PlaylistManager) ---");
        Playlist playlist = new Playlist("Rock Classics");
        Song song2 = SongFactory.createSong("grunge", "Lithium", "Nirvana", 256, 4);
        Song song3 = SongFactory.createSong("pop", "Espresso", "Sabrina Carpenter", 175, 2);

        PlaylistManager playlistManager = new PlaylistManager(playlist);

        try {
            invoker.execute(new AddSongToPlaylistCommand(playlistManager, song));
            invoker.execute(new AddSongToPlaylistCommand(playlistManager, song2));
            invoker.execute(new AddSongToPlaylistCommand(playlistManager, song3));
            System.out.println("Playlista po dodaniu 3 piosenek: " + playlist.getSongs().size() + " piosenek");

            invoker.execute(new RemoveSongFromPlaylistCommand(playlistManager, song3));
            System.out.println("Po usunięciu Espresso:           " + playlist.getSongs().size() + " piosenki");

            invoker.undo();
            System.out.println("Po cofnięciu usunięcia (undo):   " + playlist.getSongs().size() + " piosenek");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Błąd operacji na playliście: " + e.getMessage());
        }

        // Command 3: Wynik wykonania i makrokomenda przez Receiver PerformanceJudge
        System.out.println("\n--- Command 3: Komenda wyniku i makrokomenda (Receiver: PerformanceJudge) ---");
        User user = UserFactory.createUser("premium", "Marek");
        Performance performance = new Performance(song, List.of(user), 0);
        PerformanceJudge judge = new PerformanceJudge(performance);

        MacroCommand macro = new MacroCommand("Przygotuj Numer Otwarcia");
        macro.addCommand(new ChangeSongTitleCommand(songEditor, "Numb (Opening Night)"));
        macro.addCommand(new ChangeSongDifficultyCommand(songEditor, 4));
        macro.addCommand(new SetPerformanceScoreCommand(judge, 95));

        try {
            invoker.execute(macro);
            System.out.println("Po makrokomendzie: " + song + " | wynik: " + performance.getScore());

            invoker.undo();
            System.out.println("Po cofnięciu makra: " + song + " | wynik: " + performance.getScore());
        } catch (IllegalArgumentException e) {
            System.err.println("Błąd makrokomendy: " + e.getMessage());
        }

        System.out.println("\nHistoria invoker'a (rozmiar): " + invoker.getHistorySize());
        invoker.printHistory();

        // Command 4: Komendy edycji profilu użytkownika przez Receiver UserProfileEditor
        System.out.println("\n--- Command 4: Edycja profilu użytkownika (Receiver: UserProfileEditor) ---");
        User profileUser = UserFactory.createUser("standard", "Anonimowy");
        UserProfileEditor profileEditor = new UserProfileEditor(profileUser);
        System.out.println("Stan początkowy: " + profileUser);

        try {
            invoker.execute(new ChangeUserNicknameCommand(profileEditor, "RockStar99"));
            System.out.println("Po zmianie nicku:    " + profileUser);

            invoker.execute(new ChangeUserLevelCommand(profileEditor, 5));
            System.out.println("Po zmianie poziomu:  " + profileUser);

            invoker.execute(new AwardPointsCommand(profileEditor, 300, "Pierwsze wykonanie"));
            invoker.execute(new AwardPointsCommand(profileEditor, 150, "Bonus weekendowy"));
            System.out.println("Po przyznaniu punktów: " + profileUser);

            invoker.undo();
            System.out.println("Po cofnięciu bonusu: " + profileUser);

            invoker.undo();
            System.out.println("Po cofnięciu nagrody: " + profileUser);

            invoker.undo();
            invoker.undo();
            System.out.println("Po cofnięciu wszystkiego: " + profileUser);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Błąd edycji profilu: " + e.getMessage());
        }

        // Test obsługi błędnych komend
        System.out.println("\n--- Test obsługi błędów Command ---");
        try {
            invoker.execute(null);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Poprawnie obsłużono null komendę: " + e.getMessage());
        }

        try {
            // Próba cofnięcia gdy nie ma historii - Invoker rzuci wyjątkiem
            KaraokeCommandInvoker emptyInvoker = new KaraokeCommandInvoker();
            emptyInvoker.undo();
        } catch (IllegalStateException e) {
            System.out.println("✓ Poprawnie obsłużono brak komend do cofnięcia: " + e.getMessage());
        }

        System.out.println("\n----------------------------------------------------------------------------------------------");
    }

    // =========================================================================
    // INTERPRETER
    // =========================================================================
    private static void demonstrateInterpreter() {
        System.out.println("\n----------------------------------- INTERPRETER -----------------------------------");

        List<Song> songs = new ArrayList<>();
        songs.add(new SongBuilder("Numb", "Linkin Park").setGenre("Rock").setDuration(187).setDifficulty(6).build());
        songs.add(new SongBuilder("Lithium", "Nirvana").setGenre("Grunge").setDuration(256).setDifficulty(4).build());
        songs.add(new SongBuilder("Espresso", "Sabrina Carpenter").setGenre("Pop").setDuration(175).setDifficulty(2).build());
        songs.add(new SongBuilder("In Bloom", "Nirvana").setGenre("Grunge").setDuration(255).setDifficulty(5).build());
        songs.add(new SongBuilder("Master of Puppets", "Metallica").setGenre("Rock").setDuration(515).setDifficulty(9).build());
        songs.add(new SongBuilder("Shake It Off", "Taylor Swift").setGenre("Pop").setDuration(219).setDifficulty(3).build());

        // Interpreter 1: Wyrażenia terminalne
        System.out.println("\n--- Interpreter 1: Terminale – proste filtry ---");

        try {
            SongFilterExpression rockFilter = new GenreFilterExpression("Rock");
            SongFilterExpression easyFilter = new DifficultyRangeExpression(1, 4);
            SongFilterExpression nirFilter = new ArtistFilterExpression("Nirvana");

            System.out.println("Wyrażenie: " + rockFilter.getExpressionDescription());
            songs.stream().filter(rockFilter::interpret)
                    .forEach(s -> System.out.println("  ✓ " + s.getTitle() + " [" + s.getGenre() + "]"));

            System.out.println("Wyrażenie: " + easyFilter.getExpressionDescription());
            songs.stream().filter(easyFilter::interpret)
                    .forEach(s -> System.out.println("  ✓ " + s.getTitle() + " (trudność " + s.getDifficulty() + ")"));
        } catch (IllegalArgumentException e) {
            System.err.println("Błąd tworzenia filtru: " + e.getMessage());
        }

        // Test obsługi błędnych danych wejściowych w filtrach
        System.out.println("\n--- Test obsługi błędów Interpreter (filtry) ---");
        try {
            new GenreFilterExpression("");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Pusty gatunek: " + e.getMessage());
        }

        try {
            new ArtistFilterExpression(null);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Null artysta: " + e.getMessage());
        }

        try {
            new DifficultyRangeExpression(8, 3);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Niepoprawny zakres trudności: " + e.getMessage());
        }

        try {
            new DifficultyRangeExpression(0, 5);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Trudność poniżej minimum: " + e.getMessage());
        }

        // Interpreter 2: Wyrażenia nieterminalne
        System.out.println("\n--- Interpreter 2: Nieterminale – złożone wyrażenia logiczne ---");

        try {
            SongFilterExpression grungeMedium = new AndFilterExpression(
                    new GenreFilterExpression("Grunge"),
                    new DifficultyRangeExpression(4, 6)
            );
            System.out.println("Wyrażenie: " + grungeMedium.getExpressionDescription());
            songs.stream().filter(grungeMedium::interpret)
                    .forEach(s -> System.out.println("  ✓ " + s.getTitle()));

            SongFilterExpression rockOrPop = new OrFilterExpression(
                    new GenreFilterExpression("Rock"),
                    new GenreFilterExpression("Pop")
            );
            System.out.println("Wyrażenie: " + rockOrPop.getExpressionDescription());

            SongFilterExpression notNirvana = new NotFilterExpression(new ArtistFilterExpression("Nirvana"));
            SongFilterExpression complexQuery = new AndFilterExpression(notNirvana, new DifficultyRangeExpression(1, 4));
            System.out.println("Wyrażenie: " + complexQuery.getExpressionDescription());
        } catch (IllegalArgumentException e) {
            System.err.println("Błąd tworzenia wyrażenia logicznego: " + e.getMessage());
        }

        // Test obsługi null w wyrażeniach logicznych
        try {
            new AndFilterExpression(null, new GenreFilterExpression("Rock"));
        } catch (IllegalArgumentException e) {
            System.out.println("✓ And z lewym null: " + e.getMessage());
        }

        try {
            new NotFilterExpression(null);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Not z null: " + e.getMessage());
        }

        // Interpreter 3: Język reguł punktacyjnych
        System.out.println("\n--- Interpreter 3: Język reguł punktacyjnych ---");

        try {
            ScoreExpression baseScore = new ConstantScoreExpression(80, "BaseScore");
            ScoreExpression diffBonus = new ConstantScoreExpression(30, "DifficultyBonus");
            ScoreExpression combined = new AddScoreExpression(baseScore, diffBonus);
            ScoreExpression withMultiplier = new MultiplyScoreExpression(combined, 1.2, "ProMultiplier");
            ScoreExpression finalScore = new BonusScoreExpression(withMultiplier, 50, "Publiczność");

            System.out.println("Wyrażenie: " + finalScore.getExpressionDescription());
            System.out.println("Obliczony wynik: " + finalScore.interpret() + " pkt");
        } catch (IllegalArgumentException | ArithmeticException e) {
            System.err.println("Błąd reguły punktowej: " + e.getMessage());
        }

        // Test obsługi błędów w ScoreExpression
        try {
            new ConstantScoreExpression(100, "");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Pusta etykieta stałej: " + e.getMessage());
        }

        try {
            new AddScoreExpression(null, new ConstantScoreExpression(10, "Test"));
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Add z lewym null: " + e.getMessage());
        }

        try {
            new MultiplyScoreExpression(new ConstantScoreExpression(100, "Base"), 15.0, "TooHigh");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Mnożnik poza zakresem: " + e.getMessage());
        }

        try {
            new BonusScoreExpression(new ConstantScoreExpression(100, "Base"), -10, "Negative");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Bonus ujemny: " + e.getMessage());
        }

        System.out.println("\n----------------------------------------------------------------------------------------------");
    }

    private static void demonstrateSmartPlaylistInterpreter() {
        System.out.println("\n----------------------------------- SMART PLAYLIST INTERPRETER -----------------------------------");

        List<Song> allSongs = new ArrayList<>();
        allSongs.add(new SongBuilder("Numb", "Linkin Park").setGenre("Rock").setDuration(187).setDifficulty(6).build());
        allSongs.add(new SongBuilder("Lithium", "Nirvana").setGenre("Grunge").setDuration(256).setDifficulty(4).build());
        allSongs.add(new SongBuilder("Espresso", "Sabrina Carpenter").setGenre("Pop").setDuration(175).setDifficulty(2).build());
        allSongs.add(new SongBuilder("In Bloom", "Nirvana").setGenre("Grunge").setDuration(255).setDifficulty(5).build());
        allSongs.add(new SongBuilder("Master of Puppets", "Metallica").setGenre("Rock").setDuration(515).setDifficulty(9).build());
        allSongs.add(new SongBuilder("Shake It Off", "Taylor Swift").setGenre("Pop").setDuration(219).setDifficulty(3).build());

        System.out.println("\n--- Interpreter 4: Inteligentne playlisty ---");
        System.out.println("Dostępne piosenki: " + allSongs.size());

        try {
            SongFilterExpression warmUpCondition = new AndFilterExpression(
                    new GenreFilterExpression("Rock"),
                    new DifficultyRangeExpression(1, 5)
            );

            ScoreExpression warmUpScoring = new AddScoreExpression(
                    new ConstantScoreExpression(100, "BaseRock"),
                    new ConstantScoreExpression(30, "WarmUpBonus")
            );

            SmartPlaylistRule warmUpPlaylist = new SmartPlaylistRule(
                    "Rockowa Rozgrzewka",
                    warmUpCondition,
                    warmUpScoring,
                    5
            );

            System.out.println("\n" + warmUpPlaylist.getRuleDescription());
            Playlist rockWarmUp = warmUpPlaylist.generatePlaylist(allSongs);
            System.out.println("Wygenerowana playlista (" + rockWarmUp.getSongs().size() + " piosenek):");
            for (Song s : rockWarmUp.getSongs()) {
                System.out.println("  🎸 " + s.getTitle() + " - " + s.getArtist());
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Błąd tworzenia playlisty: " + e.getMessage());
        }

        // Test obsługi błędów SmartPlaylistRule
        System.out.println("\n--- Test obsługi błędów SmartPlaylistRule ---");
        try {
            new SmartPlaylistRule("", new GenreFilterExpression("Rock"), new ConstantScoreExpression(100, "Base"), 5);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Pusta nazwa reguły: " + e.getMessage());
        }

        try {
            new SmartPlaylistRule("Test", null, new ConstantScoreExpression(100, "Base"), 5);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Null condition: " + e.getMessage());
        }

        try {
            new SmartPlaylistRule("Test", new GenreFilterExpression("Rock"), null, 5);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Null scoring: " + e.getMessage());
        }

        try {
            new SmartPlaylistRule("Test", new GenreFilterExpression("Rock"), new ConstantScoreExpression(100, "Base"), -5);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Ujemny limit: " + e.getMessage());
        }

        // Test AdaptivePlaylistRule
        System.out.println("\n--- Test AdaptivePlaylistRule ---");
        try {
            User beginner = new UserBuilder("Początkujący").setLevel(2).setPoints(150).build();
            ScoreExpression adaptiveScoring = new ConstantScoreExpression(100, "AdaptiveScore");

            AdaptivePlaylistRule beginnerPlaylist = new AdaptivePlaylistRule(
                    "Adaptacyjna", beginner, adaptiveScoring, 4
            );

            System.out.println(beginnerPlaylist.getRuleDescription());
            Playlist beginnerList = beginnerPlaylist.generatePlaylist(allSongs);
            System.out.println("Playlista dla początkującego (" + beginnerList.getSongs().size() + " piosenek):");
            for (Song s : beginnerList.getSongs()) {
                System.out.println("  👤 " + s.getTitle() + " (trudność: " + s.getDifficulty() + ")");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Błąd AdaptivePlaylistRule: " + e.getMessage());
        }

        // Test TimeBasedPlaylistRule
        System.out.println("\n--- Test TimeBasedPlaylistRule ---");
        try {
            TimeBasedPlaylistRule timeLimited = new TimeBasedPlaylistRule(
                    "Wieczór Rockowy",
                    new GenreFilterExpression("Rock"),
                    new ConstantScoreExpression(100, "RockScore"),
                    1800
            );

            System.out.println(timeLimited.getRuleDescription());
            Playlist timeBasedList = timeLimited.generatePlaylist(allSongs);
            int totalDuration = timeBasedList.getSongs().stream().mapToInt(Song::getDuration).sum();
            System.out.println("Wygenerowana playlista (" + timeBasedList.getSongs().size() +
                    " piosenek, łączny czas: " + (totalDuration / 60) + " min)");
        } catch (IllegalArgumentException e) {
            System.err.println("Błąd TimeBasedPlaylistRule: " + e.getMessage());
        }

        // Test CompositePlaylistRule
        System.out.println("\n--- Test CompositePlaylistRule ---");
        try {
            CompositePlaylistRule megaPlaylist = new CompositePlaylistRule("Mega Playlista");
            megaPlaylist.addRule(new SmartPlaylistRule("Rule1",
                    new GenreFilterExpression("Rock"),
                    new ConstantScoreExpression(100, "Score1"), 2));
            megaPlaylist.addRule(new SmartPlaylistRule("Rule2",
                    new GenreFilterExpression("Pop"),
                    new ConstantScoreExpression(100, "Score2"), 2));

            System.out.println(megaPlaylist.getRuleDescription());
            Playlist combined = megaPlaylist.generatePlaylist(allSongs);
            System.out.println("Połączona playlista: " + combined.getSongs().size() + " piosenek");
        } catch (IllegalArgumentException e) {
            System.err.println("Błąd CompositePlaylistRule: " + e.getMessage());
        }

        // Test obsługi null listy piosenek
        try {
            SmartPlaylistRule rule = new SmartPlaylistRule("Test",
                    new GenreFilterExpression("Rock"),
                    new ConstantScoreExpression(100, "Base"), 5);
            rule.generatePlaylist(null);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Null lista piosenek: " + e.getMessage());
        }

        System.out.println("\n----------------------------------------------------------------------------------------------");
    }

    // =========================================================================
    // ITERATOR
    // =========================================================================
    private static void demonstrateIterator() {
        System.out.println("\n----------------------------------- ITERATOR -----------------------------------");

        Playlist playlist = new Playlist("Mieszana Playlista");
        playlist.addSong(new SongBuilder("Numb", "Linkin Park").setGenre("Rock").setDuration(187).setDifficulty(6).build());
        playlist.addSong(new SongBuilder("Espresso", "Sabrina Carpenter").setGenre("Pop").setDuration(175).setDifficulty(2).build());
        playlist.addSong(new SongBuilder("Lithium", "Nirvana").setGenre("Grunge").setDuration(256).setDifficulty(4).build());

        // Iterator sekwencyjny
        System.out.println("\n--- Iterator 1: Sekwencyjny iterator playlisty ---");
        try {
            PlaylistIterator seqIterator = new PlaylistIterator(playlist);
            System.out.println("Sekwencyjne przechodzenie po playliście:");
            int counter = 1;
            while (seqIterator.hasNext()) {
                Song s = seqIterator.next();
                System.out.println("  [" + counter++ + "] " + s.getTitle());
            }

            // Próba next() gdy brak elementów
            if (!seqIterator.hasNext()) {
                try {
                    seqIterator.next();
                } catch (KaraokeIteratorException e) {
                    System.out.println("✓ Próba next() na końcu: " + e.getMessage());
                }
            }

            seqIterator.reset();
            System.out.println("Po resecie - isAtStart: " + seqIterator.isAtStart());
        } catch (KaraokeIteratorException e) {
            System.err.println("Błąd iteratora: " + e.getMessage());
        }

        // Iterator filtrujący - pusty wynik
        System.out.println("\n--- Iterator 2: Iterator filtrujący (pusty) ---");
        try {
            GenreFilteredPlaylistIterator emptyIter = new GenreFilteredPlaylistIterator(playlist, "NieistniejącyGatunek");
            System.out.println("Znaleziono: " + emptyIter.getFilteredCount() + " piosenek");

            while (emptyIter.hasNext()) {
                Song s = emptyIter.next();
                System.out.println("  " + s.getTitle());
            }
            System.out.println("✓ Prawidłowo obsłużono pustą iterację");

            // Próba next() na pustym iteratorze
            try {
                GenreFilteredPlaylistIterator reallyEmpty = new GenreFilteredPlaylistIterator(playlist, "NieistniejącyGatunek");
                reallyEmpty.next();
            } catch (KaraokeIteratorException e) {
                System.out.println("✓ next() na pustym iteratorze: " + e.getMessage());
            }
        } catch (KaraokeIteratorException e) {
            System.err.println("Błąd iteratora filtrującego: " + e.getMessage());
        }

        // Iterator historii wykonań
        System.out.println("\n--- Iterator 3: Iterator historii wykonań ---");
        try {
            List<Performance> performances = new ArrayList<>();
            User user = UserFactory.createUser("standard", "Anna");
            performances.add(new Performance(playlist.getSongs().get(0), List.of(user), 72));
            performances.add(new Performance(playlist.getSongs().get(1), List.of(user), 88));
            performances.add(new Performance(playlist.getSongs().get(2), List.of(user), 65));

            PerformanceHistoryIterator histIterator = new PerformanceHistoryIterator(performances);
            System.out.println("Historia wykonań (od najnowszego):");
            while (histIterator.hasNext()) {
                Performance p = histIterator.next();
                System.out.println("  ← " + p.getSong().getTitle() + " | " + p.getScore());
            }

            // Test pustej historii
            PerformanceHistoryIterator emptyHistory = new PerformanceHistoryIterator(new ArrayList<>());
            if (emptyHistory.isEmpty()) {
                System.out.println("✓ Prawidłowo wykryto pustą historię");
            }
        } catch (Exception e) {
            System.err.println("Błąd iteratora historii: " + e.getMessage());
        }

        // Iterator rankingu użytkowników
        System.out.println("\n--- Iterator 4: Iterator rankingu użytkowników ---");
        try {
            List<User> users = new ArrayList<>();
            users.add(new UserBuilder("Marek").setLevel(5).setPoints(1200).build());
            users.add(new UserBuilder("Anna").setLevel(3).setPoints(450).build());
            users.add(new UserBuilder("Pablo").setLevel(7).setPoints(2100).build());

            UserRankingIterator userRankIter = new UserRankingIterator(users);
            System.out.println("Ranking użytkowników:");
            int rank = 1;
            while (userRankIter.hasNext()) {
                User u = userRankIter.next();
                System.out.println("  #" + rank++ + " " + u.getNickname() + " – " + u.getPoints() + " pkt");
            }

            // Test getTopUser na pustym rankingu
            UserRankingIterator emptyRanking = new UserRankingIterator(new ArrayList<>());
            try {
                emptyRanking.getTopUser();
            } catch (KaraokeIteratorException e) {
                System.out.println("✓ getTopUser() na pustym rankingu: " + e.getMessage());
            }
        } catch (KaraokeIteratorException e) {
            System.err.println("Błąd iteratora rankingu: " + e.getMessage());
        }

        System.out.println("\n----------------------------------------------------------------------------------------------");
    }

    // =========================================================================
    // MEDIATOR
    // =========================================================================
    private static void demonstrateMediator() {
        System.out.println("\n----------------------------------- MEDIATOR -----------------------------------");

        System.out.println("\n--- Mediator: Rejestracja komponentów i mediator sesji ---");
        KaraokeSessionMediator mediator = new KaraokeSessionMediator();

        AudioComponent audio = new AudioComponent();
        ScoringComponent scoring = new ScoringComponent();
        DisplayComponent display = new DisplayComponent();
        NotificationComponent notification = new NotificationComponent();

        try {
            mediator.registerColleague("audio", audio);
            mediator.registerColleague("scoring", scoring);
            mediator.registerColleague("display", display);
            mediator.registerColleague("notification", notification);

            System.out.println("\n--- Mediator: Zdarzenia sesji karaoke ---");

            System.out.println("\n[1] Użytkownik dołącza do sesji:");
            notification.userJoined("Freddie Mercury");

            System.out.println("\n[2] Piosenka startuje:");
            audio.startSong("Bohemian Rhapsody");

            System.out.println("\n[3] Piosenka kończy się:");
            audio.finishSong("Bohemian Rhapsody");

            System.out.println("\nPodsumowanie sesji:");
            System.out.println("  Ostatni wynik:              " + scoring.getLastScore() + " pkt");
            System.out.println("  Powiadomień wysłanych:      " + notification.getNotificationsSent());
            System.out.println("  Nagrywanie aktywne:         " + audio.isRecording());
        } catch (IllegalStateException e) {
            System.err.println("Błąd mediatora: " + e.getMessage());
        }

        // Test obsługi braku komponentu
        System.out.println("\n--- Test obsługi błędów Mediator ---");
        try {
            KaraokeSessionMediator testMediator = new KaraokeSessionMediator();
            // Nie rejestrujemy komponentu "display"
            testMediator.registerColleague("audio", new AudioComponent());
            testMediator.registerColleague("scoring", new ScoringComponent());

            // To powinno rzucić wyjątek przy próbie powiadomienia brakującego komponentu
            AudioComponent testAudio = new AudioComponent();
            testAudio.setMediator(testMediator);
            testAudio.startSong("Test Song");
        } catch (IllegalStateException e) {
            System.out.println("✓ Brak komponentu w mediatorze: " + e.getMessage());
        }

        // Test obsługi null w komponentach
        try {
            mediator.registerColleague(null, audio);
        } catch (Exception e) {
            System.out.println("✓ Rejestracja z null rolą: " + e.getClass().getSimpleName());
        }

        System.out.println("\n----------------------------------------------------------------------------------------------");
    }

    // =========================================================================
    // MEMENTO
    // =========================================================================
    private static void demonstrateMemento() {
        System.out.println("\n----------------------------------- MEMENTO -----------------------------------");

        // Memento 1: Piosenka
        System.out.println("\n--- Memento 1: Historia stanu piosenki (undo) ---");
        Song song = new SongBuilder("Numb", "Linkin Park")
                .setGenre("Rock").setDuration(187).setDifficulty(6).build();
        SongCaretaker songCT = new SongCaretaker(song);

        songCT.save();
        System.out.println("Stan 1 (zapisany):  " + song);

        song.setTitle("Numb (Remix)");
        song.setDifficulty(8);
        songCT.save();
        System.out.println("Stan 2 (zapisany):  " + song);

        song.setGenre("Nu-Metal");
        System.out.println("Stan 3 (niezapisany): " + song);

        try {
            songCT.undo();
            System.out.println("Po undo (→ stan 2): " + song);

            songCT.undo();
            System.out.println("Po undo (→ stan 1): " + song);
        } catch (EmptyHistoryException e) {
            System.out.println("Błąd undo: " + e.getMessage());
        }

        // Test undo na pustej historii
        System.out.println("\n--- Test obsługi błędów Memento ---");
        Song emptySong = new SongBuilder("Test", "Test").build();
        SongCaretaker emptyCT = new SongCaretaker(emptySong);
        try {
            emptyCT.undo();
        } catch (EmptyHistoryException e) {
            System.out.println("✓ Undo na pustej historii: " + e.getMessage());
        }

        // Memento 2: Wykonanie
        System.out.println("\n--- Memento 2: Historia stanu wykonania ---");
        User user = UserFactory.createUser("premium", "Marek");
        Performance performance = new Performance(song, List.of(user), 0);
        PerformanceCaretaker perfCT = new PerformanceCaretaker(performance);

        try {
            performance.setScore(70);
            perfCT.save();
            System.out.println("Wynik po 1. ocenie: " + performance.getScore());

            performance.setScore(85);
            perfCT.save();
            System.out.println("Wynik po korekcie:  " + performance.getScore());

            performance.setScore(40);
            System.out.println("Błędna ocena:       " + performance.getScore());

            perfCT.undo();
            System.out.println("Po undo (korekta):  " + performance.getScore());

            perfCT.undo();
            System.out.println("Po undo (1. ocena): " + performance.getScore());
        } catch (EmptyHistoryException e) {
            System.out.println("Błąd undo wykonania: " + e.getMessage());
        }

        // Memento 3: Playlista
        System.out.println("\n--- Memento 3: Historia stanu playlisty ---");
        Playlist playlist = new Playlist("Wieczór Rock");
        PlaylistCaretaker playlistCT = new PlaylistCaretaker(playlist);

        try {
            playlist.addSong(SongFactory.createSong("rock", "Numb", "Linkin Park", 187, 6));
            playlistCT.save();
            System.out.println("Playlista po dodaniu 1 piosenki: " + playlist.getSongs().size());

            playlist.addSong(SongFactory.createSong("grunge", "Lithium", "Nirvana", 256, 4));
            playlist.addSong(SongFactory.createSong("rock", "Master of Puppets", "Metallica", 515, 9));
            playlistCT.save();
            System.out.println("Playlista po dodaniu 3 piosenek: " + playlist.getSongs().size());

            playlist.setName("Wieczór Rock (Edycja Specjalna)");
            playlist.getSongs().clear();
            System.out.println("Po błędnym wyczyszczeniu: " + playlist.getSongs().size() + " piosenek");

            playlistCT.undo();
            System.out.println("Po undo: " + playlist.getSongs().size() + " piosenek, nazwa: " + playlist.getName());

            playlistCT.undo();
            System.out.println("Po 2x undo: " + playlist.getSongs().size() + " piosenek");
        } catch (EmptyHistoryException e) {
            System.out.println("Błąd undo playlisty: " + e.getMessage());
        }

        // Memento 4: Użytkownik z checkpointami
        System.out.println("\n--- Memento 4: Historia użytkownika z checkpointami ---");
        User gamer = new UserBuilder("Rockstar").setLevel(1).setPoints(0).build();
        UserCaretaker userCT = new UserCaretaker(gamer);

        try {
            userCT.saveCheckpoint("START");
            System.out.println("Checkpoint START: " + gamer);

            gamer.setLevel(3);
            gamer.setPoints(500);
            userCT.save();
            userCT.saveCheckpoint("POZIOM_3");
            System.out.println("Checkpoint POZIOM_3: " + gamer);

            gamer.setLevel(7);
            gamer.setPoints(2000);
            userCT.save();
            System.out.println("Po awansie do poziomu 7: " + gamer);

            userCT.undo();
            System.out.println("Po undo (→ poziom 3): " + gamer);

            userCT.restoreCheckpoint("START");
            System.out.println("Po restore START: " + gamer);
        } catch (EmptyHistoryException e) {
            System.out.println("Błąd undo użytkownika: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Błąd checkpointu: " + e.getMessage());
        }

        // Test restore nieistniejącego checkpointu
        try {
            userCT.restoreCheckpoint("NIEISTNIEJE");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Nieistniejący checkpoint: " + e.getMessage());
        }

        System.out.println("Checkpointów: " + userCT.getCheckpointCount() + ", Historia (stos): " + userCT.getHistorySize());

        System.out.println("\n----------------------------------------------------------------------------------------------");
    }
}
// Koniec, Tydzień 5 – Prezentacja wzorców Command, Interpreter, Iterator, Mediator, Memento (z obsługą wyjątków)