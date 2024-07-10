import resourcesManager.ImageManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class lobby extends JPanel {
    private window window;
    private ImagePathChooser imagePathChooser;
    public static final ImageIcon background = ImageManager.getImageIcon(ImageManager.ImageName.LOBBY_BACKGROUND);

    public lobby(int x, int y, window window) {
        this.window = window;
        this.setSize(x, y);
        this.setLayout(null);

        this.imagePathChooser = new ImagePathChooser(90, 90);
        this.add(imagePathChooser);

        new Thread(() -> {
            while (true) {
                String imagePath = this.imagePathChooser.getImagePath();
                if (imagePath != null) {
                    SwingUtilities.invokeLater(() -> {
                        FiltersPage filtersPage = new FiltersPage(imagePath , window);
                        switchPanel(filtersPage);
                    });
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(background.getImage(), 0, 0, 1540, 840, this);
    }

    public void switchPanel(JPanel newPanel) {
        window.getContentPane().removeAll();
        window.add(newPanel);
        window.revalidate();
        window.repaint();
        newPanel.requestFocusInWindow();
    }
}

