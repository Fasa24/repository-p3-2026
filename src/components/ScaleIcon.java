package components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScaleIcon {
    public ScaleIcon(){}

    public static ImageIcon scale(String path, int w, int h) {
        try {
            BufferedImage original = ImageIO.read(new File(path));
            BufferedImage scaled = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = scaled.createGraphics();

            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.drawImage(original, 0, 0, w, h, null);
            g2d.dispose();

            return new ImageIcon(scaled);
        } catch (IOException e) {
            System.out.println("Icon not found: " + path);
            return new ImageIcon();
        }
    }
}
