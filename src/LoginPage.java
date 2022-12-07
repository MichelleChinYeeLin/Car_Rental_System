import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginPage implements Page {

    private JFrame frame;
    private JButton login, cancel;
    private JLabel title, usernameLabel, passwordLabel;
    private JTextField username;
    private JPasswordField password;
    private JComboBox<String> userTypeSelect;
    private JButton[] buttons;
    private JLabel[] labels;

    public LoginPage() {
        frame = new JFrame("Login Page");
        GUI.JFrameSetup(frame);
        frame.setLayout(new GridBagLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.white);
        mainPanel.setPreferredSize(new Dimension(400,450));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints constraints = new GridBagConstraints();
        title = new JLabel("LOGIN");
        title.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        title.setHorizontalAlignment(JLabel.CENTER);
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipady = 30;
        constraints.gridwidth = 2;
        constraints.weighty = 1;
        constraints.weightx = 0.2;
        mainPanel.add(title, constraints);

        constraints.gridx = 0;
        String[] userType = {"Customer", "Admin"};
        userTypeSelect = new JComboBox<>(userType);
        userTypeSelect.setPreferredSize(new Dimension(20, 10));
        userTypeSelect.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        constraints.gridy = 1;
        mainPanel.add(userTypeSelect, constraints);

        constraints.gridwidth = 1;
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        labels = new JLabel[]{usernameLabel, passwordLabel};
        GUI.JLabelSetup(labels);
        constraints.gridy = 2;
        mainPanel.add(usernameLabel, constraints);
        constraints.gridy = 3;
        mainPanel.add(passwordLabel, constraints);

        GUI.JLabelSetup(labels);

        constraints.gridx = 1;
        constraints.weightx = 0.8;
        username = new JTextField();
        username.setPreferredSize(new Dimension(50, 5));
        constraints.gridy = 2;
        mainPanel.add(username, constraints);

        password = new JPasswordField();
        password.setPreferredSize(new Dimension(50, 5));
        constraints.gridy = 3;
        mainPanel.add(password, constraints);

        FlowLayout buttonLayout = new FlowLayout();
        buttonLayout.setAlignment(FlowLayout.CENTER);
        buttonLayout.setHgap(25);
        JPanel buttonPanel = new JPanel(buttonLayout);
        buttonPanel.setBackground(Color.white);
        login = new JButton("Log In");
        cancel = new JButton("Cancel");
        buttons = new JButton[]{login, cancel};
        GUI.JButtonSetup(buttons);
        buttonPanel.add(login);
        buttonPanel.add(cancel);
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.PAGE_END;
        mainPanel.add(buttonPanel, constraints);

        login.addActionListener(this);
        cancel.addActionListener(this);

        frame.add(mainPanel);
    }

    public JFrame getFrame(){
        return frame;
    };

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
                    Customer loginCustomer = new Customer(usernameInput, passwordInput);

                    if (loginCustomer.login()) {
                        GUI.playSound("ji.wav");
                        frame.setVisible(false);
                        CarRentalSystem.customerMenu = new CustomerMenu();
                        CarRentalSystem.customerMenu.getFrame().setVisible(true);
                        CarRentalSystem.currentFrame = CarRentalSystem.customerMenu.getFrame();
                    }
                }
                else if (userTypeInput.equals("Admin")) {
                    Admin loginAdmin = new Admin(usernameInput, passwordInput);
                    if (loginAdmin.login()) {
                        GUI.playSound("ji.wav");
                        frame.setVisible(false);
                        CarRentalSystem.adminMenu = new AdminMenu();
                        CarRentalSystem.adminMenu.getFrame().setVisible(true);
                        CarRentalSystem.currentFrame = CarRentalSystem.adminMenu.getFrame();
                    }
                    else {
                        GUI.playSound("ReflectYourself.wav");
                        JOptionPane.showMessageDialog(frame, "Invalid account credentials!");
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
            GUI.playSound("NormalVoice.wav");
            JOptionPane.showMessageDialog(frame, "All fields require an input!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        finally {
            username.setText("");
            password.setText("");
        }
    }
}
