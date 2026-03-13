package pollub.karaokeapp.Week3.decorator.user;

import pollub.karaokeapp.model.user.User;

/**
 * Tydzień 3, Wzorzec Decorator 3
 * Dekorator dodający bonus doświadczenia
 */
public class ExperienceBoostDecorator extends UserDecorator {

    private double pointMultiplier;
    private String boostReason;

    public ExperienceBoostDecorator(User decoratedUser, double pointMultiplier, String boostReason) {
        super(decoratedUser);
        this.pointMultiplier = pointMultiplier;
        this.boostReason = boostReason;
    }

    @Override
    public int getPoints() {
        return (int)(decoratedUser.getPoints() * pointMultiplier);
    }

    @Override
    public String toString() {
        return decoratedUser.toString() +
                " [BONUS: " + boostReason + " (x" + pointMultiplier + " punktów)]";
    }
}
// Koniec, Tydzień 3, Wzorzec Decorator 3