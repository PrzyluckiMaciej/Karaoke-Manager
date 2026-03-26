package pollub.karaokeapp.Week5.interpreter;

import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 5, Wzorzec Interpreter 1
 * Interfejs bazowy wyrażeń do filtrowania piosenek.
 * Umożliwia budowanie złożonych zapytań z prostych reguł.
 */
public interface SongFilterExpression {
    boolean interpret(Song song);
    String getExpressionDescription();
}
// Koniec, Tydzień 5, Wzorzec Interpreter 1