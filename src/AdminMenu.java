import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdminMenu implements MainPage {

    /* MAIN */
    private JFrame frame;
    private JPanel mainPanel;
    private JSpinner numberSpinner;
    private JPanel carsPanel, registrationsPanel, accountsPanel, bookingsPanel, reportsPanel, recordsPanel;
    private JButton logout, accRegistrations, accounts, bookings, cars, reports, records;
    private JButton approveButton, denyButton;
    private JButton[] buttons, carButtons, accountButtons, bookingButtons, reportButtons;
    private JPanel[] panels;

    /* CAR */
    private CarFunctions carFunctionsPanel;
    private JButton addCar, searchCar, allCar;

    /* REGISTRATION */
    private JPanel registrationFunctionsPanel;

    /* ACCOUNT */
    private JPanel accountFunctionsPanel;
    private JButton editPassword, addAdmin, searchAccount, allAccount;

    /* BOOKING */
    private BookingFunctions bookingFunctionsPanel;
    private JButton searchBooking, allBooking;

    /* REPORT */
    private JPanel reportFunctionsPanel;
    private JButton customerReport, carReport, paymentReport, feedbackReport;

    /* RECORD */
    private JPanel recordFunctionsPanel;

    public AdminMenu(){
        frame = new JFrame("Admin Menu");
        GUI.JFrameSetup(frame);
        frame.setLayout(new GridBagLayout());

        //Create buttons
        accRegistrations = new JButton("Registration");
        accounts = new JButton("Accounts");
        bookings = new JButton("Bookings");
        cars = new JButton("Cars");
        reports = new JButton("Reports");
        records = new JButton("Records");
        logout = new JButton("Logout");
        buttons = new JButton[]{accRegistrations, accounts, bookings, cars, reports, records, logout};
        accRegistrations.addActionListener(this);
        accounts.addActionListener(this);
        bookings.addActionListener(this);
        cars.addActionListener(this);
        reports.addActionListener(this);
        records.addActionListener(this);
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
        addCar = new JButton("Add Car");
        searchCar = new JButton("Search Car");
        allCar = new JButton("All Cars");
        carButtons = new JButton[]{addCar, searchCar, allCar};
        addCar.addActionListener(this);
        searchCar.addActionListener(this);
        allCar.addActionListener(this);
        GUI.subJButtonSetup(carButtons, new Dimension(110, 40));

        //Create car panel
        carsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints carConstraints = new GridBagConstraints();
        carFunctionsPanel = new CarFunctions(true);

        //Car button panel
        JPanel carButtonPanel = new JPanel(new GridBagLayout());
        carButtonPanel.add(addCar);
        carButtonPanel.add(searchCar);
        carButtonPanel.add(allCar);

        //Position elements in car panel
        carConstraints.gridy = 0;
        carsPanel.add(carButtonPanel, carConstraints);
        carConstraints.gridy = 1;
        carsPanel.add(carFunctionsPanel, carConstraints);


        /* REGISTRATION */
        registrationsPanel = new JPanel(new GridBagLayout());
        registrationFunctionsPanel = new JPanel(new GridBagLayout());
        registrationsPanel.add(registrationFunctionsPanel);


        /* ACCOUNT */
        //Create buttons
        editPassword = new JButton("Change Password");
        addAdmin = new JButton("Add Admin");
        searchAccount = new JButton("Search");
        allAccount = new JButton("All Accounts");
        accountButtons = new JButton[]{editPassword, addAdmin, searchAccount, allAccount};
        editPassword.addActionListener(this);
        addAdmin.addActionListener(this);
        searchAccount.addActionListener(this);
        allAccount.addActionListener(this);
        GUI.subJButtonSetup(accountButtons, new Dimension(120, 40));
        editPassword.setPreferredSize(new Dimension(160,40));

        //Create account panel
        accountsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints accConstraints = new GridBagConstraints();
        accountFunctionsPanel = new AccountFunctions(true);

        //Account button panel
        JPanel accountButtonPanel = new JPanel(new GridBagLayout());
        accountButtonPanel.add(editPassword);
        accountButtonPanel.add(addAdmin);
        accountButtonPanel.add(searchAccount);
        accountButtonPanel.add(allAccount);

        //Position elements in account panel
        accConstraints.gridy = 0;
        accountsPanel.add(accountButtonPanel, accConstraints);
        accConstraints.gridy = 1;
        accountsPanel.add(accountFunctionsPanel, accConstraints);


        /* BOOKING */
        //Create buttons
        searchBooking = new JButton("Search");
        allBooking = new JButton("All Bookings");
        bookingButtons = new JButton[]{searchBooking, allBooking};
        searchBooking.addActionListener(this);
        allBooking.addActionListener(this);
        GUI.subJButtonSetup(bookingButtons, new Dimension(120, 40));

        //Create booking panel
        bookingsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints bkgConstraints = new GridBagConstraints();
        bookingFunctionsPanel = new BookingFunctions(true);

        //Booking button panel
        JPanel bookingButtonPanel = new JPanel(new GridBagLayout());
        bookingButtonPanel.add(searchBooking);
        bookingButtonPanel.add(allBooking);

        //Position elements in booking panel
        bkgConstraints.gridx = 0;
        bkgConstraints.gridy = 0;
        bookingsPanel.add(bookingButtonPanel, bkgConstraints);
        bkgConstraints.gridy = 1;
        bookingsPanel.add(bookingFunctionsPanel, bkgConstraints);


        /* REPORT */
        //Create buttons
        customerReport = new JButton("Customer");
        carReport = new JButton("Car");
        paymentReport = new JButton("Payment");
        feedbackReport = new JButton("Feedback");
        reportButtons = new JButton[]{customerReport, carReport, paymentReport, feedbackReport};
        customerReport.addActionListener(this);
        carReport.addActionListener(this);
        paymentReport.addActionListener(this);
        feedbackReport.addActionListener(this);
        GUI.subJButtonSetup(reportButtons, new Dimension(110, 40));

        //Create report panel
        reportsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints repConstraints = new GridBagConstraints();
        reportFunctionsPanel = new ReportFunctions(); // to change

        //Report button panel
        JPanel reportButtonPanel = new JPanel(new GridBagLayout());
        reportButtonPanel.add(customerReport);
        reportButtonPanel.add(carReport);
        reportButtonPanel.add(paymentReport);
        reportButtonPanel.add(feedbackReport);

        //Position elements in booking panel
        repConstraints.gridy = 0;
        reportsPanel.add(reportButtonPanel, repConstraints);
        repConstraints.gridy = 1;
        reportsPanel.add(reportFunctionsPanel, repConstraints);


        /* RECORD */
        recordsPanel = new JPanel(new GridBagLayout());
        recordFunctionsPanel = new JPanel(new GridBagLayout());
        recordFunctionsPanel.setPreferredSize(new Dimension(600,500));
        recordsPanel.add(recordFunctionsPanel);


        /* MAIN */
        //Create panels
        panels = new JPanel[]{carsPanel, carFunctionsPanel, registrationsPanel, registrationFunctionsPanel,
                accountsPanel, accountFunctionsPanel, bookingsPanel, bookingFunctionsPanel,
                reportsPanel, reportFunctionsPanel, recordsPanel, recordFunctionsPanel};
        GUI.JPanelSetup(panels);

        //Create main panel
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(600, 500));
        mainPanel.setBackground(Color.white);
        mainPanel.add(registrationsPanel);
        mainPanel.add(carsPanel);
        mainPanel.add(accountsPanel);
        mainPanel.add(bookingsPanel);
        mainPanel.add(reportsPanel);
        mainPanel.add(recordsPanel);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == logout){
                GUI.playSound("ji.wav");

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date date = new Date();

                FileIO.recordList.add(0, dateFormat.format(date) + " " + CarRentalSystem.loginAdmin.getUsername() + " logout.");

                CarRentalSystem.loginAdmin = null;
                frame.setVisible(false);
                CarRentalSystem.homePage.getFrame().setVisible(true);
                CarRentalSystem.currentFrame = CarRentalSystem.homePage.getFrame();
            }
            else if (e.getSource() == accRegistrations){
                GUI.playSound("ji.wav");
                showPanel(registrationsPanel, registrationFunctionsPanel);
                showAccRegistration();
            }
            else if (e.getSource() == accounts){
                GUI.playSound("ji.wav");
                showPanel(accountsPanel, accountFunctionsPanel);
            }
            else if (e.getSource() == bookings){
                GUI.playSound("ji.wav");
                showPanel(bookingsPanel, bookingFunctionsPanel);
            }
            else if (e.getSource() == searchBooking){
                GUI.playSound("ji.wav");
                BookingFunctions.showSearchBookingPanel();
            }
            else if (e.getSource() == allBooking){
                GUI.playSound("ji.wav");
                BookingFunctions.showAllBookingPanel();
            }
            else if (e.getSource() == cars){
                GUI.playSound("ji.wav");
                showPanel(carsPanel, carFunctionsPanel);
            }
            else if (e.getSource() == reports){
                GUI.playSound("ji.wav");
                showPanel(reportsPanel, reportFunctionsPanel);
            }
            else if (e.getSource() == editPassword){
                GUI.playSound("ji.wav");
                AccountFunctions.getAdminUsername1().setText(CarRentalSystem.loginAdmin.getUsername());
                AccountFunctions.showEditPasswordPanel();
            }
            else if (e.getSource() == addAdmin){
                GUI.playSound("ji.wav");
                AccountFunctions.showAddAdminPanel();
            }
            else if (e.getSource() == searchAccount){
                GUI.playSound("ji.wav");
                AccountFunctions.showSearchAccountPanel();
            }
            else if (e.getSource() == allAccount){
                GUI.playSound("ji.wav");
                AccountFunctions.showViewAccountPanel();
            }
            else if (e.getSource() == addCar){
                GUI.playSound("ji.wav");
                CarFunctions.showAddCarPanel();
            }
            else if (e.getSource() == searchCar){
                GUI.playSound("ji.wav");
                CarFunctions.showAdminSearchCarPanel();
            }
            else if (e.getSource() == allCar){
                GUI.playSound("ji.wav");
                CarFunctions.showAllCarPanel(true);
            }
            else if (e.getSource() == approveButton){
                approveRegistration();
            }
            else if (e.getSource() == denyButton){
                denyRegistration();
            }
            else if (e.getSource() == records){
                GUI.playSound("ji.wav");
                showRecordsPanel();
                showPanel(recordsPanel, recordFunctionsPanel);
            }
            else if (e.getSource() == customerReport){
                GUI.playSound("ji.wav");
                ReportFunctions.showUserPanel();
            }
            else if (e.getSource() == carReport){
                GUI.playSound("ji.wav");
                ReportFunctions.showCarPanel();
            }
            else if (e.getSource() == paymentReport){
                GUI.playSound("ji.wav");
                ReportFunctions.showPaymentPanel();
            }
            else if (e.getSource() == feedbackReport){
                GUI.playSound("ji.wav");
                ReportFunctions.showFeedbackPanel();
            }
        } catch (Exception exception){
            GUI.playSound("NormalVoice.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Unexpected error occurred! Please try again later.", "Registration Approval Failed", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void showPanel(JPanel bigPanel, JPanel smallPanel){
        for (JPanel i : panels) {
            i.setVisible(false);
        }
        bigPanel.setVisible(true);
        smallPanel.setVisible(true);
    }

    private void approveRegistration(){

        int index = (int) numberSpinner.getValue();
        boolean isSuccess = Customer.approveRegistration(index - 1);

        if(isSuccess){
            GUI.playSound("ji.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Account registration has been approved!");
            showAccRegistration();
        }
        else{
            GUI.playSound("NormalVoice.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Unexpected error occurred! Please try again later.", "Registration Approval Failed", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void denyRegistration(){

        int index = (int) numberSpinner.getValue();
        boolean isSuccess = Customer.denyRegistration(index - 1);

        if(isSuccess){
            GUI.playSound("ji.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Account registration has been denied!");
            showAccRegistration();
        }
        else{
            GUI.playSound("ElectricVoice.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Unexpected error occurred! Please try again later.", "Registration Deny Failed", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void showAccRegistration(){
        registrationFunctionsPanel.removeAll();
        ArrayList<Customer> accRegistrationList = FileIO.getRegistrationList();

        if(accRegistrationList.size() == 0){
            JLabel emptyTableLabel = new JLabel("No pending registration requests!");
            GUI.JLabelSetup(emptyTableLabel);

            registrationFunctionsPanel.add(emptyTableLabel);
        }
        else{
            String[] tableColumn = {"No.", "Username", "Name", "Password", "Age", "Gender", "Phone", "Email", "Address"};
            Object[][] tempTable = new Object[accRegistrationList.size()][9];
            int i = 0;
            for (Customer nextCustomer : accRegistrationList){
                tempTable[i][0] = i + 1;
                tempTable[i][1] = nextCustomer.getUsername();
                tempTable[i][2] = nextCustomer.getName();
                tempTable[i][3] = nextCustomer.getPassword();
                tempTable[i][4] = nextCustomer.getAge();
                tempTable[i][5] = nextCustomer.getGender();
                tempTable[i][6] = nextCustomer.getPhone();
                tempTable[i][7] = nextCustomer.getEmail();
                tempTable[i][8] = nextCustomer.getAddress();
                i++;
            }

            JTable table = new JTable(tempTable, tableColumn);
            table.setVisible(true);
            JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            scrollPane.setPreferredSize(new Dimension(mainPanel.getWidth() - 5, mainPanel.getHeight() - 50));

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
            numberSpinner = new JSpinner(new SpinnerNumberModel(0, 0, accRegistrationList.size(), 1));
            bottomPanel.add(numberSpinner, bottomConstraints);

            bottomConstraints.gridx = 3;
            approveButton = new JButton("Approve");
            approveButton.addActionListener(this);
            bottomPanel.add(approveButton, bottomConstraints);

            bottomConstraints.gridx = 4;
            denyButton = new JButton("Deny");
            denyButton.addActionListener(this);
            bottomPanel.add(denyButton, bottomConstraints);

            JButton[] registrationButtons = new JButton[]{approveButton, denyButton};
            GUI.subJButtonSetup(registrationButtons, new Dimension(100,30));

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.weighty = 0.8;
            registrationFunctionsPanel.add(scrollPane, constraints);

            constraints.gridy = 1;
            constraints.weighty = 0.2;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            registrationFunctionsPanel.add(bottomPanel, constraints);
        }

        registrationFunctionsPanel.updateUI();
    }

    private void showRecordsPanel(){
        recordFunctionsPanel.removeAll();
        JLabel recordLabel = new JLabel();
        String labelText = "<html><body>";

        for (String text : FileIO.recordList){
            labelText += text + "<br/>";
        }

        labelText += "</body></html>";
        recordLabel.setText(labelText);
        GUI.JLabelSetup(recordLabel);
        recordLabel.setHorizontalAlignment(JLabel.LEFT);

        JPanel labelPanel = new JPanel(new GridBagLayout());
        labelPanel.setBackground(Color.white);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;

        labelPanel.add(recordLabel, constraints);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(550, 450));
        scrollPane.setViewportView(labelPanel);

        recordFunctionsPanel.add(scrollPane);
    }
}
