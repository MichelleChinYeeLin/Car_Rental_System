import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CarFunctions extends JPanel implements ActionListener{

    private JPanel addCarAttributesPanel, editCarAttributesPanel, searchCarAttributesPanel;
    private static JPanel addCarPanel, editCarPanel, deleteCarPanel, searchCarPanel, viewCarPanel;
    private JButton confirmAdd, cancelAdd, search, confirmEdit, cancelEdit;
    private JButton searchButton;
    private JLabel numberPlateLabel, brandLabel, modelLabel, colorLabel, levelLabel, priceLabel;
    private JLabel numberPlateLabel2, brandLabel2, modelLabel2, colorLabel2, levelLabel2, priceLabel2, availabilityLabel;
    private JLabel numberPlateSearchLabel, brandSearchLabel, modelSearchLabel, colorSearchLabel, levelSearchLabel, priceSearchLabel, priceSearchIndicator, availabilitySearchLabel, carNotFoundLabel;
    private JTextField numberPlate, brand, model, color, price;
    private JTextField numberPlate2, brand2, model2, color2, price2;
    private JTextField numberPlateSearch, brandSearch, modelSearch;
    private JSpinner level, level2;
    private JRadioButton available, notAvailable;
    private ButtonGroup availability;
    private JSlider priceSearchSlider;
    private JComboBox colorSearchBox, levelSearchBox, availabilitySearchBox;
    private JButton[] carButtons;
    private static JPanel[] panels;
    private JLabel[] carLabels, carLabels2;
    private JTable searchTable;

    public CarFunctions(){

        //Create buttons
        confirmAdd = new JButton("ADD");
        cancelAdd = new JButton("CANCEL");
        search = new JButton("SEARCH");
        confirmEdit = new JButton("EDIT");
        cancelEdit = new JButton("CANCEL");
        carButtons = new JButton[]{confirmAdd, cancelAdd, search, confirmEdit, cancelEdit};
        confirmAdd.addActionListener(this);
        cancelAdd.addActionListener(this);
        search.addActionListener(this);
        confirmEdit.addActionListener(this);
        cancelEdit.addActionListener(this);
        GUI.subJButtonSetup(carButtons);

        //Create labels
        numberPlateLabel = new JLabel("Number Plate:");
        numberPlateLabel2 = new JLabel("Number Plate:");
        numberPlateSearchLabel = new JLabel("Number Plate:");
        brandLabel = new JLabel("Brand:");
        brandLabel2 = new JLabel("Brand:");
        modelLabel = new JLabel("Model:");
        modelLabel2 = new JLabel("Model:");
        colorLabel = new JLabel("Color:");
        colorLabel2 = new JLabel("Color:");
        levelLabel = new JLabel("Level:");
        levelLabel2 = new JLabel("Level:");
        priceLabel = new JLabel("Price:");
        priceLabel2 = new JLabel("Price:");
        availabilityLabel = new JLabel("Availability:");
        carLabels = new JLabel[]{numberPlateLabel, brandLabel, modelLabel, colorLabel, levelLabel, priceLabel};
        carLabels2 = new JLabel[]{numberPlateLabel2, brandLabel2, modelLabel2, colorLabel2, levelLabel2, priceLabel2, availabilityLabel};
        GUI.JLabelSetup(carLabels);
        GUI.JLabelSetup(carLabels2);

        //Create input fields
        numberPlate = new JTextField(20);
        numberPlate2 = new JTextField(20);
        brand = new JTextField(20);
        brand2 = new JTextField(20);
        model = new JTextField(20);
        model2 = new JTextField(20);
        color = new JTextField(20);
        color2 = new JTextField(20);
        price = new JTextField(20);
        price2 = new JTextField(20);
        level = new JSpinner(new SpinnerNumberModel(1,1,3,1));
        level2 = new JSpinner(new SpinnerNumberModel(0,0,3,1));
        available = new JRadioButton("YES");
        notAvailable = new JRadioButton("NO");
        availability = new ButtonGroup();

        available.setFocusable(false);
        notAvailable.setFocusable(false);
        availability.add(available);
        availability.add(notAvailable);

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
        for(int i = 0; i < carLabels2.length; i++){

            if(carLabels2[i].getText().equals("Level:")){
                carConstraints.gridwidth = 1;
            }
            carConstraints.gridy = i + 1;
            editCarAttributesPanel.add(carLabels2[i], carConstraints);
        }
        //Setup fields
        carConstraints.gridx = 2;
        carConstraints.gridy = 1;
        addCarAttributesPanel.add(numberPlate, carConstraints);
        editCarAttributesPanel.add(numberPlate2, carConstraints);
        carConstraints.gridy = 2;
        addCarAttributesPanel.add(brand, carConstraints);
        editCarAttributesPanel.add(brand2, carConstraints);
        carConstraints.gridy = 3;
        addCarAttributesPanel.add(model, carConstraints);
        editCarAttributesPanel.add(model2, carConstraints);
        carConstraints.gridy = 4;
        addCarAttributesPanel.add(color, carConstraints);
        editCarAttributesPanel.add(color2, carConstraints);
        carConstraints.gridy = 5;
        addCarAttributesPanel.add(level, carConstraints);
        editCarAttributesPanel.add(level2, carConstraints);
        carConstraints.gridy = 6;
        addCarAttributesPanel.add(price, carConstraints);
        editCarAttributesPanel.add(price2, carConstraints);
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
        editCarSelectionPanel.add(search);
        editCarSelectionPanel.add(confirmEdit);
        editCarSelectionPanel.add(cancelEdit);

        carConstraints.gridy = 0;
        editCarPanel.add(editCarAttributesPanel, carConstraints);
        carConstraints.gridy = 1;
        editCarPanel.add(editCarSelectionPanel, carConstraints);

        //Delete car panel
        deleteCarPanel = new JPanel(new GridBagLayout());

        /* Search car panel */
        //Initialize components
        searchCarPanel = new JPanel(new GridBagLayout());
        searchCarAttributesPanel = new JPanel(new GridBagLayout());
        searchCarAttributesPanel.setBackground(Color.white);

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
        colorSearchBox = new JComboBox<String>(colorType);
        colorSearchBox.setFont(GUI.getDefaultFont());
        levelSearchBox = new JComboBox<String>(levelType);
        levelSearchBox.setFont(GUI.getDefaultFont());
        availabilitySearchBox = new JComboBox<String>(availabilityType);
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
        searchConstraints.fill = GridBagConstraints.HORIZONTAL;
        searchConstraints.gridx = 0;
        searchConstraints.gridy = 0;
        searchCarPanel.add(searchCarAttributesPanel, searchConstraints);

        searchConstraints.gridy = 1;
        searchCarPanel.add(carNotFoundLabel);

        //View car panel
        viewCarPanel = new JPanel(new GridBagLayout());

        //Create car functions panel
        panels = new JPanel[]{addCarPanel, editCarPanel, deleteCarPanel, searchCarPanel, viewCarPanel};
        GUI.JPanelSetup(panels);
        add(addCarPanel);
        add(editCarPanel);
        add(deleteCarPanel);
        add(searchCarPanel);
        add(viewCarPanel);
    }

    public static void showAddCarPanel() {
        showCarPanel(addCarPanel);
    }

    public static void showEditCarPanel() {
        showCarPanel(editCarPanel);
    }

    public static void showDeleteCarPanel() {
        showCarPanel(deleteCarPanel);
    }

    public static void showSearchCarPanel() {
        showCarPanel(searchCarPanel);
    }

    public static void showAllCarPanel() {
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
            else if (e.getSource() == search){
                searchCar();
            }
            else if (e.getSource() == confirmEdit){

            }
            else if (e.getSource() == cancelEdit){

            }
        }
        catch (Exception exception){
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
        try{
            String numberPlateInput = numberPlate.getText();
            String brandInput = brand.getText().toUpperCase();
            String modelInput = model.getText().toUpperCase();
            String colorInput = color.getText().toUpperCase();
            int levelInput = (int) level.getValue();
            double priceInput = Double.parseDouble(price.getText());

            /* Input validation */
            if (numberPlateInput.equals("") || brandInput.equals("") || modelInput.equals("") || colorInput.equals("")){
                throw new EmptyInputException();
            }

            //Other validation??????

            Car newCar = new Car(numberPlateInput, brandInput, modelInput, colorInput, levelInput, priceInput, true);
            ArrayList<Car> newCarList = FileIO.getCarList();
            newCarList.add(newCar);
            FileIO.setCarList(newCarList);
            //FileIO.carList.add(newCar);
            FileIO.writeCarFile();
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Car added Successfully!");
            clearAddCarField();
        }
        catch (EmptyInputException emptyInputException){
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "All fields require an input!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void searchCar(){
        try{
            ArrayList<Car> searchedList = FileIO.getCarList();

            if (numberPlateSearch.getText() != null){
                String numberPlate = numberPlateSearch.getText();
                for(Car car : searchedList){
                    if(!car.getNumberPlate().contains(numberPlate)){
                        searchedList.remove(car);
                    }
                }
            }

            if (brandSearch.getText() != null){
                String brand = brandSearch.getText();
                for (Car car : searchedList){
                    if (!car.getBrand().contains(brand)){
                        searchedList.remove(car);
                    }
                }
            }

            if (modelSearch.getText() != null){
                String model = modelSearch.getText();
                for (Car car: searchedList){
                    if (!car.getModel().contains(model)){
                        searchedList.remove(car);
                    }
                }
            }

            if (colorSearchBox.getSelectedItem() != "Any"){
                String color = (String) colorSearchBox.getSelectedItem();

                for (Car car : searchedList){
                    if (!car.getColor().equals(color)){
                        searchedList.remove(car);
                    }
                }
            }

            if (levelSearchBox.getSelectedItem() != "Any"){
                int level = Integer.parseInt((String)levelSearchBox.getSelectedItem());

                for (Car car : searchedList){
                    if (car.getLevel() != level){
                        searchedList.remove(car);
                    }
                }
            }

            double price = Double.parseDouble(priceSearchIndicator.getText());
            for (Car car : searchedList){
                if (car.getPrice() > price){
                    searchedList.remove(car);
                }
            }

            if (availabilitySearchBox.getSelectedItem() != "Any"){
                boolean availability = (String) availabilitySearchBox.getSelectedItem() == "Available" ? true : false;

                for (Car car : searchedList){
                    if (car.isAvailability() != availability){
                        searchedList.remove(car);
                    }
                }
            }

            if (searchedList.size() == 0){
            }
            else {
                String[] tableColumn = {"No", "No. Plate", "Brand", "Model", "Color", "Level", "Price", "Availability"};
                Object[][] tempTable = new Object[searchedList.size()][8];
                int i = 0;
                for (Car car : searchedList){
                    tempTable[i][0] = i + 1;
                    tempTable[i][1] = car.getNumberPlate();
                    tempTable[i][2] = car.getBrand();
                    tempTable[i][3] = car.getModel();
                    tempTable[i][4] = car.getColor();
                    tempTable[i][5] = car.getLevel();
                    tempTable[i][6] = car.getPrice();
                    tempTable[i][7] = car.isAvailability();
                    i++;
                }

                searchTable = new JTable(tempTable, tableColumn);
                searchTable.setVisible(true);
                JScrollPane scrollPane = new JScrollPane(searchTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                searchTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                searchCarPanel.add(scrollPane);
            }

            searchCarPanel.validate();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Unexpected error. Please try again.");
        }
    }
}
