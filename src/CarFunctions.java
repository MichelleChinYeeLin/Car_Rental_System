import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarFunctions extends JPanel implements ActionListener {
    /* CAR */
    private JPanel carAttributesPanel;
    private static JPanel addCarPanel, editCarPanel, deleteCarPanel, searchCarPanel, viewCarPanel;
    private JButton confirmAdd, cancelAdd, search, confirmEdit, cancelEdit;
    private JLabel numberPlateLabel, brandLabel, modelLabel, colorLabel, levelLabel, priceLabel, availabilityLabel;
    private JTextField numberPlate, brand, model, color, price;
    private JSpinner level;
    private JRadioButton available, notAvailable;
    private ButtonGroup availability;
    private JButton[] carButtons;
    private static JPanel[] panels;
    private JLabel[] carLabels;

    public CarFunctions(){

        //Create labels
        numberPlateLabel = new JLabel("Number Plate:");
        brandLabel = new JLabel("Brand:");
        modelLabel = new JLabel("Model:");
        colorLabel = new JLabel("Color:");
        levelLabel = new JLabel("Level:");
        priceLabel = new JLabel("Price:");
        availabilityLabel = new JLabel("Availability:");
        carLabels = new JLabel[]{numberPlateLabel, brandLabel, modelLabel, colorLabel, levelLabel, priceLabel, availabilityLabel};
        GUI.JLabelSetup(carLabels);

        //Create buttons
        confirmAdd = new JButton("ADD");
        cancelAdd = new JButton("CANCEL");
        search = new JButton("SEARCH");
        confirmEdit = new JButton("CONFIRM");
        cancelEdit = new JButton("CANCEL");
        carButtons = new JButton[]{confirmAdd, cancelAdd, search, confirmEdit, cancelEdit};
        confirmAdd.addActionListener(this);
        cancelAdd.addActionListener(this);
        search.addActionListener(this);
        confirmEdit.addActionListener(this);
        cancelEdit.addActionListener(this);
        GUI.subJButtonSetup(carButtons);

        //Create input fields
        numberPlate = new JTextField(20);
        brand = new JTextField(20);
        model = new JTextField(20);
        color = new JTextField(20);
        price = new JTextField(20);
        level = new JSpinner(new SpinnerNumberModel(1,1,3,1));
        available = new JRadioButton("YES");
        notAvailable = new JRadioButton("NO");
        availability = new ButtonGroup();

        available.setFocusable(false);
        notAvailable.setFocusable(false);
        availability.add(available);
        availability.add(notAvailable);

        //Car Attributes Panel
        GridBagConstraints carConstraints = new GridBagConstraints();
        carAttributesPanel = new JPanel(new GridBagLayout());
        carAttributesPanel.setBackground(Color.white);
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
            carAttributesPanel.add(carLabels[i], carConstraints);
        }
        //Setup fields
        carConstraints.gridx = 2;
        carConstraints.gridy = 1;
        carAttributesPanel.add(numberPlate, carConstraints);
        carConstraints.gridy = 2;
        carAttributesPanel.add(brand, carConstraints);
        carConstraints.gridy = 3;
        carAttributesPanel.add(model, carConstraints);
        carConstraints.gridy = 4;
        carAttributesPanel.add(color, carConstraints);
        carConstraints.gridy = 5;
        carAttributesPanel.add(level, carConstraints);
        carConstraints.gridy = 6;
        carAttributesPanel.add(price, carConstraints);
        carConstraints.gridy = 7;
        JPanel availabilityPanel = new JPanel(new FlowLayout());
        availabilityPanel.setBackground(Color.white);
        availabilityPanel.add(available);
        availabilityPanel.add(notAvailable);
        carAttributesPanel.add(availabilityPanel, carConstraints);

        //Add car panel
        addCarPanel = new JPanel(new GridBagLayout());
        JPanel addCarSelectionPanel = new JPanel(new FlowLayout());
        addCarSelectionPanel.setBackground(Color.white);
        addCarSelectionPanel.add(confirmAdd);
        addCarSelectionPanel.add(cancelAdd);

        carConstraints.gridy = 0;
        addCarPanel.add(carAttributesPanel, carConstraints);
        carConstraints.gridy = 1;
        addCarPanel.add(addCarSelectionPanel, carConstraints);

        //Edit car panel
        editCarPanel = new JPanel(new GridBagLayout());
//        JPanel editCarSelectionPanel = new JPanel(new FlowLayout());
//        editCarSelectionPanel.setBackground(Color.white);
//        editCarSelectionPanel.add(search);
//        editCarSelectionPanel.add(confirmEdit);
//        editCarSelectionPanel.add(cancelEdit);
//
//        carConstraints.gridy = 0;
//        editCarPanel.add(carAttributesPanel, carConstraints);
//        carConstraints.gridy = 1;
//        editCarPanel.add(editCarSelectionPanel, carConstraints);

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
//                available.setEnabled(false);
//                available.setSelected(true);
//                notAvailable.setEnabled(false);
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
                FileIO.carList.add(newCar);
                FileIO.writeCarFile();
                JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Car added Successfully!");
                clearAddCarField();
            }
            else if (e.getSource() == cancelAdd) {
                clearAddCarField();
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
