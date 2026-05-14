package pollub.karaokeapp.Week3.composite.equipment;

import java.util.ArrayList;
import java.util.List;

/**
 * Tydzień 3, Wzorzec Composite 3
 * Kompozyt - zestaw sprzętu
 */
public class EquipmentSetComposite implements EquipmentComponent {

    private static final int INDENT_INCREMENT = 2;

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
        printTurnOnHeader(indent);
        turnOnAllItems(indent);
    }

    private void printTurnOnHeader(String indent) {
        System.out.println(indent + "🔌 Włączanie zestawu: " + name);
    }

    private void turnOnAllItems(String indent) {
        String childIndent = indent + " ".repeat(INDENT_INCREMENT);
        for (EquipmentComponent item : items) {
            item.turnOn(childIndent);
        }
    }

    @Override
    public void turnOff(String indent) {
        isOn = false;
        printTurnOffHeader(indent);
        turnOffAllItems(indent);
    }

    private void printTurnOffHeader(String indent) {
        System.out.println(indent + "🔌 Wyłączanie zestawu: " + name);
    }

    private void turnOffAllItems(String indent) {
        String childIndent = indent + " ".repeat(INDENT_INCREMENT);
        for (EquipmentComponent item : items) {
            item.turnOff(childIndent);
        }
    }

    @Override
    public boolean isOn() {
        return isOn && items.stream().allMatch(EquipmentComponent::isOn);
    }
}