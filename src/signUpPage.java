import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class signUpPage implements ActionListener {

    private JFrame frame;
    private Button signUp, cancel;
    private JLabel title, usernameLabel, passwordLabel, phoneNumLabel, emailLabel, addressLabel;
    private JTextField username, password, phoneNum, email, address;
    private JRadioButton male, female;
    private JSpinner age;
    // JTextField or JOptionPane?

    public signUpPage(){
        frame = new JFrame("Sign Up Page");
        signUp = new Button("Sign Up");
        cancel = new Button("Cancel");
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
        age = new JSpinner();

        signUp.setFocusable(false);
        signUp.addActionListener(this);
        signUp.setBounds(80,200,50,40);
//        signUp.setBackground(new Color(227,217,176));

        cancel.setFocusable(false);
        cancel.addActionListener(this);
        cancel.setBounds(170,200,50,40);
//        cancel.setBackground(new Color(227,217,176));

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
            } else if (e.getSource() == cancel) {
                frame.setVisible(false);
                CarRentalSystem.homePage.getFrame().setVisible(true);
            }
        } catch (Exception exception){
            JOptionPane.showMessageDialog(frame,"Invalid move3");
        }
    }

}
