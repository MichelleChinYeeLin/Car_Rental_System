import javax.swing.*;

public class HomePage {

    private JFrame frame;
    private JLabel label;
    private ImageIcon icon;

    public HomePage(){
        frame = new JFrame("MichelleDaShuaiGe Car Rental System");
        label = new JLabel();
        icon = new ImageIcon("Chicken.png");

        label.setIcon(icon);

        frame.setSize(800,800);
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
