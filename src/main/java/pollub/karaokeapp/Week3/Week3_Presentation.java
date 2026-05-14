package pollub.karaokeapp.Week3;

import pollub.karaokeapp.Week3.adapter.audio.AudioInputAdapter;
import pollub.karaokeapp.Week3.adapter.lyrics.LyricsFileAdapter;
import pollub.karaokeapp.Week3.adapter.scoring.LegacyScoringAdapter;
import pollub.karaokeapp.Week3.adapter.song.ExternalSongAdapter;
import pollub.karaokeapp.Week3.bridge.audio.*;
import pollub.karaokeapp.Week3.bridge.lyrics.*;
import pollub.karaokeapp.Week3.bridge.notification.*;
import pollub.karaokeapp.Week3.bridge.performance.BonusScoringImpl;
import pollub.karaokeapp.Week3.bridge.performance.PerformanceType;
import pollub.karaokeapp.Week3.bridge.performance.SoloPerformanceType;
import pollub.karaokeapp.Week3.bridge.performance.StandardScoringImpl;
import pollub.karaokeapp.Week3.composite.category.CategoryComponent;
import pollub.karaokeapp.Week3.composite.category.CategoryComposite;
import pollub.karaokeapp.Week3.composite.category.SongCategoryLeaf;
import pollub.karaokeapp.Week3.composite.equipment.EquipmentSetComposite;
import pollub.karaokeapp.Week3.composite.equipment.SingleEquipmentLeaf;
import pollub.karaokeapp.Week3.composite.performer.GroupPerformerComposite;
import pollub.karaokeapp.Week3.composite.performer.SoloPerformerLeaf;
import pollub.karaokeapp.Week3.composite.playlist.PlaylistComposite;
import pollub.karaokeapp.Week3.composite.playlist.SongLeaf;
import pollub.karaokeapp.Week3.decorator.performance.EchoEffectDecorator;
import pollub.karaokeapp.Week3.decorator.performance.PitchCorrectionDecorator;
import pollub.karaokeapp.Week3.decorator.performance.ReverbEffectDecorator;
import pollub.karaokeapp.Week3.decorator.playlist.FilteredPlaylistDecorator;
import pollub.karaokeapp.Week3.decorator.playlist.ShuffledPlaylistDecorator;
import pollub.karaokeapp.Week3.decorator.playlist.SortedPlaylistDecorator;
import pollub.karaokeapp.Week3.decorator.scoring.BonusPointsDecorator;
import pollub.karaokeapp.Week3.decorator.scoring.DifficultyMultiplierDecorator;
import pollub.karaokeapp.Week3.decorator.scoring.TimePenaltyConfig;
import pollub.karaokeapp.Week3.decorator.scoring.TimePenaltyDecorator;
import pollub.karaokeapp.Week3.decorator.user.ExperienceBoostDecorator;
import pollub.karaokeapp.Week3.decorator.user.VocalSkillDecorator;
import pollub.karaokeapp.service.song.ExternalSongAPI;
import pollub.karaokeapp.service.audio.USBMicrophone;
import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;
import pollub.karaokeapp.Week2.builder.song.SongBuilder;
import pollub.karaokeapp.Week2.builder.user.UserBuilder;
import pollub.karaokeapp.Week2.factory.song.SongFactory;
import pollub.karaokeapp.Week2.factory.user.UserFactory;
import pollub.karaokeapp.service.scoring.ScoringStrategy;
import pollub.karaokeapp.Week2.factory.scoring.ScoringStrategyFactory;
import pollub.karaokeapp.Week2.singleton.LoggerSingleton;

import java.util.List;
import java.io.IOException;

public class Week3_Presentation {

    private static final LoggerSingleton logger = LoggerSingleton.getInstance();

    public static void week3_presentation() {
        logger.log("Rozpoczęcie prezentacji wzorców z Tygodnia 3");

        System.out.println("\n=================================================================================");
        System.out.println("PREZENTACJA NOWYCH WZORCÓW PROJEKTOWYCH - TYDZIEŃ 3");
        System.out.println("Adapter, Composite, Bridge, Decorator");
        System.out.println("=================================================================================\n");

        try {
            // 1. ADAPTER - 4 implementacje
            demonstrateAdapter();

            // 2. COMPOSITE - 4 implementacje
            demonstrateComposite();

            // 3. BRIDGE - 4 implementacje
            demonstrateBridge();

            // 4. DECORATOR - 4 implementacje
            demonstrateDecorator();

            logger.log("Zakończenie prezentacji Tygodnia 3 - sukces");
        } catch (Exception e) {
            logger.log("BŁĄD krytyczny w prezentacji: " + e.getMessage());
            System.err.println("\n❌ KRYTYCZNY BŁĄD PREZENTACJI: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n=================================================================================");
        System.out.println("KONIEC PREZENTACJI TYGODNIA 3");
        System.out.println("=================================================================================");
    }

    private static void demonstrateAdapter() {
        System.out.println("\n----------------------------------- ADAPTER -----------------------------------");

        // Adapter 1: Zewnętrzne API piosenek
        demonstrateExternalSongAdapter();

        // Adapter 2: Legacy system oceniania
        demonstrateLegacyScoringAdapter();

        // Adapter 3: Pliki z tekstami
        demonstrateLyricsFileAdapter();

        // Adapter 4: Urządzenia wejściowe
        demonstrateAudioInputAdapter();

        System.out.println("\n----------------------------------------------------------------------------------------------");
    }

    private static void demonstrateExternalSongAdapter() {
        System.out.println("\n--- Adapter 1: Adaptacja zewnętrznego API piosenek ---");
        try {
            ExternalSongAPI externalAPI = new ExternalSongAPI("Bohemian Rhapsody;Queen;354;Rock;8");
            ExternalSongAdapter adapter = new ExternalSongAdapter(externalAPI);
            Song adaptedSong = adapter.getSong();
            System.out.println("Zadaptowana piosenka z API: " + adaptedSong);
            System.out.println("Surowe dane z API: " + adapter.getRawData());
        } catch (Exception e) {
            System.err.println("❌ Błąd adaptacji API piosenek: " + e.getMessage());
            logger.log("Błąd ExternalSongAdapter: " + e.getMessage());
        }
    }

    private static void demonstrateLegacyScoringAdapter() {
        System.out.println("\n--- Adapter 2: Adaptacja legacy systemu oceniania ---");
        try {
            ScoringStrategy adaptedScoring = new LegacyScoringAdapter(80, 90, 75);
            int score = adaptedScoring.calculateScore(100);
            System.out.println("Wynik z legacy system (adaptowany): " + score);
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Błąd walidacji parametrów legacy scoring: " + e.getMessage());
            logger.log("Błąd LegacyScoringAdapter: " + e.getMessage());
        }
    }

    private static void demonstrateLyricsFileAdapter() {
        System.out.println("\n--- Adapter 3: Adaptacja plików z tekstami ---");
        try {
            Song bohemian = new SongBuilder("Bohemian Rhapsody", "Queen")
                    .setGenre("Rock")
                    .setDuration(354)
                    .setDifficulty(8)
                    .build();

            Song numb = new SongBuilder("Numb", "Linkin Park")
                    .setGenre("Rock")
                    .setDuration(187)
                    .setDifficulty(6)
                    .build();

            Song espresso = new SongBuilder("Espresso", "Sabrina Carpenter")
                    .setGenre("Pop")
                    .setDuration(175)
                    .setDifficulty(2)
                    .build();

            Song nonExistent = new SongBuilder("Nie ma tej piosenki", "Nieznany")
                    .build();

            System.out.println("Próba odczytu pliku songs.txt...");
            LyricsFileAdapter lyricsAdapter = new LyricsFileAdapter("src/main/resources/songs.txt");
            System.out.println();

            displaySongLyrics(lyricsAdapter, bohemian, "Bohemian Rhapsody");
            displaySongLyrics(lyricsAdapter, numb, "Numb");
            displaySongLyrics(lyricsAdapter, espresso, "Espresso");
            displaySongLyrics(lyricsAdapter, nonExistent, "Nieistniejąca piosenka");

        } catch (IOException e) {
            System.err.println("❌ BŁĄD KRYTYCZNY: Nie można otworzyć pliku songs.txt");
            System.err.println("   Przyczyna: " + e.getMessage());
            System.err.println("   ✅ Rozwiązanie: Utwórz plik src/main/resources/songs.txt z odpowiednią strukturą");
            logger.log("IOException w LyricsFileAdapter: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("❌ Błąd podczas odczytu tekstów: " + e.getMessage());
            logger.log("RuntimeException w LyricsFileAdapter: " + e.getMessage());
        }
    }

    private static void displaySongLyrics(LyricsFileAdapter adapter, Song song, String songName) {
        try {
            System.out.println("--- Tekst piosenki: " + songName + " ---");
            String lyrics = adapter.getLyrics(song);
            System.out.println(lyrics);
            System.out.println();
        } catch (Exception e) {
            System.err.println("⚠️ Nie udało się pobrać tekstu dla: " + songName);
            System.err.println("   Powód: " + e.getMessage());
            logger.log("Brak tekstu dla: " + songName + " - " + e.getMessage());
        }
    }

    private static void demonstrateAudioInputAdapter() {
        System.out.println("\n--- Adapter 4: Adaptacja urządzeń wejściowych ---");
        try {
            USBMicrophone usbMic = new USBMicrophone();
            AudioInputAdapter audioAdapter = new AudioInputAdapter(usbMic);
            System.out.println("Informacja o urządzeniu: " + audioAdapter.getDeviceInfo());
            byte[] audioData = audioAdapter.record(2);
            System.out.println("Nagrano " + audioData.length + " bajtów audio z mikrofonu USB");
        } catch (Exception e) {
            System.err.println("❌ Błąd adaptacji urządzenia audio: " + e.getMessage());
            logger.log("Błąd AudioInputAdapter: " + e.getMessage());
        }
    }

    private static void demonstrateComposite() {
        System.out.println("\n----------------------------------- COMPOSITE -----------------------------------");

        // Composite 1: Hierarchia playlist
        demonstratePlaylistComposite();

        // Composite 2: Hierarchia uczestników
        demonstratePerformerComposite();

        // Composite 3: Hierarchia sprzętu
        demonstrateEquipmentComposite();

        // Composite 4: Hierarchia kategorii piosenek
        demonstrateCategoryComposite();

        System.out.println("\n----------------------------------------------------------------------------------------------");
    }

    private static void demonstratePlaylistComposite() {
        System.out.println("\n--- Composite 1: Hierarchia playlist ---");
        try {
            Song song1 = SongFactory.createSong("rock", "Numb", "Linkin Park", 187, 6);
            Song song2 = SongFactory.createSong("grunge", "Lithium", "Nirvana", 256, 4);
            Song song3 = SongFactory.createSong("pop", "Espresso", "Sabrina Carpenter", 175, 2);

            SongLeaf leaf1 = new SongLeaf(song1);
            SongLeaf leaf2 = new SongLeaf(song2);
            SongLeaf leaf3 = new SongLeaf(song3);

            PlaylistComposite rockPlaylist = new PlaylistComposite("Rock Classics");
            rockPlaylist.add(leaf1);
            rockPlaylist.add(leaf2);

            PlaylistComposite mainPlaylist = new PlaylistComposite("Główna playlista");
            mainPlaylist.add(rockPlaylist);
            mainPlaylist.add(leaf3);

            System.out.println("Struktura playlist:");
            mainPlaylist.display("");
            System.out.println("Łączny czas: " + mainPlaylist.getTotalDuration() + "s");

            // Bezpieczne pobieranie piosenki z obsługą wyjątku
            try {
                System.out.println("Piosenka #2: " + mainPlaylist.getSong(1).getTitle());
                System.out.println("Piosenka #99: " + mainPlaylist.getSong(99).getTitle()); // To rzuci wyjątek
            } catch (IndexOutOfBoundsException e) {
                System.err.println("⚠️ Indeks poza zakresem playlisty: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("❌ Błąd tworzenia struktury playlist: " + e.getMessage());
            logger.log("Błąd PlaylistComposite: " + e.getMessage());
        }
    }

    private static void demonstratePerformerComposite() {
        System.out.println("\n--- Composite 2: Hierarchia uczestników ---");
        try {
            User user1 = UserFactory.createUser("standard", "Anna");
            User user2 = UserFactory.createUser("premium", "Jan");
            User user3 = UserFactory.createUser("guest", "Piotr");

            SoloPerformerLeaf ania = new SoloPerformerLeaf(user1);
            SoloPerformerLeaf jan = new SoloPerformerLeaf(user2);
            SoloPerformerLeaf piotr = new SoloPerformerLeaf(user3);

            GroupPerformerComposite duet = new GroupPerformerComposite("Duet Przyjaciół");
            duet.addMember(ania);
            duet.addMember(jan);

            GroupPerformerComposite trio = new GroupPerformerComposite("Super Trio");
            trio.addMember(duet);
            trio.addMember(piotr);

            System.out.println("Struktura wykonawców:");
            trio.perform("");
            System.out.println("Łączny poziom: " + trio.getTotalLevel());
            System.out.println("Liczba członków: " + trio.getMemberCount());

            // Test bezpiecznego dostępu do użytkownika
            try {
                User retrievedUser = trio.getUser(0);
                System.out.println("Pierwszy użytkownik: " + retrievedUser.getNickname());

                User invalidUser = trio.getUser(99); // To rzuci wyjątek
            } catch (IndexOutOfBoundsException e) {
                System.err.println("⚠️ Nieprawidłowy indeks wykonawcy: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("❌ Błąd tworzenia struktury wykonawców: " + e.getMessage());
            logger.log("Błąd PerformerComposite: " + e.getMessage());
        }
    }

    private static void demonstrateEquipmentComposite() {
        System.out.println("\n--- Composite 3: Hierarchia sprzętu ---");
        try {
            SingleEquipmentLeaf mikrofon = new SingleEquipmentLeaf("Mikrofon Shure", 450.0, 10);
            SingleEquipmentLeaf kolumna = new SingleEquipmentLeaf("Kolumna JBL", 1200.0, 200);
            SingleEquipmentLeaf mikser = new SingleEquipmentLeaf("Mikser Behringer", 800.0, 50);

            EquipmentSetComposite zestawPodstawowy = new EquipmentSetComposite("Zestaw podstawowy");
            zestawPodstawowy.addItem(mikrofon);
            zestawPodstawowy.addItem(kolumna);

            EquipmentSetComposite zestawProfesjonalny = new EquipmentSetComposite("Zestaw profesjonalny");
            zestawProfesjonalny.addItem(zestawPodstawowy);
            zestawProfesjonalny.addItem(mikser);

            System.out.println("Zestaw: " + zestawProfesjonalny.getName());
            System.out.println("Cena całkowita: " + zestawProfesjonalny.getPrice() + " zł");
            System.out.println("Pobór mocy: " + zestawProfesjonalny.getWattage() + "W");
            zestawProfesjonalny.turnOn("");

        } catch (Exception e) {
            System.err.println("❌ Błąd konfiguracji sprzętu: " + e.getMessage());
            logger.log("Błąd EquipmentComposite: " + e.getMessage());
        }
    }

    private static void demonstrateCategoryComposite() {
        System.out.println("\n--- Composite 4: Hierarchia kategorii piosenek ---");
        try {
            Song song1 = SongFactory.createSong("rock", "Numb", "Linkin Park", 187, 6);
            Song song2 = SongFactory.createSong("grunge", "Lithium", "Nirvana", 256, 4);
            Song song3 = SongFactory.createSong("pop", "Espresso", "Sabrina Carpenter", 175, 2);

            SongCategoryLeaf rockLeaf = new SongCategoryLeaf("Rock Klasyczny");
            rockLeaf.addSong(song1);
            rockLeaf.addSong(song2);

            SongCategoryLeaf metalLeaf = new SongCategoryLeaf("Metal");
            metalLeaf.addSong(SongFactory.createSong("rock", "Master of Puppets", "Metallica", 515, 9));

            SongCategoryLeaf popLeaf = new SongCategoryLeaf("Pop");
            popLeaf.addSong(song3);

            CategoryComposite rockKategoria = new CategoryComposite("Rock");
            rockKategoria.addSubcategory(rockLeaf);
            rockKategoria.addSubcategory(metalLeaf);

            CategoryComposite muzykaKategoria = new CategoryComposite("Muzyka");
            muzykaKategoria.addSubcategory(rockKategoria);
            muzykaKategoria.addSubcategory(popLeaf);

            System.out.println("Struktura kategorii:");
            muzykaKategoria.printStructure("");

            // Test wyszukiwania kategorii
            try {
                CategoryComponent found = muzykaKategoria.findCategory("Muzyka/Rock/Metal");
                if (found != null) {
                    System.out.println("✅ Znaleziono kategorię: " + found.getName());
                } else {
                    System.out.println("⚠️ Nie znaleziono kategorii");
                }

                CategoryComponent notFound = muzykaKategoria.findCategory("Nieistniejąca/Ścieżka");
                if (notFound == null) {
                    System.out.println("✅ Prawidłowo: nie znaleziono nieistniejącej kategorii");
                }
            } catch (Exception e) {
                System.err.println("⚠️ Błąd wyszukiwania kategorii: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("❌ Błąd tworzenia struktury kategorii: " + e.getMessage());
            logger.log("Błąd CategoryComposite: " + e.getMessage());
        }
    }

    private static void demonstrateBridge() {
        System.out.println("\n----------------------------------- BRIDGE -----------------------------------");

        // Bridge 1: Typ występu i system oceny
        demonstratePerformanceBridge();

        // Bridge 2: Odtwarzacz i źródło dźwięku
        demonstrateAudioBridge();

        // Bridge 3: Wyświetlanie tekstów i źródło
        demonstrateLyricsBridge();

        // Bridge 4: Powiadomienia i kanały
        demonstrateNotificationBridge();

        System.out.println("\n----------------------------------------------------------------------------------------------");
    }

    private static void demonstratePerformanceBridge() {
        System.out.println("\n--- Bridge 1: PerformanceType + ScoringImplementation ---");
        try {
            Song song = new SongBuilder("Bohemian Rhapsody", "Queen")
                    .setGenre("Rock")
                    .setDuration(354)
                    .setDifficulty(8)
                    .build();
            User user = new UserBuilder("Freddie")
                    .setLevel(10)
                    .setPoints(1000)
                    .build();

            PerformanceType soloStandard = new SoloPerformanceType(new StandardScoringImpl());
            PerformanceType soloBonus = new SoloPerformanceType(new BonusScoringImpl());

            Performance perf1 = soloStandard.createPerformance(song, List.of(user));
            Performance perf2 = soloBonus.createPerformance(song, List.of(user));

            System.out.println(soloStandard.getTypeDescription() + " -> wynik: " + perf1.getScore());
            System.out.println(soloBonus.getTypeDescription() + " -> wynik: " + perf2.getScore());

            // Test nieprawidłowej liczby uczestników
            try {
                PerformanceType invalidDuet = new SoloPerformanceType(new StandardScoringImpl());
                Performance invalidPerf = invalidDuet.createPerformance(song, List.of(user, user)); // 2 uczestników zamiast 1
            } catch (IllegalArgumentException e) {
                System.err.println("⚠️ Prawidłowo złapano błąd: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("❌ Błąd w Performance Bridge: " + e.getMessage());
            logger.log("Błąd PerformanceBridge: " + e.getMessage());
        }
    }

    private static void demonstrateAudioBridge() {
        System.out.println("\n--- Bridge 2: AudioPlayer + AudioSource ---");
        try {
            FileAudioSource fileSource = new FileAudioSource("song.mp3", 240);
            MicrophoneAudioSource micSource = new MicrophoneAudioSource("Mikrofon USB");

            AudioPlayer mp3Player = new MP3Player(fileSource);
            AudioPlayer streamingPlayer = new StreamingPlayer(micSource, "rtmp://live.karaoke.com/stream");

            mp3Player.play();
            mp3Player.setVolume(75);
            streamingPlayer.play();

            // Test nieprawidłowej głośności
            try {
                mp3Player.setVolume(150); // Powinno zostać zclampowane do 100
                System.out.println("✅ Głośność 150 została zautomatically ograniczona");
            } catch (Exception e) {
                System.err.println("⚠️ Problem z ustawianiem głośności: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("❌ Błąd w Audio Bridge: " + e.getMessage());
            logger.log("Błąd AudioBridge: " + e.getMessage());
        }
    }

    private static void demonstrateLyricsBridge() {
        System.out.println("\n--- Bridge 3: LyricsDisplay + LyricsSource ---");
        try {
            Song song = new SongBuilder("Bohemian Rhapsody", "Queen")
                    .setGenre("Rock")
                    .setDuration(354)
                    .setDifficulty(8)
                    .build();

            FileLyricsSource fileLyrics = new FileLyricsSource("bohemian.txt");
            APILyricsSource apiLyrics = new APILyricsSource("https://api.lyrics.ovh");

            LyricsDisplay scrollingDisplay = new ScrollingLyricsDisplay(fileLyrics, 3);
            LyricsDisplay karaokeDisplay = new KaraokeStyleDisplay(apiLyrics, true, "black");

            scrollingDisplay.showLyrics(song);
            karaokeDisplay.showLyrics(song);

            // Test highlightowania
            try {
                scrollingDisplay.highlightLine(2);
                karaokeDisplay.highlightLine(-1); // Nieprawidłowy numer linii
            } catch (Exception e) {
                System.err.println("⚠️ Błąd podświetlania linii: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("❌ Błąd w Lyrics Bridge: " + e.getMessage());
            logger.log("Błąd LyricsBridge: " + e.getMessage());
        }
    }

    private static void demonstrateNotificationBridge() {
        System.out.println("\n--- Bridge 4: Notification + NotificationChannel ---");
        try {
            Song song = new SongBuilder("Bohemian Rhapsody", "Queen")
                    .setGenre("Rock")
                    .setDuration(354)
                    .setDifficulty(8)
                    .build();
            User user = new UserBuilder("Freddie")
                    .setLevel(10)
                    .setPoints(1000)
                    .build();

            EmailChannel emailChannel = new EmailChannel("smtp.karaoke.com");
            SMSChannel smsChannel = new SMSChannel("+48123456789", 5);

            Notification inviteNotification = new PerformanceInviteNotification(emailChannel, song.getTitle(), "20:00");
            Notification scoreNotification = new ScoreUpdateNotification(smsChannel, 950, 3);

            inviteNotification.send("Przygotuj się!", user);
            scoreNotification.send("Świetny występ!", user);

            // Test niedostępnego kanału SMS
            try {
                SMSChannel emptySmsChannel = new SMSChannel("+48999999999", 0);
                Notification failedNotification = new ScoreUpdateNotification(emptySmsChannel, 100, 1);
                failedNotification.send("Test", user);
            } catch (RuntimeException e) {
                System.err.println("⚠️ Prawidłowo złapano błąd niedostępnego kanału: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("❌ Błąd w Notification Bridge: " + e.getMessage());
            logger.log("Błąd NotificationBridge: " + e.getMessage());
        }
    }

    private static void demonstrateDecorator() {
        System.out.println("\n----------------------------------- DECORATOR -----------------------------------");

        // Decorator 1: Dekoratory dla występów
        demonstratePerformanceDecorator();

        // Decorator 2: Dekoratory dla playlist
        demonstratePlaylistDecorator();

        // Decorator 3: Dekoratory dla użytkowników
        demonstrateUserDecorator();

        // Decorator 4: Dekoratory dla systemów punktacji
        demonstrateScoringDecorator();

        System.out.println("\n----------------------------------------------------------------------------------------------");
    }

    private static void demonstratePerformanceDecorator() {
        System.out.println("\n--- Decorator 1: Performance Decorators (efekty dźwiękowe) ---");
        try {
            Song song = new SongBuilder("Somewhere Over the Rainbow", "Israel Kamakawiwo'ole")
                    .setGenre("Pop")
                    .setDuration(225)
                    .setDifficulty(3)
                    .build();
            User user = new UserBuilder("Izzy")
                    .setLevel(5)
                    .setPoints(200)
                    .build();

            Performance basicPerformance = new Performance(song, List.of(user), 80);
            System.out.println("Podstawowy: " + basicPerformance + " -> wynik: " + basicPerformance.getScore());

            Performance withEcho = new EchoEffectDecorator(basicPerformance, 200, 0.5f);
            System.out.println("Z echem: " + withEcho + " -> wynik: " + withEcho.getScore());

            Performance withReverb = new ReverbEffectDecorator(basicPerformance, "hall", 1500);
            System.out.println("Z pogłosem: " + withReverb + " -> wynik: " + withReverb.getScore());

            Performance withAutoTune = new PitchCorrectionDecorator(basicPerformance, 7, "C");
            System.out.println("Z auto-tune: " + withAutoTune + " -> wynik: " + withAutoTune.getScore());

            // Test nieprawidłowych parametrów dekoratorów
            try {
                Performance invalidEcho = new EchoEffectDecorator(basicPerformance, -100, 0.5f);
            } catch (IllegalArgumentException e) {
                System.err.println("⚠️ Prawidłowo złapano błąd echa: " + e.getMessage());
            }

            try {
                Performance invalidPitch = new PitchCorrectionDecorator(basicPerformance, 15, "C");
            } catch (IllegalArgumentException e) {
                System.err.println("⚠️ Prawidłowo złapano błąd korekcji tonu: " + e.getMessage());
            }

            try {
                Performance invalidReverb = new ReverbEffectDecorator(basicPerformance, null, 500);
            } catch (IllegalArgumentException e) {
                System.err.println("⚠️ Prawidłowo złapano błąd pogłosu: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("❌ Błąd w Performance Decorator: " + e.getMessage());
            logger.log("Błąd PerformanceDecorator: " + e.getMessage());
        }
    }

    private static void demonstratePlaylistDecorator() {
        System.out.println("\n--- Decorator 2: Playlist Decorators (filtry, sortowanie) ---");
        try {
            Playlist playlist = new Playlist("Moja playlista");
            playlist.addSong(new SongBuilder("Numb", "Linkin Park").setGenre("Rock").setDuration(187).setDifficulty(6).build());
            playlist.addSong(new SongBuilder("Lithium", "Nirvana").setGenre("Grunge").setDuration(256).setDifficulty(4).build());
            playlist.addSong(new SongBuilder("Espresso", "Sabrina Carpenter").setGenre("Pop").setDuration(175).setDifficulty(2).build());

            System.out.println("Oryginalna playlista: " + playlist.getSongs().size() + " piosenek");

            Playlist filtered = new FilteredPlaylistDecorator(playlist, "Pop");
            System.out.println("Po filtrze (Pop): " + filtered.getSongs().size() + " piosenek");

            Playlist sorted = new SortedPlaylistDecorator(playlist, "duration");
            System.out.println("Posortowana wg czasu:");
            sorted.getSongs().forEach(s -> System.out.println("  - " + s.getTitle() + " (" + s.getDuration() + "s)"));

            Playlist shuffled = new ShuffledPlaylistDecorator(playlist, 12345);
            if (!shuffled.getSongs().isEmpty()) {
                System.out.println("Wymieszana (pierwsza piosenka): " + shuffled.getSongs().get(0).getTitle());
            }

            // Test nieprawidłowego klucza sortowania
            try {
                Playlist invalidSort = new SortedPlaylistDecorator(playlist, "invalid_key");
            } catch (IllegalArgumentException e) {
                System.err.println("⚠️ Prawidłowo złapano błąd sortowania: " + e.getMessage());
            }

            // Test filtra z null
            try {
                Playlist invalidFilter = new FilteredPlaylistDecorator(playlist, null);
            } catch (IllegalArgumentException e) {
                System.err.println("⚠️ Prawidłowo złapano błąd filtra: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("❌ Błąd w Playlist Decorator: " + e.getMessage());
            logger.log("Błąd PlaylistDecorator: " + e.getMessage());
        }
    }

    private static void demonstrateUserDecorator() {
        System.out.println("\n--- Decorator 3: User Decorators (umiejętności, bonusy) ---");
        try {
            User basicUser = new UserBuilder("Kasia")
                    .setLevel(3)
                    .setPoints(150)
                    .build();
            System.out.println("Podstawowy: " + basicUser + " -> poziom: " + basicUser.getLevel());

            User withVocal = new VocalSkillDecorator(basicUser, 8, true);
            System.out.println("Z umiejętnościami wokalnymi: " + withVocal + " -> poziom: " + withVocal.getLevel());

            User userBonus = new ExperienceBoostDecorator(basicUser, 1.5, "Weekendowy bonus");
            System.out.println("Z bonusem: " + userBonus + " -> punkty: " + userBonus.getPoints());

            // Test nieprawidłowego zakresu wokalnego
            try {
                User invalidVocal = new VocalSkillDecorator(basicUser, 15, true);
            } catch (IllegalArgumentException e) {
                System.err.println("⚠️ Prawidłowo złapano błąd zakresu wokalnego: " + e.getMessage());
            }

            // Test nieprawidłowego mnożnika bonusu
            try {
                User invalidBoost = new ExperienceBoostDecorator(basicUser, -1.0, "Test");
            } catch (IllegalArgumentException e) {
                System.err.println("⚠️ Prawidłowo złapano błąd mnożnika bonusu: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("❌ Błąd w User Decorator: " + e.getMessage());
            logger.log("Błąd UserDecorator: " + e.getMessage());
        }
    }

    private static void demonstrateScoringDecorator() {
        System.out.println("\n--- Decorator 4: Scoring Decorators (bonusy, kary, mnożniki) ---");
        try {
            ScoringStrategy baseStrategy = ScoringStrategyFactory.createStrategy("pro");
            int baseScore = 100;

            System.out.println("Bazowy wynik: " + baseStrategy.calculateScore(baseScore));

            ScoringStrategy withBonus = new BonusPointsDecorator(baseStrategy, 50, "Publiczność");
            System.out.println("Z bonusem: " + withBonus.calculateScore(baseScore));

            TimePenaltyConfig penaltyConfig = new TimePenaltyConfig(180, 200, 2);
            ScoringStrategy withPenalty = new TimePenaltyDecorator(baseStrategy, penaltyConfig);
            System.out.println("Z karą czasową: " + withPenalty.calculateScore(baseScore));

            ScoringStrategy withMultiplier = new DifficultyMultiplierDecorator(baseStrategy, 8, 3);
            System.out.println("Z mnożnikiem trudności: " + withMultiplier.calculateScore(baseScore));

            // Łączenie dekoratorów
            TimePenaltyConfig penaltyConfig2 = new TimePenaltyConfig(180, 200, 2);
            ScoringStrategy allDecorators = new BonusPointsDecorator(
                    new TimePenaltyDecorator(
                            new DifficultyMultiplierDecorator(baseStrategy, 8, 3),
                            penaltyConfig2),
                    50, "Publiczność");
            System.out.println("Wszystkie dekoratory: " + allDecorators.calculateScore(baseScore));

            // Test nieprawidłowej konfiguracji kary czasowej
            try {
                TimePenaltyConfig invalidConfig = new TimePenaltyConfig(-10, 200, 2);
                invalidConfig.validate();
            } catch (IllegalArgumentException e) {
                System.err.println("⚠️ Prawidłowo złapano błąd konfiguracji kary: " + e.getMessage());
            }

            // Test nieprawidłowej trudności
            try {
                ScoringStrategy invalidMultiplier = new DifficultyMultiplierDecorator(baseStrategy, 15, 3);
            } catch (IllegalArgumentException e) {
                System.err.println("⚠️ Prawidłowo złapano błąd trudności piosenki: " + e.getMessage());
            }

            // Test dekoratora z null
            try {
                ScoringStrategy nullDecorator = new BonusPointsDecorator(null, 50, "Test");
            } catch (IllegalArgumentException e) {
                System.err.println("⚠️ Prawidłowo złapano błąd null strategii: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("❌ Błąd w Scoring Decorator: " + e.getMessage());
            logger.log("Błąd ScoringDecorator: " + e.getMessage());
        }
    }
}