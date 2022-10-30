import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenu implements ActionListener {

    private JFrame frame;
    private JLabel title;
    private JButton logout;
    private ImageIcon logo;

    public AdminMenu(){
        frame = new JFrame();
        logo = new ImageIcon("Logo.png");
        title = new JLabel("Main Menu");
        logout = new JButton("Logout");

        title.setBounds(70,40,200,30);
        title.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,30));

        logout.setFocusable(false);
        logout.addActionListener(this);
        logout.setBounds(200,10,80,30);
        logout.setFont(new Font(Font.DIALOG, Font.ITALIC, 12));
        logout.setBackground(new Color(212, 183, 185));

        frame.setLayout(null);
        frame.setIconImage(logo.getImage());
        frame.setSize(300,300);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(155, 159, 177));
        frame.setLocationRelativeTo(null);
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
                CarRentalSystem.loginAdmin = null;
                frame.setVisible(false);
                CarRentalSystem.homePage.getFrame().setVisible(true);
            }
        } catch (Exception exception){
            JOptionPane.showMessageDialog(frame, "Invalid move!");
        }
    }
}
