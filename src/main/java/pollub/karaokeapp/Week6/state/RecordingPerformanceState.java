package pollub.karaokeapp.Week6.state;

import pollub.karaokeapp.model.performance.Performance;

/**
 * Tydzień 6, Wzorzec State 4
 * Konkretny stan - Wykonanie w trakcie nagrywania (Recording)
 */
public class RecordingPerformanceState implements PerformanceState {

    @Override
    public void play(Performance performance) {
        System.out.println("[STATE-RECORDING] Już nagrywamy! Nie można grać ponownie podczas nagrywania.");
    }

    @Override
    public void pause(Performance performance) {
        System.out.println("[STATE-RECORDING] ⏸ Wstrzymywanie nagrywania...");
        System.out.println("[STATE-RECORDING] Audio zostało zachowane");
        performance.setState(new PausedPerformanceState());
    }

    @Override
    public void stop(Performance performance) {
        System.out.println("[STATE-RECORDING] ⏹ Zatrzymywanie nagrywania...");
        System.out.println("[STATE-RECORDING] Nagranie zapisane. Wynik: " + performance.getScore() + " punktów");
        performance.setState(new StoppedPerformanceState());
    }

    @Override
    public String getStateName() {
        return "RECORDING";
    }
}
// Koniec, Tydzień 6, Wzorzec State 4
