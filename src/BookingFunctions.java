import javafx.scene.control.DatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Math.max;

public class BookingFunctions extends JPanel implements ActionListener {

    private JPanel searchBookingPanel, viewBookingPanel;
    private JLabel carNumberPlateLabel, customerNameLabel, totalPriceLabel, statusLabel, startDateLabel, endDateLabel;
    private JLabel totalPriceIndicator;
    private JTextField carNumberPlate, customerName;
    private JSlider totalPrice;
    private JComboBox<String> status;
    private DatePicker startDate, endDate;
    public BookingFunctions(){

        //Create labels
        carNumberPlateLabel = new JLabel("Car Number Plate:");
        customerNameLabel = new JLabel("Customer Name:");
        totalPriceLabel = new JLabel("Total Price:");
        statusLabel = new JLabel("Status:");
        startDateLabel = new JLabel("Start Date:");
        endDateLabel = new JLabel("End Date:");
        totalPriceIndicator = new JLabel();

        //Create input fields
        //JTextField
        carNumberPlate = new JTextField(20);
        customerName = new JTextField(20);

        //JSlider
        double maxTotalPrice = 0.0;
        for (Booking booking : FileIO.getBookingList()) {
            //maxTotalPrice = max()
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
