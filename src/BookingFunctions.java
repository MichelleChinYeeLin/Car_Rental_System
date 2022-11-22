import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static java.lang.Math.max;
import static java.lang.Math.ceil;

public class BookingFunctions extends JPanel implements ActionListener {

    private static JPanel searchBookingPanel, searchBookingAttributesPanel, searchBookingResultsPanel, editBookingPanel, editBookingAttributesPanel, viewBookingPanel;
    private JPanel startDatePanel, endDatePanel, totalPricePanel;
    private JLabel carNumberPlateSearchLabel, customerNameSearchLabel, totalPriceSearchLabel, statusSearchLabel, startDateSearchLabel, endDateSearchLabel;
    private JLabel totalPriceIndicator;
    private JLabel carNumberPlateEditLabel, customerNameEditLabel, totalPriceEditLabel, statusEditLabel, startDateEditLabel, endDateEditLabel;
    private JTextField carNumberPlateSearch, customerNameSearch;
    private JTextField carNumberPlateEdit, customerNameEdit, totalPriceEdit;
    private JSlider totalPriceSlider;
    private JSpinner startDateDaySearch, endDateDaySearch, numberSpinner;
    private JSpinner startDateDayEdit, endDateDayEdit;
    private JComboBox<String> startDateYearSearch, endDateYearSearch;
    private JComboBox<String> startDateYearEdit, endDateYearEdit;
    private JComboBox<Booking.Status> statusBoxSearch;
    private JComboBox<Booking.Status> statusBoxEdit;
    private JComboBox<Booking.Month> startDateMonthSearch, endDateMonthSearch;
    private JComboBox<Booking.Month> startDateMonthEdit, endDateMonthEdit;
    private JButton searchButton, editButton, deleteButton, OKButton, backToSearchButton;
    private JScrollPane searchTableScroll;
    private static JPanel[] panels;
    private static JTable allBookingTable;
    public BookingFunctions(){

        //Search Booking Panel
        //Create panels
        searchBookingPanel = new JPanel(new GridBagLayout());
        viewBookingPanel = new JPanel(new GridBagLayout());
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
        totalPriceSearchLabel = new JLabel("Total Price:");
        statusSearchLabel = new JLabel("Status:");
        startDateSearchLabel = new JLabel("Start Date:");
        endDateSearchLabel = new JLabel("End Date:");
        totalPriceIndicator = new JLabel();
        JLabel labels[] = {carNumberPlateSearchLabel, customerNameSearchLabel, totalPriceSearchLabel, statusSearchLabel, startDateSearchLabel, endDateSearchLabel, totalPriceIndicator};
        GUI.JLabelSetup(labels);

        //Create input fields
        //JTextField
        carNumberPlateSearch = new JTextField(5);
        customerNameSearch = new JTextField(10);

        //JSlider
        double maxTotalPrice = 0.0;
        for (Booking booking : FileIO.getBookingList()) {
            maxTotalPrice = max(maxTotalPrice, booking.getTotalPrice());
        }
        int maxTotalPriceinInt = (int) ceil(maxTotalPrice);
        totalPriceSlider = new JSlider(JSlider.HORIZONTAL, 10, maxTotalPriceinInt, maxTotalPriceinInt);
        totalPriceSlider.setMajorTickSpacing(maxTotalPriceinInt/5);
        totalPriceSlider.setMinorTickSpacing(maxTotalPriceinInt/10);
        totalPriceSlider.setFont(GUI.getDefaultFont());
        totalPriceSlider.setPaintTicks(true);
        totalPriceSlider.setPaintLabels(true);
        totalPriceSlider.setPreferredSize(new Dimension(150, 50));

        totalPriceIndicator.setText(String.valueOf(maxTotalPriceinInt));

        totalPriceSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                totalPriceIndicator.setText(String.valueOf(totalPriceSlider.getValue()));
            }
        });

        totalPricePanel.add(totalPriceSlider);
        totalPricePanel.add(totalPriceIndicator);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();

        startDateDaySearch = new JSpinner(new SpinnerNumberModel(0,0,31,1));
        endDateDaySearch = new JSpinner(new SpinnerNumberModel(0,0,31,1));

        Booking.Status[] statusType = Booking.Status.values();
        statusBoxSearch = new JComboBox<>(statusType);
        statusBoxSearch.setFont(GUI.getDefaultFont());

        Booking.Month[] month = Booking.Month.values();
        startDateMonthSearch = new JComboBox<>(month);
        endDateMonthSearch = new JComboBox<>(month);
        startDateMonthSearch.setFont(GUI.getDefaultFont());
        endDateMonthSearch.setFont(GUI.getDefaultFont());

        String[] year = {"ANY", String.valueOf(gregorianCalendar.get(Calendar.YEAR)), String.valueOf(gregorianCalendar.get(Calendar.YEAR) + 1)};
        startDateYearSearch = new JComboBox<>(year);
        endDateYearSearch = new JComboBox<>(year);
        startDateYearSearch.setFont(GUI.getDefaultFont());
        endDateYearSearch.setFont(GUI.getDefaultFont());

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

        searchAttributesConstraints.gridx = 2;
        searchBookingAttributesPanel.add(customerNameSearch, searchAttributesConstraints);

        searchAttributesConstraints.gridx = 3;
        searchBookingAttributesPanel.add(carNumberPlateSearchLabel, searchAttributesConstraints);

        searchAttributesConstraints.gridx = 4;
        searchBookingAttributesPanel.add(carNumberPlateSearch, searchAttributesConstraints);

        searchAttributesConstraints.gridx = 5;
        searchBookingAttributesPanel.add(statusSearchLabel, searchAttributesConstraints);

        searchAttributesConstraints.gridx = 6;
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

        searchAttributesConstraints.gridx = 6;
        searchAttributesConstraints.gridwidth = 1;
        searchBookingAttributesPanel.add(searchButton, searchAttributesConstraints);

        searchBookingAttributesPanel.setBackground(Color.white);

        GridBagConstraints searchConstraints = new GridBagConstraints();
        searchConstraints.fill = GridBagConstraints.BOTH;
        searchConstraints.gridx = 0;
        searchConstraints.gridy = 0;
        searchConstraints.weightx = 1;
        searchBookingPanel.add(searchBookingAttributesPanel, searchConstraints);

        searchConstraints.gridx = 0;
        searchConstraints.gridy = 1;
        searchConstraints.insets = new Insets(10,10,10,10);
        searchBookingPanel.add(searchBookingResultsPanel, searchConstraints);

        //Edit Booking Panel
        editBookingPanel = new JPanel(new GridBagLayout());
        editBookingAttributesPanel = new JPanel(new GridBagLayout());
        carNumberPlateEditLabel = new JLabel("Car Number Plate:");
        customerNameEditLabel = new JLabel("Customer Username:");
        totalPriceEditLabel = new JLabel("Total Price:");
        statusEditLabel = new JLabel("Status:");
        startDateEditLabel = new JLabel("Start Date:");
        endDateEditLabel = new JLabel("End Date:");
        JLabel editBookingLabels[] = {carNumberPlateEditLabel, customerNameEditLabel, totalPriceEditLabel, statusEditLabel, startDateEditLabel, endDateEditLabel};
        GUI.JLabelSetup(editBookingLabels);

        carNumberPlateEdit = new JTextField(20);
        customerNameEdit = new JTextField(20);
        totalPriceEdit = new JTextField(20);
        totalPriceEdit.setEnabled(false);

        statusBoxEdit = new JComboBox<>(statusType);
        statusBoxEdit.setFont(GUI.getDefaultFont());

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
        startDateEditPanel.add(startDateDayEdit);
        startDateEditPanel.add(startDateMonthEdit);
        startDateEditPanel.add(startDateYearEdit);

        JPanel endDateEditPanel = new JPanel();
        endDateEditPanel.add(endDateDayEdit);
        endDateEditPanel.add(endDateMonthEdit);
        endDateEditPanel.add(endDateYearEdit);

        GridBagConstraints editAttributesConstraints = new GridBagConstraints();
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
        editBookingAttributesPanel.add(statusBoxEdit, editAttributesConstraints);

        editAttributesConstraints.gridy = 4;
        editBookingAttributesPanel.add(startDateEditPanel, editAttributesConstraints);

        editAttributesConstraints.gridy = 5;
        editBookingAttributesPanel.add(endDateEditPanel, editAttributesConstraints);

        JPanel editBookingSelectionPanel = new JPanel(new FlowLayout());
        editBookingSelectionPanel.setBackground(Color.white);

        OKButton = new JButton("OK");
        backToSearchButton = new JButton("BACK");
        GUI.JButtonSetup(new JButton[]{OKButton, backToSearchButton});
        OKButton.addActionListener(this);
        backToSearchButton.addActionListener(this);
        editBookingSelectionPanel.add(OKButton);
        editBookingSelectionPanel.add(backToSearchButton);

        GridBagConstraints editConstraints = new GridBagConstraints();
        editConstraints.gridy = 0;
        editBookingPanel.add(editBookingAttributesPanel, editConstraints);
        editConstraints.gridy = 1;
        editBookingPanel.add(editBookingSelectionPanel, editConstraints);

        panels = new JPanel[]{searchBookingPanel, viewBookingPanel, editBookingPanel};
        GUI.JPanelSetup(panels);
        setPreferredSize(new Dimension(600,500));
        add(searchBookingPanel);
        add(viewBookingPanel);
        add(editBookingPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == searchButton) {
                searchBooking();
            }
            else if (e.getSource() == editButton) {
                if ((int) numberSpinner.getValue() == 0) {
                    throw new BookingNotFoundException();
                }
                showBookingPanel(editBookingPanel);
                showBookingDetails();
            }
            else if (e.getSource() == deleteButton) {
                if ((int) numberSpinner.getValue() == 0) {
                    throw new BookingNotFoundException();
                }

                String input = JOptionPane.showInputDialog("Type \"DELETE\" to confirm the deletion!");
                if (input != null && input.equals("DELETE")) {
                    int numberValue = (int) numberSpinner.getValue();

                    Booking.deleteBooking(numberValue);
                    JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Car has been deleted!");
                    searchBooking();
                } else {
                    JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Deletion canceled!");
                }
            }
            else if (e.getSource() == OKButton){
                String input = JOptionPane.showInputDialog("Type \"CONFIRM\" to proceed!");
                if (input != null && input.equals("CONFIRM")){
                    editBookingDetails();
                    showBookingPanel(searchBookingPanel);
                    searchBooking();
                }
                else {
                    JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Edit canceled!");
                }
            }
            else if (e.getSource() == backToSearchButton){
                showBookingPanel(searchBookingPanel);
            }
        }
        catch (BookingNotFoundException bookingNotFoundException){
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Invalid booking selected! Please try again.", "Invalid Booking", JOptionPane.WARNING_MESSAGE);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Something wrong");
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
        for (JPanel i : panels) {
            i.setVisible(false);
        }
        panel.setVisible(true);
    }

    private void showBookingDetails(){
        int numberValue = (int) numberSpinner.getValue();
        Booking booking = FileIO.bookingList.get(numberValue - 1);

        carNumberPlateEdit.setText(booking.getCar().getNumberPlate());
        customerNameEdit.setText(booking.getCustomer().getUsername());
        totalPriceEdit.setText(String.valueOf(booking.getTotalPrice()));
        statusBoxEdit.setSelectedItem(booking.getStatus());

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

    private void editBookingDetails(){
        int numberValue = (int) numberSpinner.getValue();
        String carNumberPlate = carNumberPlateEdit.getText();
        String customerName = customerNameEdit.getText();
        Booking.Status status = (Booking.Status) statusBoxEdit.getSelectedItem();

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

            Booking.editBookingDetails(numberValue, carNumberPlate, customerName, status, startDate, endDate);
        }
        catch (InvalidDateDurationException invalidDateDurationException){
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Invalid date! Please try again.", "Invalid Date Input", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void searchBooking(){
        searchBookingResultsPanel.removeAll();

        String numberPlate = carNumberPlateSearch.getText();
        String customerName = customerNameSearch.getText();
        double totalPrice = Double.parseDouble(totalPriceIndicator.getText());
        Booking.Status status = (Booking.Status) statusBoxSearch.getSelectedItem();

        int startDay = (int) startDateDaySearch.getValue();
        Booking.Month startMonth = (Booking.Month) startDateMonthSearch.getSelectedItem();
        String startYear = (String) startDateYearSearch.getSelectedItem();

        int endDay = (int) endDateDaySearch.getValue();
        Booking.Month endMonth = (Booking.Month) endDateMonthSearch.getSelectedItem();
        String endYear = (String) endDateYearSearch.getSelectedItem();

        ArrayList<Booking> searchedList = Booking.searchBooking(numberPlate, customerName, totalPrice, status, startDay, startMonth, startYear, endDay, endMonth, endYear);

        if (searchedList.size() == 0){
            JLabel bookingNotFoundLabel = new JLabel("Booking not found!");
            bookingNotFoundLabel.setHorizontalAlignment(JLabel.CENTER);
            searchBookingResultsPanel.add(bookingNotFoundLabel);
            bookingNotFoundLabel.setVisible(true);

            if (searchTableScroll != null){
                searchTableScroll.setVisible(false);
            }
        }
        else{
            String[] tableColumn = {"No.", "Car No. Plate", "Customer Username", "Total Price", "Status", "Start Date", "End Date"};
            Object[][] tempTable = new Object[searchedList.size()][tableColumn.length];
            int i = 0;
            for (Booking booking : searchedList){
                i = insertBookingTable(tempTable, i, booking);
            }

            JTable searchTable = new JTable(tempTable, tableColumn);

            searchTableScroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            searchTableScroll.setViewportView(searchTable);
            searchTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            searchTableScroll.setVisible(true);
            searchTableScroll.setPreferredSize(new Dimension(500, 225));
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

            searchBookingResultsPanel.add(bottomPanel, BorderLayout.SOUTH);

            searchBookingResultsPanel.setVisible(true);
        }

        searchBookingResultsPanel.updateUI();
        searchBookingResultsPanel.validate();
        searchBookingPanel.validate();
    }

    private static void viewAllBooking(){
        String[] tableColumn = {"No.", "Car No. Plate", "Customer Username", "Total Price", "Status", "Start Date", "End Date"};
        Object[][] tempTable = new Object[FileIO.bookingList.size()][tableColumn.length];
        int i = 0;
        for (Booking booking: FileIO.bookingList){
            i = insertBookingTable(tempTable, i, booking);
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

    private static int insertBookingTable(Object[][] tempTable, int i, Booking booking) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        tempTable[i][0] = i + 1;
        tempTable[i][1] = booking.getCar().getNumberPlate();
        tempTable[i][2] = booking.getCustomer().getUsername();
        tempTable[i][3] = booking.getTotalPrice();
        tempTable[i][4] = booking.getStatus();
        tempTable[i][5] = dateFormat.format(booking.getStartDate());
        tempTable[i][6] = dateFormat.format(booking.getEndDate());
        i++;
        return i;
    }
}
