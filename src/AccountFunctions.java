import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountFunctions extends JPanel implements ActionListener {

    private static JPanel editPasswordPanel, addAdminPanel, searchAccountPanel, viewAccountPanel;
    private JButton confirmEdit, cancelEdit, confirmAdd, cancelAdd, search;
    private JLabel usernameLabel1, passwordLabel1, passwordLabel2;
    private JLabel usernameLabel2;
    private static JTextField username1;
    private JTextField username2;
    private JPasswordField password1, password2;
    private static JPanel[] panels;
    private JLabel[] labels;
    private JButton[] accountButtons;

    public AccountFunctions(){

        //Create buttons
        confirmEdit = new JButton("EDIT");
        cancelEdit = new JButton("CANCEL");
        confirmAdd = new JButton("ADD");
        cancelAdd = new JButton("CANCEL");
        search = new JButton("SEARCH");
        accountButtons = new JButton[]{confirmEdit, cancelEdit, confirmAdd, cancelAdd, search};
        confirmEdit.addActionListener(this);
        cancelEdit.addActionListener(this);
        confirmAdd.addActionListener(this);
        cancelAdd.addActionListener(this);
        search.addActionListener(this);
        GUI.subJButtonSetup(accountButtons, new Dimension(100, 40));

        //Create labels
        usernameLabel1 = new JLabel("Username:");
        passwordLabel1 = new JLabel("Password: ");
        passwordLabel2 = new JLabel("Verify Password: ");
        usernameLabel2 = new JLabel("New Username:");
        labels = new JLabel[]{usernameLabel1, passwordLabel1, passwordLabel2, usernameLabel2};
        GUI.JLabelSetup(labels);

        //Create input fields
        username1 = new JTextField(20);
        username1.setEnabled(false);
        username2 = new JTextField(20);
        password1 = new JPasswordField(20);
        password2 = new JPasswordField(20);

        //Edit password & Add admin panel
        GridBagConstraints accConstraints = new GridBagConstraints();
        editPasswordPanel = new JPanel(new GridBagLayout());
        editPasswordPanel.setBackground(Color.white);
        addAdminPanel = new JPanel(new GridBagLayout());
        addAdminPanel.setBackground(Color.white);
        //Setup buttons
        JPanel editPasswordSelectionPanel = new JPanel();
        editPasswordSelectionPanel.setBackground(Color.white);
        editPasswordSelectionPanel.add(confirmEdit);
        editPasswordSelectionPanel.add(cancelEdit);
        JPanel addAdminSelectionPanel = new JPanel();
        addAdminSelectionPanel.setBackground(Color.white);
        addAdminSelectionPanel.add(confirmAdd);
        addAdminSelectionPanel.add(cancelAdd);
        //Setup labels
        accConstraints.insets = new Insets(10,5,10,5);
        accConstraints.weightx = 1;
        accConstraints.weighty = 1;
        accConstraints.gridx = 0;
        accConstraints.ipady = 0;
        accConstraints.gridwidth = 2;
        for(int i = 0; i < labels.length - 1; i++){

            if(labels[i].getText().equals("Level:")){
                accConstraints.gridwidth = 1;
            }
            accConstraints.gridy = i + 1;
            editPasswordPanel.add(labels[i], accConstraints);
            if (i == 0) addAdminPanel.add(usernameLabel2, accConstraints);
        }
        //Setup fields
        accConstraints.gridx = 2;
        accConstraints.gridy = 1;
        editPasswordPanel.add(username1, accConstraints);
        addAdminPanel.add(username2, accConstraints);
        accConstraints.gridy = 2;
        editPasswordPanel.add(password1, accConstraints);
        addAdminPanel.add(addAdminSelectionPanel, accConstraints);
        accConstraints.gridy = 3;
        editPasswordPanel.add(password2, accConstraints);
        accConstraints.gridy = 4;
        editPasswordPanel.add(editPasswordSelectionPanel, accConstraints);

        //Search account panel
        searchAccountPanel = new JPanel(new GridBagLayout());

        //View account panel
        viewAccountPanel = new JPanel(new GridBagLayout());

        //Create account functions panel
        panels = new JPanel[]{editPasswordPanel, addAdminPanel, searchAccountPanel, viewAccountPanel};
        GUI.JPanelSetup(panels);
        setPreferredSize(new Dimension(500,500));
        add(editPasswordPanel);
        add(addAdminPanel);
        add(searchAccountPanel);
        add(viewAccountPanel);
    }

    public static void showEditPasswordPanel(){
        showAccountPanel(editPasswordPanel);
    }

    public static void showAddAdminPanel(){
        showAccountPanel(addAdminPanel);
    }

    public static void showSearchAccountPanel(){
        showAccountPanel(searchAccountPanel);
    }

    public static void showViewAccountPanel(){
        showAccountPanel(viewAccountPanel);
    }

    public static JTextField getUsername1() {
        return username1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == confirmEdit){
                String passwordInput = String.valueOf(password1.getPassword());
                String passwordCheckInput = String.valueOf(password2.getPassword());

                if (passwordInput.equals("") || passwordCheckInput.equals("")) throw new EmptyInputException();
                if (!passwordInput.equals(passwordCheckInput)) throw new MismatchPasswordException();

                CarRentalSystem.loginAdmin.setPassword(passwordInput);
                FileIO.writeAdminFile();
                JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Your password has been successfully changed!");
                password1.setText("");
                password2.setText("");
            }
            else if (e.getSource() == cancelEdit){
                password1.setText("");
                password2.setText("");
            }
            else if (e.getSource() == confirmAdd){
                String newUsername = username2.getText();
                String newPassword = newUsername;

                if (newUsername.equals("")) throw new EmptyInputException();

                for (Admin a: FileIO.adminList) {
                    if (newUsername.equals(a.getUsername())){
                        throw new UsernameTakenException();
                    }
                }
                // New admin account's password same with username
                FileIO.adminList.add(new Admin(newUsername, newPassword));
                FileIO.writeAdminFile();
                JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "New admin account is successfully created!");
                username2.setText("");
            }
            else if (e.getSource() == cancelAdd){
                username2.setText("");
            }
        }
        catch (EmptyInputException emptyInputException){
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "All fields require an input!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        catch (MismatchPasswordException mismatchPasswordException){
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Your password does not match!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            password1.setText("");
            password2.setText("");
        }
        catch (UsernameTakenException usernameTakenException){
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Username is already taken! Please input a different username.", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            username2.setText("");
        }
        catch (Exception exception){
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Something wrong!");
        }
    }

    private static void showAccountPanel(JPanel panel){
        for (JPanel i : panels) {
            i.setVisible(false);
        }
        panel.setVisible(true);
    }

}
