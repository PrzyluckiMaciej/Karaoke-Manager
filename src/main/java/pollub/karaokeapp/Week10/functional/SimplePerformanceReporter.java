package pollub.karaokeapp.Week10.functional;

import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.user.User;

import java.util.stream.Collectors;

/**
 * Tydzień 10, Implementacja klasowa interfejsu funkcyjnego 2
 * SimplePerformanceReporter generuje szczegółowy raport wykonania,
 * zawierający tytuł piosenki, listę uczestników i wynik punktowy.
 */
public class SimplePerformanceReporter implements PerformanceReporter {

    @Override
    public String report(Performance performance) {
        String participants = performance.getParticipants().stream()
                .map(User::getNickname)
                .collect(Collectors.joining(", "));
        return String.format("[Raport] Piosenka: \"%s\" | Uczestnicy: %s | Wynik: %d pkt",
                performance.getSong().getTitle(),
                participants,
                performance.getScore());
    }
}
//Koniec, Tydzień 10, Implementacja klasowa interfejsu funkcyjnego 2
