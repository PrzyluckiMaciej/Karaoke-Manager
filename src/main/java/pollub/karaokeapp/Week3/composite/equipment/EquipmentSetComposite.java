package pollub.karaokeapp.Week3.composite.equipment;

import java.util.ArrayList;
import java.util.List;

/**
 * Tydzień 3, Wzorzec Composite 3
 * Kompozyt - zestaw sprzętu
 */
public class EquipmentSetComposite implements EquipmentComponent {

    private String name;
    private List<EquipmentComponent> items = new ArrayList<>();
    private boolean isOn;

    public EquipmentSetComposite(String name) {
        this.name = name;
        this.isOn = false;
    }

    public void addItem(EquipmentComponent item) {
        items.add(item);
    }

    public void removeItem(EquipmentComponent item) {
        items.remove(item);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return items.stream()
                .mapToDouble(EquipmentComponent::getPrice)
                .sum();
    }

    @Override
    public int getWattage() {
        return items.stream()
                .mapToInt(EquipmentComponent::getWattage)
                .sum();
    }

    @Override
    public void turnOn(String indent) {
        isOn = true;
        System.out.println(indent + "🔌 Włączanie zestawu: " + name);
        for (EquipmentComponent item : items) {
            item.turnOn(indent + "  ");
        }
    }

    @Override
    public void turnOff(String indent) {
        isOn = false;
        System.out.println(indent + "🔌 Wyłączanie zestawu: " + name);
        for (EquipmentComponent item : items) {
            item.turnOff(indent + "  ");
        }
    }

    @Override
    public boolean isOn() {
        return isOn && items.stream().allMatch(EquipmentComponent::isOn);
    }
}
// Koniec, Tydzień 3, Wzorzec Composite 3