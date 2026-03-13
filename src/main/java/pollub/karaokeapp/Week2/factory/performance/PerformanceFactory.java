package pollub.karaokeapp.Week2.factory.performance;

import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;

import java.util.List;

/**
 * Tydzień 2, Wzorzec Factory 3
 * Zastosowano Factory Method – klasa abstrakcyjna
 * definiuje metodę tworzenia Performance,
 * natomiast konkretne implementacje decydują o szczegółach.
 */
public abstract class PerformanceFactory {

    public abstract Performance createPerformance(Song song, List<User> participants);
}
// Koniec, Tydzień 2, Wzorzec Factory 3