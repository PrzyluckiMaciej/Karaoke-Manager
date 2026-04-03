package pollub.karaokeapp.Week6.state;

import pollub.karaokeapp.model.performance.Performance;

/**
 * Tydzień 6, Wzorzec State 2
 * Konkretny stan - Wykonanie trwa (Playing)
 */
public class PlayingPerformanceState implements PerformanceState {

    @Override
    public void play(Performance performance) {
        System.out.println("[STATE-PLAYING] Już gramy! Nie można grać ponownie.");
    }

    @Override
    public void pause(Performance performance) {
        System.out.println("[STATE-PLAYING] ⏸ Wstrzymywanie wykonania...");
        performance.setState(new PausedPerformanceState());
    }

    @Override
    public void stop(Performance performance) {
        System.out.println("[STATE-PLAYING] ⏹ Zatrzymywanie wykonania...");
        System.out.println("[STATE-PLAYING] Wykonanie zakończone. Wynik: " + performance.getScore() + " punktów");
        performance.setState(new StoppedPerformanceState());
    }

    @Override
    public String getStateName() {
        return "PLAYING";
    }
}
// Koniec, Tydzień 6, Wzorzec State 2
