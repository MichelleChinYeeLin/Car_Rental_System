import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPage implements ActionListener {

    private JFrame frame;
    private JButton signUp, cancel;
    private JLabel title, usernameLabel, passwordLabel, phoneNumLabel, emailLabel, addressLabel;
    private JTextField username, password, phoneNum, email, address;
    private JRadioButton male, female;
    private ButtonGroup genderGroup;
    private JSpinner age;
    // JTextField or JOptionPane?

    public SignUpPage(){
        frame = new JFrame("Sign Up Page");
        signUp = new JButton("Sign Up");
        cancel = new JButton("Cancel");
        title = new JLabel("Sign Up");
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password: ");
        phoneNumLabel = new JLabel("Phone Number: ");
        emailLabel = new JLabel("Email: ");
        addressLabel = new JLabel("Address: ");
        username = new JTextField("Username: ", 20);
        password = new JTextField("Password: ", 20);
        phoneNum = new JTextField("Password: ", 20);
        email = new JTextField("Password: ", 20); // need check if got @gmail.com or others?
        address = new JTextField("Password: ", 20);
        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        age = new JSpinner();

        signUp.setFocusable(false);
        signUp.addActionListener(this);
        signUp.setBounds(60,200,80,40);
        signUp.setFont(new Font(Font.DIALOG, Font.ITALIC, 12));
//        signUp.setBackground(new Color(227,217,176));

        cancel.setFocusable(false);
        cancel.addActionListener(this);
        cancel.setBounds(160,200,80,40);
        cancel.setFont(new Font(Font.DIALOG, Font.ITALIC, 12));
//        cancel.setBackground(new Color(227,217,176));

        // male & female
        genderGroup.add(male);
        genderGroup.add(female);

        age = new JSpinner(new SpinnerNumberModel(1,1,122,1)); // 最长寿的人122岁

//        title.setBounds(90,20,200,30);
//        title.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,30));
//        usernameLabel.setBounds(10,60,200,20);
//        usernameLabel.setFont(new Font(Font.SERIF, Font.ITALIC, 12));
//        passwordLabel.setBounds(10,120,200,20);
//        passwordLabel.setFont(new Font(Font.SERIF, Font.ITALIC, 12));

        frame.setLayout(null);
//        frame.setIconImage(logo.getImage());
        frame.setSize(300,300);
        frame.setResizable(false);
//        frame.getContentPane().setBackground(new Color(240,205,151));
        frame.setLocationRelativeTo(null);
        frame.add(signUp);
        frame.add(cancel);
    }

    public JFrame getFrame() {
        return frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == signUp){
                // get input from text field
                // isSelected -> JRadioButton
                // getValue -> JSpinner
            } else if (e.getSource() == cancel) {
                frame.setVisible(false);
                CarRentalSystem.homePage.getFrame().setVisible(true);
            }
        } catch (Exception exception){
            JOptionPane.showMessageDialog(frame,"Invalid move");
        }
    }

}
