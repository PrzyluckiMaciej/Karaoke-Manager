package pollub.karaokeapp.Week3.composite.performer;

import pollub.karaokeapp.Week3.composite.utils.IndexUtils;
import pollub.karaokeapp.model.user.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Tydzień 3, Wzorzec Composite 2
 * Kompozyt - grupa wykonawców
 */
public class GroupPerformerComposite implements PerformerComponent {

    private static final int INDENT_INCREMENT = 2;

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
        printGroupHeader(indent);
        performMembers(indent);
    }

    private void printGroupHeader(String indent) {
        System.out.println(indent + "👥 Grupa: " + groupName +
                " (" + getMemberCount() + " członków, " +
                "łączny poziom: " + getTotalLevel() + ")");
    }

    private void performMembers(String indent) {
        String childIndent = indent + " ".repeat(INDENT_INCREMENT);
        for (PerformerComponent member : members) {
            member.perform(childIndent);
        }
    }

    @Override
    public User getUser(int index) {
        IndexUtils.validateIndex(index, getMemberCount());
        PerformerComponent targetComponent = IndexUtils.findComponentByIndex(
                index, members, PerformerComponent::getMemberCount
        );
        int offset = calculateOffset(index, targetComponent);
        return targetComponent.getUser(offset);
    }

    private int calculateOffset(int globalIndex, PerformerComponent targetComponent) {
        int currentIndex = 0;
        for (PerformerComponent component : members) {
            if (component == targetComponent) {
                return globalIndex - currentIndex;
            }
            currentIndex += component.getMemberCount();
        }
        return globalIndex;
    }
}