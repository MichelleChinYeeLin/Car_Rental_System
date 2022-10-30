import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage implements ActionListener {

    private JFrame frame;
    private JButton login, cancel;
    private JLabel title, usernameLabel, passwordLabel;
    private JTextField username, password;
    private ImageIcon logo;

    public LoginPage() {
        frame = new JFrame("Login Page");
        login = new JButton("Log In");
        cancel = new JButton("Cancel");
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        title = new JLabel("LOGIN");
        username = new JTextField();
        password = new JTextField();
        logo = new ImageIcon("Logo.png");

        login.setFocusable(false);
        login.addActionListener(this);
        login.setBounds(60,200,80,40);
        login.setFont(new Font(Font.DIALOG, Font.ITALIC, 12));
//        login.setBackground(new Color(227,217,176));

        cancel.setFocusable(false);
        cancel.addActionListener(this);
        cancel.setBounds(160,200,80,40);
        cancel.setFont(new Font(Font.DIALOG, Font.ITALIC, 12));
//        cancel.setBackground(new Color(227,217,176));

        title.setBounds(90,20,200,30);
        title.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,30));
        usernameLabel.setBounds(10,60,200,20);
        usernameLabel.setFont(new Font(Font.SERIF, Font.ITALIC, 12));
        passwordLabel.setBounds(10,120,200,20);
        passwordLabel.setFont(new Font(Font.SERIF, Font.ITALIC, 12));

        username.setBounds(10, 80,260, 40);
        password.setBounds(10,140, 260, 40);

        frame.setLayout(null);
        frame.setIconImage(logo.getImage());
        frame.setSize(300,300);
        frame.setResizable(false);
//        frame.getContentPane().setBackground(new Color(240,205,151));
        frame.setLocationRelativeTo(null);
        frame.add(title);
        frame.add(usernameLabel);
        frame.add(passwordLabel);
        frame.add(login);
        frame.add(cancel);
        frame.add(username);
        frame.add(password);
    }

    public JFrame getFrame(){
        return frame;
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == login){
                String usernameInput = username.getText();
                String passwordInput = password.getText();
                // Non-static method 'login()' cannot be referenced from a static context 不懂什么意思我先把user那里的弄成static
                if (User.login(usernameInput, passwordInput)){
                    frame.setVisible(false);
                    CarRentalSystem.mainMenu.getFrame().setVisible(true);
                }
            } else if (e.getSource() == cancel) {
                frame.setVisible(false);
                CarRentalSystem.homePage.getFrame().setVisible(true);
            }
        } catch (Exception exception){
            JOptionPane.showMessageDialog(frame,"Invalid move");
        }
    }
}
