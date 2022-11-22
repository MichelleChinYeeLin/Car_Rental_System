import sun.java2d.pipe.SpanShapeRenderer;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Booking {

    //private String bookingID;
    private Car car;
    private Customer customer;
    private double totalPrice;
    private Status status;
    private Date startDate;
    private Date endDate;

    public Booking (Car car, Customer customer, double totalPrice, Status status, Date startDate, Date endDate) {
        this.car = car;
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Booking (Car car, Customer customer, Status status, Date startDate, Date endDate) {
        this.car = car;
        this.customer = customer;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = calcTotalPrice(car, customer, startDate, endDate);
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public double calcTotalPrice(Car car, Customer customer, Date startDate, Date endDate){
        long milliSecDiff = Math.abs(endDate.getTime() - startDate.getTime());
        long duration = TimeUnit.DAYS.convert(milliSecDiff, TimeUnit.MILLISECONDS);

        if (customer.getPoints() > 200){
            return car.getPrice() * duration * 0.9;
        }
        else if (customer.getPoints() > 500){
            return car.getPrice() * duration * 0.8;
        }

        return car.getPrice() * duration;
    }

    private static boolean validateCarDetails(String numberPlate, String customerName, Status status, Date startDate, Date endDate){
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
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Status must not be \'ANY\'! ", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }

        return false;
    }

    public static void addBooking(Car car, Customer customer, Date startDate, Date endDate){
        try{
            if (startDate.after(endDate)){
                throw new InvalidDateDurationException();
            }
            Status status = Status.BOOKED;
            Booking booking = new Booking(car, customer, status, startDate, endDate);
            FileIO.bookingList.add(booking);
        }
        catch (InvalidDateDurationException invalidDateDurationException){
            //TODO show message for customer
        }
        catch(Exception exception){
            //TODO show message for customer
        }
    }

    public static void editBookingDetails(int numberValue, String numberPlate, String customerName, Status status, Date startDate, Date endDate){
        Booking booking = FileIO.bookingList.get(numberValue - 1);
        boolean isValid = validateCarDetails(numberPlate, customerName, status, startDate, endDate);

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

            booking.setStatus(status);
            booking.setStartDate(startDate);
            booking.setEndDate(endDate);
        }
    }

    public static void deleteBooking(int numberValue){
        FileIO.bookingList.remove(numberValue - 1);
    }

    public static ArrayList<Booking> searchBooking(String numberPlate, String customerName, double totalPrice, Status status, int startDay, Month startMonth, String startYear, int endDay, Month endMonth, String endYear){
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
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Date unable to parse.");
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
        COMPLETED;
    }
}
