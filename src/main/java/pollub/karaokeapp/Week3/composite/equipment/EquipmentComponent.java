package pollub.karaokeapp.Week3.composite.equipment;

/**
 * Tydzień 3, Wzorzec Composite 3
 * Interfejs dla elementów sprzętu karaoke
 */
public interface EquipmentComponent {
    String getName();
    double getPrice();
    int getWattage();
    void turnOn(String indent);
    void turnOff(String indent);
    boolean isOn();
}
// Koniec, Tydzień 3, Wzorzec Composite 3