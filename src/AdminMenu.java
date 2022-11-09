import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenu implements ActionListener {

    /* MAIN */
    private JFrame frame;
    private JPanel carPanel, registrationPanel, customerPanel, bookingsPanel, settingsPanel;
    private JButton logout, accRegistration, customers, bookings, cars, settings;

    /* CAR */
    private CarFunctions carFunctionsPanel;
    private JButton addCar, editCar, deleteCar, searchCar, allCar;

    /* REGISTRATION */
    private JPanel registrationFunctionsPanel;

    /* CUSTOMER */
    private JPanel customerFunctionsPanel;

    /* BOOKING */
    private JPanel bookingFunctionsPanel;

    /* SETTING */
    private JPanel settingFunctionsPanel;

    private JButton[] buttons, carButtons;
    private JPanel[] panels;

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
        //Create buttons
        addCar = new JButton("Add");
        editCar = new JButton("Edit");
        deleteCar = new JButton("Delete");
        searchCar = new JButton("Search");
        allCar = new JButton("All Cars");
        carButtons = new JButton[]{addCar, editCar, deleteCar, searchCar, allCar};
        addCar.addActionListener(this);
        editCar.addActionListener(this);
        deleteCar.addActionListener(this);
        searchCar.addActionListener(this);
        allCar.addActionListener(this);
        GUI.subJButtonSetup(carButtons);

        //Create car panel
        carPanel = new JPanel(new GridBagLayout());
        GridBagConstraints carConstraints = new GridBagConstraints();
        carFunctionsPanel = new CarFunctions();

        //Car button panel
        JPanel carButtonPanel = new JPanel(new GridBagLayout());
        carButtonPanel.add(addCar);
        carButtonPanel.add(editCar);
        carButtonPanel.add(deleteCar);
        carButtonPanel.add(searchCar);
        carButtonPanel.add(allCar);

        //Position elements in car panel
        carConstraints.gridy = 0;
        carPanel.add(carButtonPanel, carConstraints);
        carConstraints.gridy = 1;
        carPanel.add(carFunctionsPanel, carConstraints);


        /* REGISTRATION */
        registrationPanel = new JPanel(new GridBagLayout());
        GridBagConstraints regConstraints = new GridBagConstraints();
        registrationFunctionsPanel = new JPanel(); // to change

        /* CUSTOMER */
        customerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints cusConstraints = new GridBagConstraints();
        customerFunctionsPanel = new JPanel(); // to change

        /* BOOKING */
        bookingsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints bkgConstraints = new GridBagConstraints();
        bookingFunctionsPanel = new JPanel(); // to change

        /* SETTING */
        settingsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints stgConstraints = new GridBagConstraints();
        settingFunctionsPanel = new JPanel(); // to change


        /* MAIN */
        //Create panels
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
                CarFunctions.showAddCarPanel();
            }
            else if (e.getSource() == editCar){
                CarFunctions.showEditCarPanel();
            }
            else if (e.getSource() == deleteCar){
                CarFunctions.showDeleteCarPanel();
            }
            else if (e.getSource() == searchCar){
                CarFunctions.showSearchCarPanel();
            }
            else if (e.getSource() == allCar){
                CarFunctions.showAllCarPanel();
            }
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

}
