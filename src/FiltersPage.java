import resourcesManager.ImageManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class FiltersPage extends JPanel {
    private window window;
    public static final ImageIcon background = ImageManager.getImageIcon(ImageManager.ImageName.FILTER_BACKGROUND);
    private BufferedImage originalPic;
    private BufferedImage drawPic;
    private boolean markingPoints = false;
    private Point[] markedPoints = new Point[4];
    private int markedCount = 0;

    public FiltersPage(String path , window window) {
        this.window = window;
        try {
            File file = new File(path);
            if (file.exists()) {
                this.originalPic = ImageIO.read(file);
                this.drawPic = new BufferedImage(originalPic.getWidth(), originalPic.getHeight(), originalPic.getType());
                Graphics2D g = drawPic.createGraphics();
                g.drawImage(originalPic, 0, 0, null);
                g.dispose();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.setLayout(null);
        this.setBounds(0, 0, 1540, 840);

        JButton saveImage = new JButton();
        saveImage.setBounds(100, 120, 120, 40);
        saveImage.setIcon(ImageManager.getImageIcon(ImageManager.ImageName.SAVE_IMAGE_BUTTON));
        saveImage.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");
            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                SaveImage(fileToSave.getAbsolutePath());
            }
        });
        this.add(saveImage);

        JButton backToLobby = new JButton();
        backToLobby.setBounds(1300, 120, 120, 40);
        backToLobby.setIcon(ImageManager.getImageIcon(ImageManager.ImageName.BACK_TO_LOBBY_BUTTON));
        backToLobby.addActionListener(e -> {
            lobby lobby = new lobby(1920,1080,window);
            switchPanel(lobby);
        });
        this.add(backToLobby);



        JButton addNoise = new JButton();
        addNoise.setBounds(100, 630, 120, 40);
        addNoise.setIcon(ImageManager.getImageIcon(ImageManager.ImageName.ADD_NOISE_BUTTON));
        addNoise.addActionListener(e -> {
            AddNoise(70);
            repaint();
        });
        this.add(addNoise);

        JButton vintage = new JButton();
        vintage.setBounds(100, 750, 120, 40);
        vintage.setIcon(ImageManager.getImageIcon(ImageManager.ImageName.VINTAGE_BUTTON));
        vintage.addActionListener(e -> {
            Vintage(40);
            repaint();
        });
        this.add(vintage);

        JButton blackAndWhite = new JButton();
        blackAndWhite.setBounds(300, 630, 120, 40);
        blackAndWhite.setIcon(ImageManager.getImageIcon(ImageManager.ImageName.GRAYSCALE_BUTTON));
        blackAndWhite.addActionListener(e -> {
            BlackAndWhite();
            repaint();
        });
        this.add(blackAndWhite);

        JButton posterize = new JButton();
        posterize.setBounds(300, 750, 120, 40);
        posterize.setIcon(ImageManager.getImageIcon(ImageManager.ImageName.POSTERIZE_BUTTON));
        posterize.addActionListener(e -> {
            Posterize();
            repaint();
        });
        this.add(posterize);

        JButton tint = new JButton();
        tint.setBounds(500, 750, 120, 40);
        tint.setIcon(ImageManager.getImageIcon(ImageManager.ImageName.TINT_BUTTON));
        tint.addActionListener(e -> {
            Tint();
            repaint();
        });
        this.add(tint);

        JButton mirror = new JButton();
        mirror.setBounds(500, 630, 120, 40);
        mirror.setIcon(ImageManager.getImageIcon(ImageManager.ImageName.MIRROR_BUTTON));
        mirror.addActionListener(e -> {
            Mirror();
            repaint();
        });
        this.add(mirror);

        JButton pixelate = new JButton();
        pixelate.setBounds(700, 750, 120, 40);
        pixelate.setIcon(ImageManager.getImageIcon(ImageManager.ImageName.PIXELATE_BUTTON));
        pixelate.addActionListener(e -> {
            Pixelate();
            repaint();
        });
        this.add(pixelate);

        JButton removeColor = new JButton();
        removeColor.setBounds(700, 630, 120, 40);
        removeColor.setIcon(ImageManager.getImageIcon(ImageManager.ImageName.REMOVE_COLOR_BUTTON));
        removeColor.addActionListener(e -> {
            RemoveColor(ColorComponent.RED);
            repaint();
        });
        this.add(removeColor);

        JButton resetFilters = new JButton();
        resetFilters.setBounds(700, 530, 120, 40);
        resetFilters.setIcon(ImageManager.getImageIcon(ImageManager.ImageName.RESET_FILTER_BUTTON));
        resetFilters.addActionListener(e -> {
            resetFilters();
            repaint();
        });
        this.add(resetFilters);

        JButton negative = new JButton();
        negative.setBounds(900, 630, 120, 40);
        negative.setIcon(ImageManager.getImageIcon(ImageManager.ImageName.NEGATIVE_BUTTON));
        negative.addActionListener(e -> {
            Negative();
            repaint();
        });
        this.add(negative);

        JButton contrast = new JButton();
        contrast.setBounds(900, 750, 120, 40);
        contrast.setIcon(ImageManager.getImageIcon(ImageManager.ImageName.CONTRAST_BUTTON));
        contrast.addActionListener(e -> {
            Contrast(1.8);
            repaint();
        });
        this.add(contrast);

        JButton vignette = new JButton();
        vignette.setBounds(1100, 630, 120, 40);
        vignette.setIcon(ImageManager.getImageIcon(ImageManager.ImageName.VIGNETTE_BUTTON));
        vignette.addActionListener(e -> {
            Vignette();
            repaint();
        });
        this.add(vignette);


        JButton sepia = new JButton();
        sepia.setBounds(1100, 750, 120, 40);
        sepia.setIcon(ImageManager.getImageIcon(ImageManager.ImageName.SEPIA_BUTTON));
        sepia.addActionListener(e -> {
            Sepia();
            repaint();
        });
        this.add(sepia);

        JButton lighter = new JButton();
        lighter.setBounds(1300, 750, 120, 40);
        lighter.setIcon(ImageManager.getImageIcon(ImageManager.ImageName.LIGHTER_BUTTON));
        lighter.addActionListener(e -> {
            Lighter();
            repaint();
        });
        this.add(lighter);

        JButton darker = new JButton();
        darker.setBounds(1300, 630, 120, 40);
        darker.setIcon(ImageManager.getImageIcon(ImageManager.ImageName.DARKER_BUTTON));
        darker.addActionListener(e -> {
            Darker();
            repaint();
        });
        this.add(darker);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (markingPoints) {
                    if (markedCount < 4) {
                        markedPoints[markedCount] = e.getPoint();
                        markedCount++;
                        if (markedCount == 4) {
                            // Draw square on image
                            drawSquareOnImage();
                        }
                        repaint();
                    }
                }
            }
        });

        JButton markPointsButton = new JButton("Mark Points");
        markPointsButton.setBounds(300, 530, 120, 40);
        markPointsButton.addActionListener(e -> {
            markingPoints = true;
            markedCount = 0;
            repaint();
        });
        this.add(markPointsButton);

        JButton clearPointsButton = new JButton("Clear Points");
        clearPointsButton.setBounds(500, 530, 120, 40);
        clearPointsButton.addActionListener(e -> {
            markingPoints = false;
            markedCount = 0;
            repaint();
        });
        this.add(clearPointsButton);
    }

    private void drawSquareOnImage() {
        Graphics2D g = drawPic.createGraphics();
        g.setColor(Color.RED); // Example: Use a different color for clarity
        for (int i = 0; i < 3; i++) {
            g.drawLine(markedPoints[i].x, markedPoints[i].y, markedPoints[i + 1].x, markedPoints[i + 1].y);
        }
        g.drawLine(markedPoints[3].x, markedPoints[3].y, markedPoints[0].x, markedPoints[0].y);
        g.dispose();
    }
    private void Vintage(int noiseIntensity) {
        if (markedCount == 4) {
            int minX = Math.min(markedPoints[0].x, Math.min(markedPoints[1].x, Math.min(markedPoints[2].x, markedPoints[3].x)));
            int minY = Math.min(markedPoints[0].y, Math.min(markedPoints[1].y, Math.min(markedPoints[2].y, markedPoints[3].y)));
            int maxX = Math.max(markedPoints[0].x, Math.max(markedPoints[1].x, Math.max(markedPoints[2].x, markedPoints[3].x)));
            int maxY = Math.max(markedPoints[0].y, Math.max(markedPoints[1].y, Math.max(markedPoints[2].y, markedPoints[3].y)));

            for (int i = minY; i < maxY; i++) {
                for (int j = minX; j < maxX; j++) {

                    int rgb = originalPic.getRGB(j, i);
                    Color color = new Color(rgb);

                    int r = color.getRed();
                    int g = color.getGreen();
                    int b = color.getBlue();

                    int tr = (int) (0.393 * r + 0.769 * g + 0.189 * b);
                    int tg = (int) (0.349 * r + 0.686 * g + 0.168 * b);
                    int tb = (int) (0.272 * r + 0.534 * g + 0.131 * b);

                    tr = Math.min(255, tr);
                    tg = Math.min(255, tg);
                    tb = Math.min(255, tb);

                    int newR = (int) (0.8 * tr + 0.2 * r);
                    int newG = (int) (0.8 * tg + 0.2 * g);
                    int newB = (int) (0.8 * tb + 0.2 * b);

                    Random random = new Random();
                    int noise = random.nextInt(noiseIntensity + 1);
                    newR = Math.min(Math.max(newR + noise, 0), 255);
                    newG = Math.min(Math.max(newG + noise, 0), 255);
                    newB = Math.min(Math.max(newB + noise, 0), 255);

                    Color sepiaNoiseColor = new Color(newR, newG, newB);

                    int vintageR = (int) (0.6 * sepiaNoiseColor.getRed() + 0.4 * sepiaNoiseColor.getRed());
                    int vintageG = (int) (0.6 * sepiaNoiseColor.getGreen() + 0.4 * sepiaNoiseColor.getGreen());
                    int vintageB = (int) (0.6 * sepiaNoiseColor.getBlue() + 0.4 * sepiaNoiseColor.getBlue());

                    vintageR = Math.min(255, vintageR);
                    vintageG = Math.min(255, vintageG);
                    vintageB = Math.min(255, vintageB);

                    Color vintageColor = new Color(vintageR, vintageG, vintageB);

                    drawPic.setRGB(i, j, vintageColor.getRGB());
                }
            }
        }else {
            int width = drawPic.getWidth();
            int height = drawPic.getHeight();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {

                    int rgb = originalPic.getRGB(x, y);
                    Color color = new Color(rgb);

                    int r = color.getRed();
                    int g = color.getGreen();
                    int b = color.getBlue();

                    int tr = (int) (0.393 * r + 0.769 * g + 0.189 * b);
                    int tg = (int) (0.349 * r + 0.686 * g + 0.168 * b);
                    int tb = (int) (0.272 * r + 0.534 * g + 0.131 * b);

                    tr = Math.min(255, tr);
                    tg = Math.min(255, tg);
                    tb = Math.min(255, tb);

                    int newR = (int) (0.8 * tr + 0.2 * r);
                    int newG = (int) (0.8 * tg + 0.2 * g);
                    int newB = (int) (0.8 * tb + 0.2 * b);

                    Random random = new Random();
                    int noise = random.nextInt(noiseIntensity + 1);
                    newR = Math.min(Math.max(newR + noise, 0), 255);
                    newG = Math.min(Math.max(newG + noise, 0), 255);
                    newB = Math.min(Math.max(newB + noise, 0), 255);

                    Color sepiaNoiseColor = new Color(newR, newG, newB);

                    int vintageR = (int) (0.6 * sepiaNoiseColor.getRed() + 0.4 * sepiaNoiseColor.getRed());
                    int vintageG = (int) (0.6 * sepiaNoiseColor.getGreen() + 0.4 * sepiaNoiseColor.getGreen());
                    int vintageB = (int) (0.6 * sepiaNoiseColor.getBlue() + 0.4 * sepiaNoiseColor.getBlue());

                    vintageR = Math.min(255, vintageR);
                    vintageG = Math.min(255, vintageG);
                    vintageB = Math.min(255, vintageB);

                    Color vintageColor = new Color(vintageR, vintageG, vintageB);

                    drawPic.setRGB(x, y, vintageColor.getRGB());
                }
            }
        }
    }


    private void AddNoise(int intensity) {
        Random random = new Random();
        int width = drawPic.getWidth();
        int height = drawPic.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color originalColor = new Color(originalPic.getRGB(x, y));
                int red = originalColor.getRed();
                int green = originalColor.getGreen();
                int blue = originalColor.getBlue();

                int noise = random.nextInt(intensity + 1);
                red = Math.min(Math.max(red + noise, 0), 255);
                green = Math.min(Math.max(green + noise, 0), 255);
                blue = Math.min(Math.max(blue + noise, 0), 255);

                Color newColor = new Color(red, green, blue);
                drawPic.setRGB(x, y, newColor.getRGB());
            }
        }
    }

    private void Vignette() {
        int width = drawPic.getWidth();
        int height = drawPic.getHeight();
        int centerX = width / 2;
        int centerY = height / 2;

        double maxDistance = Math.sqrt(centerX * centerX + centerY * centerY);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = originalPic.getRGB(x, y);
                Color color = new Color(rgb);

                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                double distance = Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));
                double darkenFactor = distance / maxDistance;

                r = (int) (r * (1 - darkenFactor));
                g = (int) (g * (1 - darkenFactor));
                b = (int) (b * (1 - darkenFactor));

                r = Math.max(0, Math.min(255, r));
                g = Math.max(0, Math.min(255, g));
                b = Math.max(0, Math.min(255, b));

                Color newColor = new Color(r, g, b);
                drawPic.setRGB(x, y, newColor.getRGB());
            }
        }
    }

    private void Darker() {
        int width = drawPic.getWidth();
        int height = drawPic.getHeight();

        int darkenFactor = 70;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = originalPic.getRGB(x, y);
                Color color = new Color(rgb);

                int r = color.getRed() - darkenFactor;
                int g = color.getGreen() - darkenFactor;
                int b = color.getBlue() - darkenFactor;

                r = Math.max(0, r);
                g = Math.max(0, g);
                b = Math.max(0, b);

                Color newColor = new Color(r, g, b);
                drawPic.setRGB(x, y, newColor.getRGB());
            }
        }
    }

    private void Lighter() {
        int width = drawPic.getWidth();
        int height = drawPic.getHeight();

        int lightenFactor = 60;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = originalPic.getRGB(x, y);
                Color color = new Color(rgb);

                int r = color.getRed() + lightenFactor;
                int g = color.getGreen() + lightenFactor;
                int b = color.getBlue() + lightenFactor;

                r = Math.min(255, r);
                g = Math.min(255, g);
                b = Math.min(255, b);

                Color newColor = new Color(r, g, b);
                drawPic.setRGB(x, y, newColor.getRGB());
            }
        }
    }

    private void Sepia() {
        int width = drawPic.getWidth();
        int height = drawPic.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = originalPic.getRGB(x, y);
                Color color = new Color(rgb);

                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                int tr = (int)(0.393 * r + 0.769 * g + 0.189 * b);
                int tg = (int)(0.349 * r + 0.686 * g + 0.168 * b);
                int tb = (int)(0.272 * r + 0.534 * g + 0.131 * b);

                tr = Math.min(255, tr);
                tg = Math.min(255, tg);
                tb = Math.min(255, tb);

                int newR = (int)(0.8 * tr + 0.2 * r);
                int newG = (int)(0.8 * tg + 0.2 * g);
                int newB = (int)(0.8 * tb + 0.2 * b);

                Color newColor = new Color(newR, newG, newB);
                drawPic.setRGB(x, y, newColor.getRGB());
            }
        }
    }


    private void Contrast(double factor) {
        int width = drawPic.getWidth();
        int height = drawPic.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = originalPic.getRGB(x, y);
                Color color = new Color(rgb);

                int r = (int)(((((color.getRed() / 255.0) - 0.5) * factor) + 0.5) * 255.0);
                int g = (int)(((((color.getGreen() / 255.0) - 0.5) * factor) + 0.5) * 255.0);
                int b = (int)(((((color.getBlue() / 255.0) - 0.5) * factor) + 0.5) * 255.0);

                r = Math.min(Math.max(r, 0), 255);
                g = Math.min(Math.max(g, 0), 255);
                b = Math.min(Math.max(b, 0), 255);

                Color newColor = new Color(r, g, b);
                drawPic.setRGB(x, y, newColor.getRGB());
            }
        }
    }

    private void Negative() {
        int width = drawPic.getWidth();
        int height = drawPic.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = originalPic.getRGB(x, y);
                Color color = new Color(rgb);

                int r = 255 - color.getRed();
                int g = 255 - color.getGreen();
                int b = 255 - color.getBlue();

                Color newColor = new Color(r, g, b);
                drawPic.setRGB(x, y, newColor.getRGB());
            }
        }
    }

    private void RemoveColor(ColorComponent componentToRemove) {
        int width = drawPic.getWidth();
        int height = drawPic.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = originalPic.getRGB(x, y);
                Color color = new Color(rgb);

                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                if (componentToRemove == ColorComponent.RED) {
                    r = 0;
                }
                else if (componentToRemove == ColorComponent.GREEN) {
                    g = 0;
                }
                else if (componentToRemove == ColorComponent.BLUE) {
                    b = 0;
                }

                Color newColor = new Color(r, g, b);
                drawPic.setRGB(x, y, newColor.getRGB());
            }
        }
    }

    enum ColorComponent {
        RED, GREEN, BLUE
    }



    private void Pixelate() {
        int pixelSize = 10;

        int width = drawPic.getWidth();
        int height = drawPic.getHeight();

        for (int y = 0; y < height; y += pixelSize) {
            for (int x = 0; x < width; x += pixelSize) {
                int pixelColor = originalPic.getRGB(x, y);
                for (int yd = y; (yd < y + pixelSize) && (yd < height); yd++) {
                    for (int xd = x; (xd < x + pixelSize) && (xd < width); xd++) {
                        drawPic.setRGB(xd, yd, pixelColor);
                    }
                }
            }
        }

    }

    private void Mirror() {
        int width = drawPic.getWidth();
        int height = drawPic.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width / 2; x++) {
                int rgb = originalPic.getRGB(x, y);
                drawPic.setRGB(x, y, rgb);
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = width / 2; x < width; x++) {
                int rgb = originalPic.getRGB(width - x - 1, y);
                drawPic.setRGB(x, y, rgb);
            }
        }
    }
    private void Tint() {
        int width = drawPic.getWidth();
        int height = drawPic.getHeight();

        int greenTint = 30;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = originalPic.getRGB(x, y);
                Color color = new Color(rgb);

                int r = color.getRed();
                int g = color.getGreen() + greenTint;
                int b = color.getBlue();

                g = Math.min(g, 255);

                Color newColor = new Color(r, g, b);
                drawPic.setRGB(x, y, newColor.getRGB());
            }
        }
    }



    private void Posterize() {
        int width = drawPic.getWidth();
        int height = drawPic.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = originalPic.getRGB(x, y);

                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                r = quantizeColor(r, 5);
                g = quantizeColor(g, 5);
                b = quantizeColor(b, 5);

                int newRGB = (r << 16) | (g << 8) | b;
                drawPic.setRGB(x, y, newRGB);
            }
        }
    }
    private static int quantizeColor(int color, int levels) {
        int interval = 256 / levels;
        return interval * (color / interval);
    }

    private void BlackAndWhite() {
        int width = drawPic.getWidth();
        int height = drawPic.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(originalPic.getRGB(x, y));
                int grayscale = (int) (color.getRed() * 0.299 + color.getGreen() * 0.587 + color.getBlue() * 0.114);
                Color newColor = new Color(grayscale, grayscale, grayscale);
                drawPic.setRGB(x, y, newColor.getRGB());
            }
        }
    }
    private void SaveImage(String outputPath) {
        if (!outputPath.toLowerCase().endsWith(".png")) {
            outputPath += ".png";
        }
        try {
            File outputFile = new File(outputPath);
            ImageIO.write(drawPic, "png", outputFile);
            JOptionPane.showMessageDialog(this, "Image saved successfully as " + outputFile.getName());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving image: " + e.getMessage());
        }
    }
    public void switchPanel(JPanel newPanel) {
        window.getContentPane().removeAll();
        window.add(newPanel);
        window.revalidate();
        window.repaint();
        newPanel.requestFocusInWindow();
    }
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(background.getImage(), -70, 0, 1650, 870, this);
        if (drawPic != null) {
            graphics.drawImage(drawPic, 319, 58, 875, 427, this);
        } else {
            System.out.println("Error: Image is not loaded");
        }
    }
    private void resetFilters() {
        if (originalPic != null) {
            Graphics2D g = drawPic.createGraphics();
            g.drawImage(originalPic, 0, 0, null);
            g.dispose();
        } else {
            System.out.println("Error: Original image is not loaded");
        }
    }
    private void resetPoints() {
        markedCount = 0;
        markingPoints = false;
        repaint();
    }
}
