import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookingFunctions extends JPanel implements ActionListener {

    private static boolean isAdmin;
    private String receiptText;

    //Admin Booking
    private static JPanel searchBookingPanel, searchBookingAttributesPanel, searchBookingResultsPanel, editBookingPanel, editBookingAttributesPanel, viewBookingPanel, adminReceiptPanel;
    private JPanel startDatePanel, endDatePanel, totalPricePanel;
    private JLabel carNumberPlateSearchLabel, customerNameSearchLabel, totalPriceSearchLabel, statusSearchLabel, startDateSearchLabel, endDateSearchLabel, penaltyTypeSearchLabel, totalPriceIndicator;
    private JLabel carNumberPlateEditLabel, customerNameEditLabel, totalPriceEditLabel, outstandingPaymentEditLabel, statusEditLabel, startDateEditLabel, endDateEditLabel, penaltyTypeEditLabel;
    private JTextField carNumberPlateSearch, customerNameSearch, carNumberPlateEdit, customerNameEdit, totalPriceEdit, outstandingPaymentEdit;
    private JSlider totalPriceSlider;
    private JSpinner startDateDaySearch, endDateDaySearch, numberSpinnerEdit, numberSpinnerReceipt, startDateDayEdit, endDateDayEdit;
    private JComboBox<String> startDateYearSearch, endDateYearSearch, startDateYearEdit, endDateYearEdit;
    private JComboBox<Booking.Status> statusBoxSearch, statusBoxEdit;
    private JComboBox<Booking.Month> startDateMonthSearch, endDateMonthSearch, startDateMonthEdit, endDateMonthEdit;
    private JComboBox<Booking.PenaltyType> penaltyTypeSearch, penaltyTypeEdit;
    private JButton searchButton, editAdminButton, deleteAdminButton, OKAdminButton, backToSearchButton, receiptAdminButton;
    private JScrollPane searchTableScroll;
    private static JPanel[] adminPanels, customerPanels;
    private static JTable allBookingTable;

    //Customer Booking
    private static JPanel ongoingBookingPanel, completedBookingPanel, customerViewBookingPanel, customerEditBookingPanel, customerEditBookingAttributesPanel, customerReceiptPanel, customerBookingPaymentPanel;
    private static JTable ongoingBookingTable, completedBookingTable, customerAllBookingTable;
    private JTextField cardNumber, cvv;
    private JButton editCustomerButton, deleteCustomerButton, OKCustomerButton, backCustomerButton, receiptCustomerButton, printButton, payButton, confirmPaymentButton, cancelPaymentButton;

    private ArrayList<Booking> currentBookingList;
    public BookingFunctions(boolean isAdmin){

        this.isAdmin = isAdmin;

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        Booking.Status[] statusType = Booking.Status.values();
        Booking.Month[] month = Booking.Month.values();
        String[] year = {"ANY", String.valueOf(gregorianCalendar.get(Calendar.YEAR)), String.valueOf(gregorianCalendar.get(Calendar.YEAR) + 1)};
        Booking.PenaltyType[] penaltyType = Booking.PenaltyType.values();

        if (isAdmin){
            //Search Booking Panel
            //Create panels
            searchBookingPanel = new JPanel(new GridBagLayout());
            searchBookingAttributesPanel = new JPanel(new GridBagLayout());
            searchBookingResultsPanel = new JPanel();
            searchBookingResultsPanel.setPreferredSize(new Dimension(500, 300));
            searchBookingResultsPanel.setBackground(Color.white);

            startDatePanel = new JPanel();
            endDatePanel = new JPanel();
            totalPricePanel = new JPanel();
            startDatePanel.setBackground(Color.white);
            endDatePanel.setBackground(Color.white);
            totalPricePanel.setBackground(Color.white);

            //Create labels
            carNumberPlateSearchLabel = new JLabel("No. Plate:");
            customerNameSearchLabel = new JLabel("Customer:");
            totalPriceSearchLabel = new JLabel("Price:");
            statusSearchLabel = new JLabel("Status:");
            startDateSearchLabel = new JLabel("Start Date:");
            endDateSearchLabel = new JLabel("End Date:");
            totalPriceIndicator = new JLabel();
            penaltyTypeSearchLabel = new JLabel("Penalty:");
            JLabel labels[] = {carNumberPlateSearchLabel, customerNameSearchLabel, totalPriceSearchLabel, statusSearchLabel, startDateSearchLabel, endDateSearchLabel, totalPriceIndicator, penaltyTypeSearchLabel};
            GUI.JLabelSetup(labels);

            //Create input fields
            //JTextField
            carNumberPlateSearch = new JTextField(5);
            customerNameSearch = new JTextField(10);

            //JSlider
            int maxTotalPriceInInt = Booking.calcMaxTotalPrice();
            totalPriceSlider = new JSlider(JSlider.HORIZONTAL, 10, maxTotalPriceInInt, maxTotalPriceInInt);
            GUI.JSliderSetup(totalPriceSlider, false);
            totalPriceSlider.setPreferredSize(new Dimension(150, 50));

            totalPriceIndicator.setText(String.valueOf(maxTotalPriceInInt));

            totalPriceSlider.addChangeListener(changeEvent -> totalPriceIndicator.setText(String.valueOf(totalPriceSlider.getValue())));

            totalPricePanel.add(totalPriceSlider);
            totalPricePanel.add(totalPriceIndicator);

            startDateDaySearch = new JSpinner(new SpinnerNumberModel(0,0,31,1));
            endDateDaySearch = new JSpinner(new SpinnerNumberModel(0,0,31,1));

            statusBoxSearch = new JComboBox<>(statusType);
            statusBoxSearch.setFont(GUI.getDefaultFont());

            startDateMonthSearch = new JComboBox<>(month);
            endDateMonthSearch = new JComboBox<>(month);
            startDateMonthSearch.setFont(GUI.getDefaultFont());
            endDateMonthSearch.setFont(GUI.getDefaultFont());

            startDateYearSearch = new JComboBox<>(year);
            endDateYearSearch = new JComboBox<>(year);
            startDateYearSearch.setFont(GUI.getDefaultFont());
            endDateYearSearch.setFont(GUI.getDefaultFont());

            penaltyTypeSearch = new JComboBox<>(penaltyType);
            penaltyTypeSearch.setFont(GUI.getDefaultFont());

            startDatePanel.add(startDateDaySearch);
            startDatePanel.add(startDateMonthSearch);
            startDatePanel.add(startDateYearSearch);
            endDatePanel.add(endDateDaySearch);
            endDatePanel.add(endDateMonthSearch);
            endDatePanel.add(endDateYearSearch);

            searchButton = new JButton("SEARCH");
            GUI.JButtonSetup(searchButton);
            searchButton.addActionListener(this);

            GridBagConstraints searchAttributesConstraints = new GridBagConstraints();
            searchAttributesConstraints.fill = GridBagConstraints.HORIZONTAL;
            searchAttributesConstraints.insets = new Insets(2,2,2,2);
            searchAttributesConstraints.weightx = 0.1;
            searchAttributesConstraints.gridy = 0;

            searchAttributesConstraints.gridx = 0;
            searchAttributesConstraints.gridwidth = 1;
            searchBookingAttributesPanel.add(customerNameSearchLabel, searchAttributesConstraints);

            searchAttributesConstraints.gridx = 1;
            searchBookingAttributesPanel.add(customerNameSearch, searchAttributesConstraints);

            searchAttributesConstraints.gridx = 2;
            searchBookingAttributesPanel.add(carNumberPlateSearchLabel, searchAttributesConstraints);

            searchAttributesConstraints.gridx = 3;
            searchBookingAttributesPanel.add(carNumberPlateSearch, searchAttributesConstraints);

            searchAttributesConstraints.gridx = 4;
            searchBookingAttributesPanel.add(statusSearchLabel, searchAttributesConstraints);

            searchAttributesConstraints.gridx = 5;
            searchBookingAttributesPanel.add(statusBoxSearch, searchAttributesConstraints);

            searchAttributesConstraints.gridy = 1;
            searchAttributesConstraints.gridx = 0;
            searchBookingAttributesPanel.add(startDateSearchLabel, searchAttributesConstraints);

            searchAttributesConstraints.gridx = 1;
            searchAttributesConstraints.gridwidth = 3;
            searchBookingAttributesPanel.add(startDatePanel, searchAttributesConstraints);

            searchAttributesConstraints.gridx = 4;
            searchAttributesConstraints.gridwidth = 1;
            searchBookingAttributesPanel.add(totalPriceSearchLabel, searchAttributesConstraints);

            searchAttributesConstraints.gridx = 5;
            searchAttributesConstraints.gridwidth = 2;
            searchBookingAttributesPanel.add(totalPricePanel, searchAttributesConstraints);

            searchAttributesConstraints.gridy = 2;
            searchAttributesConstraints.gridx = 0;
            searchAttributesConstraints.gridwidth = 1;
            searchBookingAttributesPanel.add(endDateSearchLabel, searchAttributesConstraints);

            searchAttributesConstraints.gridx = 1;
            searchAttributesConstraints.gridwidth = 3;
            searchBookingAttributesPanel.add(endDatePanel, searchAttributesConstraints);

            searchAttributesConstraints.gridy = 3;
            searchAttributesConstraints.gridx = 0;
            searchAttributesConstraints.gridwidth = 1;
            searchBookingAttributesPanel.add(penaltyTypeSearchLabel, searchAttributesConstraints);

            searchAttributesConstraints.gridx = 1;
            searchAttributesConstraints.gridwidth =3;
            searchBookingAttributesPanel.add(penaltyTypeSearch, searchAttributesConstraints);

            searchAttributesConstraints.gridx = 5;
            searchAttributesConstraints.gridwidth = 1;
            searchBookingAttributesPanel.add(searchButton, searchAttributesConstraints);

            searchBookingAttributesPanel.setBackground(Color.white);

            GridBagConstraints searchConstraints = new GridBagConstraints();
            searchConstraints.fill = GridBagConstraints.BOTH;
            searchConstraints.gridx = 0;
            searchConstraints.gridy = 0;
            searchConstraints.weightx = 1;
            searchConstraints.insets = new Insets(10,10,10,10);
            searchBookingPanel.add(searchBookingAttributesPanel, searchConstraints);

            searchConstraints.gridx = 0;
            searchConstraints.gridy = 1;
            searchBookingPanel.add(searchBookingResultsPanel, searchConstraints);

            //Edit Booking Panel
            editBookingPanel = new JPanel(new GridBagLayout());
            editBookingAttributesPanel = new JPanel(new GridBagLayout());
            editBookingAttributesPanel.setBackground(Color.white);
            carNumberPlateEditLabel = new JLabel("Car Number Plate:");
            customerNameEditLabel = new JLabel("Customer Username:");
            totalPriceEditLabel = new JLabel("Price:");
            outstandingPaymentEditLabel = new JLabel("Outstanding Payment:");
            statusEditLabel = new JLabel("Status:");
            penaltyTypeEditLabel = new JLabel("Penalty Type:");
            startDateEditLabel = new JLabel("Start Date:");
            endDateEditLabel = new JLabel("End Date:");
            JLabel editBookingLabels[] = {carNumberPlateEditLabel, customerNameEditLabel, totalPriceEditLabel, outstandingPaymentEditLabel, statusEditLabel, penaltyTypeEditLabel, startDateEditLabel, endDateEditLabel};
            GUI.JLabelSetup(editBookingLabels);

            carNumberPlateEdit = new JTextField(20);
            customerNameEdit = new JTextField(20);
            totalPriceEdit = new JTextField(20);
            outstandingPaymentEdit = new JTextField(20);
            totalPriceEdit.setEnabled(false);

            statusBoxEdit = new JComboBox<>(statusType);
            statusBoxEdit.setFont(GUI.getDefaultFont());

            penaltyTypeEdit = new JComboBox<>(penaltyType);
            penaltyTypeEdit.setFont(GUI.getDefaultFont());

            startDateDayEdit = new JSpinner(new SpinnerNumberModel(0,0,31,1));
            endDateDayEdit = new JSpinner(new SpinnerNumberModel(0,0,31,1));

            startDateMonthEdit = new JComboBox<>(month);
            endDateMonthEdit = new JComboBox<>(month);
            startDateMonthEdit.setFont(GUI.getDefaultFont());
            endDateMonthEdit.setFont(GUI.getDefaultFont());

            startDateYearEdit = new JComboBox<>(year);
            endDateYearEdit = new JComboBox<>(year);
            startDateYearEdit.setFont(GUI.getDefaultFont());
            endDateYearEdit.setFont(GUI.getDefaultFont());

            JPanel startDateEditPanel = new JPanel();
            startDateEditPanel.setBackground(Color.white);
            startDateEditPanel.add(startDateDayEdit);
            startDateEditPanel.add(startDateMonthEdit);
            startDateEditPanel.add(startDateYearEdit);

            JPanel endDateEditPanel = new JPanel();
            endDateEditPanel.setBackground(Color.white);
            endDateEditPanel.add(endDateDayEdit);
            endDateEditPanel.add(endDateMonthEdit);
            endDateEditPanel.add(endDateYearEdit);

            GridBagConstraints editAttributesConstraints = new GridBagConstraints();
            editAttributesConstraints.insets = new Insets(2,2,2,2);
            editAttributesConstraints.gridx = 0;
            for(int i = 0; i < editBookingLabels.length; i++){
                editAttributesConstraints.gridy = i;
                editBookingAttributesPanel.add(editBookingLabels[i], editAttributesConstraints);
            }

            editAttributesConstraints.gridx = 1;
            editAttributesConstraints.gridy = 0;
            editBookingAttributesPanel.add(carNumberPlateEdit, editAttributesConstraints);

            editAttributesConstraints.gridy = 1;
            editBookingAttributesPanel.add(customerNameEdit, editAttributesConstraints);

            editAttributesConstraints.gridy = 2;
            editBookingAttributesPanel.add(totalPriceEdit, editAttributesConstraints);

            editAttributesConstraints.gridy = 3;
            editBookingAttributesPanel.add(outstandingPaymentEdit, editAttributesConstraints);

            editAttributesConstraints.gridy = 4;
            editBookingAttributesPanel.add(statusBoxEdit, editAttributesConstraints);

            editAttributesConstraints.gridy = 5;
            editBookingAttributesPanel.add(penaltyTypeEdit, editAttributesConstraints);

            editAttributesConstraints.gridy = 6;
            editBookingAttributesPanel.add(startDateEditPanel, editAttributesConstraints);

            editAttributesConstraints.gridy = 7;
            editBookingAttributesPanel.add(endDateEditPanel, editAttributesConstraints);

            JPanel editBookingSelectionPanel = new JPanel(new FlowLayout());
            editBookingSelectionPanel.setBackground(Color.white);

            OKAdminButton = new JButton("OK");
            backToSearchButton = new JButton("BACK");
            receiptAdminButton = new JButton("RECEIPT");
            GUI.JButtonSetup(new JButton[]{OKAdminButton, backToSearchButton, receiptAdminButton});
            OKAdminButton.addActionListener(this);
            backToSearchButton.addActionListener(this);
            receiptAdminButton.addActionListener(this);
            editBookingSelectionPanel.add(OKAdminButton);
            editBookingSelectionPanel.add(backToSearchButton);
            editBookingSelectionPanel.add(receiptAdminButton);

            GridBagConstraints editConstraints = new GridBagConstraints();
            editConstraints.gridy = 0;
            editBookingPanel.add(editBookingAttributesPanel, editConstraints);
            editConstraints.gridy = 1;
            editBookingPanel.add(editBookingSelectionPanel, editConstraints);

            //View Booking Panel
            //Create Panel
            viewBookingPanel = new JPanel(new GridBagLayout());

            adminReceiptPanel = new JPanel(new GridBagLayout());

            adminPanels = new JPanel[]{searchBookingPanel, viewBookingPanel, editBookingPanel, adminReceiptPanel};
            GUI.JPanelSetup(adminPanels);
            setPreferredSize(new Dimension(600,500));
            add(searchBookingPanel);
            add(viewBookingPanel);
            add(editBookingPanel);
            add(adminReceiptPanel);
        }

        else {
            //JPanel
            ongoingBookingPanel = new JPanel(new GridBagLayout());
            completedBookingPanel = new JPanel(new GridBagLayout());
            customerViewBookingPanel = new JPanel(new GridBagLayout());

            //Edit Panel
            customerEditBookingPanel = new JPanel(new GridBagLayout());
            customerEditBookingAttributesPanel = new JPanel(new GridBagLayout());
            customerEditBookingAttributesPanel.setBackground(Color.white);

            editBookingPanel = new JPanel(new GridBagLayout());
            editBookingAttributesPanel = new JPanel(new GridBagLayout());
            carNumberPlateEditLabel = new JLabel("Car Number Plate:");
            startDateEditLabel = new JLabel("Start Date:");
            endDateEditLabel = new JLabel("End Date:");
            JLabel editBookingLabels[] = {carNumberPlateEditLabel, startDateEditLabel, endDateEditLabel};
            GUI.JLabelSetup(editBookingLabels);

            carNumberPlateEdit = new JTextField(20);
            startDateDayEdit = new JSpinner(new SpinnerNumberModel(0,0,31,1));
            endDateDayEdit = new JSpinner(new SpinnerNumberModel(0,0,31,1));

            startDateMonthEdit = new JComboBox<>(month);
            endDateMonthEdit = new JComboBox<>(month);
            startDateMonthEdit.setFont(GUI.getDefaultFont());
            endDateMonthEdit.setFont(GUI.getDefaultFont());

            startDateYearEdit = new JComboBox<>(year);
            endDateYearEdit = new JComboBox<>(year);
            startDateYearEdit.setFont(GUI.getDefaultFont());
            endDateYearEdit.setFont(GUI.getDefaultFont());

            JPanel startDateEditPanel = new JPanel();
            startDateEditPanel.setBackground(Color.white);
            startDateEditPanel.add(startDateDayEdit);
            startDateEditPanel.add(startDateMonthEdit);
            startDateEditPanel.add(startDateYearEdit);

            JPanel endDateEditPanel = new JPanel();
            endDateEditPanel.setBackground(Color.white);
            endDateEditPanel.add(endDateDayEdit);
            endDateEditPanel.add(endDateMonthEdit);
            endDateEditPanel.add(endDateYearEdit);

            GridBagConstraints editAttributesConstraints = new GridBagConstraints();
            editAttributesConstraints.insets = new Insets(20,2,20,2);
            editAttributesConstraints.gridx = 0;
            for(int i = 0; i < editBookingLabels.length; i++){
                editAttributesConstraints.gridy = i;
                customerEditBookingAttributesPanel.add(editBookingLabels[i], editAttributesConstraints);
            }

            editAttributesConstraints.gridx = 1;
            editAttributesConstraints.gridy = 0;
            customerEditBookingAttributesPanel.add(carNumberPlateEdit, editAttributesConstraints);

            editAttributesConstraints.gridy = 1;
            customerEditBookingAttributesPanel.add(startDateEditPanel, editAttributesConstraints);

            editAttributesConstraints.gridy = 2;
            customerEditBookingAttributesPanel.add(endDateEditPanel, editAttributesConstraints);

            JPanel editBookingSelectionPanel = new JPanel(new FlowLayout());
            editBookingSelectionPanel.setBackground(Color.white);

            OKCustomerButton = new JButton("OK");
            backCustomerButton = new JButton("BACK");
            GUI.JButtonSetup(new JButton[]{OKCustomerButton, backCustomerButton});
            OKCustomerButton.addActionListener(this);
            backCustomerButton.addActionListener(this);
            editBookingSelectionPanel.add(OKCustomerButton);
            editBookingSelectionPanel.add(backCustomerButton);

            GridBagConstraints editConstraints = new GridBagConstraints();
            editConstraints.insets = new Insets(10,2,20,2);
            editConstraints.gridx = 0;
            editConstraints.gridy = 0;
            customerEditBookingPanel.add(customerEditBookingAttributesPanel, editConstraints);
            editConstraints.gridy = 1;
            customerEditBookingPanel.add(editBookingSelectionPanel, editConstraints);

            customerReceiptPanel = new JPanel(new GridBagLayout());
            customerBookingPaymentPanel = new JPanel(new GridBagLayout());

            customerPanels = new JPanel[]{ongoingBookingPanel, completedBookingPanel, customerViewBookingPanel, customerEditBookingPanel, customerReceiptPanel, customerBookingPaymentPanel};
            GUI.JPanelSetup(customerPanels);
            setPreferredSize(new Dimension(600,500));
            add(ongoingBookingPanel);
            add(completedBookingPanel);
            add(customerViewBookingPanel);
            add(customerEditBookingPanel);
            add(customerReceiptPanel);
            add(customerBookingPaymentPanel);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == searchButton) {
                GUI.playSound("ji.wav");
                searchBooking();
            }
            else if (e.getSource() == editAdminButton) {
                if ((int) numberSpinnerEdit.getValue() == 0) {
                    throw new BookingNotFoundException();
                }
                GUI.playSound("ji.wav");
                showBookingPanel(editBookingPanel);
                showBookingDetailsAdmin();
            }
            else if (e.getSource() == deleteAdminButton) {
                if ((int) numberSpinnerEdit.getValue() == 0) {
                    throw new BookingNotFoundException();
                }
                GUI.playSound("DontSayFiveDe.wav");

                String input = JOptionPane.showInputDialog("Type \"DELETE\" to confirm the deletion!");
                if (input != null && input.equals("DELETE")) {
                    int numberValue = (int) numberSpinnerEdit.getValue();

                    Booking.deleteBooking(numberValue);
                    JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Booking has been deleted!");
                    searchBooking();
                } else {
                    GUI.playSound("ElectricVoice.wav");
                    JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Deletion canceled!");
                }
            }
            else if (e.getSource() == OKAdminButton){
                GUI.playSound("ji.wav");
                String input = JOptionPane.showInputDialog("Type \"CONFIRM\" to proceed!");
                if (input != null && input.equals("CONFIRM")){
                    editBookingDetailsAdmin();
                    JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Booking has been edited!");
                    showBookingPanel(searchBookingPanel);
                    searchBooking();
                }
                else {
                    GUI.playSound("ElectricVoice.wav");
                    JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Edit canceled!");
                }
            }
            else if (e.getSource() == backToSearchButton){
                GUI.playSound("ji.wav");
                showBookingPanel(searchBookingPanel);
            }
            else if (e.getSource() == receiptAdminButton){
                GUI.playSound("ji.wav");
                int numberValue = (int) numberSpinnerEdit.getValue();
                Booking booking = FileIO.bookingList.get(numberValue - 1);
                generateReceipt(booking);
            }
            else if (e.getSource() == editCustomerButton){
                if ((int) numberSpinnerEdit.getValue() == 0) {
                    throw new BookingNotFoundException();
                }
                GUI.playSound("ji.wav");

                if (currentBookingList.get((int)numberSpinnerEdit.getValue() - 1).getStatus() != Booking.Status.BOOKED){
                    JOptionPane.showMessageDialog(this, "You cannot edit this booking anymore!", "Edit Failed", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    showBookingPanel(customerEditBookingPanel);
                    showBookingDetailsCustomer();
                }
            }
            else if (e.getSource() == deleteCustomerButton){
                if ((int) numberSpinnerEdit.getValue() == 0) {
                    throw new BookingNotFoundException();
                }
                GUI.playSound("ji.wav");

                if (currentBookingList.get((int)numberSpinnerEdit.getValue() - 1).getStatus() != Booking.Status.BOOKED){
                    JOptionPane.showMessageDialog(this, "You cannot delete this booking anymore!", "Delete Failed", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    String input = JOptionPane.showInputDialog("Type \"DELETE\" to confirm the deletion!");
                    if (input != null && input.equals("DELETE")) {
                        int numberValue = (int) numberSpinnerEdit.getValue();
                        Booking.deleteBooking(currentBookingList.get(numberValue - 1));
                        JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Booking has been deleted!");
                        GUI.playSound("DontSayFiveDe.wav");
                        viewOngoingBookingCustomer();
                    } else {
                        GUI.playSound("ElectricVoice.wav");
                        JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Deletion canceled!");
                    }
                }
            }
            else if (e.getSource() == OKCustomerButton){
                GUI.playSound("ji.wav");
                String input = JOptionPane.showInputDialog("Type \"CONFIRM\" to proceed!");
                if (input != null && input.equals("CONFIRM")){
                    editBookingDetailsCustomer();
                    showBookingPanel(ongoingBookingPanel);
                    viewOngoingBookingCustomer();
                }
                else {
                    GUI.playSound("ElectricVoice.wav");
                    JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Edit canceled!");
                }
            }
            else if (e.getSource() == backCustomerButton){
                GUI.playSound("ji.wav");
                showBookingPanel(ongoingBookingPanel);
                viewOngoingBookingCustomer();
            }
            else if (e.getSource() == receiptCustomerButton){
                GUI.playSound("ji.wav");
                int numberValue = (int) numberSpinnerReceipt.getValue();
                Booking booking = CarRentalSystem.loginCustomer.getMyBookings().get(numberValue - 1);
                generateReceipt(booking);
            }
            else if (e.getSource() == printButton){
                GUI.playSound("ji.wav");
                printReceipt();
            }
            else if (e.getSource() == payButton){
                if ((int) numberSpinnerEdit.getValue() == 0) {
                    throw new BookingNotFoundException();
                }
                GUI.playSound("ji.wav");
                showPaymentPanel();
            }
            else if (e.getSource() == confirmPaymentButton){
                GUI.playSound("ji.wav");
                confirmCustomerPayment();
                showBookingPanel(ongoingBookingPanel);
                viewOngoingBookingCustomer();
            }
            else if (e.getSource() == cancelPaymentButton){
                showBookingPanel(ongoingBookingPanel);
                GUI.playSound("ji.wav");
                viewOngoingBookingCustomer();
            }
        }
        catch (BookingNotFoundException bookingNotFoundException){
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Invalid booking selected! Please try again.", "Invalid Booking", JOptionPane.WARNING_MESSAGE);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Unexpected error occurred! Please try again later.", "Registration Approval Failed", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void showSearchBookingPanel(){
        showBookingPanel(searchBookingPanel);
    }
    public static void showAllBookingPanel(){
        viewBookingPanel.removeAll();
        viewAllBooking();
        showBookingPanel(viewBookingPanel);
    }
    public static void showBookingPanel(JPanel panel){
        if (isAdmin){
            for (JPanel i : adminPanels) {
                i.setVisible(false);
            }
        }

        else {
            for (JPanel i : customerPanels) {
                i.setVisible(false);
            }
        }

        panel.setVisible(true);
    }

    private void showBookingDetailsAdmin(){
        int numberValue = (int) numberSpinnerEdit.getValue();
        Booking booking = currentBookingList.get(numberValue - 1);

        carNumberPlateEdit.setText(booking.getCar().getNumberPlate());
        customerNameEdit.setText(booking.getCustomer().getUsername());
        totalPriceEdit.setText(String.valueOf(booking.getTotalPrice()));
        outstandingPaymentEdit.setText(String.valueOf(booking.getOutstandingPayment()));
        statusBoxEdit.setSelectedItem(booking.getStatus());
        penaltyTypeEdit.setSelectedItem(booking.getPenalty());

        Date startDate = booking.getStartDate();
        Date endDate = booking.getEndDate();

        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

        startDateDayEdit.setValue(Integer.parseInt(dayFormat.format(startDate)));
        endDateDayEdit.setValue(Integer.parseInt(dayFormat.format(endDate)));

        startDateMonthEdit.setSelectedIndex(Integer.parseInt(monthFormat.format(startDate)));
        endDateMonthEdit.setSelectedIndex(Integer.parseInt(monthFormat.format(endDate)));

        startDateYearEdit.setSelectedItem(yearFormat.format(startDate));
        endDateYearEdit.setSelectedItem(yearFormat.format(endDate));
    }

    private void showBookingDetailsCustomer(){
        int numberValue = (int) numberSpinnerEdit.getValue();
        Booking booking = currentBookingList.get(numberValue - 1);

        carNumberPlateEdit.setText(booking.getCar().getNumberPlate());
        carNumberPlateEdit.setEnabled(false);

        Date startDate = booking.getStartDate();
        Date endDate = booking.getEndDate();

        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

        startDateDayEdit.setValue(Integer.parseInt(dayFormat.format(startDate)));
        endDateDayEdit.setValue(Integer.parseInt(dayFormat.format(endDate)));

        startDateMonthEdit.setSelectedIndex(Integer.parseInt(monthFormat.format(startDate)));
        endDateMonthEdit.setSelectedIndex(Integer.parseInt(monthFormat.format(endDate)));

        startDateYearEdit.setSelectedItem(yearFormat.format(startDate));
        endDateYearEdit.setSelectedItem(yearFormat.format(endDate));
    }

    private void editBookingDetailsAdmin(){
        int numberValue = (int) numberSpinnerEdit.getValue();
        Booking booking = currentBookingList.get(numberValue - 1);
        String carNumberPlate = carNumberPlateEdit.getText();
        String customerName = customerNameEdit.getText();
        double outstandingPayment = Double.parseDouble(outstandingPaymentEdit.getText());
        Booking.Status status = (Booking.Status) statusBoxEdit.getSelectedItem();
        Booking.PenaltyType penalty = (Booking.PenaltyType) penaltyTypeEdit.getSelectedItem();

        try{
            int startDateDay = (int) startDateDayEdit.getValue();
            Booking.Month startDateMonth = (Booking.Month) startDateMonthEdit.getSelectedItem();
            String startDateYear = (String) startDateYearEdit.getSelectedItem();

            int endDateDay = (int) endDateDayEdit.getValue();
            Booking.Month endDateMonth = (Booking.Month) endDateMonthEdit.getSelectedItem();
            String endDateYear = (String) endDateYearEdit.getSelectedItem();

            if(!Booking.isValidDate(startDateDay, startDateMonth, startDateYear) || !Booking.isValidDate(endDateDay, endDateMonth, endDateYear)){
                throw new InvalidDateDurationException();
            }

            Date startDate = Booking.convertToDate(startDateDay, startDateMonth, startDateYear);
            Date endDate = Booking.convertToDate(endDateDay, endDateMonth, endDateYear);

            Booking.editBookingDetails(booking, carNumberPlate, customerName, outstandingPayment, status, penalty, startDate, endDate);
            GUI.JSliderSetup(totalPriceSlider, false);
        }
        catch (InvalidDateDurationException invalidDateDurationException){
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Invalid date! Please try again.", "Invalid Date Input", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void searchBooking(){
        currentBookingList = new ArrayList<>();
        searchBookingResultsPanel.removeAll();

        String numberPlate = carNumberPlateSearch.getText();
        String customerName = customerNameSearch.getText();
        double totalPrice = Double.parseDouble(totalPriceIndicator.getText());
        Booking.Status status = (Booking.Status) statusBoxSearch.getSelectedItem();
        Booking.PenaltyType penaltyType = (Booking.PenaltyType) penaltyTypeSearch.getSelectedItem();

        int startDay = (int) startDateDaySearch.getValue();
        Booking.Month startMonth = (Booking.Month) startDateMonthSearch.getSelectedItem();
        String startYear = (String) startDateYearSearch.getSelectedItem();

        int endDay = (int) endDateDaySearch.getValue();
        Booking.Month endMonth = (Booking.Month) endDateMonthSearch.getSelectedItem();
        String endYear = (String) endDateYearSearch.getSelectedItem();

        ArrayList<Booking> searchedList = Booking.searchBooking(numberPlate, customerName, totalPrice, status, penaltyType, startDay, startMonth, startYear, endDay, endMonth, endYear);

        if (searchedList.size() == 0){
            JLabel bookingNotFoundLabel = new JLabel("Booking not found!");
            bookingNotFoundLabel.setHorizontalAlignment(JLabel.CENTER);
            bookingNotFoundLabel.setFont(GUI.getDefaultFont());
            searchBookingResultsPanel.add(bookingNotFoundLabel);
            bookingNotFoundLabel.setVisible(true);

            if (searchTableScroll != null){
                searchTableScroll.setVisible(false);
            }
        }
        else{
            String[] tableColumn = {"No.", "Car No. Plate", "Customer Username", "Total Price", "Outstanding Payment", "Status", "Penalty Type", "Start Date", "End Date"};
            Object[][] tempTable = new Object[searchedList.size()][tableColumn.length];
            int i = 0;
            for (Booking booking : searchedList){
                currentBookingList.add(booking);
                i = insertBookingTableAdmin(tempTable, i, booking);
            }

            JTable searchTable = new JTable(tempTable, tableColumn);

            searchTableScroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            searchTableScroll.setViewportView(searchTable);
            searchTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            searchTableScroll.setVisible(true);
            searchTableScroll.setPreferredSize(new Dimension(500, 150));
            searchBookingResultsPanel.add(searchTableScroll, BorderLayout.CENTER);

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
            int maxNum = searchedList.size();

            numberSpinnerEdit = new JSpinner(new SpinnerNumberModel(0, 0, maxNum, 1));
            bottomPanel.add(numberSpinnerEdit, bottomConstraints);

            editAdminButton = new JButton("EDIT");
            deleteAdminButton = new JButton("DELETE");
            editAdminButton.addActionListener(this);
            deleteAdminButton.addActionListener(this);
            JButton[] buttons = new JButton[]{editAdminButton, deleteAdminButton};
            GUI.subJButtonSetup(buttons, new Dimension(100,30));

            bottomConstraints.gridx = 3;
            bottomPanel.add(editAdminButton, bottomConstraints);

            bottomConstraints.gridx = 4;
            bottomPanel.add(deleteAdminButton, bottomConstraints);

            searchBookingResultsPanel.add(bottomPanel, BorderLayout.SOUTH);

            searchBookingResultsPanel.setVisible(true);
        }

        searchBookingResultsPanel.updateUI();
        searchBookingResultsPanel.validate();
        searchBookingPanel.validate();
    }

    private static void viewAllBooking(){
        String[] tableColumn = {"No.", "Car No. Plate", "Customer Username", "Total Price", "Outstanding Payment", "Status", "Penalty Type", "Start Date", "End Date"};
        Object[][] tempTable = new Object[FileIO.bookingList.size()][tableColumn.length];
        int i = 0;
        for (Booking booking: FileIO.bookingList){
            i = insertBookingTableAdmin(tempTable, i, booking);
        }

        allBookingTable = new JTable(tempTable, tableColumn);
        allBookingTable.setVisible(true);
        JScrollPane bookingScrollPane = new JScrollPane(allBookingTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        allBookingTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        bookingScrollPane.setPreferredSize(new Dimension(500, 420));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(15,5,10,5);
        viewBookingPanel.add(bookingScrollPane, constraints);

        viewBookingPanel.validate();
    }

    private static int insertBookingTableAdmin(Object[][] tempTable, int i, Booking booking) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        tempTable[i][0] = i + 1;
        tempTable[i][1] = booking.getCar().getNumberPlate();
        tempTable[i][2] = booking.getCustomer().getUsername();
        tempTable[i][3] = booking.getTotalPrice();
        tempTable[i][4] = booking.getOutstandingPayment();
        tempTable[i][5] = booking.getStatus();
        tempTable[i][6] = booking.getPenalty();
        tempTable[i][7] = dateFormat.format(booking.getStartDate());
        tempTable[i][8] = dateFormat.format(booking.getEndDate());
        i++;
        return i;
    }

    public static void showAllBookingCustomerPanel(){
        customerViewBookingPanel.removeAll();
        viewAllBookingCustomer();
        showBookingPanel(customerViewBookingPanel);
    }

    public static void showOngoingBookingCustomerPanel(){
        showBookingPanel(ongoingBookingPanel);
    }

    public static void showCompletedBookingCustomerPanel(){
        showBookingPanel(completedBookingPanel);
    }

    private static void viewAllBookingCustomer(){
        String[] tableColumn = {"No.", "Car No. Plate", "Total Price", "Outstanding Payment", "Status", "Penalty Type", "Start Date", "End Date"};
        Object[][] tempTable = new Object[CarRentalSystem.loginCustomer.getMyBookings().size()][tableColumn.length];
        int i = 0;
        boolean bookingFound = false;
        for (Booking booking: FileIO.bookingList){
            if (booking.getCustomer().getUsername().equals(CarRentalSystem.loginCustomer.getUsername())){
                i = insertBookingTableCustomer(tempTable, i, booking);
                bookingFound = true;
            }
        }

        if (bookingFound){
            customerAllBookingTable = new JTable(tempTable, tableColumn);
            customerAllBookingTable.setVisible(true);
            JScrollPane bookingScrollPane = new JScrollPane(customerAllBookingTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            customerAllBookingTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            bookingScrollPane.setPreferredSize(new Dimension(500, 420));

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets = new Insets(15,5,10,5);
            customerViewBookingPanel.add(bookingScrollPane, constraints);
        }

        else {
            JLabel bookingNotFoundLabel = new JLabel("No Bookings Found!");
            GUI.JLabelSetup(bookingNotFoundLabel);
            customerViewBookingPanel.add(bookingNotFoundLabel);
        }

        customerViewBookingPanel.validate();
    }

    public void viewOngoingBookingCustomer(){
        currentBookingList = new ArrayList<>();

        ongoingBookingPanel.removeAll();
        int row = 0;
        for (Booking booking: CarRentalSystem.loginCustomer.getMyBookings()){
            if (booking.getStatus() != Booking.Status.COMPLETED){
                row++;
            }
        }

        String[] tableColumn = {"No.", "Car No. Plate", "Total Price", "Outstanding Payment", "Status", "Penalty Type", "Start Date", "End Date"};
        Object[][] tempTable = new Object[row][tableColumn.length];
        int i = 0;
        boolean bookingFound = false;

        for (Booking booking: CarRentalSystem.loginCustomer.getMyBookings()){
            if (booking.getStatus() != Booking.Status.COMPLETED){
                i = insertBookingTableCustomer(tempTable, i, booking);
                currentBookingList.add(booking);
                bookingFound = true;
            }
        }

        if (bookingFound){
            ongoingBookingTable = new JTable(tempTable, tableColumn);
            ongoingBookingTable.setVisible(true);
            JScrollPane bookingScrollPane = new JScrollPane(ongoingBookingTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            ongoingBookingTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            bookingScrollPane.setPreferredSize(new Dimension(500, 350));

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets = new Insets(15,5,10,5);
            ongoingBookingPanel.add(bookingScrollPane, constraints);

            JPanel bottomPanel = new JPanel(new GridBagLayout());
            bottomPanel.setBackground(Color.white);
            GridBagConstraints bottomConstraints = new GridBagConstraints();

            bottomConstraints.fill = GridBagConstraints.BOTH;
            bottomConstraints.anchor = GridBagConstraints.WEST;
            bottomConstraints.gridx = 0;
            bottomConstraints.insets = new Insets(5,5,5,5);
            JLabel numberLabel = new JLabel("Row Number: ");
            GUI.JLabelSetup(numberLabel);
            bottomPanel.add(numberLabel, bottomConstraints);

            bottomConstraints.gridx = 1;
            int maxNum = i;

            numberSpinnerEdit = new JSpinner(new SpinnerNumberModel(0,0,maxNum, 1));
            bottomPanel.add(numberSpinnerEdit, bottomConstraints);

            editCustomerButton = new JButton("EDIT");
            deleteCustomerButton = new JButton("DELETE");
            payButton = new JButton("PAY");
            editCustomerButton.addActionListener(this);
            deleteCustomerButton.addActionListener(this);
            payButton.addActionListener(this);
            JButton[] buttons = new JButton[]{editCustomerButton, deleteCustomerButton, payButton};
            GUI.subJButtonSetup(buttons, new Dimension(100,30));

            bottomConstraints.gridx = 3;
            bottomPanel.add(editCustomerButton, bottomConstraints);

            bottomConstraints.gridx = 4;
            bottomPanel.add(deleteCustomerButton, bottomConstraints);

            bottomConstraints.gridx = 5;
            bottomPanel.add(payButton, bottomConstraints);

            bottomConstraints.gridx = 0;
            bottomConstraints.gridwidth = 5;
            bottomConstraints.gridy = 1;
            ongoingBookingPanel.add(bottomPanel, bottomConstraints);
        }

        else {
            JLabel bookingNotFoundLabel = new JLabel("No Bookings Found!");
            GUI.JLabelSetup(bookingNotFoundLabel);
            ongoingBookingPanel.add(bookingNotFoundLabel);
        }

        ongoingBookingPanel.updateUI();
        ongoingBookingPanel.validate();
    }

    public void viewCompletedBookingCustomer(){
        completedBookingPanel.removeAll();
        int row = 0;
        for (Booking booking: CarRentalSystem.loginCustomer.getMyBookings()){
            if (booking.getStatus() == Booking.Status.COMPLETED){
                row++;
            }
        }

        String[] tableColumn = {"No.", "Car No. Plate", "Total Price", "Outstanding Payment", "Status", "Penalty Type", "Start Date", "End Date"};
        Object[][] tempTable = new Object[row][tableColumn.length];
        int i = 0;
        boolean bookingFound = false;
        for (Booking booking: CarRentalSystem.loginCustomer.getMyBookings()){
            if (booking.getStatus() == Booking.Status.COMPLETED){
                i = insertBookingTableCustomer(tempTable, i, booking);
                bookingFound = true;
            }
        }

        if (bookingFound){
            completedBookingTable = new JTable(tempTable, tableColumn);
            completedBookingTable.setVisible(true);
            JScrollPane bookingScrollPane = new JScrollPane(completedBookingTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            completedBookingTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            bookingScrollPane.setPreferredSize(new Dimension(500, 320));

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets = new Insets(15,5,10,5);
            completedBookingPanel.add(bookingScrollPane, constraints);

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
            int maxNum = i;

            numberSpinnerReceipt = new JSpinner(new SpinnerNumberModel(1,1,maxNum, 1));
            bottomPanel.add(numberSpinnerReceipt, bottomConstraints);

            receiptCustomerButton = new JButton("RECEIPT");
            receiptCustomerButton.addActionListener(this);
            JButton[] buttons = new JButton[]{receiptCustomerButton};
            GUI.JButtonSetup(buttons);

            bottomConstraints.gridx = 3;
            bottomPanel.add(receiptCustomerButton, bottomConstraints);

            bottomConstraints.gridx = 0;
            bottomConstraints.gridwidth = 4;
            bottomConstraints.gridy = 1;
            completedBookingPanel.add(bottomPanel, bottomConstraints);
        }

        else {
            JLabel bookingNotFoundLabel = new JLabel("No Bookings Found!");
            GUI.JLabelSetup(bookingNotFoundLabel);
            completedBookingPanel.add(bookingNotFoundLabel);
        }

        completedBookingPanel.validate();
    }

    private void editBookingDetailsCustomer(){
        int numberValue = (int) numberSpinnerEdit.getValue();
        Booking booking = currentBookingList.get(numberValue - 1);
        String carNumberPlate = carNumberPlateEdit.getText();

        try{
            int startDateDay = (int) startDateDayEdit.getValue();
            Booking.Month startDateMonth = (Booking.Month) startDateMonthEdit.getSelectedItem();
            String startDateYear = (String) startDateYearEdit.getSelectedItem();

            int endDateDay = (int) endDateDayEdit.getValue();
            Booking.Month endDateMonth = (Booking.Month) endDateMonthEdit.getSelectedItem();
            String endDateYear = (String) endDateYearEdit.getSelectedItem();

            if(!Booking.isValidDate(startDateDay, startDateMonth, startDateYear) || !Booking.isValidDate(endDateDay, endDateMonth, endDateYear)){
                throw new InvalidDateDurationException();
            }

            Date startDate = Booking.convertToDate(startDateDay, startDateMonth, startDateYear);
            Date endDate = Booking.convertToDate(endDateDay, endDateMonth, endDateYear);

            Booking.editBookingDetails(booking, carNumberPlate, startDate, endDate);
        }
        catch (InvalidDateDurationException invalidDateDurationException){
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Invalid date! Please try again.", "Invalid Date Input", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void showPaymentPanel(){
        customerBookingPaymentPanel.removeAll();
        int numberValue = (int) numberSpinnerEdit.getValue();
        Booking booking = currentBookingList.get(numberValue - 1);

        if (booking.getOutstandingPayment() == 0){
            JOptionPane.showMessageDialog(this, "No outstanding payment for this booking!", "No Outstanding Payment", JOptionPane.WARNING_MESSAGE);
            showOngoingBookingCustomerPanel();
            viewOngoingBookingCustomer();
            return;
        }

        JLabel totalPriceLabel = new JLabel();
        totalPriceLabel.setText("Total Price: " + booking.getTotalPrice());
        JLabel outstandingPaymentLabel = new JLabel();
        outstandingPaymentLabel.setText("Outstanding Payment: " + booking.getOutstandingPayment());

        JLabel cardNumberLabel = new JLabel("Card Number: ");
        JLabel cvvLabel = new JLabel("CVC/ CVV: ");
        GUI.JLabelSetup(new JLabel[]{totalPriceLabel, outstandingPaymentLabel, cardNumberLabel, cvvLabel});

        cardNumber = new JTextField(20);
        cvv = new JTextField(20);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 10, 10, 10);
        customerBookingPaymentPanel.add(totalPriceLabel, constraints);

        constraints.gridy = 1;
        customerBookingPaymentPanel.add(outstandingPaymentLabel, constraints);

        constraints.gridwidth = 1;
        constraints.gridy = 2;
        customerBookingPaymentPanel.add(cardNumberLabel, constraints);

        constraints.gridx = 1;
        customerBookingPaymentPanel.add(cardNumber, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        customerBookingPaymentPanel.add(cvvLabel, constraints);

        constraints.gridx = 1;
        customerBookingPaymentPanel.add(cvv, constraints);

        confirmPaymentButton = new JButton("CONFIRM");
        cancelPaymentButton = new JButton("CANCEL");
        confirmPaymentButton.addActionListener(this);
        cancelPaymentButton.addActionListener(this);
        GUI.JButtonSetup(new JButton[]{confirmPaymentButton, cancelPaymentButton});

        constraints.gridy = 4;
        constraints.gridx = 0;
        customerBookingPaymentPanel.add(confirmPaymentButton, constraints);

        constraints.gridx = 1;
        customerBookingPaymentPanel.add(cancelPaymentButton, constraints);

        showBookingPanel(customerBookingPaymentPanel);
    }

    private void confirmCustomerPayment(){

        if (cardNumber.getText().equals("") || cvv.getText().equals("")){
            GUI.playSound("NormalVoice.wav");
            JOptionPane.showMessageDialog(this, "Payment details incomplete!", "Payment Failed", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher cardMatcher = pattern.matcher(cardNumber.getText());
        Matcher cvvMatcher = pattern.matcher(cvv.getText());

        if (!cardMatcher.matches() || !cvvMatcher.matches()){
            JOptionPane.showMessageDialog(this, "Invalid card number or cvv!", "Payment Failed", JOptionPane.WARNING_MESSAGE);
            GUI.resetFields(new JComponent[]{cardNumber, cvv});
            return;
        }

        int numberValue = (int) numberSpinnerEdit.getValue();
        Booking booking = currentBookingList.get(numberValue - 1);

        Booking.makePayment(booking);
        JOptionPane.showMessageDialog(this, "Payment successful!", "Payment Succeeded", JOptionPane.WARNING_MESSAGE);
    }

    private static int insertBookingTableCustomer(Object[][] tempTable, int i, Booking booking) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        tempTable[i][0] = i + 1;
        tempTable[i][1] = booking.getCar().getNumberPlate();
        tempTable[i][2] = booking.getTotalPrice();
        tempTable[i][3] = booking.getOutstandingPayment();
        tempTable[i][4] = booking.getStatus();
        tempTable[i][5] = booking.getPenalty();
        tempTable[i][6] = dateFormat.format(booking.getStartDate());
        tempTable[i][7] = dateFormat.format(booking.getEndDate());
        i++;
        return i;
    }

    private void generateReceipt(Booking booking){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        long milliSecDiff = Math.abs(booking.getEndDate().getTime() - booking.getStartDate().getTime());
        long duration = TimeUnit.DAYS.convert(milliSecDiff, TimeUnit.MILLISECONDS);

        receiptText = "<html><body><div style=\"max-width:220px\"><h1 style=\"text-align:center;\">Rent A Car</h1>";

        for (int i = 0; i < 40; i++){
            receiptText += "-";
        }

        receiptText += "<br/><p>";

        receiptText += "Customer Username: " + booking.getCustomer().getUsername() + "<br/>";
        receiptText += "Customer Name: " + booking.getCustomer().getName() + "<br/>";
        receiptText += "Car Number Plate: " + booking.getCar().getNumberPlate() + "<br/>";
        receiptText += "Start Date: " + dateFormat.format(booking.getStartDate()) + "<br/>";
        receiptText += "End Date: " + dateFormat.format(booking.getEndDate()) + "<br/></p>";

        for (int i = 0; i < 40; i++){
            receiptText += "-";
        }

        JLabel receiptLabel = new JLabel();
        receiptLabel.setText(receiptText);

        receiptText += "<div style=\"text-align:right;width:100%\"><span style=\"float:left\">Car Price Per Day: </span>\n" +
                String.format("%.2f", booking.getCar().getPrice()) + "</div>";
        receiptText += "<div style=\"text-align:right;width:100%\"><span style=\"float:left\">Duration (day): </span>\n" +
                duration + "</div>";
        receiptText += "<div style=\"text-align:right;width:100%\"><span style=\"float:left\">Penalty Fee: </span>\n" +
                String.format("%.2f", booking.calcPenaltyFee()) + "</div>";
        receiptText += "<div style=\"text-align:right;width:100%\"><span style=\"float:left\">Member Discount: </span>\n" +
                String.format("%.2f", booking.calcMemberDiscount()) + "</div>";
        receiptText += "<div style=\"text-align:right;width:100%\"><span style=\"float:left\">Total Price: </span>\n" +
                String.format("%.2f", booking.getTotalPrice()) + "</div>";
        receiptText += "<p style=\"text-align:center\">Please come again!</p></div></body></html>";

        JLabel textLabels = new JLabel();
        textLabels.setText("<html>Car Price Per Day: <br/>" +
                "Duration (day): <br/>" +
                "Penalty Fee: <br/>" +
                "Member Discount: <br/>" +
                "Total Price: </html>");

        JLabel priceLabels = new JLabel();
        priceLabels.setText("<html><p style=\"text-align:right;\">" + String.format("%.2f", booking.getCar().getPrice()) + "<br/>" +
                duration + "<br/>" +
                String.format("%.2f", booking.calcPenaltyFee()) + "<br/>" +
                String.format("%.2f", booking.calcMemberDiscount()) + "<br/>" +
                String.format("%.2f", booking.getTotalPrice()) + "</p></html>");
        priceLabels.setHorizontalAlignment(JLabel.RIGHT);

        GUI.JLabelSetup(new JLabel[]{receiptLabel, textLabels, priceLabels});

        JPanel receiptPanel = new JPanel(new GridBagLayout());
        receiptPanel.setBackground(Color.white);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        receiptPanel.add(receiptLabel, constraints);

        constraints.gridy = 1;
        constraints.gridwidth = 1;
        receiptPanel.add(textLabels, constraints);

        constraints.gridx = 2;
        constraints.anchor = GridBagConstraints.EAST;
        receiptPanel.add(priceLabels, constraints);

        printButton = new JButton("PRINT");
        printButton.addActionListener(this);
        GUI.JButtonSetup(printButton);

        if (isAdmin){
            adminReceiptPanel.removeAll();

            GridBagConstraints receiptConstraints = new GridBagConstraints();
            receiptConstraints.gridy = 0;
            adminReceiptPanel.add(receiptPanel, receiptConstraints);

            receiptConstraints.gridy = 1;
            receiptConstraints.insets = new Insets(40,40,40,40);
            adminReceiptPanel.add(printButton, receiptConstraints);
            showBookingPanel(adminReceiptPanel);
        }
        else {
            customerReceiptPanel.removeAll();

            GridBagConstraints receiptConstraints = new GridBagConstraints();
            receiptConstraints.gridy = 0;
            customerReceiptPanel.add(receiptPanel, receiptConstraints);

            receiptConstraints.gridy = 1;
            receiptConstraints.insets = new Insets(40,40,40,40);
            customerReceiptPanel.add(printButton, receiptConstraints);

            showBookingPanel(customerReceiptPanel);
        }
    }

    private void printReceipt(){
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
            Date date = new Date();
            String fileName = "receipt_" + dateFormat.format(date) + ".html";

            FileWriter fw = new FileWriter(fileName);

            fw.write(receiptText);

            fw.close();

            JOptionPane.showMessageDialog(this, "Receipt printed successfully!", "Receipt Printed", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IOException ioException){
            GUI.playSound("ReflectYourself.wav");
            System.out.println("Unable to open file. Please try again.");
            ioException.printStackTrace();
        }
        catch (Exception e){
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Unexpected error occurred! Please try again later.", "Registration Approval Failed", JOptionPane.WARNING_MESSAGE);
        }
    }
}
