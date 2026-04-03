package pollub.karaokeapp.Week6.observer;

/**
 * Tydzień 6, Wzorzec Observer 4
 * Konkretny obserwator - manager playlisty reaguje na zmiany w piosence
 * Przykład: zmiana trudności może wymagać przeorganizowania playlisty
 */
public class PlaylistObserver implements KaraokeObserver {

    private String name = "Playlist Manager";

    @Override
    public void update(String event, Object data) {
        switch (event) {
            case "SONG_DIFFICULTY_CHANGED":
                System.out.println("[OBSERVER-PLAYLIST] Trudność zmieniona! Aktualizuję sortowanie playlisty");
                reorganizePlaylist((String) data);
                break;
            case "SONG_TITLE_CHANGED":
                System.out.println("[OBSERVER-PLAYLIST] Tytuł zmieniony: " + data);
                updatePlaylistMetadata((String) data);
                break;
        }
    }

    private void reorganizePlaylist(String difficultyInfo) {
        System.out.println("  🎵 Przeorganizowanie playlisty ze względu na: " + difficultyInfo);
        System.out.println("  ✓ Playlista ponownie posortowana");
    }

    private void updatePlaylistMetadata(String titleInfo) {
        System.out.println("  📝 Aktualizacja metadanych playlisty");
        System.out.println("  ✓ Indeks piosenki zaktualizowany");
    }

    @Override
    public String getObserverName() {
        return name;
    }
}
// Koniec, Tydzień 6, Wzorzec Observer 4
