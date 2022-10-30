import java.util.Date;

public class Booking {

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
}
