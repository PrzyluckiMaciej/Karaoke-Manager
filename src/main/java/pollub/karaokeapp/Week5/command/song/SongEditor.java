package pollub.karaokeapp.Week5.command.song;

import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 5, Wzorzec Command 1 – Receiver
 * Odbiorca (Receiver) zawierający logikę biznesową edycji piosenki.
 * Komendy delegują do tej klasy zamiast operować bezpośrednio na Song.
 */
public class SongEditor {

    public static final int MIN_DIFFICULTY = 1;
    public static final int MAX_DIFFICULTY = 5;
    public static final int DEFAULT_DIFFICULTY = 3;

    private static final String LOG_PREFIX = "[SONG-RECEIVER]";

    private final Song song;

    public SongEditor(Song song) {
        if (song == null) {
            throw new IllegalArgumentException("Piosenka nie może być null");
        }
        this.song = song;
    }

    public String getCurrentTitle() {
        return song.getTitle();
    }

    public void changeTitle(String newTitle) {
        validateTitle(newTitle);
        logTitleChange(newTitle);
        song.setTitle(newTitle);
    }

    public int getCurrentDifficulty() {
        return song.getDifficulty();
    }

    public void changeDifficulty(int newDifficulty) {
        validateDifficulty(newDifficulty);
        logDifficultyChange(newDifficulty);
        song.setDifficulty(newDifficulty);
    }

    public Song getSong() {
        return song;
    }

    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Tytuł piosenki nie może być pusty");
        }
    }

    private void validateDifficulty(int difficulty) {
        if (difficulty < MIN_DIFFICULTY || difficulty > MAX_DIFFICULTY) {
            throw new IllegalArgumentException(
                    "Trudność musi być między " + MIN_DIFFICULTY + " a " + MAX_DIFFICULTY +
                            ". Podano: " + difficulty
            );
        }
    }

    private void logTitleChange(String newTitle) {
        System.out.println(LOG_PREFIX + " Zmiana tytułu: '" + song.getTitle() + "' → '" + newTitle + "'");
    }

    private void logDifficultyChange(int newDifficulty) {
        System.out.println(LOG_PREFIX + " Zmiana trudności: " + song.getDifficulty() + " → " + newDifficulty);
    }
}
// Koniec, Tydzień 5, Wzorzec Command 1 – Receiver