package pollub.karaokeapp.Week5.mediator;

/**
 * Tydzień 5, Wzorzec Mediator 1
 * Komponent audio – uczestnik mediatora.
 * Zarządza nagrywaniem i odtwarzaniem. Komunikuje się przez mediatora.
 */
public class AudioComponent extends KaraokeColleague {

    private static final int SIMULATED_RECORDING_SIZE = 1024;
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
                startRecording(data);
                break;
            case "STOP_RECORDING":
                stopRecording();
                break;
            default:
                System.out.println("[AUDIO] Nieobsługiwane zdarzenie: " + event);
        }
    }

    private void startRecording(Object data) {
        isRecording = true;
        System.out.println("[AUDIO] 🎤 Rozpoczęto nagrywanie: " + data);
        lastRecording = new byte[SIMULATED_RECORDING_SIZE];
    }

    private void stopRecording() {
        isRecording = false;
        System.out.println("[AUDIO] ⏹ Zakończono nagrywanie");
    }

    public boolean isRecording() { return isRecording; }
    public byte[] getLastRecording() { return lastRecording; }
}
// Koniec, Tydzień 5, Wzorzec Mediator 1