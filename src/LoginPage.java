import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage implements ActionListener {

    private JFrame frame;
    private JButton login, cancel;
    private JLabel title, usernameLabel, passwordLabel;
    private JTextField username;
    private JPasswordField password;
    private JComboBox<String> userTypeSelect;
    private ImageIcon logo;

    public LoginPage() {
        frame = new JFrame("Login Page");
        login = new JButton("Log In");
        cancel = new JButton("Cancel");
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        title = new JLabel("LOGIN");
        username = new JTextField();
        password = new JPasswordField();
        String[] userType = {"Customer", "Admin"};
        userTypeSelect = new JComboBox<String>(userType);
        logo = new ImageIcon("Logo.png");

        login.setFocusable(false);
        login.addActionListener(this);
        login.setBounds(60,200,80,40);
        login.setFont(new Font(Font.DIALOG, Font.ITALIC, 12));
        login.setBackground(new Color(212, 183, 185));

        cancel.setFocusable(false);
        cancel.addActionListener(this);
        cancel.setBounds(160,200,80,40);
        cancel.setFont(new Font(Font.DIALOG, Font.ITALIC, 12));
        cancel.setBackground(new Color(212, 183, 185));

        title.setBounds(80,20,200,40);
        title.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,38));
        usernameLabel.setBounds(10,100,200,20);
        usernameLabel.setFont(new Font(Font.SERIF, Font.ITALIC, 12));
        passwordLabel.setBounds(10,140,200,20);
        passwordLabel.setFont(new Font(Font.SERIF, Font.ITALIC, 12));

        username.setBounds(10, 120,260, 20);
        password.setBounds(10,160, 260, 20);

        userTypeSelect.setBounds(10,80,80,20);
        userTypeSelect.setFont(new Font(Font.SERIF, Font.ITALIC, 12));

        frame.setLayout(null);
        frame.setIconImage(logo.getImage());
        frame.setSize(300,300);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(155, 159, 177));
        frame.setLocationRelativeTo(null);
        frame.add(title);
        frame.add(usernameLabel);
        frame.add(passwordLabel);
        frame.add(login);
        frame.add(cancel);
        frame.add(username);
        frame.add(password);
        frame.add(userTypeSelect);
    }

    public JFrame getFrame(){
        return frame;
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == login){
                String usernameInput = username.getText();
                String passwordInput = String.valueOf(password.getPassword());
                String userTypeInput = (String) userTypeSelect.getSelectedItem();
                if (userTypeInput.equals("Customer")){
                    if (Customer.login(usernameInput, passwordInput)){
                        frame.setVisible(false);
                        CarRentalSystem.customerMenu.getFrame().setVisible(true);
                    }else {
                        JOptionPane.showMessageDialog(frame, "Invalid account credentials!");
                    }
                } else if (userTypeInput.equals("Admin")) {
                    if (Admin.login(usernameInput, passwordInput)){
                        frame.setVisible(false);
                        CarRentalSystem.adminMenu.getFrame().setVisible(true);
                    }else {
                        JOptionPane.showMessageDialog(frame, "Invalid account credentials!");
                    }
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
