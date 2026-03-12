package pollub.karaokeapp.app;

import pollub.karaokeapp.adapter.audio.AudioInputAdapter;
import pollub.karaokeapp.adapter.lyrics.LyricsFileAdapter;
import pollub.karaokeapp.adapter.scoring.LegacyScoringAdapter;
import pollub.karaokeapp.adapter.song.ExternalSongAdapter;
import pollub.karaokeapp.bridge.audio.*;
import pollub.karaokeapp.bridge.lyrics.*;
import pollub.karaokeapp.bridge.notification.*;
import pollub.karaokeapp.bridge.performance.*;
import pollub.karaokeapp.composite.category.CategoryComposite;
import pollub.karaokeapp.composite.category.SongCategoryLeaf;
import pollub.karaokeapp.composite.equipment.EquipmentSetComposite;
import pollub.karaokeapp.composite.equipment.SingleEquipmentLeaf;
import pollub.karaokeapp.composite.performer.GroupPerformerComposite;
import pollub.karaokeapp.composite.performer.SoloPerformerLeaf;
import pollub.karaokeapp.composite.playlist.PlaylistComposite;
import pollub.karaokeapp.composite.playlist.SongLeaf;
import pollub.karaokeapp.decorator.performance.EchoEffectDecorator;
import pollub.karaokeapp.decorator.performance.PitchCorrectionDecorator;
import pollub.karaokeapp.decorator.performance.ReverbEffectDecorator;
import pollub.karaokeapp.decorator.playlist.FilteredPlaylistDecorator;
import pollub.karaokeapp.decorator.playlist.ShuffledPlaylistDecorator;
import pollub.karaokeapp.decorator.playlist.SortedPlaylistDecorator;
import pollub.karaokeapp.decorator.scoring.BonusPointsDecorator;
import pollub.karaokeapp.decorator.scoring.DifficultyMultiplierDecorator;
import pollub.karaokeapp.decorator.scoring.TimePenaltyDecorator;
import pollub.karaokeapp.decorator.user.ExperienceBoostDecorator;
import pollub.karaokeapp.decorator.user.VocalSkillDecorator;
import pollub.karaokeapp.external.ExternalSongAPI;
import pollub.karaokeapp.external.LegacyScoringSystem;
import pollub.karaokeapp.external.USBMicrophone;
import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.playlist.Playlist;
import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;
import pollub.karaokeapp.builder.song.SongBuilder;
import pollub.karaokeapp.builder.user.UserBuilder;
import pollub.karaokeapp.factory.song.SongFactory;
import pollub.karaokeapp.factory.user.UserFactory;
import pollub.karaokeapp.service.scoring.ScoringStrategy;
import pollub.karaokeapp.factory.scoring.ScoringStrategyFactory;
import pollub.karaokeapp.singleton.LoggerSingleton;

import java.util.List;

public class Week3_Presentation {

    public static void week3_presentation() {
        LoggerSingleton logger = LoggerSingleton.getInstance();
        logger.log("Rozpoczęcie prezentacji wzorców z Tygodnia 3");

        System.out.println("\n=================================================================================");
        System.out.println("PREZENTACJA NOWYCH WZORCÓW PROJEKTOWYCH - TYDZIEŃ 3");
        System.out.println("Adapter, Composite, Bridge, Decorator");
        System.out.println("=================================================================================\n");

        // 1. ADAPTER - 4 implementacje
        demonstrateAdapter();

        // 2. COMPOSITE - 4 implementacje
        demonstrateComposite();

        // 3. BRIDGE - 4 implementacje
        demonstrateBridge();

        // 4. DECORATOR - 4 implementacje
        demonstrateDecorator();

        logger.log("Zakończenie prezentacji Tygodnia 3");
    }

    private static void demonstrateAdapter() {
        System.out.println("\n----------------------------------- ADAPTER -----------------------------------");

        // Adapter 1: Zewnętrzne API piosenek
        System.out.println("\n--- Adapter 1: Adaptacja zewnętrznego API piosenek ---");
        ExternalSongAPI externalAPI = new ExternalSongAPI("Bohemian Rhapsody;Queen;354;Rock;8");
        Song adaptedSong = new ExternalSongAdapter(externalAPI);
        System.out.println("Zadaptowana piosenka z API: " + adaptedSong);

        // Adapter 2: Legacy system oceniania
        System.out.println("\n--- Adapter 2: Adaptacja legacy systemu oceniania ---");
        LegacyScoringSystem legacySystem = new LegacyScoringSystem();
        ScoringStrategy adaptedScoring = new LegacyScoringAdapter(legacySystem, 80, 90, 75);
        int score = adaptedScoring.calculateScore(100);
        System.out.println("Wynik z legacy system (adaptowany): " + score);

        // Adapter 3: Pliki z tekstami
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

            System.out.println("--- Tekst piosenki: Bohemian Rhapsody ---");
            String lyrics1 = lyricsAdapter.getLyrics(bohemian);
            System.out.println(lyrics1);
            System.out.println();

            System.out.println("--- Tekst piosenki: Numb ---");
            String lyrics2 = lyricsAdapter.getLyrics(numb);
            System.out.println(lyrics2);
            System.out.println();

            System.out.println("--- Tekst piosenki: Espresso ---");
            String lyrics3 = lyricsAdapter.getLyrics(espresso);
            System.out.println(lyrics3);
            System.out.println();

            System.out.println("--- Próba odczytu nieistniejącej piosenki ---");
            String lyrics4 = lyricsAdapter.getLyrics(nonExistent);
            System.out.println(lyrics4);

        } catch (Exception e) {
            System.err.println("Błąd podczas odczytu pliku songs.txt: " + e.getMessage());
            e.printStackTrace();
        }

        // Adapter 4: Urządzenia wejściowe
        System.out.println("\n--- Adapter 4: Adaptacja urządzeń wejściowych ---");
        USBMicrophone usbMic = new USBMicrophone();
        AudioInputAdapter audioAdapter = new AudioInputAdapter(usbMic);
        System.out.println("Informacja o urządzeniu: " + audioAdapter.getDeviceInfo());
        byte[] audioData = audioAdapter.record(2);
        System.out.println("Nagrano " + audioData.length + " bajtów audio z mikrofonu USB");

        System.out.println("\n----------------------------------------------------------------------------------------------");
    }

    private static void demonstrateComposite() {
        System.out.println("\n----------------------------------- COMPOSITE -----------------------------------");

        // Composite 1: Hierarchia playlist
        System.out.println("\n--- Composite 1: Hierarchia playlist ---");
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
        System.out.println("Piosenka #2: " + mainPlaylist.getSong(1).getTitle());

        // Composite 2: Hierarchia uczestników
        System.out.println("\n--- Composite 2: Hierarchia uczestników ---");
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
        trio.addMember(duet); // grupa może zawierać inną grupę!
        trio.addMember(piotr);

        System.out.println("Struktura wykonawców:");
        trio.perform("");
        System.out.println("Łączny poziom: " + trio.getTotalLevel());
        System.out.println("Liczba członków: " + trio.getMemberCount());

        // Composite 3: Hierarchia sprzętu
        System.out.println("\n--- Composite 3: Hierarchia sprzętu ---");
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

        // Composite 4: Hierarchia kategorii piosenek
        System.out.println("\n--- Composite 4: Hierarchia kategorii piosenek ---");

        SongCategoryLeaf rockLeaf = new SongCategoryLeaf("Rock Klasyczny");
        rockLeaf.addSong(song1); // Numb
        rockLeaf.addSong(song2); // Lithium

        SongCategoryLeaf metalLeaf = new SongCategoryLeaf("Metal");
        metalLeaf.addSong(SongFactory.createSong("rock", "Master of Puppets", "Metallica", 515, 9));

        SongCategoryLeaf popLeaf = new SongCategoryLeaf("Pop");
        popLeaf.addSong(song3); // Espresso

        CategoryComposite rockKategoria = new CategoryComposite("Rock");
        rockKategoria.addSubcategory(rockLeaf);
        rockKategoria.addSubcategory(metalLeaf);

        CategoryComposite muzykaKategoria = new CategoryComposite("Muzyka");
        muzykaKategoria.addSubcategory(rockKategoria);
        muzykaKategoria.addSubcategory(popLeaf);

        System.out.println("Struktura kategorii:");
        muzykaKategoria.printStructure("");

        System.out.println("\n----------------------------------------------------------------------------------------------");
    }

    private static void demonstrateBridge() {
        System.out.println("\n----------------------------------- BRIDGE -----------------------------------");

        // Bridge 1: Typ występu i system oceny
        System.out.println("\n--- Bridge 1: PerformanceType + ScoringImplementation ---");
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

        // Bridge 2: Odtwarzacz i źródło dźwięku
        System.out.println("\n--- Bridge 2: AudioPlayer + AudioSource ---");
        FileAudioSource fileSource = new FileAudioSource("song.mp3", 240);
        MicrophoneAudioSource micSource = new MicrophoneAudioSource("Mikrofon USB");

        AudioPlayer mp3Player = new MP3Player(fileSource);
        AudioPlayer streamingPlayer = new StreamingPlayer(micSource, "rtmp://live.karaoke.com/stream");

        mp3Player.play();
        mp3Player.setVolume(75);
        streamingPlayer.play();

        // Bridge 3: Wyświetlanie tekstów i źródło
        System.out.println("\n--- Bridge 3: LyricsDisplay + LyricsSource ---");
        FileLyricsSource fileLyrics = new FileLyricsSource("bohemian.txt");
        APILyricsSource apiLyrics = new APILyricsSource("https://api.lyrics.ovh");

        LyricsDisplay scrollingDisplay = new ScrollingLyricsDisplay(fileLyrics, 3);
        LyricsDisplay karaokeDisplay = new KaraokeStyleDisplay(apiLyrics, true, "black");

        scrollingDisplay.showLyrics(song);
        karaokeDisplay.showLyrics(song);

        // Bridge 4: Powiadomienia i kanały
        System.out.println("\n--- Bridge 4: Notification + NotificationChannel ---");
        EmailChannel emailChannel = new EmailChannel("smtp.karaoke.com");
        SMSChannel smsChannel = new SMSChannel("+48123456789", 5);

        Notification inviteNotification = new PerformanceInviteNotification(emailChannel, song.getTitle(), "20:00");
        Notification scoreNotification = new ScoreUpdateNotification(smsChannel, 950, 3);

        inviteNotification.send("Przygotuj się!", user);
        scoreNotification.send("Świetny występ!", user);

        System.out.println("\n----------------------------------------------------------------------------------------------");
    }

    private static void demonstrateDecorator() {
        System.out.println("\n----------------------------------- DECORATOR -----------------------------------");

        // Decorator 1: Dekoratory dla występów
        System.out.println("\n--- Decorator 1: Performance Decorators (efekty dźwiękowe) ---");
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

        // Decorator 2: Dekoratory dla playlist
        System.out.println("\n--- Decorator 2: Playlist Decorators (filtry, sortowanie) ---");
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
        System.out.println("Wymieszana (pierwsza piosenka): " + shuffled.getSongs().getFirst().getTitle());

        // Decorator 3: Dekoratory dla użytkowników
        System.out.println("\n--- Decorator 3: User Decorators (umiejętności, bonusy) ---");
        User basicUser = new UserBuilder("Kasia")
                .setLevel(3)
                .setPoints(150)
                .build();
        System.out.println("Podstawowy: " + basicUser + " -> poziom: " + basicUser.getLevel());

        User withVocal = new VocalSkillDecorator(basicUser, 8, true);
        System.out.println("Z umiejętnościami wokalnymi: " + withVocal + " -> poziom: " + withVocal.getLevel());

        User userBonus = new ExperienceBoostDecorator(basicUser, 1.5, "Weekendowy bonus");
        System.out.println("Z bonusem: " + userBonus + " -> punkty: " + userBonus.getPoints());

        // Decorator 4: Dekoratory dla systemów punktacji
        System.out.println("\n--- Decorator 4: Scoring Decorators (bonusy, kary, mnożniki) ---");
        ScoringStrategy baseStrategy = ScoringStrategyFactory.createStrategy("pro");
        int baseScore = 100;

        System.out.println("Bazowy wynik: " + baseStrategy.calculateScore(baseScore));

        ScoringStrategy withBonus = new BonusPointsDecorator(baseStrategy, 50, "Publiczność");
        System.out.println("Z bonusem: " + withBonus.calculateScore(baseScore));

        ScoringStrategy withPenalty = new TimePenaltyDecorator(baseStrategy, 180, 200, 2);
        System.out.println("Z karą czasową: " + withPenalty.calculateScore(baseScore));

        ScoringStrategy withMultiplier = new DifficultyMultiplierDecorator(baseStrategy, 8, 3);
        System.out.println("Z mnożnikiem trudności: " + withMultiplier.calculateScore(baseScore));

        // Łączenie dekoratorów
        ScoringStrategy allDecorators = new BonusPointsDecorator(
                new TimePenaltyDecorator(
                        new DifficultyMultiplierDecorator(baseStrategy, 8, 3),
                        180, 200, 2),
                50, "Publiczność");
        System.out.println("Wszystkie dekoratory: " + allDecorators.calculateScore(baseScore));

        System.out.println("\n----------------------------------------------------------------------------------------------");
    }
}