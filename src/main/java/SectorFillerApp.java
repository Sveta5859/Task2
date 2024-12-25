import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
public class SectorFillerApp {
    private final Sector sector;
    private final ColorSelector startColorSelector;
    private final ColorSelector endColorSelector;
    private final JPanel drawingPanel;

    public SectorFillerApp() {
        sector = new GradientSector(Color.BLUE, Color.BLACK);

        // Передаем обработчики в ColorSelector
        startColorSelector = new ColorSelector(Color.BLUE, this::updateColors);
        endColorSelector = new ColorSelector(Color.BLACK, this::updateColors);

        JFrame frame = new JFrame("Sector Filler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel controlPanel = createControlPanel();
        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                int radius = Math.min(centerX, centerY) * 4 / 5;
                sector.draw(g2d, centerX, centerY, radius, 45, 90);
            }
        };

        frame.setLayout(new BorderLayout());
        frame.add(controlPanel, BorderLayout.NORTH);
        frame.add(drawingPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Начальный цвет:"));
        panel.add(startColorSelector.getComboBox());
        panel.add(new JLabel("Конечный цвет:"));
        panel.add(endColorSelector.getComboBox());
        return panel;
    }

    private void updateColors(ActionEvent e) {
        String startColorName = (String) startColorSelector.getComboBox().getSelectedItem();
        String endColorName = (String) endColorSelector.getComboBox().getSelectedItem();

        if (startColorName != null && endColorName != null) {
            startColorSelector.updateSelectedColor(startColorName);
            endColorSelector.updateSelectedColor(endColorName);
            sector.updateColors(startColorSelector.getSelectedColor(), endColorSelector.getSelectedColor());
            drawingPanel.repaint();
        }
    }
}