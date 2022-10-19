import java.util.ArrayList;

public class Customer extends User{

    private String name;
    private String email;
    private String address;
//    private ArrayList<Booking> myBookings = new ArrayList<>();

    public Customer(String username, String password, String name, String email, String address){
        super(username, password);
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    public ArrayList<Booking> getMyBookings() {
//        return myBookings;
//    }
//
//    public void setMyBookings(ArrayList<Booking> myBookings) {
//        this.myBookings = myBookings;
//    }

}
