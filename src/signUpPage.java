import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class signUpPage implements ActionListener {

    private JFrame frame;
    private Button signUp, cancel;
    // JTextField or JOptionPane?

    public signUpPage(){
        frame = new JFrame("Sign Up Page");
        signUp = new Button("Sign Up");
        cancel = new Button("Cancel");

        signUp.setFocusable(false);
        signUp.addActionListener(this);
        signUp.setBounds(80,200,50,40);
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
