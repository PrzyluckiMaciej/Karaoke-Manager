package pollub.karaokeapp.Week3.composite.performer;

import pollub.karaokeapp.model.user.User;

/**
 * Tydzień 3, Wzorzec Composite 2
 * Liść - pojedynczy wykonawca
 */
public class SoloPerformerLeaf implements PerformerComponent {

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
        return 1;
    }

    @Override
    public void perform(String indent) {
        System.out.println(indent + "🎤 " + user.getNickname() + " występuje solo (poziom: " + user.getLevel() + ")");
    }

    @Override
    public User getUser(int index) {
        if (index == 0) {
            return user;
        }
        throw new IndexOutOfBoundsException("Index: " + index);
    }
}
// Koniec, Tydzień 3, Wzorzec Composite 2