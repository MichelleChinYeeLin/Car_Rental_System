import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Booking {

    public enum Month{
        ANY(0),
        JANUARY(1),
        FEBRUARY(2),
        MARCH(3),
        APRIL(4),
        MAY(5),
        JUNE(6),
        JULY(7),
        AUGUST(8),
        SEPTEMBER(9),
        OCTOBER(10),
        NOVEMBER(11),
        DECEMBER(12);

        private final int value;
        private Month(int value){
            this.value = value;
        }

        public int getValue(){
            return value;
        }
    }

    public enum Status{
        ANY,
        BOOKED,
        CONFIRMED,
        IN_PROGRESS,
        RETURNED,
        COMPLETED;
    }

    public enum PenaltyType{
        ANY,
        NONE,
        LATE,
        MINOR_DAMAGE,
        LATE_MINOR_DAMAGE,
        MAJOR_DAMAGE,
        LATE_MAJOR_DAMAGE;
    }

    //private String bookingID;
    private Car car;
    private Customer customer;
    private double totalPrice;
    private double outstandingPayment;
    private Status status;
    private PenaltyType penalty;
    private Date startDate;
    private Date endDate;

    public Booking (Car car, Customer customer, double totalPrice, double outstandingPayment, Status status, PenaltyType penalty, Date startDate, Date endDate) {
        this.car = car;
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.outstandingPayment = outstandingPayment;
        this.status = status;
        this.penalty = penalty;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Booking (Car car, Customer customer, Status status, Date startDate, Date endDate) {
        this.car = car;
        this.customer = customer;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = calcTotalPrice(car, startDate, endDate);
        this.totalPrice -= calcMemberDiscount();
        this.outstandingPayment = totalPrice;
        this.penalty = PenaltyType.NONE;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getOutstandingPayment() {
        return outstandingPayment;
    }

    public void setOutstandingPayment(double outstandingPayment) {
        this.outstandingPayment = outstandingPayment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;

        if (status == Status.RETURNED){
            calcTotalPrice();
        }
    }

    public PenaltyType getPenalty() {
        return penalty;
    }

    public void setPenalty(PenaltyType penalty) {
        this.penalty = penalty;

        if (penalty != PenaltyType.NONE){
            calcTotalPrice();
        }
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double calcTotalPrice(Car car, Date startDate, Date endDate){
        long milliSecDiff = Math.abs(endDate.getTime() - startDate.getTime());
        long duration = TimeUnit.DAYS.convert(milliSecDiff, TimeUnit.MILLISECONDS);

        return car.getPrice() * duration;
    }

    public void calcTotalPrice(){
        totalPrice += calcPenaltyFee();
    }

    public double calcMemberDiscount(){
        if (customer.getPoints() > 500){
            return totalPrice * 0.2;
        }
        else if (customer.getPoints() > 200){
            return totalPrice * 0.1;
        }
        else if (customer.getPoints() < 0) {
            return -totalPrice * 0.1;
        }

        return 0;
    }

    public double calcPenaltyFee(){
        double penaltyFee = 0;

        if (penalty == PenaltyType.LATE){
            penaltyFee = totalPrice * 0.05;
        }

        else if (penalty == PenaltyType.MINOR_DAMAGE){
            penaltyFee = totalPrice * 0.10;
        }

        else if (penalty == PenaltyType.LATE_MINOR_DAMAGE){
            penaltyFee = totalPrice * 0.15;
        }

        else if (penalty == PenaltyType.MAJOR_DAMAGE){
            penaltyFee = totalPrice * 0.20;
        }

        else if (penalty == PenaltyType.LATE_MAJOR_DAMAGE){
            penaltyFee = totalPrice * 0.25;
        }

        return penaltyFee;
    }

    public static boolean makePayment(Booking booking, double payment){
        if (booking.getOutstandingPayment() < payment){
            return false;
        }

        booking.setOutstandingPayment(booking.getOutstandingPayment() - payment);

        if (booking.getOutstandingPayment() == 0){
            booking.setStatus(Status.COMPLETED);
        }

        return true;
    }

    private static boolean validateCarDetails(String numberPlate, String customerName, Status status, PenaltyType penalty, Date startDate, Date endDate){
        try{
            boolean numberPlateFound = false;
            boolean customerNameFound = false;
            if(numberPlate.equals("") || customerName.equals("")){
                throw new EmptyInputException();
            }

            for (Car car : FileIO.carList){
                if (car.getNumberPlate().equalsIgnoreCase(numberPlate)){
                    numberPlateFound = true;
                }
            }

            if (!numberPlateFound){
                throw new NumberPlateNotFoundException();
            }

            for (Customer customer : FileIO.customerList){
                if (customer.getUsername().equalsIgnoreCase(customerName)){
                    customerNameFound = true;
                }
            }

            if (!customerNameFound){
                throw new NameNotFoundException();
            }

            if (status == Status.ANY){
                throw new InvalidStatusException();
            }

            if (penalty == PenaltyType.ANY){
                throw new InvalidPenaltyException();
            }

            if (startDate.after(endDate)){
                throw new InvalidDateDurationException();
            }

            return true;
        }
        catch (EmptyInputException emptyInputException){
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "All fields require an input!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        catch (NumberPlateNotFoundException numberPlateNotFoundException){
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Car not found!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        catch (NameNotFoundException nameNotFoundException){
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Customer not found!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        catch (InvalidDateDurationException invalidDateDurationException){
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "End date must be after the start date!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        catch (InvalidStatusException invalidStatusException){
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Status must not be \"ANY\"! ", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        catch (InvalidPenaltyException invalidPenaltyException){
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Penalty must not be \"ANY\"! ", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }

        return false;
    }

    private static boolean validateCarDetails(String numberPlate, Date startDate, Date endDate){

        try{
            boolean numberPlateFound = false;

            if(numberPlate.equals("")){
                throw new EmptyInputException();
            }

            for (Car car : FileIO.carList){
                if (car.getNumberPlate().equalsIgnoreCase(numberPlate)){
                    numberPlateFound = true;
                }
            }

            if (!numberPlateFound){
                throw new NumberPlateNotFoundException();
            }

            if (startDate.after(endDate)){
                throw new InvalidDateDurationException();
            }

            return true;
        }
        catch (EmptyInputException emptyInputException){
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "All fields require an input!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        catch (NumberPlateNotFoundException numberPlateNotFoundException){
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Car not found!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        catch (InvalidDateDurationException invalidDateDurationException){
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "End date must be after the start date!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }

        return false;
    }

    public static boolean addBooking(Car car, Customer customer, Date startDate, Date endDate){
        boolean flag = false;
        try{
            if (startDate.after(endDate)){
                throw new InvalidDateDurationException();
            }
            Status status = Status.BOOKED;
            Booking booking = new Booking(car, customer, status, startDate, endDate);
            FileIO.bookingList.add(booking);
            flag = true;
        }
        catch (InvalidDateDurationException invalidDateDurationException){
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "End date must be after the start date!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        catch(Exception exception){
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Something wrong!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        return flag;
    }

    public static void editBookingDetails(int numberValue, String numberPlate, String customerName, Status status, PenaltyType penalty, Date startDate, Date endDate){
        Booking booking = FileIO.bookingList.get(numberValue - 1);
        boolean isValid = validateCarDetails(numberPlate, customerName, status, penalty, startDate, endDate);

        if (isValid){
            for (Car car : FileIO.carList){
                if (car.getNumberPlate().equalsIgnoreCase(numberPlate)){
                    booking.setCar(car);
                    break;
                }
            }

            for (Customer customer : FileIO.customerList){
                if (customer.getUsername().equalsIgnoreCase(customerName)){
                    booking.setCustomer(customer);
                    break;
                }
            }

            booking.setPenalty(penalty);
            booking.setStatus(status);
            booking.setStartDate(startDate);
            booking.setEndDate(endDate);
        }
    }

    public static void editBookingDetails(Booking customerBooking, String numberPlate, Date startDate, Date endDate){
        boolean isValid = validateCarDetails(numberPlate, startDate, endDate);

        if (isValid){
            for(Booking booking : FileIO.bookingList){
                if (booking == customerBooking){
                    for (Car car : FileIO.carList){
                        if (car.getNumberPlate().equalsIgnoreCase(numberPlate)){
                            booking.setCar(car);
                            break;
                        }
                    }

                    booking.setStartDate(startDate);
                    booking.setEndDate(endDate);
                }
                break;
            }
        }
    }

    public static void deleteBooking(int numberValue){
        FileIO.bookingList.remove(numberValue - 1);
    }

    public static void deleteBooking(Booking booking){
        FileIO.bookingList.remove(booking);
    }

    public static ArrayList<Booking> searchBooking(String numberPlate, String customerName, double totalPrice, Status status, PenaltyType penalty, int startDay, Month startMonth, String startYear, int endDay, Month endMonth, String endYear){
        ArrayList<Booking> bookingList = FileIO.getBookingList();
        ArrayList<Booking> searchedBooking = new ArrayList<>();

        for (Booking booking : bookingList){
            boolean isMatch = true;

            if(!booking.getCar().getNumberPlate().toLowerCase().contains(numberPlate.toLowerCase())){
                isMatch = false;
            }

            if(!booking.getCustomer().getUsername().toLowerCase().contains(customerName.toLowerCase())){
                isMatch = false;
            }

            if(booking.getTotalPrice() > totalPrice){
                isMatch = false;
            }

            if(status != Status.ANY){

                if(booking.getStatus() != status){
                    isMatch = false;
                }
            }

            if (penalty != PenaltyType.ANY){

                if(booking.getPenalty() != penalty){
                    isMatch = false;
                }
            }

            Date startDate = convertToDate(startDay, startMonth, startYear);
            Date endDate = convertToDate(endDay, endMonth, endYear);
            SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
            SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
            SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

            if(startDate != null){

                Date bookingStartDate = booking.getStartDate();

                if(!dayFormat.format(startDate).equals(dayFormat.format(bookingStartDate)) && startDay != 0){
                    isMatch = false;
                }

                if(!monthFormat.format(startDate).equals(monthFormat.format(bookingStartDate)) && startMonth != Month.ANY){
                    isMatch = false;
                }

                if(!yearFormat.format(startDate).equals(yearFormat.format(bookingStartDate)) && !startYear.equalsIgnoreCase("ANY")){
                    isMatch = false;
                }
            }

            if(endDate != null){

                Date bookingEndDate = booking.getEndDate();

                if(!dayFormat.format(endDate).equals(dayFormat.format(bookingEndDate)) && endDay != 0){
                    isMatch = false;
                }

                if(!monthFormat.format(endDate).equals(monthFormat.format(bookingEndDate)) && endMonth != Month.ANY){
                    isMatch = false;
                }

                if(!yearFormat.format(endDate).equals(yearFormat.format(bookingEndDate)) && !endYear.equalsIgnoreCase("ANY")){
                    isMatch = false;
                }
            }

            if(isMatch){
                searchedBooking.add(booking);
            }
        }

        return searchedBooking;
    }

    public static Date convertToDate(int day, Month month, String year){
        try{
            String dateFormatString = "";
            String dateText = "";
            boolean hasPrevious = false;

            if (day != 0){
                dateFormatString += "dd";
                dateText += day;
                hasPrevious = true;
            }

            if (month.getValue() != 0){

                if (hasPrevious){
                    dateFormatString += "-";
                    dateText += "-";
                }

                dateFormatString += "MM";
                dateText += month.getValue();
                hasPrevious = true;
            }

            if (!year.equalsIgnoreCase("ANY")){

                if (hasPrevious){
                    dateFormatString += "-";
                    dateText += "-";
                }

                dateFormatString += "yyyy";
                dateText += year;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatString);

            if (dateText.equals("") && dateFormatString.equals("")){
                return null;
            }

            Date date = dateFormat.parse(dateText);
            return date;
        }
        catch (ParseException parseException){
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Date unable to parse.");
            return null;
        }
    }

    public static boolean isValidDate(int day, Month month, String year){
        boolean isValid = true;

        if (day < 1){
            isValid = false;
        }

        if (month == Month.ANY){
            isValid = false;
        }

        if (year.equalsIgnoreCase("ANY")){
            isValid = false;
        }

        return isValid;
    }


}
