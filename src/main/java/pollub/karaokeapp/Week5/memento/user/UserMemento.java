package pollub.karaokeapp.Week5.memento.user;

/**
 * Tydzień 5, Wzorzec Memento 4
 * Migawka stanu użytkownika (poziom i punkty).
 * Umożliwia cofanie zmian profilu gracza.
 */
public class UserMemento {

    private final String nickname;
    private final int level;
    private final int points;
    private final long timestamp;

    public UserMemento(String nickname, int level, int points) {
        this.nickname = nickname;
        this.level = level;
        this.points = points;
        this.timestamp = System.currentTimeMillis();
    }

    public String getNickname()  { return nickname; }
    public int getLevel()        { return level; }
    public int getPoints()       { return points; }
    public long getTimestamp()   { return timestamp; }

    @Override
    public String toString() {
        return "UserSnapshot{nickname='" + nickname + "', level=" + level + ", points=" + points + "}";
    }
}
// Koniec, Tydzień 5, Wzorzec Memento 4