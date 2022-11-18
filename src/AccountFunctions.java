import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Objects;

public class AccountFunctions extends JPanel implements ActionListener {

    private static JPanel editPasswordPanel, addAdminPanel, searchAccountPanel, viewAccountPanel, editAccountPanel;
    private JPanel customerOnlyAttributes, searchAccountAttributesPanel, searchResultPanel;
    private JButton confirmEdit, cancelEdit, confirmAdd, cancelAdd, search, editButton, resetPassword, OKButton, backToSearch, deleteButton;
    private JLabel usernameLabel1, passwordLabel1, passwordLabel2, usernameLabel2;
    private JLabel usernameSearchLabel, nameSearchLabel, phoneSearchLabel, genderSearchLabel, ageSearchLabel1, ageSearchLabel2,
            emailSearchLabel, addressSearchLabel, pointSearchLabel1, pointSearchLabel2;
    private JLabel usernameEditLabel, passwordEditLabel, nameEditLabel, phoneEditLabel, genderEditLabel, ageEditLabel,
            emailEditLabel, addressEditLabel, pointEditLabel;
    private static JTextField username1, username2;
    private JTextField usernameSearch, nameSearch, phoneSearch, emailSearch, addressSearch;
    private JTextField usernameEdit, nameEdit, phoneEdit, emailEdit, addressEdit;
    private static JPasswordField password1, password2;
    private JPasswordField passwordEdit;
    private JRadioButton male, female;
    private ButtonGroup genderGroup;
    private JComboBox<String> userType, genderSearch;
    private static JSpinner fromAge, toAge, fromPoint, toPoint;
    private JSpinner numberSpinner, ageEdit, pointEdit;
    private JTable searchTable;
    private static JTable allAdminTable, allCustomerTable;
    private static JPanel[] panels;
    private JLabel[] labels, searchLabels, editLabels;
    private JButton[] accountButtons;
    private JComponent[] components;

    public AccountFunctions(){

        //Create buttons
        confirmEdit = new JButton("EDIT");
        cancelEdit = new JButton("CANCEL");
        confirmEdit.addActionListener(this);
        cancelEdit.addActionListener(this);
        confirmAdd = new JButton("ADD");
        cancelAdd = new JButton("CANCEL");
        confirmAdd.addActionListener(this);
        cancelAdd.addActionListener(this);
        search = new JButton("SEARCH");
        search.addActionListener(this);
        resetPassword = new JButton("RESET PASSWORD");
        resetPassword.addActionListener(this);
        OKButton = new JButton("OK");
        OKButton.addActionListener(this);
        backToSearch = new JButton("BACK");
        backToSearch.addActionListener(this);
        accountButtons = new JButton[]{confirmEdit, cancelEdit, confirmAdd, cancelAdd, search, resetPassword, OKButton, backToSearch};
        GUI.subJButtonSetup(accountButtons, new Dimension(100, 40));
        resetPassword.setPreferredSize(new Dimension(180,40));

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

        //Create input fields
        //JTextField
        username1 = new JTextField(20);
        username1.setEnabled(false);
        username2 = new JTextField(20);
        usernameSearch = new JTextField(20);
        usernameSearch.setPreferredSize(new Dimension(35,40));
        nameSearch = new JTextField(10);
        phoneSearch = new JTextField(10);
        emailSearch = new JTextField(10);
        addressSearch = new JTextField(10);
        usernameEdit = new JTextField(20);
        usernameEdit.setEnabled(false);
        nameEdit = new JTextField(20);
        phoneEdit = new JTextField(20);
        emailEdit = new JTextField(20);
        addressEdit = new JTextField(20);

        //JPasswordField
        password1 = new JPasswordField(20);
        password2 = new JPasswordField(20);
        passwordEdit = new JPasswordField(20);
        passwordEdit.setEditable(false);

        //JComboBox
        String[] userTypes = {"Admin", "Customer"};
        String[] genderTypes = {"male", "female"};
        userType = new JComboBox<>(userTypes);
        userType.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        userType.setPreferredSize(new Dimension(100,40));
        userType.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED){
                customerOnlyAttributes.setVisible(Objects.equals(userType.getSelectedItem(), "Customer"));
                searchResultPanel.removeAll();
            }
        });
        genderSearch = new JComboBox<>(genderTypes);
        genderSearch.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        genderSearch.setPreferredSize(new Dimension(100,25));

        //JSpinner
        fromAge = new JSpinner(new SpinnerNumberModel(17,1,122,1));
        fromAge.setPreferredSize(new Dimension(100,25));
        toAge = new JSpinner(new SpinnerNumberModel(17,1,122,1));
        toAge.setPreferredSize(new Dimension(100,25));
        fromPoint = new JSpinner(new SpinnerNumberModel(0,0,1000,1));
        fromPoint.setPreferredSize(new Dimension(100,25));
        toPoint = new JSpinner(new SpinnerNumberModel(0,0,1000,1));
        toPoint.setPreferredSize(new Dimension(100,25));
        ageEdit = new JSpinner(new SpinnerNumberModel(17,17,122,1));
        ageEdit.setPreferredSize(new Dimension(100,25));
        pointEdit = new JSpinner(new SpinnerNumberModel(0,0,1000,1));
        pointEdit.setPreferredSize(new Dimension(100,25));

        //JRadioButtons
        male = new JRadioButton("male");
        male.setFocusable(false);
        female = new JRadioButton("female");
        female.setFocusable(false);
        genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        //JComponent array
        components = new JComponent[]{nameEdit, phoneEdit, emailEdit, addressEdit,
                male, female, ageEdit, pointEdit};

        //Edit password & Add admin & Edit account panel
        GridBagConstraints accConstraints = new GridBagConstraints();
        editPasswordPanel = new JPanel(new GridBagLayout());
        addAdminPanel = new JPanel(new GridBagLayout());
        editAccountPanel = new JPanel(new GridBagLayout());

        //Setup buttons
        JPanel editPasswordSelectionPanel = new JPanel();
        editPasswordSelectionPanel.setBackground(Color.white);
        editPasswordSelectionPanel.add(confirmEdit);
        editPasswordSelectionPanel.add(cancelEdit);
        JPanel addAdminSelectionPanel = new JPanel();
        addAdminSelectionPanel.setBackground(Color.white);
        addAdminSelectionPanel.add(confirmAdd);
        addAdminSelectionPanel.add(cancelAdd);
        JPanel editAccountSelectionPanel = new JPanel();
        editAccountSelectionPanel.setBackground(Color.white);
        editAccountSelectionPanel.add(resetPassword);
        editAccountSelectionPanel.add(OKButton);
        editAccountSelectionPanel.add(backToSearch);

        //Setup labels
        accConstraints.insets = new Insets(8,5,8,5);
        accConstraints.weightx = 1;
        accConstraints.weighty = 1;
        accConstraints.gridx = 0;
        accConstraints.ipady = 0;
        accConstraints.gridwidth = 2;
        for(int i = 0; i < labels.length - 1; i++){
            accConstraints.gridy = i + 1;
            editPasswordPanel.add(labels[i], accConstraints);
            if (i == 0) addAdminPanel.add(usernameLabel2, accConstraints);
        }
        for(int i = 0; i < editLabels.length; i++){
            accConstraints.gridy = i + 1;
            editAccountPanel.add(editLabels[i], accConstraints);
        }

        //Setup fields
        accConstraints.gridx = 2;
        accConstraints.gridy = 1;
        editPasswordPanel.add(username1, accConstraints);
        addAdminPanel.add(username2, accConstraints);
        editAccountPanel.add(usernameEdit, accConstraints);

        accConstraints.gridy = 2;
        editPasswordPanel.add(password1, accConstraints);
        addAdminPanel.add(addAdminSelectionPanel, accConstraints);
        editAccountPanel.add(passwordEdit, accConstraints);

        accConstraints.gridy = 3;
        editPasswordPanel.add(password2, accConstraints);
        editAccountPanel.add(nameEdit, accConstraints);

        accConstraints.gridy = 4;
        editPasswordPanel.add(editPasswordSelectionPanel, accConstraints);
        editAccountPanel.add(phoneEdit, accConstraints);

        accConstraints.gridy = 5;
        JPanel genderPanel = new JPanel();
        genderPanel.setBackground(Color.white);
        genderPanel.add(male);
        genderPanel.add(female);
        editAccountPanel.add(genderPanel, accConstraints);

        accConstraints.gridy = 6;
        editAccountPanel.add(ageEdit, accConstraints);

        accConstraints.gridy = 7;
        editAccountPanel.add(emailEdit, accConstraints);

        accConstraints.gridy = 8;
        editAccountPanel.add(addressEdit, accConstraints);

        accConstraints.gridy = 9;
        editAccountPanel.add(pointEdit, accConstraints);

        accConstraints.gridy = 10;
        editAccountPanel.add(editAccountSelectionPanel, accConstraints);


        //Search account panel
        searchAccountPanel = new JPanel(new GridBagLayout());
        searchAccountAttributesPanel = new JPanel(new GridBagLayout());
        searchAccountAttributesPanel.setBackground(Color.white);
        JPanel commonAttributes = new JPanel();
        commonAttributes.setBackground(Color.white);
        customerOnlyAttributes = new JPanel(new GridBagLayout());
        customerOnlyAttributes.setBackground(Color.white);
        customerOnlyAttributes.setVisible(false);
        searchResultPanel = new JPanel(new GridBagLayout());
        searchResultPanel.setBackground(Color.white);
        searchResultPanel.setPreferredSize(new Dimension(500,250));

        //Common attributes
        commonAttributes.add(usernameSearchLabel);
        commonAttributes.add(usernameSearch);
        commonAttributes.add(userType);
        commonAttributes.add(search);

        //Customer only attributes
        GridBagConstraints searchAttributeConstraints = new GridBagConstraints();
        searchAttributeConstraints.insets = new Insets(2,2,2,2);
        searchAttributeConstraints.fill = GridBagConstraints.CENTER;
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

        searchAttributeConstraints.gridy = 1;
        searchAttributeConstraints.gridx = 0;
        customerOnlyAttributes.add(addressSearchLabel, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 1;
        customerOnlyAttributes.add(addressSearch, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 2;
        customerOnlyAttributes.add(ageSearchLabel1, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 3;
        customerOnlyAttributes.add(fromAge, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 4;
        customerOnlyAttributes.add(ageSearchLabel2, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 5;
        customerOnlyAttributes.add(toAge, searchAttributeConstraints);

        searchAttributeConstraints.gridy = 2;
        searchAttributeConstraints.gridx = 0;
        customerOnlyAttributes.add(genderSearchLabel, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 1;
        customerOnlyAttributes.add(genderSearch, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 2;
        customerOnlyAttributes.add(pointSearchLabel1, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 3;
        customerOnlyAttributes.add(fromPoint, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 4;
        customerOnlyAttributes.add(pointSearchLabel2, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 5;
        customerOnlyAttributes.add(toPoint, searchAttributeConstraints);


        //Add two panels above -> search account attributes panel
        accConstraints.gridy = 0;
        searchAccountAttributesPanel.add(commonAttributes, accConstraints);
        accConstraints.gridy = 1;
        searchAccountAttributesPanel.add(customerOnlyAttributes, accConstraints);

        //Add search account attributes panel & searched result -> search account panel
        accConstraints.gridy = 0;
        searchAccountPanel.add(searchAccountAttributesPanel, accConstraints);
        accConstraints.gridy = 1;
        searchAccountPanel.add(searchResultPanel, accConstraints);


        //View account panel
        viewAccountPanel = new JPanel(new GridBagLayout());

        //Create account functions panel
        panels = new JPanel[]{editPasswordPanel, addAdminPanel, searchAccountPanel, viewAccountPanel, editAccountPanel};
        GUI.JPanelSetup(panels);
        setPreferredSize(new Dimension(500,500));
        add(editPasswordPanel);
        add(addAdminPanel);
        add(searchAccountPanel);
        add(editAccountPanel);
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
        viewAccountPanel.removeAll();
        viewAllAccount();
        showAccountPanel(viewAccountPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == confirmEdit){
                String passwordInput = String.valueOf(password1.getPassword());
                String passwordCheckInput = String.valueOf(password2.getPassword());

                Admin.changePassword(passwordInput, passwordCheckInput);
            }
            else if (e.getSource() == cancelEdit){
                password1.setText("");
                password2.setText("");
            }
            else if (e.getSource() == confirmAdd){
                String newUsername = username2.getText();

                Admin.addAdmin(newUsername);
            }
            else if (e.getSource() == cancelAdd){
                username2.setText("");
            }
            else if (e.getSource() == search){
                searchResultPanel.removeAll();
                searchAccount();
            }
            else if (e.getSource() == editButton){
                if((int) numberSpinner.getValue() == 0){
                    throw new UserNotFoundException();
                }
                showAccountPanel(editAccountPanel);
                showAccountDetails();
            }
            else if (e.getSource() == resetPassword){
                String input = JOptionPane.showInputDialog("Type \"RESET\" to reset the password!");
                if (input != null && input.equals("RESET")){
                    int numberValue = (int) numberSpinner.getValue();
                    String accountType = (String) userType.getSelectedItem();
                    Admin.resetPassword(numberValue, accountType);
                }
                else {
                    JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Reset canceled!");
                }
            }
            else if (e.getSource() == OKButton){
                String input = JOptionPane.showInputDialog("Type \"CONFIRM\" to proceed!");
                if (input != null && input.equals("CONFIRM")){
                    editAccountDetails();
                }
                else {
                    JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Edit canceled!");
                }
            }
            else if (e.getSource() == backToSearch){
                showAccountPanel(searchAccountPanel);
            }
            else if (e.getSource() == deleteButton){
                String input = JOptionPane.showInputDialog("Type \"DELETE\" to confirm the deletion!");
                if (input != null && input.equals("DELETE")){
                    int numberValue = (int) numberSpinner.getValue();
                    String accountType = (String) userType.getSelectedItem();

                    if (Admin.deleteAccount(numberValue, accountType)){
                        JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "User account has been deleted!");
                    }
                    else {
                        throw new LastAdminException();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Deletion canceled!");
                }
            }
        } catch (UserNotFoundException userNotFoundException){
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Please select a row number to edit!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (LastAdminException lastAdminException){
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "You can't delete the last admin account!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (Exception exception){
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Something wrong!");
        }
    }

    private static void showAccountPanel(JPanel panel){
        for (JPanel i : panels) {
            i.setVisible(false);
        }
        panel.setVisible(true);
    }

    public static JSpinner getFromAge() {
        return fromAge;
    }

    public static JSpinner getToAge() {
        return toAge;
    }

    public static JSpinner getFromPoint() {
        return fromPoint;
    }

    public static JSpinner getToPoint() {
        return toPoint;
    }

    public static JPasswordField getPassword1() {
        return password1;
    }

    public static JPasswordField getPassword2() {
        return password2;
    }

    public static JTextField getUsername1() {
        return username1;
    }

    public static JTextField getUsername2() {
        return username2;
    }


    private void searchAccount(){
        ArrayList<Admin> searchedAdminList = new ArrayList<>();
        ArrayList<Customer> searchedCustomerList = new ArrayList<>();
        String userTypeInput = (String) userType.getSelectedItem();
        String usernameInput = usernameSearch.getText();
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
            String nameInput = nameSearch.getText();
            String phoneInput = phoneSearch.getText();
            String emailInput = emailSearch.getText();
            String addressInput = addressSearch.getText();
            String genderInput = (String) genderSearch.getSelectedItem();
            int ageValue1 = (int) fromAge.getValue();
            int ageValue2 = (int) toAge.getValue();
            int pointValue1 = (int) fromPoint.getValue();
            int pointValue2 = (int) toPoint.getValue();

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
        searchResultPanel.add(scrollPane, constraints);

        constraints.gridy = 1;
        constraints.weighty = 0.2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.WEST;
        searchResultPanel.add(bottomPanel, constraints);

        searchResultPanel.validate();
    }

    private void showAccountDetails() {
        Admin adminAccount;
        Customer customerAccount;
        int numberValue = (int) numberSpinner.getValue();
        resetFields(components);
        ageEdit.setValue(17);
        pointEdit.setValue(0);

        if (Objects.equals(userType.getSelectedItem(), "Admin")) {
            adminAccount = FileIO.adminList.get(numberValue - 1);
            usernameEdit.setText(adminAccount.getUsername());
            passwordEdit.setText(adminAccount.getPassword());
            for (JComponent i : components) {
                i.setEnabled(false);
            }
        }
        else {
            customerAccount = FileIO.customerList.get(numberValue - 1);
            usernameEdit.setText(customerAccount.getUsername());
            passwordEdit.setText(customerAccount.getPassword());
            nameEdit.setText(customerAccount.getName());
            phoneEdit.setText(customerAccount.getPhone());
            emailEdit.setText(customerAccount.getEmail());
            addressEdit.setText(customerAccount.getAddress());
            if (customerAccount.getGender().equals("male")){
                male.setSelected(true);
            }
            else {
                female.setSelected(true);
            }
            ageEdit.setValue(customerAccount.getAge());
            pointEdit.setValue(customerAccount.getPoints());
        }
    }

    public static void resetFields(JComponent[] components){ //TODO: POLYMORPHISM
        for (JComponent i : components) {
            i.setEnabled(true);
            if (i instanceof JTextField){
                ((JTextField) i).setText("");
            }
            if (i instanceof JRadioButton){
                ((JRadioButton) i).setSelected(false);
            }
        }
    }

    private void editAccountDetails(){
        int numberValue = (int) numberSpinner.getValue();

        try {
            if (userType.getSelectedItem().equals("Admin")) {
                throw new InvalidUserException();
            }
            else if (userType.getSelectedItem().equals("Customer")){

                String nameInput = nameEdit.getText();
                String phoneInput = phoneEdit.getText();
                String emailInput = emailEdit.getText();
                String addressInput = addressEdit.getText();
                String genderInput;
                if (male.isSelected()){
                    genderInput = "male";
                }
                else {
                    genderInput = "female";
                }
                int ageValue = (int) ageEdit.getValue();
                int pointValue = (int) pointEdit.getValue();

                Admin.editAccountDetails(numberValue, nameInput, phoneInput, emailInput, addressInput, genderInput, ageValue, pointValue);
            }
        } catch (InvalidUserException invalidUserException) {
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Admin details are not available to modify!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (Exception exception){
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Something wrong!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
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
        viewAccountPanel.add(admScrollPane, constraints);
        constraints.gridy = 1;
        viewAccountPanel.add(cusScrollPane, constraints);

        viewAccountPanel.validate();
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
}
