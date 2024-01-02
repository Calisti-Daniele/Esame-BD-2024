package gui;

import javax.swing.*;
import java.awt.*;

public class ShadowButton extends JButton {

    private static final Color SHADOW_COLOR = new Color(0, 0, 0, 50);
    private static final int SHADOW_OFFSET = 5;

    public ShadowButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Disegna l'ombra
        g2d.setColor(SHADOW_COLOR);
        g2d.fillRect(SHADOW_OFFSET, getHeight() - getHeight() / 4, getWidth() - SHADOW_OFFSET, getHeight());

        super.paintComponent(g);

        g2d.dispose();
    }
}
