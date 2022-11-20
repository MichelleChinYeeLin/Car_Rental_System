import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerMenu implements ActionListener {

    /* MAIN */
    private JFrame frame;
    private JPanel carsPanel, bookingsPanel, accountPanel;
    private JButton logout, cars, booking, account;
    private JButton[] buttons, carButtons, bookingButtons, accountButtons;
    private JPanel[] panels;

    /* CAR */
    private CarFunctions carFunctionsPanel;
    private JButton searchCar, allCar;
    // search -> book?

    /* BOOKING */
    private JPanel bookingFunctionsPanel;
//    private CarFunctions forSearchOnly;
    private JButton makeBooking, deleteBooking, viewBooking;
    // book -> search car -> book
    // view -> (return car -> payment)/receipt/feedback
    // cant quit during payment!!!!!!!!!!!!!!!!!!!!!!!!!!

    /* ACCOUNT */
    private JPanel accountFunctionsPanel; // show profile
    private JButton changePassword, editAccount, deleteAccount, OKButton, cancelButton;
    private JLabel usernameEditLabel, passwordEditLabel, nameEditLabel, phoneEditLabel, genderEditLabel, ageEditLabel,
            emailEditLabel, addressEditLabel, pointEditLabel;
    private JTextField usernameEdit, nameEdit, phoneEdit, emailEdit, addressEdit;
    private static JPasswordField passwordEdit;
    private JSpinner ageEdit, pointEdit;
    private JRadioButton male, female;
    private ButtonGroup genderGroup;
    private JLabel[] accountLabels;
    private JComponent[] components;

    public CustomerMenu(){
        frame = new JFrame("Customer Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUI.JFrameSetup(frame);
        frame.setLayout(new GridBagLayout());

        //Create buttons
        cars = new JButton("Cars");
        booking = new JButton("Bookings");
        account = new JButton("Account");
        logout = new JButton("Logout");
        buttons = new JButton[]{cars, booking, account, logout};
        cars.addActionListener(this);
        booking.addActionListener(this);
        account.addActionListener(this);
        logout.addActionListener(this);
        GUI.JButtonSetup(buttons);

        //Button Panel
        GridBagLayout buttonLayout = new GridBagLayout();
        JPanel buttonPanel = new JPanel(buttonLayout);
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 0;
        buttonConstraints.fill = GridBagConstraints.HORIZONTAL;
        for(int i = 0; i < buttons.length; i++){
            buttonConstraints.gridy = i;
            buttonPanel.add(buttons[i], buttonConstraints);
        }

        //Position button panel in the frame
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5,5,5,5);
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.weighty = 1;
        constraints.weightx = 0.01;
        frame.add(buttonPanel, constraints);

        /* CAR */
        //Create buttons
        searchCar = new JButton("Search car");
        allCar = new JButton("All Cars");
        carButtons = new JButton[]{searchCar, allCar};
        searchCar.addActionListener(this);
        allCar.addActionListener(this);
        GUI.subJButtonSetup(carButtons, new Dimension(120, 40));

        //Create car panel
        carsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints carConstraints = new GridBagConstraints();
        carFunctionsPanel = new CarFunctions();
        //TODO dont know why cant display

        //Car button panel
        JPanel carButtonPanel = new JPanel(new GridBagLayout());
        carButtonPanel.add(searchCar);
        carButtonPanel.add(allCar);

        //Position elements in car panel
        carConstraints.gridy = 0;
        carsPanel.add(carButtonPanel, carConstraints);
        carConstraints.gridy = 1;
        carsPanel.add(carFunctionsPanel, carConstraints);


        /* BOOKING */
        //Create buttons
        makeBooking = new JButton("Make Booking");
        deleteBooking = new JButton("Delete Booking");
        viewBooking = new JButton("All Bookings");
        bookingButtons = new JButton[]{makeBooking, deleteBooking, viewBooking};
        makeBooking.addActionListener(this);
        deleteBooking.addActionListener(this);
        viewBooking.addActionListener(this);
        GUI.subJButtonSetup(bookingButtons, new Dimension(140, 40));

        //Create booking panel
        bookingsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints bkgConstraints = new GridBagConstraints();
        bookingFunctionsPanel = new JPanel(); // to change

        //Booking button panel
        JPanel bookingButtonPanel = new JPanel(new GridBagLayout());
        bookingButtonPanel.add(makeBooking);
        bookingButtonPanel.add(deleteBooking);
        bookingButtonPanel.add(viewBooking);

        //Position elements in booking panel
        bkgConstraints.gridy = 0;
        bookingsPanel.add(bookingButtonPanel, bkgConstraints);
        bkgConstraints.gridy = 1;
        bookingsPanel.add(bookingFunctionsPanel, bkgConstraints);


        /* ACCOUNT */
        //Create buttons
        changePassword = new JButton("Change Password");
        editAccount = new JButton("Edit Account");
        deleteAccount = new JButton("Delete Account");
        OKButton = new JButton("OK");
        cancelButton = new JButton("CANCEL");
        accountButtons = new JButton[]{changePassword, editAccount, deleteAccount, OKButton, cancelButton};
        changePassword.addActionListener(this);
        editAccount.addActionListener(this);
        deleteAccount.addActionListener(this);
        OKButton.addActionListener(this);
        cancelButton.addActionListener(this);
        GUI.subJButtonSetup(accountButtons, new Dimension(140, 40));
        changePassword.setPreferredSize(new Dimension(160,40));

        //Create labels
        usernameEditLabel = new JLabel("Username:");
        passwordEditLabel = new JLabel("Password:");
        nameEditLabel = new JLabel("Name:");
        phoneEditLabel = new JLabel("Phone:");
        genderEditLabel = new JLabel("Gender:");
        ageEditLabel = new JLabel("Age:");
        emailEditLabel = new JLabel("Email:");
        addressEditLabel = new JLabel("Address:");
        pointEditLabel = new JLabel("Point:");
        accountLabels = new JLabel[]{usernameEditLabel, passwordEditLabel, nameEditLabel, phoneEditLabel, genderEditLabel, ageEditLabel,
                emailEditLabel, addressEditLabel, pointEditLabel};
        GUI.JLabelSetup(accountLabels);

        //Create input fields
        //JTextField
        usernameEdit = new JTextField(20);
        nameEdit = new JTextField(20);
        phoneEdit = new JTextField(20);
        emailEdit = new JTextField(20);
        addressEdit = new JTextField(20);

        //JPasswordField
        passwordEdit = new JPasswordField(20);

        //JSpinner
        ageEdit = new JSpinner(new SpinnerNumberModel(17, 17,122,1));
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
        components = new JComponent[]{usernameEdit, nameEdit, phoneEdit, emailEdit, addressEdit, passwordEdit, ageEdit, pointEdit, male, female};

        //Create control unit
        JPanel selectionPanel = new JPanel();
        selectionPanel.setBackground(Color.white);
        selectionPanel.add(OKButton);
        selectionPanel.add(cancelButton);

        //Create account panel
        accountPanel = new JPanel(new GridBagLayout());
        GridBagConstraints accConstraints = new GridBagConstraints();

        accountFunctionsPanel = new JPanel(new GridBagLayout());
        accConstraints.insets = new Insets(8,5,8,5);
        accConstraints.weightx = 1;
        accConstraints.weighty = 1;
        accConstraints.gridx = 0;
        accConstraints.ipady = 0;
        accConstraints.gridwidth = 2;
        for(int i = 0; i < accountLabels.length; i++){
            accConstraints.gridy = i + 1;
            accountFunctionsPanel.add(accountLabels[i], accConstraints);
        }

        //Setup fields
        accConstraints.gridx = 2;
        accConstraints.gridy = 1;
        accountFunctionsPanel.add(usernameEdit, accConstraints);

        accConstraints.gridy = 2;
        accountFunctionsPanel.add(passwordEdit, accConstraints);

        accConstraints.gridy = 3;
        accountFunctionsPanel.add(nameEdit, accConstraints);

        accConstraints.gridy = 4;
        accountFunctionsPanel.add(phoneEdit, accConstraints);

        accConstraints.gridy = 5;
        JPanel genderPanel = new JPanel();
        genderPanel.setBackground(Color.white);
        genderPanel.add(male);
        genderPanel.add(female);
        accountFunctionsPanel.add(genderPanel, accConstraints);

        accConstraints.gridy = 6;
        accountFunctionsPanel.add(ageEdit, accConstraints);

        accConstraints.gridy = 7;
        accountFunctionsPanel.add(emailEdit, accConstraints);

        accConstraints.gridy = 8;
        accountFunctionsPanel.add(addressEdit, accConstraints);

        accConstraints.gridy = 9;
        accountFunctionsPanel.add(pointEdit, accConstraints);

        accConstraints.gridy = 10;
        accountFunctionsPanel.add(selectionPanel, accConstraints);

        //Account button panel
        JPanel accountButtonPanel = new JPanel(new GridBagLayout());
        accountButtonPanel.add(changePassword);
        accountButtonPanel.add(editAccount);
        accountButtonPanel.add(deleteAccount);

        //Position elements in account panel
        accConstraints.gridy = 0;
        accountPanel.add(accountButtonPanel, accConstraints);
        accConstraints.gridy = 1;
        accountPanel.add(accountFunctionsPanel, accConstraints);


        /* MAIN */
        //Create panels
        panels = new JPanel[]{carsPanel, carFunctionsPanel, bookingsPanel, bookingFunctionsPanel, accountPanel, accountFunctionsPanel};
        GUI.JPanelSetup(panels);

        //Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(600, 500));
        mainPanel.setBackground(Color.white);
        mainPanel.add(carsPanel);
        mainPanel.add(bookingsPanel);
        mainPanel.add(accountPanel);
        mainPanel.validate();

        //Position main panel in the frame
        constraints.insets = new Insets(5,0,5,5);
        constraints.gridx = 1;
        constraints.weighty = 1;
        constraints.weightx = 0.99;
        constraints.fill = GridBagConstraints.BOTH;
        frame.add(mainPanel, constraints);
    }

    public JFrame getFrame() {
        return frame;
    }

    public static JPasswordField getPasswordEdit() {
        return passwordEdit;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == logout){
                GUI.playSound("ji.wav");
                CarRentalSystem.loginCustomer = null;
                frame.setVisible(false);
                CarRentalSystem.homePage.getFrame().setVisible(true);
            }
            else if (e.getSource() == cars){
                GUI.playSound("ji.wav");
                showCustomerPanel(carsPanel, carFunctionsPanel);
            }
            else if (e.getSource() == booking){
                GUI.playSound("ji.wav");
                showCustomerPanel(bookingsPanel, bookingFunctionsPanel);
            }
            else if (e.getSource() == account){
                GUI.playSound("ji.wav");
                showCustomerPanel(accountPanel, accountFunctionsPanel);
                showCustomerDetails();
            }
            else if (e.getSource() == searchCar){
                GUI.playSound("ji.wav");
                CarFunctions.showSearchCarPanel();
            }
            else if (e.getSource() == allCar){
                GUI.playSound("ji.wav");
                CarFunctions.showAllCarPanel();
            }
            else if (e.getSource() == changePassword){
                GUI.playSound("ji.wav");
                GUI.disableFields(components);
                Customer.checkIdentity();
            }
            else if (e.getSource() == editAccount){
                GUI.playSound("ji.wav");
                passwordEdit.setEditable(false);
                nameEdit.setEditable(true);
                phoneEdit.setEditable(true);
                emailEdit.setEditable(true);
                addressEdit.setEditable(true);
                ageEdit.setEnabled(true);
                male.setEnabled(true);
                female.setEnabled(true);
            }
            else if (e.getSource() == deleteAccount){
                GUI.playSound("ji.wav");
                String input = JOptionPane.showInputDialog("Type \"DELETE\" to confirm the deletion!");
                if (input != null && input.equals("DELETE")){
                    GUI.playSound("DontSayFiveDe.wav");
                    Customer.deleteAccount();
                    JOptionPane.showMessageDialog(frame, "Goodbye Honey~", "Account Deleted", JOptionPane.INFORMATION_MESSAGE);
                    CarRentalSystem.loginCustomer = null;
                    frame.setVisible(false);
                    CarRentalSystem.homePage.getFrame().setVisible(true);
                }
                else {
                    GUI.playSound("ElectricVoice.wav");
                    JOptionPane.showMessageDialog(CarRentalSystem.customerMenu.getFrame(), "Deletion canceled!");
                }
            }
            else if (e.getSource() == OKButton){
                GUI.playSound("ji.wav");
                if (passwordEdit.isEditable()){
                    String password = String.valueOf(passwordEdit.getPassword());
                    if (Customer.changePassword(password)){
                        passwordEdit.setEditable(false);
                    }
                }
                else {
                    String name = nameEdit.getText();
                    String phone = phoneEdit.getText();
                    String email = emailEdit.getText();
                    String address = addressEdit.getText();
                    int age = (int) ageEdit.getValue();
                    String gender;
                    if (male.isSelected()) gender = "male";
                    else gender = "female";

                    if (Customer.editAccountDetails(name, phone, email, address, gender, age)){
                        GUI.disableFields(components);
                    }
                }
            }
            else if (e.getSource() == cancelButton){
                GUI.playSound("ji.wav");
                GUI.disableFields(components);
            }
        } catch (Exception exception){
            GUI.playSound("NormalVoice.wav");
            JOptionPane.showMessageDialog(frame, "Invalid move!");
        }
    }

    private void showCustomerPanel(JPanel bigPanel, JPanel smallPanel){
        for (JPanel i : panels) {
            i.setVisible(false);
        }
        bigPanel.setVisible(true);
        smallPanel.setVisible(true);
    }

    private void showCustomerDetails(){
        AccountFunctions.resetFields(components);
        GUI.disableFields(components);

        usernameEdit.setText(CarRentalSystem.loginCustomer.getUsername());
        nameEdit.setText(CarRentalSystem.loginCustomer.getName());
        phoneEdit.setText(CarRentalSystem.loginCustomer.getPhone());
        emailEdit.setText(CarRentalSystem.loginCustomer.getEmail());
        addressEdit.setText(CarRentalSystem.loginCustomer.getAddress());
        passwordEdit.setText(CarRentalSystem.loginCustomer.getPassword());
        ageEdit.setValue(CarRentalSystem.loginCustomer.getAge());
        pointEdit.setValue(CarRentalSystem.loginCustomer.getPoints());
        if (CarRentalSystem.loginCustomer.getGender().equals("male")){
            male.setSelected(true);
        }
        else {
            female.setSelected(true);
        }
    }
}
