import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage implements ActionListener {

    private JFrame frame;
    private JButton login, signUp, quit;
    private JLabel label;
//    private ImageIcon homeBackground;
    private JButton[] buttons;

    public HomePage(){
        frame = new JFrame("Michelle & HeYuan's Car Rental System");
        login = new JButton("Login");
        signUp = new JButton("Sign Up");
        quit = new JButton("Quit");
        label = new JLabel();
//        homeBackground = new ImageIcon("HomePage.png");
//        label.setIcon(homeBackground);
//        label.setBounds(-550, -370, 1748,1240); // 其实好像蛮丑的

        login.setBounds(60,200,80,40);
        signUp.setBounds(160,200,80,40);
        quit.setBounds(220,10,60,30);

        login.addActionListener(this);
        signUp.addActionListener(this);
        quit.addActionListener(this);

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
