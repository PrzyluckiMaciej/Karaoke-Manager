package pollub.karaokeapp.Week3.composite.performer;

import pollub.karaokeapp.model.user.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Tydzień 3, Wzorzec Composite 2
 * Kompozyt - grupa wykonawców
 */
public class GroupPerformerComposite implements PerformerComponent {

    private String groupName;
    private List<PerformerComponent> members = new ArrayList<>();

    public GroupPerformerComposite(String groupName) {
        this.groupName = groupName;
    }

    public void addMember(PerformerComponent member) {
        members.add(member);
    }

    public void removeMember(PerformerComponent member) {
        members.remove(member);
    }

    @Override
    public String getName() {
        return groupName;
    }

    @Override
    public int getTotalLevel() {
        return members.stream()
                .mapToInt(PerformerComponent::getTotalLevel)
                .sum();
    }

    @Override
    public int getMemberCount() {
        return members.stream()
                .mapToInt(PerformerComponent::getMemberCount)
                .sum();
    }

    @Override
    public void perform(String indent) {
        System.out.println(indent + "👥 Grupa: " + groupName + " (" + getMemberCount() + " członków, łączny poziom: " + getTotalLevel() + ")");
        for (PerformerComponent member : members) {
            member.perform(indent + "  ");
        }
    }

    @Override
    public User getUser(int index) {
        if (index < 0 || index >= getMemberCount()) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        int currentIndex = index;
        for (PerformerComponent member : members) {
            if (currentIndex < member.getMemberCount()) {
                return member.getUser(currentIndex);
            }
            currentIndex -= member.getMemberCount();
        }

        throw new IndexOutOfBoundsException("Nie znaleziono użytkownika o indeksie: " + index);
    }
}
// Koniec, Tydzień 3, Wzorzec Composite 2