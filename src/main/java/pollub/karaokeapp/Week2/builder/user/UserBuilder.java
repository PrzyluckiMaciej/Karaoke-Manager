package pollub.karaokeapp.Week2.builder.user;

import pollub.karaokeapp.Week2.KaraokeConstants;
import pollub.karaokeapp.model.user.User;

/**
 * Tydzień 2, Wzorzec Builder 2
 * Builder Użytkownika (Uczestnika Karaoke) z wymaganym polem nickname,
 * poziom trudności karaoke i liczba punktów zdobytych są opcjonalne.
 */
public class UserBuilder {

    private final String nickname;
    private int level = KaraokeConstants.USER_DEFAULT_LEVEL;
    private int points = KaraokeConstants.USER_DEFAULT_POINTS;

    public UserBuilder(String nickname) {
        this.nickname = nickname;
    }

    public UserBuilder setLevel(int level) {
        this.level = level;
        return this;
    }

    public UserBuilder setPoints(int points) {
        this.points = points;
        return this;
    }

    public User build() {
        return new User(nickname, level, points);
    }
}
// Koniec, Tydzień 2, Wzorzec Builder 2