import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SignUpPage implements Page {

    private JFrame frame;
    private JButton signUp, cancel;
    private JLabel title, usernameLabel, passwordLabel, passwordCheckLabel, nameLabel, ageLabel, phoneNumLabel, emailLabel, addressLabel;
    private static JTextField username, name, phoneNum, email, address;
    private static JPasswordField password, passwordCheck;
    private JRadioButton male, female;
    private ButtonGroup genderGroup;
    private static JSpinner age;
    private JButton[] buttons;
    private JLabel[] labels;

    public SignUpPage(){
        //Create frame
        frame = new JFrame("Sign Up Page");
        GUI.JFrameSetup(frame);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints frameConstraints = new GridBagConstraints();

        //Create panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.white);
        mainPanel.setPreferredSize(new Dimension(400,450));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints constraints = new GridBagConstraints();

        //Create title
        title = new JLabel("Sign Up");
        title.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        title.setHorizontalAlignment(JLabel.CENTER);
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 3;
        constraints.ipady = 30;
        mainPanel.add(title, constraints);

        //Create labels
        usernameLabel = new JLabel("Unique Username:");
        passwordLabel = new JLabel("Password:");
        passwordCheckLabel = new JLabel("Confirm your password:");
        nameLabel = new JLabel("Name:");
        ageLabel = new JLabel("Age:");
        phoneNumLabel = new JLabel("Phone(ex. 0123456789):");
        emailLabel = new JLabel("Email(ex. xxx@gmail.com):");
        addressLabel = new JLabel("Address:");
        labels = new JLabel[]{usernameLabel, passwordLabel, passwordCheckLabel, nameLabel, ageLabel, phoneNumLabel, emailLabel, addressLabel};
        GUI.JLabelSetup(labels);

        //Set label positions
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.ipady = 0;
        for(int i = 0; i < labels.length; i++){

            constraints.gridwidth = 2;
            if(labels[i].getText().equals("Age: ")){
                constraints.gridwidth = 1;
            }

            constraints.gridy = i + 1;

            mainPanel.add(labels[i], constraints);
        }

        //Create and set input field positions
        constraints.gridwidth = 2;
        constraints.gridx = 2;
        username = new JTextField(20);
        constraints.gridy = 1;
        mainPanel.add(username, constraints);

        password = new JPasswordField(20);
        constraints.gridy = 2;
        mainPanel.add(password, constraints);

        passwordCheck = new JPasswordField(20);
        constraints.gridy = 3;
        mainPanel.add(passwordCheck, constraints);

        name = new JTextField(20);
        constraints.gridy = 4;
        mainPanel.add(name, constraints);

        age = new JSpinner(new SpinnerNumberModel(17,1,122,1));
        age.setPreferredSize(new Dimension(45, 25));
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.fill = GridBagConstraints.NONE;
        mainPanel.add(age, constraints);

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        male.setFocusable(false);
        female.setFocusable(false);
        genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        FlowLayout genderLayout = new FlowLayout();
        genderLayout.setAlignment(FlowLayout.LEFT);
        JPanel genderPanel = new JPanel(genderLayout);
        genderPanel.setBackground(Color.white);
        genderPanel.add(male);
        genderPanel.add(female);
        constraints.gridx = 2;
        constraints.gridy = 5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(genderPanel, constraints);

        constraints.gridwidth = 2;
        constraints.gridx = 2;
        phoneNum = new JTextField(20);
        constraints.gridy = 6;
        mainPanel.add(phoneNum, constraints);

        email = new JTextField(20);
        constraints.gridy = 7;
        mainPanel.add(email, constraints);

        address = new JTextField(20);
        constraints.gridy = 8;
        mainPanel.add(address, constraints);

        signUp = new JButton("Sign Up");
        cancel = new JButton("Cancel");
        signUp.addActionListener(this);
        cancel.addActionListener(this);
        buttons = new JButton[]{signUp, cancel};
        GUI.JButtonSetup(buttons);

        //Create and set buttons
        FlowLayout buttonLayout = new FlowLayout();
        buttonLayout.setAlignment(FlowLayout.CENTER);
        buttonLayout.setHgap(25);
        JPanel buttonPanel = new JPanel(buttonLayout);
        buttonPanel.setBackground(Color.white);
        buttonPanel.add(signUp);
        buttonPanel.add(cancel);
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.anchor = GridBagConstraints.PAGE_END;
        buttonConstraints.fill = GridBagConstraints.HORIZONTAL;
        buttonConstraints.weighty = 1;
        buttonConstraints.weightx = 1;
        buttonConstraints.gridwidth = 3;
        buttonConstraints.gridx = 0;
        mainPanel.add(buttonPanel, buttonConstraints);

        frame.add(mainPanel, frameConstraints);
    }

    public JFrame getFrame() {
        return frame;
    }

    public static JTextField getUsername() {
        return username;
    }

    public static JTextField getName() {
        return name;
    }

    public static JTextField getPhoneNum() {
        return phoneNum;
    }

    public static JTextField getEmail() {
        return email;
    }

    public static JPasswordField getPassword() {
        return password;
    }

    public static JPasswordField getPasswordCheck() {
        return passwordCheck;
    }

    public static JSpinner getAge() {
        return age;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == signUp){
                String usernameInput = username.getText().trim();
                String passwordInput = String.valueOf(password.getPassword());
                String passwordCheckInput = String.valueOf(passwordCheck.getPassword());
                String nameInput = name.getText().trim();
                String phoneNumInput = phoneNum.getText();
                String emailInput = email.getText();
                String addressInput = address.getText();
                int ageInput = (int) age.getValue();

                String gender;
                if (male.isSelected()){
                    gender = male.getText();
                }
                else if (female.isSelected()) {
                    gender = female.getText();
                }
                else{
                    throw new EmptyInputException();
                }

                if (Customer.validateCustomerDetails(false, usernameInput, nameInput, ageInput, passwordInput, passwordCheckInput, phoneNumInput, emailInput)){
                    GUI.playSound("ji.wav");
                    Customer newCustomer = new Customer(usernameInput, passwordInput, nameInput, phoneNumInput, gender, ageInput, emailInput, addressInput);
                    newCustomer.signUp();
                    JOptionPane.showMessageDialog(frame, "Your registration request has been sent to admin!");
                    frame.setVisible(false);
                    CarRentalSystem.homePage.getFrame().setVisible(true);
                    clearSignUpField();
                }
            }
            else if (e.getSource() == cancel) {
                GUI.playSound("ji.wav");
                frame.setVisible(false);
                CarRentalSystem.homePage.getFrame().setVisible(true);
            }
        }
        catch(EmptyInputException emptyInputException){
            GUI.playSound("NormalVoice.wav");
            JOptionPane.showMessageDialog(frame, "All fields require an input!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void clearSignUpField(){
        username.setText("");
        password.setText("");
        passwordCheck.setText("");
        name.setText("");
        age.setValue(17);
        male.setSelected(false);
        female.setSelected(false);
        phoneNum.setText("");
        email.setText("");
        address.setText("");
    }
}