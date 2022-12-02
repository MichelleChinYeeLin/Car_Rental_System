import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminMenu implements ActionListener {

    /* MAIN */
    private JFrame frame;
    private JPanel mainPanel;
    private JLabel title;
    private JSpinner numberSpinner;
    private JPanel carsPanel, registrationsPanel, accountsPanel, bookingsPanel, reportsPanel;
    private JButton logout, accRegistrations, accounts, bookings, cars, reports;
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
    // search -> edit/delete

    /* BOOKING */
    private BookingFunctions bookingFunctionsPanel;
    private JButton searchBooking, allBooking;
    // search -> confirm/decline/generate receipt

    /* REPORT */
    private JPanel reportFunctionsPanel;
    private JButton genderReport, ageReport, paymentAnalysis, feedbackAnalysis;


    public AdminMenu(){
        frame = new JFrame("Admin Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUI.JFrameSetup(frame);
        frame.setLayout(new GridBagLayout());

        //Create buttons
        accRegistrations = new JButton("Registration");
        accounts = new JButton("Accounts");
        bookings = new JButton("Bookings");
        cars = new JButton("Cars");
        reports = new JButton("Reports");
        logout = new JButton("Logout");
        buttons = new JButton[]{accRegistrations, accounts, bookings, cars, reports, logout};
        accRegistrations.addActionListener(this);
        accounts.addActionListener(this);
        bookings.addActionListener(this);
        cars.addActionListener(this);
        reports.addActionListener(this);
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
        carFunctionsPanel = new CarFunctions();

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
        //GridBagConstraints regConstraints = new GridBagConstraints();
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
        accountFunctionsPanel = new AccountFunctions();

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
        genderReport = new JButton("Gender");
        ageReport = new JButton("Age");
        paymentAnalysis = new JButton("Payment");
        feedbackAnalysis = new JButton("Feedback");
        reportButtons = new JButton[]{genderReport, ageReport, paymentAnalysis, feedbackAnalysis};
        genderReport.addActionListener(this);
        ageReport.addActionListener(this);
        paymentAnalysis.addActionListener(this);
        feedbackAnalysis.addActionListener(this);
        GUI.subJButtonSetup(reportButtons, new Dimension(110, 40));

        //Create report panel
        reportsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints repConstraints = new GridBagConstraints();
        reportFunctionsPanel = new JPanel(); // to change

        //Report button panel
        JPanel reportButtonPanel = new JPanel(new GridBagLayout());
        reportButtonPanel.add(genderReport);
        reportButtonPanel.add(ageReport);
        reportButtonPanel.add(paymentAnalysis);
        reportButtonPanel.add(feedbackAnalysis);

        //Position elements in booking panel
        repConstraints.gridy = 0;
        reportsPanel.add(reportButtonPanel, repConstraints);
        repConstraints.gridy = 1;
        reportsPanel.add(reportFunctionsPanel, repConstraints);


        /* MAIN */
        //Create panels
        panels = new JPanel[]{carsPanel, carFunctionsPanel, registrationsPanel, registrationFunctionsPanel,
                accountsPanel, accountFunctionsPanel, bookingsPanel, bookingFunctionsPanel,
                reportsPanel, reportFunctionsPanel};
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
                CarRentalSystem.loginAdmin = null;
                frame.setVisible(false);
                CarRentalSystem.homePage.getFrame().setVisible(true);
                CarRentalSystem.currentFrame = CarRentalSystem.homePage.getFrame();
            }
            else if (e.getSource() == accRegistrations){
                GUI.playSound("ji.wav");
                showAdminPanel(registrationsPanel, registrationFunctionsPanel);
                showAccRegistration();
            }
            else if (e.getSource() == accounts){
                GUI.playSound("ji.wav");
                showAdminPanel(accountsPanel, accountFunctionsPanel);
            }
            else if (e.getSource() == bookings){
                GUI.playSound("ji.wav");
                showAdminPanel(bookingsPanel, bookingFunctionsPanel);
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
                showAdminPanel(carsPanel, carFunctionsPanel);
            }
            else if (e.getSource() == reports){
                GUI.playSound("ji.wav");
                showAdminPanel(reportsPanel, reportFunctionsPanel);
            }
            else if (e.getSource() == editPassword){
                GUI.playSound("ji.wav");
                AccountFunctions.getUsername1().setText(CarRentalSystem.loginAdmin.getUsername());
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
                CarFunctions.showSearchCarPanel();
            }
            else if (e.getSource() == allCar){
                GUI.playSound("ji.wav");
                CarFunctions.showAllCarPanel();
            }
            else if (e.getSource() == approveButton){
                approveRegistration();
            }
            else if (e.getSource() == denyButton){
                denyRegistration();
            }
        } catch (Exception exception){
            GUI.playSound("niganma.wav");
            System.out.println("HI something wrong");
        }
    }

    private void showAdminPanel(JPanel bigPanel, JPanel smallPanel){
        for (JPanel i : panels) {
            i.setVisible(false);
        }
        bigPanel.setVisible(true);
        smallPanel.setVisible(true);
    }

    private void approveRegistration(){

        int index = (int)numberSpinner.getValue();
        boolean isSuccess = Customer.approveRegistration(index - 1);

        if(isSuccess){
            showAccRegistration();
        }
        else{
            JOptionPane.showMessageDialog(this.getFrame(), "Unexpected error occurred! Please try again later.", "Registration Approval Failed", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void denyRegistration(){

        int index = (int)numberSpinner.getValue();
        boolean isSuccess = Customer.denyRegistration(index - 1);

        if(isSuccess){
            showAccRegistration();
        }
        else{
            JOptionPane.showMessageDialog(this.getFrame(), "Unexpected error occurred! Please try again later.", "Registration Deny Failed", JOptionPane.WARNING_MESSAGE);
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
}
