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
        validateBoostParameters(pointMultiplier, boostReason);
        this.pointMultiplier = pointMultiplier;
        this.boostReason = boostReason;
    }

    private void validateBoostParameters(double multiplier, String reason) {
        if (multiplier <= 0) {
            throw new IllegalArgumentException("Point multiplier must be positive");
        }
        if (reason == null || reason.trim().isEmpty()) {
            throw new IllegalArgumentException("Boost reason cannot be null or empty");
        }
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