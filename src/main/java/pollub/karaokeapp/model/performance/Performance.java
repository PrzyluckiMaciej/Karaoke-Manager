package pollub.karaokeapp.model.performance;

import pollub.karaokeapp.model.song.Song;
import pollub.karaokeapp.model.user.User;
import pollub.karaokeapp.prototype.Prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * Tydzień 2, Wzorzec Prototype 4
 * Klasa Performance implementuje Prototype,
 * umożliwiając tworzenie kopii występów jako szablonów (kopia użytkowników i kopia piosenki).
 */
public class Performance implements Prototype<Performance> {

    private Song song;
    private List<User> participants;
    private int score;

    public Performance(Song song, List<User> participants, int score) {
        this.song = song;
        this.participants = participants;
        this.score = score;
    }

    public List<User> getParticipants() {
        return participants;
    }

    @Override
    public Performance clone() {
        List<User> clonedUsers = new ArrayList<>();
        for (User user : participants) {
            clonedUsers.add(user.clone());
        }
        return new Performance(song.clone(), clonedUsers, score);
    }

    @Override
    public String toString() {
        return "Performance{" +
                "song = " + song +
                ", participants=" + participants +
                ", score=" + score +
                '}';
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
//Koniec, Tydzień 2, Wzorzec Prototype 4