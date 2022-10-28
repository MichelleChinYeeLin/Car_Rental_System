import jdk.jshell.spi.SPIResolutionException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginPage implements ActionListener {

    private JFrame frame;
    private Button login, cancel;
    // JTextField or JOptionPane?

    public loginPage() {
        frame = new JFrame("Login Page");
        login = new Button("Log In");
        cancel = new Button("Cancel");

        login.setFocusable(false);
        login.addActionListener(this);
        login.setBounds(80,200,50,40);
//        signUp.setBackground(new Color(227,217,176));

        cancel.setFocusable(false);
        cancel.addActionListener(this);
        cancel.setBounds(170,200,50,40);
//        cancel.setBackground(new Color(227,217,176));

        frame.setLayout(null);
//        frame.setIconImage(logo.getImage());
        frame.setSize(300,300);
        frame.setResizable(false);
//        frame.getContentPane().setBackground(new Color(240,205,151)); // 颜色再调一下真的很丑 你给我做gui是个错误的选择
        frame.setLocationRelativeTo(null);
        frame.add(login);
        frame.add(cancel);

    }

    public JFrame getFrame(){
        return frame;
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == login){
                // get input from text field
            } else if (e.getSource() == cancel) {
                frame.setVisible(false);
                CarRentalSystem.homePage.getFrame().setVisible(true);
            }
        } catch (Exception exception){
            JOptionPane.showMessageDialog(frame,"Invalid move2");
        }
    }
}
