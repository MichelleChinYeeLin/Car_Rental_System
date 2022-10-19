import java.util.Date;

public class Booking {

//    private int bookingID; 这个不懂要不要
//    private Customer customer; 这个应该要
    private Car car;
    private double totalPrice;
    private boolean paid;
    private Date startDate;
    private Date endDate;

    public Booking (Car car, double totalPrice, boolean paid, Date startDate, Date endDate) {
        this.car = car;
        this.totalPrice = totalPrice;
        this.paid = paid;
        this.startDate = startDate;
        this.endDate = endDate;
    }


}
