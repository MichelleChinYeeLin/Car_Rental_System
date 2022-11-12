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
    private JButton editAccount, deleteAccount;

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
        searchCar = new JButton("Search");
        allCar = new JButton("All Cars");
        carButtons = new JButton[]{searchCar, allCar};
        searchCar.addActionListener(this);
        allCar.addActionListener(this);
        GUI.subJButtonSetup(carButtons, new Dimension(100, 40));

        //Create car panel
        carsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints carConstraints = new GridBagConstraints();
        carFunctionsPanel = new CarFunctions();

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
        editAccount = new JButton("Edit Account");
        deleteAccount = new JButton("Delete Account");
        accountButtons = new JButton[]{editAccount, deleteAccount};
        editAccount.addActionListener(this);
        deleteAccount.addActionListener(this);
        GUI.subJButtonSetup(accountButtons, new Dimension(140, 40));

        //Create account panel
        accountPanel = new JPanel(new GridBagLayout());
        GridBagConstraints accConstraints = new GridBagConstraints();
        accountFunctionsPanel = new JPanel(); // to change

        //Account button panel
        JPanel accountButtonPanel = new JPanel(new GridBagLayout());
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
        mainPanel.setBackground(Color.white);
        mainPanel.add(carsPanel);
        mainPanel.add(bookingsPanel);
        mainPanel.add(accountPanel);

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
                CarRentalSystem.loginCustomer = null;
                frame.setVisible(false);
                CarRentalSystem.homePage.getFrame().setVisible(true);
            }
            else if (e.getSource() == cars){
                showCustomerPanel(carsPanel, carFunctionsPanel);
            }
            else if (e.getSource() == booking){
                showCustomerPanel(bookingsPanel, bookingFunctionsPanel);
            }
            else if (e.getSource() == account){
                showCustomerPanel(accountPanel, accountFunctionsPanel);
            }
            else if (e.getSource() == searchCar){
                CarFunctions.showSearchCarPanel();
            }
            else if (e.getSource() == allCar){
                CarFunctions.showAllCarPanel();
            }
        } catch (Exception exception){
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
}
