package pollub.karaokeapp.Week2.builder.performance;

import pollub.karaokeapp.model.performance.Performance;
import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Tydzień 2, Wzorzec Builder 3
 * Builder dla wystąpienia z wymaganym polem song,
 * uczestnicy i wynik opcjonalne.
 */
public class PerformanceBuilder {

    private final Song song;
    private List<User> participants = new ArrayList<>();
    private int score = 0;

    public PerformanceBuilder(Song song) {
        this.song = song;
    }

    public PerformanceBuilder addParticipant(User user) {
        this.participants.add(user);
        return this;
    }

    public PerformanceBuilder setScore(int score) {
        this.score = score;
        return this;
    }

    public Performance build() {
        return new Performance(song, participants, score);
    }
}
// Koniec, Tydzień 2, Wzorzec Builder 3