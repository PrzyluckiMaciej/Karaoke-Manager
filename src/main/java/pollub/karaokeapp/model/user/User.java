package pollub.karaokeapp.model.user;

import pollub.karaokeapp.Week2.prototype.Prototype;

/**
 * Tydzień 2, Wzorzec Prototype 3
 * Klasa User implementuje wzorzec Prototype,
 * umożliwiając kopiowanie ustawień użytkownika.
 */
public class User implements Prototype<User> {

    private String nickname;
    private int level;
    private int points;

    public User(String nickname, int level, int points) {
        this.nickname = nickname;
        this.level = level;
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public User clone() {
        return new User(nickname, level, points);
    }

    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", level=" + level +
                ", points=" + points +
                '}';
    }
}
//Koniec, Tydzień 2, Wzorzec Prototype 3