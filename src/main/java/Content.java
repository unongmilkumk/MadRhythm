import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;

public class Content extends JPanel implements ActionListener {
    static int BPM = 60;
    Timer timer;
    public static ArrayList<Long> tile = new ArrayList<>(java.util.List.of());
    public static ArrayList<Long> tile1 = new ArrayList<>(java.util.List.of(1700L, 2200L, 2680L, 2920L, 3160L, 3400L, 3620L));
    public static ArrayList<Long> tile2 = new ArrayList<>(java.util.List.of(1700L, 1820L, 1940L, 2200L, 2320L, 2440L, 2680L,
            2800L, 2920L, 3040L, 3160L, 3400L, 3620L));
    public static ArrayList<Long> tile3 = new ArrayList<>(java.util.List.of());
    public static ArrayList<Boolean> key = new ArrayList<>();
    public static ArrayList<Boolean> key1 = new ArrayList<>();
    public static ArrayList<Boolean> key2 = new ArrayList<>();
    public static ArrayList<Boolean> key3 = new ArrayList<>();
    public Content() {
        tile.forEach((x) -> key.add(false));
        tile1.forEach((x) -> key1.add(false));
        tile2.forEach((x) -> key2.add(false));
        tile3.forEach((x) -> key3.add(false));
        try {
            playAudio(AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource("Stage 1.wav")));
        } catch (UnsupportedAudioFileException | IOException ex) {
            ex.printStackTrace();
        }
        timer = new Timer(0, this);
        timer.start();
    }

    public void playAudio(AudioInputStream ais) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.stop();
            clip.open(ais);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f);
            clip.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void restartGame() {
        dTick = System.currentTimeMillis();
        tile.forEach((x) -> key.add(false));
        tile1.forEach((x) -> key1.add(false));
        tile2.forEach((x) -> key2.add(false));
        tile3.forEach((x) -> key3.add(false));
    }

    public static long dTick = System.currentTimeMillis();
    public static long tick;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, 1920, 1080);
        g.setColor(new Color(180, 180, 180));
        g.fillRect(850, 0, 10, 750);
        g.fillRect(970, 0, 10, 750);
        g.fillRect(1090, 0, 10, 750);
        g.fillRect(720, 700, 510, 10);
        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i < tile.size(); i++) {
            if (!key.get(i)) g.fillRect(745, tile.get(i).intValue() * -1 + (int) (((tick * BPM) / 60) / 2), 100, 25);
        }
        for (int i = 0; i < tile1.size(); i++) {
            if (!key1.get(i)) g.fillRect(865, tile1.get(i).intValue() * -1 + (int) (((tick * BPM) / 60) / 2), 100, 25);
        }
        for (int i = 0; i < tile2.size(); i++) {
            if (!key2.get(i)) g.fillRect(985, tile2.get(i).intValue() * -1 + (int) (((tick * BPM) / 60) / 2), 100, 25);
        }
        for (int i = 0; i < tile3.size(); i++) {
            if (!key3.get(i)) g.fillRect(1105, tile3.get(i).intValue() * -1 + (int) (((tick * BPM) / 60) / 2), 100, 25);
        }
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(720, 750, 510, 500);
        g.setColor(Color.BLACK);
        g.fillRect(720, 0, 20, 750);
        g.fillRect(1210, 0, 20, 750);
        g.fillRect(720, 750, 510, 25);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tick = System.currentTimeMillis() - dTick;
        repaint();
    }

    public static class KeyListener extends KeyAdapter {
        public void def(ArrayList<Long> ti, ArrayList<Boolean> ke) {
            boolean a = false;
            for (int i = 0; i < ti.size(); i++) {
                long o = ti.get(i).intValue() * -1L + (int) (((tick * BPM) / 60) / 2);
                if (725 <= o && o <= 775) {
                    System.out.println("Perfect");
                    ke.set(i, true);
                    a = true;
                    break;
                } else if (700 <= o && o <= 800) {
                    System.out.println("Good");
                    ke.set(i, true);
                    a = true;
                    break;
                }
            }
            if (!a) System.out.println("Miss");
        }
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_D) {
                def(tile, key);
            }
            if (e.getKeyCode() == KeyEvent.VK_F) {
                def(tile1, key1);
            }
            if (e.getKeyCode() == KeyEvent.VK_J) {
                def(tile2, key2);
            }
            if (e.getKeyCode() == KeyEvent.VK_K) {
                def(tile3, key3);
            }
            if (e.getKeyCode() == KeyEvent.VK_P) {
                System.out.println((((tick * BPM) / 60) / 2));
            }
        }
    }
}
