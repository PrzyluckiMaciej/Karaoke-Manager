package pollub.karaokeapp.Week2.factory.song;

import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 2, Wzorzec Factory 1
 * Zastosowanie Simple Factory do tworzenia obiektów Song
 * w zależności od podanego typu gatunku.
 */
public class SongFactory {

    public static Song createSong(String type, String title, String artist, int duration, int difficulty) {

        switch (type.toLowerCase()) {
            case "pop":
                return new Song(title, artist, duration, "Pop", difficulty);
            case "rock":
                return new Song(title, artist, duration, "Rock", difficulty);
            case "rap":
                return new Song(title, artist, duration, "Rap", difficulty);
            case "jazz":
                return new Song(title, artist, duration, "Jazz", difficulty);
            case "grunge":
                return new Song(title, artist, duration, "Grunge", difficulty);
            default:
                throw new IllegalArgumentException("Nieznany typ piosenki!");
        }
    }
}
//Koniec, Tydzień 2, Wzorzec Factory 1