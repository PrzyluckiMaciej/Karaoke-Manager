package pollub.karaokeapp.Week10.functional;

import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 10, Interfejs funkcyjny 1
 * SongRatingFunction ocenia piosenkę i zwraca wynik liczbowy.
 * Umożliwia definiowanie różnych strategii oceniania piosenek
 * za pomocą wyrażeń lambda.
 */
@FunctionalInterface
public interface SongRatingFunction {
    int rate(Song song);
}
//Koniec, Tydzień 10, Interfejs funkcyjny 1
