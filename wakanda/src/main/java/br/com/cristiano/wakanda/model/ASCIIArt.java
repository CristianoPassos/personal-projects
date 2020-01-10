package br.com.cristiano.wakanda.model;

import br.com.cristiano.wakanda.view.util.MessagesUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapted from: mkyong, https://www.mkyong.com/java/ascii-art-java-example/
 */
public class ASCIIArt {
    int width = 100;
    int height = 30;

    public List<String> createArt(String messageKey) {
        String word = MessagesUtils.getText(messageKey);
        List<String> art = new ArrayList<>();
        BufferedImage image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("SansSerif", Font.BOLD, 12));

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString(word, 10, 20);

        for (int y = 0; y < this.height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < this.width; x++) {
                sb.append(image.getRGB(x, y) == -16777216 ? " " : "$");
            }
            if (sb.toString().trim().isEmpty()) {
                continue;
            }
            art.add(sb.toString());
        }
        return art;
    }
}
