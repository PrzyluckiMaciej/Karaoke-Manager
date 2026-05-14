package pollub.karaokeapp.Week3.composite.playlist;

import pollub.karaokeapp.Week3.composite.utils.IndexUtils;
import pollub.karaokeapp.model.song.Song;
import java.util.ArrayList;
import java.util.List;

/**
 * Tydzień 3, Wzorzec Composite 1
 * Kompozyt - playlista zawierająca inne elementy (piosenki lub playlisty)
 */
public class PlaylistComposite implements PlaylistComponent {

    private static final int INDENT_INCREMENT = 2;

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
        displayHeader(indent);
        displayComponents(indent);
    }

    private void displayHeader(String indent) {
        System.out.println(indent + "📁 Playlista: " + name +
                " (czas: " + getTotalDuration() + "s, piosenek: " + getSongCount() + ")");
    }

    private void displayComponents(String indent) {
        String childIndent = indent + " ".repeat(INDENT_INCREMENT);
        for (PlaylistComponent component : components) {
            component.display(childIndent);
        }
    }

    @Override
    public Song getSong(int index) {
        IndexUtils.validateIndex(index, getSongCount());
        PlaylistComponent targetComponent = IndexUtils.findComponentByIndex(
                index, components, PlaylistComponent::getSongCount
        );
        int offset = calculateOffset(index, targetComponent);
        return targetComponent.getSong(offset);
    }

    private int calculateOffset(int globalIndex, PlaylistComponent targetComponent) {
        int currentIndex = 0;
        for (PlaylistComponent component : components) {
            if (component == targetComponent) {
                return globalIndex - currentIndex;
            }
            currentIndex += component.getSongCount();
        }
        return globalIndex;
    }
}