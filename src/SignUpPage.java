import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPage implements ActionListener {

    private JFrame frame;
    private JButton signUp, cancel;
    private JLabel title, usernameLabel, passwordLabel, passwordCheckLabel, ageLabel, phoneNumLabel, emailLabel, addressLabel;
    private JTextField username, phoneNum, email, address;
    private JPasswordField password, passwordCheck;
    private JRadioButton male, female;
    private ButtonGroup genderGroup;
    private JSpinner age;
    private ImageIcon logo;

    public SignUpPage(){
        frame = new JFrame("Sign Up Page");
        genderGroup = new ButtonGroup();
        logo = new ImageIcon("Logo.png");

        signUp = new JButton("Sign Up");
        cancel = new JButton("Cancel");

        title = new JLabel("Sign Up");
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password: ");
        passwordCheckLabel = new JLabel("Check your password: ");
        ageLabel = new JLabel("Age:");
        phoneNumLabel = new JLabel("Phone Number: ");
        emailLabel = new JLabel("Email: ");
        addressLabel = new JLabel("Address: ");

        username = new JTextField(20);
        phoneNum = new JTextField(20);
        email = new JTextField(20);
        address = new JTextField(20);
        password = new JPasswordField(20);
        passwordCheck = new JPasswordField(20);
        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        age = new JSpinner(new SpinnerNumberModel(1,1,122,1)); // 最长寿的人122岁

        signUp.setFocusable(false);
        signUp.addActionListener(this);
        signUp.setBounds(60,370,80,40);
        signUp.setFont(new Font(Font.DIALOG, Font.ITALIC, 12));
        signUp.setBackground(new Color(212, 183, 185));

        cancel.setFocusable(false);
        cancel.addActionListener(this);
        cancel.setBounds(160,370,80,40);
        cancel.setFont(new Font(Font.DIALOG, Font.ITALIC, 12));
        cancel.setBackground(new Color(212, 183, 185));

        title.setBounds(80,20,200,40);
        title.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,30));
        usernameLabel.setBounds(10,80,200,20);
        usernameLabel.setFont(new Font(Font.SERIF, Font.ITALIC, 12));
        passwordLabel.setBounds(10,120,200,20);
        passwordLabel.setFont(new Font(Font.SERIF, Font.ITALIC, 12));
        passwordCheckLabel.setBounds(10,160,200,20);
        passwordCheckLabel.setFont(new Font(Font.SERIF, Font.ITALIC, 12));
        ageLabel.setBounds(10,210,30,20);
        ageLabel.setFont(new Font(Font.SERIF, Font.ITALIC, 12));
        phoneNumLabel.setBounds(10,230,200,20);
        phoneNumLabel.setFont(new Font(Font.SERIF, Font.ITALIC, 12));
        emailLabel.setBounds(10,270,200,20);
        emailLabel.setFont(new Font(Font.SERIF, Font.ITALIC, 12));
        addressLabel.setBounds(10,310,200,20);
        addressLabel.setFont(new Font(Font.SERIF, Font.ITALIC, 12));

        username.setBounds(10,100,260,20);
        password.setBounds(10,140,260,20);
        passwordCheck.setBounds(10,180,260,20);
        age.setBounds(40,210,40,20);
        male.setBounds(100,210,60,20);
        male.setFocusable(false);
        female.setBounds(170,210,80,20);
        female.setFocusable(false);
        phoneNum.setBounds(10,250,260,20);
        email.setBounds(10,290,260,20);
        address.setBounds(10,330,260,20);

        genderGroup.add(male);
        genderGroup.add(female);

        frame.setLayout(null);
        frame.setIconImage(logo.getImage());
        frame.setSize(300,470);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(155, 159, 177));
        frame.setLocationRelativeTo(null);
        frame.add(signUp);
        frame.add(cancel);
        frame.add(title);
        frame.add(usernameLabel);
        frame.add(username);
        frame.add(passwordLabel);
        frame.add(password);
        frame.add(passwordCheckLabel);
        frame.add(passwordCheck);
        frame.add(male);
        frame.add(female);
        frame.add(ageLabel);
        frame.add(age);
        frame.add(phoneNumLabel);
        frame.add(phoneNum);
        frame.add(emailLabel);
        frame.add(email);
        frame.add(addressLabel);
        frame.add(address);
    }

    public JFrame getFrame() {
        return frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == signUp){
                String usernameInput = username.getText();
                String passwordInput = String.valueOf(password.getPassword());
                String passwordCheckInput = String.valueOf(passwordCheck.getPassword());
                String phoneNumInput = phoneNum.getText();
                String emailInput = email.getText();
                String addressInput = address.getText();
                int ageInput = (int) age.getValue();
                String gender;
                if (male.isSelected()){
                    gender = male.getText();
                } else if (female.isSelected()) {
                    gender = female.getText();
                } else {
                    throw new Exception(); // 过后我研究一下不同error怎样弄
                }
                if (!passwordInput.equals(passwordCheckInput)){
                    throw new Exception();
                }
                if (Customer.signUp(usernameInput, passwordInput, ageInput, gender, phoneNumInput, emailInput, addressInput)){
                    JOptionPane.showMessageDialog(frame, "Your registration request has been sent to admin!");
                    frame.setVisible(false);
                    CarRentalSystem.homePage.getFrame().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(frame, "Something's wrong!");
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
