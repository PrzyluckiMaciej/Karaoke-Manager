package pollub.karaokeapp.Week6.state;

import pollub.karaokeapp.model.performance.Performance;

/**
 * Tydzień 6, Wzorzec State 1
 * Interfejs reprezentujący różne stany wykonania
 */
public interface PerformanceState {
    void play(Performance performance);
    void pause(Performance performance);
    void stop(Performance performance);
    String getStateName();
}
// Koniec, Tydzień 6, Wzorzec State 1
