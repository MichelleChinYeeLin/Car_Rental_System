import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginPage implements ActionListener {

    private JFrame frame;
    private Button login, cancel;
    private JLabel title, usernameLabel, passwordLabel;
    private JTextField username, password;

    public loginPage() {
        frame = new JFrame("Login Page");
        login = new Button("Log In");
        cancel = new Button("Cancel");
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        title = new JLabel("LOGIN");
        username = new JTextField();
        password = new JTextField();

        login.setFocusable(false);
        login.addActionListener(this);
        login.setBounds(80,200,50,40);
//        login.setBackground(new Color(227,217,176));

        cancel.setFocusable(false);
        cancel.addActionListener(this);
        cancel.setBounds(170,200,50,40);
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
//        frame.setIconImage(logo.getImage());
        frame.setSize(300,300);
        frame.setResizable(false);
//        frame.getContentPane().setBackground(new Color(240,205,151)); // 颜色再调一下真的很丑 你给我做gui是个错误的选择
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
//                User.login(usernameInput, passwordInput); // Non-static method 'login()' cannot be referenced from a static context
            } else if (e.getSource() == cancel) {
                frame.setVisible(false);
                CarRentalSystem.homePage.getFrame().setVisible(true);
            }
        } catch (Exception exception){
            JOptionPane.showMessageDialog(frame,"Invalid move2");
        }
    }
}
