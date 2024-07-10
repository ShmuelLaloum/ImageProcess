import javax.swing.*;
import java.awt.*;

public class window extends JFrame{
    public window() {
        this.setTitle("Image process");
        this.setLayout(null);
        this.setSize(1920, 1080);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        lobby lobby = new lobby(1920, 1080, this);
        this.add(lobby);
        this.setVisible(true);
    }
}
