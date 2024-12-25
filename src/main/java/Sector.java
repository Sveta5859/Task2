import java.awt.*;

public abstract class Sector {
    protected Color startColor;
    protected Color endColor;

    public Sector(Color startColor, Color endColor) {
        this.startColor = startColor;
        this.endColor = endColor;
    }

    public void updateColors(Color startColor, Color endColor) {
        this.startColor = startColor;
        this.endColor = endColor;
    }

    public abstract void draw(Graphics2D g2d, int x, int y, int radius, double startAngle, double arcAngle);
}