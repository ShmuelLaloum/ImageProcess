import resourcesManager.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ImagePathChooser extends JPanel {
    private String imagePath;

    public ImagePathChooser(int x, int y) {
        this.setLayout(new BorderLayout());
        this.setBounds(690, 370, x, y);
        this.setOpaque(false);

        JButton uploadButton = new JButton(ImageManager.getImageIcon(ImageManager.ImageName.UPLOAD_BUTTON));
        uploadButton.setOpaque(false);
        uploadButton.setContentAreaFilled(false);
        uploadButton.setBorderPainted(false);
        uploadButton.setFocusPainted(false);
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseImage();
            }
        });
        this.add(uploadButton);
    }

    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    String filename = f.getName().toLowerCase();
                    return filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".png") || filename.endsWith(".gif");
                }
            }

            @Override
            public String getDescription() {
                return "Image Files (JPG, JPEG, PNG, GIF)";
            }
        });

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            imagePath = selectedFile.getPath();
            setImagePath(imagePath);
        }
    }

    private void setImagePath(String path) {
        this.imagePath = path;
    }

    public String getImagePath() {
        return imagePath;
    }
}


