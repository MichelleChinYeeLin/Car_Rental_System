import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminMenu implements ActionListener {

    private JFrame frame;
    private JPanel mainPanel;
    private JLabel title;
    private JSpinner numberSpinner;
    private JButton logout, accRegistration, customers, bookings, cars, settings, approveButton, denyButton;
    private JButton[] buttons;

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
        GridBagLayout buttonLayout = new GridBagLayout();
        JPanel buttonPanel = new JPanel(buttonLayout);
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

        //Create main panel
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.white);

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
                showAccRegistration();
            }
            else if (e.getSource() == approveButton){
                approveRegistration();
            }
            else if (e.getSource() == denyButton){
                denyRegistration();
            }
        } catch (Exception exception){
            System.out.println(exception);
            JOptionPane.showMessageDialog(frame, "Invalid move!");
        }
    }

    private void showCustomerList(){

    }

    private void approveRegistration(){

        ArrayList<Customer> accRegistrationList = FileIO.getRegistrationList();
        int numberValue = (int)numberSpinner.getValue();

        if(numberValue > 0){
            Customer newCustomer = accRegistrationList.get(numberValue - 1);
            accRegistrationList.remove(newCustomer);
            FileIO.setRegistrationList(accRegistrationList);

            ArrayList<Customer> customerList = FileIO.getCustomerList();
            customerList.add(newCustomer);
            FileIO.setCustomerList(customerList);
            FileIO.writeRegistrationFile();
            FileIO.writeCustomerFile();

            showAccRegistration();
        }
    }

    private void denyRegistration(){

        ArrayList<Customer> accRegistrationList = FileIO.getRegistrationList();
        int numberValue = (int)numberSpinner.getValue();

        if(numberValue > 0){
            Customer deletedRegistration = accRegistrationList.get(numberValue - 1);
            accRegistrationList.remove(deletedRegistration);
            FileIO.setRegistrationList(accRegistrationList);
            FileIO.writeRegistrationFile();

            showAccRegistration();
        }
    }

    private void showAccRegistration(){
        mainPanel.removeAll();
        ArrayList<Customer> accRegistrationList = FileIO.getRegistrationList();

        if(accRegistrationList.size() == 0){
            JLabel emptyTableLabel = new JLabel("No pending registration requests!");
            GUI.JLabelSetup(emptyTableLabel);

            mainPanel.add(emptyTableLabel);
            mainPanel.updateUI();
        }
        else{
            String[] tableColumn = {"No.", "Username", "Name", "Password", "Age", "Gender", "Phone", "Email", "Address"};
            Object[][] tempTable = new Object[accRegistrationList.size()][9];
            int i = 0;
            for (Customer nextCustomer : accRegistrationList){
                tempTable[i][0] = i + 1;
                tempTable[i][1] = nextCustomer.getUsername();
                tempTable[i][2] = nextCustomer.getName();
                tempTable[i][3] = nextCustomer.getPassword();
                tempTable[i][4] = nextCustomer.getAge();
                tempTable[i][5] = nextCustomer.getGender();
                tempTable[i][6] = nextCustomer.getPhone();
                tempTable[i][7] = nextCustomer.getEmail();
                tempTable[i][8] = nextCustomer.getAddress();
                i++;
            }

            JTable table = new JTable(tempTable, tableColumn);
            table.setVisible(true);
            JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            scrollPane.setPreferredSize(new Dimension(mainPanel.getWidth() - 5, mainPanel.getHeight() - 50));

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
            numberSpinner = new JSpinner(new SpinnerNumberModel(0, 0, accRegistrationList.size(), 1));
            bottomPanel.add(numberSpinner, bottomConstraints);

            bottomConstraints.gridx = 3;
            approveButton = new JButton("Approve");
            approveButton.addActionListener(this);
            bottomPanel.add(approveButton, bottomConstraints);

            bottomConstraints.gridx = 4;
            denyButton = new JButton("Deny");
            denyButton.addActionListener(this);
            bottomPanel.add(denyButton, bottomConstraints);

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.weighty = 0.8;
            mainPanel.add(scrollPane, constraints);

            constraints.gridy = 1;
            constraints.weighty = 0.2;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.anchor = GridBagConstraints.WEST;
            mainPanel.add(bottomPanel, constraints);
            mainPanel.updateUI();
        }
    }
}
