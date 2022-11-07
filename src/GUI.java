import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.io.File;

public class GUI {

    private static final Color frameBackgroundColor = new Color(40, 48, 76); //Navy Blue
    private static final int frameLength = 750;
    private static final int frameHeight = 550;

    public static Color getFrameBackgroundColor(){
        return frameBackgroundColor;
    }

    public static void JButtonSetup(JButton[] buttons){
        for (JButton button : buttons) {
            button.setFocusable(false);
            button.setFont(new Font(Font.SERIF, Font.BOLD, 20));
            button.setBackground(Color.white);
            button.setPreferredSize(new Dimension(150, 40));
        }
    }

    public static void subJButtonSetup(JButton[] buttons){
        for (JButton button : buttons) {
            button.setFocusable(false);
            button.setFont(new Font(Font.SERIF, Font.BOLD, 16));
            button.setBackground(Color.white);
            button.setPreferredSize(new Dimension(100, 40));
        }
    }

    public static void JFrameSetup(JFrame frame){
        ImageIcon logo = new ImageIcon("src/images/Logo.png");
        frame.setSize(frameLength,frameHeight);
        frame.setIconImage(logo.getImage());
        frame.setResizable(true);
        frame.getContentPane().setBackground(getFrameBackgroundColor());
        frame.setLocation(600, 180);
    }

    public static void JLabelSetup(JLabel[] labels){
        for (JLabel label : labels) {
            label.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
            label.setHorizontalAlignment(JLabel.LEFT);
        }
    }

    public static void JPanelSetup(JPanel[] panels){
        for (JPanel panel : panels) {
            panel.setVisible(false);
            panel.setBackground(Color.white);
        }
    }

    public static void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}
