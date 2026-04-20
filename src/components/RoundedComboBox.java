package components;

import javax.swing.*;
import java.awt.*;

public class RoundedComboBox extends JComboBox<String> {
    private final int radius;

    public RoundedComboBox(String[] items, int radius) {
        super(items);
        this.radius = radius;
        setOpaque(false);
        setBackground(Color.WHITE);

        setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus);

                if (index == -1) {
                    label.setOpaque(false);
                } else {
                    label.setOpaque(true);
                    label.setBackground(isSelected ? new Color(200, 220, 255) : Color.WHITE);
                }

                label.setForeground(Color.BLACK);
                label.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 8));
                return label;
            }
        });

        setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton btn = new JButton("▾");
                btn.setOpaque(false);
                btn.setContentAreaFilled(false);
                btn.setBorderPainted(false);
                btn.setForeground(Color.GRAY);
                return btn;
            }
        });

        addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent e) {
                Object popup = getUI().getAccessibleChild(RoundedComboBox.this, 0);
                if (popup instanceof JComponent) {
                    JComponent popupComp = (JComponent) popup;

                    for (Component c : popupComp.getComponents()) {
                        if (c instanceof JScrollPane) {
                            JScrollPane sp = (JScrollPane) c;
                            JList<?> list = (JList<?>) sp.getViewport().getView();
                            list.setBackground(Color.WHITE);
                            list.setForeground(Color.BLACK);
                            list.setSelectionBackground(new Color(200, 220, 255));
                            list.setSelectionForeground(Color.BLACK);
                        }
                    }
                }
            }

            @Override public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent e) {}
            @Override public void popupMenuCanceled(javax.swing.event.PopupMenuEvent e) {}
        });
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

    @Override
    protected void paintBorder(Graphics g) {}
}