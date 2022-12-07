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

    public static Font getDefaultFont(){
        return new Font("Serif", Font.PLAIN, 16);
    }

    public static void JButtonSetup(JButton[] buttons){
        for (JButton button : buttons) {
            button.setFocusable(false);
            button.setFont(new Font(Font.SERIF, Font.BOLD, 20));
            button.setBackground(Color.white);
            button.setPreferredSize(new Dimension(150, 40));
        }
    }

    public static void JButtonSetup(JButton button){
        button.setFocusable(false);
        button.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        button.setBackground(Color.white);
        button.setPreferredSize(new Dimension(150, 40));
    }

    public static void subJButtonSetup(JButton[] buttons, Dimension dim) {
        for (JButton button : buttons) {
            button.setFocusable(false);
            button.setFont(new Font(Font.SERIF, Font.BOLD, 16));
            button.setBackground(Color.white);
            button.setPreferredSize(dim);
        }
    }

    public static void JFrameSetup(JFrame frame){
        ImageIcon logo = new ImageIcon("src/images/Logo.png");
        frame.setSize(frameLength,frameHeight);
        frame.setIconImage(logo.getImage());
        frame.getContentPane().setBackground(getFrameBackgroundColor());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public static void JLabelSetup(JLabel[] labels){
        for (JLabel label : labels) {
            label.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
            label.setHorizontalAlignment(JLabel.LEFT);
        }
    }

    public static void JLabelSetup(JLabel label){
        label.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        label.setHorizontalAlignment(JLabel.LEFT);
    }

    public static void JLabelTitleSetup(JLabel label){
        label.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
    }

    public static void JPanelSetup(JPanel[] panels){
        for (JPanel panel : panels) {
            panel.setVisible(false);
            panel.setBackground(Color.white);
        }
    }

    public static void JSliderSetup(JSlider slider, boolean inCarFunctions){
        int maxInInt;

        if (inCarFunctions){
            maxInInt = Car.calcMaxPrice();
        } else {
            maxInInt = Booking.calcMaxTotalPrice();
        }

        slider.setMaximum(maxInInt);
        slider.setMajorTickSpacing(maxInInt/5);
        slider.setMinorTickSpacing(maxInInt/10);
        slider.setFont(GUI.getDefaultFont());
        slider.setPaintTicks(true);
    }

    public static void disableFields(JComponent[] components){
        for (JComponent i : components) {
            if (i instanceof JTextField){
                ((JTextField) i).setEditable(false);
            }
            if (i instanceof JPasswordField){
                ((JPasswordField) i).setEditable(false);
            }
            if (i instanceof JRadioButton || i instanceof JSpinner){
                i.setEnabled(false);
            }
        }
    }

    public static void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception exception) {
            System.out.println("Error with playing sound.");
            exception.printStackTrace();
        }
    }

    public static void resetFields(JComponent[] components){
        for (JComponent i : components) {
            i.setEnabled(true);
            if (i instanceof JTextField){
                ((JTextField) i).setText("");
            }
            if (i instanceof JRadioButton){
                ((JRadioButton) i).setSelected(false);
            }
            if (i instanceof JLabel){
                ((JLabel) i).setText("");
            }
            if (i instanceof JComboBox<?>){
                ((JComboBox<?>) i).setSelectedIndex(0);
            }
        }
    }
}
