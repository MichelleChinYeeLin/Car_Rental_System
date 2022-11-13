import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        usernameLabel = new JLabel("A Unique Username:");
        passwordLabel = new JLabel("Password: ");
        passwordCheckLabel = new JLabel("Validate your password: ");
        nameLabel = new JLabel("Name: ");
        ageLabel = new JLabel("Age:");
        phoneNumLabel = new JLabel("Phone Number: ");
        emailLabel = new JLabel("Email: ");
        addressLabel = new JLabel("Address: ");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == signUp){
                GUI.playSound("ji.wav");
                String usernameInput = username.getText();
                String passwordInput = String.valueOf(password.getPassword());
                String passwordCheckInput = String.valueOf(passwordCheck.getPassword());
                String nameInput = name.getText();
                String phoneNumInput = phoneNum.getText();
                String emailInput = email.getText();
                String addressInput = address.getText();
                int ageInput = (int) age.getValue();

                String gender = "";

                /* Input validation */

                // Username
                if(usernameInput.equals("")) throw new EmptyInputException();

                // Name
                if(nameInput.equals("")) throw new EmptyInputException();

                else{
                    char[] chars = nameInput.toCharArray();
                    for (char c : chars) {
                        if (!Character.isLetter(c) && !Character.isSpaceChar(c)) throw new InvalidNameException();
                    }
                }

                // Age
                if (ageInput < 17) throw new InvalidAgeException();

                // Gender
                if (male.isSelected()){
                    gender = male.getText();
                }
                else if (female.isSelected()) {
                    gender = female.getText();
                }
                else{
                    throw new EmptyInputException();
                }

                //Password
                if (passwordInput.equals("") || passwordCheckInput.equals("")) throw new EmptyInputException();
                if (!passwordInput.equals(passwordCheckInput)) throw new MismatchPasswordException();

                // Phone Number
                if (!phoneNumInput.matches("[0-9]+")) throw new InvalidPhoneException();

                // Email & Address ??
                //TODO email input validation

                if (Customer.signUp(usernameInput, passwordInput, nameInput, ageInput, gender, phoneNumInput, emailInput, addressInput)){
                    JOptionPane.showMessageDialog(frame, "Your registration request has been sent to admin!");
                    frame.setVisible(false);
                    CarRentalSystem.homePage.getFrame().setVisible(true);
                    clearSignUpField();
                }
                else {
                    throw new UsernameTakenException();
                }
            }
            else if (e.getSource() == cancel) {
                GUI.playSound("ji.wav");
                frame.setVisible(false);
                CarRentalSystem.homePage.getFrame().setVisible(true);
            }
        }
        catch(InvalidAgeException invalidAgeException){
            JOptionPane.showMessageDialog(frame, "Invalid age entered! You must be 17 or above to register.", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            age.setValue(17);
        }
        catch(InvalidNameException invalidNameException){
            JOptionPane.showMessageDialog(frame, "Invalid name entered! Your name must only include characters or spaces.", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            name.setText("");
        }
        catch(InvalidPhoneException invalidPhoneException){
            JOptionPane.showMessageDialog(frame, "Invalid phone number entered! Your phone number must only include numbers.", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            phoneNum.setText("");
        }
        catch(MismatchPasswordException mismatchPasswordException){
            JOptionPane.showMessageDialog(frame, "Your password does not match!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            password.setText("");
            passwordCheck.setText("");
        }
        catch(EmptyInputException emptyInputException){
            JOptionPane.showMessageDialog(frame, "All fields require an input!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        catch(UsernameTakenException usernameTakenException){
            JOptionPane.showMessageDialog(frame, "Username is already taken! Please input a different username.", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            username.setText("");
        }
        catch(Exception exception){
            JOptionPane.showMessageDialog(frame, "Unexpected error has occurred!", "Unexpected error!", JOptionPane.WARNING_MESSAGE);
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