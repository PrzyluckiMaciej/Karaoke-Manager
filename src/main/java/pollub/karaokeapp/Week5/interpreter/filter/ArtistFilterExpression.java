package pollub.karaokeapp.Week5.interpreter.filter;

import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 5, Wzorzec Interpreter 1
 * Terminal – filtruje piosenki po artyście.
 */
public class ArtistFilterExpression implements SongFilterExpression {

    private final String artist;

    public ArtistFilterExpression(String artist) {
        if (artist == null || artist.trim().isEmpty()) {
            throw new IllegalArgumentException("Nazwa artysty nie może być pusta");
        }
        this.artist = artist;
    }

    @Override
    public boolean interpret(Song song) {
        if (song == null) {
            throw new IllegalArgumentException("Piosenka nie może być null");
        }
        String songArtist = song.getArtist();
        if (songArtist == null) {
            return false;
        }
        return songArtist.equalsIgnoreCase(artist);
    }

    @Override
    public String getExpressionDescription() {
        return "artysta='" + artist + "'";
    }
}