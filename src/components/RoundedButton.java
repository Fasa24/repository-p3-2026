package components;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {
    private final int radius;

    public RoundedButton(String text, int radius) {
        super(text);
        this.radius = radius;
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Container parent = getParent();
        while (parent != null && !parent.isOpaque()) {
            parent = parent.getParent();
        }
        g2.setColor(parent != null ? parent.getBackground() : UIManager.getColor("Panel.background"));
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius * 2, radius * 2);

        g2.dispose();
        super.paintComponent(g);
    }
}