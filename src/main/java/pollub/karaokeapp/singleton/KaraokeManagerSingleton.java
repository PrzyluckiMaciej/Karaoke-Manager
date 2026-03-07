package pollub.karaokeapp.singleton;

/**
 * Tydzień 2, Wzorzec Singleton 4
 * Enum Singleton to najbezpieczniejsza metoda w Javie, odporna na serializację i refleksję.
 * Globalny manager konfiguracji karaoke.Można użyć typu wyliczeniowego do implementacji
 * wzorca projektowego singletonu, ponieważ Java zapewnia, że każda wartość wyliczeniowa jest
 * tworzona tylko raz w programie Java.
 */
public enum KaraokeManagerSingleton {

    INSTANCE;

    private int currentStage = 1;

    public void nextStage() {
        currentStage++;
    }

    public int getCurrentStage() {
        return currentStage;
    }
}
// Koniec, Tydzień 2, Wzorzec Singleton 4