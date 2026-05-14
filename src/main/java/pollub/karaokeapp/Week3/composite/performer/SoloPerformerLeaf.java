package pollub.karaokeapp.Week3.composite.performer;

import pollub.karaokeapp.Week3.composite.utils.IndexUtils;
import pollub.karaokeapp.model.user.User;

/**
 * Tydzień 3, Wzorzec Composite 2
 * Liść - pojedynczy wykonawca
 */
public class SoloPerformerLeaf implements PerformerComponent {

    private static final int SINGLE_MEMBER_COUNT = 1;
    private static final int INDENT_SPACES = 2;

    private User user;

    public SoloPerformerLeaf(User user) {
        this.user = user;
    }

    @Override
    public String getName() {
        return user.getNickname();
    }

    @Override
    public int getTotalLevel() {
        return user.getLevel();
    }

    @Override
    public int getMemberCount() {
        return SINGLE_MEMBER_COUNT;
    }

    @Override
    public void perform(String indent) {
        System.out.println(indent + "🎤 " + user.getNickname() +
                " występuje solo (poziom: " + user.getLevel() + ")");
    }

    @Override
    public User getUser(int index) {
        if (IndexUtils.isFirstElement(index)) {
            return user;
        }
        throw new IndexOutOfBoundsException("Index: " + index + ", dozwolony tylko 0");
    }
}