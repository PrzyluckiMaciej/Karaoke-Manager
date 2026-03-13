package pollub.karaokeapp.Week3.composite.playlist;

import pollub.karaokeapp.model.song.Song;
import java.util.ArrayList;
import java.util.List;

/**
 * Tydzień 3, Wzorzec Composite 1
 * Kompozyt - playlista zawierająca inne elementy (piosenki lub playlisty)
 */
public class PlaylistComposite implements PlaylistComponent {

    private String name;
    private List<PlaylistComponent> components = new ArrayList<>();

    public PlaylistComposite(String name) {
        this.name = name;
    }

    public void add(PlaylistComponent component) {
        components.add(component);
    }

    public void remove(PlaylistComponent component) {
        components.remove(component);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getTotalDuration() {
        return components.stream()
                .mapToInt(PlaylistComponent::getTotalDuration)
                .sum();
    }

    @Override
    public int getSongCount() {
        return components.stream()
                .mapToInt(PlaylistComponent::getSongCount)
                .sum();
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "📁 Playlista: " + name + " (czas: " + getTotalDuration() + "s, piosenek: " + getSongCount() + ")");
        for (PlaylistComponent component : components) {
            component.display(indent + "  ");
        }
    }

    @Override
    public Song getSong(int index) {
        if (index < 0 || index >= getSongCount()) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        int currentIndex = index;
        for (PlaylistComponent component : components) {
            if (currentIndex < component.getSongCount()) {
                return component.getSong(currentIndex);
            }
            currentIndex -= component.getSongCount();
        }

        throw new IndexOutOfBoundsException("Nie znaleziono piosenki o indeksie: " + index);
    }
}
// Koniec, Tydzień 3, Wzorzec Composite 1