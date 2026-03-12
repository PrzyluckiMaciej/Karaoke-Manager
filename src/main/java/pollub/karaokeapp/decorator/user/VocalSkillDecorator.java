// VocalSkillDecorator.java
package pollub.karaokeapp.decorator.user;

import pollub.karaokeapp.model.user.User;

/**
 * Tydzień 3, Wzorzec Decorator 3
 * Dekorator dodający umiejętności wokalne
 */
public class VocalSkillDecorator extends UserDecorator {

    private int vocalRange; // skala 1-10
    private boolean hasPerfectPitch;

    public VocalSkillDecorator(User decoratedUser, int vocalRange, boolean hasPerfectPitch) {
        super(decoratedUser);
        this.vocalRange = vocalRange;
        this.hasPerfectPitch = hasPerfectPitch;
    }

    @Override
    public int getLevel() {
        // Umiejętności wokalne zwiększają poziom
        int bonus = vocalRange / 2 + (hasPerfectPitch ? 3 : 0);
        return decoratedUser.getLevel() + bonus;
    }

    @Override
    public String toString() {
        return decoratedUser.toString() +
                " [Wokal: zakres=" + vocalRange + ", słuch absolutny=" + hasPerfectPitch + "]";
    }
}
// Koniec, Tydzień 3, Wzorzec Decorator 3