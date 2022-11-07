import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenu implements ActionListener {

    private JFrame frame;
    private JPanel carPanel, registrationPanel, customerPanel, bookingsPanel, settingsPanel;
    private JPanel addCarPanel, editCarPanel, deleteCarPanel, searchCarPanel, viewCarPanel;
    private JLabel label;
    private JButton logout, accRegistration, customers, bookings, cars, settings;
    private JButton addCar, editCar, deleteCar, searchCar, allCar;
    private JButton[] buttons, carButtons;
    private JPanel[] panels, carPanels;

    public AdminMenu(){
        frame = new JFrame("Admin Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUI.JFrameSetup(frame);
        frame.setLayout(new GridBagLayout());

        //Create labels
        label = new JLabel("HI");

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

        //Car button panel
        JPanel carButtonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints carConstraints = new GridBagConstraints();
        carButtonPanel.add(addCar, carConstraints);
        carButtonPanel.add(editCar, carConstraints);
        carButtonPanel.add(deleteCar, carConstraints);
        carButtonPanel.add(searchCar, carConstraints);
        carButtonPanel.add(allCar, carConstraints);

        //Add car panel
        addCarPanel = new JPanel(new GridBagLayout());
        addCarPanel.add(label);

        //Edit car panel
        editCarPanel = new JPanel(new GridBagLayout());

        //Delete car panel
        deleteCarPanel = new JPanel(new GridBagLayout());

        //Search car panel
        searchCarPanel = new JPanel(new GridBagLayout());

        //View car panel
        viewCarPanel = new JPanel(new GridBagLayout());

        //Create car panel
        carPanel = new JPanel(new GridBagLayout());
        carPanel.setBackground(Color.white);

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
        } catch (Exception exception){
            JOptionPane.showMessageDialog(frame, "Invalid move!");
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
}
