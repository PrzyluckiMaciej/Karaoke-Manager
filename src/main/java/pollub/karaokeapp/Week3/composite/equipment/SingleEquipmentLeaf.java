package pollub.karaokeapp.Week3.composite.equipment;

/**
 * Tydzień 3, Wzorzec Composite 3
 * Liść - pojedynczy element sprzętu
 */
public class SingleEquipmentLeaf implements EquipmentComponent {

    private String name;
    private double price;
    private int wattage;
    private boolean isOn;

    public SingleEquipmentLeaf(String name, double price, int wattage) {
        this.name = name;
        this.price = price;
        this.wattage = wattage;
        this.isOn = false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getWattage() {
        return wattage;
    }

    @Override
    public void turnOn(String indent) {
        isOn = true;
        System.out.println(indent + "⚡ Włączono: " + name);
    }

    @Override
    public void turnOff(String indent) {
        isOn = false;
        System.out.println(indent + "⚡ Wyłączono: " + name);
    }

    @Override
    public boolean isOn() {
        return isOn;
    }
}
// Koniec, Tydzień 3, Wzorzec Composite 3