package pollub.karaokeapp.Week4.flyweight;

/**
 * Tydzień 4, Wzorzec Flyweight 4
 * Flyweight dla współdzielonych awatarów użytkowników
 * Zmniejsza pamięć poprzez dzielenie danych graficznych
 */
public class UserAvatarFlyweight {

    private final String avatarId;
    private final String avatarName;
    private final String imageData;
    private final String color;
    private final String badgeType;

    public UserAvatarFlyweight(
            String avatarId,
            String avatarName,
            String imageData,
            String color,
            String badgeType) {
        this.avatarId = avatarId;
        this.avatarName = avatarName;
        this.imageData = imageData;
        this.color = color;
        this.badgeType = badgeType;
    }

    public String getAvatarId() {
        return avatarId;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public String getImageData() {
        return imageData;
    }

    public String getColor() {
        return color;
    }

    public String getBadgeType() {
        return badgeType;
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "id='" + avatarId + '\'' +
                ", name='" + avatarName + '\'' +
                ", color='" + color + '\'' +
                ", badge='" + badgeType + '\'' +
                '}';
    }
}
// Koniec, Tydzień 4, Wzorzec Flyweight 4
