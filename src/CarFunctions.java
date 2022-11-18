import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.lang.Math.ceil;
import static java.lang.Math.max;

public class CarFunctions extends JPanel implements ActionListener{

    private JPanel addCarAttributesPanel, editCarAttributesPanel, searchCarAttributesPanel;
    private static JPanel addCarPanel, editCarPanel, searchCarPanel, viewCarPanel;
    private JPanel searchResultsPanel;
    private JButton confirmAdd, cancelAdd, searchButton, OKButton, backToSearch, editButton, deleteButton;
    private JLabel numberPlateLabel, brandLabel, modelLabel, colorLabel, levelLabel, priceLabel;
    private JLabel numberPlateEditLabel, brandEditLabel, modelEditLabel, colorEditLabel, levelEditLabel, priceEditLabel, availabilityEditLabel;
    private JLabel numberPlateSearchLabel, brandSearchLabel, modelSearchLabel, colorSearchLabel, levelSearchLabel, priceSearchLabel, priceSearchIndicator, availabilitySearchLabel;
    private JLabel carNotFoundLabel;
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
    private JButton[] carButtons;
    private static JPanel[] panels;
    private JLabel[] carLabels, editCarLabels, searchCarLabels;
    private JComponent[] components;

    public CarFunctions(){

        //Create buttons
        confirmAdd = new JButton("ADD");
        cancelAdd = new JButton("CANCEL");
        searchButton = new JButton("SEARCH");
        OKButton = new JButton("OK");
        backToSearch = new JButton("BACK");
        carButtons = new JButton[]{confirmAdd, cancelAdd, searchButton, OKButton, backToSearch};
        confirmAdd.addActionListener(this);
        cancelAdd.addActionListener(this);
        searchButton.addActionListener(this);
        OKButton.addActionListener(this);
        backToSearch.addActionListener(this);
        GUI.subJButtonSetup(carButtons, new Dimension(100, 40));

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
        priceSearchIndicator = new JLabel("10");
        availabilitySearchLabel = new JLabel("Availability:");
//        carNotFoundLabel = new JLabel("No cars found!");
//        JLabel[] searchCarLabels = {numberPlateSearchLabel, brandSearchLabel, modelSearchLabel, colorSearchLabel, levelSearchLabel, priceSearchLabel, priceSearchIndicator, availabilitySearchLabel, carNotFoundLabel};
        searchCarLabels = new JLabel[]{numberPlateSearchLabel, brandSearchLabel, modelSearchLabel, colorSearchLabel, levelSearchLabel, priceSearchLabel, priceSearchIndicator, availabilitySearchLabel};
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
        String[] colorType = {"Any", "Black", "White", "Silver", "Red", "Blue", "Yellow"};
        String[] levelType = {"Any", "1", "2", "3"};
        String[] availabilityType = {"Any", "Available", "Unavailable"};
        colorSearchBox = new JComboBox<>(colorType);
        colorSearchBox.setFont(GUI.getDefaultFont());
        levelSearchBox = new JComboBox<>(levelType);
        levelSearchBox.setFont(GUI.getDefaultFont());
        availabilitySearchBox = new JComboBox<>(availabilityType);
        availabilitySearchBox.setFont(GUI.getDefaultFont());

        //JSlider
        double maxPrice = 0.0;
        for (Car c : FileIO.carList) {
            maxPrice = max(maxPrice, c.getPrice());
        }
        int maxPriceInInt = (int) ceil(maxPrice);
        priceSearchSlider = new JSlider(JSlider.HORIZONTAL, 10, maxPriceInInt, 10);
        priceSearchSlider.setMajorTickSpacing(maxPriceInInt/5);
        priceSearchSlider.setMinorTickSpacing(maxPriceInInt/10);
        priceSearchSlider.setFont(GUI.getDefaultFont());
        priceSearchSlider.setPaintTicks(true);
        priceSearchSlider.setPaintLabels(true);
        priceSearchSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                priceSearchIndicator.setText(String.valueOf(priceSearchSlider.getValue()));
            }
        });

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
        editCarSelectionPanel.add(backToSearch);

        carConstraints.gridy = 0;
        editCarPanel.add(editCarAttributesPanel, carConstraints);
        carConstraints.gridy = 1;
        editCarPanel.add(editCarSelectionPanel, carConstraints);

        //Search car panel
        searchCarPanel = new JPanel(new GridBagLayout());
        searchCarAttributesPanel = new JPanel(new GridBagLayout());
        searchCarAttributesPanel.setBackground(Color.white);
        searchResultsPanel = new JPanel(new BorderLayout());
        searchResultsPanel.setBackground(Color.white);
        searchResultsPanel.setPreferredSize(new Dimension(500, 300));

        //JLabels
        numberPlateSearchLabel = new JLabel("No. Plate:");
        brandSearchLabel = new JLabel("Brand:");
        modelSearchLabel = new JLabel("Model:");
        colorSearchLabel = new JLabel("Color:");
        levelSearchLabel = new JLabel("Level:");
        priceSearchLabel = new JLabel("Price:");
        priceSearchIndicator = new JLabel("200");
        availabilitySearchLabel = new JLabel("Availability:");
        carNotFoundLabel = new JLabel("No cars found!");
        JLabel[] searchLabels = {numberPlateSearchLabel, brandSearchLabel, modelSearchLabel, colorSearchLabel, levelSearchLabel, priceSearchLabel, priceSearchIndicator, availabilitySearchLabel, carNotFoundLabel};
//        JLabel[] searchLabels = {numberPlateSearchLabel, brandSearchLabel, modelSearchLabel, colorSearchLabel, levelSearchLabel, priceSearchLabel, priceSearchIndicator, availabilitySearchLabel};
        GUI.JLabelSetup(searchLabels);

        //JTextFields
        numberPlateSearch = new JTextField(10);
        brandSearch = new JTextField(10);
        modelSearch = new JTextField(10);

        //JSlider
        priceSearchSlider = new JSlider(JSlider.HORIZONTAL, 10, 200, 200);
        priceSearchSlider.setMajorTickSpacing(50);
        priceSearchSlider.setMinorTickSpacing(10);
        priceSearchSlider.setFont(GUI.getDefaultFont());
        priceSearchSlider.setPaintTicks(true);
        priceSearchSlider.setPaintLabels(true);
        priceSearchSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                priceSearchIndicator.setText(String.valueOf(priceSearchSlider.getValue()));
            }
        });

        //JComboBox
        String[] colorType = {"Any", "Black", "White", "Silver", "Red", "Blue", "Yellow"};
        String[] levelType = {"Any", "1", "2", "3"};
        String[] availabilityType = {"Any", "Available", "Unavailable"};
        colorSearchBox = new JComboBox<>(colorType);
        colorSearchBox.setFont(GUI.getDefaultFont());
        levelSearchBox = new JComboBox<>(levelType);
        levelSearchBox.setFont(GUI.getDefaultFont());
        availabilitySearchBox = new JComboBox<>(availabilityType);
        availabilitySearchBox.setFont(GUI.getDefaultFont());

        //JButton
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);

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

        searchAttributeConstraints.gridx = 5;
        searchCarAttributesPanel.add(searchButton, searchAttributeConstraints);

        GridBagConstraints searchConstraints = new GridBagConstraints();
        searchConstraints.fill = GridBagConstraints.BOTH;
        searchConstraints.gridx = 0;
        searchConstraints.gridy = 0;
        searchConstraints.weightx = 1;
        searchCarPanel.add(searchCarAttributesPanel, searchConstraints);

        carNotFoundLabel.setVisible(false);
        carNotFoundLabel.setHorizontalAlignment(JLabel.CENTER);
        searchResultsPanel.add(carNotFoundLabel);

        searchConstraints.gridx = 0;
        searchConstraints.gridy = 1;
        searchConstraints.insets = new Insets(10,10,10,10);
        searchCarPanel.add(searchResultsPanel, searchConstraints);

        //View car panel
        viewCarPanel = new JPanel(new GridBagLayout());

        //Create car functions panel
        panels = new JPanel[]{addCarPanel, editCarPanel, searchCarPanel, viewCarPanel};
        GUI.JPanelSetup(panels);
        setPreferredSize(new Dimension(600,500));
        add(addCarPanel);
        add(editCarPanel);
        add(searchCarPanel);
        add(viewCarPanel);
    }

    public static void showAddCarPanel() {
        showCarPanel(addCarPanel);
    }

    public static void showSearchCarPanel() {
        showCarPanel(searchCarPanel);
    }

    public static void showAllCarPanel() {
        viewCarPanel.removeAll();
        viewAllCar();
        showCarPanel(viewCarPanel);
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
            else if (e.getSource() == searchButton){
                searchCar();
            }
            else if (e.getSource() == editButton){
                if((int) numberSpinner.getValue() == 0){
                    throw new CarNotFoundException();
                }
                showCarPanel(editCarPanel);
                showCarDetails();
            }
            else if (e.getSource() == OKButton){
                String input = JOptionPane.showInputDialog("Type \"CONFIRM\" to proceed!");
                if (input != null && input.equals("CONFIRM")){
                    editCarDetails();
                }
                else {
                    JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Edit canceled!");
                }
            }
            else if (e.getSource() == deleteButton){
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
            else if (e.getSource() == backToSearch){
                showCarPanel(searchCarPanel);
            }
        }
        catch (CarNotFoundException carNotFoundException){
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Please select a row number to edit!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        catch (Exception exception){
            exception.printStackTrace();
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Something wrong");
        }
    }

    private void clearAddCarField(){
        numberPlate.setText("");
        brand.setText("");
        model.setText("");
        color.setText("");
        price.setText("");
        level.setValue(1);
        available.setSelected(false);
        notAvailable.setSelected(false);
    }

    private static void showCarPanel(JPanel panel){
        for (JPanel i : panels) {
            i.setVisible(false);
        }
        panel.setVisible(true);
    }

    private void addCar(){
        String numberPlateInput = numberPlate.getText();
        String brandInput = brand.getText().toUpperCase();
        String modelInput = model.getText().toUpperCase();
        String colorInput = color.getText().toUpperCase();
        int levelInput = (int) level.getValue();
        String priceInput = price.getText();

        Car.addCar(numberPlateInput, brandInput, modelInput, colorInput, levelInput, priceInput);
        clearAddCarField();
    }

    private void searchCar(){
        String numberPlate = numberPlateSearch.getText();
        String brand = brandSearch.getText();
        String model = modelSearch.getText();
        String color = (String) colorSearchBox.getSelectedItem();
        String level = (String) levelSearchBox.getSelectedItem();
        double price = Double.parseDouble(priceSearchIndicator.getText());
        String availability = (String) availabilitySearchBox.getSelectedItem();

        ArrayList<Car> searchedList = Car.searchCar(numberPlate, brand, model, color.toUpperCase(), level, price, availability);

        if (searchedList.size() == 0){
            carNotFoundLabel.setVisible(true);

            if (searchTableScroll != null){
                searchTableScroll.setVisible(false);
            }
        }
        else {
            carNotFoundLabel.setVisible(false);
            String[] tableColumn = {"No.", "No. Plate", "Brand", "Model", "Color", "Level", "Price", "Availability"};
            Object[][] tempTable = new Object[searchedList.size()][8];
            int i = 0;
            for (Car car : searchedList){
                i = insertCarTable(tempTable, i, car);
            }

            JTable searchTable = new JTable(tempTable, tableColumn);

            searchTableScroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            searchResultsPanel.add(searchTableScroll, BorderLayout.CENTER);
            searchTableScroll.setViewportView(searchTable);
            searchTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            searchTableScroll.setVisible(true);

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

            searchResultsPanel.add(bottomPanel, BorderLayout.SOUTH);

            searchResultsPanel.setVisible(true);
            searchResultsPanel.validate();

        }

        searchCarPanel.validate();
    }

    private void showCarDetails(){
        int numberValue = (int) numberSpinner.getValue();
        Car car = FileIO.carList.get(numberValue - 1);
        AccountFunctions.resetFields(components);
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

    private void editCarDetails(){
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

    private static void viewAllCar(){
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

    private static int insertCarTable(Object[][] tempTable, int i, Car car) {
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
}
