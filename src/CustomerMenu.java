import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerMenu implements ActionListener {

    /* MAIN */
    private JFrame frame;
    private JPanel carsPanel, bookingsPanel, accountPanel, feedbackPanel;
    private JButton logout, cars, booking, account, feedback;
    private JButton[] buttons, carButtons, bookingButtons, accountButtons;
    private JPanel[] panels;

    /* CAR */
    private CarFunctions carFunctionsPanel;
    private JButton searchCar, allCar;
    // search -> book?

    /* BOOKING */
    private BookingFunctions bookingFunctionsPanel;
//    private CarFunctions forSearchOnly;
    private JButton ongoingBooking, completedBooking, viewBooking;
    // book -> search car -> book
    // view -> (return car -> payment)/receipt/feedback
    // cant quit during payment!!!!!!!!!!!!!!!!!!!!!!!!!!

    /* ACCOUNT */
    private JPanel accountFunctionsPanel; // show profile
    private JButton editAccount, deleteAccount;

    /* FEEDBACK */
    private JPanel feedbackFunctionsPanel;
    private JSlider feedbackRating;
    private JTextArea feedbackComment;
    private JButton submitFeedback;

    public CustomerMenu(){
        frame = new JFrame("Customer Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUI.JFrameSetup(frame);
        frame.setLayout(new GridBagLayout());

        //Create buttons
        cars = new JButton("Cars");
        booking = new JButton("Bookings");
        account = new JButton("Account");
        feedback = new JButton("Feedback");
        logout = new JButton("Logout");
        buttons = new JButton[]{cars, booking, account, feedback, logout};
        cars.addActionListener(this);
        booking.addActionListener(this);
        account.addActionListener(this);
        feedback.addActionListener(this);
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
        ongoingBooking = new JButton("Ongoing");
        completedBooking = new JButton("Completed");
        viewBooking = new JButton("All Bookings");
        bookingButtons = new JButton[]{ongoingBooking, completedBooking, viewBooking};
        ongoingBooking.addActionListener(this);
        completedBooking.addActionListener(this);
        viewBooking.addActionListener(this);
        GUI.subJButtonSetup(bookingButtons, new Dimension(140, 40));

        //Create booking panel
        bookingsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints bkgConstraints = new GridBagConstraints();
        bookingFunctionsPanel = new BookingFunctions(false);

        //Booking button panel
        JPanel bookingButtonPanel = new JPanel(new GridBagLayout());
        bookingButtonPanel.add(ongoingBooking);
        bookingButtonPanel.add(completedBooking);
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


        /* FEEDBACK */
        feedbackPanel = new JPanel();
        feedbackFunctionsPanel = new JPanel(new GridBagLayout());

        JLabel ratingLabel = new JLabel("Rating:");
        JLabel commentLabel = new JLabel("Comment:");
        GUI.JLabelSetup(new JLabel[]{ratingLabel, commentLabel});

        submitFeedback = new JButton("SUBMIT");
        submitFeedback.addActionListener(this);
        GUI.JButtonSetup(submitFeedback);

        feedbackRating = new JSlider(JSlider.HORIZONTAL, 1, 10, 10);
        feedbackRating.setMajorTickSpacing(1);
        feedbackRating.setFont(GUI.getDefaultFont());
        feedbackRating.setPaintTicks(true);
        feedbackRating.setPaintLabels(true);
        feedbackComment = new JTextArea();
        feedbackComment.setPreferredSize(new Dimension(300, 200));
        Border border = BorderFactory.createLineBorder(Color.black);
        feedbackComment.setBorder(border);

        GridBagConstraints feedbackConstraints = new GridBagConstraints();
        feedbackConstraints.fill = GridBagConstraints.HORIZONTAL;
        feedbackConstraints.gridx = 0;
        feedbackConstraints.gridy = 0;
        feedbackConstraints.insets = new Insets(10,10,10,10);
        feedbackFunctionsPanel.add(ratingLabel, feedbackConstraints);

        feedbackConstraints.gridy = 1;
        feedbackFunctionsPanel.add(commentLabel, feedbackConstraints);

        feedbackConstraints.gridy = 0;
        feedbackConstraints.gridx = 1;
        feedbackFunctionsPanel.add(feedbackRating, feedbackConstraints);

        feedbackConstraints.gridy = 2;
        feedbackConstraints.gridx = 0;
        feedbackConstraints.gridwidth = 2;
        feedbackConstraints.insets = new Insets(0,0,0,0);
        feedbackFunctionsPanel.add(feedbackComment, feedbackConstraints);

        feedbackConstraints.gridy = 3;
        feedbackConstraints.insets = new Insets(10,10,10,10);
        feedbackFunctionsPanel.add(submitFeedback, feedbackConstraints);

        feedbackPanel.add(feedbackFunctionsPanel);

        /* MAIN */
        //Create panels
        panels = new JPanel[]{carsPanel, carFunctionsPanel, bookingsPanel, bookingFunctionsPanel, accountPanel, accountFunctionsPanel, feedbackPanel, feedbackFunctionsPanel};
        GUI.JPanelSetup(panels);

        //Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.white);
        mainPanel.add(carsPanel);
        mainPanel.add(bookingsPanel);
        mainPanel.add(accountPanel);
        mainPanel.add(feedbackPanel);

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

                FileIO.recordList.add(0, dateFormat.format(date) + " " + CarRentalSystem.loginCustomer.getUsername() + " logout.");

                CarRentalSystem.loginCustomer = null;
                frame.setVisible(false);
                CarRentalSystem.homePage.getFrame().setVisible(true);
                CarRentalSystem.currentFrame = CarRentalSystem.homePage.getFrame();
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
            }
            else if (e.getSource() == feedback){
                GUI.playSound("ji.wav");
                showCustomerPanel(feedbackPanel, feedbackFunctionsPanel);
            }
            else if (e.getSource() == searchCar){
                GUI.playSound("ji.wav");
                CarFunctions.showSearchCarPanel();
            }
            else if (e.getSource() == allCar){
                GUI.playSound("ji.wav");
                CarFunctions.showAllCarPanel();
            }
            else if (e.getSource() == ongoingBooking){
                GUI.playSound("ji.wav");
                BookingFunctions.showOngoingBookingCustomerPanel();
                bookingFunctionsPanel.viewOngoingBookingCustomer();
            }
            else if (e.getSource() == completedBooking){
                GUI.playSound("ji.wav");
                BookingFunctions.showCompletedBookingCustomerPanel();
                bookingFunctionsPanel.viewCompletedBookingCustomer();
            }
            else if (e.getSource() == viewBooking){
                GUI.playSound("ji.wav");
                BookingFunctions.showAllBookingCustomerPanel();
            }
            else if (e.getSource() == submitFeedback){
                GUI.playSound("ji.wav");
                submitCustomerFeedback();
            }
        } catch (Exception exception){
            GUI.playSound("niganma.wav");
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

    private void submitCustomerFeedback(){
        int rating = feedbackRating.getValue();
        String comment = feedbackComment.getText();

        Feedback feedback = new Feedback(CarRentalSystem.loginCustomer, rating, comment);
        FileIO.feedbackList.add(feedback);

        JOptionPane.showMessageDialog(frame, "Feedback submitted successfully!", "Feedback Submitted", JOptionPane.INFORMATION_MESSAGE);
    }
}
