import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenu implements ActionListener {

    /* MAIN */
    private JFrame frame;
    private JPanel carPanel, registrationPanel, customerPanel, bookingsPanel, settingsPanel;
    private JButton logout, accRegistration, customers, bookings, cars, settings;
    private JPanel addCarPanel, editCarPanel, deleteCarPanel, searchCarPanel, viewCarPanel;

    /* CAR */
    private JPanel carAttributesPanel, carFunctionsPanel;
    private JButton addCar, editCar, deleteCar, searchCar, allCar, confirmAdd, cancelAdd, search, confirmEdit, cancelEdit;
    private JLabel numberPlateLabel, brandLabel, modelLabel, colorLabel, levelLabel, priceLabel, availabilityLabel;
    private JTextField numberPlate, brand, model, color, price;
    private JSpinner level;
    private JRadioButton available, notAvailable;
    private ButtonGroup availability;

    /* REGISTRATION */
    private JPanel registrationFunctionsPanel;

    /* CUSTOMER */
    private JPanel customerFunctionsPanel;

    /* BOOKING */
    private JPanel bookingFunctionsPanel;

    /* SETTING */
    private JPanel settingFunctionsPanel;

    private JButton[] buttons, carButtons;
    private JPanel[] panels, carPanels;
    private JLabel[] carLabels;

    public AdminMenu(){
        frame = new JFrame("Admin Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUI.JFrameSetup(frame);
        frame.setLayout(new GridBagLayout());

        //Create buttons
        accRegistration = new JButton("Registration");
        customers = new JButton("Customers");
        bookings = new JButton("Bookings");
        cars = new JButton("Cars");
        settings = new JButton("Settings");
        logout = new JButton("Logout");
        buttons = new JButton[]{accRegistration, customers, bookings, cars, settings, logout};
        accRegistration.addActionListener(this);
        customers.addActionListener(this);
        bookings.addActionListener(this);
        cars.addActionListener(this);
        settings.addActionListener(this);
        logout.addActionListener(this);
        GUI.JButtonSetup(buttons);

        //Button Panel
        GridBagLayout ButtonLayout = new GridBagLayout();
        JPanel buttonPanel = new JPanel(ButtonLayout);
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
        addCar = new JButton("Add");
        editCar = new JButton("Edit");
        deleteCar = new JButton("Delete");
        searchCar = new JButton("Search");
        allCar = new JButton("All Cars");
        confirmAdd = new JButton("ADD");
        cancelAdd = new JButton("CANCEL");
        search = new JButton("SEARCH");
        confirmEdit = new JButton("CONFIRM");
        cancelEdit = new JButton("CANCEL");
        carButtons = new JButton[]{addCar, editCar, deleteCar, searchCar, allCar, confirmAdd, cancelAdd, search, confirmEdit, cancelEdit};
        addCar.addActionListener(this);
        editCar.addActionListener(this);
        deleteCar.addActionListener(this);
        searchCar.addActionListener(this);
        allCar.addActionListener(this);
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

        //Create car panel
        carPanel = new JPanel(new GridBagLayout());
        GridBagConstraints carConstraints = new GridBagConstraints();

        //Car Attributes Panel
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
        JPanel selectionPanel = new JPanel(new FlowLayout());
        selectionPanel.setBackground(Color.white);
        selectionPanel.add(confirmAdd);
        selectionPanel.add(cancelAdd);

        carConstraints.gridy = 0;
        addCarPanel.add(carAttributesPanel, carConstraints);
        carConstraints.gridy = 1;
        addCarPanel.add(selectionPanel, carConstraints);

        //Edit car panel
        editCarPanel = new JPanel(new GridBagLayout());
        selectionPanel = new JPanel(new FlowLayout());
        selectionPanel.setBackground(Color.white);
        selectionPanel.add(search);
        selectionPanel.add(confirmEdit);
        selectionPanel.add(cancelEdit);

        carConstraints.gridy = 0;
        editCarPanel.add(carAttributesPanel, carConstraints);
        carConstraints.gridy = 1;
        editCarPanel.add(selectionPanel, carConstraints);

        //Delete car panel
        deleteCarPanel = new JPanel(new GridBagLayout());

        //Search car panel
        searchCarPanel = new JPanel(new GridBagLayout());

        //View car panel
        viewCarPanel = new JPanel(new GridBagLayout());

        //Car button panel
        JPanel carButtonPanel = new JPanel(new GridBagLayout());
        carButtonPanel.add(addCar);
        carButtonPanel.add(editCar);
        carButtonPanel.add(deleteCar);
        carButtonPanel.add(searchCar);
        carButtonPanel.add(allCar);

        //Create car functions panel & add all things in same place
        carPanels = new JPanel[]{addCarPanel, editCarPanel, deleteCarPanel, searchCarPanel, viewCarPanel};
        GUI.JPanelSetup(carPanels);
        carFunctionsPanel = new JPanel();
        carFunctionsPanel.setPreferredSize(new Dimension(500,500));
        carFunctionsPanel.add(addCarPanel);
        carFunctionsPanel.add(editCarPanel);
        carFunctionsPanel.add(deleteCarPanel);
        carFunctionsPanel.add(searchCarPanel);
        carFunctionsPanel.add(viewCarPanel);

        //Position elements in car panel
        carConstraints.gridy = 0;
        carPanel.add(carButtonPanel, carConstraints);
        carConstraints.gridy = 1;
        carPanel.add(carFunctionsPanel, carConstraints);


        /* REGISTRATION */
        //Create registration functions panel & add all things in same place
        registrationFunctionsPanel = new JPanel();

        /* CUSTOMER */
        //Create customer functions panel & add all things in same place
        customerFunctionsPanel = new JPanel();

        /* BOOKING */
        //Create booking functions panel & add all things in same place
        bookingFunctionsPanel = new JPanel();

        /* SETTING */
        //Create setting functions panel & add all things in same place
        settingFunctionsPanel = new JPanel();


        /* MAIN */
        //Create panels
        registrationPanel = new JPanel();
        customerPanel = new JPanel();
        bookingsPanel = new JPanel();
        settingsPanel = new JPanel();
        panels = new JPanel[]{carPanel, carFunctionsPanel, registrationPanel, registrationFunctionsPanel,
                customerPanel, customerFunctionsPanel, bookingsPanel, bookingFunctionsPanel,
                settingsPanel, settingFunctionsPanel};
        GUI.JPanelSetup(panels);

        //Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.white);
        mainPanel.add(carPanel);
        mainPanel.validate();

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
                CarRentalSystem.loginAdmin = null;
                frame.setVisible(false);
                CarRentalSystem.homePage.getFrame().setVisible(true);
            }
            else if (e.getSource() == accRegistration){
                showPanel(registrationPanel, registrationFunctionsPanel);
            }
            else if (e.getSource() == customers){
                showPanel(customerPanel, customerFunctionsPanel);
            }
            else if (e.getSource() == bookings){
                showPanel(bookingsPanel, bookingFunctionsPanel);
            }
            else if (e.getSource() == cars){
                showPanel(carPanel, carFunctionsPanel);
            }
            else if (e.getSource() == settings){
                showPanel(settingsPanel, settingFunctionsPanel);
            }
            else if (e.getSource() == addCar){
                showCarPanel(addCarPanel);
                available.setEnabled(false);
                available.setSelected(true);
                notAvailable.setEnabled(false);
            }
            else if (e.getSource() == editCar){
                showCarPanel(editCarPanel);
            }
            else if (e.getSource() == deleteCar){
                showCarPanel(deleteCarPanel);
            }
            else if (e.getSource() == searchCar){
                showCarPanel(searchCarPanel);
            }
            else if (e.getSource() == allCar){
                showCarPanel(viewCarPanel);
            }
            else if (e.getSource() == confirmAdd) {
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
                JOptionPane.showMessageDialog(frame, "Car added Successfully!");
                clearAddCarField();
            }
            else if (e.getSource() == cancelAdd) {
                clearAddCarField();
            }
        } catch (EmptyInputException emptyInputException){
            JOptionPane.showMessageDialog(frame, "All fields require an input!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (NumberFormatException numberFormatException){
            JOptionPane.showMessageDialog(frame, "Error format for price!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (Exception exception){
            System.out.println("HI something wrong");
        }
    }

    private void showCustomerList(){

    }

    private void showPanel(JPanel bigPanel, JPanel smallPanel){
        for (JPanel i : panels) {
            i.setVisible(false);
        }
        bigPanel.setVisible(true);
        smallPanel.setVisible(true);
    }

    private void showCarPanel(JPanel panel){
        for (JPanel i : carPanels) {
            i.setVisible(false);
        }
        panel.setVisible(true);
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
}
