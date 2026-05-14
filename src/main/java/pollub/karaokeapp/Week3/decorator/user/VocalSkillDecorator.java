package pollub.karaokeapp.Week3.decorator.user;

import pollub.karaokeapp.model.user.User;

/**
 * Tydzień 3, Wzorzec Decorator 3
 * Dekorator dodający umiejętności wokalne
 */
public class VocalSkillDecorator extends UserDecorator {

    private static final int VOCAL_RANGE_DIVISOR = 2;
    private static final int PERFECT_PITCH_BONUS = 3;
    private static final int MIN_VOCAL_RANGE = 1;
    private static final int MAX_VOCAL_RANGE = 10;

    private int vocalRange;
    private boolean hasPerfectPitch;

    public VocalSkillDecorator(User decoratedUser, int vocalRange, boolean hasPerfectPitch) {
        super(decoratedUser);
        validateVocalParameters(vocalRange);
        this.vocalRange = vocalRange;
        this.hasPerfectPitch = hasPerfectPitch;
    }

    private void validateVocalParameters(int vocalRange) {
        if (vocalRange < MIN_VOCAL_RANGE || vocalRange > MAX_VOCAL_RANGE) {
            throw new IllegalArgumentException("Vocal range must be between 1 and 10");
        }
    }

    @Override
    public int getLevel() {
        int bonus = (vocalRange / VOCAL_RANGE_DIVISOR);
        if (hasPerfectPitch) {
            bonus += PERFECT_PITCH_BONUS;
        }
        return decoratedUser.getLevel() + bonus;
    }

    @Override
    public String toString() {
        return decoratedUser.toString() +
                " [Wokal: zakres=" + vocalRange + ", słuch absolutny=" + hasPerfectPitch + "]";
    }
}
// Koniec, Tydzień 3, Wzorzec Decorator 3