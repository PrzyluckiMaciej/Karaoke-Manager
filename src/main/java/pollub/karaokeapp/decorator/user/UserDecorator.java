package pollub.karaokeapp.decorator.user;

import pollub.karaokeapp.model.user.User;

/**
 * Tydzień 3, Wzorzec Decorator 3
 * Bazowa klasa dekoratora dla użytkownika
 */
public abstract class UserDecorator extends User {

    protected User decoratedUser;

    public UserDecorator(User decoratedUser) {
        super(decoratedUser.getNickname(), decoratedUser.getLevel(), decoratedUser.getPoints());
        this.decoratedUser = decoratedUser;
    }

    @Override
    public String getNickname() {
        return decoratedUser.getNickname();
    }

    @Override
    public int getLevel() {
        return decoratedUser.getLevel();
    }

    @Override
    public int getPoints() {
        return decoratedUser.getPoints();
    }

    @Override
    public String toString() {
        return decoratedUser.toString();
    }
}
// Koniec, Tydzień 3, Wzorzec Decorator 3