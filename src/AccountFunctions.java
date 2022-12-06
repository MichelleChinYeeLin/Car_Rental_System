import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Objects;

public class AccountFunctions extends JPanel implements ActionListener {

    private boolean isAdmin;

    // Edit account
    private JLabel usernameEditLabel, passwordEditLabel, nameEditLabel, phoneEditLabel, genderEditLabel, ageEditLabel,
            emailEditLabel, addressEditLabel, pointEditLabel;
    private JLabel[] editLabels;

    // Admin
    private static JPanel adminEditPasswordPanel, addAdminPanel, adminSearchAccountPanel, adminViewAccountPanel, adminEditAccountPanel;
    private JPanel customerOnlyAttributes, searchAccountAttributesPanel, adminSearchResultPanel;
    private JButton adminConfirmEdit, adminCancelEdit, adminConfirmAdd, adminCancelAdd, adminSearch, editButton, adminResetPassword, adminOKButton, backToSearch, deleteButton;
    private JLabel adminUsernameLabel1, adminPasswordLabel1, adminPasswordLabel2, adminUsernameLabel2;
    private JLabel adminUsernameSearchLabel, adminNameSearchLabel, adminPhoneSearchLabel, adminGenderSearchLabel, adminAgeSearchLabel1, adminAgeSearchLabel2,
            adminEmailSearchLabel, adminAddressSearchLabel, adminPointSearchLabel1, adminPointSearchLabel2;
    private static JTextField adminUsername1, adminUsername2;
    private JTextField adminUsernameSearch, adminNameSearch, adminPhoneSearch, adminEmailSearch, adminAddressSearch;
    private JTextField adminUsernameEdit, adminNameEdit, adminPhoneEdit, adminEmailEdit, adminAddressEdit;
    private static JPasswordField adminPassword1, adminPassword2;
    private JPasswordField adminPasswordEdit;
    private JRadioButton adminMale, adminFemale;
    private ButtonGroup adminGenderGroup;
    private JComboBox<String> adminUserType, adminGenderSearch;
    private static JSpinner adminFromAge, adminToAge, adminFromPoint, adminToPoint;
    private JSpinner numberSpinner, adminAgeEdit, adminPointEdit;
    private JTable searchTable;
    private static JTable allAdminTable, allCustomerTable;
    private static JPanel[] adminPanels;
    private JLabel[] adminLabels, adminSearchLabels;
    private JButton[] adminAccountButtons;
    private JComponent[] adminComponents;

    // Customer
    private static JPanel customerPanel;
    private static JLabel customerPointAndLevel;
    private JButton customerOKButton, cancelButton;
    private static JTextField customerUsernameEdit, customerNameEdit, customerPhoneEdit, customerEmailEdit, customerAddressEdit;
    private static JPasswordField customerPasswordEdit;
    private static JSpinner customerAgeEdit;
    private static JRadioButton customerMale, customerFemale;
    private ButtonGroup customerGenderGroup;
    private JButton[] customerAccountButtons;
    private static JComponent[] customerComponents;

    public AccountFunctions(boolean isAdmin){

        this.isAdmin = isAdmin;

        /* Edit account */
        usernameEditLabel = new JLabel("Username:");
        passwordEditLabel = new JLabel("Password:");
        nameEditLabel = new JLabel("Name:");
        phoneEditLabel = new JLabel("Phone:");
        genderEditLabel = new JLabel("Gender:");
        ageEditLabel = new JLabel("Age:");
        emailEditLabel = new JLabel("Email:");
        addressEditLabel = new JLabel("Address:");
        pointEditLabel = new JLabel("Point:");
        editLabels = new JLabel[]{usernameEditLabel, passwordEditLabel, nameEditLabel, phoneEditLabel, genderEditLabel, ageEditLabel,
                emailEditLabel, addressEditLabel, pointEditLabel};
        GUI.JLabelSetup(editLabels);


        if (isAdmin){
            //Create buttons
            adminConfirmEdit = new JButton("EDIT");
            adminCancelEdit = new JButton("CANCEL");
            adminConfirmEdit.addActionListener(this);
            adminCancelEdit.addActionListener(this);
            adminConfirmAdd = new JButton("ADD");
            adminCancelAdd = new JButton("CANCEL");
            adminConfirmAdd.addActionListener(this);
            adminCancelAdd.addActionListener(this);
            adminSearch = new JButton("SEARCH");
            adminSearch.addActionListener(this);
            adminResetPassword = new JButton("RESET PASSWORD");
            adminResetPassword.addActionListener(this);
            adminOKButton = new JButton("OK");
            adminOKButton.addActionListener(this);
            backToSearch = new JButton("BACK");
            backToSearch.addActionListener(this);
            adminAccountButtons = new JButton[]{adminConfirmEdit, adminCancelEdit, adminConfirmAdd, adminCancelAdd,
                    adminSearch, adminResetPassword, adminOKButton, backToSearch};
            GUI.subJButtonSetup(adminAccountButtons, new Dimension(100, 40));
            adminResetPassword.setPreferredSize(new Dimension(180,40));

            //Create labels
            adminUsernameLabel1 = new JLabel("Username:");
            adminPasswordLabel1 = new JLabel("Password: ");
            adminPasswordLabel2 = new JLabel("Verify Password: ");
            adminUsernameLabel2 = new JLabel("New Username:");
            adminLabels = new JLabel[]{adminUsernameLabel1, adminPasswordLabel1, adminPasswordLabel2, adminUsernameLabel2};
            GUI.JLabelSetup(adminLabels);

            adminUsernameSearchLabel = new JLabel("Username:");
            adminNameSearchLabel = new JLabel("Name:");
            adminPhoneSearchLabel = new JLabel("Phone:");
            adminGenderSearchLabel = new JLabel("Gender:");
            adminAgeSearchLabel1 = new JLabel("Age:");
            adminAgeSearchLabel2 = new JLabel("to");
            adminEmailSearchLabel = new JLabel("Email:");
            adminAddressSearchLabel = new JLabel("Address:");
            adminPointSearchLabel1 = new JLabel("Point:");
            adminPointSearchLabel2 = new JLabel("to");
            adminSearchLabels = new JLabel[]{adminUsernameSearchLabel, adminNameSearchLabel, adminPhoneSearchLabel,
                    adminGenderSearchLabel, adminAgeSearchLabel1, adminAgeSearchLabel2, adminEmailSearchLabel,
                    adminAddressSearchLabel, adminPointSearchLabel1, adminPointSearchLabel2};
            GUI.JLabelSetup(adminSearchLabels);

            //Create input fields
            //JTextField
            adminUsername1 = new JTextField(20);
            adminUsername1.setEnabled(false);
            adminUsername2 = new JTextField(20);
            adminUsernameSearch = new JTextField(20);
            adminUsernameSearch.setPreferredSize(new Dimension(35,40));
            adminNameSearch = new JTextField(10);
            adminPhoneSearch = new JTextField(10);
            adminEmailSearch = new JTextField(10);
            adminAddressSearch = new JTextField(10);
            adminUsernameEdit = new JTextField(20);
            adminUsernameEdit.setEnabled(false);
            adminNameEdit = new JTextField(20);
            adminPhoneEdit = new JTextField(20);
            adminEmailEdit = new JTextField(20);
            adminAddressEdit = new JTextField(20);

            //JPasswordField
            adminPassword1 = new JPasswordField(20);
            adminPassword2 = new JPasswordField(20);
            adminPasswordEdit = new JPasswordField(20);
            adminPasswordEdit.setEditable(false);

            //JComboBox
            String[] userTypes = {"Admin", "Customer"};
            String[] genderTypes = {"Male", "Female"};
            adminUserType = new JComboBox<>(userTypes);
            adminUserType.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
            adminUserType.setPreferredSize(new Dimension(100,40));
            adminUserType.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    customerOnlyAttributes.setVisible(Objects.equals(adminUserType.getSelectedItem(), "Customer"));
                    adminSearchResultPanel.removeAll();
                }
            });
            adminGenderSearch = new JComboBox<>(genderTypes);
            adminGenderSearch.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
            adminGenderSearch.setPreferredSize(new Dimension(100,25));

            //JSpinner
            adminFromAge = new JSpinner(new SpinnerNumberModel(17,1,122,1));
            adminFromAge.setPreferredSize(new Dimension(100,25));
            adminToAge = new JSpinner(new SpinnerNumberModel(17,1,122,1));
            adminToAge.setPreferredSize(new Dimension(100,25));
            adminFromPoint = new JSpinner(new SpinnerNumberModel(0,0,1000,1));
            adminFromPoint.setPreferredSize(new Dimension(100,25));
            adminToPoint = new JSpinner(new SpinnerNumberModel(0,0,1000,1));
            adminToPoint.setPreferredSize(new Dimension(100,25));
            adminAgeEdit = new JSpinner(new SpinnerNumberModel(17,17,122,1));
            adminAgeEdit.setPreferredSize(new Dimension(100,25));
            adminPointEdit = new JSpinner(new SpinnerNumberModel(0,0,1000,1));
            adminPointEdit.setPreferredSize(new Dimension(100,25));

            //JRadioButtons
            adminMale = new JRadioButton("Male");
            adminMale.setFocusable(false);
            adminFemale = new JRadioButton("Female");
            adminFemale.setFocusable(false);
            adminGenderGroup = new ButtonGroup();
            adminGenderGroup.add(adminMale);
            adminGenderGroup.add(adminFemale);

            //JComponent array
            adminComponents = new JComponent[]{adminNameEdit, adminPhoneEdit, adminEmailEdit, adminAddressEdit,
                    adminMale, adminFemale, adminAgeEdit, adminPointEdit};

            //Edit password & Add admin & Edit account panel
            GridBagConstraints accConstraints = new GridBagConstraints();
            adminEditPasswordPanel = new JPanel(new GridBagLayout());
            addAdminPanel = new JPanel(new GridBagLayout());
            adminEditAccountPanel = new JPanel(new GridBagLayout());

            //Setup buttons
            JPanel editPasswordSelectionPanel = new JPanel();
            editPasswordSelectionPanel.setBackground(Color.white);
            editPasswordSelectionPanel.add(adminConfirmEdit);
            editPasswordSelectionPanel.add(adminCancelEdit);
            JPanel addAdminSelectionPanel = new JPanel();
            addAdminSelectionPanel.setBackground(Color.white);
            addAdminSelectionPanel.add(adminConfirmAdd);
            addAdminSelectionPanel.add(adminCancelAdd);
            JPanel editAccountSelectionPanel = new JPanel();
            editAccountSelectionPanel.setBackground(Color.white);
            editAccountSelectionPanel.add(adminResetPassword);
            editAccountSelectionPanel.add(adminOKButton);
            editAccountSelectionPanel.add(backToSearch);

            //Setup labels
            accConstraints.insets = new Insets(8,5,8,5);
            accConstraints.weightx = 1;
            accConstraints.weighty = 1;
            accConstraints.gridx = 0;
            accConstraints.ipady = 0;
            accConstraints.gridwidth = 2;
            for(int i = 0; i < adminLabels.length - 1; i++){
                accConstraints.gridy = i + 1;
                adminEditPasswordPanel.add(adminLabels[i], accConstraints);
                if (i == 0) addAdminPanel.add(adminUsernameLabel2, accConstraints);
            }
            for(int i = 0; i < editLabels.length; i++){
                accConstraints.gridy = i + 1;
                adminEditAccountPanel.add(editLabels[i], accConstraints);
            }

            //Setup fields
            accConstraints.gridx = 2;
            accConstraints.gridy = 1;
            adminEditPasswordPanel.add(adminUsername1, accConstraints);
            addAdminPanel.add(adminUsername2, accConstraints);
            adminEditAccountPanel.add(adminUsernameEdit, accConstraints);

            accConstraints.gridy = 2;
            adminEditPasswordPanel.add(adminPassword1, accConstraints);
            addAdminPanel.add(addAdminSelectionPanel, accConstraints);
            adminEditAccountPanel.add(adminPasswordEdit, accConstraints);

            accConstraints.gridy = 3;
            adminEditPasswordPanel.add(adminPassword2, accConstraints);
            adminEditAccountPanel.add(adminNameEdit, accConstraints);

            accConstraints.gridy = 4;
            adminEditPasswordPanel.add(editPasswordSelectionPanel, accConstraints);
            adminEditAccountPanel.add(adminPhoneEdit, accConstraints);

            accConstraints.gridy = 5;
            JPanel genderPanel = new JPanel();
            genderPanel.setBackground(Color.white);
            genderPanel.add(adminMale);
            genderPanel.add(adminFemale);
            adminEditAccountPanel.add(genderPanel, accConstraints);

            accConstraints.gridy = 6;
            adminEditAccountPanel.add(adminAgeEdit, accConstraints);

            accConstraints.gridy = 7;
            adminEditAccountPanel.add(adminEmailEdit, accConstraints);

            accConstraints.gridy = 8;
            adminEditAccountPanel.add(adminAddressEdit, accConstraints);

            accConstraints.gridy = 9;
            adminEditAccountPanel.add(adminPointEdit, accConstraints);

            accConstraints.gridy = 10;
            adminEditAccountPanel.add(editAccountSelectionPanel, accConstraints);


            //Search account panel
            adminSearchAccountPanel = new JPanel(new GridBagLayout());
            searchAccountAttributesPanel = new JPanel(new GridBagLayout());
            searchAccountAttributesPanel.setBackground(Color.white);
            JPanel commonAttributes = new JPanel();
            commonAttributes.setBackground(Color.white);
            customerOnlyAttributes = new JPanel(new GridBagLayout());
            customerOnlyAttributes.setBackground(Color.white);
            customerOnlyAttributes.setVisible(false);
            adminSearchResultPanel = new JPanel(new GridBagLayout());
            adminSearchResultPanel.setBackground(Color.white);
            adminSearchResultPanel.setPreferredSize(new Dimension(500,250));

            //Common attributes
            commonAttributes.add(adminUsernameSearchLabel);
            commonAttributes.add(adminUsernameSearch);
            commonAttributes.add(adminUserType);
            commonAttributes.add(adminSearch);

            //Customer only attributes
            GridBagConstraints searchAttributeConstraints = new GridBagConstraints();
            searchAttributeConstraints.insets = new Insets(2,2,2,2);
            searchAttributeConstraints.fill = GridBagConstraints.CENTER;
            searchAttributeConstraints.gridy = 0;
            searchAttributeConstraints.gridx = 0;
            searchAttributeConstraints.weightx = 0.1;
            customerOnlyAttributes.add(adminNameSearchLabel, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 1;
            customerOnlyAttributes.add(adminNameSearch, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 2;
            customerOnlyAttributes.add(adminPhoneSearchLabel, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 3;
            customerOnlyAttributes.add(adminPhoneSearch, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 4;
            customerOnlyAttributes.add(adminEmailSearchLabel, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 5;
            customerOnlyAttributes.add(adminEmailSearch, searchAttributeConstraints);

            searchAttributeConstraints.gridy = 1;
            searchAttributeConstraints.gridx = 0;
            customerOnlyAttributes.add(adminAddressSearchLabel, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 1;
            customerOnlyAttributes.add(adminAddressSearch, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 2;
            customerOnlyAttributes.add(adminAgeSearchLabel1, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 3;
            customerOnlyAttributes.add(adminFromAge, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 4;
            customerOnlyAttributes.add(adminAgeSearchLabel2, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 5;
            customerOnlyAttributes.add(adminToAge, searchAttributeConstraints);

            searchAttributeConstraints.gridy = 2;
            searchAttributeConstraints.gridx = 0;
            customerOnlyAttributes.add(adminGenderSearchLabel, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 1;
            customerOnlyAttributes.add(adminGenderSearch, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 2;
            customerOnlyAttributes.add(adminPointSearchLabel1, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 3;
            customerOnlyAttributes.add(adminFromPoint, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 4;
            customerOnlyAttributes.add(adminPointSearchLabel2, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 5;
            customerOnlyAttributes.add(adminToPoint, searchAttributeConstraints);


            //Add two panels above -> search account attributes panel
            accConstraints.gridy = 0;
            searchAccountAttributesPanel.add(commonAttributes, accConstraints);
            accConstraints.gridy = 1;
            searchAccountAttributesPanel.add(customerOnlyAttributes, accConstraints);

            //Add search account attributes panel & searched result -> search account panel
            accConstraints.gridy = 0;
            adminSearchAccountPanel.add(searchAccountAttributesPanel, accConstraints);
            accConstraints.gridy = 1;
            adminSearchAccountPanel.add(adminSearchResultPanel, accConstraints);


            //View account panel
            adminViewAccountPanel = new JPanel(new GridBagLayout());

            //Create account functions panel
            adminPanels = new JPanel[]{adminEditPasswordPanel, addAdminPanel, adminSearchAccountPanel, adminViewAccountPanel, adminEditAccountPanel};
            GUI.JPanelSetup(adminPanels);
            add(adminEditPasswordPanel);
            add(addAdminPanel);
            add(adminSearchAccountPanel);
            add(adminEditAccountPanel);
            add(adminViewAccountPanel);
        }

        else {
            //Create buttons
            customerOKButton = new JButton("OK");
            cancelButton = new JButton("CANCEL");
            customerAccountButtons = new JButton[]{customerOKButton, cancelButton};
            GUI.subJButtonSetup(customerAccountButtons, new Dimension(100, 40));

            //Create labels
            customerPointAndLevel = new JLabel("");
            GUI.JLabelSetup(customerPointAndLevel);

            //Create input fields
            //JTextField
            customerUsernameEdit = new JTextField(20);
            customerNameEdit = new JTextField(20);
            customerPhoneEdit = new JTextField(20);
            customerEmailEdit = new JTextField(20);
            customerAddressEdit = new JTextField(20);

            //JPasswordField
            customerPasswordEdit = new JPasswordField(20);

            //JSpinner
            customerAgeEdit = new JSpinner(new SpinnerNumberModel(17, 17,122,1));
            customerAgeEdit.setPreferredSize(new Dimension(100,25));

            //JRadioButtons
            customerMale = new JRadioButton("male");
            customerMale.setFocusable(false);
            customerFemale = new JRadioButton("female");
            customerFemale.setFocusable(false);
            customerGenderGroup = new ButtonGroup();
            customerGenderGroup.add(customerMale);
            customerGenderGroup.add(customerFemale);

            //JComponent array
            customerComponents = new JComponent[]{customerUsernameEdit, customerNameEdit, customerPhoneEdit,
                    customerEmailEdit, customerAddressEdit, customerPasswordEdit, customerAgeEdit,
                    customerPointAndLevel, customerMale, customerFemale};

            //Create control unit
            JPanel selectionPanel = new JPanel();
            selectionPanel.setBackground(Color.white);
            selectionPanel.add(customerOKButton);
            selectionPanel.add(cancelButton);

            customerPanel = new JPanel(new GridBagLayout());
            customerPanel.setBackground(Color.white);

            GridBagConstraints accConstraints = new GridBagConstraints();
            accConstraints.insets = new Insets(8,5,8,5);
            accConstraints.weightx = 1;
            accConstraints.weighty = 1;
            accConstraints.gridx = 0;
            accConstraints.ipady = 0;
            accConstraints.gridwidth = 2;
            for(int i = 0; i < editLabels.length; i++){
                accConstraints.gridy = i + 1;
                customerPanel.add(editLabels[i], accConstraints);
            }

            //Setup fields
            accConstraints.gridx = 2;
            accConstraints.gridy = 1;
            customerPanel.add(customerUsernameEdit, accConstraints);

            accConstraints.gridy = 2;
            customerPanel.add(customerPasswordEdit, accConstraints);

            accConstraints.gridy = 3;
            customerPanel.add(customerNameEdit, accConstraints);

            accConstraints.gridy = 4;
            customerPanel.add(customerPhoneEdit, accConstraints);

            accConstraints.gridy = 5;
            JPanel genderPanel = new JPanel();
            genderPanel.setBackground(Color.white);
            genderPanel.add(customerMale);
            genderPanel.add(customerFemale);
            customerPanel.add(genderPanel, accConstraints);

            accConstraints.gridy = 6;
            customerPanel.add(customerAgeEdit, accConstraints);

            accConstraints.gridy = 7;
            customerPanel.add(customerEmailEdit, accConstraints);

            accConstraints.gridy = 8;
            customerPanel.add(customerAddressEdit, accConstraints);

            accConstraints.gridy = 9;
            customerPanel.add(customerPointAndLevel, accConstraints);

            accConstraints.gridy = 10;
            customerPanel.add(selectionPanel, accConstraints);

            add(customerPanel);
        }

        setPreferredSize(new Dimension(500,500));

    }

    public static void showEditPasswordPanel(){
        showAccountPanel(adminEditPasswordPanel);
    }

    public static void showAddAdminPanel(){
        showAccountPanel(addAdminPanel);
    }

    public static void showSearchAccountPanel(){
        showAccountPanel(adminSearchAccountPanel);
    }

    public static void showViewAccountPanel(){
        adminViewAccountPanel.removeAll();
        viewAllAccount();
        showAccountPanel(adminViewAccountPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == adminConfirmEdit){
                String passwordInput = String.valueOf(adminPassword1.getPassword());
                String passwordCheckInput = String.valueOf(adminPassword2.getPassword());

                Admin.changePassword(passwordInput, passwordCheckInput);
            }
            else if (e.getSource() == adminCancelEdit){
                GUI.playSound("ji.wav");
                adminPassword1.setText("");
                adminPassword2.setText("");
            }
            else if (e.getSource() == adminConfirmAdd){
                String newUsername = adminUsername2.getText();

                if (Admin.addAdmin(newUsername)){
                    Admin newAdmin = new Admin(newUsername, newUsername);
                    newAdmin.signUp();
                    GUI.playSound("DontSayFiveDe.wav");
                    JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "New admin account is successfully created!");
                }
            }
            else if (e.getSource() == adminCancelAdd){
                GUI.playSound("ji.wav");
                adminUsername2.setText("");
            }
            else if (e.getSource() == adminSearch){
                GUI.playSound("ji.wav");
                adminSearchResultPanel.removeAll();
                searchAccount();
            }
            else if (e.getSource() == editButton){
                if((int) numberSpinner.getValue() == 0){
                    throw new UserNotFoundException();
                }
                GUI.playSound("ji.wav");
                showAccountPanel(adminEditAccountPanel);
                showAccountDetails();
            }
            else if (e.getSource() == adminResetPassword){
                String input = JOptionPane.showInputDialog("Type \"RESET\" to reset the password!");
                if (input != null && input.equals("RESET")){
                    GUI.playSound("ji.wav");
                    int numberValue = (int) numberSpinner.getValue();
                    String accountType = (String) adminUserType.getSelectedItem();
                    Admin.resetPassword(numberValue, accountType);
                }
                else {
                    GUI.playSound("ElectricVoice.wav");
                    JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Reset canceled!");
                }
            }
            else if (e.getSource() == adminOKButton){
                if (Objects.equals(adminUserType.getSelectedItem(), "Admin")) {
                    throw new InvalidUserException();
                }
                String input = JOptionPane.showInputDialog("Type \"CONFIRM\" to proceed!");
                if (input != null && input.equals("CONFIRM")){
                    editAccountDetails();
                }
                else {
                    GUI.playSound("ElectricVoice.wav");
                    JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Edit canceled!");
                }
            }
            else if (e.getSource() == backToSearch){
                GUI.playSound("ji.wav");
                showAccountPanel(adminSearchAccountPanel);
            }
            else if (e.getSource() == deleteButton){
                if((int) numberSpinner.getValue() == 0){
                    throw new UserNotFoundException();
                }
                String input = JOptionPane.showInputDialog("Type \"DELETE\" to confirm the deletion!");
                if (input != null && input.equals("DELETE")){
                    int numberValue = (int) numberSpinner.getValue();
                    String accountType = (String) adminUserType.getSelectedItem();

                    if (Admin.deleteAccount(numberValue, accountType)){
                        GUI.playSound("DontSayFiveDe.wav");
                        JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "User account has been deleted!");
                        adminSearchResultPanel.removeAll();
                        searchAccount();
                    }
                    else {
                        throw new LastAdminException();
                    }
                }
                else {
                    GUI.playSound("ElectricVoice.wav");
                    JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Deletion canceled!");
                }
            }
            else if (e.getSource() == customerOKButton){
                GUI.playSound("ji.wav");
                if (customerPasswordEdit.isEditable()){
                    String password = String.valueOf(customerPasswordEdit.getPassword());
                    if (Customer.changePassword(password)){
                        customerPasswordEdit.setEditable(false);
                    }
                }
                else {
                    String name = customerNameEdit.getText();
                    String phone = customerPhoneEdit.getText();
                    String email = customerEmailEdit.getText();
                    String address = customerAddressEdit.getText();
                    int age = (int) customerAgeEdit.getValue();
                    String gender;
                    if (customerMale.isSelected()) gender = "male";
                    else gender = "female";

                    if (Customer.editAccountDetails(name, phone, email, address, gender, age)){
                        GUI.disableFields(customerComponents);
                    }
                }
            }
            else if (e.getSource() == cancelButton){
                GUI.playSound("ji.wav");
                GUI.disableFields(customerComponents);
            }
        } catch (UserNotFoundException userNotFoundException){
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Please select a row number to edit!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (InvalidUserException invalidUserException) {
            GUI.playSound("ElectricVoice.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Admin details are not available to modify!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (LastAdminException lastAdminException){
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "You can't delete the last admin account!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (Exception exception){
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Unexpected error occurred! Please try again later.", "Registration Approval Failed", JOptionPane.WARNING_MESSAGE);
        }
    }

    private static void showAccountPanel(JPanel panel){
        for (JPanel i : adminPanels) {
            i.setVisible(false);
        }
        panel.setVisible(true);
    }

    public static JSpinner getAdminFromAge() {
        return adminFromAge;
    }

    public static JSpinner getAdminToAge() {
        return adminToAge;
    }

    public static JSpinner getAdminFromPoint() {
        return adminFromPoint;
    }

    public static JSpinner getAdminToPoint() {
        return adminToPoint;
    }

    public static JPasswordField getAdminPassword1() {
        return adminPassword1;
    }

    public static JPasswordField getAdminPassword2() {
        return adminPassword2;
    }

    public static JTextField getAdminUsername1() {
        return adminUsername1;
    }

    public static JTextField getAdminUsername2() {
        return adminUsername2;
    }


    private void searchAccount(){
        ArrayList<Admin> searchedAdminList = new ArrayList<>();
        ArrayList<Customer> searchedCustomerList = new ArrayList<>();
        String userTypeInput = (String) adminUserType.getSelectedItem();
        String usernameInput = adminUsernameSearch.getText();
        String[] tableColumn = new String[]{};
        Object[][] tempTable = new Object[][]{};


        if (userTypeInput.equals("Admin")){
            searchedAdminList = Admin.searchAdmin(usernameInput);

            if (!(searchedAdminList.size() == 0)){
                tableColumn = new String[]{"No", "Username"};
                tempTable = new Object[searchedAdminList.size()][2];
                int i = 0;
                for (Admin admin : searchedAdminList){
                    i = insertAdminTable(tempTable, i, admin);
                }
            }
        }
        else {
            String nameInput = adminNameSearch.getText();
            String phoneInput = adminPhoneSearch.getText();
            String emailInput = adminEmailSearch.getText();
            String addressInput = adminAddressSearch.getText();
            String genderInput = (String) adminGenderSearch.getSelectedItem();
            int ageValue1 = (int) adminFromAge.getValue();
            int ageValue2 = (int) adminToAge.getValue();
            int pointValue1 = (int) adminFromPoint.getValue();
            int pointValue2 = (int) adminToPoint.getValue();

            searchedCustomerList = Admin.searchCustomer(usernameInput, nameInput, phoneInput, emailInput, addressInput, genderInput, ageValue1, ageValue2, pointValue1, pointValue2);

            if (!(searchedCustomerList.size() == 0)){
                tableColumn = new String[]{"No", "Username", "Name", "Phone Num.", "Gender", "Age", "Email", "Address", "Points"};
                tempTable = new Object[searchedCustomerList.size()][9];
                int i = 0;
                for (Customer customer : searchedCustomerList){
                    i = insertCustomerTable(tempTable, i, customer);
                }
            }
        }

        searchTable = new JTable(tempTable, tableColumn);
        searchTable.setVisible(true);
        JScrollPane scrollPane = new JScrollPane(searchTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        searchTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPane.setPreferredSize(new Dimension(500,200));

        JPanel bottomPanel = new JPanel(new GridBagLayout());
        bottomPanel.setBackground(Color.white);
        GridBagConstraints bottomConstraints = new GridBagConstraints();

        bottomConstraints.fill = GridBagConstraints.BOTH;
        bottomConstraints.anchor = GridBagConstraints.WEST;
        bottomConstraints.gridx = 0;
        bottomConstraints.insets = new Insets(5,5,5,20);
        JLabel numberLabel = new JLabel("Row Number: ");
        GUI.JLabelSetup(numberLabel);
        bottomPanel.add(numberLabel, bottomConstraints);

        bottomConstraints.gridx = 1;
        int maxNum;
        if (userTypeInput.equals("Admin")){
            maxNum = searchedAdminList.size();
        }
        else {
            maxNum = searchedCustomerList.size();
        }
        numberSpinner = new JSpinner(new SpinnerNumberModel(0, 0, maxNum, 1));
        bottomPanel.add(numberSpinner, bottomConstraints);

        editButton = new JButton("EDIT");
        deleteButton = new JButton("DELETE");
        editButton.addActionListener(this);
        deleteButton.addActionListener(this);
        JButton[] buttons = new JButton[]{editButton, deleteButton};
        GUI.subJButtonSetup(buttons, new Dimension(100,30));

        bottomConstraints.gridx = 3;
        bottomPanel.add(editButton, bottomConstraints);

        bottomConstraints.gridx = 4;
        bottomPanel.add(deleteButton, bottomConstraints);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 0.8;
        adminSearchResultPanel.add(scrollPane, constraints);

        constraints.gridy = 1;
        constraints.weighty = 0.2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.WEST;
        adminSearchResultPanel.add(bottomPanel, constraints);

        adminSearchResultPanel.validate();
    }

    private void showAccountDetails() {
        Admin adminAccount;
        Customer customerAccount;
        int numberValue = (int) numberSpinner.getValue();
        GUI.resetFields(adminComponents);
        adminAgeEdit.setValue(17);
        adminPointEdit.setValue(0);

        if (Objects.equals(adminUserType.getSelectedItem(), "Admin")) {
            adminAccount = FileIO.adminList.get(numberValue - 1);
            adminUsernameEdit.setText(adminAccount.getUsername());
            adminPasswordEdit.setText(adminAccount.getPassword());
            for (JComponent i : adminComponents) {
                i.setEnabled(false);
            }
        }
        else {
            customerAccount = FileIO.customerList.get(numberValue - 1);
            adminUsernameEdit.setText(customerAccount.getUsername());
            adminPasswordEdit.setText(customerAccount.getPassword());
            adminNameEdit.setText(customerAccount.getName());
            adminPhoneEdit.setText(customerAccount.getPhone());
            adminEmailEdit.setText(customerAccount.getEmail());
            adminAddressEdit.setText(customerAccount.getAddress());
            if (customerAccount.getGender().equals("Male")) {
                adminMale.setSelected(true);
            }
            else {
                adminFemale.setSelected(true);
            }
            adminAgeEdit.setValue(customerAccount.getAge());
            adminPointEdit.setValue(customerAccount.getPoints());
        }
    }

    private void editAccountDetails(){
        int numberValue = (int) numberSpinner.getValue();

        if (adminUserType.getSelectedItem().equals("Customer")){
            String nameInput = adminNameEdit.getText();
            String phoneInput = adminPhoneEdit.getText();
            String emailInput = adminEmailEdit.getText();
            String addressInput = adminAddressEdit.getText();
            String genderInput;
            if (adminMale.isSelected()){
                genderInput = "Male";
            }
            else {
                genderInput = "Female";
            }
            int ageValue = (int) adminAgeEdit.getValue();
            int pointValue = (int) adminPointEdit.getValue();

            Admin.editAccountDetails(numberValue, nameInput, phoneInput, emailInput, addressInput, genderInput, ageValue, pointValue);
        }
    }

    private static void viewAllAccount() {
        String[] adminColumn = new String[]{"No", "Username"};
        Object[][] adminTempTable = new Object[FileIO.adminList.size()][2];
        int i = 0;
        for (Admin admin : FileIO.adminList) {
            i = insertAdminTable(adminTempTable, i, admin);
        }

        allAdminTable = new JTable(adminTempTable, adminColumn);
        allAdminTable.setVisible(true);
        JScrollPane admScrollPane = new JScrollPane(allAdminTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        allAdminTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        admScrollPane.setPreferredSize(new Dimension(500,200));

        String[] customerColumn = new String[]{"No", "Username", "Name", "Phone Num.", "Gender", "Age", "Email", "Address", "Points"};
        Object[][] customerTempTable = new Object[FileIO.customerList.size()][9];
        int j = 0;
        for (Customer customer : FileIO.customerList){
            j = insertCustomerTable(customerTempTable, j, customer);
        }

        allCustomerTable = new JTable(customerTempTable, customerColumn);
        allCustomerTable.setVisible(true);
        JScrollPane cusScrollPane = new JScrollPane(allCustomerTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        allCustomerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        cusScrollPane.setPreferredSize(new Dimension(500,200));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10,5,10,5);
        constraints.gridy = 0;
        adminViewAccountPanel.add(admScrollPane, constraints);
        constraints.gridy = 1;
        adminViewAccountPanel.add(cusScrollPane, constraints);

        adminViewAccountPanel.validate();
    }

    private static int insertAdminTable(Object[][] adminTempTable, int i, Admin admin){
        adminTempTable[i][0] = i + 1;
        adminTempTable[i][1] = admin.getUsername();
        i++;
        return i;
    }

    private static int insertCustomerTable(Object[][] customerTempTable, int j, Customer customer) {
        customerTempTable[j][0] = j + 1;
        customerTempTable[j][1] = customer.getUsername();
        customerTempTable[j][2] = customer.getName();
        customerTempTable[j][3] = customer.getPhone();
        customerTempTable[j][4] = customer.getGender();
        customerTempTable[j][5] = customer.getAge();
        customerTempTable[j][6] = customer.getEmail();
        customerTempTable[j][7] = customer.getAddress();
        customerTempTable[j][8] = customer.getPoints();
        j++;
        return j;
    }

    public static void showCustomerDetails(){
        GUI.resetFields(customerComponents);
        GUI.disableFields(customerComponents);

        int points = CarRentalSystem.loginCustomer.getPoints();
        int memberLevel = CarRentalSystem.loginCustomer.getMemberLevel();
        String memberDetails = points + " : (" + "Level: " + memberLevel + ")";

        customerUsernameEdit.setText(CarRentalSystem.loginCustomer.getUsername());
        customerNameEdit.setText(CarRentalSystem.loginCustomer.getName());
        customerPhoneEdit.setText(CarRentalSystem.loginCustomer.getPhone());
        customerEmailEdit.setText(CarRentalSystem.loginCustomer.getEmail());
        customerAddressEdit.setText(CarRentalSystem.loginCustomer.getAddress());
        customerPasswordEdit.setText(CarRentalSystem.loginCustomer.getPassword());
        customerAgeEdit.setValue(CarRentalSystem.loginCustomer.getAge());
        customerPointAndLevel.setText(memberDetails);
        if (CarRentalSystem.loginCustomer.getGender().equals("male")){
            customerMale.setSelected(true);
        }
        else {
            customerFemale.setSelected(true);
        }
    }

    public static void customerChangePassword(){
        GUI.disableFields(customerComponents);
        if (Customer.checkIdentity("You can now change your password!")){
            customerPasswordEdit.setEditable(true);
        }
    }

    public static void customerEditAccount(){
        customerPasswordEdit.setEditable(false);
        customerNameEdit.setEditable(true);
        customerPhoneEdit.setEditable(true);
        customerEmailEdit.setEditable(true);
        customerAddressEdit.setEditable(true);
        customerAgeEdit.setEnabled(true);
        customerMale.setEnabled(true);
        customerFemale.setEnabled(true);
    }

    public static void customerDeleteAccount(){
        GUI.disableFields(customerComponents);
        int size = CarRentalSystem.loginCustomer.getMyBookings().size();
        if (Customer.checkIdentity("Verification success!") && CarRentalSystem.loginCustomer.getMyBookings().get(size - 1).getStatus() == Booking.Status.COMPLETED){
            String input = JOptionPane.showInputDialog("Type \"DELETE\" to confirm the deletion!");
            if (input != null && input.equals("DELETE")){
                GUI.playSound("DontSayFiveDe.wav");
                Customer.deleteAccount();
                JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Goodbye Honey~", "Account Deleted", JOptionPane.INFORMATION_MESSAGE);
                CarRentalSystem.loginCustomer = null;
                CarRentalSystem.currentFrame.setVisible(false);
                CarRentalSystem.homePage.getFrame().setVisible(true);
            }
            else {
                GUI.playSound("ElectricVoice.wav");
                JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Deletion canceled!");
            }
        }
    }
}
