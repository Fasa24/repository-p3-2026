package components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class RoundButton extends JButton {

    private boolean drawBorder;
    private float borderThickness;

    public RoundButton(String label) {
        super(label);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);

        this.drawBorder = false;
        this.borderThickness = 0;
    }

    public void setDrawBorder(boolean drawBorder) {
        this.drawBorder = drawBorder;
        repaint(); 
    }

    public boolean isDrawBorder() {
        return drawBorder;
    }

    public void setBorderThickness(float thickness) {
        this.borderThickness = thickness;
        repaint();
    }

    public float getBorderThickness() {
        return borderThickness;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isArmed()) {
            g2.setColor(Color.LIGHT_GRAY);
        } else {
            g2.setColor(getBackground());
        }
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        g2.dispose();

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        if (drawBorder && borderThickness > 0) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(new BasicStroke(borderThickness));
            g2.setColor(getForeground());

            int offset = (int) (borderThickness / 2);
            g2.drawRoundRect(offset, offset,
            				getWidth() - offset * 2,
            				getHeight() - 1 - offset * 2,
            				30, 30);
            g2.dispose();
        }
    }

    @Override
    public boolean contains(int x, int y) {
    	return new java.awt.geom.RoundRectangle2D.Float(
    			0, 0, getWidth(), getHeight(), 30, 30
    	).contains(x, y);
    }
}
