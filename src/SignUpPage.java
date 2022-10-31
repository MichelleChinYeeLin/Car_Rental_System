import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPage implements ActionListener {

    private JFrame frame;
    private JButton signUp, cancel;
    private JLabel title, usernameLabel, passwordLabel, passwordCheckLabel, nameLabel, ageLabel, phoneNumLabel, emailLabel, addressLabel;
    private JTextField username, name, phoneNum, email, address;
    private JPasswordField password, passwordCheck;
    private JRadioButton male, female;
    private ButtonGroup genderGroup;
    private JSpinner age;
    private JButton[] buttons;
    private JLabel[] labels;

    public SignUpPage(){
        frame = new JFrame("Sign Up Page");
        genderGroup = new ButtonGroup();
        signUp = new JButton("Sign Up");
        cancel = new JButton("Cancel");
        title = new JLabel("Sign Up");
        usernameLabel = new JLabel("A Unique Username:");
        passwordLabel = new JLabel("Password: ");
        passwordCheckLabel = new JLabel("Check your password: ");
        nameLabel = new JLabel("Name: ");
        ageLabel = new JLabel("Age:");
        phoneNumLabel = new JLabel("Phone Number: ");
        emailLabel = new JLabel("Email: ");
        addressLabel = new JLabel("Address: ");
        username = new JTextField(20);
        name = new JTextField(20);
        phoneNum = new JTextField(20);
        email = new JTextField(20);
        address = new JTextField(20);
        password = new JPasswordField(20);
        passwordCheck = new JPasswordField(20);
        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        age = new JSpinner(new SpinnerNumberModel(17,1,122,1)); // 最长寿的人122岁

        title.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,30));

        title.setBounds(80,20,200,40);
        usernameLabel.setBounds(10,80,200,20);
        username.setBounds(10,100,260,20);
        passwordLabel.setBounds(10,120,200,20);
        password.setBounds(10,140,260,20);
        passwordCheckLabel.setBounds(10,160,200,20);
        passwordCheck.setBounds(10,180,260,20);
        nameLabel.setBounds(10,200,200,20);
        name.setBounds(10,220,260,20);
        ageLabel.setBounds(10,250,30,20);
        age.setBounds(40,250,40,20);
        male.setBounds(100,250,60,20);
        female.setBounds(170,250,80,20);
        phoneNumLabel.setBounds(10,280,200,20);
        phoneNum.setBounds(10,300,260,20);
        emailLabel.setBounds(10,320,200,20);
        email.setBounds(10,340,260,20);
        addressLabel.setBounds(10,360,200,20);
        address.setBounds(10,380,260,20);
        signUp.setBounds(60,410,80,40);
        cancel.setBounds(160,410,80,40);

        male.setFocusable(false);
        female.setFocusable(false);
        genderGroup.add(male);
        genderGroup.add(female);

        signUp.addActionListener(this);
        cancel.addActionListener(this);

        buttons = new JButton[]{signUp, cancel};
        labels = new JLabel[]{usernameLabel, passwordLabel, passwordCheckLabel, nameLabel, ageLabel, phoneNumLabel, emailLabel, addressLabel};
        GUI.JButtonSetup(buttons);
        GUI.JLabelSetup(labels);
        GUI.JFrameSetup(frame);

        frame.setSize(300,500);
        frame.add(signUp);
        frame.add(cancel);
        frame.add(title);
        frame.add(usernameLabel);
        frame.add(username);
        frame.add(passwordLabel);
        frame.add(password);
        frame.add(passwordCheckLabel);
        frame.add(passwordCheck);
        frame.add(nameLabel);
        frame.add(name);
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
                String nameInput = name.getText();
                String phoneNumInput = phoneNum.getText();
                String emailInput = email.getText();
                String addressInput = address.getText();
                int ageInput = (int) age.getValue();
                String gender;
                char[] chars = nameInput.toCharArray();

                // Input validation
                // Username
                if (usernameInput.length() > 0){
                    for (Customer c : FileIO.getCustomerList()) {
                        if (usernameInput.equals(c.getUsername())) throw new Exception("This username had been used!");
                    }
                } else {
                    throw new Exception("Empty username is not allowed!");
                }
                // Password
                if (!passwordInput.equals(passwordCheckInput)) throw new Exception("Your passwords aren't the same!");
                // Name
                for (char c : chars) {
                    if (!Character.isLetter(c)) throw new Exception("Your name should only includes letters!");
                }
                // Age
                if (ageInput < 17) throw new Exception("You should be at least 17 years old!");
                // Gender
                if (male.isSelected()){
                    gender = male.getText();
                } else if (female.isSelected()) {
                    gender = female.getText();
                } else {
                    throw new Exception("You hadn't choose any gender!"); // 过后我研究一下不同error怎样弄
                }
                // Phone Number
                if (!phoneNumInput.matches("[0-9]+")) throw new Exception("Your phone number should includes only numbers!");
                // Email & Address ??

                if (Customer.signUp(usernameInput, passwordInput, nameInput, ageInput, gender, phoneNumInput, emailInput, addressInput)){
                    JOptionPane.showMessageDialog(frame, "Your registration request has been sent to admin!");
                    frame.setVisible(false);
                    CarRentalSystem.homePage.getFrame().setVisible(true);
                } else {
                    throw new Exception("You had sent duplicate requests!");
                }
            } else if (e.getSource() == cancel) {
                frame.setVisible(false);
                CarRentalSystem.homePage.getFrame().setVisible(true);
            }
        } catch (Exception exception){
            JOptionPane.showMessageDialog(frame, exception.getMessage());
        } finally {
            clearSignUpField();
        }
    }

    private void clearSignUpField(){
        username.setText("");
        password.setText("");
        passwordCheck.setText("");
        name.setText("");
        age.setValue(17);
        male.setSelected(false); // 不懂做么这两个没用
        female.setSelected(false);
        phoneNum.setText("");
        email.setText("");
        address.setText("");
    }
}
