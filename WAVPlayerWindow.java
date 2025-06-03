import java.awt.*;
import java.io.File;
import javax.sound.sampled.*;
import javax.swing.*;

public class WAVPlayerWindow {

    public WAVPlayerWindow() {
        // Only show window when object is created
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    // Separate method to start playing music
    public void startMusic() {
        playMusic("GLaDOS - Still Alive.wav");
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("GLaDOS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 150);

        JLabel label = new JLabel("Now Playing: Still Alive.wav", SwingUtilities.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        frame.getContentPane().add(label);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void playMusic(String fileName) {
        new Thread(() -> {
            try {
                File soundFile = new File(fileName);
                if (!soundFile.exists()) {
                    System.err.println("File not found: " + fileName);
                    return;
                }

                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();  // Plays once

                // Uncomment below to loop
                // clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}