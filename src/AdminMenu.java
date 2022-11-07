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
    private JButton addCar, editCar, deleteCar, searchCar, allCar, confirmAdd, cancelAdd;
    private JLabel numberPlateLabel, brandLabel, modelLabel, colorLabel, levelLabel, priceLabel, availabilityLabel;
    private JTextField numberPlate, brand, model, color, price;
    private JSpinner level;
    private JRadioButton available, notAvailable;
    private ButtonGroup availability;

    /* REGISTRATION */

    /* CUSTOMER */

    /* BOOKING */

    /* SETTING */

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
        carButtons = new JButton[]{addCar, editCar, deleteCar, searchCar, allCar, confirmAdd, cancelAdd};
        addCar.addActionListener(this);
        editCar.addActionListener(this);
        deleteCar.addActionListener(this);
        searchCar.addActionListener(this);
        allCar.addActionListener(this);
        confirmAdd.addActionListener(this);
        cancelAdd.addActionListener(this);
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

        //Car button panel
        JPanel carButtonPanel = new JPanel(new GridBagLayout());
        carButtonPanel.add(addCar);
        carButtonPanel.add(editCar);
        carButtonPanel.add(deleteCar);
        carButtonPanel.add(searchCar);
        carButtonPanel.add(allCar);

        //Add car panel
        addCarPanel = new JPanel(new GridBagLayout());
        //Setup labels
        carConstraints.insets = new Insets(10,5,10,5);
        carConstraints.weightx = 1;
        carConstraints.weighty = 1;
        carConstraints.gridx = 0;
        carConstraints.ipady = 0;
        for(int i = 0; i < carLabels.length - 1; i++){

            carConstraints.gridwidth = 2;
            if(carLabels[i].getText().equals("Level:")){
                carConstraints.gridwidth = 1;
            }
            carConstraints.gridy = i + 1;
            addCarPanel.add(carLabels[i], carConstraints);
        }
        //Setup fields
        carConstraints.gridwidth = 2;
        carConstraints.gridx = 2;
        carConstraints.gridy = 1;
        addCarPanel.add(numberPlate, carConstraints);
        carConstraints.gridy = 2;
        addCarPanel.add(brand, carConstraints);
        carConstraints.gridy = 3;
        addCarPanel.add(model, carConstraints);
        carConstraints.gridy = 4;
        addCarPanel.add(color, carConstraints);
        carConstraints.gridy = 5;
        addCarPanel.add(level, carConstraints);
        carConstraints.gridy = 6;
        addCarPanel.add(price, carConstraints);
        carConstraints.gridy = 7;
//        JPanel availabilityPanel = new JPanel(new FlowLayout());
//        availabilityPanel.setBackground(Color.white);
//        availabilityPanel.add(available);
//        availabilityPanel.add(notAvailable);
//        addCarPanel.add(availabilityPanel, carConstraints);
//        carConstraints.gridy = 8;
        JPanel selectionPanel = new JPanel(new FlowLayout());
        selectionPanel.setBackground(Color.white);
        selectionPanel.add(confirmAdd);
        selectionPanel.add(cancelAdd);
        addCarPanel.add(selectionPanel, carConstraints);


        //Edit car panel
        editCarPanel = new JPanel(new GridBagLayout());

        //Delete car panel
        deleteCarPanel = new JPanel(new GridBagLayout());

        //Search car panel
        searchCarPanel = new JPanel(new GridBagLayout());

        //View car panel
        viewCarPanel = new JPanel(new GridBagLayout());

        //Position elements in car panel
        carConstraints.insets = new Insets(5,5,5,5);
        carConstraints.gridy = 0;
        carPanel.add(carButtonPanel, carConstraints);
        carConstraints.gridy = 1;
        carPanel.add(addCarPanel, carConstraints);

        //All car panels
        carPanels = new JPanel[]{addCarPanel, editCarPanel, deleteCarPanel, searchCarPanel, viewCarPanel};
        GUI.JPanelSetup(carPanels);


        /* MAIN */
        //Create panels
        registrationPanel = new JPanel();
        customerPanel = new JPanel();
        bookingsPanel = new JPanel();
        settingsPanel = new JPanel();
        panels = new JPanel[]{carPanel, registrationPanel, customerPanel, bookingsPanel, settingsPanel};
        GUI.JPanelSetup(panels);

        //Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.white);
        mainPanel.add(carPanel);

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
                showPanel(registrationPanel);
            }
            else if (e.getSource() == customers){
                showPanel(customerPanel);
            }
            else if (e.getSource() == bookings){
                showPanel(bookingsPanel);
            }
            else if (e.getSource() == cars){
                showPanel(carPanel);
            }
            else if (e.getSource() == settings){
                showPanel(settingsPanel);
            }
            else if (e.getSource() == addCar){
                showCarPanel(addCarPanel);
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

    private void showPanel(JPanel panel){
        for (JPanel i : panels) {
            i.setVisible(false);
        }
        panel.setVisible(true);
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
