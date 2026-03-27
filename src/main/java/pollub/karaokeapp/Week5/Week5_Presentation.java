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

        System.out.println("\n=================================================================================");
        System.out.println("PREZENTACJA NOWYCH WZORCÓW PROJEKTOWYCH - TYDZIEŃ 5");
        System.out.println("Command, Interpreter, Iterator, Mediator, Memento");
        System.out.println("=================================================================================\n");

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

        // Receiver: SongEditor – zawiera całą logikę biznesową edycji piosenki
        SongEditor songEditor = new SongEditor(song);
        System.out.println("Stan początkowy: " + song);

        invoker.execute(new ChangeSongTitleCommand(songEditor, "Numb (Encore)"));
        System.out.println("Po zmianie tytułu:    " + song);

        invoker.execute(new ChangeSongDifficultyCommand(songEditor, 8));
        System.out.println("Po zmianie trudności: " + song);

        invoker.undo();
        System.out.println("Po cofnięciu (undo):  " + song);

        invoker.redo();
        System.out.println("Po ponowieniu (redo): " + song);

        // Command 2: Komendy playlisty przez Receiver PlaylistManager
        System.out.println("\n--- Command 2: Komendy zarządzania playlistą (Receiver: PlaylistManager) ---");
        Playlist playlist = new Playlist("Rock Classics");
        Song song2 = SongFactory.createSong("grunge", "Lithium", "Nirvana", 256, 4);
        Song song3 = SongFactory.createSong("pop", "Espresso", "Sabrina Carpenter", 175, 2);

        // Receiver: PlaylistManager – zawiera całą logikę biznesową operacji na playliście
        PlaylistManager playlistManager = new PlaylistManager(playlist);

        invoker.execute(new AddSongToPlaylistCommand(playlistManager, song));
        invoker.execute(new AddSongToPlaylistCommand(playlistManager, song2));
        invoker.execute(new AddSongToPlaylistCommand(playlistManager, song3));
        System.out.println("Playlista po dodaniu 3 piosenek: " + playlist.getSongs().size() + " piosenek");

        invoker.execute(new RemoveSongFromPlaylistCommand(playlistManager, song3));
        System.out.println("Po usunięciu Espresso:           " + playlist.getSongs().size() + " piosenki");

        invoker.undo(); // cofnij usunięcie
        System.out.println("Po cofnięciu usunięcia (undo):   " + playlist.getSongs().size() + " piosenek");

        // Command 3: Wynik wykonania i makrokomenda przez Receiver PerformanceJudge
        System.out.println("\n--- Command 3: Komenda wyniku i makrokomenda (Receiver: PerformanceJudge) ---");
        User user = UserFactory.createUser("premium", "Marek");
        Performance performance = new Performance(song, List.of(user), 0);

        // Receiver: PerformanceJudge – zawiera całą logikę biznesową oceniania wykonania
        PerformanceJudge judge = new PerformanceJudge(performance);

        // Makrokomenda: kilka operacji (każda z własnym Receiverem) jako jedna jednostka
        MacroCommand macro = new MacroCommand("Przygotuj Numer Otwarcia");
        macro.addCommand(new ChangeSongTitleCommand(songEditor, "Numb (Opening Night)"));
        macro.addCommand(new ChangeSongDifficultyCommand(songEditor, 9));
        macro.addCommand(new SetPerformanceScoreCommand(judge, 95));

        invoker.execute(macro);
        System.out.println("Po makrokomendzie: " + song + " | wynik: " + performance.getScore());

        invoker.undo(); // cofa całą makrokomendę
        System.out.println("Po cofnięciu makra: " + song + " | wynik: " + performance.getScore());

        System.out.println("\nHistoria invoker'a (rozmiar): " + invoker.getHistorySize());
        invoker.printHistory();

        // Command 4: Komendy edycji profilu użytkownika przez Receiver UserProfileEditor
        System.out.println("\n--- Command 4: Edycja profilu użytkownika (Receiver: UserProfileEditor) ---");
        User profileUser = UserFactory.createUser("standard", "Anonimowy");

        // Receiver: UserProfileEditor – zawiera całą logikę biznesową edycji profilu
        UserProfileEditor profileEditor = new UserProfileEditor(profileUser);
        System.out.println("Stan początkowy: " + profileUser);

        invoker.execute(new ChangeUserNicknameCommand(profileEditor, "RockStar99"));
        System.out.println("Po zmianie nicku:    " + profileUser);

        invoker.execute(new ChangeUserLevelCommand(profileEditor, 5));
        System.out.println("Po zmianie poziomu:  " + profileUser);

        invoker.execute(new AwardPointsCommand(profileEditor, 300, "Pierwsze wykonanie"));
        invoker.execute(new AwardPointsCommand(profileEditor, 150, "Bonus weekendowy"));
        System.out.println("Po przyznaniu punktów: " + profileUser);

        invoker.undo(); // cofnij bonus weekendowy
        System.out.println("Po cofnięciu bonusu: " + profileUser);

        invoker.undo(); // cofnij pierwsze wykonanie
        System.out.println("Po cofnięciu nagrody: " + profileUser);

        invoker.undo(); // cofnij zmianę poziomu
        invoker.undo(); // cofnij zmianę nicku
        System.out.println("Po cofnięciu wszystkiego: " + profileUser);

        System.out.println("\n----------------------------------------------------------------------------------------------");
    }

    // =========================================================================
    // INTERPRETER
    // =========================================================================
    private static void demonstrateInterpreter() {
        System.out.println("\n----------------------------------- INTERPRETER -----------------------------------");

        // Pula piosenek do testów
        List<Song> songs = new ArrayList<>();
        songs.add(new SongBuilder("Numb", "Linkin Park").setGenre("Rock").setDuration(187).setDifficulty(6).build());
        songs.add(new SongBuilder("Lithium", "Nirvana").setGenre("Grunge").setDuration(256).setDifficulty(4).build());
        songs.add(new SongBuilder("Espresso", "Sabrina Carpenter").setGenre("Pop").setDuration(175).setDifficulty(2).build());
        songs.add(new SongBuilder("In Bloom", "Nirvana").setGenre("Grunge").setDuration(255).setDifficulty(5).build());
        songs.add(new SongBuilder("Master of Puppets", "Metallica").setGenre("Rock").setDuration(515).setDifficulty(9).build());
        songs.add(new SongBuilder("Shake It Off", "Taylor Swift").setGenre("Pop").setDuration(219).setDifficulty(3).build());

        // Interpreter 1: Wyrażenia terminalne (proste filtry)
        System.out.println("\n--- Interpreter 1: Terminale – proste filtry ---");
        SongFilterExpression rockFilter = new GenreFilterExpression("Rock");
        SongFilterExpression easyFilter = new DifficultyRangeExpression(1, 4);
        SongFilterExpression nirFilter  = new ArtistFilterExpression("Nirvana");

        System.out.println("Wyrażenie: " + rockFilter.getExpressionDescription());
        songs.stream().filter(rockFilter::interpret)
                .forEach(s -> System.out.println("  ✓ " + s.getTitle() + " [" + s.getGenre() + "]"));

        System.out.println("Wyrażenie: " + easyFilter.getExpressionDescription());
        songs.stream().filter(easyFilter::interpret)
                .forEach(s -> System.out.println("  ✓ " + s.getTitle() + " (trudność " + s.getDifficulty() + ")"));

        // Interpreter 2: Wyrażenia nieterminalne (AND, OR, NOT)
        System.out.println("\n--- Interpreter 3: Nieterminale – złożone wyrażenia logiczne ---");

        SongFilterExpression grungeMedium = new AndFilterExpression(
                new GenreFilterExpression("Grunge"),
                new DifficultyRangeExpression(4, 6)
        );
        System.out.println("Wyrażenie: " + grungeMedium.getExpressionDescription());
        songs.stream().filter(grungeMedium::interpret)
                .forEach(s -> System.out.println("  ✓ " + s.getTitle() + " [" + s.getGenre() + ", diff=" + s.getDifficulty() + "]"));

        SongFilterExpression rockOrPop = new OrFilterExpression(
                new GenreFilterExpression("Rock"),
                new GenreFilterExpression("Pop")
        );
        System.out.println("Wyrażenie: " + rockOrPop.getExpressionDescription());
        songs.stream().filter(rockOrPop::interpret)
                .forEach(s -> System.out.println("  ✓ " + s.getTitle() + " [" + s.getGenre() + "]"));

        SongFilterExpression notNirvana = new NotFilterExpression(nirFilter);
        SongFilterExpression complexQuery = new AndFilterExpression(notNirvana, easyFilter);
        System.out.println("Wyrażenie: " + complexQuery.getExpressionDescription());
        songs.stream().filter(complexQuery::interpret)
                .forEach(s -> System.out.println("  ✓ " + s.getTitle() + " – " + s.getArtist()));

        // Interpreter 3: Język reguł punktacyjnych
        System.out.println("\n--- Interpreter 4: Język reguł punktacyjnych ---");

        // Reguła: BONUS[Publiczność+50](BaseScore(80) + DifficultyBonus(30)) * Multiplier[1.2]
        ScoreExpression baseScore      = new ConstantScoreExpression(80, "BaseScore");
        ScoreExpression diffBonus      = new ConstantScoreExpression(30, "DifficultyBonus");
        ScoreExpression combined       = new AddScoreExpression(baseScore, diffBonus);
        ScoreExpression withMultiplier = new MultiplyScoreExpression(combined, 1.2, "ProMultiplier");
        ScoreExpression finalScore     = new BonusScoreExpression(withMultiplier, 50, "Publiczność");

        System.out.println("Wyrażenie: " + finalScore.getExpressionDescription());
        System.out.println("Obliczony wynik: " + finalScore.interpret() + " pkt");

        // Prosta reguła: bazowy * trudność
        ScoreExpression simpleRule = new MultiplyScoreExpression(
                new ConstantScoreExpression(100, "Base"),
                1.5, "ExpertMultiplier"
        );
        System.out.println("Prosta reguła: " + simpleRule.getExpressionDescription()
                + " = " + simpleRule.interpret() + " pkt");

        System.out.println("\n----------------------------------------------------------------------------------------------");
    }

    private static void demonstrateSmartPlaylistInterpreter() {
        System.out.println("\n----------------------------------- SMART PLAYLIST INTERPRETER -----------------------------------");

        // Pula piosenek do testów
        List<Song> allSongs = new ArrayList<>();
        allSongs.add(new SongBuilder("Numb", "Linkin Park").setGenre("Rock").setDuration(187).setDifficulty(6).build());
        allSongs.add(new SongBuilder("Lithium", "Nirvana").setGenre("Grunge").setDuration(256).setDifficulty(4).build());
        allSongs.add(new SongBuilder("Espresso", "Sabrina Carpenter").setGenre("Pop").setDuration(175).setDifficulty(2).build());
        allSongs.add(new SongBuilder("In Bloom", "Nirvana").setGenre("Grunge").setDuration(255).setDifficulty(5).build());
        allSongs.add(new SongBuilder("Master of Puppets", "Metallica").setGenre("Rock").setDuration(515).setDifficulty(9).build());
        allSongs.add(new SongBuilder("Shake It Off", "Taylor Swift").setGenre("Pop").setDuration(219).setDifficulty(3).build());
        allSongs.add(new SongBuilder("Bohemian Rhapsody", "Queen").setGenre("Rock").setDuration(355).setDifficulty(8).build());
        allSongs.add(new SongBuilder("Smells Like Teen Spirit", "Nirvana").setGenre("Grunge").setDuration(301).setDifficulty(7).build());
        allSongs.add(new SongBuilder("Blinding Lights", "The Weeknd").setGenre("Pop").setDuration(200).setDifficulty(4).build());
        allSongs.add(new SongBuilder("Wonderwall", "Oasis").setGenre("Rock").setDuration(258).setDifficulty(3).build());
        allSongs.add(new SongBuilder("Seven Nation Army", "The White Stripes").setGenre("Rock").setDuration(232).setDifficulty(4).build());
        allSongs.add(new SongBuilder("Wish You Were Here", "Pink Floyd").setGenre("Rock").setDuration(334).setDifficulty(4).build());
        allSongs.add(new SongBuilder("Zombie", "The Cranberries").setGenre("Rock").setDuration(307).setDifficulty(5).build());
        allSongs.add(new SongBuilder("Creep", "Radiohead").setGenre("Rock").setDuration(235).setDifficulty(4).build());

        System.out.println("\n--- Interpreter 5: Inteligentne playlisty ---");
        System.out.println("Dostępne piosenki: " + allSongs.size());

        // Reguła 1: Rockowa rozgrzewka (łatwe rockowe piosenki)
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
        if (rockWarmUp.getSongs().isEmpty()) {
            System.out.println("  ⚠️ Brak piosenek spełniających kryteria!");
        } else {
            for (Song s : rockWarmUp.getSongs()) {
                System.out.println("  🎸 " + s.getTitle() + " - " + s.getArtist() +
                        " (trudność: " + s.getDifficulty() + ")");
            }
        }

        // Reguła 2: Zaawansowane wyzwanie
        SongFilterExpression challengeCondition = new AndFilterExpression(
                new OrFilterExpression(
                        new GenreFilterExpression("Rock"),
                        new GenreFilterExpression("Grunge")
                ),
                new DifficultyRangeExpression(7, 10)
        );

        ScoreExpression challengeScoring = new MultiplyScoreExpression(
                new ConstantScoreExpression(150, "BaseChallenge"),
                1.5,
                "DifficultyMultiplier"
        );

        SmartPlaylistRule challengePlaylist = new SmartPlaylistRule(
                "Zaawansowane Wyzwanie",
                challengeCondition,
                challengeScoring,
                3
        );

        System.out.println("\n" + challengePlaylist.getRuleDescription());
        Playlist challenge = challengePlaylist.generatePlaylist(allSongs);
        System.out.println("Wygenerowana playlista (" + challenge.getSongs().size() + " piosenek):");
        for (Song s : challenge.getSongs()) {
            System.out.println("  🔥 " + s.getTitle() + " - " + s.getArtist() +
                    " (trudność: " + s.getDifficulty() + ", czas: " + s.getDuration() + "s)");
        }

        // Reguła 3: Pop mix
        SongFilterExpression popCondition = new AndFilterExpression(
                new GenreFilterExpression("Pop"),
                new NotFilterExpression(new ArtistFilterExpression("Taylor Swift"))
        );

        ScoreExpression popScoring = new AddScoreExpression(
                new ConstantScoreExpression(80, "BasePop"),
                new BonusScoreExpression(
                        new ConstantScoreExpression(0, "PopularityBonus"),
                        25,
                        "Trending"
                )
        );

        SmartPlaylistRule popPlaylist = new SmartPlaylistRule(
                "Pop Mix (bez Taylor Swift)",
                popCondition,
                popScoring,
                3
        );

        System.out.println("\n" + popPlaylist.getRuleDescription());
        Playlist popMix = popPlaylist.generatePlaylist(allSongs);
        System.out.println("Wygenerowana playlista (" + popMix.getSongs().size() + " piosenek):");
        for (Song s : popMix.getSongs()) {
            System.out.println("  🎤 " + s.getTitle() + " - " + s.getArtist());
        }

        // Kompozyt reguł
        System.out.println("\n--- Kompozyt reguł: Mega Playlista Wieczoru ---");
        CompositePlaylistRule megaPlaylist = new CompositePlaylistRule("Mega Wieczór Karaoke");
        megaPlaylist.addRule(warmUpPlaylist);
        megaPlaylist.addRule(challengePlaylist);
        megaPlaylist.addRule(popPlaylist);

        System.out.println(megaPlaylist.getRuleDescription());
        Playlist eveningPlaylist = megaPlaylist.generatePlaylist(allSongs);
        System.out.println("\nKompletna playlista wieczoru (" + eveningPlaylist.getSongs().size() + " piosenek):");
        for (Song s : eveningPlaylist.getSongs()) {
            System.out.println("  ✨ " + s.getTitle() + " - " + s.getArtist());
        }

        // Adaptacyjna playlista
        System.out.println("\n--- Adaptacyjna playlista (dostosowana do poziomu użytkownika) ---");
        User beginner = new UserBuilder("Początkujący").setLevel(2).setPoints(150).build();
        User intermediate = new UserBuilder("Średniak").setLevel(5).setPoints(800).build();
        User advanced = new UserBuilder("Ekspert").setLevel(9).setPoints(2500).build();

        ScoreExpression adaptiveScoring = new ConstantScoreExpression(100, "AdaptiveScore");

        AdaptivePlaylistRule beginnerPlaylist = new AdaptivePlaylistRule(
                "Adaptacyjna", beginner, adaptiveScoring, 4
        );

        System.out.println("\n" + beginnerPlaylist.getRuleDescription());
        Playlist beginnerList = beginnerPlaylist.generatePlaylist(allSongs);
        System.out.println("Playlista dla początkującego (" + beginnerList.getSongs().size() + " piosenek):");
        for (Song s : beginnerList.getSongs()) {
            System.out.println("  👤 " + s.getTitle() + " - " + s.getArtist() +
                    " (trudność: " + s.getDifficulty() + ")");
        }

        AdaptivePlaylistRule intermediatePlaylist = new AdaptivePlaylistRule(
                "Adaptacyjna", intermediate, adaptiveScoring, 4
        );

        System.out.println("\n" + intermediatePlaylist.getRuleDescription());
        Playlist intermediateList = intermediatePlaylist.generatePlaylist(allSongs);
        System.out.println("Playlista dla średniozaawansowanego (" + intermediateList.getSongs().size() + " piosenek):");
        for (Song s : intermediateList.getSongs()) {
            System.out.println("  👤 " + s.getTitle() + " - " + s.getArtist() +
                    " (trudność: " + s.getDifficulty() + ")");
        }

        AdaptivePlaylistRule advancedPlaylist = new AdaptivePlaylistRule(
                "Adaptacyjna", advanced, adaptiveScoring, 4
        );

        System.out.println("\n" + advancedPlaylist.getRuleDescription());
        Playlist advancedList = advancedPlaylist.generatePlaylist(allSongs);
        System.out.println("Playlista dla eksperta (" + advancedList.getSongs().size() + " piosenek):");
        for (Song s : advancedList.getSongs()) {
            System.out.println("  👤 " + s.getTitle() + " - " + s.getArtist() +
                    " (trudność: " + s.getDifficulty() + ")");
        }

        // Reguła z limitem czasowym
        System.out.println("\n--- Reguła z limitem czasowym (30 minut) ---");
        TimeBasedPlaylistRule timeLimited = new TimeBasedPlaylistRule(
                "Wieczór Rockowy",
                new GenreFilterExpression("Rock"),
                new ConstantScoreExpression(100, "RockScore"),
                1800 // 30 minut = 1800 sekund
        );

        System.out.println(timeLimited.getRuleDescription());
        Playlist timeBasedList = timeLimited.generatePlaylist(allSongs);
        int totalDuration = 0;
        for (Song s : timeBasedList.getSongs()) {
            totalDuration += s.getDuration();
        }
        System.out.println("Wygenerowana playlista (" + timeBasedList.getSongs().size() +
                " piosenek, łączny czas: " + (totalDuration / 60) + " min " +
                (totalDuration % 60) + " s):");
        for (Song s : timeBasedList.getSongs()) {
            System.out.println("  ⏱️ " + s.getTitle() + " - " + s.getArtist() +
                    " (" + (s.getDuration() / 60) + ":" + String.format("%02d", s.getDuration() % 60) + ")");
        }

        // Ranking piosenek według punktacji
        System.out.println("\n--- Ranking piosenek rockowych (według reguły punktowej) ---");
        ScoreExpression rankingScoring = new MultiplyScoreExpression(
                new ConstantScoreExpression(100, "Base"),
                1.2,
                "PopularityMultiplier"
        );

        SongFilterExpression rockOnly = new GenreFilterExpression("Rock");
        SmartPlaylistRule rankingPlaylist = new SmartPlaylistRule(
                "Top Rock Hits",
                rockOnly,
                rankingScoring,
                10
        );

        Playlist rankedRock = rankingPlaylist.generatePlaylist(allSongs);
        System.out.println("Ranking rockowych hitów:");
        int rank = 1;
        for (Song s : rankedRock.getSongs()) {
            System.out.println("  #" + rank++ + " " + s.getTitle() + " - " + s.getArtist() +
                    " (trudność: " + s.getDifficulty() + ")");
        }

        System.out.println("\n----------------------------------------------------------------------------------------------");
    }

    // =========================================================================
    // ITERATOR
    // =========================================================================
    private static void demonstrateIterator() {
        System.out.println("\n----------------------------------- ITERATOR -----------------------------------");

        // Przygotowanie danych
        Playlist playlist = new Playlist("Mieszana Playlista");
        playlist.addSong(new SongBuilder("Numb", "Linkin Park").setGenre("Rock").setDuration(187).setDifficulty(6).build());
        playlist.addSong(new SongBuilder("Espresso", "Sabrina Carpenter").setGenre("Pop").setDuration(175).setDifficulty(2).build());
        playlist.addSong(new SongBuilder("Lithium", "Nirvana").setGenre("Grunge").setDuration(256).setDifficulty(4).build());
        playlist.addSong(new SongBuilder("In Bloom", "Nirvana").setGenre("Grunge").setDuration(255).setDifficulty(5).build());
        playlist.addSong(new SongBuilder("Shake It Off", "Taylor Swift").setGenre("Pop").setDuration(219).setDifficulty(3).build());

        // Iterator 2: Sekwencyjny i filtrujący iterator playlisty
        System.out.println("\n--- Iterator 2: Sekwencyjny i filtrujący iterator playlisty ---");
        PlaylistIterator seqIterator = new PlaylistIterator(playlist);
        System.out.println("Sekwencyjne przechodzenie po playliście:");
        while (seqIterator.hasNext()) {
            Song s = seqIterator.next();
            System.out.println("  [" + seqIterator.getCurrentIndex() + "] " + s.getTitle() + " – " + s.getArtist());
        }

        GenreFilteredPlaylistIterator grungIter = new GenreFilteredPlaylistIterator(playlist, "Grunge");
        System.out.println("Piosenki Grunge (iterator filtrujący) – znaleziono: " + grungIter.getFilteredCount());
        while (grungIter.hasNext()) {
            System.out.println("  ✓ " + grungIter.next().getTitle());
        }

        // Iterator 3: Historia wykonań (od najnowszego) i ranking
        System.out.println("\n--- Iterator 3: Iterator historii wykonań i rankingu ---");
        User user1 = UserFactory.createUser("standard", "Anna");
        User user2 = UserFactory.createUser("premium", "Jan");
        List<Performance> performances = new ArrayList<>();
        performances.add(new Performance(playlist.getSongs().get(0), List.of(user1), 72));
        performances.add(new Performance(playlist.getSongs().get(1), List.of(user2), 88));
        performances.add(new Performance(playlist.getSongs().get(2), List.of(user1), 65));
        performances.add(new Performance(playlist.getSongs().get(3), List.of(user1, user2), 91));

        PerformanceHistoryIterator histIterator = new PerformanceHistoryIterator(performances);
        System.out.println("Historia wykonań (od najnowszego):");
        while (histIterator.hasNext()) {
            Performance p = histIterator.next();
            System.out.println("  ← " + p.getSong().getTitle() + " | wynik: " + p.getScore());
        }

        TopScorePerformanceIterator rankIterator = new TopScorePerformanceIterator(performances);
        System.out.println("Ranking wykonań (od najlepszego):");
        int rank = 1;
        while (rankIterator.hasNext()) {
            Performance p = rankIterator.next();
            System.out.println("  #" + rank++ + " " + p.getSong().getTitle() + " – " + p.getScore() + " pkt");
        }

        // Iterator 4: Ranking użytkowników i piosenki wg trudności
        System.out.println("\n--- Iterator 4: Ranking użytkowników i piosenki wg trudności ---");
        List<User> users = new ArrayList<>();
        users.add(new UserBuilder("Marek").setLevel(5).setPoints(1200).build());
        users.add(new UserBuilder("Anna").setLevel(3).setPoints(450).build());
        users.add(new UserBuilder("Pablo").setLevel(7).setPoints(2100).build());
        users.add(new UserBuilder("Kasia").setLevel(2).setPoints(300).build());

        UserRankingIterator userRankIter = new UserRankingIterator(users);
        System.out.println("Ranking użytkowników (wg punktów):");
        int uRank = 1;
        while (userRankIter.hasNext()) {
            User u = userRankIter.next();
            System.out.println("  #" + uRank++ + " " + u.getNickname() + " – " + u.getPoints() + " pkt, poziom " + u.getLevel());
        }

        DifficultySortedSongIterator diffIter = new DifficultySortedSongIterator(playlist.getSongs());
        System.out.println("Piosenki od najłatwiejszej do najtrudniejszej (tryb nauki):");
        while (diffIter.hasNext()) {
            Song s = diffIter.next();
            System.out.println("  diff " + s.getDifficulty() + " → " + s.getTitle());
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

        AudioComponent        audio        = new AudioComponent();
        ScoringComponent      scoring      = new ScoringComponent();
        DisplayComponent      display      = new DisplayComponent();
        NotificationComponent notification = new NotificationComponent();

        mediator.registerColleague("audio",        audio);
        mediator.registerColleague("scoring",      scoring);
        mediator.registerColleague("display",      display);
        mediator.registerColleague("notification", notification);

        // Mediator: Przepływ sesji przez mediatora
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

        songCT.undo();
        System.out.println("Po undo (→ stan 2): " + song);

        songCT.undo();
        System.out.println("Po undo (→ stan 1): " + song);

        System.out.println("Rozmiar historii:   " + songCT.getHistorySize());

        // Memento 2: Wykonanie
        System.out.println("\n--- Memento 2: Historia stanu wykonania (wynik) ---");
        User user = UserFactory.createUser("premium", "Marek");
        Performance performance = new Performance(song, List.of(user), 0);
        PerformanceCaretaker perfCT = new PerformanceCaretaker(performance);

        performance.setScore(70);
        perfCT.save();
        System.out.println("Wynik po 1. ocenie: " + performance.getScore());

        performance.setScore(85);
        perfCT.save();
        System.out.println("Wynik po korekcie:  " + performance.getScore());

        performance.setScore(40); // błędna ocena
        System.out.println("Błędna ocena:       " + performance.getScore());

        perfCT.undo();
        System.out.println("Po undo (korekta):  " + performance.getScore());

        // Memento 3: Playlista
        System.out.println("\n--- Memento 3: Historia stanu playlisty ---");
        Playlist playlist = new Playlist("Wieczór Rock");
        PlaylistCaretaker playlistCT = new PlaylistCaretaker(playlist);

        playlist.addSong(SongFactory.createSong("rock", "Numb", "Linkin Park", 187, 6));
        playlistCT.save();
        System.out.println("Playlista po dodaniu 1 piosenki: " + playlist.getSongs().size());

        playlist.addSong(SongFactory.createSong("grunge", "Lithium", "Nirvana", 256, 4));
        playlist.addSong(SongFactory.createSong("rock", "Master of Puppets", "Metallica", 515, 9));
        playlistCT.save();
        System.out.println("Playlista po dodaniu 3 piosenek: " + playlist.getSongs().size());

        playlist.setName("Wieczór Rock (Edycja Specjalna)");
        playlist.getSongs().clear();
        System.out.println("Po błędnym wyczyszczeniu: " + playlist.getSongs().size() + " piosenek, nazwa: " + playlist.getName());

        playlistCT.undo();
        System.out.println("Po undo: " + playlist.getSongs().size() + " piosenek, nazwa: " + playlist.getName());

        playlistCT.undo();
        System.out.println("Po 2x undo: " + playlist.getSongs().size() + " piosenek");

        // Memento 4: Użytkownik z checkpointami
        System.out.println("\n--- Memento 4: Historia użytkownika z checkpointami ---");
        User gamer = new UserBuilder("Rockstar").setLevel(1).setPoints(0).build();
        UserCaretaker userCT = new UserCaretaker(gamer);

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

        userCT.undo(); // cofnij do poziomu 3
        System.out.println("Po undo (→ poziom 3): " + gamer);

        userCT.restoreCheckpoint("START"); // przywróć do samego początku
        System.out.println("Po restore START: " + gamer);

        System.out.println("Checkpointów: " + userCT.getCheckpointCount() + ", Historia (stos): " + userCT.getHistorySize());

        System.out.println("\n----------------------------------------------------------------------------------------------");
    }
}
// Koniec, Tydzień 5 – Prezentacja wzorców Command, Interpreter, Iterator, Mediator, Memento