// composite/utils/IndexUtils.java
package pollub.karaokeapp.Week3.composite.utils;

import java.util.List;
import java.util.function.Function;

/**
 * Klasa pomocnicza do obsługi indeksowania w strukturach composite
 * Eliminuje powtórzenia kodu (DRY)
 */
public class IndexUtils {

    private static final int FIRST_ELEMENT_INDEX = 0;

    private IndexUtils() {
        // Ukryty konstruktor - klasa narzędziowa
    }

    /**
     * Znajduje komponent nadrzędny na podstawie indeksu globalnego
     * @param index globalny indeks do znalezienia
     * @param components lista komponentów
     * @param countExtractor funkcja pobierająca liczbę elementów w komponencie
     * @param <T> typ komponentu
     * @return znaleziony komponent
     * @throws IndexOutOfBoundsException gdy indeks poza zakresem
     */
    public static <T> T findComponentByIndex(int index, List<T> components,
                                             Function<T, Integer> countExtractor) {
        validateIndex(index);

        int currentIndex = index;
        for (T component : components) {
            int componentSize = countExtractor.apply(component);
            if (currentIndex < componentSize) {
                return component;
            }
            currentIndex -= componentSize;
        }

        throw new IndexOutOfBoundsException("Nie znaleziono komponentu dla indeksu: " + index);
    }

    /**
     * Sprawdza czy indeks jest pierwszym elementem (0)
     */
    public static boolean isFirstElement(int index) {
        return index == FIRST_ELEMENT_INDEX;
    }

    /**
     * Waliduje zakres indeksu
     */
    public static void validateIndex(int index) {
        if (index < FIRST_ELEMENT_INDEX) {
            throw new IndexOutOfBoundsException("Indeks nie może być ujemny: " + index);
        }
    }

    /**
     * Waliduje indeks z rozmiarem
     */
    public static void validateIndex(int index, int size) {
        validateIndex(index);
        if (index >= size) {
            throw new IndexOutOfBoundsException("Indeks: " + index + ", rozmiar: " + size);
        }
    }
}