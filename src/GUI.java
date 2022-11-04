import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;

public class GUI {

    private static final Color frameBackgroundColor = new Color(40, 48, 76); //Navy Blue
    private static final Color buttonColor = new Color(255,255,255);

    public static Color getFrameBackgroundColor(){
        return frameBackgroundColor;
    }

    public static Color getButtonColor(){
        return buttonColor;
    }

    public static void JButtonSetup(JButton[] buttons){
        for (JButton i : buttons) {
            i.setFocusable(false);
            i.setFont(new Font(Font.SERIF, Font.BOLD, 20));
            i.setBackground(getButtonColor());
            i.setPreferredSize(new Dimension(150, 40));
        }
    }

    public static void JFrameSetup(JFrame frame){
        ImageIcon logo = new ImageIcon("src/images/Logo.png");
        frame.setSize(750,550);
        frame.setIconImage(logo.getImage());
        frame.setResizable(true);
        frame.getContentPane().setBackground(getFrameBackgroundColor());
        frame.setLocation(600, 180);
    }

    public static void JLabelSetup(JLabel[] labels){
        for (JLabel i : labels) {
            i.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
            i.setHorizontalAlignment(JLabel.LEFT);
        }
    }
}
