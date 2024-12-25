import java.awt.*;
import java.awt.geom.Arc2D;

public class GradientSector extends Sector {
    public GradientSector(Color startColor, Color endColor) {
        super(startColor, endColor);
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int radius, double startAngle, double arcAngle) {
        Arc2D.Double sector = new Arc2D.Double(x - radius, y - radius, 2 * radius, 2 * radius, startAngle, arcAngle, Arc2D.PIE);
        g2d.setPaint(new RadialGradientPaint(x, y, radius, new float[]{0f, 1f}, new Color[]{startColor, endColor}));
        g2d.fill(sector);
    }
}