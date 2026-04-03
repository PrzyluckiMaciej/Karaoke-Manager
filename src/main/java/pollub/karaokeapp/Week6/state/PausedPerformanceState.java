package pollub.karaokeapp.Week6.state;

import pollub.karaokeapp.model.performance.Performance;

/**
 * Tydzień 6, Wzorzec State 3
 * Konkretny stan - Wykonanie wstrzymane (Paused)
 */
public class PausedPerformanceState implements PerformanceState {

    @Override
    public void play(Performance performance) {
        System.out.println("[STATE-PAUSED] ▶ Wznowienie wykonania...");
        performance.setState(new PlayingPerformanceState());
    }

    @Override
    public void pause(Performance performance) {
        System.out.println("[STATE-PAUSED] Już wstrzymane! Nie można wstrzymać ponownie.");
    }

    @Override
    public void stop(Performance performance) {
        System.out.println("[STATE-PAUSED] ⏹ Zatrzymywanie wstrzymanego wykonania...");
        performance.setState(new StoppedPerformanceState());
    }

    @Override
    public String getStateName() {
        return "PAUSED";
    }
}
// Koniec, Tydzień 6, Wzorzec State 3
