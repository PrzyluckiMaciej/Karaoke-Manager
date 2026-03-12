// InstrumentSkillDecorator.java
package pollub.karaokeapp.decorator.user;

import pollub.karaokeapp.model.user.User;

/**
 * Tydzień 3, Wzorzec Decorator 3
 * Dekorator dodający umiejętności instrumentalne
 */
public class InstrumentSkillDecorator extends UserDecorator {

    private String instrument;
    private int skillLevel; // 1-10

    public InstrumentSkillDecorator(User decoratedUser, String instrument, int skillLevel) {
        super(decoratedUser);
        this.instrument = instrument;
        this.skillLevel = skillLevel;
    }

    @Override
    public int getLevel() {
        return decoratedUser.getLevel() + skillLevel / 2;
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