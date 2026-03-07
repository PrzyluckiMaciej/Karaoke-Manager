package pollub.karaokeapp.singleton;

/**
 * Tydzień 2, Wzorzec Singleton 1
 * Eager Initialization, czyli instancja singletona tworzona jest od razu przy ładowaniu klasy.
 * Tutaj globalna konfiguracja Karaoke.
 */
public class ConfigSingleton {

    private static final ConfigSingleton INSTANCE = new ConfigSingleton();

    private String configName = "DefaultConfig";

    private ConfigSingleton() {
        // Prywatny konstruktor – uniemożliwia tworzenie instancji z zewnątrz
    }

    public static ConfigSingleton getInstance() {
        return INSTANCE;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }
}
// Koniec, Tydzień 2, Wzorzec Singleton 1