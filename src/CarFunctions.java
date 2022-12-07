import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CarFunctions extends JPanel implements ActionListener{

    private boolean isAdmin;

    // Search car
    private JPanel searchResultsPanel;
    private JLabel numberPlateSearchLabel, brandSearchLabel, modelSearchLabel, colorSearchLabel,
            levelSearchLabel, priceSearchLabel, priceSearchIndicator, availabilitySearchLabel;
    private JTextField numberPlateSearch, brandSearch, modelSearch;
    private JComboBox<Car.Color> colorSearchBox;
    private JComboBox<String> levelSearchBox, availabilitySearchBox;
    private static JSlider priceSearchSlider;
    private static JPanel searchCarPanel;
    private JPanel searchCarAttributesPanel;
    private static ArrayList<Car> searchedList;

    // Admin
    private JPanel addCarAttributesPanel, editCarAttributesPanel;
    private static JPanel addCarPanel, editCarPanel, viewCarPanel;
    private JButton confirmAdd, cancelAdd, adminSearchButton, OKButton, adminBackToSearch, editButton, deleteButton;
    private JLabel numberPlateLabel, brandLabel, modelLabel, colorLabel, levelLabel, priceLabel;
    private JLabel numberPlateEditLabel, brandEditLabel, modelEditLabel, colorEditLabel, levelEditLabel,
            priceEditLabel, availabilityEditLabel;
    private JTextField numberPlate, brand, model, price;
    private JTextField numberPlateEdit, brandEdit, modelEdit, priceEdit;
    private JComboBox<Car.Color> color, colorEdit;
    private JSpinner level, levelEdit, numberSpinner;
    private JRadioButton available, notAvailable;
    private ButtonGroup availability;
    private static JTable allCarTable;
    private JScrollPane searchTableScroll;
    private JButton[] adminCarButtons;
    private static JPanel[] adminPanels;
    private JLabel[] carLabels, editCarLabels, searchCarLabels;
    private JComponent[] components;

    // Customer
    private JPanel createBookingPanel, bookingAttributesPanel;
    private JButton customerSearchButton, customerBackToSearch, customerBookButton, confirmBooking;
    private JLabel carNumberPlateLabel, startDateLabel, endDateLabel, carDetails;
    private JComboBox<String> startDateYear, endDateYear;
    private JComboBox<Booking.Month> startDateMonth, endDateMonth;
    private JSpinner startDateDay, endDateDay;
    private JButton[] customerCarButtons;
    private static JPanel[] customerPanels;


    public CarFunctions(boolean isAdmin){

        this.isAdmin = isAdmin;

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        Booking.Month[] month = Booking.Month.values();
        String[] year = {"ANY", String.valueOf(gregorianCalendar.get(Calendar.YEAR)), String.valueOf(gregorianCalendar.get(Calendar.YEAR) + 1)};
        Car.Color[] colorType = Car.Color.values();
        String[] levelType = {"Any", "1", "2", "3"};
        String[] availabilityType = {"Any", "Available", "Unavailable"};

        /* Search car */
        // Create labels
        numberPlateSearchLabel = new JLabel("No. Plate:");
        brandSearchLabel = new JLabel("Brand:");
        modelSearchLabel = new JLabel("Model:");
        colorSearchLabel = new JLabel("Color:");
        levelSearchLabel = new JLabel("Level:");
        priceSearchLabel = new JLabel("Price:");
        priceSearchIndicator = new JLabel();
        availabilitySearchLabel = new JLabel("Availability:");
        searchCarLabels = new JLabel[]{numberPlateSearchLabel, brandSearchLabel, modelSearchLabel, colorSearchLabel, levelSearchLabel,
                priceSearchLabel, priceSearchIndicator, availabilitySearchLabel};
        GUI.JLabelSetup(searchCarLabels);

        // Create input fields
        //JTextField
        numberPlateSearch = new JTextField(10);
        brandSearch = new JTextField(10);
        modelSearch = new JTextField(10);

        //JComboBox
        colorSearchBox = new JComboBox<>(colorType);
        colorSearchBox.setFont(GUI.getDefaultFont());
        levelSearchBox = new JComboBox<>(levelType);
        levelSearchBox.setFont(GUI.getDefaultFont());
        availabilitySearchBox = new JComboBox<>(availabilityType);
        availabilitySearchBox.setFont(GUI.getDefaultFont());

        //JSlider
        int maxPriceInInt = Car.calcMaxPrice();
        priceSearchSlider = new JSlider(JSlider.HORIZONTAL, 10, maxPriceInInt, maxPriceInInt);
        GUI.JSliderSetup(priceSearchSlider, true);

        priceSearchIndicator.setText(String.valueOf(maxPriceInInt));
        priceSearchSlider.addChangeListener(changeEvent -> priceSearchIndicator.setText(String.valueOf(priceSearchSlider.getValue())));

        searchResultsPanel = new JPanel(new BorderLayout());
        searchResultsPanel.setBackground(Color.white);
        searchResultsPanel.setPreferredSize(new Dimension(500, 300));

        //Search car panel
        searchCarPanel = new JPanel(new GridBagLayout());
        searchCarAttributesPanel = new JPanel(new GridBagLayout());
        searchCarAttributesPanel.setBackground(Color.white);

        //Position components in the search field panel
        GridBagConstraints searchAttributeConstraints = new GridBagConstraints();
        searchAttributeConstraints.fill = GridBagConstraints.HORIZONTAL;
        searchAttributeConstraints.insets = new Insets(2,2,2,2);
        searchAttributeConstraints.gridy = 0;
        searchAttributeConstraints.gridx = 0;
        searchAttributeConstraints.weightx = 0.1;
        searchCarAttributesPanel.add(numberPlateSearchLabel, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 1;
        searchCarAttributesPanel.add(numberPlateSearch, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 2;
        searchCarAttributesPanel.add(brandSearchLabel, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 3;
        searchCarAttributesPanel.add(brandSearch, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 4;
        searchCarAttributesPanel.add(modelSearchLabel, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 5;
        searchCarAttributesPanel.add(modelSearch, searchAttributeConstraints);

        searchAttributeConstraints.gridy = 1;
        searchAttributeConstraints.gridx = 0;
        searchCarAttributesPanel.add(levelSearchLabel, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 1;
        searchCarAttributesPanel.add(levelSearchBox, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 2;
        searchCarAttributesPanel.add(colorSearchLabel, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 3;
        searchCarAttributesPanel.add(colorSearchBox, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 4;
        searchCarAttributesPanel.add(availabilitySearchLabel, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 5;
        searchCarAttributesPanel.add(availabilitySearchBox, searchAttributeConstraints);

        searchAttributeConstraints.gridy = 2;
        searchAttributeConstraints.gridx = 0;
        searchCarAttributesPanel.add(priceSearchLabel, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 1;
        searchAttributeConstraints.gridwidth = 3;
        searchCarAttributesPanel.add(priceSearchSlider, searchAttributeConstraints);

        searchAttributeConstraints.gridx = 4;
        searchAttributeConstraints.gridwidth = 1;
        searchCarAttributesPanel.add(priceSearchIndicator, searchAttributeConstraints);

        GridBagConstraints searchConstraints = new GridBagConstraints();
        searchConstraints.fill = GridBagConstraints.BOTH;
        searchConstraints.gridx = 0;
        searchConstraints.gridy = 0;
        searchConstraints.weightx = 1;
        searchCarPanel.add(searchCarAttributesPanel, searchConstraints);

        searchConstraints.gridx = 0;
        searchConstraints.gridy = 1;
        searchConstraints.insets = new Insets(10,10,10,10);
        searchCarPanel.add(searchResultsPanel, searchConstraints);

        if (isAdmin){
            //Create buttons
            confirmAdd = new JButton("ADD");
            cancelAdd = new JButton("CANCEL");
            adminSearchButton = new JButton("SEARCH");
            OKButton = new JButton("OK");
            adminBackToSearch = new JButton("BACK");
            adminCarButtons = new JButton[]{confirmAdd, cancelAdd, adminSearchButton, OKButton, adminBackToSearch};
            confirmAdd.addActionListener(this);
            cancelAdd.addActionListener(this);
            adminSearchButton.addActionListener(this);
            OKButton.addActionListener(this);
            adminBackToSearch.addActionListener(this);
            GUI.subJButtonSetup(adminCarButtons, new Dimension(100, 40));

            //Create labels
            numberPlateLabel = new JLabel("Number Plate:");
            brandLabel = new JLabel("Brand:");
            modelLabel = new JLabel("Model:");
            colorLabel = new JLabel("Color:");
            levelLabel = new JLabel("Level:");
            priceLabel = new JLabel("Price:");
            carLabels = new JLabel[]{numberPlateLabel, brandLabel, modelLabel, colorLabel, levelLabel, priceLabel};
            GUI.JLabelSetup(carLabels);

            numberPlateEditLabel = new JLabel("Number Plate:");
            brandEditLabel = new JLabel("Brand:");
            modelEditLabel = new JLabel("Model:");
            colorEditLabel = new JLabel("Color:");
            levelEditLabel = new JLabel("Level:");
            priceEditLabel = new JLabel("Price:");
            availabilityEditLabel = new JLabel("Availability:");
            editCarLabels = new JLabel[]{numberPlateEditLabel, brandEditLabel, modelEditLabel, colorEditLabel,
                    levelEditLabel, priceEditLabel, availabilityEditLabel};
            GUI.JLabelSetup(editCarLabels);

            //Create input fields
            //JTextField
            numberPlate = new JTextField(20);
            brand = new JTextField(20);
            model = new JTextField(20);
            price = new JTextField(20);
            numberPlateEdit = new JTextField(20);
            brandEdit = new JTextField(20);
            modelEdit = new JTextField(20);
            priceEdit = new JTextField(20);

            //JSpinner
            level = new JSpinner(new SpinnerNumberModel(1,1,3,1));
            levelEdit = new JSpinner(new SpinnerNumberModel(1,1,3,1));

            //JRadioButton
            available = new JRadioButton("YES");
            notAvailable = new JRadioButton("NO");
            availability = new ButtonGroup();

            available.setFocusable(false);
            notAvailable.setFocusable(false);
            availability.add(available);
            availability.add(notAvailable);

            //JComboBox
            color = new JComboBox<>(colorType);
            color.setFont(GUI.getDefaultFont());
            colorEdit = new JComboBox<>(colorType);
            colorEdit.setFont(GUI.getDefaultFont());

            //JComponent array
            components = new JComponent[]{numberPlateEdit, brandEdit, modelEdit, colorEdit,
                    priceEdit, available, notAvailable};

            //ADD Car Attributes Panel & EDIT Car Attributes Panel
            GridBagConstraints carConstraints = new GridBagConstraints();
            addCarAttributesPanel = new JPanel(new GridBagLayout());
            addCarAttributesPanel.setBackground(Color.white);
            editCarAttributesPanel = new JPanel(new GridBagLayout());
            editCarAttributesPanel.setBackground(Color.white);

            //Setup labels
            carConstraints.insets = new Insets(10,5,10,5);
            carConstraints.weightx = 1;
            carConstraints.weighty = 1;
            carConstraints.gridx = 0;
            carConstraints.ipady = 0;
            carConstraints.gridwidth = 2;
            for(int i = 0; i < carLabels.length; i++){

                if(carLabels[i].getText().equals("Level:")){
                    carConstraints.gridwidth = 1;
                }
                carConstraints.gridy = i + 1;
                addCarAttributesPanel.add(carLabels[i], carConstraints);
            }
            for(int i = 0; i < editCarLabels.length; i++){

                if(editCarLabels[i].getText().equals("Level:")){
                    carConstraints.gridwidth = 1;
                }
                carConstraints.gridy = i + 1;
                editCarAttributesPanel.add(editCarLabels[i], carConstraints);
            }

            //Setup fields
            carConstraints.gridx = 2;
            carConstraints.gridy = 1;
            addCarAttributesPanel.add(numberPlate, carConstraints);
            editCarAttributesPanel.add(numberPlateEdit, carConstraints);

            carConstraints.gridy = 2;
            addCarAttributesPanel.add(brand, carConstraints);
            editCarAttributesPanel.add(brandEdit, carConstraints);

            carConstraints.gridy = 3;
            addCarAttributesPanel.add(model, carConstraints);
            editCarAttributesPanel.add(modelEdit, carConstraints);

            carConstraints.gridy = 4;
            addCarAttributesPanel.add(color, carConstraints);
            editCarAttributesPanel.add(colorEdit, carConstraints);

            carConstraints.gridy = 5;
            addCarAttributesPanel.add(level, carConstraints);
            editCarAttributesPanel.add(levelEdit, carConstraints);

            carConstraints.gridy = 6;
            addCarAttributesPanel.add(price, carConstraints);
            editCarAttributesPanel.add(priceEdit, carConstraints);

            carConstraints.gridy = 7;
            JPanel availabilityPanel = new JPanel(new FlowLayout());
            availabilityPanel.setBackground(Color.white);
            availabilityPanel.add(available);
            availabilityPanel.add(notAvailable);
            editCarAttributesPanel.add(availabilityPanel, carConstraints);

            //Add car panel
            addCarPanel = new JPanel(new GridBagLayout());
            JPanel addCarSelectionPanel = new JPanel(new FlowLayout());
            addCarSelectionPanel.setBackground(Color.white);
            addCarSelectionPanel.add(confirmAdd);
            addCarSelectionPanel.add(cancelAdd);

            carConstraints.gridy = 0;
            addCarPanel.add(addCarAttributesPanel, carConstraints);
            carConstraints.gridy = 1;
            addCarPanel.add(addCarSelectionPanel, carConstraints);

            //Edit car panel
            editCarPanel = new JPanel(new GridBagLayout());
            JPanel editCarSelectionPanel = new JPanel(new FlowLayout());
            editCarSelectionPanel.setBackground(Color.white);
            editCarSelectionPanel.add(OKButton);
            editCarSelectionPanel.add(adminBackToSearch);

            carConstraints.gridy = 0;
            editCarPanel.add(editCarAttributesPanel, carConstraints);
            carConstraints.gridy = 1;
            editCarPanel.add(editCarSelectionPanel, carConstraints);

            //Search car panel
            searchAttributeConstraints.gridx = 5;
            searchCarAttributesPanel.add(adminSearchButton, searchAttributeConstraints);

            //View car panel
            viewCarPanel = new JPanel(new GridBagLayout());

            //Create car functions panel
            adminPanels = new JPanel[]{addCarPanel, editCarPanel, searchCarPanel, viewCarPanel};
            GUI.JPanelSetup(adminPanels);
            add(addCarPanel);
            add(editCarPanel);
            add(searchCarPanel);
            add(viewCarPanel);
        }

        else {
            //Create buttons
            customerSearchButton = new JButton("SEARCH");
            customerBackToSearch = new JButton("BACK");
            confirmBooking = new JButton("CONFIRM");
            customerSearchButton.addActionListener(this);
            customerBackToSearch.addActionListener(this);
            confirmBooking.addActionListener(this);
            customerCarButtons = new JButton[]{customerSearchButton, customerBackToSearch, confirmBooking};
            GUI.subJButtonSetup(customerCarButtons, new Dimension(100, 40));
            confirmBooking.setPreferredSize(new Dimension(120, 40));

            //Create labels
            carNumberPlateLabel = new JLabel("Car Selected:");
            startDateLabel = new JLabel("Start Date:");
            endDateLabel = new JLabel("End Date:");
            JLabel[] createBookingLabels = {carNumberPlateLabel, startDateLabel, endDateLabel};
            GUI.JLabelSetup(createBookingLabels);

            //Create input fields
            //JComboBox
            startDateMonth = new JComboBox<>(month);
            endDateMonth = new JComboBox<>(month);
            startDateMonth.setFont(GUI.getDefaultFont());
            endDateMonth.setFont(GUI.getDefaultFont());
            startDateYear = new JComboBox<>(year);
            endDateYear = new JComboBox<>(year);
            startDateYear.setFont(GUI.getDefaultFont());
            endDateYear.setFont(GUI.getDefaultFont());

            //JSpinner
            startDateDay = new JSpinner(new SpinnerNumberModel(0,0,31,1));
            endDateDay = new JSpinner(new SpinnerNumberModel(0,0,31,1));

            //Search car panel
            searchAttributeConstraints.gridx = 5;
            searchCarAttributesPanel.add(customerSearchButton, searchAttributeConstraints);

            //Book car panel
            createBookingPanel = new JPanel(new GridBagLayout());
            bookingAttributesPanel = new JPanel(new GridBagLayout());
            bookingAttributesPanel.setBackground(Color.white);

            JPanel startDateEditPanel = new JPanel();
            startDateEditPanel.setBackground(Color.white);
            startDateEditPanel.add(startDateDay);
            startDateEditPanel.add(startDateMonth);
            startDateEditPanel.add(startDateYear);

            JPanel endDateEditPanel = new JPanel();
            endDateEditPanel.setBackground(Color.white);
            endDateEditPanel.add(endDateDay);
            endDateEditPanel.add(endDateMonth);
            endDateEditPanel.add(endDateYear);

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets = new Insets(20,2,20,2);
            constraints.gridx = 0;
            for(int i = 0; i < createBookingLabels.length; i++){
                constraints.gridy = i;
                bookingAttributesPanel.add(createBookingLabels[i], constraints);
            }

            carDetails = new JLabel("");
            GUI.JLabelSetup(carDetails);

            constraints.gridx = 1;
            constraints.gridy = 0;
            bookingAttributesPanel.add(carDetails, constraints);

            constraints.gridy = 1;
            bookingAttributesPanel.add(startDateEditPanel, constraints);

            constraints.gridy = 2;
            bookingAttributesPanel.add(endDateEditPanel, constraints);

            JPanel editBookingSelectionPanel = new JPanel(new FlowLayout());
            editBookingSelectionPanel.setBackground(Color.white);
            editBookingSelectionPanel.add(confirmBooking);
            editBookingSelectionPanel.add(customerBackToSearch);

            GridBagConstraints bkgConstraints = new GridBagConstraints();
            bkgConstraints.insets = new Insets(10,2,20,2);
            bkgConstraints.gridx = 0;
            bkgConstraints.gridy = 0;
            createBookingPanel.add(bookingAttributesPanel, bkgConstraints);
            bkgConstraints.gridy = 1;
            createBookingPanel.add(editBookingSelectionPanel, bkgConstraints);

            //View car panel
            viewCarPanel = new JPanel(new GridBagLayout());

            //Create car functions panel
            customerPanels = new JPanel[]{searchCarPanel, viewCarPanel, createBookingPanel};
            GUI.JPanelSetup(customerPanels);
            add(searchCarPanel);
            add(viewCarPanel);
            add(createBookingPanel);

        }

        setPreferredSize(new Dimension(600,500));

    }

    public static ArrayList<Car> getSearchedList(){
        return searchedList;
    }

    public static JSlider getPriceSearchSlider() {
        return priceSearchSlider;
    }

    public static void showAddCarPanel() {
        showAdminCarPanel(addCarPanel);
    }

    public static void showAdminSearchCarPanel() {
        showAdminCarPanel(searchCarPanel);
    }

    public static void showCustomerSearchCarPanel() {
        showCustomerCarPanel(searchCarPanel);
    }

    public static void showAllCarPanel(boolean isAdmin) {
        viewCarPanel.removeAll();
        viewAllCar();
        if (isAdmin) showAdminCarPanel(viewCarPanel);
        else showCustomerCarPanel(viewCarPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == confirmAdd) {
                GUI.playSound("ji.wav");
                addCar();
            }
            else if (e.getSource() == cancelAdd) {
                GUI.playSound("ji.wav");
                clearAddCarField();
            }
            else if (e.getSource() == adminSearchButton){
                GUI.playSound("ji.wav");
                searchCar(true);
            }
            else if (e.getSource() == editButton){
                if((int) numberSpinner.getValue() == 0){
                    throw new CarNotFoundException();
                }
                GUI.playSound("ji.wav");
                showAdminCarPanel(editCarPanel);
                showCarDetails();
            }
            else if (e.getSource() == OKButton){
                GUI.playSound("ji.wav");
                String input = JOptionPane.showInputDialog("Type \"CONFIRM\" to proceed!");
                if (input != null && input.equals("CONFIRM")){
                    editCarDetails();
                }
                else {
                    GUI.playSound("ElectricVoice.wav");
                    JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Edit canceled!");
                }
            }
            else if (e.getSource() == deleteButton){
                if((int) numberSpinner.getValue() == 0){
                    throw new CarNotFoundException();
                }
                GUI.playSound("DontSayFiveDe.wav");

                String input = JOptionPane.showInputDialog("Type \"DELETE\" to confirm the deletion!");
                if (input != null && input.equals("DELETE")){
                    int numberValue = (int) numberSpinner.getValue();

                    Car.deleteCar(numberValue);
                    JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Car has been deleted!");
                    searchCar(true);
                }
                else {
                    GUI.playSound("ElectricVoice.wav");
                    JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Deletion canceled!");
                }
            }
            else if (e.getSource() == adminBackToSearch){
                GUI.playSound("ji.wav");
                showAdminCarPanel(searchCarPanel);
                searchCar(true);
            }
            else if (e.getSource() == customerSearchButton){
                GUI.playSound("ji.wav");
                searchCar(false);
            }
            else if (e.getSource() == customerBookButton){
                int numberValue = (int) numberSpinner.getValue();
                if(numberValue == 0){
                    throw new CarNotFoundException();
                }
                GUI.playSound("ji.wav");

                Car toBook = searchedList.get(numberValue -1);
                if (validateQualification(toBook)){
                    showCustomerCarPanel(createBookingPanel);
                    carDetails.setText(toBook.getNumberPlate() + " | " + toBook.getBrand() + " | " + toBook.getModel());
                }
            }
            else if (e.getSource() == confirmBooking){
                int numberValue = (int) numberSpinner.getValue();
                Car toBook = searchedList.get(numberValue -1);
                int startDay = (int) startDateDay.getValue();
                Booking.Month startMonth = (Booking.Month) startDateMonth.getSelectedItem();
                String startYear = (String) startDateYear.getSelectedItem();

                int endDay = (int) endDateDay.getValue();
                Booking.Month endMonth = (Booking.Month) endDateMonth.getSelectedItem();
                String endYear = (String) endDateYear.getSelectedItem();

                if(!Booking.isValidDate(startDay, startMonth, startYear) || !Booking.isValidDate(endDay, endMonth, endYear)){
                    throw new InvalidDateDurationException();
                }

                Date startDate = Booking.convertToDate(startDay, startMonth, startYear);
                Date endDate = Booking.convertToDate(endDay, endMonth, endYear);

                if (Booking.addBooking(toBook, CarRentalSystem.loginCustomer, startDate, endDate)){
                    GUI.playSound("DontSayFiveDe.wav");
                    JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Booking success!");
                    showCustomerCarPanel(searchCarPanel);
                    searchCar(false);
                }
            }
            else if (e.getSource() == customerBackToSearch){
                GUI.playSound("ji.wav");
                showCustomerCarPanel(searchCarPanel);
            }
        }
        catch (CarNotFoundException carNotFoundException){
            GUI.playSound("NormalVoice.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Please select a row number to edit!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        catch (InvalidDateDurationException invalidDateDurationException){
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Invalid date! Please try again.", "Invalid Date Input", JOptionPane.WARNING_MESSAGE);
        }
        catch (Exception exception){
            exception.printStackTrace();
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Unexpected error occurred! Please try again later.", "Registration Approval Failed", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void clearAddCarField(){
        numberPlate.setText("");
        brand.setText("");
        model.setText("");
        color.setSelectedIndex(0);
        price.setText("");
        level.setValue(1);
        available.setSelected(false);
        notAvailable.setSelected(false);
    }

    public static void showAdminCarPanel(JPanel panel){
        for (JPanel i : adminPanels) {
            i.setVisible(false);
        }
        panel.setVisible(true);
    }

    public static void showCustomerCarPanel(JPanel panel){
        for (JPanel i : customerPanels) {
            i.setVisible(false);
        }
        panel.setVisible(true);
    }

    public void addCar(){
        String numberPlateInput = numberPlate.getText();
        String brandInput = brand.getText().toUpperCase();
        String modelInput = model.getText().toUpperCase();
        Car.Color colorInput = (Car.Color) color.getSelectedItem();
        int levelInput = (int) level.getValue();
        String priceInput = price.getText();

        Car.addCar(numberPlateInput, brandInput, modelInput, colorInput, levelInput, priceInput);
        clearAddCarField();
    }

    public void searchCar(boolean isAdmin){
        searchResultsPanel.removeAll();

        String numberPlate = numberPlateSearch.getText();
        String brand = brandSearch.getText();
        String model = modelSearch.getText();
        Car.Color color = (Car.Color) colorSearchBox.getSelectedItem();
        String level = String.valueOf(levelSearchBox.getSelectedItem());
        double price = Double.parseDouble(priceSearchIndicator.getText());
        String availability = String.valueOf(availabilitySearchBox.getSelectedItem());

        searchedList = Car.searchCar(numberPlate, brand, model, color, level, price, availability);

        if (searchedList.size() == 0){
            JLabel carNotFoundLabel = new JLabel("No cars found!");
            carNotFoundLabel.setFont(GUI.getDefaultFont());
            carNotFoundLabel.setHorizontalAlignment(JLabel.CENTER);
            if (isAdmin) {
                searchResultsPanel.add(carNotFoundLabel);
            } else {
                searchResultsPanel.add(carNotFoundLabel);
            }
            carNotFoundLabel.setVisible(true);

            if (searchTableScroll != null){
                searchTableScroll.setVisible(false);
            }
        }
        else {
            String[] tableColumn = {"No.", "No. Plate", "Brand", "Model", "Color", "Level", "Price", "Availability"};
            Object[][] tempTable = new Object[searchedList.size()][tableColumn.length];
            int i = 0;
            for (Car car : searchedList){
                i = insertCarTable(tempTable, i, car);
            }

            JTable searchTable = new JTable(tempTable, tableColumn);

            searchTableScroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            searchTableScroll.setViewportView(searchTable);
            searchTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            searchTableScroll.setVisible(true);

            searchResultsPanel.add(searchTableScroll, BorderLayout.CENTER);

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

            if (isAdmin){
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
            } else {
                customerBookButton = new JButton("BOOK");
                customerBookButton.addActionListener(this);
                JButton[] buttons = new JButton[]{customerBookButton};
                GUI.subJButtonSetup(buttons, new Dimension(100,30));

                bottomConstraints.gridx = 3;
                bottomPanel.add(customerBookButton, bottomConstraints);
            }
            searchResultsPanel.add(bottomPanel, BorderLayout.SOUTH);
        }

        searchResultsPanel.setVisible(true);
        searchResultsPanel.validate();
    }

    public void showCarDetails(){
        int numberValue = (int) numberSpinner.getValue();
        Car car = searchedList.get(numberValue - 1);
        GUI.resetFields(components);
        levelEdit.setValue(1);

        numberPlateEdit.setText(car.getNumberPlate());
        brandEdit.setText(car.getBrand());
        modelEdit.setText(car.getModel());
        colorEdit.setSelectedItem(car.getColor());
        levelEdit.setValue(car.getLevel());
        priceEdit.setText(String.valueOf(car.getPrice()));
        if (car.isAvailability()){
            available.setSelected(true);
        }
        else {
            notAvailable.setSelected(true);
        }
    }

    public void editCarDetails(){
        int numberValue = (int) numberSpinner.getValue();
        String numberPlateInput = numberPlateEdit.getText();
        String brandInput = brandEdit.getText().toUpperCase();
        String modelInput = modelEdit.getText().toUpperCase();
        Car.Color colorInput = (Car.Color) colorEdit.getSelectedItem();
        int levelInput = (int) levelEdit.getValue();
        String priceInput = priceEdit.getText();
        boolean availability;
        availability = available.isSelected();

        Car.editCarDetails(numberValue, numberPlateInput, brandInput, modelInput, colorInput, levelInput, priceInput, availability);
    }

    public static void viewAllCar(){
        String[] tableColumn = {"No.", "No. Plate", "Brand", "Model", "Color", "Level", "Price", "Availability"};
        Object[][] tempTable = new Object[FileIO.carList.size()][8];
        int i = 0;
        for (Car car : FileIO.carList){
            i = insertCarTable(tempTable, i, car);
        }

        allCarTable = new JTable(tempTable, tableColumn);
        allCarTable.setVisible(true);
        JScrollPane carScrollPane = new JScrollPane(allCarTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        allCarTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        carScrollPane.setPreferredSize(new Dimension(500,420));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(15,5,10,5);
        viewCarPanel.add(carScrollPane, constraints);

        viewCarPanel.validate();
    }

    public static int insertCarTable(Object[][] tempTable, int i, Car car) {
        tempTable[i][0] = i + 1;
        tempTable[i][1] = car.getNumberPlate();
        tempTable[i][2] = car.getBrand();
        tempTable[i][3] = car.getModel();
        tempTable[i][4] = car.getColor();
        tempTable[i][5] = car.getLevel();
        tempTable[i][6] = car.getPrice();
        tempTable[i][7] = car.isAvailability();
        i++;
        return i;
    }

    public boolean validateQualification(Car toBook){
        boolean flag = false;
        int memberLevel = CarRentalSystem.loginCustomer.getMemberLevel();
        int size = CarRentalSystem.loginCustomer.getMyBookings().size();

        try {
            if (!toBook.isAvailability()){
                throw new CarNotFoundException();
            }

            // BANNED Customer
            if (memberLevel == 0){
                throw new InvalidUserException();
            }

            if (memberLevel == -1){
                if (!(toBook.getLevel() == 1)){
                    throw new InvalidBookingException();
                }
            }
            else {
                if (memberLevel < toBook.getLevel()){
                    throw new InvalidPointException();
                }
            }

            if (size != 0){
                if (CarRentalSystem.loginCustomer.getMyBookings().get(size - 1).getStatus() == Booking.Status.BOOKED){
                    throw new ExceedBookingQuantityException();
                }

                if (CarRentalSystem.loginCustomer.getMyBookings().get(size - 1).getStatus() != Booking.Status.COMPLETED){
                    throw new BookingNotCompletedException();
                }
            }

            flag =  true;
        }
        catch (CarNotFoundException carNotFoundException){
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Car is not available!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        catch (InvalidUserException invalidUserException) {
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "You had been banned for booking any car!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        catch (InvalidBookingException invalidBookingException) {
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "You can only book Level-1 car!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        catch (InvalidPointException invalidPointException) {
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "You can't book Level-" + toBook.getLevel() + " car!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        catch (ExceedBookingQuantityException exceedBookingQuantityException) {
            GUI.playSound("ElectricVoice.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "You can only book one car at one time!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        catch (BookingNotCompletedException bookingNotCompletedException) {
            GUI.playSound("NormalVoice.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Your previous booking haven't completed!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        return flag;
    }
}
