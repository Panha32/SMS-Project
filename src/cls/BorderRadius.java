package cls;

import javax.swing.border.AbstractBorder;
import java.awt.*;

public class BorderRadius extends AbstractBorder {
    private int radius;

    public BorderRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.WHITE);
//        g2d.setStroke(new BasicStroke(1));

        // Draw rounded rectangle
        g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(radius + 1, radius + 1, radius + 2, radius);
    }
}

