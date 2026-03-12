package pollub.karaokeapp.service.lyrics;

import pollub.karaokeapp.model.song.Song;

public interface LyricsProvider {
    String getLyrics(Song song);
}