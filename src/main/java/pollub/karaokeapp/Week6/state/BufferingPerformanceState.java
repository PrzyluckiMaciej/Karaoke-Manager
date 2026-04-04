package pollub.karaokeapp.Week6.state;

import pollub.karaokeapp.model.performance.Performance;

/**
 * Tydzień 6, Wzorzec State 4
 * Konkretny stan - Buforowanie piosenki przed wykonaniem (ładowanie)
 */
public class BufferingPerformanceState implements PerformanceState {

    @Override
    public void play(Performance performance) {
        System.out.println("[STATE-BUFFERING] Buforowanie w toku... Nie można jeszcze grać.");
    }

    @Override
    public void pause(Performance performance) {
        System.out.println("[STATE-BUFFERING] Buforowanie w toku... Nie można pauzować.");
    }

    @Override
    public void stop(Performance performance) {
        System.out.println("[STATE-BUFFERING] ⏹ Anulowanie buforowania...");
        performance.setState(new StoppedPerformanceState());
    }

    @Override
    public String getStateName() {
        return "BUFFERING";
    }
}
// Koniec, Tydzień 6, Wzorzec State 4
