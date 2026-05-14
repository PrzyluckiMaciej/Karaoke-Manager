package pollub.karaokeapp.Week6.state;

import pollub.karaokeapp.Week6.exception.IllegalPerformanceStateException;
import pollub.karaokeapp.model.performance.Performance;

/**
 * Tydzień 6, Wzorzec State 4
 * Konkretny stan - Buforowanie piosenki przed wykonaniem (ładowanie)
 */
public class BufferingPerformanceState implements PerformanceState {

    @Override
    public void play(Performance performance) {
        throw new IllegalPerformanceStateException(
                "Nie można grać podczas buforowania. Zaczekaj na zakończenie ładowania."
        );
    }

    @Override
    public void pause(Performance performance) {
        throw new IllegalPerformanceStateException(
                "Nie można pauzować podczas buforowania."
        );
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