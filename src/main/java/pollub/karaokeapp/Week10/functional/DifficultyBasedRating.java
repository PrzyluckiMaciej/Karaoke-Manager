package pollub.karaokeapp.Week10.functional;

import pollub.karaokeapp.model.song.Song;

/**
 * Tydzień 10, Implementacja klasowa interfejsu funkcyjnego 1
 * DifficultyBasedRating ocenia piosenkę na podstawie jej poziomu trudności.
 * Im wyższa trudność, tym wyższy wynik — z premią za piosenki na najwyższym poziomie.
 */
public class DifficultyBasedRating implements SongRatingFunction {

    private static final int MAX_DIFFICULTY = 10;
    private static final int ELITE_BONUS = 50;

    @Override
    public int rate(Song song) {
        int baseRating = song.getDifficulty() * 10;
        if (song.getDifficulty() == MAX_DIFFICULTY) {
            baseRating += ELITE_BONUS;
        }
        return baseRating;
    }
}
//Koniec, Tydzień 10, Implementacja klasowa interfejsu funkcyjnego 1
