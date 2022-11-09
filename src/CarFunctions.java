import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CarFunctions extends JPanel implements ActionListener {

    private JPanel addCarAttributesPanel, editCarAttributesPanel;
    private static JPanel addCarPanel, editCarPanel, deleteCarPanel, searchCarPanel, viewCarPanel;
    private JButton confirmAdd, cancelAdd, search, confirmEdit, cancelEdit;
    private JLabel numberPlateLabel, brandLabel, modelLabel, colorLabel, levelLabel, priceLabel;
    private JLabel numberPlateLabel2, brandLabel2, modelLabel2, colorLabel2, levelLabel2, priceLabel2, availabilityLabel;
    private JTextField numberPlate, brand, model, color, price;
    private JTextField numberPlate2, brand2, model2, color2, price2;
    private JSpinner level, level2;
    private JRadioButton available, notAvailable;
    private ButtonGroup availability;
    private JButton[] carButtons;
    private static JPanel[] panels;
    private JLabel[] carLabels, carLabels2;

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

        //Search car panel
        searchCarPanel = new JPanel(new GridBagLayout());

        //View car panel
        viewCarPanel = new JPanel(new GridBagLayout());

        //Create car functions panel
        panels = new JPanel[]{addCarPanel, editCarPanel, deleteCarPanel, searchCarPanel, viewCarPanel};
        GUI.JPanelSetup(panels);
        setPreferredSize(new Dimension(500,500));
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
            else if (e.getSource() == cancelAdd) {
                clearAddCarField();
            }
            else if (e.getSource() == search){
                String numberPlateInput = numberPlate2.getText();
                String brandInput = brand2.getText().toUpperCase();
                String modelInput = model2.getText().toUpperCase();
                String colorInput = color2.getText().toUpperCase();
                int levelInput = (int) level2.getValue();
                double priceInput = 0.0;
                boolean availableInput = false;
                boolean flag = true;
                ArrayList<Car> foundCars = null;

                //Check if the buttons had been clicked
                if (available.isSelected() || notAvailable.isSelected()){
                    if (available.isSelected()){
                        availableInput = true;
                    }
                }
                else {
                    flag = false; //没选到就false
                }

                //Check price
                if (!price2.getText().equals("") && !price2.getText().equals("0.0")){
                    priceInput = Double.parseDouble(price2.getText());
                }

                if (numberPlateInput.equals("") && brandInput.equals("") && modelInput.equals("")
                        && colorInput.equals("") && levelInput == 0 && priceInput == 0.0 && flag){
                    throw new EmptyInputException();
                }
                foundCars = Car.searchCar(numberPlateInput, brandInput, modelInput, colorInput, levelInput, priceInput, availableInput, flag);


            }
            else if (e.getSource() == confirmEdit){

            }
            else if (e.getSource() == cancelEdit){

            }
        } catch (EmptyInputException emptyInputException){
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "All fields require an input!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (NumberFormatException numberFormatException){
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Error format for price!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (Exception exception){
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

}
