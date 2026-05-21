package pollub.karaokeapp.Week10.functional;

import pollub.karaokeapp.model.performance.Performance;

/**
 * Tydzień 10, Interfejs funkcyjny 2
 * PerformanceReporter generuje tekstowy raport z wykonania.
 * Umożliwia definiowanie różnych formatów raportowania
 * za pomocą wyrażeń lambda.
 */
@FunctionalInterface
public interface PerformanceReporter {
    String report(Performance performance);
}
//Koniec, Tydzień 10, Interfejs funkcyjny 2
