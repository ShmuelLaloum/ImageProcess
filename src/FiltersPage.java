import resourcesManager.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

public class FiltersPage extends JPanel {
    private window window;
    public static final ImageIcon background = ImageManager.getImageIcon(ImageManager.ImageName.FILTER_BACKGROUND);
    private BufferedImage originalPic;
    private BufferedImage drawPic;
    private int divisionX = 1190;
    private boolean dragging = false;
    private Point dragStart;
    private int maxDividerHeight = 446;

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
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    Rectangle dividerBounds = new Rectangle(divisionX - 5, 0, 10, getHeight());
                    if (dividerBounds.contains(e.getPoint())) {
                        dragging = true;
                        dragStart = e.getPoint();
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                dragging = false;
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragging) {
                    int offsetX = e.getX() - dragStart.x;
                    int newDivisionX = divisionX + offsetX;
                    int minDivisionX = 363;
                    int maxDivisionX = 1180;
                    if (newDivisionX >= minDivisionX && newDivisionX <= maxDivisionX) {
                        divisionX = newDivisionX;
                    }
                    repaint();
                    dragStart = e.getPoint();
                }
            }
        });
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        g.drawImage(drawPic, 363, 55, 817, 416, null);

        Graphics2D g2d = (Graphics2D) g;
        Stroke oldStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(7.5f));
        g2d.setColor(Color.BLACK);
        int dividerHeight = Math.min(drawPic.getHeight() + 20, maxDividerHeight);
        g2d.drawLine(divisionX, 55, divisionX, 500);
        g2d.setStroke(oldStroke);
    }

    private void Vintage(int noiseIntensity) {
        int width = originalPic.getWidth();
        int height = originalPic.getHeight();
        int displayedWidth = 817;
        int imageXOffset = 363;
        int divisionLineInImageCoords = (divisionX - imageXOffset) * width / displayedWidth;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x <= divisionLineInImageCoords) {
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
                }else {
                    drawPic.setRGB(x,y,originalPic.getRGB(x,y));
                }
            }
        }
    }



    private void AddNoise(int intensity) {
        Random random = new Random();
        int width = originalPic.getWidth();
        int height = originalPic.getHeight();
        int displayedWidth = 817;
        int imageXOffset = 363;
        int divisionLineInImageCoords = (divisionX - imageXOffset) * width / displayedWidth;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x <= divisionLineInImageCoords) {
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
                } else {
                    drawPic.setRGB(x,y,originalPic.getRGB(x,y));
                }
            }
        }
    }

    private void Vignette() {
        int width = originalPic.getWidth();
        int height = originalPic.getHeight();
        int displayedWidth = 817;
        int imageXOffset = 363;
        int divisionLineInImageCoords = (divisionX - imageXOffset) * width / displayedWidth;

        int centerX = width / 2;
        int centerY = height / 2;

        double maxDistance = Math.sqrt(centerX * centerX + centerY * centerY);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x <= divisionLineInImageCoords) {
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
                } else {
                    drawPic.setRGB(x,y,originalPic.getRGB(x,y));
                }
            }
        }
    }

    private void Darker() {
        int width = originalPic.getWidth();
        int height = originalPic.getHeight();
        int displayedWidth = 817;
        int imageXOffset = 363;
        int divisionLineInImageCoords = (divisionX - imageXOffset) * width / displayedWidth;


        int darkenFactor = 70;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x <= divisionLineInImageCoords) {
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
                } else {
                    drawPic.setRGB(x,y,originalPic.getRGB(x,y));
                }
            }
        }
    }

    private void Lighter() {
        int width = originalPic.getWidth();
        int height = originalPic.getHeight();
        int displayedWidth = 817;
        int imageXOffset = 363;
        int divisionLineInImageCoords = (divisionX - imageXOffset) * width / displayedWidth;

        int lightenFactor = 60;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x <= divisionLineInImageCoords) {
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
                } else {
                    drawPic.setRGB(x,y,originalPic.getRGB(x,y));
                }
            }
        }
    }

    private void Sepia() {
        int width = originalPic.getWidth();
        int height = originalPic.getHeight();
        int displayedWidth = 817;
        int imageXOffset = 363;
        int divisionLineInImageCoords = (divisionX - imageXOffset) * width / displayedWidth;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x <= divisionLineInImageCoords) {
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
                } else {
                    drawPic.setRGB(x,y,originalPic.getRGB(x,y));
                }
            }
        }
    }


    private void Contrast(double factor) {
        int width = originalPic.getWidth();
        int height = originalPic.getHeight();
        int displayedWidth = 817;
        int imageXOffset = 363;
        int divisionLineInImageCoords = (divisionX - imageXOffset) * width / displayedWidth;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x <= divisionLineInImageCoords) {
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
                } else {
                    drawPic.setRGB(x,y,originalPic.getRGB(x,y));
                }
            }
        }
    }

    private void Negative() {
        int width = originalPic.getWidth();
        int height = originalPic.getHeight();
        int displayedWidth = 817;
        int imageXOffset = 363;
        int divisionLineInImageCoords = (divisionX - imageXOffset) * width / displayedWidth;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x <= divisionLineInImageCoords) {
                    int rgb = originalPic.getRGB(x, y);
                    Color color = new Color(rgb);

                    int r = 255 - color.getRed();
                    int g = 255 - color.getGreen();
                    int b = 255 - color.getBlue();

                    Color newColor = new Color(r, g, b);
                    drawPic.setRGB(x, y, newColor.getRGB());
                } else {
                    drawPic.setRGB(x,y,originalPic.getRGB(x,y));
                }
            }
        }
    }

    private void RemoveColor(ColorComponent componentToRemove) {
        int width = originalPic.getWidth();
        int height = originalPic.getHeight();
        int displayedWidth = 817;
        int imageXOffset = 363;
        int divisionLineInImageCoords = (divisionX - imageXOffset) * width / displayedWidth;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x <= divisionLineInImageCoords) {
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
                } else {
                    drawPic.setRGB(x,y,originalPic.getRGB(x,y));
                }
            }
        }
    }

    enum ColorComponent {
        RED, GREEN, BLUE
    }



    private void Pixelate() {
        int pixelSize = 10;

        int width = originalPic.getWidth();
        int height = originalPic.getHeight();
        int displayedWidth = 817;
        int imageXOffset = 363;
        int divisionLineInImageCoords = (divisionX - imageXOffset) * width / displayedWidth;


        for (int y = 0; y < height; y += pixelSize) {
            for (int x = 0; x < width; x += pixelSize) {
                if (x <= divisionLineInImageCoords) {
                    int pixelColor = originalPic.getRGB(x, y);
                    for (int yd = y; (yd < y + pixelSize) && (yd < height); yd++) {
                        for (int xd = x; (xd < x + pixelSize) && (xd < width); xd++) {
                            drawPic.setRGB(xd, yd, pixelColor);
                        }
                    }
                } else {
                    drawPic.setRGB(x,y,originalPic.getRGB(x,y));
                }
            }
        }

    }

    private void Mirror() {
        int width = originalPic.getWidth();
        int height = originalPic.getHeight();
        int displayedWidth = 817;
        int imageXOffset = 363;
        int divisionLineInImageCoords = (divisionX - imageXOffset) * width / displayedWidth;


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width ; x++) {
                if (x <= divisionLineInImageCoords) {
                    int leftRGB = originalPic.getRGB(x, y);
                    int rightRGB = originalPic.getRGB(width - x - 1, y);

                    drawPic.setRGB(x, y, rightRGB);
                    drawPic.setRGB(width - x - 1, y, leftRGB);
                } else {
                    drawPic.setRGB(x,y,originalPic.getRGB(x,y));
                }
            }
        }
    }

    private void Tint() {
        int width = originalPic.getWidth();
        int height = originalPic.getHeight();
        int displayedWidth = 817;
        int imageXOffset = 363;
        int divisionLineInImageCoords = (divisionX - imageXOffset) * width / displayedWidth;


        int greenTint = 30;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x <= divisionLineInImageCoords) {
                    int rgb = originalPic.getRGB(x, y);
                    Color color = new Color(rgb);

                    int r = color.getRed();
                    int g = color.getGreen() + greenTint;
                    int b = color.getBlue();

                    g = Math.min(g, 255);

                    Color newColor = new Color(r, g, b);
                    drawPic.setRGB(x, y, newColor.getRGB());
                } else {
                    drawPic.setRGB(x,y,originalPic.getRGB(x,y));
                }
            }
        }
    }
    private void Posterize() {
        int width = originalPic.getWidth();
        int height = originalPic.getHeight();
        int displayedWidth = 817;
        int imageXOffset = 363;
        int divisionLineInImageCoords = (divisionX - imageXOffset) * width / displayedWidth;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x <= divisionLineInImageCoords) {
                    int rgb = originalPic.getRGB(x, y);

                    int r = (rgb >> 16) & 0xFF;
                    int g = (rgb >> 8) & 0xFF;
                    int b = rgb & 0xFF;

                    r = quantizeColor(r, 5);
                    g = quantizeColor(g, 5);
                    b = quantizeColor(b, 5);

                    int newRGB = (r << 16) | (g << 8) | b;
                    drawPic.setRGB(x, y, newRGB);
                } else {
                    drawPic.setRGB(x,y,originalPic.getRGB(x,y));
                }
            }
        }
    }
    private static int quantizeColor(int color, int levels) {
        int interval = 256 / levels;
        return interval * (color / interval);
    }

    private void BlackAndWhite() {
        int width = originalPic.getWidth();
        int height = originalPic.getHeight();
        int displayedWidth = 817;
        int imageXOffset = 363;
        int divisionLineInImageCoords = (divisionX - imageXOffset) * width / displayedWidth;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x <= divisionLineInImageCoords) {
                    Color color = new Color(originalPic.getRGB(x, y));
                    int grayscale = (int) (color.getRed() * 0.299 + color.getGreen() * 0.587 + color.getBlue() * 0.114);
                    Color newColor = new Color(grayscale, grayscale, grayscale);
                    drawPic.setRGB(x, y, newColor.getRGB());
                } else {
                    drawPic.setRGB(x,y,originalPic.getRGB(x,y));
                }
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
    private void resetFilters() {
        if (originalPic != null) {
            Graphics2D g = drawPic.createGraphics();
            g.drawImage(originalPic, 0, 0, null);
            g.dispose();
        } else {
            System.out.println("Error: Original image is not loaded");
        }
    }
}
