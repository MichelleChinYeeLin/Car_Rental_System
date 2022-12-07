import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReportFunctions extends JPanel implements ActionListener {

    private static JPanel customerPanel, carPanel, paymentPanel, feedbackPanel;
    private static JButton printUserButton, printCarButton, printPaymentButton, printFeedbackButton;
    private static JPanel[] panels;
    private static String customerPrintText, carPrintText, paymentPrintText, feedbackPrintText;

    public ReportFunctions(){
        customerPanel = new JPanel(new GridBagLayout());
        carPanel = new JPanel(new GridBagLayout());
        paymentPanel = new JPanel(new GridBagLayout());
        feedbackPanel = new JPanel(new GridBagLayout());

        printUserButton = new JButton("PRINT");
        printCarButton = new JButton("PRINT");
        printPaymentButton = new JButton("PRINT");
        printFeedbackButton = new JButton("PRINT");
        printUserButton.addActionListener(this);
        printCarButton.addActionListener(this);
        printPaymentButton.addActionListener(this);
        printFeedbackButton.addActionListener(this);
        GUI.JButtonSetup(new JButton[]{printUserButton, printCarButton, printPaymentButton, printFeedbackButton});

        panels = new JPanel[]{customerPanel, carPanel, paymentPanel, feedbackPanel};
        GUI.JPanelSetup(panels);
        setPreferredSize(new Dimension(600,500));
        add(customerPanel);
        add(carPanel);
        add(paymentPanel);
        add(feedbackPanel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == printUserButton){
                GUI.playSound("ji.wav");
                generateReport("user_report", customerPrintText);
            }
            else if (e.getSource() == printCarButton){
                GUI.playSound("ji.wav");
                generateReport("car_report", carPrintText);
            }
            else if (e.getSource() == printPaymentButton){
                GUI.playSound("ji.wav");
                generateReport("payment_report", paymentPrintText);
            }
            else if (e.getSource() == printFeedbackButton){
                GUI.playSound("ji.wav");
                generateReport("feedback_report", feedbackPrintText);
            }
        }
        catch (Exception exception){
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Unexpected error occurred! Please try again later.", "Registration Approval Failed", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void showUserPanel(){
        generateUserPanel();
        showReportPanel(customerPanel);
    }

    public static void showCarPanel(){
        generateCarPanel();
        showReportPanel(carPanel);
    }

    public static void showPaymentPanel(){
        generatePaymentPanel();
        showReportPanel(paymentPanel);
    }

    public static void showFeedbackPanel(){
        generateFeedbackPanel();
        showReportPanel(feedbackPanel);
    }

    private static void showReportPanel(JPanel panel){
        for (JPanel i : panels){
            i.setVisible(false);
        }
        panel.setVisible(true);
    }

    private static void generateUserPanel(){
        customerPanel.removeAll();

        int maleNum = 0, femaleNum = 0, totalUserNum = 0;
        int less20 = 0, less30 = 0, less40 = 0, less50 = 0, less122 = 0;

        for (Customer customer : FileIO.customerList){
            if (customer.getGender().equalsIgnoreCase("male")){
                maleNum++;
            }
            else {
                femaleNum++;
            }

            if (customer.getAge() <= 20){
                less20++;
            }
            else if (customer.getAge() <= 30){
                less30++;
            }
            else if (customer.getAge() <= 40){
                less40++;
            }
            else if (customer.getAge() <= 50){
                less50++;
            }
            else {
                less122++;
            }

            totalUserNum++;
        }

        JLabel totalUserNumLabel = new JLabel();
        GUI.JLabelSetup(totalUserNumLabel);
        totalUserNumLabel.setText("Total Number Of Customers: " + totalUserNum);

        // Gender Panel
        JPanel genderPanel = new JPanel(new GridBagLayout());
        genderPanel.setPreferredSize(new Dimension(250, 250));
        genderPanel.setBackground(Color.white);
        JLabel genderTitle = new JLabel("Gender Analysis");
        JLabel genderLabel = new JLabel();
        JLabel genderAnalysis = new JLabel();
        GUI.JLabelTitleSetup(genderTitle);
        GUI.JLabelSetup(new JLabel[]{genderLabel, genderAnalysis});

        double malePercentage = (Double.valueOf(maleNum) / totalUserNum) * 100;
        double femalePercentage = (Double.valueOf(femaleNum) / totalUserNum) * 100;

        String labelText = "<html><body><p>Male Number<br/>Male Percent<br/>Female Number<br/>Female Percent</p></body></html>";
        genderLabel.setText(labelText);

        String analysisText = "<html><body><p style=\"text-align:right\">" + maleNum + "<br/>" + String.format("%.2f", malePercentage) + "%<br/>";
        analysisText += femaleNum + "<br/>" + String.format("%.2f", femalePercentage) + "%<br/></p></body></html>";
        genderAnalysis.setText(analysisText);

        //HTML
        customerPrintText = "<html><body><div style=\"max-width:300px\"><p>Total Number Of Customers: " + totalUserNum + "</p>";
        customerPrintText += "<h1 style=\"text-align:center\">Gender Analysis</h1>";
        customerPrintText += "<div style=\"text-align:right;width:100%\"><span style=\"float:left\">Male Number: </span>\n" +
                maleNum + "</div>";
        customerPrintText += "<div style=\"text-align:right;width:100%\"><span style=\"float:left\">Male Percentage: </span>\n" +
                String.format("%.2f", malePercentage) + "%</div>";
        customerPrintText += "<div style=\"text-align:right;width:100%\"><span style=\"float:left\">Female Number: </span>\n" +
                femaleNum + "</div>";
        customerPrintText += "<div style=\"text-align:right;width:100%\"><span style=\"float:left\">Female Percentage: </span>\n" +
                String.format("%.2f", femalePercentage) + "%</div></div>";

        //Age Panel
        JPanel agePanel = new JPanel(new GridBagLayout());
        agePanel.setPreferredSize(new Dimension(250, 250));
        agePanel.setBackground(Color.white);
        JLabel ageTitle = new JLabel("Age Analysis");
        JLabel ageLabel = new JLabel();
        JLabel ageAnalysis = new JLabel();
        GUI.JLabelTitleSetup(ageTitle);
        GUI.JLabelSetup(new JLabel[]{ageLabel, ageAnalysis});

        labelText = "<html><body><p>17 - 20<br/>21 - 30<br/>31 - 40<br/>41 - 50<br/> > 50</p></body></html>";
        ageLabel.setText(labelText);

        analysisText = "<html><body><p style=\"text-align:right\">" + less20 + "<br/>" + less30 + "<br/>" + less40 + "<br/>" + less50 + "<br/>" + less122;
        ageAnalysis.setText(analysisText);

        customerPrintText += "<div style=\"max-width:300px\"><h1 style=\"text-align:center\">Age Analysis</h1>";
        customerPrintText += "<div style=\"text-align:right;width:100%\"><span style=\"float:left\">17 - 20: </span>\n" +
                less20 + "</div>";
        customerPrintText += "<div style=\"text-align:right;width:100%\"><span style=\"float:left\">21 - 30: </span>\n" +
                less30 + "</div>";
        customerPrintText += "<div style=\"text-align:right;width:100%\"><span style=\"float:left\">31 - 40: </span>\n" +
                less40 + "</div>";
        customerPrintText += "<div style=\"text-align:right;width:100%\"><span style=\"float:left\">41 - 50: </span>\n" +
                less50 + "</div>";
        customerPrintText += "<div style=\"text-align:right;width:100%\"><span style=\"float:left\">> 50: </span>\n" +
                less122 + "</div></div></body></html>";

        GridBagConstraints panelConstraints = new GridBagConstraints();
        GridBagConstraints constraints = new GridBagConstraints();

        panelConstraints.gridx = 0;
        panelConstraints.gridy = 0;
        panelConstraints.gridwidth = 3;
        genderPanel.add(genderTitle, panelConstraints);
        agePanel.add(ageTitle, panelConstraints);

        panelConstraints.gridy = 1;
        panelConstraints.gridwidth = 1;
        panelConstraints.insets = new Insets(20, 20, 20, 20);
        genderPanel.add(genderLabel, panelConstraints);
        agePanel.add(ageLabel, panelConstraints);

        panelConstraints.gridx = 1;
        genderPanel.add(genderAnalysis, panelConstraints);
        agePanel.add(ageAnalysis, panelConstraints);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.white);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.PAGE_START;
        mainPanel.add(genderPanel, constraints);

        constraints.gridx = 1;
        mainPanel.add(agePanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(20, 20, 20, 20);
        mainPanel.add(printUserButton, constraints);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(550, 350));
        scrollPane.setViewportView(mainPanel);

        GridBagConstraints mainConstraints = new GridBagConstraints();
        mainConstraints.gridx = 0;
        mainConstraints.gridy = 0;
        mainConstraints.anchor = GridBagConstraints.WEST;
        customerPanel.add(totalUserNumLabel, mainConstraints);

        mainConstraints.gridy = 1;
        mainConstraints.anchor = GridBagConstraints.CENTER;
        customerPanel.add(mainPanel, mainConstraints);

        genderPanel.validate();
        agePanel.validate();
        mainPanel.validate();
        customerPanel.validate();
    }

    private static void generateCarPanel(){
        carPanel.removeAll();

        Car.Color[] colorType = Car.Color.values();

        ArrayList<Integer> colorNum = new ArrayList<>();
        ArrayList<String> brandType = new ArrayList<>();
        ArrayList<Integer> brandNum = new ArrayList<>();
        ArrayList<Integer> levelType = new ArrayList<>();
        ArrayList<Integer> levelNum = new ArrayList<>();

        String labelText = "<html><body><p>";

        for (int i = 0; i < colorType.length; i++){
            colorNum.add(0);
            labelText += String.valueOf(colorType[i]) + "<br/>";
        }

        int totalCarNum = 0;
        for (Car car : FileIO.carList){

            // Get number of cars with colors
            for (int i = 0; i < colorType.length; i++){
                if (car.getColorType() == colorType[i]){
                    colorNum.set(i, colorNum.get(i) + 1);
                    break;
                }
            }

            // Get number of cars with brands
            boolean isUniqueBrand = true;
            for (int i = 0; i < brandType.size(); i++){
                if (car.getBrand().equalsIgnoreCase(brandType.get(i))){
                    isUniqueBrand = false;
                    brandNum.set(i, brandNum.get(i) + 1);
                    break;
                }
            }

            if (isUniqueBrand){
                brandType.add(car.getBrand());
                brandNum.add(1);
            }

            // Get number of cars with level
            boolean isUniqueLevel = true;
            for (int i = 0; i < levelType.size(); i++){
                if (car.getLevel() == levelType.get(i)){
                    isUniqueLevel = false;
                    levelNum.set(i, levelNum.get(i) + 1);
                    break;
                }
            }

            if (isUniqueLevel){
                levelType.add(car.getLevel());
                levelNum.add(1);
            }

            totalCarNum++;
        }

        JLabel totalCarNumLabel = new JLabel();
        totalCarNumLabel.setText("Total Number Of Cars: " + totalCarNum);
        GUI.JLabelSetup(totalCarNumLabel);

        // Color panel
        JPanel colorPanel = new JPanel(new GridBagLayout());
        colorPanel.setPreferredSize(new Dimension(250, 250));
        colorPanel.setBackground(Color.white);
        JLabel colorTitle = new JLabel("Color Analysis");
        JLabel colorLabel = new JLabel();
        JLabel colorAnalysis = new JLabel();
        GUI.JLabelTitleSetup(colorTitle);
        GUI.JLabelSetup(new JLabel[]{colorLabel, colorAnalysis});

        colorLabel.setText(labelText);
        String analysisText = "<html><body><p style=\"text-align:right\">";

        for (Integer num : colorNum){
            analysisText += num + "<br/>";
        }

        colorAnalysis.setText(analysisText);

        //HTML
        carPrintText = "<html><body><div style=\"max-width:400px\"><p>Total Number Of Cars: " + totalCarNum + "</p>" +
                "<h1 style=\"text-align:center\">Color Analysis</h1>";

        for (int i = 0; i < colorType.length; i++){
            carPrintText += "<div style=\"text-align:right;width:100%\"><span style=\"float:left\">" + colorType[i] + " </span>\n" +
                    colorNum.get(i) + "</div>";
        }

        //Brand Panel
        JPanel brandPanel = new JPanel(new GridBagLayout());
        brandPanel.setPreferredSize(new Dimension(250, 250));
        brandPanel.setBackground(Color.white);
        JLabel brandTitle = new JLabel("Brand Analysis");
        JLabel brandLabel = new JLabel();
        JLabel brandAnalysis = new JLabel();
        GUI.JLabelTitleSetup(brandTitle);
        GUI.JLabelSetup(new JLabel[]{brandLabel, brandAnalysis});

        labelText = "<html><body><p>";
        analysisText = "<html><body><p style=\"text-align:right\">";
        carPrintText += "<h1 style=\"text-align:center\">Brand Analysis</h1>";

        for (int i = 0; i < brandType.size(); i++){
            labelText += brandType.get(i) + "<br/>";
            analysisText += brandNum.get(i) + "<br/>";
            carPrintText += "<div style=\"text-align:right;width:100%\"><span style=\"float:left\">" + brandType.get(i) + " </span>\n" +
                    brandNum.get(i) + "</div>";
        }

        labelText += "</p></body></html>";
        analysisText += "</p></body></html>";

        brandLabel.setText(labelText);
        brandAnalysis.setText(analysisText);

        //Level Panel
        JPanel levelPanel = new JPanel(new GridBagLayout());
        levelPanel.setPreferredSize(new Dimension(250, 250));
        levelPanel.setBackground(Color.white);
        JLabel levelTitle = new JLabel("Level Analysis");
        JLabel levelLabel = new JLabel();
        JLabel levelAnalysis = new JLabel();
        GUI.JLabelTitleSetup(levelTitle);
        GUI.JLabelSetup(new JLabel[]{levelLabel, levelAnalysis});

        labelText = "<html><body><p>";
        analysisText = "<html><body><p style=\"text-align:right\">";
        carPrintText += "<h1 style=\"text-align:center\">Level Analysis</h1>";

        for (int i = 0; i < levelType.size(); i++){
            labelText += "Level " + levelType.get(i) + "<br/>";
            analysisText += levelNum.get(i) + "<br/>";
            carPrintText += "<div style=\"text-align:right;width:100%\"><span style=\"float:left\">Level " + levelType.get(i) + " </span>\n" +
                    levelNum.get(i) + "</div>";
        }

        labelText += "</p></body></html>";
        analysisText += "</p></body></html>";

        levelLabel.setText(labelText);
        levelAnalysis.setText(analysisText);

        //Most Popular Car Panel
        JPanel popularCarPanel = new JPanel(new GridBagLayout());
        popularCarPanel.setBackground(Color.white);
        popularCarPanel.setPreferredSize(new Dimension(250, 250));

        //Find the most popular car
        ArrayList<Car> car = new ArrayList<>();
        ArrayList<Integer> carNum = new ArrayList<>();

        for (Booking booking : FileIO.bookingList){
            boolean isUniqueCar = true;

            for (int i = 0; i < car.size(); i++){
                if (booking.getCar() == car.get(i)){
                    isUniqueCar = false;
                    carNum.set(i, carNum.get(i) + 1);
                    break;
                }
            }

            if (isUniqueCar){
                car.add(booking.getCar());
                carNum.add(1);
            }
        }

        JLabel popularCarTitle = new JLabel("Top 3 Most Booked Cars");
        GUI.JLabelTitleSetup(popularCarTitle);
        JLabel popularCarLabel = new JLabel();
        JLabel popularCarAnalysis = new JLabel();
        String popularCarLabelText = "<html><body><p>#1<br/>#2<br/>#3<br/></p></body></html>";
        popularCarLabel.setText(popularCarLabelText);
        String popularCarAnalysisText = "";
        GUI.JLabelSetup(new JLabel[]{popularCarLabel, popularCarAnalysis});
        carPrintText += "<div><h1 style=\"text-align:center\">Top 3 Most Booked Cars</h1>";

        int highestNum = 0, highest2Num = 0, highest3Num = 0;

        //Find highest number
        for (int i = 0; i < carNum.size(); i++){

            int number = carNum.get(i);

            if (number > highest3Num){

                if (number > highest2Num){

                    if (number > highestNum){
                        highest3Num = highest2Num;
                        highest2Num = highestNum;
                        highestNum = number;
                    }
                    else {
                        highest3Num = highest2Num;
                        highest2Num = number;
                    }
                }
                else {
                    highest3Num = number;
                }
            }
        }

        String highestText = "";
        String highest2Text = "";
        String highest3Text = "";

        for (int i = 0; i < carNum.size(); i++){

            if (carNum.get(i) == highestNum && highestText.equals("")){
                highestText = car.get(i).getNumberPlate();
            }
            else if (carNum.get(i) == highest2Num && highest2Text.equals("")){
                highest2Text = car.get(i).getNumberPlate();
            }
            else if (carNum.get(i) == highest3Num && highest3Text.equals("")){
                highest3Text = car.get(i).getNumberPlate();
            }
        }
        popularCarAnalysisText = "<html><body><p>" + highestText + "<br/>" + highest2Text + "<br/>" + highest3Text + "<br/></p></body></html>";
        popularCarAnalysis.setText(popularCarAnalysisText);

        carPrintText += "<div style=\"text-align:right;width:100%\"><span style=\"float:left\">#1 </span>\n" +
                highestText + "</div>";
        carPrintText += "<div style=\"text-align:right;width:100%\"><span style=\"float:left\">#2 </span>\n" +
                highest2Text + "</div>";
        carPrintText += "<div style=\"text-align:right;width:100%\"><span style=\"float:left\">#3 </span>\n" +
                highest3Text + "</div>";

        carPrintText += "</div></div></body></html>";
        GridBagConstraints panelConstraints = new GridBagConstraints();
        GridBagConstraints constraints = new GridBagConstraints();

        panelConstraints.gridx = 0;
        panelConstraints.gridy = 0;
        panelConstraints.gridwidth = 3;
        colorPanel.add(colorTitle, panelConstraints);
        brandPanel.add(brandTitle, panelConstraints);
        levelPanel.add(levelTitle, panelConstraints);
        popularCarPanel.add(popularCarTitle, panelConstraints);

        panelConstraints.gridy = 1;
        panelConstraints.gridwidth = 1;
        panelConstraints.insets = new Insets(20, 20, 20, 20);
        colorPanel.add(colorLabel, panelConstraints);
        brandPanel.add(brandLabel, panelConstraints);
        levelPanel.add(levelLabel, panelConstraints);
        popularCarPanel.add(popularCarLabel, panelConstraints);

        panelConstraints.gridx = 1;
        colorPanel.add(colorAnalysis, panelConstraints);
        brandPanel.add(brandAnalysis, panelConstraints);
        levelPanel.add(levelAnalysis, panelConstraints);
        popularCarPanel.add(popularCarAnalysis, panelConstraints);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.white);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(colorPanel, constraints);

        constraints.gridx = 1;
        mainPanel.add(brandPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPanel.add(levelPanel, constraints);

        constraints.gridx = 1;
        mainPanel.add(popularCarPanel, constraints);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(550, 350));
        scrollPane.setViewportView(mainPanel);

        GridBagConstraints mainConstraints = new GridBagConstraints();
        mainConstraints.gridx = 0;
        mainConstraints.gridy = 0;
        mainConstraints.anchor = GridBagConstraints.WEST;
        carPanel.add(totalCarNumLabel, mainConstraints);

        mainConstraints.gridy = 1;
        mainConstraints.anchor = GridBagConstraints.CENTER;
        carPanel.add(scrollPane, mainConstraints);

        mainConstraints.gridy = 2;
        constraints.insets = new Insets(20,20,20,20);
        carPanel.add(printCarButton, mainConstraints);

        colorPanel.validate();
        brandPanel.validate();
        mainPanel.validate();
        carPanel.validate();
    }

    private static void generatePaymentPanel(){
        paymentPanel.removeAll();

        int totalBookings = 0;
        double totalRevenue = 0, totalOutstandingPayment = 0, totalBookingPrice = 0, averageBookingPayment;

        for (Booking booking : FileIO.bookingList){
            totalBookings++;

            totalBookingPrice += booking.getTotalPrice();
            totalRevenue += booking.getTotalPrice() - booking.getOutstandingPayment();
            totalOutstandingPayment += booking.getOutstandingPayment();
        }

        averageBookingPayment = totalBookingPrice / totalBookings;
        String labelText = "<html><body><p>Total Number Of Bookings: <br/>Total Revenue: <br/>Total Outstanding Payment: <br/>" +
                "Average Booking Price: <br/></p></body></html>";
        String analysisText = "<html><body><p>" + String.format("%10d", totalBookings) + "<br/>" + String.format("%10.2f", totalRevenue) + "<br/>" + String.format("%10.2f", totalOutstandingPayment) + "<br/>" +
                String.format("%10.2f", averageBookingPayment) + "</p></body></html>";

        JLabel paymentTitle = new JLabel("Payment Analysis");
        GUI.JLabelTitleSetup(paymentTitle);

        JLabel paymentLabel = new JLabel();
        paymentLabel.setText(labelText);
        JLabel paymentAnalysis = new JLabel();
        paymentAnalysis.setText(analysisText);
        GUI.JLabelSetup(new JLabel[]{paymentLabel, paymentAnalysis});
        paymentAnalysis.setHorizontalAlignment(JLabel.RIGHT);

        paymentPrintText = "<html><body><div style=\"max-width:400px\"><h1 style=\"text-align:center\">Payment Analysis</h1>";
        paymentPrintText += "<div style=\"text-align:right;width:100%\"><span style=\"float:left\">Total Number Of Bookings: </span>" +
                totalBookings + "</div>";
        paymentPrintText += "<div style=\"text-align:right;width:100%\"><span style=\"float:left\">Total Revenue: </span>" +
                String.format("%.2f", totalRevenue) + "</div>";
        paymentPrintText += "<div style=\"text-align:right;width:100%\"><span style=\"float:left\">Total Outstanding Payment: </span>" +
                String.format("%.2f", totalOutstandingPayment) + "</div>";
        paymentPrintText += "<div style=\"text-align:right;width:100%\"><span style=\"float:left\">Average Booking Price: </span>" +
                String.format("%.2f", averageBookingPayment) + "</div>";
        paymentPrintText += "</div></body></html>";

        JPanel main = new JPanel(new GridBagLayout());
        main.setPreferredSize(new Dimension(400, 250));
        main.setBackground(Color.white);
        GridBagConstraints paymentConstraints = new GridBagConstraints();
        GridBagConstraints constraints = new GridBagConstraints();

        paymentConstraints.gridx = 0;
        paymentConstraints.gridy = 0;
        paymentConstraints.gridwidth = 2;
        paymentConstraints.fill = GridBagConstraints.HORIZONTAL;
        main.add(paymentTitle, paymentConstraints);

        paymentConstraints.gridy = 1;
        paymentConstraints.gridwidth = 1;
        paymentConstraints.insets = new Insets(20,20,20,20);
        main.add(paymentLabel, paymentConstraints);

        paymentConstraints.gridx = 1;
        paymentConstraints.anchor = GridBagConstraints.EAST;
        main.add(paymentAnalysis, paymentConstraints);

        constraints.gridx = 0;
        constraints.gridy = 0;
        paymentPanel.add(main, constraints);

        constraints.gridy = 1;
        constraints.insets = new Insets(10,10,10,10);
        paymentPanel.add(printPaymentButton, constraints);
    }

    private static void generateFeedbackPanel(){
        feedbackPanel.removeAll();

        boolean isLeft = true;
        int rowNum = 0, ratingSum = 0, ratingNum = 0;
        double ratingAverage;
        String reportText = "";

        JPanel main = new JPanel(new GridBagLayout());
        main.setBackground(Color.white);

        for (Feedback feedback : FileIO.feedbackList){
            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBackground(Color.white);
            JLabel userLabel = new JLabel("Username: ");
            JLabel ratingLabel = new JLabel("Rating: ");
            JLabel commentLabel = new JLabel("Comment: ");
            JLabel userNameDisplay = new JLabel();
            userNameDisplay.setText(feedback.getCustomer().getUsername());
            JLabel ratingDisplay = new JLabel();
            ratingDisplay.setText(String.valueOf(feedback.getRating()));
            GUI.JLabelSetup(new JLabel[]{userLabel, ratingLabel, commentLabel, userNameDisplay, ratingDisplay});

            JTextArea commentDisplay = new JTextArea();
            Border border = BorderFactory.createLineBorder(Color.black);
            commentDisplay.setBorder(border);
            commentDisplay.setPreferredSize(new Dimension(200, 100));
            commentDisplay.setText(feedback.getComment());
            commentDisplay.setEditable(false);

            GridBagConstraints feedbackConstraints = new GridBagConstraints();
            feedbackConstraints.anchor = GridBagConstraints.WEST;
            feedbackConstraints.gridx = 0;
            feedbackConstraints.gridy = 0;
            panel.add(userLabel, feedbackConstraints);

            feedbackConstraints.gridx = 1;
            panel.add(userNameDisplay, feedbackConstraints);

            feedbackConstraints.gridx = 0;
            feedbackConstraints.gridy = 1;
            panel.add(ratingLabel, feedbackConstraints);

            feedbackConstraints.gridx = 1;
            panel.add(ratingDisplay, feedbackConstraints);

            feedbackConstraints.gridx = 0;
            feedbackConstraints.gridy = 2;
            panel.add(commentLabel, feedbackConstraints);

            feedbackConstraints.gridy = 3;
            feedbackConstraints.gridwidth = 2;
            panel.add(commentDisplay, feedbackConstraints);

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets = new Insets(10,10,10,10);
            constraints.gridy = rowNum;

            if (isLeft){
                isLeft = false;
                constraints.gridx = 0;
            }
            else {
                isLeft = true;
                constraints.gridx = 1;
                rowNum++;
            }
            main.add(panel, constraints);

            ratingSum += feedback.getRating();
            ratingNum++;

            reportText += "<div><p>Username: " + feedback.getCustomer().getUsername() + "<br/>";
            reportText += "Rating: " + feedback.getRating() + "<br/>Comment<br/></p>";
            reportText += "<div style=\"max-width:200\"><p>" + feedback.getComment() + "</p></div></div>";
        }

        ratingAverage = ratingSum / ratingNum;

        JLabel ratingAverageLabel = new JLabel();
        GUI.JLabelSetup(ratingAverageLabel);
        ratingAverageLabel.setText("Average User Rating: " + String.format("%.2f", ratingAverage) + " / 10.00");
        reportText = "<h2>Average User Rating: " + String.format("%.2f", ratingAverage) + "</h2>" + reportText;

        feedbackPrintText = "<html><body><div style=\"max-width:400px;align:center\">" + reportText;
        feedbackPrintText += "</div></body></html>";

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(main);
        scrollPane.setPreferredSize(new Dimension(550, 350));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridy = 0;
        feedbackPanel.add(ratingAverageLabel, constraints);

        constraints.gridy = 1;
        feedbackPanel.add(scrollPane, constraints);

        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10,10,10,10);
        feedbackPanel.add(printFeedbackButton, constraints);
    }

    private void generateReport(String reportType, String printText){
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
            Date date = new Date();
            String fileName = reportType + "_" + dateFormat.format(date) + ".html";

            FileWriter fw = new FileWriter(fileName);

            fw.write(printText);

            fw.close();

            JOptionPane.showMessageDialog(this, "Report printed successfully!", "Receipt Printed", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IOException ioException){
            System.out.println("Unable to open file. Please try again.");
            ioException.printStackTrace();
        }
        catch (Exception e){
            System.out.println("An unexpected error has occurred. Please try again.");
        }
    }
}
