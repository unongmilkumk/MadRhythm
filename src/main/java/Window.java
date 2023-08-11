import javax.swing.*;

public class Window extends JFrame {
    public Window() {
        setSize(1920, 1080);
        addKeyListener(new Content.KeyListener());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(new Content());
        setVisible(true);
    }
}

