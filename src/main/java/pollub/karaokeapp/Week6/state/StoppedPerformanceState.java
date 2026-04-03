package pollub.karaokeapp.Week6.state;

import pollub.karaokeapp.model.performance.Performance;

/**
 * Tydzień 6, Wzorzec State 4
 * Konkretny stan - Wykonanie zatrzymane (Stopped)
 */
public class StoppedPerformanceState implements PerformanceState {

    @Override
    public void play(Performance performance) {
        System.out.println("[STATE-STOPPED] ▶ Uruchamianie nowego wykonania...");
        performance.setState(new PlayingPerformanceState());
    }

    @Override
    public void pause(Performance performance) {
        System.out.println("[STATE-STOPPED] Nie można wstrzymać zatrzymanego wykonania!");
    }

    @Override
    public void stop(Performance performance) {
        System.out.println("[STATE-STOPPED] Już zatrzymane!");
    }

    @Override
    public String getStateName() {
        return "STOPPED";
    }
}
// Koniec, Tydzień 6, Wzorzec State 4
