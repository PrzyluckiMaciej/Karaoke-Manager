package pollub.karaokeapp.Week6.observer;

import pollub.karaokeapp.model.song.Song;
import java.util.ArrayList;
import java.util.List;

/**
 * Tydzień 6, Wzorzec Observer 2
 * Publisher dla zdarzeń piosenki
 * Obserwuje zmiany w Song (trudność, gatunek, tytuł)
 */
public class SongEventPublisher {

    private List<KaraokeObserver> observers = new ArrayList<>();
    private Song song;

    public SongEventPublisher(Song song) {
        this.song = song;
    }

    public void subscribe(KaraokeObserver observer) {
        observers.add(observer);
        System.out.println("[PUBLISHER] " + observer.getObserverName() + " zasubskrybował zdarzenia piosenki");
    }

    public void unsubscribe(KaraokeObserver observer) {
        observers.remove(observer);
        System.out.println("[PUBLISHER] " + observer.getObserverName() + " rezygnuje ze subskrypcji");
    }

    public void changeSongTitle(String newTitle) {
        String oldTitle = song.getTitle();
        song.setTitle(newTitle);
        notifyObservers("SONG_TITLE_CHANGED", oldTitle + " -> " + newTitle);
    }

    public void changeSongDifficulty(int newDifficulty) {
        int oldDifficulty = song.getDifficulty();
        song.setDifficulty(newDifficulty);
        notifyObservers("SONG_DIFFICULTY_CHANGED", "Trudność: " + oldDifficulty + " -> " + newDifficulty);
    }

    private void notifyObservers(String event, Object data) {
        System.out.println("\n[EVENT] " + event);
        for (KaraokeObserver observer : observers) {
            observer.update(event, data);
        }
    }

    public Song getSong() {
        return song;
    }
}
// Koniec, Tydzień 6, Wzorzec Observer 2
