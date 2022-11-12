import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage implements ActionListener {

    private JFrame frame;
    private JButton login, signUp, quit;
    private JLabel logoLabel;
    private ImageIcon homeBackground;
    private JButton[] buttons;

    public HomePage(){
        frame = new JFrame("Michelle & HeYuan's Car Rental System");
        GUI.JFrameSetup(frame);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        login = new JButton("Login");
        signUp = new JButton("Sign Up");
        quit = new JButton("Quit");
        buttons = new JButton[]{login, signUp, quit};
        GUI.JButtonSetup(buttons);
        login.addActionListener(this);
        signUp.addActionListener(this);
        quit.addActionListener(this);

        //Login
        FlowLayout topLayout = new FlowLayout();
        topLayout.setAlignment(FlowLayout.RIGHT);
        JPanel topButtons = new JPanel(topLayout);
        topButtons.setBackground(GUI.getFrameBackgroundColor());
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        topButtons.add(quit);
        frame.add(topButtons, constraints);

        //Logo
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(GUI.getFrameBackgroundColor());
        logoLabel = new JLabel();
        homeBackground = new ImageIcon(getClass().getResource("images/Logo_With_Text.png"));
        Image image = homeBackground.getImage();
        Image newImage = image.getScaledInstance(450, 300, Image.SCALE_SMOOTH);
        homeBackground = new ImageIcon(newImage);
        logoLabel.setIcon(homeBackground);
        logoPanel.add(logoLabel);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.ipady = 20;
        constraints.gridx = 0;
        constraints.gridy = 1;
        frame.add(logoPanel, constraints);

        //Sign Up & Quit
        FlowLayout bottomLayout = new FlowLayout();
        bottomLayout.setAlignment(FlowLayout.CENTER);
        bottomLayout.setHgap(100);
        JPanel bottomButtons = new JPanel(bottomLayout);
        bottomButtons.setBackground(GUI.getFrameBackgroundColor());
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.ipady = 0;
        constraints.gridx = 0;
        constraints.gridy = 2;
        bottomButtons.add(login);
        bottomButtons.add(signUp);
        frame.add(bottomButtons, constraints);

        frame.setVisible(true);
    }

    public JFrame getFrame(){
        return frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login){
            frame.setVisible(false);
            CarRentalSystem.loginPage.getFrame().setVisible(true);
        } else if (e.getSource() == signUp) {
            frame.setVisible(false);
            CarRentalSystem.signUpPage.getFrame().setVisible(true);
        } else if (e.getSource() == quit) {
            FileIO.writeAllFiles();
            System.exit(0);
        }
    }
}
