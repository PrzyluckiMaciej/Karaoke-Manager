package pollub.karaokeapp.Week3.decorator.user;

import pollub.karaokeapp.model.user.User;

/**
 * Tydzień 3, Wzorzec Decorator 3
 * Dekorator dodający umiejętności instrumentalne
 */
public class InstrumentSkillDecorator extends UserDecorator {

    private static final int SKILL_DIVISOR = 2;
    private static final int MIN_SKILL_LEVEL = 1;
    private static final int MAX_SKILL_LEVEL = 10;

    private String instrument;
    private int skillLevel;

    public InstrumentSkillDecorator(User decoratedUser, String instrument, int skillLevel) {
        super(decoratedUser);
        validateInstrumentParameters(instrument, skillLevel);
        this.instrument = instrument;
        this.skillLevel = skillLevel;
    }

    private void validateInstrumentParameters(String instrument, int skillLevel) {
        if (instrument == null || instrument.trim().isEmpty()) {
            throw new IllegalArgumentException("Instrument cannot be null or empty");
        }
        if (skillLevel < MIN_SKILL_LEVEL || skillLevel > MAX_SKILL_LEVEL) {
            throw new IllegalArgumentException("Skill level must be between 1 and 10");
        }
    }

    @Override
    public int getLevel() {
        return decoratedUser.getLevel() + (skillLevel / SKILL_DIVISOR);
    }

    @Override
    public String toString() {
        return decoratedUser.toString() +
                " [Instrument: " + instrument + " (poziom " + skillLevel + ")]";
    }

    public String getInstrument() {
        return instrument;
    }
}
// Koniec, Tydzień 3, Wzorzec Decorator 3