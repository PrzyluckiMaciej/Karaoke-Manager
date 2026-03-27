package pollub.karaokeapp.Week5.command.song;

import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 5, Wzorzec Command 1 – Receiver
 * Odbiorca (Receiver) zawierający logikę biznesową edycji piosenki.
 * Komendy delegują do tej klasy zamiast operować bezpośrednio na Song.
 */
public class SongEditor {

    private final Song song;

    public SongEditor(Song song) {
        this.song = song;
    }

    public String getCurrentTitle() {
        return song.getTitle();
    }

    public void changeTitle(String newTitle) {
        System.out.println("[SONG-RECEIVER] Zmiana tytułu: '" + song.getTitle() + "' → '" + newTitle + "'");
        song.setTitle(newTitle);
    }

    public int getCurrentDifficulty() {
        return song.getDifficulty();
    }

    public void changeDifficulty(int newDifficulty) {
        System.out.println("[SONG-RECEIVER] Zmiana trudności: " + song.getDifficulty() + " → " + newDifficulty);
        song.setDifficulty(newDifficulty);
    }

    public Song getSong() {
        return song;
    }
}
// Koniec, Tydzień 5, Wzorzec Command 1 – Receiver