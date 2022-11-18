import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Booking {

    private String bookingID;
    private Car car;
    private Customer customer;
    private double totalPrice;
    private String status;
    private Date startDate;
    private Date endDate;

    public Booking (Car car, Customer customer, double totalPrice, String status, Date startDate, Date endDate) {
        this.car = car;
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Booking (Car car, Customer customer, String status, Date startDate, Date endDate) {
        this.car = car;
        this.customer = customer;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = calcTotalPrice(car, startDate, endDate);
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public double calcTotalPrice(Car car, Date startDate, Date endDate){
        long milliSecDiff = Math.abs(endDate.getTime() - startDate.getTime());
        long duration = TimeUnit.DAYS.convert(milliSecDiff, TimeUnit.MILLISECONDS);

        return car.getPrice() * duration;
    }

    public static boolean addBooking(Car car, Customer customer, Date startDate, Date endDate){
        try{
            String status = "Booked";
            Booking booking = new Booking(car, customer, status, startDate, endDate);
            ArrayList<Booking> bookingList = FileIO.getBookingList();
            bookingList.add(booking);
            FileIO.setBookingList(bookingList);

            return true;
        }
        catch(Exception exception){
            return false;
        }
    }

    public static boolean deleteBooking(int index){
        try{
            ArrayList<Booking> bookingList = FileIO.getBookingList();
            bookingList.remove(index);
            FileIO.setBookingList(bookingList);

            return true;
        }
        catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
            return false;
        }
    }
}
