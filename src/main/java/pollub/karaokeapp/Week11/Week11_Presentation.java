package pollub.karaokeapp.Week11;

import pollub.karaokeapp.Week2.builder.song.SongBuilder;
import pollub.karaokeapp.Week2.builder.user.UserBuilder;
import pollub.karaokeapp.Week2.builder.performance.PerformanceBuilder;
import pollub.karaokeapp.Week2.singleton.LoggerSingleton;
import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;
import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.Week4.facade.ScoringSystemFacade;

import java.util.Arrays;
import java.util.List;

/**
 * Tydzień 11, Demonstracja AspectJ
 * Prezentacja czterech aspektów (LoggingAspect, PerformanceMonitoringAspect,
 * ValidationAspect, CachingAspect) pokazujących programowanie zorientowane na aspekty w Java.
 * AspectJ umożliwia ingerencję w logikę biznesową bez jej modyfikacji.
 */
public class Week11_Presentation {

    public static void week11_presentation() {
        LoggerSingleton logger = LoggerSingleton.getInstance();

        System.out.println("\n================================================================================");
        System.out.println("PREZENTACJA PROGRAMOWANIA ASPEKTOWEGO - TYDZIEŃ 11");
        System.out.println("AspectJ: Logging, Performance Monitoring, Validation, Caching");
        System.out.println("================================================================================\n");

        try {
            demonstrateLoggingAspect();
            demonstratePerformanceMonitoringAspect();
            demonstrateValidationAspect();
            demonstrateCachingAspect();
            logger.log("Zakończenie prezentacji Tygodnia 11 - sukces");
        } catch (Exception e) {
            logger.log("Wystąpił błąd: " + e.getMessage());
            System.err.println("\nBłąd" + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n================================================================================");
        System.out.println("KONIEC PREZENTACJI TYGODNIA 11");
        System.out.println("================================================================================");
    }

    // =========================================================================
    // ASPECT 1: LOGGING
    // =========================================================================
    private static void demonstrateLoggingAspect() {
        System.out.println("\n----------------------------------- ASPECT 1: LOGGING -----------------------------------");

        LoggerSingleton logger = LoggerSingleton.getInstance();
        logger.log("Demonstracja LoggingAspect - automatyczne logowanie operacji");

        try {
            // Test 1: Tworzenie piosenek (loguje Before, AfterReturning)
            System.out.println("\n--- Test 1: Tworzenie piosenek ---");
            Song song1 = new SongBuilder("Bohemian Rhapsody", "Queen")
                    .setGenre("Rock")
                    .setDuration(354)
                    .setDifficulty(8)
                    .build();
            System.out.println("✓ Piosenka utworzona: " + song1.getTitle());

            Song song2 = new SongBuilder("Imagine", "John Lennon")
                    .setGenre("Pop")
                    .setDuration(183)
                    .setDifficulty(5)
                    .build();
            System.out.println("✓ Piosenka utworzona: " + song2.getTitle());

            // Test 2: Tworzenie użytkowników
            System.out.println("\n--- Test 2: Tworzenie użytkowników ---");
            User user1 = new UserBuilder("Rockowy Jan")
                    .setLevel(7)
                    .setPoints(500)
                    .build();
            System.out.println("✓ Użytkownik utworzony: " + user1.getNickname());

            User user2 = new UserBuilder("Pop Maria")
                    .setLevel(6)
                    .setPoints(400)
                    .build();
            System.out.println("✓ Użytkownik utworzony: " + user2.getNickname());

            // Test 3: Tworzenie wykonania (loguje wszystkie operacje)
            System.out.println("\n--- Test 3: Tworzenie wykonania ---");
            Performance perf = new PerformanceBuilder(song1)
                    .addParticipant(user1)
                    .setScore(350)
                    .build();
            System.out.println("✓ Wykonanie utworzone");

            // Test 4: Logowanie błędów (AfterThrowing)
            System.out.println("\n--- Test 4: Obsługa błędów ---");
            try {
                User invalidUser = new UserBuilder("Invalid")
                        .setLevel(15)  // Błąd walidacji - poza zakresem
                        .build();
            } catch (Exception e) {
                System.out.println("✓ Błąd poprawnie przechwycony i zaloggowany");
            }

        } catch (Exception e) {
            System.err.println("❌ Błąd w demonstracji LoggingAspect: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n----------------------------------------------------------------------------------------------");
    }

    // =========================================================================
    // ASPECT 2: PERFORMANCE MONITORING
    // =========================================================================
    private static void demonstratePerformanceMonitoringAspect() {
        System.out.println("\n----------------------------------- ASPECT 2: PERFORMANCE MONITORING -----------------------------------");

        LoggerSingleton logger = LoggerSingleton.getInstance();
        logger.log("Demonstracja PerformanceMonitoringAspect - mierzenie wydajności operacji");

        try {
            // Test 1: Operacje szybkie (< 100ms)
            System.out.println("\n--- Test 1: Operacje szybkie ---");
            Song quickSong = new SongBuilder("Fast Song", "Quick Artist")
                    .setGenre("Pop")
                    .setDuration(180)
                    .setDifficulty(3)
                    .build();
            System.out.println("✓ Szybka operacja: " + quickSong.getTitle());

            // Test 2: Operacje zwykle (< 100ms)
            System.out.println("\n--- Test 2: Operacje standardowe ---");
            Song standardSong = new SongBuilder("Standard Song", "Standard Artist")
                    .setGenre("Rock")
                    .setDuration(240)
                    .setDifficulty(6)
                    .build();
            System.out.println("✓ Standardowa operacja: " + standardSong.getTitle());

            // Test 3: Operacje na kolekcjach (mogą być wolne)
            System.out.println("\n--- Test 3: Operacje na kolekcjach ---");
            Playlist playlist = new Playlist("Performance Playlist");
            for (int i = 0; i < 5; i++) {
                Song s = new SongBuilder("Song " + i, "Artist " + i)
                        .setGenre("Mix")
                        .setDuration(180 + i * 10)
                        .setDifficulty(i + 1)
                        .build();
                playlist.addSong(s);
            }
            System.out.println("✓ Playlista z 5 piosenkami utworzona");

            // Test 4: Operacje na użytkownikach z różnymi parametrami
            System.out.println("\n--- Test 4: Monitorowanie operacji użytkownika ---");
            User perfUser = new UserBuilder("Performance Tester")
                    .setLevel(5)
                    .setPoints(250)
                    .build();
            perfUser.setLevel(7);
            perfUser.setPoints(500);
            System.out.println("✓ Użytkownik monitorowany: " + perfUser.getNickname());

        } catch (Exception e) {
            System.err.println("Błąd w demonstracji PerformanceMonitoringAspect: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n----------------------------------------------------------------------------------------------");
    }

    // =========================================================================
    // ASPECT 3: VALIDATION
    // =========================================================================
    private static void demonstrateValidationAspect() {
        System.out.println("\n----------------------------------- ASPECT 3: VALIDATION -----------------------------------");

        LoggerSingleton logger = LoggerSingleton.getInstance();
        logger.log("Demonstracja ValidationAspect - automatyczna walidacja danych");

        try {
            // Test 1: Prawidłowe dane
            System.out.println("\n--- Test 1: Walidacja poprawnych danych ---");
            User validUser = new UserBuilder("Valid User")
                    .setLevel(5)
                    .setPoints(250)
                    .build();
            System.out.println("Użytkownik z prawidłowymi danymi: " + validUser.getNickname());

            // Test 2: Walidacja stringów
            System.out.println("\n--- Test 2: Walidacja tytułu piosenki ---");
            Song validSong = new SongBuilder("Walidowana Piosenka", "Artysta")
                    .setGenre("Pop")
                    .setDuration(200)
                    .setDifficulty(5)
                    .build();
            validSong.setTitle("Zmieniony Tytuł");
            System.out.println("Tytuł piosenki zmieniony: " + validSong.getTitle());

            // Test 3: Walidacja zakresu poziomu (0-10)
            System.out.println("\n--- Test 3: Walidacja zakresu poziomu (0-10) ---");
            User levelUser = new UserBuilder("Level Tester")
                    .setLevel(5)
                    .build();
            levelUser.setLevel(10);  // Max - OK
            System.out.println("Poziom 10 (maksimum) - poprawny");

            levelUser.setLevel(0);   // Min - OK
            System.out.println("Poziom 0 (minimum) - poprawny");

            // Test 4: Walidacja punktów (≥ 0)
            System.out.println("\n--- Test 4: Walidacja punktów (≥ 0) ---");
            User pointsUser = new UserBuilder("Points Tester")
                    .setPoints(0)
                    .build();
            pointsUser.setPoints(1000);
            System.out.println("Punkty 1000 - poprawne");

            // Test 5: Walidacja czasu trwania (> 0)
            System.out.println("\n--- Test 5: Walidacja czasu trwania (> 0) ---");
            Song durationSong = new SongBuilder("Duration Test", "Artist")
                    .setDuration(180)
                    .build();
            durationSong.setDuration(300);
            System.out.println("Czas trwania 300s - poprawny");

            // Test 6: Walidacja trudności (1-10)
            System.out.println("\n--- Test 6: Walidacja trudności (1-10) ---");
            Song difficultySong = new SongBuilder("Difficulty Test", "Artist")
                    .setDifficulty(5)
                    .build();
            difficultySong.setDifficulty(10);  // Max - OK
            System.out.println("Trudność 10 (maksimum) - poprawna");

            difficultySong.setDifficulty(1);   // Min - OK
            System.out.println("Trudność 1 (minimum) - poprawna");

            // Test 7: Obsługa błędnych danych
            System.out.println("\n--- Test 7: Obsługa walidacji błędnych danych ---");

            // Błąd 1: Poziom poza zakresem
            try {
                User errorUser = new UserBuilder("Error Test")
                        .setLevel(15)  // Błąd: > 10
                        .build();
                errorUser.setLevel(12);
                System.out.println("BŁĄD: Powinien być zgłoszony wyjątek");
            } catch (IllegalArgumentException e) {
                System.out.println("Poziom 15 odrzucony: " + e.getMessage());
            }

            // Błąd 2: Punkty ujemne
            try {
                User errorUser2 = new UserBuilder("Error Test 2")
                        .setPoints(-100)  // Błąd: ujemne
                        .build();
                System.out.println("BŁĄD: Powinien być zgłoszony wyjątek");
            } catch (IllegalArgumentException e) {
                System.out.println("Punkty -100 odrzucone: " + e.getMessage());
            }

            // Błąd 3: Czas trwania = 0
            try {
                Song errorSong = new SongBuilder("Error Song", "Artist")
                        .setDuration(0)  // Błąd: nie > 0
                        .build();
                System.out.println("BŁĄD: Powinien być zgłoszony wyjątek");
            } catch (IllegalArgumentException e) {
                System.out.println("Czas 0 odrzucony: " + e.getMessage());
            }

            // Błąd 4: Trudność poza zakresem
            try {
                Song errorSong2 = new SongBuilder("Error Song 2", "Artist")
                        .setDifficulty(11)  // Błąd: > 10
                        .build();
                System.out.println("BŁĄD: Powinien być zgłoszony wyjątek");
            } catch (IllegalArgumentException e) {
                System.out.println("Trudność 11 odrzucona: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("BŁĄD: Powinien być zgłoszony wyjątek" + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n----------------------------------------------------------------------------------------------");
    }

    // =========================================================================
    // ASPECT 4: CACHING
    // =========================================================================
    private static void demonstrateCachingAspect() {
        System.out.println("\n----------------------------------- ASPECT 4: CACHING -----------------------------------");

        LoggerSingleton logger = LoggerSingleton.getInstance();
        logger.log("Demonstracja CachingAspect - cache'owanie kosztownych obliczeń");

        try {
            // Przygotowanie przykładowych obiektów
            Song cachedSong = new SongBuilder("Cached Song", "Cache Artist")
                    .setGenre("Pop")
                    .setDuration(210)
                    .setDifficulty(5)
                    .build();

            User singer = new UserBuilder("Cache Singer")
                    .setLevel(6)
                    .setPoints(200)
                    .build();

            Performance performance = new PerformanceBuilder(cachedSong)
                    .addParticipant(singer)
                    .setScore(0)
                    .build();

            ScoringSystemFacade scoring = new ScoringSystemFacade();

            // Symulacja "ciężkiego" audio (tablica bajtów)
            byte[] audioSample = new byte[2000];
            for (int i = 0; i < audioSample.length; i++) {
                audioSample[i] = (byte)(i % 127);
            }

            // Pierwsze wywołanie - obliczane i zapisywane do cache
            System.out.println("\n--- Test 1: Pierwsze wywołanie - powinno obliczyć wynik i zapisać do cache ---");
            int score1 = scoring.calculatePerformanceScore(performance, audioSample);
            System.out.println("Wynik (pierwsze): " + score1);

            // Drugie wywołanie z tymi samymi parametrami - powinien być HIT w cache
            System.out.println("\n--- Test 2: Drugie wywołanie z tymi samymi parametrami - powinien być CACHE HIT ---");
            int score2 = scoring.calculatePerformanceScore(performance, audioSample);
            System.out.println("Wynik (drugie): " + score2);

            if (score1 == score2) {
                System.out.println("Cache działa: wynik drugi równy pierwszemu (HIT)");
            } else {
                System.out.println("Wyniki różne — możliwe że cache nie działa lub metoda generuje losowy wynik");
            }

            // Test z innym audio - powinno być put do cache
            System.out.println("\n--- Test 3: Wywołanie z innym audio - nowy wpis w cache ---");
            byte[] audioSample2 = Arrays.copyOf(audioSample, audioSample.length);
            audioSample2[0] = (byte)(audioSample2[0] + 1); // niewielka zmiana -> inny klucz
            int score3 = scoring.calculatePerformanceScore(performance, audioSample2);
            System.out.println("Wynik (inny audio): " + score3);

            logger.log("Demonstracja caching zakończona");

        } catch (Exception e) {
            System.err.println("❌ Błąd w demonstracji CachingAspect: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n----------------------------------------------------------------------------------------------");
    }
}
// Koniec, Tydzień 11, Demonstracja AspectJ
