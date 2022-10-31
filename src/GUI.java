import javax.swing.*;
import java.awt.*;

public class GUI {

    public static void JButtonSetup(JButton[] buttons){
        for (JButton i : buttons) {
            i.setFocusable(false);
            i.setFont(new Font(Font.DIALOG, Font.ITALIC, 12));
            i.setBackground(new Color(212, 183, 185));
        }
    }

    public static void JFrameSetup(JFrame frame){
        ImageIcon logo = new ImageIcon("Logo.png");
        frame.setLayout(null);
        frame.setIconImage(logo.getImage());
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(155, 159, 177)); // 颜色可以调一下蛮丑的
        frame.setLocation(600, 180);
    }

    public static void JLabelSetup(JLabel[] labels){
        for (JLabel i : labels) {
            i.setFont(new Font(Font.SERIF, Font.ITALIC, 12));
        }
    }
}
