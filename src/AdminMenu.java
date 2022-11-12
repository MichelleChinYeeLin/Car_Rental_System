import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenu implements ActionListener {

    /* MAIN */
    private JFrame frame;
    private JPanel carsPanel, registrationsPanel, accountsPanel, bookingsPanel, reportsPanel;
    private JButton logout, accRegistrations, accounts, bookings, cars, reports;
    private JButton[] buttons, carButtons, accountButtons, bookingButtons, reportButtons;
    private JPanel[] panels;

    /* CAR */
    private CarFunctions carFunctionsPanel;
    private JButton addCar, editCar, deleteCar, searchCar, allCar;

    /* REGISTRATION */
    private JPanel registrationFunctionsPanel;

    /* ACCOUNT */
    private JPanel accountFunctionsPanel;
    private JButton editPassword, addAdmin, searchAccount, allAccount;
    // search -> edit/delete

    /* BOOKING */
    private JPanel bookingFunctionsPanel;
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
        GridBagLayout ButtonLayout = new GridBagLayout();
        JPanel buttonPanel = new JPanel(ButtonLayout);
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
        addCar = new JButton("Add");
        editCar = new JButton("Edit");
        deleteCar = new JButton("Delete");
        searchCar = new JButton("Search");
        allCar = new JButton("All Cars");
        carButtons = new JButton[]{addCar, editCar, deleteCar, searchCar, allCar};
        addCar.addActionListener(this);
        editCar.addActionListener(this);
        deleteCar.addActionListener(this);
        searchCar.addActionListener(this);
        allCar.addActionListener(this);
        GUI.subJButtonSetup(carButtons, new Dimension(100, 40));

        //Create car panel
        carsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints carConstraints = new GridBagConstraints();
        carFunctionsPanel = new CarFunctions();

        //Car button panel
        JPanel carButtonPanel = new JPanel(new GridBagLayout());
        carButtonPanel.add(addCar);
        carButtonPanel.add(editCar);
        carButtonPanel.add(deleteCar);
        carButtonPanel.add(searchCar);
        carButtonPanel.add(allCar);

        //Position elements in car panel
        carConstraints.gridy = 0;
        carsPanel.add(carButtonPanel, carConstraints);
        carConstraints.gridy = 1;
        carsPanel.add(carFunctionsPanel, carConstraints);


        /* REGISTRATION */
        registrationsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints regConstraints = new GridBagConstraints();
        registrationFunctionsPanel = new JPanel(); // to change


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
        bookingFunctionsPanel = new JPanel(); // to change

        //Booking button panel
        JPanel bookingButtonPanel = new JPanel(new GridBagLayout());
        bookingButtonPanel.add(searchBooking);
        bookingButtonPanel.add(allBooking);

        //Position elements in booking panel
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
        reportsPanel.add(bookingFunctionsPanel, repConstraints);


        /* MAIN */
        //Create panels
        panels = new JPanel[]{carsPanel, carFunctionsPanel, registrationsPanel, registrationFunctionsPanel,
                accountsPanel, accountFunctionsPanel, bookingsPanel, bookingFunctionsPanel,
                reportsPanel, reportFunctionsPanel};
        GUI.JPanelSetup(panels);

        //Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.white);
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
                CarRentalSystem.loginAdmin = null;
                frame.setVisible(false);
                CarRentalSystem.homePage.getFrame().setVisible(true);
            }
            else if (e.getSource() == accRegistrations){
                showAdminPanel(registrationsPanel, registrationFunctionsPanel);
            }
            else if (e.getSource() == accounts){
                showAdminPanel(accountsPanel, accountFunctionsPanel);
            }
            else if (e.getSource() == bookings){
                showAdminPanel(bookingsPanel, bookingFunctionsPanel);
            }
            else if (e.getSource() == cars){
                showAdminPanel(carsPanel, carFunctionsPanel);
            }
            else if (e.getSource() == reports){
                showAdminPanel(reportsPanel, reportFunctionsPanel);
            }
            else if (e.getSource() == editPassword){
                AccountFunctions.getUsername1().setText(CarRentalSystem.loginAdmin.getUsername());
                AccountFunctions.showEditPasswordPanel();
            }
            else if (e.getSource() == addAdmin){
                AccountFunctions.showAddAdminPanel();
            }
            else if (e.getSource() == searchAccount){
                AccountFunctions.showSearchAccountPanel();
            }
            else if (e.getSource() == allAccount){
                AccountFunctions.showViewAccountPanel();
            }
            else if (e.getSource() == addCar){
                CarFunctions.showAddCarPanel();
            }
            else if (e.getSource() == editCar){
                CarFunctions.showEditCarPanel();
            }
            else if (e.getSource() == deleteCar){
                CarFunctions.showDeleteCarPanel();
            }
            else if (e.getSource() == searchCar){
                CarFunctions.showSearchCarPanel();
            }
            else if (e.getSource() == allCar){
                CarFunctions.showAllCarPanel();
            }
        } catch (Exception exception){
            System.out.println("HI something wrong");
        }
    }

    private void showCustomerList(){

    }

    private void showAdminPanel(JPanel bigPanel, JPanel smallPanel){
        for (JPanel i : panels) {
            i.setVisible(false);
        }
        bigPanel.setVisible(true);
        smallPanel.setVisible(true);
    }

}
