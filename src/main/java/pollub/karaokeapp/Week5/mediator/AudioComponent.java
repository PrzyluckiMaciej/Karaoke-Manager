package pollub.karaokeapp.Week5.mediator;

/**
 * Tydzień 5, Wzorzec Mediator 3
 * Komponent audio – uczestnik mediatora.
 * Zarządza nagrywaniem i odtwarzaniem. Komunikuje się przez mediatora.
 */
public class AudioComponent extends KaraokeColleague {

    private boolean isRecording = false;
    private byte[] lastRecording;

    public AudioComponent() {
        super("AudioComponent");
    }

    public void startSong(String songTitle) {
        System.out.println("[AUDIO] Odtwarzanie piosenki: " + songTitle);
        mediator.notify(this, "SONG_STARTED", songTitle);
    }

    public void finishSong(String songTitle) {
        System.out.println("[AUDIO] Zakończono piosenkę: " + songTitle);
        mediator.notify(this, "SONG_FINISHED", songTitle);
    }

    @Override
    public void receive(String event, Object data) {
        switch (event) {
            case "START_RECORDING":
                isRecording = true;
                System.out.println("[AUDIO] 🎤 Rozpoczęto nagrywanie: " + data);
                lastRecording = new byte[1024]; // symulacja
                break;
            case "STOP_RECORDING":
                isRecording = false;
                System.out.println("[AUDIO] ⏹ Zakończono nagrywanie");
                break;
            default:
                System.out.println("[AUDIO] Nieobsługiwane zdarzenie: " + event);
        }
    }

    public boolean isRecording() { return isRecording; }
    public byte[] getLastRecording() { return lastRecording; }
}
// Koniec, Tydzień 5, Wzorzec Mediator 3