import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.lang.Math.ceil;
import static java.lang.Math.max;

public class CarFunctions extends JPanel implements ActionListener{

    private boolean isAdmin;

    // Admin functions
    private JPanel addCarAttributesPanel, editCarAttributesPanel, adminSearchCarAttributesPanel, adminSearchResultsPanel;
    private static JPanel addCarPanel, editCarPanel, adminSearchCarPanel, adminViewCarPanel;
    private JButton confirmAdd, cancelAdd, adminSearchButton, OKButton, adminBackToSearch, editButton, deleteButton;
    private JLabel numberPlateLabel, brandLabel, modelLabel, colorLabel, levelLabel, priceLabel;
    private JLabel numberPlateEditLabel, brandEditLabel, modelEditLabel, colorEditLabel, levelEditLabel,
            priceEditLabel, availabilityEditLabel;
    private JLabel numberPlateSearchLabel, brandSearchLabel, modelSearchLabel, colorSearchLabel,
            levelSearchLabel, priceSearchLabel, priceSearchIndicator, availabilitySearchLabel;
    private JTextField numberPlate, brand, model, color, price;
    private JTextField numberPlateEdit, brandEdit, modelEdit, colorEdit, priceEdit;
    private JTextField numberPlateSearch, brandSearch, modelSearch;
    private JSpinner level, levelEdit, numberSpinner;
    private JRadioButton available, notAvailable;
    private ButtonGroup availability;
    private JSlider priceSearchSlider;
    private JComboBox<String> colorSearchBox, levelSearchBox, availabilitySearchBox;
    private static JTable allCarTable;
    private JScrollPane searchTableScroll;
    private JButton[] adminCarButtons;
    private static JPanel[] adminPanels;
    private JLabel[] carLabels, editCarLabels, searchCarLabels;
    private JComponent[] components;

    // Customer functions
    private JPanel customerSearchCarAttributesPanel, customerSearchResultsPanel, customerBookingPanel;
    private static JPanel customerSearchCarPanel, customerViewCarPanel;
    private JButton customerSearchButton, customerBackToSearch, customerBookButton;
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
        String[] colorType = {"Any", "Black", "White", "Silver", "Red", "Blue", "Yellow"};
        String[] levelType = {"Any", "1", "2", "3"};
        String[] availabilityType = {"Any", "Available", "Unavailable"};

        double maxPrice = 0.0;
        for (Car c : FileIO.carList) {
            maxPrice = max(maxPrice, c.getPrice());
        }
        int maxPriceInInt = (int) ceil(maxPrice);

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

            //Create input fields
            //JTextField
            numberPlate = new JTextField(20);
            brand = new JTextField(20);
            model = new JTextField(20);
            color = new JTextField(20);
            price = new JTextField(20);
            numberPlateEdit = new JTextField(20);
            brandEdit = new JTextField(20);
            modelEdit = new JTextField(20);
            colorEdit = new JTextField(20);
            priceEdit = new JTextField(20);
            numberPlateSearch = new JTextField(10);
            brandSearch = new JTextField(10);
            modelSearch = new JTextField(10);

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
            colorSearchBox = new JComboBox<>(colorType);
            colorSearchBox.setFont(GUI.getDefaultFont());
            levelSearchBox = new JComboBox<>(levelType);
            levelSearchBox.setFont(GUI.getDefaultFont());
            availabilitySearchBox = new JComboBox<>(availabilityType);
            availabilitySearchBox.setFont(GUI.getDefaultFont());

            //JSlider
            priceSearchSlider = new JSlider(JSlider.HORIZONTAL, 10, maxPriceInInt, maxPriceInInt);
            priceSearchSlider.setMajorTickSpacing(maxPriceInInt/5);
            priceSearchSlider.setMinorTickSpacing(maxPriceInInt/10);
            priceSearchSlider.setFont(GUI.getDefaultFont());
            priceSearchSlider.setPaintTicks(true);
            priceSearchSlider.setPaintLabels(true);

            priceSearchIndicator.setText(String.valueOf(maxPriceInInt));
            priceSearchSlider.addChangeListener(changeEvent -> priceSearchIndicator.setText(String.valueOf(priceSearchSlider.getValue())));

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
            adminSearchCarPanel = new JPanel(new GridBagLayout());
            adminSearchCarAttributesPanel = new JPanel(new GridBagLayout());
            adminSearchCarAttributesPanel.setBackground(Color.white);
            adminSearchResultsPanel = new JPanel(new BorderLayout());
            adminSearchResultsPanel.setBackground(Color.white);
            adminSearchResultsPanel.setPreferredSize(new Dimension(500, 300));

            //Position components in the search field panel
            GridBagConstraints searchAttributeConstraints = new GridBagConstraints();
            searchAttributeConstraints.fill = GridBagConstraints.HORIZONTAL;
            searchAttributeConstraints.insets = new Insets(2,2,2,2);
            searchAttributeConstraints.gridy = 0;
            searchAttributeConstraints.gridx = 0;
            searchAttributeConstraints.weightx = 0.1;
            adminSearchCarAttributesPanel.add(numberPlateSearchLabel, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 1;
            adminSearchCarAttributesPanel.add(numberPlateSearch, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 2;
            adminSearchCarAttributesPanel.add(brandSearchLabel, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 3;
            adminSearchCarAttributesPanel.add(brandSearch, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 4;
            adminSearchCarAttributesPanel.add(modelSearchLabel, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 5;
            adminSearchCarAttributesPanel.add(modelSearch, searchAttributeConstraints);

            searchAttributeConstraints.gridy = 1;
            searchAttributeConstraints.gridx = 0;
            adminSearchCarAttributesPanel.add(levelSearchLabel, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 1;
            adminSearchCarAttributesPanel.add(levelSearchBox, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 2;
            adminSearchCarAttributesPanel.add(colorSearchLabel, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 3;
            adminSearchCarAttributesPanel.add(colorSearchBox, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 4;
            adminSearchCarAttributesPanel.add(availabilitySearchLabel, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 5;
            adminSearchCarAttributesPanel.add(availabilitySearchBox, searchAttributeConstraints);

            searchAttributeConstraints.gridy = 2;
            searchAttributeConstraints.gridx = 0;
            adminSearchCarAttributesPanel.add(priceSearchLabel, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 1;
            searchAttributeConstraints.gridwidth = 3;
            adminSearchCarAttributesPanel.add(priceSearchSlider, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 4;
            searchAttributeConstraints.gridwidth = 1;
            adminSearchCarAttributesPanel.add(priceSearchIndicator, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 5;
            adminSearchCarAttributesPanel.add(adminSearchButton, searchAttributeConstraints);

            GridBagConstraints adminSearchConstraints = new GridBagConstraints();
            adminSearchConstraints.fill = GridBagConstraints.BOTH;
            adminSearchConstraints.gridx = 0;
            adminSearchConstraints.gridy = 0;
            adminSearchConstraints.weightx = 1;
            adminSearchCarPanel.add(adminSearchCarAttributesPanel, adminSearchConstraints);

            adminSearchConstraints.gridx = 0;
            adminSearchConstraints.gridy = 1;
            adminSearchConstraints.insets = new Insets(10,10,10,10);
            adminSearchCarPanel.add(adminSearchResultsPanel, adminSearchConstraints);

            //View car panel
            adminViewCarPanel = new JPanel(new GridBagLayout());

            //Create car functions panel
            adminPanels = new JPanel[]{addCarPanel, editCarPanel, adminSearchCarPanel, adminViewCarPanel};
            GUI.JPanelSetup(adminPanels);
            add(addCarPanel);
            add(editCarPanel);
            add(adminSearchCarPanel);
            add(adminViewCarPanel);
        }

        else {
            //Create buttons
            customerSearchButton = new JButton("SEARCH");
            customerBackToSearch = new JButton("BACK");
            customerSearchButton.addActionListener(this);
            customerBackToSearch.addActionListener(this);
            customerCarButtons = new JButton[]{customerSearchButton, customerBackToSearch};
            GUI.subJButtonSetup(customerCarButtons, new Dimension(100, 40));

            //Create labels
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

            //Create input fields
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

            startDateMonth = new JComboBox<>(month);
            endDateMonth = new JComboBox<>(month);
            startDateMonth.setFont(GUI.getDefaultFont());
            endDateMonth.setFont(GUI.getDefaultFont());
            startDateYear = new JComboBox<>(year);
            endDateYear = new JComboBox<>(year);
            startDateYear.setFont(GUI.getDefaultFont());
            endDateYear.setFont(GUI.getDefaultFont());

            //JSlider
            priceSearchSlider = new JSlider(JSlider.HORIZONTAL, 10, maxPriceInInt, maxPriceInInt);
            priceSearchSlider.setMajorTickSpacing(maxPriceInInt/5);
            priceSearchSlider.setMinorTickSpacing(maxPriceInInt/10);
            priceSearchSlider.setFont(GUI.getDefaultFont());
            priceSearchSlider.setPaintTicks(true);
            priceSearchSlider.setPaintLabels(true);

            priceSearchIndicator.setText(String.valueOf(maxPriceInInt));
            priceSearchSlider.addChangeListener(changeEvent -> priceSearchIndicator.setText(String.valueOf(priceSearchSlider.getValue())));

            //JSpinner
            startDateDay = new JSpinner(new SpinnerNumberModel(0,0,31,1));
            endDateDay = new JSpinner(new SpinnerNumberModel(0,0,31,1));

            //Search car panel
            customerSearchCarPanel = new JPanel(new GridBagLayout());
            customerSearchCarAttributesPanel = new JPanel(new GridBagLayout());
            customerSearchCarAttributesPanel.setBackground(Color.white);
            customerSearchResultsPanel = new JPanel(new BorderLayout());
            customerSearchResultsPanel.setBackground(Color.white);
            customerSearchResultsPanel.setPreferredSize(new Dimension(500, 300));

            //Position components in the search field panel
            GridBagConstraints searchAttributeConstraints = new GridBagConstraints();
            searchAttributeConstraints.fill = GridBagConstraints.HORIZONTAL;
            searchAttributeConstraints.insets = new Insets(2,2,2,2);
            searchAttributeConstraints.gridy = 0;
            searchAttributeConstraints.gridx = 0;
            searchAttributeConstraints.weightx = 0.1;
            customerSearchCarAttributesPanel.add(numberPlateSearchLabel, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 1;
            customerSearchCarAttributesPanel.add(numberPlateSearch, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 2;
            customerSearchCarAttributesPanel.add(brandSearchLabel, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 3;
            customerSearchCarAttributesPanel.add(brandSearch, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 4;
            customerSearchCarAttributesPanel.add(modelSearchLabel, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 5;
            customerSearchCarAttributesPanel.add(modelSearch, searchAttributeConstraints);

            searchAttributeConstraints.gridy = 1;
            searchAttributeConstraints.gridx = 0;
            customerSearchCarAttributesPanel.add(levelSearchLabel, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 1;
            customerSearchCarAttributesPanel.add(levelSearchBox, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 2;
            customerSearchCarAttributesPanel.add(colorSearchLabel, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 3;
            customerSearchCarAttributesPanel.add(colorSearchBox, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 4;
            customerSearchCarAttributesPanel.add(availabilitySearchLabel, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 5;
            customerSearchCarAttributesPanel.add(availabilitySearchBox, searchAttributeConstraints);

            searchAttributeConstraints.gridy = 2;
            searchAttributeConstraints.gridx = 0;
            customerSearchCarAttributesPanel.add(priceSearchLabel, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 1;
            searchAttributeConstraints.gridwidth = 3;
            customerSearchCarAttributesPanel.add(priceSearchSlider, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 4;
            searchAttributeConstraints.gridwidth = 1;
            customerSearchCarAttributesPanel.add(priceSearchIndicator, searchAttributeConstraints);

            searchAttributeConstraints.gridx = 5;
            customerSearchCarAttributesPanel.add(customerSearchButton, searchAttributeConstraints);

            GridBagConstraints searchConstraints = new GridBagConstraints();
            searchConstraints.fill = GridBagConstraints.BOTH;
            searchConstraints.gridx = 0;
            searchConstraints.gridy = 0;
            searchConstraints.weightx = 1;
            customerSearchCarPanel.add(customerSearchCarAttributesPanel, searchConstraints);

            searchConstraints.gridx = 0;
            searchConstraints.gridy = 1;
            searchConstraints.insets = new Insets(10,10,10,10);
            customerSearchCarPanel.add(customerSearchResultsPanel, searchConstraints);

            //Book car panel
            customerBookingPanel = new JPanel(new GridBagLayout());

            //View car panel
            customerViewCarPanel = new JPanel(new GridBagLayout());

            //Create car functions panel
            customerPanels = new JPanel[]{customerSearchCarPanel, customerViewCarPanel, customerBookingPanel};
            GUI.JPanelSetup(customerPanels);
            add(customerSearchCarPanel);
            add(customerViewCarPanel);
            add(customerBookingPanel);

        }

        setPreferredSize(new Dimension(600,500));

    }

    public static void showAddCarPanel() {
        showAdminCarPanel(addCarPanel);
    }

    public static void showAdminSearchCarPanel() {
        showAdminCarPanel(adminSearchCarPanel);
    }

    public static void showAdminAllCarPanel() {
        adminViewCarPanel.removeAll();
        viewAllCar();
        showAdminCarPanel(adminViewCarPanel);
    }

    public static void showCustomerSearchCarPanel() {
        showCustomerCarPanel(customerSearchCarPanel);
    }

    public static void showCustomerAllCarPanel() {
        customerViewCarPanel.removeAll();
//        viewAllCar();
        showCustomerCarPanel(customerViewCarPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == confirmAdd) {
                addCar();
            }
            else if (e.getSource() == cancelAdd) {
                clearAddCarField();
            }
            else if (e.getSource() == adminSearchButton){
                searchCar(true);
            }
            else if (e.getSource() == editButton){
                if((int) numberSpinner.getValue() == 0){
                    throw new CarNotFoundException();
                }
                showAdminCarPanel(editCarPanel);
                showCarDetails();
            }
            else if (e.getSource() == OKButton){
                GUI.playSound("DontSayFiveDe.wav");
                String input = JOptionPane.showInputDialog("Type \"CONFIRM\" to proceed!");
                if (input != null && input.equals("CONFIRM")){
                    editCarDetails();
                }
                else {
                    JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Edit canceled!");
                }
            }
            else if (e.getSource() == deleteButton){
                GUI.playSound("DontSayFiveDe.wav");
                if((int) numberSpinner.getValue() == 0){
                    throw new CarNotFoundException();
                }

                String input = JOptionPane.showInputDialog("Type \"DELETE\" to confirm the deletion!");
                if (input != null && input.equals("DELETE")){
                    int numberValue = (int) numberSpinner.getValue();

                    Car.deleteCar(numberValue);
                    JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Car has been deleted!");
                }
                else {
                    JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Deletion canceled!");
                }
            }
            else if (e.getSource() == adminBackToSearch){
                showAdminCarPanel(adminSearchCarPanel);
            }
            else if (e.getSource() == customerSearchButton){
                searchCar(false);
            }
            else if (e.getSource() == customerBookButton){
                showCustomerCarPanel(customerBookingPanel);
            }
            else if (e.getSource() == customerBackToSearch){
                showCustomerCarPanel(customerSearchCarPanel);
            }
        }
        catch (CarNotFoundException carNotFoundException){
            GUI.playSound("NormalVoice.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Please select a row number to edit!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        catch (Exception exception){
            exception.printStackTrace();
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Something wrong");
        }
    }

    public void clearAddCarField(){
        numberPlate.setText("");
        brand.setText("");
        model.setText("");
        color.setText("");
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

    public void addCar(){
        String numberPlateInput = numberPlate.getText();
        String brandInput = brand.getText().toUpperCase();
        String modelInput = model.getText().toUpperCase();
        String colorInput = color.getText().toUpperCase();
        int levelInput = (int) level.getValue();
        String priceInput = price.getText();

        Car.addCar(numberPlateInput, brandInput, modelInput, colorInput, levelInput, priceInput);
        clearAddCarField();
    }

    public void searchCar(boolean isAdmin){
        String numberPlate;
        String brand;
        String model;
        String color;
        String level;
        double price;
        String availability;

        if (isAdmin){
            adminSearchResultsPanel.removeAll();

            numberPlate = numberPlateSearch.getText();
            brand = brandSearch.getText();
            model = modelSearch.getText();
            color = (String) colorSearchBox.getSelectedItem();
            level = (String) levelSearchBox.getSelectedItem();
            price = Double.parseDouble(priceSearchIndicator.getText());
            availability = (String) availabilitySearchBox.getSelectedItem();
        }
        else {
            customerSearchResultsPanel.removeAll();

            numberPlate = numberPlateSearch.getText();
            brand = brandSearch.getText();
            model = modelSearch.getText();
            color = (String) colorSearchBox.getSelectedItem();
            level = (String) levelSearchBox.getSelectedItem();
            price = Double.parseDouble(priceSearchIndicator.getText());
            availability = (String) availabilitySearchBox.getSelectedItem();
        }

        ArrayList<Car> searchedList = Car.searchCar(numberPlate, brand, model, color.toUpperCase(), level, price, availability);

        if (searchedList.size() == 0){
            if (isAdmin) {
                JLabel carNotFoundLabel = new JLabel("No cars found!");
                carNotFoundLabel.setHorizontalAlignment(JLabel.CENTER);
                adminSearchResultsPanel.add(carNotFoundLabel);
                carNotFoundLabel.setVisible(true);
            } else {
                JLabel carNotFoundLabel = new JLabel("No cars found!");
                carNotFoundLabel.setHorizontalAlignment(JLabel.CENTER);
                customerSearchResultsPanel.add(carNotFoundLabel);
                carNotFoundLabel.setVisible(true);
            }

            if (searchTableScroll != null){
                searchTableScroll.setVisible(false);
            }
        }
        else {
            if (isAdmin){
                adminSearchCar(searchedList);
            } else {
                customerSearchCar(searchedList);
            }
        }
    }

    public void adminSearchCar(ArrayList<Car> searchedList){
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

        adminSearchResultsPanel.add(searchTableScroll, BorderLayout.CENTER);

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

        adminSearchResultsPanel.add(bottomPanel, BorderLayout.SOUTH);

        adminSearchResultsPanel.setVisible(true);
        adminSearchResultsPanel.validate();

        adminSearchCarPanel.validate();
    }

    public void customerSearchCar(ArrayList<Car> searchedList){
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

        customerSearchResultsPanel.add(searchTableScroll, BorderLayout.CENTER);

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

        customerBookButton = new JButton("BOOK");
        customerBookButton.addActionListener(this);
        JButton[] buttons = new JButton[]{customerBookButton};
        GUI.subJButtonSetup(buttons, new Dimension(100,30));

        bottomConstraints.gridx = 3;
        bottomPanel.add(customerBookButton, bottomConstraints);

        customerSearchResultsPanel.add(bottomPanel, BorderLayout.SOUTH);

        customerSearchResultsPanel.setVisible(true);
        customerSearchResultsPanel.validate();

        customerSearchCarPanel.validate();
    }

    public void showCarDetails(){
        int numberValue = (int) numberSpinner.getValue();
        Car car = FileIO.carList.get(numberValue - 1);
        GUI.resetFields(components);
        levelEdit.setValue(1);

        numberPlateEdit.setText(car.getNumberPlate());
        brandEdit.setText(car.getBrand());
        modelEdit.setText(car.getModel());
        colorEdit.setText(car.getColor());
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
        String colorInput = colorEdit.getText().toUpperCase();
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
        adminViewCarPanel.add(carScrollPane, constraints);

        adminViewCarPanel.validate();
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

    public static void showCustomerCarPanel(JPanel panel){
        for (JPanel i : customerPanels) {
            i.setVisible(false);
        }
        panel.setVisible(true);
    }

}
