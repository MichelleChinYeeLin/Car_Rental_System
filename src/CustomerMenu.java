import javax.swing.*;
import java.awt.*;

public class CustomerMenu {

    private JFrame frame;
    private JLabel title;

    public CustomerMenu(){
        frame = new JFrame();
        title = new JLabel("Main Menu");

        title.setBounds(70,20,200,30);
        title.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,30));

        frame.setLayout(null);
//        frame.setIconImage(logo.getImage());
        frame.setSize(300,300);
        frame.setResizable(false);
//        frame.getContentPane().setBackground(new Color(240,205,151));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 先这样
        frame.add(title);
    }

    public JFrame getFrame() {
        return frame;
    }

}
