import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerMenu implements ActionListener {

    private JFrame frame;
    private JLabel title;
    private JButton logout;
    private JButton[] buttons;

    public CustomerMenu(){
        frame = new JFrame();
        title = new JLabel("Main Menu");
        logout = new JButton("Logout");

        title.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,30));

        title.setBounds(70,40,200,30);
        logout.setBounds(200,10,80,30);

        logout.addActionListener(this);

        buttons = new JButton[]{logout};
        GUI.JButtonSetup(buttons);
        GUI.JFrameSetup(frame);
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 先这样
        frame.add(title);
        frame.add(logout);
    }

    public JFrame getFrame() {
        return frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == logout){
                CarRentalSystem.loginCustomer = null;
                frame.setVisible(false);
                CarRentalSystem.homePage.getFrame().setVisible(true);
            }
        } catch (Exception exception){
            JOptionPane.showMessageDialog(frame, "Invalid move!");
        }
    }
}
