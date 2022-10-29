import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage implements ActionListener {

    private JFrame frame;
    private Button login, signUp, quit;
    private JLabel label;
    private ImageIcon logo, homeBackground;

    public HomePage(){
        frame = new JFrame("Michelle & HeYuan's Car Rental System");
        login = new Button("Login");
        signUp = new Button("Sign Up");
        quit = new Button("Quit");
        label = new JLabel();
        logo = new ImageIcon("Logo.png"); // 可以去改
        homeBackground = new ImageIcon("HomePage.png");

        login.setFocusable(false);
        login.addActionListener(this);
        login.setBounds(80,200,50,40);
        login.setFont(new Font(Font.DIALOG, Font.ITALIC, 12));
        login.setBackground(new Color(212, 183, 185));

        signUp.setFocusable(false);
        signUp.addActionListener(this);
        signUp.setBounds(170,200,50,40);
        signUp.setFont(new Font(Font.DIALOG, Font.ITALIC, 12));
        signUp.setBackground(new Color(212, 183, 185));

        quit.setFocusable(false);
        quit.addActionListener(this);
        quit.setBounds(230,10,50,20);
        quit.setFont(new Font(Font.DIALOG, Font.ITALIC, 12));
        quit.setBackground(new Color(212, 183, 185));

//        label.setIcon(homeBackground);
//        label.setBounds(-550, -370, 1748,1240); // 其实好像蛮丑的

        frame.setLayout(null);
        frame.setIconImage(logo.getImage());
        frame.setSize(300,300);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(155, 159, 177)); // 颜色可以调一下蛮丑的
        frame.setLocationRelativeTo(null);
//        frame.add(label);
        frame.add(login);
        frame.add(signUp);
        frame.add(quit);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
