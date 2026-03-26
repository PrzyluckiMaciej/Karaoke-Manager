package pollub.karaokeapp.Week5.iterator;

/**
 * Tydzień 5, Wzorzec Iterator
 * Generyczny interfejs iteratora karaoke.
 * Umożliwia sekwencyjny dostęp do elementów kolekcji bez ujawniania jej struktury.
 */
public interface KaraokeIterator<T> {
    boolean hasNext();
    T next();
    void reset();
}
// Koniec, Tydzień 5, Wzorzec Iterator