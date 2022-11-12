import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AccountFunctions extends JPanel implements ActionListener {

    private static JPanel editPasswordPanel, addAdminPanel, searchAccountPanel, viewAccountPanel;
    private JPanel customerOnlyAttributes;
    private JPanel searchAccountAttributesPanel;
    private JButton confirmEdit, cancelEdit, confirmAdd, cancelAdd, search;
    private JLabel usernameLabel1, passwordLabel1, passwordLabel2;
    private JLabel usernameLabel2;
    private JLabel usernameSearchLabel, nameSearchLabel, phoneSearchLabel, genderSearchLabel, ageSearchLabel1, ageSearchLabel2,
            emailSearchLabel, addressSearchLabel, pointSearchLabel1, pointSearchLabel2;
    private static JTextField username1;
    private JTextField username2;
    private JTextField usernameSearch, nameSearch, phoneSearch, emailSearch, addressSearch;
    private JPasswordField password1, password2;
    private JComboBox<String> userType, genderSearch;
    private JSpinner fromAge, toAge, fromPoint, toPoint;
    private static JPanel[] panels;
    private JLabel[] labels, searchLabels;
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
        usernameSearchLabel = new JLabel("Username:");
        nameSearchLabel = new JLabel("Name:");
        phoneSearchLabel = new JLabel("Phone:");
        genderSearchLabel = new JLabel("Gender:");
        ageSearchLabel1 = new JLabel("Age:");
        ageSearchLabel2 = new JLabel("to");
        emailSearchLabel = new JLabel("Email:");
        addressSearchLabel = new JLabel("Address:");
        pointSearchLabel1 = new JLabel("Point:");
        pointSearchLabel2 = new JLabel("to");
        searchLabels = new JLabel[]{usernameSearchLabel, nameSearchLabel, phoneSearchLabel, genderSearchLabel, ageSearchLabel1, ageSearchLabel2,
                emailSearchLabel, addressSearchLabel, pointSearchLabel1, pointSearchLabel2};
        GUI.JLabelSetup(searchLabels);

        //Create input fields
        //JTextField
        username1 = new JTextField(20);
        username1.setEnabled(false);
        username2 = new JTextField(20);
        usernameSearch = new JTextField(20);
        usernameSearch.setPreferredSize(new Dimension(35,40));
        nameSearch = new JTextField(7);
        phoneSearch = new JTextField(7);
        emailSearch = new JTextField(7);
        addressSearch = new JTextField(7);

        //JPasswordField
        password1 = new JPasswordField(20);
        password2 = new JPasswordField(20);

        //JComboBox
        String[] userTypes = {"Customer", "Admin"};
        String[] genderTypes = {"male", "female"};
        userType = new JComboBox<>(userTypes);
        userType.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        userType.setPreferredSize(new Dimension(100,40));
        genderSearch = new JComboBox<>(genderTypes);
        genderSearch.setFont(new Font(Font.SERIF, Font.PLAIN, 16));

        //JSpinner
        fromAge = new JSpinner(new SpinnerNumberModel(17,1,122,1));
        toAge = new JSpinner(new SpinnerNumberModel(17,1,122,1));
        fromPoint = new JSpinner(new SpinnerNumberModel(0,0,1000,1));
        toPoint = new JSpinner(new SpinnerNumberModel(0,0,1000,1));


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
        searchAccountAttributesPanel = new JPanel(new GridBagLayout());
        searchAccountAttributesPanel.setBackground(Color.white);
        JPanel commonAttributes = new JPanel();
        commonAttributes.setBackground(Color.white);
        customerOnlyAttributes = new JPanel(new GridBagLayout());
        customerOnlyAttributes.setBackground(Color.white);
        customerOnlyAttributes.setVisible(false);

        //Common attributes
        commonAttributes.add(usernameSearchLabel);
        commonAttributes.add(usernameSearch);
        commonAttributes.add(userType);
        commonAttributes.add(search);

        //Customer only attributes
        GridBagConstraints searchAttributeConstraints = new GridBagConstraints();
        searchAttributeConstraints.insets = new Insets(2,2,2,2);
        searchAttributeConstraints.fill = GridBagConstraints.HORIZONTAL;
        searchAttributeConstraints.gridy = 0;
        searchAttributeConstraints.gridx = 0;
        searchAttributeConstraints.weightx = 0.1;
        customerOnlyAttributes.add(nameSearchLabel, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 1;
        customerOnlyAttributes.add(nameSearch, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 2;
        customerOnlyAttributes.add(phoneSearchLabel, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 3;
        customerOnlyAttributes.add(phoneSearch, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 4;
        customerOnlyAttributes.add(emailSearchLabel, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 5;
        customerOnlyAttributes.add(emailSearch, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 6;
        customerOnlyAttributes.add(addressSearchLabel, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 7;
        customerOnlyAttributes.add(addressSearch, searchAttributeConstraints);

        searchAttributeConstraints.gridy = 1;
        searchAttributeConstraints.gridx = 0;
        customerOnlyAttributes.add(ageSearchLabel1, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 1;
        customerOnlyAttributes.add(fromAge, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 2;
        customerOnlyAttributes.add(ageSearchLabel2, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 3;
        customerOnlyAttributes.add(toAge, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 4;
        customerOnlyAttributes.add(pointSearchLabel1, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 5;
        customerOnlyAttributes.add(fromPoint, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 6;
        customerOnlyAttributes.add(pointSearchLabel2, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 7;
        customerOnlyAttributes.add(toPoint, searchAttributeConstraints);

        searchAttributeConstraints.gridy = 2;
        searchAttributeConstraints.gridx = 3;
        customerOnlyAttributes.add(genderSearchLabel, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 4;
        customerOnlyAttributes.add(genderSearch, searchAttributeConstraints);

        //Add two panels above -> search account attributes panel
        accConstraints.gridy = 0;
        searchAccountAttributesPanel.add(commonAttributes, accConstraints);
        accConstraints.gridy = 1;
        searchAccountAttributesPanel.add(customerOnlyAttributes, accConstraints);

        //Add search account attributes panel & searched result -> search account panel
        accConstraints.gridy = 0;
        searchAccountPanel.add(searchAccountAttributesPanel, accConstraints);
        accConstraints.gridy = 1;
//        searchAccountPanel.add(, accConstraints); // search result



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
            else if (e.getSource() == search){
                searchAccount();
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

    private void searchAccount(){
        try {
            ArrayList<Admin> searchedAdminList = FileIO.getAdminList();
            ArrayList<Customer> searchedCustomerList = FileIO.getCustomerList();
            String userTypeInput = (String) userType.getSelectedItem();
            String usernameInput = usernameSearchLabel.getText().toUpperCase();

            customerOnlyAttributes.setVisible(userTypeInput.equals("Customer"));

            if (userTypeInput.equals("Admin")){
                searchedAdminList.removeIf(a -> !a.getUsername().contains(usernameInput));
                //下面的换成这个
//                for (Admin a : searchedAdminList) {
//                    if (!a.getUsername().contains(usernameInput)){
//                        searchedAdminList.remove(a);
//                    }
//                }
            }
            else {
                String nameInput = nameSearch.getText();
                //TODO: get input from fields
            }
        }
        catch (Exception exception){
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Unexpected error. Please try again.");
        }
    }

}
