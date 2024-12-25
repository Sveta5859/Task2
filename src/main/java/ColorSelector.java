import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class ColorSelector {
    private final JComboBox<String> colorCombo;
    private Color selectedColor;

    private static final Map<String, Color> COLOR_MAP = new HashMap<>();
    static {
        COLOR_MAP.put("Красный", Color.RED);
        COLOR_MAP.put("Оранжевый", Color.ORANGE);
        COLOR_MAP.put("Желтый", Color.YELLOW);
        COLOR_MAP.put("Зеленый", Color.GREEN);
        COLOR_MAP.put("Голубой", Color.CYAN);
        COLOR_MAP.put("Синий", Color.BLUE);
        COLOR_MAP.put("Фиолетовый", Color.MAGENTA);
        COLOR_MAP.put("Чёрный", Color.BLACK);
    }

    public ColorSelector(Color defaultColor, ActionListener listener) {
        this.selectedColor = defaultColor;
        this.colorCombo = new JComboBox<>(COLOR_MAP.keySet().toArray(new String[0]));

        // Устанавливаем значение по умолчанию и добавляем слушатель
        String defaultColorName = getColorName(defaultColor);
        if (defaultColorName != null) {
            colorCombo.setSelectedItem(defaultColorName);
        }
        colorCombo.addActionListener(e -> {
            updateSelectedColor((String) colorCombo.getSelectedItem());
            listener.actionPerformed(e);
        });
    }

    public JComboBox<String> getComboBox() {
        return colorCombo;
    }

    public Color getSelectedColor() {
        return selectedColor;
    }

    public void updateSelectedColor(String colorName) {
        this.selectedColor = COLOR_MAP.getOrDefault(colorName, Color.BLACK);
    }

    private String getColorName(Color color) {
        return COLOR_MAP.entrySet().stream()
                .filter(entry -> entry.getValue().equals(color))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }
}