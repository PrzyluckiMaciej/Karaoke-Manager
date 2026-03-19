package pollub.karaokeapp.Week4.flyweight;

/**
 * Tydzień 4, Wzorzec Flyweight 3
 * Flyweight dla współdzielonych metadanych piosenek
 * Przechowuje wspólne dane dla wielu kopii piosenek
 */
public class SongMetadataFlyweight {

    private final String language;
    private final String contentRating;
    private final String copyright;
    private final String licenseType;
    private final boolean isExplicit;

    public SongMetadataFlyweight(
            String language,
            String contentRating,
            String copyright,
            String licenseType,
            boolean isExplicit) {
        this.language = language;
        this.contentRating = contentRating;
        this.copyright = copyright;
        this.licenseType = licenseType;
        this.isExplicit = isExplicit;
    }

    public String getLanguage() {
        return language;
    }

    public String getContentRating() {
        return contentRating;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public boolean isExplicit() {
        return isExplicit;
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "lang='" + language + '\'' +
                ", rating='" + contentRating + '\'' +
                ", explicit=" + isExplicit +
                '}';
    }
}
// Koniec, Tydzień 4, Wzorzec Flyweight 3
