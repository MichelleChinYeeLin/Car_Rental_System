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
    private JButton[] buttons;
    private JLabel[] labels;

    public LoginPage() {
        frame = new JFrame("Login Page");
        String[] userType = {"Customer", "Admin"};
        logo = new ImageIcon("Logo.png");
        login = new JButton("Log In");
        cancel = new JButton("Cancel");
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        title = new JLabel("LOGIN");
        username = new JTextField();
        password = new JPasswordField();
        userTypeSelect = new JComboBox<String>(userType);

        title.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 38));
        userTypeSelect.setFont(new Font(Font.SERIF, Font.ITALIC, 12));

        title.setBounds(80, 20, 200, 40);
        userTypeSelect.setBounds(10, 80, 80, 20);
        usernameLabel.setBounds(10, 100, 200, 20);
        passwordLabel.setBounds(10, 140, 200, 20);
        username.setBounds(10, 120, 260, 20);
        password.setBounds(10, 160, 260, 20);
        login.setBounds(60, 200, 80, 40);
        cancel.setBounds(160, 200, 80, 40);

        login.addActionListener(this);
        cancel.addActionListener(this);

        buttons = new JButton[]{login, cancel};
        labels = new JLabel[]{usernameLabel, passwordLabel};
        GUI.JButtonSetup(buttons);
        GUI.JLabelSetup(labels);
        GUI.JFrameSetup(frame);

        frame.setSize(300, 300);
        frame.add(title);
        frame.add(usernameLabel);
        frame.add(passwordLabel);
        frame.add(login);
        frame.add(cancel);
        frame.add(username);
        frame.add(password);
        frame.add(userTypeSelect);
    }

    public JFrame getFrame() {
        return frame;
    }

    ;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == login) {
                String usernameInput = username.getText();
                String passwordInput = String.valueOf(password.getPassword());
                String userTypeInput = (String) userTypeSelect.getSelectedItem();

                if (usernameInput.equals("") || passwordInput.equals("")){
                    throw new EmptyInputException();
                }

                if (userTypeInput.equals("Customer")) {
                    if (Customer.login(usernameInput, passwordInput)) {
                        GUI.playSound("ji.wav");
                        frame.setVisible(false);
                        CarRentalSystem.customerMenu.getFrame().setVisible(true);
                    }
                }
                else if (userTypeInput.equals("Admin")) {
                    if (Admin.login(usernameInput, passwordInput)) {
                        GUI.playSound("ji.wav");
                        frame.setVisible(false);
                        CarRentalSystem.adminMenu.getFrame().setVisible(true);
                    }
                    else {
                        GUI.playSound("niganma.wav");
                        JOptionPane.showMessageDialog(frame, "Invalid account credentials! FK OFF!");
                        frame.setVisible(false);
                        CarRentalSystem.homePage.getFrame().setVisible(true);
                    }
                }
            }
            else if (e.getSource() == cancel) {
                GUI.playSound("ji.wav");
                frame.setVisible(false);
                CarRentalSystem.homePage.getFrame().setVisible(true);
            }
        }
        catch (EmptyInputException emptyInputException) {
            GUI.playSound("niganma.wav");
            JOptionPane.showMessageDialog(frame, "All fields require an input!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        finally {
            username.setText("");
            password.setText("");
        }
    }
}