package pollub.karaokeapp.Week10;

import pollub.karaokeapp.Week2.builder.song.SongBuilder;
import pollub.karaokeapp.Week2.builder.user.UserBuilder;
import pollub.karaokeapp.Week2.singleton.LoggerSingleton;
import pollub.karaokeapp.Week10.functional.*;
import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Week10_Presentation {

    public static void week10_presentation() {
        LoggerSingleton logger = LoggerSingleton.getInstance();
        logger.log("Rozpoczęcie prezentacji Tygodnia 10");

        System.out.println("\n================================================================================");
        System.out.println("PREZENTACJA TYGODNIA 10 - PROGRAMOWANIE FUNKCYJNE W JAVIE");
        System.out.println("Interfejsy funkcyjne, wyrażenia lambda, Streams API, Predicate, Function");
        System.out.println("================================================================================\n");

        demonstrateFunctionalInterfaces();
        demonstrateStreams();
        demonstratePredicateAndFunction();

        logger.log("Zakończenie prezentacji Tygodnia 10");
    }

    // =========================================================================
    // ZADANIE 1 — 3 INTERFEJSY FUNKCYJNE Z LAMBDAMI
    // =========================================================================
    private static void demonstrateFunctionalInterfaces() {
        System.out.println("\n---------------- ZADANIE 1: INTERFEJSY FUNKCYJNE I WYRAŻENIA LAMBDA ----------------");

        List<Song> songs = buildSampleSongs();
        List<User> users = buildSampleUsers();
        List<Performance> performances = buildSamplePerformances(songs, users);

        // --- Interfejs funkcyjny 1: SongRatingFunction ---
        System.out.println("\n--- SongRatingFunction (interfejs funkcyjny 1) ---");

        // Implementacja klasowa
        SongRatingFunction difficultyRating = new DifficultyBasedRating();
        System.out.println("Implementacja klasowa (DifficultyBasedRating):");
        for (Song s : songs) {
            System.out.println("  \"" + s.getTitle() + "\" -> ocena: " + difficultyRating.rate(s));
        }

        // Implementacja przez wyrażenie lambda — ocena na podstawie czasu trwania
        SongRatingFunction durationRating = song -> song.getDuration() / 10;
        System.out.println("Wyrażenie lambda (ocena wg czasu trwania):");
        for (Song s : songs) {
            System.out.println("  \"" + s.getTitle() + "\" (" + s.getDuration() + "s) -> ocena: " + durationRating.rate(s));
        }

        // Wyrażenie lambda — złożona reguła: trudność * czas / 100
        SongRatingFunction compositeRating = song -> (song.getDifficulty() * song.getDuration()) / 100;
        System.out.println("Wyrażenie lambda (trudność × czas / 100):");
        for (Song s : songs) {
            System.out.println("  \"" + s.getTitle() + "\" -> ocena: " + compositeRating.rate(s));
        }

        // --- Interfejs funkcyjny 2: PerformanceReporter ---
        System.out.println("\n--- PerformanceReporter (interfejs funkcyjny 2) ---");

        // Implementacja klasowa
        PerformanceReporter detailedReporter = new SimplePerformanceReporter();
        System.out.println("Implementacja klasowa (SimplePerformanceReporter):");
        for (Performance p : performances) {
            System.out.println("  " + detailedReporter.report(p));
        }

        // Implementacja przez wyrażenie lambda — zwięzły format
        PerformanceReporter shortReporter = p ->
                p.getSong().getTitle() + " -> " + p.getScore() + " pkt";
        System.out.println("Wyrażenie lambda (zwięzły format):");
        for (Performance p : performances) {
            System.out.println("  " + shortReporter.report(p));
        }

        // Wyrażenie lambda — format z oceną słowną
        PerformanceReporter gradeReporter = p -> {
            String grade = p.getScore() >= 300 ? "Doskonały" : p.getScore() >= 200 ? "Dobry" : "Słaby";
            return p.getSong().getTitle() + " [" + grade + ": " + p.getScore() + " pkt]";
        };
        System.out.println("Wyrażenie lambda (format z oceną słowną):");
        for (Performance p : performances) {
            System.out.println("  " + gradeReporter.report(p));
        }

        // --- Interfejs funkcyjny 3: UserPointsCalculator ---
        System.out.println("\n--- UserPointsCalculator (interfejs funkcyjny 3) ---");
        int basePoints = 100;

        // Implementacja klasowa
        UserPointsCalculator levelCalculator = new LevelBasedPointsCalculator();
        System.out.println("Implementacja klasowa (LevelBasedPointsCalculator), baza = " + basePoints + " pkt:");
        for (User u : users) {
            System.out.println("  " + u.getNickname() + " (poziom " + u.getLevel() + ") -> " + levelCalculator.calculate(u, basePoints) + " pkt");
        }

        // Implementacja przez wyrażenie lambda — premia za wysoki poziom
        UserPointsCalculator bonusCalculator = (user, pts) -> user.getLevel() >= 8 ? pts * 2 : pts;
        System.out.println("Wyrażenie lambda (podwójna premia od poziomu 8+):");
        for (User u : users) {
            System.out.println("  " + u.getNickname() + " (poziom " + u.getLevel() + ") -> " + bonusCalculator.calculate(u, basePoints) + " pkt");
        }

        // Wyrażenie lambda — naliczanie z uwzględnieniem istniejących punktów
        UserPointsCalculator accumulatingCalculator = (user, pts) -> user.getPoints() + pts * user.getLevel();
        System.out.println("Wyrażenie lambda (punkty akumulowane: posiadane + baza × poziom):");
        for (User u : users) {
            System.out.println("  " + u.getNickname() + " (posiadane: " + u.getPoints() + ") -> " + accumulatingCalculator.calculate(u, basePoints) + " pkt");
        }

        System.out.println("--------------------------------------------------------------------------------\n");
    }

    // =========================================================================
    // ZADANIE 2 — PRZETWARZANIE STRUMIENIOWE 3 KOLEKCJI
    // =========================================================================
    private static void demonstrateStreams() {
        System.out.println("\n---------------- ZADANIE 2: PRZETWARZANIE STRUMIENIOWE KOLEKCJI ----------------");

        List<Song> songs = buildSampleSongs();
        List<User> users = buildSampleUsers();
        List<Performance> performances = buildSamplePerformances(songs, users);

        // --- Kolekcja 1: List<Song> ---
        System.out.println("\n--- Kolekcja 1: List<Song> ---");

        System.out.println("Piosenki rockowe posortowane po trudności (malejąco):");
        List<String> rockSongLabels = songs.stream()
                .filter(s -> "Rock".equals(s.getGenre()))
                .sorted(Comparator.comparingInt(Song::getDifficulty).reversed())
                .map(s -> s.getTitle() + " [trudność: " + s.getDifficulty() + "]")
                .collect(Collectors.toList());
        rockSongLabels.forEach(label -> System.out.println("  " + label));

        System.out.println("Średnia trudność wszystkich piosenek: "
                + songs.stream()
                       .mapToInt(Song::getDifficulty)
                       .average()
                       .orElse(0.0));

        System.out.println("Unikalne gatunki muzyczne:");
        songs.stream()
                .map(Song::getGenre)
                .distinct()
                .sorted()
                .forEach(genre -> System.out.println("  " + genre));

        // --- Kolekcja 2: List<User> ---
        System.out.println("\n--- Kolekcja 2: List<User> ---");

        System.out.println("Użytkownicy z poziomem >= 6 posortowani wg punktów (malejąco):");
        users.stream()
                .filter(u -> u.getLevel() >= 6)
                .sorted(Comparator.comparingInt(User::getPoints).reversed())
                .forEach(u -> System.out.println("  " + u.getNickname()
                        + " | poziom: " + u.getLevel()
                        + " | punkty: " + u.getPoints()));

        int totalPoints = users.stream()
                .mapToInt(User::getPoints)
                .sum();
        System.out.println("Suma punktów wszystkich użytkowników: " + totalPoints);

        OptionalInt maxLevel = users.stream()
                .mapToInt(User::getLevel)
                .max();
        System.out.println("Najwyższy poziom w systemie: " + maxLevel.orElse(0));

        // --- Kolekcja 3: List<Performance> ---
        System.out.println("\n--- Kolekcja 3: List<Performance> ---");

        System.out.println("Top 3 wykonań wg wyniku:");
        performances.stream()
                .sorted(Comparator.comparingInt(Performance::getScore).reversed())
                .limit(3)
                .forEach(p -> System.out.println("  \"" + p.getSong().getTitle()
                        + "\" - " + p.getScore() + " pkt"));

        System.out.println("Grupowanie wykonań wg gatunku piosenki:");
        Map<String, Long> performancesByGenre = performances.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getSong().getGenre(),
                        Collectors.counting()
                ));
        performancesByGenre.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(e -> System.out.println("  " + e.getKey() + ": " + e.getValue() + " wykonań"));

        System.out.println("Uczestnicy wszystkich wykonań (bez powtórzeń):");
        performances.stream()
                .flatMap(p -> p.getParticipants().stream())
                .map(User::getNickname)
                .distinct()
                .sorted()
                .forEach(name -> System.out.println("  " + name));

        System.out.println("--------------------------------------------------------------------------------\n");
    }

    // =========================================================================
    // ZADANIE 3 — PREDICATE I FUNCTION
    // =========================================================================
    private static void demonstratePredicateAndFunction() {
        System.out.println("\n---------------- ZADANIE 3: PREDICATE I FUNCTION ----------------");

        List<Song> songs = buildSampleSongs();

        // --- Predicate ---
        System.out.println("\n--- Predicate<Song> ---");

        Predicate<Song> isHardSong = song -> song.getDifficulty() >= 8;
        Predicate<Song> isRockSong = song -> "Rock".equals(song.getGenre());
        Predicate<Song> isLongSong = song -> song.getDuration() > 250;

        System.out.println("Trudne piosenki (trudność >= 8):");
        songs.stream()
                .filter(isHardSong)
                .forEach(s -> System.out.println("  " + s.getTitle() + " [trudność: " + s.getDifficulty() + "]"));

        // Kompozycja Predicate: and()
        Predicate<Song> hardRock = isHardSong.and(isRockSong);
        System.out.println("Trudne piosenki rockowe (and):");
        songs.stream()
                .filter(hardRock)
                .forEach(s -> System.out.println("  " + s.getTitle()));

        // Kompozycja Predicate: or()
        Predicate<Song> hardOrLong = isHardSong.or(isLongSong);
        System.out.println("Trudne LUB długie piosenki (or):");
        songs.stream()
                .filter(hardOrLong)
                .forEach(s -> System.out.println("  " + s.getTitle()
                        + " [trudność: " + s.getDifficulty() + ", czas: " + s.getDuration() + "s]"));

        // Negacja Predicate: negate()
        Predicate<Song> easyOrMedium = isHardSong.negate();
        System.out.println("Łatwe i średnie piosenki (negate trudności >= 8):");
        songs.stream()
                .filter(easyOrMedium)
                .forEach(s -> System.out.println("  " + s.getTitle() + " [trudność: " + s.getDifficulty() + "]"));

        // --- Function ---
        System.out.println("\n--- Function<Song, String> ---");

        Function<Song, String> toLabel = song ->
                "\"" + song.getTitle() + "\" - " + song.getArtist();

        Function<Song, String> toDifficultyLabel = song ->
                song.getTitle() + " [" + song.getDifficulty() + "/10]";

        // Łańcuchowanie Function: andThen()
        Function<Song, String> toUpperLabel = toDifficultyLabel.andThen(String::toUpperCase);

        System.out.println("Etykiety piosenek (Function toLabel):");
        songs.stream()
                .map(toLabel)
                .forEach(label -> System.out.println("  " + label));

        System.out.println("Etykiety piosenek wersalikami (andThen toUpperCase):");
        songs.stream()
                .map(toUpperLabel)
                .forEach(label -> System.out.println("  " + label));

        // Łańcuchowanie Function: compose() — najpierw wyodrębnij gatunek, potem sformatuj
        Function<String, String> addBrackets = genre -> "[" + genre + "]";
        Function<Song, String> toGenreLabel = addBrackets.compose(Song::getGenre);

        System.out.println("Gatunek w nawiasach (compose Song::getGenre + addBrackets):");
        songs.stream()
                .map(toGenreLabel)
                .distinct()
                .forEach(label -> System.out.println("  " + label));

        // Połączenie Predicate i Function w strumieniu
        System.out.println("\nTrudne piosenki rockowe sformatowane (Predicate.and + Function.andThen):");
        songs.stream()
                .filter(isHardSong.and(isRockSong))
                .map(toUpperLabel)
                .forEach(label -> System.out.println("  " + label));

        System.out.println("--------------------------------------------------------------------------------\n");
    }

    // =========================================================================
    // DANE TESTOWE
    // =========================================================================
    private static List<Song> buildSampleSongs() {
        return List.of(
                new SongBuilder("Numb", "Linkin Park").setGenre("Rock").setDuration(187).setDifficulty(6).build(),
                new SongBuilder("Bohemian Rhapsody", "Queen").setGenre("Rock").setDuration(354).setDifficulty(9).build(),
                new SongBuilder("Shape of You", "Ed Sheeran").setGenre("Pop").setDuration(233).setDifficulty(4).build(),
                new SongBuilder("Stairway to Heaven", "Led Zeppelin").setGenre("Rock").setDuration(482).setDifficulty(8).build(),
                new SongBuilder("Blinding Lights", "The Weeknd").setGenre("Pop").setDuration(200).setDifficulty(5).build(),
                new SongBuilder("Fear of the Dark", "Iron Maiden").setGenre("Rock").setDuration(437).setDifficulty(10).build(),
                new SongBuilder("Despacito", "Luis Fonsi").setGenre("Latin").setDuration(229).setDifficulty(3).build()
        );
    }

    private static List<User> buildSampleUsers() {
        return List.of(
                new UserBuilder("Anna").setLevel(8).setPoints(1200).build(),
                new UserBuilder("Bartek").setLevel(5).setPoints(450).build(),
                new UserBuilder("Celina").setLevel(10).setPoints(3000).build(),
                new UserBuilder("Dawid").setLevel(3).setPoints(150).build(),
                new UserBuilder("Ewa").setLevel(7).setPoints(950).build(),
                new UserBuilder("Filip").setLevel(6).setPoints(700).build()
        );
    }

    private static List<Performance> buildSamplePerformances(List<Song> songs, List<User> users) {
        return List.of(
                new Performance(songs.get(0), List.of(users.get(0)), 280),
                new Performance(songs.get(1), List.of(users.get(2), users.get(4)), 420),
                new Performance(songs.get(2), List.of(users.get(1)), 195),
                new Performance(songs.get(3), List.of(users.get(0), users.get(2)), 375),
                new Performance(songs.get(4), List.of(users.get(3)), 160),
                new Performance(songs.get(5), List.of(users.get(4)), 340)
        );
    }
}
//Koniec, Tydzień 10
