import javax.swing.*;
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
                }
                else {
                    throw new UsernameTakenException();
                }
            }
            else if (e.getSource() == cancel) {
                frame.setVisible(false);
                CarRentalSystem.homePage.getFrame().setVisible(true);
            }
        }
        catch(InvalidAgeException invalidAgeException){
            JOptionPane.showMessageDialog(frame, "Invalid age entered! You must be 17 or above to register.", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        catch(InvalidNameException invalidNameException){
            JOptionPane.showMessageDialog(frame, "Invalid name entered! Your name must only include characters or spaces.", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        catch(InvalidPhoneException invalidPhoneException){
            JOptionPane.showMessageDialog(frame, "Invalid phone number entered! Your phone number must only include numbers.", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        catch(MismatchPasswordException mismatchPasswordException){
            JOptionPane.showMessageDialog(frame, "Your password does not match!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        catch(EmptyInputException emptyInputException){
            JOptionPane.showMessageDialog(frame, "All fields require an input!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        catch(UsernameTakenException usernameTakenException){
            JOptionPane.showMessageDialog(frame, "Username is already taken! Please input a different username.", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        catch(Exception exception){

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
class InvalidAgeException extends Exception{}

class InvalidNameException extends Exception{}

class InvalidPhoneException extends Exception{}

class MismatchPasswordException extends Exception{}

class EmptyInputException extends Exception{}

class UsernameTakenException extends Exception{}

class WrongPasswordException extends Exception{}

class UserNotFoundException extends Exception{}