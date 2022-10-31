import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage implements ActionListener {

    private JFrame frame;
    private JButton login, signUp, quit;
    private JLabel label;
//    private ImageIcon logo, homeBackground;
    private JButton[] buttons;

    public HomePage(){
        frame = new JFrame("Michelle & HeYuan's Car Rental System");
        login = new JButton("Login");
        signUp = new JButton("Sign Up");
        quit = new JButton("Quit");
        label = new JLabel();
//        logo = new ImageIcon("Logo.png"); // 可以去改
//        homeBackground = new ImageIcon("HomePage.png");

//        login.setFocusable(false);
//        login.setFont(new Font(Font.DIALOG, Font.ITALIC, 12));
//        login.setBackground(new Color(212, 183, 185));
        login.addActionListener(this);
        login.setBounds(60,200,80,40);

//        signUp.setFocusable(false);
//        signUp.setFont(new Font(Font.DIALOG, Font.ITALIC, 12));
//        signUp.setBackground(new Color(212, 183, 185));
        signUp.addActionListener(this);
        signUp.setBounds(160,200,80,40);

//        quit.setFocusable(false);
//        quit.setFont(new Font(Font.DIALOG, Font.ITALIC, 12));
//        quit.setBackground(new Color(212, 183, 185));
        quit.addActionListener(this);
        quit.setBounds(220,10,60,30);

//        label.setIcon(homeBackground);
//        label.setBounds(-550, -370, 1748,1240); // 其实好像蛮丑的

//        frame.setLayout(null);
//        frame.setIconImage(logo.getImage());
//        frame.setResizable(false);
//        frame.getContentPane().setBackground(new Color(155, 159, 177)); // 颜色可以调一下蛮丑的
//        frame.setLocationRelativeTo(null);
//        frame.add(label);
        buttons = new JButton[]{login, signUp, quit};
        GUI.JButtonSetup(buttons);
        GUI.JFrameSetup(frame);
        frame.setSize(300,300);
        frame.add(login);
        frame.add(signUp);
        frame.add(quit);
        frame.setVisible(true);
    }

    public JFrame getFrame(){
        return frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login){
            frame.setVisible(false);
            CarRentalSystem.loginPage.getFrame().setVisible(true);
        } else if (e.getSource() == signUp) {
            frame.setVisible(false);
            CarRentalSystem.signUpPage.getFrame().setVisible(true);
        } else if (e.getSource() == quit) {
            System.exit(0); // 先这样 过后再看要不要做一个新的confirm page hmmmmmmmmmmmmmmmm
        }
    }
}
